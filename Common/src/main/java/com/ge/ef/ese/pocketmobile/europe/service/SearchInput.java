
package com.ge.ef.ese.pocketmobile.europe.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SearchInput complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SearchInput">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="unitNr" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="registrationCd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="chassisNr" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="customerNr" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="pickupDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="branchNr" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SearchInput", propOrder = {
    "unitNr",
    "registrationCd",
    "chassisNr",
    "customerNr",
    "pickupDate",
    "branchNr"
})
public class SearchInput {

    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer unitNr;
    @XmlElement(required = true, nillable = true)
    protected String registrationCd;
    @XmlElement(required = true, nillable = true)
    protected String chassisNr;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer customerNr;
    @XmlElement(required = true, nillable = true)
    protected String pickupDate;
    protected int branchNr;

    /**
     * Gets the value of the unitNr property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getUnitNr() {
        return unitNr;
    }

    /**
     * Sets the value of the unitNr property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setUnitNr(Integer value) {
        this.unitNr = value;
    }

    /**
     * Gets the value of the registrationCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegistrationCd() {
        return registrationCd;
    }

    /**
     * Sets the value of the registrationCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegistrationCd(String value) {
        this.registrationCd = value;
    }

    /**
     * Gets the value of the chassisNr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChassisNr() {
        return chassisNr;
    }

    /**
     * Sets the value of the chassisNr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChassisNr(String value) {
        this.chassisNr = value;
    }

    /**
     * Gets the value of the customerNr property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCustomerNr() {
        return customerNr;
    }

    /**
     * Sets the value of the customerNr property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCustomerNr(Integer value) {
        this.customerNr = value;
    }

    /**
     * Gets the value of the pickupDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPickupDate() {
        return pickupDate;
    }

    /**
     * Sets the value of the pickupDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPickupDate(String value) {
        this.pickupDate = value;
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

}
