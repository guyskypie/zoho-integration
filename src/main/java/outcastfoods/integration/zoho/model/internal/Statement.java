package outcastfoods.integration.zoho.model.internal;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class Statement {

    private String fromDate;
    private String toDate;
    private String contactName;
    private BigDecimal balanceDue;
    private BigDecimal openingbalance;
    private List<StatementLine> statementLines;

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public BigDecimal getBalanceDue() {
        return balanceDue;
    }

    public void setBalanceDue(BigDecimal balanceDue) {
        this.balanceDue = balanceDue;
    }

    public BigDecimal getOpeningbalance() {
        return openingbalance;
    }

    public void setOpeningbalance(BigDecimal openingbalance) {
        this.openingbalance = openingbalance;
    }

    public List<StatementLine> getStatementLines() {
        return statementLines;
    }

    public void setStatementLines(List<StatementLine> statementLines) {
        Collections.reverse(statementLines);
        this.statementLines = statementLines;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }
}
