package outcastfoods.integration.zoho.zohoapiclient;


import com.fasterxml.jackson.databind.ObjectMapper;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.jboss.logging.Logger;
import outcastfoods.integration.zoho.model.externalapi.InvoiceCreate;
import outcastfoods.integration.zoho.model.externalapi.InvoiceMultipart;
import outcastfoods.integration.zoho.model.internal.InvoiceInfo;
import outcastfoods.integration.zoho.service.AuthService;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import java.util.stream.Collectors;


@ApplicationScoped
public class CreateInvoiceClient {

    private static final Logger LOG = Logger.getLogger(CreateInvoiceClient.class);

    @Inject
    AuthService authService;

    /**
     * @return response success or error string
     * @throws IOException
     * @throws InterruptedException
     */
    public InvoiceInfo createInvoice(InvoiceCreate invoiceCreate) throws IOException, InterruptedException {

        ObjectMapper objectMapper = new ObjectMapper();

        String invoiceJSON = objectMapper.writeValueAsString(invoiceCreate);
        InvoiceMultipart invoiceMultipart = new InvoiceMultipart();
        invoiceMultipart.setJSONString(invoiceJSON);

        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("JSONString", invoiceJSON);
        //parameters.put("JSONString", "{\"customer_id\":\"REDACTED_ID\",\"reference_number\":\"guy-test1\",\"line_items\":[{\"item_id\":\"1471239000000068016\",\"rate\":\"35\",\"quantity\":\"4\"}],\"date\":\"2022-03-07\"}");
        String form = parameters.keySet().stream()
                .map(key -> key + "=" + URLEncoder.encode(parameters.get(key), StandardCharsets.UTF_8))
                .collect(Collectors.joining("&"));


        String url = "https://www.zohoapis.com/books/v3/invoices?organization_id=671750272";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url))
                .headers("Content-Type", "application/x-www-form-urlencoded")
                .headers("Authorization", authService.getAccessToken().getBearerToken())
                .POST(HttpRequest.BodyPublishers.ofString(form)).build();
        HttpResponse<?> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode() + response.body().toString());


        // jsonString is of type java.lang.String
        JsonObject jsonObject = JsonParser.parseString(response.body().toString()).getAsJsonObject();
        String invoiceId = jsonObject.getAsJsonObject("invoice").get("invoice_id").getAsString();
        String invoiceNumber = jsonObject.getAsJsonObject("invoice").get("invoice_number").getAsString();
        InvoiceInfo invoiceInfo = new InvoiceInfo();
        invoiceInfo.setInvoiceNumber(invoiceNumber);
        invoiceInfo.setId(invoiceId);

        if (response.statusCode() != 201) {
            LOG.error("Could not create invoice: " + response.body().toString());
        }

        return invoiceInfo;
    }

    public File downloadInvoicePdfs(File dstFile, String invoiceIds) throws MalformedURLException {

        String downloadPdfs = "https://www.zohoapis.com/books/v3/invoices/pdf?invoice_ids="+invoiceIds;
        URL url = new URL(downloadPdfs);
        CloseableHttpClient httpclient = HttpClients.custom()
                .setRedirectStrategy(new LaxRedirectStrategy()) // adds HTTP REDIRECT support to GET and POST methods
                .build();
        try {

            HttpGet get = new HttpGet(url.toURI()); // we're using GET but it could be via POST as well
                get.setHeader("Authorization", authService.getAccessToken().getBearerToken());
            File downloaded = httpclient.execute(get, new FileDownloadResponseHandler(dstFile));
            return downloaded;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            IOUtils.closeQuietly(httpclient);
        }
    }

    public class FileDownloadResponseHandler implements ResponseHandler<File> {

        private final File target;

        public FileDownloadResponseHandler(File target) {
            this.target = target;
        }

        @Override
        public File handleResponse(org.apache.http.HttpResponse httpResponse) throws ClientProtocolException, IOException {
            InputStream source = httpResponse.getEntity().getContent();
            FileUtils.copyInputStreamToFile(source, this.target);
            return this.target;
        }
    }

}
