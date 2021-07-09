//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.10.03 at 08:32:30 PM IST 
//


package com.tip.estimationreport.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BaseModel complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BaseModel">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="listOfLabour" type="{http://www.example.org/EstimationReport}LabourDetails" maxOccurs="unbounded"/>
 *         &lt;element name="additionalParts" type="{http://www.example.org/EstimationReport}AdditionalPartDetails" minOccurs="0"/>
 *         &lt;element name="CustomJob" type="{http://www.example.org/EstimationReport}CustomJobs" minOccurs="0"/>
 *         &lt;element name="otherFees" type="{http://www.example.org/EstimationReport}OtherFees" minOccurs="0"/>
 *         &lt;element name="Total_Charges" type="{http://www.example.org/EstimationReport}TotalCharge" minOccurs="0"/>
 *         &lt;element name="Branch_Details" type="{http://www.example.org/EstimationReport}BranchDetails" minOccurs="0"/>
 *         &lt;element name="Customer_Details" type="{http://www.example.org/EstimationReport}CustomerDetails" minOccurs="0"/>
 *         &lt;element name="Estimation_Details" type="{http://www.example.org/EstimationReport}EstimationDetails" minOccurs="0"/>
 *         &lt;element name="listOfImages" type="{http://www.example.org/EstimationReport}ImageDetails" maxOccurs="unbounded"/>
 *         &lt;element name="Notes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Comments" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Currency" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CountryCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LogoUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Report_Loc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="version" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="supplementary" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BaseModel", propOrder = {
    "listOfLabour",
    "additionalParts",
    "customJob",
    "otherFees",
    "totalCharges",
    "branchDetails",
    "customerDetails",
    "estimationDetails",
    "listOfImages",
    "notes",
    "comments",
    "currency",
    "countryCd",
    "logoUrl",
    "reportLoc",
    "version",
    "supplementary"
})
public class BaseModel {

    @XmlElement(required = true)
    protected List<LabourDetails> listOfLabour;
    protected AdditionalPartDetails additionalParts;
    @XmlElement(name = "CustomJob")
    protected CustomJobs customJob;
    protected OtherFees otherFees;
    @XmlElement(name = "Total_Charges")
    protected TotalCharge totalCharges;
    @XmlElement(name = "Branch_Details")
    protected BranchDetails branchDetails;
    @XmlElement(name = "Customer_Details")
    protected CustomerDetails customerDetails;
    @XmlElement(name = "Estimation_Details")
    protected EstimationDetails estimationDetails;
    @XmlElement(required = true)
    protected List<ImageDetails> listOfImages;
    @XmlElement(name = "Notes")
    protected String notes;
    @XmlElement(name = "Comments")
    protected String comments;
    @XmlElement(name = "Currency")
    protected String currency;
    @XmlElement(name = "CountryCd")
    protected String countryCd;
    @XmlElement(name = "LogoUrl")
    protected String logoUrl;
    @XmlElement(name = "Report_Loc")
    protected String reportLoc;
    protected Integer version;
    protected Integer supplementary;

    /**
     * Gets the value of the listOfLabour property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listOfLabour property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListOfLabour().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LabourDetails }
     * 
     * 
     */
    public List<LabourDetails> getListOfLabour() {
        if (listOfLabour == null) {
            listOfLabour = new ArrayList<LabourDetails>();
        }
        return this.listOfLabour;
    }

    /**
     * Gets the value of the additionalParts property.
     * 
     * @return
     *     possible object is
     *     {@link AdditionalPartDetails }
     *     
     */
    public AdditionalPartDetails getAdditionalParts() {
        return additionalParts;
    }

    /**
     * Sets the value of the additionalParts property.
     * 
     * @param value
     *     allowed object is
     *     {@link AdditionalPartDetails }
     *     
     */
    public void setAdditionalParts(AdditionalPartDetails value) {
        this.additionalParts = value;
    }

    /**
     * Gets the value of the customJob property.
     * 
     * @return
     *     possible object is
     *     {@link CustomJobs }
     *     
     */
    public CustomJobs getCustomJob() {
        return customJob;
    }

    /**
     * Sets the value of the customJob property.
     * 
     * @param value
     *     allowed object is
     *     {@link CustomJobs }
     *     
     */
    public void setCustomJob(CustomJobs value) {
        this.customJob = value;
    }

    /**
     * Gets the value of the otherFees property.
     * 
     * @return
     *     possible object is
     *     {@link OtherFees }
     *     
     */
    public OtherFees getOtherFees() {
        return otherFees;
    }

    /**
     * Sets the value of the otherFees property.
     * 
     * @param value
     *     allowed object is
     *     {@link OtherFees }
     *     
     */
    public void setOtherFees(OtherFees value) {
        this.otherFees = value;
    }

    /**
     * Gets the value of the totalCharges property.
     * 
     * @return
     *     possible object is
     *     {@link TotalCharge }
     *     
     */
    public TotalCharge getTotalCharges() {
        return totalCharges;
    }

    /**
     * Sets the value of the totalCharges property.
     * 
     * @param value
     *     allowed object is
     *     {@link TotalCharge }
     *     
     */
    public void setTotalCharges(TotalCharge value) {
        this.totalCharges = value;
    }

    /**
     * Gets the value of the branchDetails property.
     * 
     * @return
     *     possible object is
     *     {@link BranchDetails }
     *     
     */
    public BranchDetails getBranchDetails() {
        return branchDetails;
    }

    /**
     * Sets the value of the branchDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link BranchDetails }
     *     
     */
    public void setBranchDetails(BranchDetails value) {
        this.branchDetails = value;
    }

    /**
     * Gets the value of the customerDetails property.
     * 
     * @return
     *     possible object is
     *     {@link CustomerDetails }
     *     
     */
    public CustomerDetails getCustomerDetails() {
        return customerDetails;
    }

    /**
     * Sets the value of the customerDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link CustomerDetails }
     *     
     */
    public void setCustomerDetails(CustomerDetails value) {
        this.customerDetails = value;
    }

    /**
     * Gets the value of the estimationDetails property.
     * 
     * @return
     *     possible object is
     *     {@link EstimationDetails }
     *     
     */
    public EstimationDetails getEstimationDetails() {
        return estimationDetails;
    }

    /**
     * Sets the value of the estimationDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link EstimationDetails }
     *     
     */
    public void setEstimationDetails(EstimationDetails value) {
        this.estimationDetails = value;
    }

    /**
     * Gets the value of the listOfImages property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listOfImages property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListOfImages().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ImageDetails }
     * 
     * 
     */
    public List<ImageDetails> getListOfImages() {
        if (listOfImages == null) {
            listOfImages = new ArrayList<ImageDetails>();
        }
        return this.listOfImages;
    }

    /**
     * Gets the value of the notes property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNotes() {
        return notes;
    }

    /**
     * Sets the value of the notes property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNotes(String value) {
        this.notes = value;
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
     * Gets the value of the currency property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Sets the value of the currency property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrency(String value) {
        this.currency = value;
    }

    /**
     * Gets the value of the countryCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountryCd() {
        return countryCd;
    }

    /**
     * Sets the value of the countryCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountryCd(String value) {
        this.countryCd = value;
    }

    /**
     * Gets the value of the logoUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLogoUrl() {
        return logoUrl;
    }

    /**
     * Sets the value of the logoUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLogoUrl(String value) {
        this.logoUrl = value;
    }

    /**
     * Gets the value of the reportLoc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReportLoc() {
        return reportLoc;
    }

    /**
     * Sets the value of the reportLoc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReportLoc(String value) {
        this.reportLoc = value;
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

}
