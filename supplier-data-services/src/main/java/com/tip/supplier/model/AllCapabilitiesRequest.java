//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.06.21 at 03:25:11 PM IST 
//


package com.tip.supplier.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AllCapabilitiesRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AllCapabilitiesRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="supplierId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="langId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="ssoId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="errorCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AllCapabilitiesRequest", propOrder = {
    "supplierId",
    "langId",
    "ssoId",
    "errorCd"
})
public class AllCapabilitiesRequest {

    protected Integer supplierId;
    protected Integer langId;
    protected String ssoId;
    protected String errorCd;

    /**
     * Gets the value of the supplierId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSupplierId() {
        return supplierId;
    }

    /**
     * Sets the value of the supplierId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSupplierId(Integer value) {
        this.supplierId = value;
    }

    /**
     * Gets the value of the langId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getLangId() {
        return langId;
    }

    /**
     * Sets the value of the langId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setLangId(Integer value) {
        this.langId = value;
    }

    /**
     * Gets the value of the ssoId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSsoId() {
        return ssoId;
    }

    /**
     * Sets the value of the ssoId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSsoId(String value) {
        this.ssoId = value;
    }

    /**
     * Gets the value of the errorCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorCd() {
        return errorCd;
    }

    /**
     * Sets the value of the errorCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorCd(String value) {
        this.errorCd = value;
    }

}
