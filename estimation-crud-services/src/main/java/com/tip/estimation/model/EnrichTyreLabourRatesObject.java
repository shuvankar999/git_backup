//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.09.05 at 06:39:55 PM IST 
//


package com.tip.estimation.model;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EnrichTyreLabourRatesObject complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EnrichTyreLabourRatesObject">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="labourCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="labourTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tyreLabourTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rate" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EnrichTyreLabourRatesObject", propOrder = {
    "labourCd",
    "labourTime",
    "tyreLabourTime",
    "rate"
})
public class EnrichTyreLabourRatesObject {

    protected String labourCd;
    protected String labourTime;
    protected String tyreLabourTime;
    protected BigDecimal rate;

    /**
     * Gets the value of the labourCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLabourCd() {
        return labourCd;
    }

    /**
     * Sets the value of the labourCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLabourCd(String value) {
        this.labourCd = value;
    }

    /**
     * Gets the value of the labourTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLabourTime() {
        return labourTime;
    }

    /**
     * Sets the value of the labourTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLabourTime(String value) {
        this.labourTime = value;
    }

    /**
     * Gets the value of the tyreLabourTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTyreLabourTime() {
        return tyreLabourTime;
    }

    /**
     * Sets the value of the tyreLabourTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTyreLabourTime(String value) {
        this.tyreLabourTime = value;
    }

    /**
     * Gets the value of the rate property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getRate() {
        return rate;
    }

    /**
     * Sets the value of the rate property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setRate(BigDecimal value) {
        this.rate = value;
    }

}