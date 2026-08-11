package my.integration.zoho.model.externalapi;

import java.util.List;

public class Item {

    public long item_id;
    public String name;
    public String status;
    public String description;
    public int rate;
    public String unit;
    public long tax_id;
    public String tax_name;
    public String tax_percentage;
    public String tax_type;
    public String sku;
    public String product_type;
    public List<ItemTaxPreference> item_tax_preferences;

    public class ItemTaxPreference {
        public long tax_id;
        public String tax_specification;
    }

    public long getItem_id() {
        return item_id;
    }

    public void setItem_id(long item_id) {
        this.item_id = item_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public long getTax_id() {
        return tax_id;
    }

    public void setTax_id(long tax_id) {
        this.tax_id = tax_id;
    }

    public String getTax_name() {
        return tax_name;
    }

    public void setTax_name(String tax_name) {
        this.tax_name = tax_name;
    }

    public String getTax_percentage() {
        return tax_percentage;
    }

    public void setTax_percentage(String tax_percentage) {
        this.tax_percentage = tax_percentage;
    }

    public String getTax_type() {
        return tax_type;
    }

    public void setTax_type(String tax_type) {
        this.tax_type = tax_type;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public List<ItemTaxPreference> getItem_tax_preferences() {
        return item_tax_preferences;
    }

    public void setItem_tax_preferences(List<ItemTaxPreference> item_tax_preferences) {
        this.item_tax_preferences = item_tax_preferences;
    }
}
