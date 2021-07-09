//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.06.27 at 06:46:42 PM IST 
//


package com.tip.supplier.model;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LabourDynamicLinesObject complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LabourDynamicLinesObject">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="supplierId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Labour_Rate_Group_Id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Labour_Rate_Group_Desc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Labour_Rate_Sub_Group_Id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Labour_Rate_Sub_Group_Desc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Labour_Rate_Id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Seq_Id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Labour_Rate_Desc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Day_Rate" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Night_Rate" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Mobile_Rate" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Sat_Rate" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Sun_Rate" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Pub_Holiday_Rate" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Is_Enabled" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LabourDynamicLinesObject", propOrder = {
    "supplierId",
    "labourRateGroupId",
    "labourRateGroupDesc",
    "labourRateSubGroupId",
    "labourRateSubGroupDesc",
    "labourRateId",
    "seqId",
    "labourRateDesc",
    "dayRate",
    "nightRate",
    "mobileRate",
    "satRate",
    "sunRate",
    "pubHolidayRate",
    "isEnabled"
})
public class LabourDynamicLinesObject {

    protected Integer supplierId;
    @XmlElement(name = "Labour_Rate_Group_Id")
    protected Integer labourRateGroupId;
    @XmlElement(name = "Labour_Rate_Group_Desc")
    protected String labourRateGroupDesc;
    @XmlElement(name = "Labour_Rate_Sub_Group_Id")
    protected Integer labourRateSubGroupId;
    @XmlElement(name = "Labour_Rate_Sub_Group_Desc")
    protected String labourRateSubGroupDesc;
    @XmlElement(name = "Labour_Rate_Id")
    protected Integer labourRateId;
    @XmlElement(name = "Seq_Id")
    protected Integer seqId;
    @XmlElement(name = "Labour_Rate_Desc")
    protected String labourRateDesc;
    @XmlElement(name = "Day_Rate")
    protected String dayRate;
    @XmlElement(name = "Night_Rate")
    protected String nightRate;
    @XmlElement(name = "Mobile_Rate")
    protected String mobileRate;
    @XmlElement(name = "Sat_Rate")
    protected String satRate;
    @XmlElement(name = "Sun_Rate")
    protected String sunRate;
    @XmlElement(name = "Pub_Holiday_Rate")
    protected String pubHolidayRate;
    @XmlElement(name = "Is_Enabled")
    protected String isEnabled;

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
     * Gets the value of the labourRateGroupId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getLabourRateGroupId() {
        return labourRateGroupId;
    }

    /**
     * Sets the value of the labourRateGroupId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setLabourRateGroupId(Integer value) {
        this.labourRateGroupId = value;
    }

    /**
     * Gets the value of the labourRateGroupDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLabourRateGroupDesc() {
        return labourRateGroupDesc;
    }

    /**
     * Sets the value of the labourRateGroupDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLabourRateGroupDesc(String value) {
        this.labourRateGroupDesc = value;
    }

    /**
     * Gets the value of the labourRateSubGroupId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getLabourRateSubGroupId() {
        return labourRateSubGroupId;
    }

    /**
     * Sets the value of the labourRateSubGroupId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setLabourRateSubGroupId(Integer value) {
        this.labourRateSubGroupId = value;
    }

    /**
     * Gets the value of the labourRateSubGroupDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLabourRateSubGroupDesc() {
        return labourRateSubGroupDesc;
    }

    /**
     * Sets the value of the labourRateSubGroupDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLabourRateSubGroupDesc(String value) {
        this.labourRateSubGroupDesc = value;
    }

    /**
     * Gets the value of the labourRateId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getLabourRateId() {
        return labourRateId;
    }

    /**
     * Sets the value of the labourRateId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setLabourRateId(Integer value) {
        this.labourRateId = value;
    }

    /**
     * Gets the value of the seqId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSeqId() {
        return seqId;
    }

    /**
     * Sets the value of the seqId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSeqId(Integer value) {
        this.seqId = value;
    }

    /**
     * Gets the value of the labourRateDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLabourRateDesc() {
        return labourRateDesc;
    }

    /**
     * Sets the value of the labourRateDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLabourRateDesc(String value) {
        this.labourRateDesc = value;
    }

    /**
     * Gets the value of the dayRate property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public String getDayRate() {
        return dayRate;
    }

    /**
     * Sets the value of the dayRate property.
     * 
     * @param string
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDayRate(String string) {
        this.dayRate = string;
    }

    /**
     * Gets the value of the nightRate property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public String getNightRate() {
        return nightRate;
    }

    /**
     * Sets the value of the nightRate property.
     * 
     * @param string
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setNightRate(String string) {
        this.nightRate = string;
    }

    /**
     * Gets the value of the mobileRate property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public String getMobileRate() {
        return mobileRate;
    }

    /**
     * Sets the value of the mobileRate property.
     * 
     * @param string
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMobileRate(String string) {
        this.mobileRate = string;
    }

    /**
     * Gets the value of the satRate property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public String getSatRate() {
        return satRate;
    }

    /**
     * Sets the value of the satRate property.
     * 
     * @param string
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSatRate(String string) {
        this.satRate = string;
    }

    /**
     * Gets the value of the sunRate property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public String getSunRate() {
        return sunRate;
    }

    /**
     * Sets the value of the sunRate property.
     * 
     * @param string
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSunRate(String string) {
        this.sunRate = string;
    }

    /**
     * Gets the value of the pubHolidayRate property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public String getPubHolidayRate() {
        return pubHolidayRate;
    }

    /**
     * Sets the value of the pubHolidayRate property.
     * 
     * @param string
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPubHolidayRate(String string) {
        this.pubHolidayRate = string;
    }

    /**
     * Gets the value of the isEnabled property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsEnabled() {
        return isEnabled;
    }

    /**
     * Sets the value of the isEnabled property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsEnabled(String value) {
        this.isEnabled = value;
    }

}
