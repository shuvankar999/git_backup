//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.03.13 at 08:30:00 PM IST 
//


package com.tip.thirdpartyequip.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CabInspObj complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CabInspObj">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CabInspCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CabInspCnt" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="CabDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CabInspInd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CabInspRemarks" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CabImageLoc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cabInspType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CabInspObj", propOrder = {
    "cabInspCd",
    "cabInspCnt",
    "cabDesc",
    "cabInspInd",
    "cabInspRemarks",
    "cabImageLoc",
    "cabInspType"
})
public class CabInspObj {

    @XmlElement(name = "CabInspCd")
    protected String cabInspCd;
    @XmlElement(name = "CabInspCnt")
    protected Integer cabInspCnt;
    @XmlElement(name = "CabDesc")
    protected String cabDesc;
    @XmlElement(name = "CabInspInd")
    protected String cabInspInd;
    @XmlElement(name = "CabInspRemarks")
    protected String cabInspRemarks;
    @XmlElement(name = "CabImageLoc")
    protected String cabImageLoc;
    protected String cabInspType;

    /**
     * Gets the value of the cabInspCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCabInspCd() {
        return cabInspCd;
    }

    /**
     * Sets the value of the cabInspCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCabInspCd(String value) {
        this.cabInspCd = value;
    }

    /**
     * Gets the value of the cabInspCnt property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCabInspCnt() {
        return cabInspCnt;
    }

    /**
     * Sets the value of the cabInspCnt property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCabInspCnt(Integer value) {
        this.cabInspCnt = value;
    }

    /**
     * Gets the value of the cabDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCabDesc() {
        return cabDesc;
    }

    /**
     * Sets the value of the cabDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCabDesc(String value) {
        this.cabDesc = value;
    }

    /**
     * Gets the value of the cabInspInd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCabInspInd() {
        return cabInspInd;
    }

    /**
     * Sets the value of the cabInspInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCabInspInd(String value) {
        this.cabInspInd = value;
    }

    /**
     * Gets the value of the cabInspRemarks property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCabInspRemarks() {
        return cabInspRemarks;
    }

    /**
     * Sets the value of the cabInspRemarks property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCabInspRemarks(String value) {
        this.cabInspRemarks = value;
    }

    /**
     * Gets the value of the cabImageLoc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCabImageLoc() {
        return cabImageLoc;
    }

    /**
     * Sets the value of the cabImageLoc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCabImageLoc(String value) {
        this.cabImageLoc = value;
    }

    /**
     * Gets the value of the cabInspType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCabInspType() {
        return cabInspType;
    }

    /**
     * Sets the value of the cabInspType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCabInspType(String value) {
        this.cabInspType = value;
    }

}
