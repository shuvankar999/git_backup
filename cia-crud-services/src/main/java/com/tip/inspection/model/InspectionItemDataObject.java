//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.08.06 at 04:05:12 PM IST 
//


package com.tip.inspection.model;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for InspectionItemDataObject complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="InspectionItemDataObject">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Insp_Id" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="Insp_Cd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Insp_Ind" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Insp_Desc" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="rejCd" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="rejDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InspectionItemDataObject", propOrder = {
    "inspId",
    "inspCd",
    "inspInd",
    "inspDesc",
    "rejCd",
    "rejDesc"
})
public class InspectionItemDataObject {
    @XmlElement(name = "Insp_Id", required = true)
    protected BigDecimal inspId;
    @NotNull(message="inspCd is missing or null")
    @XmlElement(name = "Insp_Cd", required = true)
    protected String inspCd;
    @NotNull(message="inspInd is missing or null")
    @XmlElement(name = "Insp_Ind", required = true)
    protected String inspInd;
    @NotNull(message="inspDesc is missing or null")
    @XmlElement(name = "Insp_Desc", required = true)
    protected String inspDesc;
    protected Integer rejCd;
    protected String rejDesc;

    /**
     * Gets the value of the inspId property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getInspId() {
        return inspId;
    }

    /**
     * Sets the value of the inspId property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setInspId(BigDecimal value) {
        this.inspId = value;
    }

    /**
     * Gets the value of the inspCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInspCd() {
        return inspCd;
    }

    /**
     * Sets the value of the inspCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInspCd(String value) {
        this.inspCd = value;
    }

    /**
     * Gets the value of the inspInd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInspInd() {
        return inspInd;
    }

    /**
     * Sets the value of the inspInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInspInd(String value) {
        this.inspInd = value;
    }

    /**
     * Gets the value of the inspDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInspDesc() {
        return inspDesc;
    }

    /**
     * Sets the value of the inspDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInspDesc(String value) {
        this.inspDesc = value;
    }

    /**
     * Gets the value of the rejCd property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRejCd() {
        return rejCd;
    }

    /**
     * Sets the value of the rejCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRejCd(Integer value) {
        this.rejCd = value;
    }

    /**
     * Gets the value of the rejDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRejDesc() {
        return rejDesc;
    }

    /**
     * Sets the value of the rejDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRejDesc(String value) {
        this.rejDesc = value;
    }

}
