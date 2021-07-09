//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.07.26 at 07:43:35 PM IST 
//


package com.tip.suppliermasterdata.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CountryDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CountryDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="country_skey" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="country_name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="country_code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="phone_code" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="phone_format" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="state_req_flag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="currency_name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="currency_format" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="postal_code_format" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="country_dialing_cd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="region_name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CountryDetails", propOrder = {
    "countrySkey",
    "countryName",
    "countryCode",
    "phoneCode",
    "phoneFormat",
    "stateReqFlag",
    "currencyName",
    "currencyFormat",
    "postalCodeFormat",
    "countryDialingCd",
    "regionName"
})
public class CountryDetails {

    @XmlElement(name = "country_skey")
    protected Integer countrySkey;
    @XmlElement(name = "country_name")
    protected String countryName;
    @XmlElement(name = "country_code")
    protected String countryCode;
    @XmlElement(name = "phone_code")
    protected Integer phoneCode;
    @XmlElement(name = "phone_format")
    protected String phoneFormat;
    @XmlElement(name = "state_req_flag")
    protected String stateReqFlag;
    @XmlElement(name = "currency_name")
    protected String currencyName;
    @XmlElement(name = "currency_format")
    protected String currencyFormat;
    @XmlElement(name = "postal_code_format")
    protected String postalCodeFormat;
    @XmlElement(name = "country_dialing_cd")
    protected String countryDialingCd;
    @XmlElement(name = "region_name")
    protected String regionName;

    /**
     * Gets the value of the countrySkey property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCountrySkey() {
        return countrySkey;
    }

    /**
     * Sets the value of the countrySkey property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCountrySkey(Integer value) {
        this.countrySkey = value;
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
     * Gets the value of the countryCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * Sets the value of the countryCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountryCode(String value) {
        this.countryCode = value;
    }

    /**
     * Gets the value of the phoneCode property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPhoneCode() {
        return phoneCode;
    }

    /**
     * Sets the value of the phoneCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPhoneCode(Integer value) {
        this.phoneCode = value;
    }

    /**
     * Gets the value of the phoneFormat property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhoneFormat() {
        return phoneFormat;
    }

    /**
     * Sets the value of the phoneFormat property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhoneFormat(String value) {
        this.phoneFormat = value;
    }

    /**
     * Gets the value of the stateReqFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStateReqFlag() {
        return stateReqFlag;
    }

    /**
     * Sets the value of the stateReqFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStateReqFlag(String value) {
        this.stateReqFlag = value;
    }

    /**
     * Gets the value of the currencyName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrencyName() {
        return currencyName;
    }

    /**
     * Sets the value of the currencyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrencyName(String value) {
        this.currencyName = value;
    }

    /**
     * Gets the value of the currencyFormat property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrencyFormat() {
        return currencyFormat;
    }

    /**
     * Sets the value of the currencyFormat property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrencyFormat(String value) {
        this.currencyFormat = value;
    }

    /**
     * Gets the value of the postalCodeFormat property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostalCodeFormat() {
        return postalCodeFormat;
    }

    /**
     * Sets the value of the postalCodeFormat property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostalCodeFormat(String value) {
        this.postalCodeFormat = value;
    }

    /**
     * Gets the value of the countryDialingCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountryDialingCd() {
        return countryDialingCd;
    }

    /**
     * Sets the value of the countryDialingCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountryDialingCd(String value) {
        this.countryDialingCd = value;
    }

    /**
     * Gets the value of the regionName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegionName() {
        return regionName;
    }

    /**
     * Sets the value of the regionName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegionName(String value) {
        this.regionName = value;
    }

}
