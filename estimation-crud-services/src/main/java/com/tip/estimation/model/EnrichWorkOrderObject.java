//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.10.04 at 07:57:08 PM IST 
//


package com.tip.estimation.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EnrichWorkOrderObject complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EnrichWorkOrderObject">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="estimationId" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="estnWOId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="version" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="supplementary" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="loCommentsCustomers" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="loCommentsInternal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="supplierId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="ssoId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EstnWotList" type="{http://www.example.org/SaveEnrichDetails}EnrichWotObject" maxOccurs="unbounded"/>
 *         &lt;element name="estnMatchedPartLists" type="{http://www.example.org/SaveEnrichDetails}EnrichPartsObject" maxOccurs="unbounded"/>
 *         &lt;element name="labourTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EnrichWorkOrderObject", propOrder = {
    "estimationId",
    "estnWOId",
    "version",
    "supplementary",
    "loCommentsCustomers",
    "loCommentsInternal",
    "supplierId",
    "ssoId",
    "estnWotList",
    "estnMatchedPartLists",
    "labourTime"
})
public class EnrichWorkOrderObject {

    protected BigDecimal estimationId;
    protected Integer estnWOId;
    protected Integer version;
    protected Integer supplementary;
    protected String loCommentsCustomers;
    protected String loCommentsInternal;
    protected Integer supplierId;
    protected String ssoId;
    @XmlElement(name = "EstnWotList", required = true)
    protected List<EnrichWotObject> estnWotList;
    @XmlElement(required = true)
    protected List<EnrichPartsObject> estnMatchedPartLists;
    protected String labourTime;

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
     * Gets the value of the loCommentsCustomers property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoCommentsCustomers() {
        return loCommentsCustomers;
    }

    /**
     * Sets the value of the loCommentsCustomers property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoCommentsCustomers(String value) {
        this.loCommentsCustomers = value;
    }

    /**
     * Gets the value of the loCommentsInternal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoCommentsInternal() {
        return loCommentsInternal;
    }

    /**
     * Sets the value of the loCommentsInternal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoCommentsInternal(String value) {
        this.loCommentsInternal = value;
    }

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

    /**
     * Gets the value of the estnWotList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the estnWotList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEstnWotList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EnrichWotObject }
     * 
     * 
     */
    public List<EnrichWotObject> getEstnWotList() {
        if (estnWotList == null) {
            estnWotList = new ArrayList<EnrichWotObject>();
        }
        return this.estnWotList;
    }

    /**
     * Gets the value of the estnMatchedPartLists property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the estnMatchedPartLists property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEstnMatchedPartLists().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EnrichPartsObject }
     * 
     * 
     */
    public List<EnrichPartsObject> getEstnMatchedPartLists() {
        if (estnMatchedPartLists == null) {
            estnMatchedPartLists = new ArrayList<EnrichPartsObject>();
        }
        return this.estnMatchedPartLists;
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

}
