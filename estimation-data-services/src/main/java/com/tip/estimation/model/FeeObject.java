//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.09.19 at 02:55:30 PM IST 
//


package com.tip.estimation.model;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FeeObject complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FeeObject">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="feesgroupId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="feessubgroupId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="feesitemId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="feeDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fee" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="showHide" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isApproved" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isRejected" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FeeObject", propOrder = {
    "feesgroupId",
    "feessubgroupId",
    "feesitemId",
    "feeDesc",
    "fee",
    "showHide",
    "isApproved",
    "isRejected"
})
public class FeeObject {

    protected Integer feesgroupId;
    protected Integer feessubgroupId;
    protected Integer feesitemId;
    protected String feeDesc;
    protected BigDecimal fee;
    protected boolean showHide;
    protected String isApproved;
    protected String isRejected;

    /**
     * Gets the value of the feesgroupId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getFeesgroupId() {
        return feesgroupId;
    }

    /**
     * Sets the value of the feesgroupId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setFeesgroupId(Integer value) {
        this.feesgroupId = value;
    }

    /**
     * Gets the value of the feessubgroupId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getFeessubgroupId() {
        return feessubgroupId;
    }

    /**
     * Sets the value of the feessubgroupId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setFeessubgroupId(Integer value) {
        this.feessubgroupId = value;
    }

    /**
     * Gets the value of the feesitemId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getFeesitemId() {
        return feesitemId;
    }

    /**
     * Sets the value of the feesitemId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setFeesitemId(Integer value) {
        this.feesitemId = value;
    }

    /**
     * Gets the value of the feeDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFeeDesc() {
        return feeDesc;
    }

    /**
     * Sets the value of the feeDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFeeDesc(String value) {
        this.feeDesc = value;
    }

    /**
     * Gets the value of the fee property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getFee() {
        return fee;
    }

    /**
     * Sets the value of the fee property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setFee(BigDecimal value) {
        this.fee = value;
    }

    /**
     * Gets the value of the showHide property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public boolean getShowHide() {
        return showHide;
    }

    /**
     * Sets the value of the showHide property.
     * 
     * @param flag
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShowHide(boolean flag) {
        this.showHide = flag;
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

}
