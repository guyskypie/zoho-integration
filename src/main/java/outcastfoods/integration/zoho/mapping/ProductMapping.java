package outcastfoods.integration.zoho.mapping;

import java.util.Arrays;
import java.util.List;

public enum ProductMapping {

    CLASSIC_FALAFEL("CLF-P001","1471239000000068016"),
    CRAZY_FALAFEL("CRF-P001","1471239000000068034"),
    BURGER_MIX("BROG-P","1471239000000068003"),
    FLAPJACKS("FLPJ-P001","1471239000000283021");

    private String productCode;
    private String itemId;

    ProductMapping(String productCode, String itemId) {
        this.productCode = productCode;
        this.itemId = itemId;
    }


    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public static String findItemIdByProductCode(String productCode){

        List<ProductMapping> productMappings = Arrays.asList(values());

        ProductMapping productMapping = productMappings.stream()
                .filter(mapping -> productCode.equals(mapping.getProductCode()))
                .findFirst()
                .orElse(null);

        return productMapping.getItemId();
    }


    public static ProductMapping findByItemId(String itemId){

        List<ProductMapping> productMappings = Arrays.asList(values());

        ProductMapping productMapping = productMappings.stream()
                .filter(mapping -> itemId.equals(mapping.getItemId()))
                .findFirst()
                .orElse(null);

        return productMapping;
    }

}
