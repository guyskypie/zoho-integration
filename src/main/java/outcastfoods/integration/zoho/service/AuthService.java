package outcastfoods.integration.zoho.service;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;
import outcastfoods.integration.zoho.auth.Token;

import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.stream.Collectors;

@ApplicationScoped
public class AuthService {

    private static final Logger LOG = Logger.getLogger(AuthService.class);

    @ConfigProperty(name = "quarkus.oidc-client.client-id")
    String clientId;

    @ConfigProperty(name = "quarkus.oidc-client.credentials.secret")
    String clientSecret;

    private Token token = null;

    public Token getAccessToken() throws IOException, InterruptedException {

        if(token == null || !token.isValid()){
            String url = "https://accounts.zoho.com/oauth/v2/token";

            HashMap<String, String> parameters = new HashMap<>();
            parameters.put("client_id", clientId);
            parameters.put("client_secret", clientSecret);
            parameters.put("grant_type", "client_credentials");
            parameters.put("scope", "ZohoBooks.invoices.READ ZohoBooks.contacts.READ ZohoBooks.customerpayments.READ ZohoBooks.creditnotes.READ ZohoBooks.invoices.CREATE");
            String form = parameters.keySet().stream()
                    .map(key -> key + "=" + URLEncoder.encode(parameters.get(key), StandardCharsets.UTF_8))
                    .collect(Collectors.joining("&"));

            //String encoding = Base64.getEncoder().encodeToString(keys.getBytes());
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url))
                    .headers("Content-Type", "application/x-www-form-urlencoded")
                    //.headers("Content-Type", "application/x-www-form-urlencoded", "Authorization", "Basic "+encoding)
                    .POST(HttpRequest.BodyPublishers.ofString(form)).build();
            HttpResponse<?> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.statusCode() + response.body().toString());
            String la = "sdsds";
            if(response.statusCode()==200){
                LocalDateTime now = LocalDateTime.now();

                String responseBody = response.body().toString();
                String [] accessTokenPart = responseBody.split(",");
                String [] accessTokenArr = accessTokenPart[0].split(":");
                //String tokenValue = accessTokenArr[1].replace('"','');;
                String tokenValue = accessTokenArr[1].replaceAll("^\"|\"$", "");
                token = new Token(tokenValue, now.plusSeconds(3500));
            } else {
                LOG.error("Could not aurthenticate: "  + response.body().toString());
            }

        }


        return token;
    }
}
