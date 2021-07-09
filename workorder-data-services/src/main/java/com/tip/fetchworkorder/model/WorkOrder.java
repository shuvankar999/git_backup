//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.03.16 at 05:09:13 PM IST 
//


package com.tip.fetchworkorder.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WorkOrder complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WorkOrder">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Work_Pack_Nr" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Work_Order_Nr" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Level1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Group_Desc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Wot_Cnt" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Guide_Time" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="Actual_Time" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="Diff" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="Workorder_Comments" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Wo_Status" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Wo_Status_Desc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Reason_Cd" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Reason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Validation_Status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Supplier_Id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Supplier_Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Payment_Vendor_Id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Payment_Vendor_Currency_Cd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Purchase_Order_Nr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Alert" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TechCommentsObjectList" type="{http://www.example.org/WorkPackResponse}TechCommentsObject" maxOccurs="unbounded"/>
 *         &lt;element name="WorkOrderTaskList" type="{http://www.example.org/WorkPackResponse}WorkOrderTask" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WorkOrder", propOrder = {
    "workPackNr",
    "workOrderNr",
    "level1",
    "groupDesc",
    "wotCnt",
    "guideTime",
    "actualTime",
    "diff",
    "workorderComments",
    "woStatus",
    "woStatusDesc",
    "reasonCd",
    "reason",
    "validationStatus",
    "supplierId",
    "supplierName",
    "paymentVendorId",
    "paymentVendorCurrencyCd",
    "purchaseOrderNr",
    "alert",
    "techCommentsObjectList",
    "workOrderTaskList"
})
public class WorkOrder {

    @XmlElement(name = "Work_Pack_Nr")
    protected BigDecimal workPackNr;
    @XmlElement(name = "Work_Order_Nr")
    protected Integer workOrderNr;
    @XmlElement(name = "Level1")
    protected String level1;
    @XmlElement(name = "Group_Desc")
    protected String groupDesc;
    @XmlElement(name = "Wot_Cnt")
    protected Integer wotCnt;
    @XmlElement(name = "Guide_Time")
    protected Double guideTime;
    @XmlElement(name = "Actual_Time")
    protected Double actualTime;
    @XmlElement(name = "Diff")
    protected Double diff;
    @XmlElement(name = "Workorder_Comments")
    protected String workorderComments;
    @XmlElement(name = "Wo_Status")
    protected Integer woStatus;
    @XmlElement(name = "Wo_Status_Desc")
    protected String woStatusDesc;
    @XmlElement(name = "Reason_Cd")
    protected Integer reasonCd;
    @XmlElement(name = "Reason")
    protected String reason;
    @XmlElement(name = "Validation_Status")
    protected String validationStatus;
    @XmlElement(name = "Supplier_Id")
    protected Integer supplierId;
    @XmlElement(name = "Supplier_Name")
    protected String supplierName;
    @XmlElement(name = "Payment_Vendor_Id")
    protected Integer paymentVendorId;
    @XmlElement(name = "Payment_Vendor_Currency_Cd")
    protected String paymentVendorCurrencyCd;
    @XmlElement(name = "Purchase_Order_Nr")
    protected String purchaseOrderNr;
    @XmlElement(name = "Alert")
    protected String alert;
    @XmlElement(name = "TechCommentsObjectList", required = true)
    protected List<TechCommentsObject> techCommentsObjectList;
    @XmlElement(name = "WorkOrderTaskList", required = true)
    protected List<WorkOrderTask> workOrderTaskList;

    /**
     * Gets the value of the workPackNr property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getWorkPackNr() {
        return workPackNr;
    }

    /**
     * Sets the value of the workPackNr property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setWorkPackNr(BigDecimal value) {
        this.workPackNr = value;
    }

    /**
     * Gets the value of the workOrderNr property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getWorkOrderNr() {
        return workOrderNr;
    }

    /**
     * Sets the value of the workOrderNr property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setWorkOrderNr(Integer value) {
        this.workOrderNr = value;
    }

    /**
     * Gets the value of the level1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLevel1() {
        return level1;
    }

    /**
     * Sets the value of the level1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLevel1(String value) {
        this.level1 = value;
    }

    /**
     * Gets the value of the groupDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGroupDesc() {
        return groupDesc;
    }

    /**
     * Sets the value of the groupDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGroupDesc(String value) {
        this.groupDesc = value;
    }

    /**
     * Gets the value of the wotCnt property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getWotCnt() {
        return wotCnt;
    }

    /**
     * Sets the value of the wotCnt property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setWotCnt(Integer value) {
        this.wotCnt = value;
    }

    /**
     * Gets the value of the guideTime property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getGuideTime() {
        return guideTime;
    }

    /**
     * Sets the value of the guideTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setGuideTime(Double value) {
        this.guideTime = value;
    }

    /**
     * Gets the value of the actualTime property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getActualTime() {
        return actualTime;
    }

    /**
     * Sets the value of the actualTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setActualTime(Double value) {
        this.actualTime = value;
    }

    /**
     * Gets the value of the diff property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getDiff() {
        return diff;
    }

    /**
     * Sets the value of the diff property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setDiff(Double value) {
        this.diff = value;
    }

    /**
     * Gets the value of the workorderComments property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWorkorderComments() {
        return workorderComments;
    }

    /**
     * Sets the value of the workorderComments property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWorkorderComments(String value) {
        this.workorderComments = value;
    }

    /**
     * Gets the value of the woStatus property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getWoStatus() {
        return woStatus;
    }

    /**
     * Sets the value of the woStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setWoStatus(Integer value) {
        this.woStatus = value;
    }

    /**
     * Gets the value of the woStatusDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWoStatusDesc() {
        return woStatusDesc;
    }

    /**
     * Sets the value of the woStatusDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWoStatusDesc(String value) {
        this.woStatusDesc = value;
    }

    /**
     * Gets the value of the reasonCd property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getReasonCd() {
        return reasonCd;
    }

    /**
     * Sets the value of the reasonCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setReasonCd(Integer value) {
        this.reasonCd = value;
    }

    /**
     * Gets the value of the reason property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReason() {
        return reason;
    }

    /**
     * Sets the value of the reason property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReason(String value) {
        this.reason = value;
    }

    /**
     * Gets the value of the validationStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValidationStatus() {
        return validationStatus;
    }

    /**
     * Sets the value of the validationStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValidationStatus(String value) {
        this.validationStatus = value;
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
     * Gets the value of the paymentVendorId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPaymentVendorId() {
        return paymentVendorId;
    }

    /**
     * Sets the value of the paymentVendorId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPaymentVendorId(Integer value) {
        this.paymentVendorId = value;
    }

    /**
     * Gets the value of the paymentVendorCurrencyCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentVendorCurrencyCd() {
        return paymentVendorCurrencyCd;
    }

    /**
     * Sets the value of the paymentVendorCurrencyCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentVendorCurrencyCd(String value) {
        this.paymentVendorCurrencyCd = value;
    }

    /**
     * Gets the value of the purchaseOrderNr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPurchaseOrderNr() {
        return purchaseOrderNr;
    }

    /**
     * Sets the value of the purchaseOrderNr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPurchaseOrderNr(String value) {
        this.purchaseOrderNr = value;
    }

    /**
     * Gets the value of the alert property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlert() {
        return alert;
    }

    /**
     * Sets the value of the alert property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlert(String value) {
        this.alert = value;
    }

    /**
     * Gets the value of the techCommentsObjectList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the techCommentsObjectList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTechCommentsObjectList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TechCommentsObject }
     * 
     * 
     */
    public List<TechCommentsObject> getTechCommentsObjectList() {
        if (techCommentsObjectList == null) {
            techCommentsObjectList = new ArrayList<TechCommentsObject>();
        }
        return this.techCommentsObjectList;
    }

    /**
     * Gets the value of the workOrderTaskList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the workOrderTaskList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWorkOrderTaskList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WorkOrderTask }
     * 
     * 
     */
    public List<WorkOrderTask> getWorkOrderTaskList() {
        if (workOrderTaskList == null) {
            workOrderTaskList = new ArrayList<WorkOrderTask>();
        }
        return this.workOrderTaskList;
    }

}
