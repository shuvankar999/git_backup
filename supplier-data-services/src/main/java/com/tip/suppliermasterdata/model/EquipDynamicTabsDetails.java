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
 * <p>Java class for EquipDynamicTabsDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EquipDynamicTabsDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="catgr_id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="catgr_desc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="catgr_shortn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="specgr_id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="specgr_shortn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="specgr_descr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EquipDynamicTabsDetails", propOrder = {
    "catgrId",
    "catgrDesc",
    "catgrShortn",
    "specgrId",
    "specgrShortn",
    "specgrDescr"
})
public class EquipDynamicTabsDetails {

    @XmlElement(name = "catgr_id")
    protected Integer catgrId;
    @XmlElement(name = "catgr_desc")
    protected String catgrDesc;
    @XmlElement(name = "catgr_shortn")
    protected String catgrShortn;
    @XmlElement(name = "specgr_id")
    protected Integer specgrId;
    @XmlElement(name = "specgr_shortn")
    protected String specgrShortn;
    @XmlElement(name = "specgr_descr")
    protected String specgrDescr;

    /**
     * Gets the value of the catgrId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCatgrId() {
        return catgrId;
    }

    /**
     * Sets the value of the catgrId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCatgrId(Integer value) {
        this.catgrId = value;
    }

    /**
     * Gets the value of the catgrDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCatgrDesc() {
        return catgrDesc;
    }

    /**
     * Sets the value of the catgrDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCatgrDesc(String value) {
        this.catgrDesc = value;
    }

    /**
     * Gets the value of the catgrShortn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCatgrShortn() {
        return catgrShortn;
    }

    /**
     * Sets the value of the catgrShortn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCatgrShortn(String value) {
        this.catgrShortn = value;
    }

    /**
     * Gets the value of the specgrId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSpecgrId() {
        return specgrId;
    }

    /**
     * Sets the value of the specgrId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSpecgrId(Integer value) {
        this.specgrId = value;
    }

    /**
     * Gets the value of the specgrShortn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpecgrShortn() {
        return specgrShortn;
    }

    /**
     * Sets the value of the specgrShortn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpecgrShortn(String value) {
        this.specgrShortn = value;
    }

    /**
     * Gets the value of the specgrDescr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpecgrDescr() {
        return specgrDescr;
    }

    /**
     * Sets the value of the specgrDescr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpecgrDescr(String value) {
        this.specgrDescr = value;
    }

}