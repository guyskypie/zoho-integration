package my.integration.zoho.model.externalapi;

import java.math.BigDecimal;

public class InvoiceLineItem {




        public long line_item_id;
        public long item_id;
        public long project_id;
        public String project_name;
        public String item_type;
        public String product_type;
        public String expense_id;
        public String name;
        public int item_order;
        public int bcy_rate;
        public int rate;
        public int quantity;
        public String unit;
        public int discount_amount;
        public int discount;
        public long tax_id;
        public String tax_name;
        public String tax_type;
        public BigDecimal tax_percentage;
        public BigDecimal item_total;

        public InvoiceLineItem() {
            super();
        }

        public long getLine_item_id() {
            return line_item_id;
        }

        public void setLine_item_id(long line_item_id) {
            this.line_item_id = line_item_id;
        }

        public long getItem_id() {
            return item_id;
        }

        public void setItem_id(long item_id) {
            this.item_id = item_id;
        }

        public long getProject_id() {
            return project_id;
        }

        public void setProject_id(long project_id) {
            this.project_id = project_id;
        }

        public String getProject_name() {
            return project_name;
        }

        public void setProject_name(String project_name) {
            this.project_name = project_name;
        }

        public String getItem_type() {
            return item_type;
        }

        public void setItem_type(String item_type) {
            this.item_type = item_type;
        }

        public String getProduct_type() {
            return product_type;
        }

        public void setProduct_type(String product_type) {
            this.product_type = product_type;
        }

        public String getExpense_id() {
            return expense_id;
        }

        public void setExpense_id(String expense_id) {
            this.expense_id = expense_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getItem_order() {
            return item_order;
        }

        public void setItem_order(int item_order) {
            this.item_order = item_order;
        }

        public int getBcy_rate() {
            return bcy_rate;
        }

        public void setBcy_rate(int bcy_rate) {
            this.bcy_rate = bcy_rate;
        }

        public int getRate() {
            return rate;
        }

        public void setRate(int rate) {
            this.rate = rate;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public int getDiscount_amount() {
            return discount_amount;
        }

        public void setDiscount_amount(int discount_amount) {
            this.discount_amount = discount_amount;
        }

        public int getDiscount() {
            return discount;
        }

        public void setDiscount(int discount) {
            this.discount = discount;
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

        public String getTax_type() {
            return tax_type;
        }

        public void setTax_type(String tax_type) {
            this.tax_type = tax_type;
        }

        public BigDecimal getTax_percentage() {
            return tax_percentage;
        }

        public void setTax_percentage(BigDecimal tax_percentage) {
            this.tax_percentage = tax_percentage;
        }

        public BigDecimal getItem_total() {
            return item_total;
        }

        public void setItem_total(BigDecimal item_total) {
            this.item_total = item_total;
        }


}
