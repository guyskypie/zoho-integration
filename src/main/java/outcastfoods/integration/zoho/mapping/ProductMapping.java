package outcastfoods.integration.zoho.mapping;

import java.util.Arrays;
import java.util.List;

public enum ProductMapping {

    CLASSIC_FALAFEL("CLF-P001","1471239000000068016","16001651128151","Classic Falafel Mix 220g"),
    CRAZY_FALAFEL("CRF-P001","1471239000000068034","16001651128168"," Crazy Falafel Mix 220g"),
    BURGER_MIX("BROG-P","1471239000000068003","16001651155546", "Burger Mix 230g"),
    FLAPJACKS("FLPJ-P001","1471239000000283021","16001651155553", "Flapjack Mix 250g");

    private String productCode;
    private String itemId;
    private String caseOf6Barcode;
    private String articleDescription;

    ProductMapping(String productCode, String itemId, String caseOf6Barcode, String articleDescription) {
        this.productCode = productCode;
        this.itemId = itemId;
        this.caseOf6Barcode = caseOf6Barcode;
        this.articleDescription = articleDescription;
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

    public String getCaseOf6Barcode() {
        return caseOf6Barcode;
    }

    public void setCaseOf6Barcode(String itemId) {
        this.caseOf6Barcode = caseOf6Barcode;
    }

    public String getArticleDescription() {
        return articleDescription;
    }

    public void setArticleDescription(String articleDescription) {
        this.articleDescription = articleDescription;
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
