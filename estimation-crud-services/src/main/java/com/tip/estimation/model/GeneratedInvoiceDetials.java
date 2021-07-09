package com.tip.estimation.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@XmlRootElement(name="GeneratedInvoiceDetials")
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class GeneratedInvoiceDetials {

    private String errorCd;
    private Integer docId;
    private String docName;
    private String invoiceNr;

    public String getErrorCd() {
        return errorCd;
    }

    public void setErrorCd(String value) {
        this.errorCd = value;
    }

    public Integer getDocId() {
        return docId;
    }

    public void setDocId(Integer value) {
        this.docId = value;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String value) {
        this.docName = value;
    }

    public String getInvoiceNr() {
        return invoiceNr;
    }

    public void setInvoiceNr(String value) {
        this.invoiceNr = value;
    }

}
