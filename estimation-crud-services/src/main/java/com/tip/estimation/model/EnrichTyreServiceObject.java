//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.10.04 at 07:57:08 PM IST 
//


package com.tip.estimation.model;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EnrichTyreServiceObject complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EnrichTyreServiceObject">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="estimationId" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="estnWOId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="estnWOTId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="tyreServicegrpId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="tyreServicesubgrpId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="tyreServiceItemId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="version" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="supplementary" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="charges" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="showHide" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isApproved" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isRejected" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rejectedReason" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="ssoId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EnrichTyreServiceObject", propOrder = {
    "estimationId",
    "estnWOId",
    "estnWOTId",
    "tyreServicegrpId",
    "tyreServicesubgrpId",
    "tyreServiceItemId",
    "version",
    "supplementary",
    "charges",
    "showHide",
    "isApproved",
    "isRejected",
    "rejectedReason",
    "ssoId"
})
public class EnrichTyreServiceObject {

    protected BigDecimal estimationId;
    protected Integer estnWOId;
    protected Integer estnWOTId;
    protected Integer tyreServicegrpId;
    protected Integer tyreServicesubgrpId;
    protected Integer tyreServiceItemId;
    protected Integer version;
    protected Integer supplementary;
    protected BigDecimal charges;
    protected String showHide;
    protected String isApproved;
    protected String isRejected;
    protected Integer rejectedReason;
    protected String ssoId;

    /**
     * Gets the value of the estimationId property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getEstimationId() {
        return estimationId;
    }

    /**
     * Sets the value of the estimationId property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setEstimationId(BigDecimal value) {
        this.estimationId = value;
    }

    /**
     * Gets the value of the estnWOId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getEstnWOId() {
        return estnWOId;
    }

    /**
     * Sets the value of the estnWOId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setEstnWOId(Integer value) {
        this.estnWOId = value;
    }

    /**
     * Gets the value of the estnWOTId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getEstnWOTId() {
        return estnWOTId;
    }

    /**
     * Sets the value of the estnWOTId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setEstnWOTId(Integer value) {
        this.estnWOTId = value;
    }

    /**
     * Gets the value of the tyreServicegrpId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTyreServicegrpId() {
        return tyreServicegrpId;
    }

    /**
     * Sets the value of the tyreServicegrpId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTyreServicegrpId(Integer value) {
        this.tyreServicegrpId = value;
    }

    /**
     * Gets the value of the tyreServicesubgrpId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTyreServicesubgrpId() {
        return tyreServicesubgrpId;
    }

    /**
     * Sets the value of the tyreServicesubgrpId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTyreServicesubgrpId(Integer value) {
        this.tyreServicesubgrpId = value;
    }

    /**
     * Gets the value of the tyreServiceItemId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTyreServiceItemId() {
        return tyreServiceItemId;
    }

    /**
     * Sets the value of the tyreServiceItemId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTyreServiceItemId(Integer value) {
        this.tyreServiceItemId = value;
    }

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setVersion(Integer value) {
        this.version = value;
    }

    /**
     * Gets the value of the supplementary property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSupplementary() {
        return supplementary;
    }

    /**
     * Sets the value of the supplementary property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSupplementary(Integer value) {
        this.supplementary = value;
    }

    /**
     * Gets the value of the charges property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCharges() {
        return charges;
    }

    /**
     * Sets the value of the charges property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCharges(BigDecimal value) {
        this.charges = value;
    }

    /**
     * Gets the value of the showHide property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShowHide() {
        return showHide;
    }

    /**
     * Sets the value of the showHide property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShowHide(String value) {
        this.showHide = value;
    }

    /**
     * Gets the value of the isApproved property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsApproved() {
        return isApproved;
    }

    /**
     * Sets the value of the isApproved property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsApproved(String value) {
        this.isApproved = value;
    }

    /**
     * Gets the value of the isRejected property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsRejected() {
        return isRejected;
    }

    /**
     * Sets the value of the isRejected property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsRejected(String value) {
        this.isRejected = value;
    }

    /**
     * Gets the value of the rejectedReason property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRejectedReason() {
        return rejectedReason;
    }

    /**
     * Sets the value of the rejectedReason property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRejectedReason(Integer value) {
        this.rejectedReason = value;
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

}
