//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.06.01 at 09:11:15 PM IST 
//


package com.tip.fetchdynamicmasterdata.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Manufacturer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Manufacturer">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AssetManufacturer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Manuf_Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Manufacturer", propOrder = {
    "assetManufacturer",
    "manufName"
})
public class Manufacturer {

    @XmlElement(name = "AssetManufacturer")
    protected String assetManufacturer;
    @XmlElement(name = "Manuf_Name")
    protected String manufName;

    /**
     * Gets the value of the assetManufacturer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAssetManufacturer() {
        return assetManufacturer;
    }

    /**
     * Sets the value of the assetManufacturer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAssetManufacturer(String value) {
        this.assetManufacturer = value;
    }

    /**
     * Gets the value of the manufName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getManufName() {
        return manufName;
    }

    /**
     * Sets the value of the manufName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setManufName(String value) {
        this.manufName = value;
    }

}
