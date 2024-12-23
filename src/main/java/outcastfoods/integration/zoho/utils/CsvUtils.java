package outcastfoods.integration.zoho.utils;

import outcastfoods.integration.zoho.mapping.ProductMapping;
import outcastfoods.integration.zoho.model.internal.OrderCsvLine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CsvUtils {

    final static String COMMA_DELIMITER = ",";

    /**
     *
     * @return list of rows with values for each row
     */
    public static List<List<String>> getCsvData(String filePath) throws IOException {
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                records.add(Arrays.asList(values));
            }
        }

        return records.subList(1, records.size());
    }


    public static List<OrderCsvLine>  readOrderLines(String inputFolder) throws IOException {

        List<OrderCsvLine> orderLines = new ArrayList();

        final File folder = new File(inputFolder);
        List<String> filePaths = FileUtils.listFilePathsForFolder(folder);
        for (String filePath : filePaths) {
            if(!filePath.contains("DS_Store")) {


                List<List<String>> csvLines = CsvUtils.getCsvData(filePath);

                for (List<String> csvLine : csvLines) {
                    OrderCsvLine orderCsvLine = new OrderCsvLine();
                    orderCsvLine.setPoNumber(csvLine.get(1));
                    orderCsvLine.setDeliveryDate(csvLine.get(4));
                    orderCsvLine.setStoreCode(csvLine.get(5));
                    orderCsvLine.setStoreDescription(csvLine.get(6));
                    orderCsvLine.setProductCode(csvLine.get(7));
                    orderCsvLine.setPackSize(Double.valueOf(csvLine.get(10)).intValue());
                    orderCsvLine.setQuantity(Double.valueOf(csvLine.get(11)).intValue());

                    orderLines.add(orderCsvLine);
                }
            }
        }

        return orderLines;
    }

    public static String getCaseQuantityForProduct(Map<ProductMapping, String> productToQuantity, ProductMapping product){
        String quantity = productToQuantity.get(product);

        return quantity == null ? "0" : quantity;
    }
}
