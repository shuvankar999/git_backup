//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.12.11 at 07:38:03 PM IST 
//


package com.tip.asset.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DynamicPosVal complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DynamicPosVal">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="specgr_id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="specitm_seq" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="specval_seq" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="specval_descr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="specval_shortcode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DynamicPosVal", propOrder = {
    "specgrId",
    "specitmSeq",
    "specvalSeq",
    "specvalDescr",
    "specvalShortcode"
})
public class DynamicPosVal {

    @XmlElement(name = "specgr_id")
    protected Integer specgrId;
    @XmlElement(name = "specitm_seq")
    protected Integer specitmSeq;
    @XmlElement(name = "specval_seq")
    protected Integer specvalSeq;
    @XmlElement(name = "specval_descr")
    protected String specvalDescr;
    @XmlElement(name = "specval_shortcode")
    protected String specvalShortcode;

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
     * Gets the value of the specitmSeq property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSpecitmSeq() {
        return specitmSeq;
    }

    /**
     * Sets the value of the specitmSeq property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSpecitmSeq(Integer value) {
        this.specitmSeq = value;
    }

    /**
     * Gets the value of the specvalSeq property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSpecvalSeq() {
        return specvalSeq;
    }

    /**
     * Sets the value of the specvalSeq property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSpecvalSeq(Integer value) {
        this.specvalSeq = value;
    }

    /**
     * Gets the value of the specvalDescr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpecvalDescr() {
        return specvalDescr;
    }

    /**
     * Sets the value of the specvalDescr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpecvalDescr(String value) {
        this.specvalDescr = value;
    }

    /**
     * Gets the value of the specvalShortcode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpecvalShortcode() {
        return specvalShortcode;
    }

    /**
     * Sets the value of the specvalShortcode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpecvalShortcode(String value) {
        this.specvalShortcode = value;
    }

}
