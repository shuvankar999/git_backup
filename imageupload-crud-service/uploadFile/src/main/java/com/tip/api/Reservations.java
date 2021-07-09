
package com.tip.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Reservations complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Reservations">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="unitNr" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="branchNr" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="companyNr" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="customerNr" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="resrvdOutDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="resrvdInDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="resrvdRemarks" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Reservations", propOrder = {
    "unitNr",
    "branchNr",
    "companyNr",
    "customerNr",
    "resrvdOutDate",
    "resrvdInDate",
    "resrvdRemarks"
})
public class Reservations {

    protected int unitNr;
    protected int branchNr;
    protected int companyNr;
    protected int customerNr;
    @XmlElement(required = true)
    protected String resrvdOutDate;
    @XmlElement(required = true, nillable = true)
    protected String resrvdInDate;
    @XmlElement(required = true, nillable = true)
    protected String resrvdRemarks;

    /**
     * Gets the value of the unitNr property.
     * 
     */
    public int getUnitNr() {
        return unitNr;
    }

    /**
     * Sets the value of the unitNr property.
     * 
     */
    public void setUnitNr(int value) {
        this.unitNr = value;
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
     * Gets the value of the companyNr property.
     * 
     */
    public int getCompanyNr() {
        return companyNr;
    }

    /**
     * Sets the value of the companyNr property.
     * 
     */
    public void setCompanyNr(int value) {
        this.companyNr = value;
    }

    /**
     * Gets the value of the customerNr property.
     * 
     */
    public int getCustomerNr() {
        return customerNr;
    }

    /**
     * Sets the value of the customerNr property.
     * 
     */
    public void setCustomerNr(int value) {
        this.customerNr = value;
    }

    /**
     * Gets the value of the resrvdOutDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResrvdOutDate() {
        return resrvdOutDate;
    }

    /**
     * Sets the value of the resrvdOutDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResrvdOutDate(String value) {
        this.resrvdOutDate = value;
    }

    /**
     * Gets the value of the resrvdInDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResrvdInDate() {
        return resrvdInDate;
    }

    /**
     * Sets the value of the resrvdInDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResrvdInDate(String value) {
        this.resrvdInDate = value;
    }

    /**
     * Gets the value of the resrvdRemarks property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResrvdRemarks() {
        return resrvdRemarks;
    }

    /**
     * Sets the value of the resrvdRemarks property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResrvdRemarks(String value) {
        this.resrvdRemarks = value;
    }

}
