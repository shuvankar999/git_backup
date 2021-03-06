//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.04.11 at 07:48:20 PM IST 
//


package com.tip.rplanner.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AssignedWpObject complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AssignedWpObject">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="workPackNr" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="technicianId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="groups" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="assetNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="customerRefNr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="registrationNr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="requiredDoneDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="totalTargetTime" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="customerNr" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="customerName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="chassisNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="workOrderCnt" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Comments" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="supplierId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="driverWaiting" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="workPackStatusId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="workPackStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="alert" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="jobStartTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="jobEndTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="extraTime" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="assignedTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="primarytechnician" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="assignWPPriority" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="addTimeFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="timeAdded" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="extraTimeFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Assigned_Technician_List" type="{http://www.example.org/Rplanner}AssignedTechnicianObject" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AssignedWpObject", propOrder = {
    "workPackNr",
    "technicianId",
    "groups",
    "assetNumber",
    "customerRefNr",
    "registrationNr",
    "requiredDoneDate",
    "totalTargetTime",
    "customerNr",
    "customerName",
    "chassisNumber",
    "workOrderCnt",
    "comments",
    "supplierId",
    "driverWaiting",
    "workPackStatusId",
    "workPackStatus",
    "alert",
    "jobStartTime",
    "jobEndTime",
    "extraTime",
    "assignedTime",
    "primarytechnician",
    "assignWPPriority",
    "addTimeFlag",
    "timeAdded",
    "extraTimeFlag",
    "status",
    "assignedTechnicianList"
})
public class AssignedWpObject {

    protected BigDecimal workPackNr;
    protected String technicianId;
    protected String groups;
    protected String assetNumber;
    protected String customerRefNr;
    protected String registrationNr;
    protected String requiredDoneDate;
    protected Double totalTargetTime;
    protected Integer customerNr;
    protected String customerName;
    protected String chassisNumber;
    protected Integer workOrderCnt;
    @XmlElement(name = "Comments")
    protected String comments;
    protected Integer supplierId;
    protected String driverWaiting;
    protected Integer workPackStatusId;
    protected String workPackStatus;
    protected String alert;
    protected String jobStartTime;
    protected String jobEndTime;
    protected Double extraTime;
    protected String assignedTime;
    protected String primarytechnician;
    protected Integer assignWPPriority;
    protected String addTimeFlag;
    protected Double timeAdded;
    protected String extraTimeFlag;
    protected String status;
    @XmlElement(name = "Assigned_Technician_List", required = true)
    protected List<AssignedTechnicianObject> assignedTechnicianList;

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
     * Gets the value of the technicianId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTechnicianId() {
        return technicianId;
    }

    /**
     * Sets the value of the technicianId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTechnicianId(String value) {
        this.technicianId = value;
    }

    /**
     * Gets the value of the groups property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGroups() {
        return groups;
    }

    /**
     * Sets the value of the groups property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGroups(String value) {
        this.groups = value;
    }

    /**
     * Gets the value of the assetNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAssetNumber() {
        return assetNumber;
    }

    /**
     * Sets the value of the assetNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAssetNumber(String value) {
        this.assetNumber = value;
    }

    /**
     * Gets the value of the customerRefNr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerRefNr() {
        return customerRefNr;
    }

    /**
     * Sets the value of the customerRefNr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerRefNr(String value) {
        this.customerRefNr = value;
    }

    /**
     * Gets the value of the registrationNr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegistrationNr() {
        return registrationNr;
    }

    /**
     * Sets the value of the registrationNr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegistrationNr(String value) {
        this.registrationNr = value;
    }

    /**
     * Gets the value of the requiredDoneDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequiredDoneDate() {
        return requiredDoneDate;
    }

    /**
     * Sets the value of the requiredDoneDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequiredDoneDate(String value) {
        this.requiredDoneDate = value;
    }

    /**
     * Gets the value of the totalTargetTime property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getTotalTargetTime() {
        return totalTargetTime;
    }

    /**
     * Sets the value of the totalTargetTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setTotalTargetTime(Double value) {
        this.totalTargetTime = value;
    }

    /**
     * Gets the value of the customerNr property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCustomerNr() {
        return customerNr;
    }

    /**
     * Sets the value of the customerNr property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCustomerNr(Integer value) {
        this.customerNr = value;
    }

    /**
     * Gets the value of the customerName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Sets the value of the customerName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerName(String value) {
        this.customerName = value;
    }

    /**
     * Gets the value of the chassisNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChassisNumber() {
        return chassisNumber;
    }

    /**
     * Sets the value of the chassisNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChassisNumber(String value) {
        this.chassisNumber = value;
    }

    /**
     * Gets the value of the workOrderCnt property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getWorkOrderCnt() {
        return workOrderCnt;
    }

    /**
     * Sets the value of the workOrderCnt property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setWorkOrderCnt(Integer value) {
        this.workOrderCnt = value;
    }

    /**
     * Gets the value of the comments property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComments() {
        return comments;
    }

    /**
     * Sets the value of the comments property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComments(String value) {
        this.comments = value;
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
     * Gets the value of the driverWaiting property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriverWaiting() {
        return driverWaiting;
    }

    /**
     * Sets the value of the driverWaiting property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverWaiting(String value) {
        this.driverWaiting = value;
    }

    /**
     * Gets the value of the workPackStatusId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getWorkPackStatusId() {
        return workPackStatusId;
    }

    /**
     * Sets the value of the workPackStatusId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setWorkPackStatusId(Integer value) {
        this.workPackStatusId = value;
    }

    /**
     * Gets the value of the workPackStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWorkPackStatus() {
        return workPackStatus;
    }

    /**
     * Sets the value of the workPackStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWorkPackStatus(String value) {
        this.workPackStatus = value;
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
     * Gets the value of the jobStartTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJobStartTime() {
        return jobStartTime;
    }

    /**
     * Sets the value of the jobStartTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJobStartTime(String value) {
        this.jobStartTime = value;
    }

    /**
     * Gets the value of the jobEndTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJobEndTime() {
        return jobEndTime;
    }

    /**
     * Sets the value of the jobEndTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJobEndTime(String value) {
        this.jobEndTime = value;
    }

    /**
     * Gets the value of the extraTime property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getExtraTime() {
        return extraTime;
    }

    /**
     * Sets the value of the extraTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setExtraTime(Double value) {
        this.extraTime = value;
    }

    /**
     * Gets the value of the assignedTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAssignedTime() {
        return assignedTime;
    }

    /**
     * Sets the value of the assignedTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAssignedTime(String value) {
        this.assignedTime = value;
    }

    /**
     * Gets the value of the primarytechnician property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrimarytechnician() {
        return primarytechnician;
    }

    /**
     * Sets the value of the primarytechnician property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrimarytechnician(String value) {
        this.primarytechnician = value;
    }

    /**
     * Gets the value of the assignWPPriority property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAssignWPPriority() {
        return assignWPPriority;
    }

    /**
     * Sets the value of the assignWPPriority property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAssignWPPriority(Integer value) {
        this.assignWPPriority = value;
    }

    /**
     * Gets the value of the addTimeFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddTimeFlag() {
        return addTimeFlag;
    }

    /**
     * Sets the value of the addTimeFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddTimeFlag(String value) {
        this.addTimeFlag = value;
    }

    /**
     * Gets the value of the timeAdded property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getTimeAdded() {
        return timeAdded;
    }

    /**
     * Sets the value of the timeAdded property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setTimeAdded(Double value) {
        this.timeAdded = value;
    }

    /**
     * Gets the value of the extraTimeFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtraTimeFlag() {
        return extraTimeFlag;
    }

    /**
     * Sets the value of the extraTimeFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtraTimeFlag(String value) {
        this.extraTimeFlag = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Gets the value of the assignedTechnicianList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the assignedTechnicianList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAssignedTechnicianList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AssignedTechnicianObject }
     * 
     * 
     */
    public List<AssignedTechnicianObject> getAssignedTechnicianList() {
        if (assignedTechnicianList == null) {
            assignedTechnicianList = new ArrayList<AssignedTechnicianObject>();
        }
        return this.assignedTechnicianList;
    }

}
