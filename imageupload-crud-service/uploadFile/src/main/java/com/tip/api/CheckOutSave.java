
package com.tip.api;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sessionId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="transactionId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="branchNr" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="saveRequest" type="{http://service.europe.pocketmobile.ese.ef.ge.com}CheckOutSaveInput"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "sessionId",
    "transactionId",
    "branchNr",
    "saveRequest"
})
@XmlRootElement(name = "checkOutSave")
public class CheckOutSave {

    @XmlElement(required = true)
    protected String sessionId;
    @XmlElement(required = true)
    protected String transactionId;
    protected int branchNr;
    @XmlElement(required = true)
    protected CheckOutSaveInput saveRequest;

    /**
     * Gets the value of the sessionId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * Sets the value of the sessionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSessionId(String value) {
        this.sessionId = value;
    }

    /**
     * Gets the value of the transactionId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * Sets the value of the transactionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionId(String value) {
        this.transactionId = value;
    }

    /**
     * Gets the value of the branchNr property.
     * 
     */
    public int getBranchNr() {
        return branchNr;
    }

    /**
     * Sets the value of the branchNr property.
     * 
     */
    public void setBranchNr(int value) {
        this.branchNr = value;
    }

    /**
     * Gets the value of the saveRequest property.
     * 
     * @return
     *     possible object is
     *     {@link CheckOutSaveInput }
     *     
     */
    public CheckOutSaveInput getSaveRequest() {
        return saveRequest;
    }

    /**
     * Sets the value of the saveRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link CheckOutSaveInput }
     *     
     */
    public void setSaveRequest(CheckOutSaveInput value) {
        this.saveRequest = value;
    }

}
