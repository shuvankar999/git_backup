//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.08.20 at 05:59:02 PM IST 
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
 * <p>Java class for SuplyWoObject complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SuplyWoObject">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="estimationId" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="estnWOId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="supplierId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="supplierName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="groupCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="groupCdDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LoTaskCount" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="PartsCount" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="estnTyreCnt" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="LoCommentsInternal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LoCommentsCustomers" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotTagetTime" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="LabourTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EstnLabourRatesList" type="{http://www.example.org/EstnSuplymntryDetails}SuplyLabourRatesObject" maxOccurs="unbounded"/>
 *         &lt;element name="EtsnTyreLabourRatesList" type="{http://www.example.org/EstnSuplymntryDetails}SuplyTyreLabourRatesObject" maxOccurs="unbounded"/>
 *         &lt;element name="EstnWotList" type="{http://www.example.org/EstnSuplymntryDetails}SuplyWotObject" maxOccurs="unbounded"/>
 *         &lt;element name="EstnMatchedPartLists" type="{http://www.example.org/EstnSuplymntryDetails}SuplyPartsObject" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SuplyWoObject", propOrder = {
    "estimationId",
    "estnWOId",
    "supplierId",
    "supplierName",
    "groupCd",
    "groupCdDesc",
    "loTaskCount",
    "partsCount",
    "estnTyreCnt",
    "loCommentsInternal",
    "loCommentsCustomers",
    "totTagetTime",
    "labourTime",
    "estnLabourRatesList",
    "etsnTyreLabourRatesList",
    "estnWotList",
    "estnMatchedPartLists"
})
public class SuplyWoObject {

    protected BigDecimal estimationId;
    protected Integer estnWOId;
    protected Integer supplierId;
    protected String supplierName;
    protected String groupCd;
    protected String groupCdDesc;
    @XmlElement(name = "LoTaskCount")
    protected Integer loTaskCount;
    @XmlElement(name = "PartsCount")
    protected Integer partsCount;
    protected Integer estnTyreCnt;
    @XmlElement(name = "LoCommentsInternal")
    protected String loCommentsInternal;
    @XmlElement(name = "LoCommentsCustomers")
    protected String loCommentsCustomers;
    @XmlElement(name = "TotTagetTime")
    protected Double totTagetTime;
    @XmlElement(name = "LabourTime")
    protected String labourTime;
    @XmlElement(name = "EstnLabourRatesList", required = true)
    protected List<SuplyLabourRatesObject> estnLabourRatesList;
    @XmlElement(name = "EtsnTyreLabourRatesList", required = true)
    protected List<SuplyTyreLabourRatesObject> etsnTyreLabourRatesList;
    @XmlElement(name = "EstnWotList", required = true)
    protected List<SuplyWotObject> estnWotList;
    @XmlElement(name = "EstnMatchedPartLists", required = true)
    protected List<SuplyPartsObject> estnMatchedPartLists;

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
     * Gets the value of the supplierName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSupplierName() {
        return supplierName;
    }

    /**
     * Sets the value of the supplierName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSupplierName(String value) {
        this.supplierName = value;
    }

    /**
     * Gets the value of the groupCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGroupCd() {
        return groupCd;
    }

    /**
     * Sets the value of the groupCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGroupCd(String value) {
        this.groupCd = value;
    }

    /**
     * Gets the value of the groupCdDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGroupCdDesc() {
        return groupCdDesc;
    }

    /**
     * Sets the value of the groupCdDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGroupCdDesc(String value) {
        this.groupCdDesc = value;
    }

    /**
     * Gets the value of the loTaskCount property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getLoTaskCount() {
        return loTaskCount;
    }

    /**
     * Sets the value of the loTaskCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setLoTaskCount(Integer value) {
        this.loTaskCount = value;
    }

    /**
     * Gets the value of the partsCount property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPartsCount() {
        return partsCount;
    }

    /**
     * Sets the value of the partsCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPartsCount(Integer value) {
        this.partsCount = value;
    }

    /**
     * Gets the value of the estnTyreCnt property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getEstnTyreCnt() {
        return estnTyreCnt;
    }

    /**
     * Sets the value of the estnTyreCnt property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setEstnTyreCnt(Integer value) {
        this.estnTyreCnt = value;
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
     * Gets the value of the totTagetTime property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getTotTagetTime() {
        return totTagetTime;
    }

    /**
     * Sets the value of the totTagetTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setTotTagetTime(Double value) {
        this.totTagetTime = value;
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

    /**
     * Gets the value of the estnLabourRatesList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the estnLabourRatesList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEstnLabourRatesList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SuplyLabourRatesObject }
     * 
     * 
     */
    public List<SuplyLabourRatesObject> getEstnLabourRatesList() {
        if (estnLabourRatesList == null) {
            estnLabourRatesList = new ArrayList<SuplyLabourRatesObject>();
        }
        return this.estnLabourRatesList;
    }

    /**
     * Gets the value of the etsnTyreLabourRatesList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the etsnTyreLabourRatesList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEtsnTyreLabourRatesList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SuplyTyreLabourRatesObject }
     * 
     * 
     */
    public List<SuplyTyreLabourRatesObject> getEtsnTyreLabourRatesList() {
        if (etsnTyreLabourRatesList == null) {
            etsnTyreLabourRatesList = new ArrayList<SuplyTyreLabourRatesObject>();
        }
        return this.etsnTyreLabourRatesList;
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
     * {@link SuplyWotObject }
     * 
     * 
     */
    public List<SuplyWotObject> getEstnWotList() {
        if (estnWotList == null) {
            estnWotList = new ArrayList<SuplyWotObject>();
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
     * {@link SuplyPartsObject }
     * 
     * 
     */
    public List<SuplyPartsObject> getEstnMatchedPartLists() {
        if (estnMatchedPartLists == null) {
            estnMatchedPartLists = new ArrayList<SuplyPartsObject>();
        }
        return this.estnMatchedPartLists;
    }

}
