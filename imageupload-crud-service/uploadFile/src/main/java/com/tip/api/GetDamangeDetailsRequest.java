
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
 *         &lt;element name="branchNr" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="getDamangeDetailsRequest" type="{http://service.europe.pocketmobile.ese.ef.ge.com}getDamageDetailsInput"/>
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
    "branchNr",
    "getDamangeDetailsRequest"
})
@XmlRootElement(name = "getDamangeDetailsRequest")
public class GetDamangeDetailsRequest {

    @XmlElement(required = true)
    protected String sessionId;
    protected int branchNr;
    @XmlElement(required = true)
    protected GetDamageDetailsInput getDamangeDetailsRequest;

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
     * Gets the value of the getDamangeDetailsRequest property.
     * 
     * @return
     *     possible object is
     *     {@link GetDamageDetailsInput }
     *     
     */
    public GetDamageDetailsInput getGetDamangeDetailsRequest() {
        return getDamangeDetailsRequest;
    }

    /**
     * Sets the value of the getDamangeDetailsRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetDamageDetailsInput }
     *     
     */
    public void setGetDamangeDetailsRequest(GetDamageDetailsInput value) {
        this.getDamangeDetailsRequest = value;
    }

}
