package com.tip.estimation.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class EstnWoListObject {

    protected BigDecimal estimationId;
    protected Integer estnWOId;
    protected Integer supplierId;
    protected String supplierName;
    protected String groupCd;
    protected String groupCdDesc;
    protected Integer loTaskCount;
    protected Integer partsCount;
    protected Integer estnTyreCnt;
    protected String loCommentsInternal;
    protected String loCommentsCustomers;
    protected Double totTagetTime;
    protected String labourTime;
    protected List<EstnLabourRatesObject> estnLabourRatesList;
    protected List<EtsnTyreLabourRatesObject> etsnTyreLabourRatesList;
    protected List<EstnWotListObject> estnWotList;
    protected List<EstnPartLists> estnMatchedPartLists;

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
    public String getloCommentsInternal() {
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
    public void setloCommentsInternal(String value) {
        this.loCommentsInternal = value==null?value:value.trim();
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
        this.loCommentsCustomers = value==null?value:value.trim();
    }

    /**
     * Gets the value of the totTagetTime property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Double getTotTagetTime() {
        return totTagetTime;
    }

    /**
     * Sets the value of the totTagetTime property.
     * 
     * @param double1
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setTotTagetTime(Double double1) {
        this.totTagetTime = double1;
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
     * {@link EstnLabourRatesObject }
     * 
     * 
     */
    public List<EstnLabourRatesObject> getEstnLabourRatesList() {
        if (estnLabourRatesList == null) {
            estnLabourRatesList = new ArrayList<EstnLabourRatesObject>();
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
     * {@link EtsnTyreLabourRatesObject }
     * 
     * 
     */
    public List<EtsnTyreLabourRatesObject> getEtsnTyreLabourRatesList() {
        if (etsnTyreLabourRatesList == null) {
            etsnTyreLabourRatesList = new ArrayList<EtsnTyreLabourRatesObject>();
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
     * {@link EstnWotListObject }
     * 
     * 
     */
    public List<EstnWotListObject> getEstnWotList() {
        if (estnWotList == null) {
            estnWotList = new ArrayList<EstnWotListObject>();
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
     * {@link EstnPartLists }
     * 
     * 
     */
    public List<EstnPartLists> getEstnMatchedPartLists() {
        if (estnMatchedPartLists == null) {
            estnMatchedPartLists = new ArrayList<EstnPartLists>();
        }
        return this.estnMatchedPartLists;
    }


}
