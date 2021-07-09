//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.06.11 at 03:23:59 PM IST 
//


package com.tip.estimationparts.model;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PartsObjectList complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PartsObjectList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="partsdescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="supplierpartnr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="supplierid" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="costprice" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="oelistprice" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="currency" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PartsObjectList", propOrder = {
    "partsdescription",
    "supplierpartnr",
    "supplierid",
    "costprice",
    "oelistprice",
    "currency",
    "tippartdna"
})
public class PartsObjectList {

    protected String partsdescription;
    protected String supplierpartnr;
    protected Integer supplierid;
    protected Double costprice;
    protected Double oelistprice;
    protected String currency;
    protected String tippartdna;

    /**
     * Gets the value of the partsdescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartsdescription() {
        return partsdescription;
    }

    /**
     * Sets the value of the partsdescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartsdescription(String value) {
        this.partsdescription = value;
    }

    /**
     * Gets the value of the supplierpartnr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSupplierpartnr() {
        return supplierpartnr;
    }

    /**
     * Sets the value of the supplierpartnr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSupplierpartnr(String value) {
        this.supplierpartnr = value;
    }

    /**
     * Gets the value of the supplierid property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSupplierid() {
        return supplierid;
    }

    /**
     * Sets the value of the supplierid property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSupplierid(Integer value) {
        this.supplierid = value;
    }

    /**
     * Gets the value of the costprice property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public Double getCostprice() {
        return costprice;
    }

    /**
     * Sets the value of the costprice property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCostprice(Double value) {
        this.costprice = value;
    }

    /**
     * Gets the value of the oelistprice property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public Double getOelistprice() {
        return oelistprice;
    }

    /**
     * Sets the value of the oelistprice property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setOelistprice(Double value) {
        this.oelistprice = value;
    }

    /**
     * Gets the value of the currency property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Sets the value of the currency property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrency(String value) {
        this.currency = value;
    }

	public String getTippartdna() {
		return tippartdna;
	}

	public void setTippartdna(String tippartdna) {
		this.tippartdna = tippartdna;
	}


    

}
