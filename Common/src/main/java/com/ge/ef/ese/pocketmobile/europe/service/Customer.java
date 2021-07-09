
package com.ge.ef.ese.pocketmobile.europe.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Customer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Customer">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="customerCompanyNr" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="customerNr" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="customerNumberCombi" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="addressTypeRef" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="addr" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="city" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="zipCd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="countryName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="checkinBillingFlag" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="checkinBillingText" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="checkoutMiscFlag" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="checkoutMiscText" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="checkinMiscText" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="checkinMiscFlag" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="customerOperationalEmail" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="customerOperationalFax" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Customer", propOrder = {
    "customerCompanyNr",
    "customerNr",
    "customerNumberCombi",
    "addressTypeRef",
    "name",
    "addr",
    "city",
    "zipCd",
    "countryName",
    "checkinBillingFlag",
    "checkinBillingText",
    "checkoutMiscFlag",
    "checkoutMiscText",
    "checkinMiscText",
    "checkinMiscFlag",
    "customerOperationalEmail",
    "customerOperationalFax"
})
public class Customer {

    protected int customerCompanyNr;
    protected int customerNr;
    @XmlElement(required = true)
    protected String customerNumberCombi;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer addressTypeRef;
    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true, nillable = true)
    protected String addr;
    @XmlElement(required = true, nillable = true)
    protected String city;
    @XmlElement(required = true, nillable = true)
    protected String zipCd;
    @XmlElement(required = true, nillable = true)
    protected String countryName;
    @XmlElement(required = true, nillable = true)
    protected String checkinBillingFlag;
    @XmlElement(required = true, nillable = true)
    protected String checkinBillingText;
    @XmlElement(required = true, nillable = true)
    protected String checkoutMiscFlag;
    @XmlElement(required = true, nillable = true)
    protected String checkoutMiscText;
    @XmlElement(required = true, nillable = true)
    protected String checkinMiscText;
    @XmlElement(required = true, nillable = true)
    protected String checkinMiscFlag;
    @XmlElement(required = true, nillable = true)
    protected String customerOperationalEmail;
    @XmlElement(required = true, nillable = true)
    protected String customerOperationalFax;

    /**
     * Gets the value of the customerCompanyNr property.
     * 
     */
    public int getCustomerCompanyNr() {
        return customerCompanyNr;
    }

    /**
     * Sets the value of the customerCompanyNr property.
     * 
     */
    public void setCustomerCompanyNr(int value) {
        this.customerCompanyNr = value;
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
     * Gets the value of the customerNumberCombi property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerNumberCombi() {
        return customerNumberCombi;
    }

    /**
     * Sets the value of the customerNumberCombi property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerNumberCombi(String value) {
        this.customerNumberCombi = value;
    }

    /**
     * Gets the value of the addressTypeRef property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAddressTypeRef() {
        return addressTypeRef;
    }

    /**
     * Sets the value of the addressTypeRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAddressTypeRef(Integer value) {
        this.addressTypeRef = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the addr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddr() {
        return addr;
    }

    /**
     * Sets the value of the addr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddr(String value) {
        this.addr = value;
    }

    /**
     * Gets the value of the city property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the value of the city property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCity(String value) {
        this.city = value;
    }

    /**
     * Gets the value of the zipCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZipCd() {
        return zipCd;
    }

    /**
     * Sets the value of the zipCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZipCd(String value) {
        this.zipCd = value;
    }

    /**
     * Gets the value of the countryName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * Sets the value of the countryName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountryName(String value) {
        this.countryName = value;
    }

    /**
     * Gets the value of the checkinBillingFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCheckinBillingFlag() {
        return checkinBillingFlag;
    }

    /**
     * Sets the value of the checkinBillingFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCheckinBillingFlag(String value) {
        this.checkinBillingFlag = value;
    }

    /**
     * Gets the value of the checkinBillingText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCheckinBillingText() {
        return checkinBillingText;
    }

    /**
     * Sets the value of the checkinBillingText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCheckinBillingText(String value) {
        this.checkinBillingText = value;
    }

    /**
     * Gets the value of the checkoutMiscFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCheckoutMiscFlag() {
        return checkoutMiscFlag;
    }

    /**
     * Sets the value of the checkoutMiscFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCheckoutMiscFlag(String value) {
        this.checkoutMiscFlag = value;
    }

    /**
     * Gets the value of the checkoutMiscText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCheckoutMiscText() {
        return checkoutMiscText;
    }

    /**
     * Sets the value of the checkoutMiscText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCheckoutMiscText(String value) {
        this.checkoutMiscText = value;
    }

    /**
     * Gets the value of the checkinMiscText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCheckinMiscText() {
        return checkinMiscText;
    }

    /**
     * Sets the value of the checkinMiscText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCheckinMiscText(String value) {
        this.checkinMiscText = value;
    }

    /**
     * Gets the value of the checkinMiscFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCheckinMiscFlag() {
        return checkinMiscFlag;
    }

    /**
     * Sets the value of the checkinMiscFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCheckinMiscFlag(String value) {
        this.checkinMiscFlag = value;
    }

    /**
     * Gets the value of the customerOperationalEmail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerOperationalEmail() {
        return customerOperationalEmail;
    }

    /**
     * Sets the value of the customerOperationalEmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerOperationalEmail(String value) {
        this.customerOperationalEmail = value;
    }

    /**
     * Gets the value of the customerOperationalFax property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerOperationalFax() {
        return customerOperationalFax;
    }

    /**
     * Sets the value of the customerOperationalFax property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerOperationalFax(String value) {
        this.customerOperationalFax = value;
    }

}
