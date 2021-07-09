
package com.tip.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ReservedUnit complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ReservedUnit">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="unitNr" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="companyNr" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="customerNr" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="customerName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="unitStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="resrvdRemarks" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="resrvdOutDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReservedUnit", propOrder = {
    "unitNr",
    "companyNr",
    "customerNr",
    "customerName",
    "unitStatus",
    "resrvdRemarks",
    "resrvdOutDate"
})
public class ReservedUnit {

    protected int unitNr;
    protected int companyNr;
    protected int customerNr;
    @XmlElement(required = true, nillable = true)
    protected String customerName;
    @XmlElement(required = true, nillable = true)
    protected String unitStatus;
    @XmlElement(required = true, nillable = true)
    protected String resrvdRemarks;
    @XmlElement(required = true, nillable = true)
    protected String resrvdOutDate;

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
     * Gets the value of the customerName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Sets the value of the customerName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerName(String value) {
        this.customerName = value;
    }

    /**
     * Gets the value of the unitStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnitStatus() {
        return unitStatus;
    }

    /**
     * Sets the value of the unitStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnitStatus(String value) {
        this.unitStatus = value;
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

}
