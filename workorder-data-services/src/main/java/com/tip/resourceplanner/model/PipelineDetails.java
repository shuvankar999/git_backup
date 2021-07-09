//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.07.19 at 08:53:20 PM IST 
//


package com.tip.resourceplanner.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.math.BigDecimal;


/**
 * <p>Java class for PipelineDetails complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="PipelineDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Asset_Number" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Customer_Reference_Nr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Registration_Nr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Work_Pack_Nr" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Required_Done_Date" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Total_Guide_Time" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="Customer_Nr" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Customer_Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Chassis_Number" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="workOrder_Cnt" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Supplier_Id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Driver_Waiting" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Work_Pack_Status_Id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="WorkPack_Status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PipelineDetails", propOrder = {
        "assetNumber",
        "customerReferenceNr",
        "registrationNr",
        "workPackNr",
        "requiredDoneDate",
        "totalGuideTime",
        "customerNr",
        "customerName",
        "chassisNumber",
        "workOrderCnt",
        "description",
        "supplierId",
        "driverWaiting",
        "workPackStatusId",
        "workPackStatus"
})
public class PipelineDetails {

    @XmlElement(name = "Asset_Number")
    protected Integer assetNumber;
    @XmlElement(name = "Customer_Reference_Nr")
    protected String customerReferenceNr;
    @XmlElement(name = "Registration_Nr")
    protected String registrationNr;
    @XmlElement(name = "Work_Pack_Nr")
    protected BigDecimal workPackNr;
    @XmlElement(name = "Required_Done_Date")
    protected String requiredDoneDate;
    @XmlElement(name = "Total_Guide_Time")
    protected Double totalGuideTime;
    @XmlElement(name = "Customer_Nr")
    protected Integer customerNr;
    @XmlElement(name = "Customer_Name")
    protected String customerName;
    @XmlElement(name = "Chassis_Number")
    protected String chassisNumber;
    @XmlElement(name = "workOrder_Cnt")
    protected Integer workOrderCnt;
    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "Supplier_Id")
    protected Integer supplierId;
    @XmlElement(name = "Driver_Waiting")
    protected String driverWaiting;
    @XmlElement(name = "Work_Pack_Status_Id")
    protected Integer workPackStatusId;
    @XmlElement(name = "WorkPack_Status")
    protected String workPackStatus;

    /**
     * Gets the value of the assetNumber property.
     *
     * @return possible object is
     * {@link Integer }
     */
    public Integer getAssetNumber() {
        return assetNumber;
    }

    /**
     * Sets the value of the assetNumber property.
     *
     * @param value allowed object is
     *              {@link Integer }
     */
    public void setAssetNumber(Integer value) {
        this.assetNumber = value;
    }

    /**
     * Gets the value of the customerReferenceNr property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCustomerReferenceNr() {
        return customerReferenceNr;
    }

    /**
     * Sets the value of the customerReferenceNr property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCustomerReferenceNr(String value) {
        this.customerReferenceNr = value;
    }

    /**
     * Gets the value of the registrationNr property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getRegistrationNr() {
        return registrationNr;
    }

    /**
     * Sets the value of the registrationNr property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setRegistrationNr(String value) {
        this.registrationNr = value;
    }

    /**
     * Gets the value of the workPackNr property.
     *
     * @return possible object is
     * {@link BigDecimal }
     */
    public BigDecimal getWorkPackNr() {
        return workPackNr;
    }

    /**
     * Sets the value of the workPackNr property.
     *
     * @param value allowed object is
     *              {@link BigDecimal }
     */
    public void setWorkPackNr(BigDecimal value) {
        this.workPackNr = value;
    }

    /**
     * Gets the value of the requiredDoneDate property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getRequiredDoneDate() {
        return requiredDoneDate;
    }

    /**
     * Sets the value of the requiredDoneDate property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setRequiredDoneDate(String value) {
        this.requiredDoneDate = value;
    }

    /**
     * Gets the value of the totalGuideTime property.
     *
     * @return possible object is
     * {@link Double }
     */
    public Double getTotalGuideTime() {
        return totalGuideTime;
    }

    /**
     * Sets the value of the totalGuideTime property.
     *
     * @param value allowed object is
     *              {@link Double }
     */
    public void setTotalGuideTime(Double value) {
        this.totalGuideTime = value;
    }

    /**
     * Gets the value of the customerNr property.
     *
     * @return possible object is
     * {@link Integer }
     */
    public Integer getCustomerNr() {
        return customerNr;
    }

    /**
     * Sets the value of the customerNr property.
     *
     * @param value allowed object is
     *              {@link Integer }
     */
    public void setCustomerNr(Integer value) {
        this.customerNr = value;
    }

    /**
     * Gets the value of the customerName property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Sets the value of the customerName property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCustomerName(String value) {
        this.customerName = value;
    }

    /**
     * Gets the value of the chassisNumber property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getChassisNumber() {
        return chassisNumber;
    }

    /**
     * Sets the value of the chassisNumber property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setChassisNumber(String value) {
        this.chassisNumber = value;
    }

    /**
     * Gets the value of the workOrderCnt property.
     *
     * @return possible object is
     * {@link Integer }
     */
    public Integer getWorkOrderCnt() {
        return workOrderCnt;
    }

    /**
     * Sets the value of the workOrderCnt property.
     *
     * @param value allowed object is
     *              {@link Integer }
     */
    public void setWorkOrderCnt(Integer value) {
        this.workOrderCnt = value;
    }

    /**
     * Gets the value of the description property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the supplierId property.
     *
     * @return possible object is
     * {@link Integer }
     */
    public Integer getSupplierId() {
        return supplierId;
    }

    /**
     * Sets the value of the supplierId property.
     *
     * @param value allowed object is
     *              {@link Integer }
     */
    public void setSupplierId(Integer value) {
        this.supplierId = value;
    }

    /**
     * Gets the value of the driverWaiting property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getDriverWaiting() {
        return driverWaiting;
    }

    /**
     * Sets the value of the driverWaiting property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDriverWaiting(String value) {
        this.driverWaiting = value;
    }

    /**
     * Gets the value of the workPackStatusId property.
     *
     * @return possible object is
     * {@link Integer }
     */
    public Integer getWorkPackStatusId() {
        return workPackStatusId;
    }

    /**
     * Sets the value of the workPackStatusId property.
     *
     * @param value allowed object is
     *              {@link Integer }
     */
    public void setWorkPackStatusId(Integer value) {
        this.workPackStatusId = value;
    }

    /**
     * Gets the value of the workPackStatus property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getWorkPackStatus() {
        return workPackStatus;
    }

    /**
     * Sets the value of the workPackStatus property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setWorkPackStatus(String value) {
        this.workPackStatus = value;
    }

}
