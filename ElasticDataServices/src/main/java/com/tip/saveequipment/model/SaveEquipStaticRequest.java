//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.05.30 at 01:53:45 PM IST 
//


package com.tip.saveequipment.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SaveEquipStaticRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SaveEquipStaticRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Unit_Nr" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Catgr_Id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Unit_Comment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Catgrp_Code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Serial_Nr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Category_Cd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Group_Cd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Manufacturer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Model_Year" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Licence_Nr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Licence_Country_Cd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Available_For_Sale_Ind" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Lien_Cd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Aquired_Company" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Aquired_Refrnc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Customer_Refrnc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Branch_Nr" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Status_Comment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Operational_Status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Sold_Date" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SaveEquipStaticRequest", propOrder = {
    "unitNr",
    "catgrId",
    "unitComment",
    "catgrpCode",
    "serialNr",
    "categoryCd",
    "groupCd",
    "manufacturer",
    "modelYear",
    "licenceNr",
    "licenceCountryCd",
    "availableForSaleInd",
    "lienCd",
    "aquiredCompany",
    "aquiredRefrnc",
    "customerRefrnc",
    "branchNr",
    "statusComment",
    "operationalStatus",
    "soldDate"
})
public class SaveEquipStaticRequest {

    @XmlElement(name = "Unit_Nr")
    protected Integer unitNr;
    @XmlElement(name = "Catgr_Id")
    protected Integer catgrId;
    @XmlElement(name = "Unit_Comment")
    protected String unitComment;
    @XmlElement(name = "Catgrp_Code")
    protected String catgrpCode;
    @XmlElement(name = "Serial_Nr")
    protected String serialNr;
    @XmlElement(name = "Category_Cd")
    protected String categoryCd;
    @XmlElement(name = "Group_Cd")
    protected String groupCd;
    @XmlElement(name = "Manufacturer")
    protected String manufacturer;
    @XmlElement(name = "Model_Year")
    protected Integer modelYear;
    @XmlElement(name = "Licence_Nr")
    protected String licenceNr;
    @XmlElement(name = "Licence_Country_Cd")
    protected String licenceCountryCd;
    @XmlElement(name = "Available_For_Sale_Ind")
    protected String availableForSaleInd;
    @XmlElement(name = "Lien_Cd")
    protected String lienCd;
    @XmlElement(name = "Aquired_Company")
    protected String aquiredCompany;
    @XmlElement(name = "Aquired_Refrnc")
    protected String aquiredRefrnc;
    @XmlElement(name = "Customer_Refrnc")
    protected String customerRefrnc;
    @XmlElement(name = "Branch_Nr")
    protected Integer branchNr;
    @XmlElement(name = "Status_Comment")
    protected String statusComment;
    @XmlElement(name = "Operational_Status")
    protected String operationalStatus;
    @XmlElement(name = "Sold_Date")
    protected String soldDate;

    /**
     * Gets the value of the unitNr property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getUnitNr() {
        return unitNr;
    }

    /**
     * Sets the value of the unitNr property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setUnitNr(Integer value) {
        this.unitNr = value;
    }

    /**
     * Gets the value of the catgrId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCatgrId() {
        return catgrId;
    }

    /**
     * Sets the value of the catgrId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCatgrId(Integer value) {
        this.catgrId = value;
    }

    /**
     * Gets the value of the unitComment property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnitComment() {
        return unitComment;
    }

    /**
     * Sets the value of the unitComment property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnitComment(String value) {
        this.unitComment = value;
    }

    /**
     * Gets the value of the catgrpCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCatgrpCode() {
        return catgrpCode;
    }

    /**
     * Sets the value of the catgrpCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCatgrpCode(String value) {
        this.catgrpCode = value;
    }

    /**
     * Gets the value of the serialNr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSerialNr() {
        return serialNr;
    }

    /**
     * Sets the value of the serialNr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSerialNr(String value) {
        this.serialNr = value;
    }

    /**
     * Gets the value of the categoryCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategoryCd() {
        return categoryCd;
    }

    /**
     * Sets the value of the categoryCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategoryCd(String value) {
        this.categoryCd = value;
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
     * Gets the value of the manufacturer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * Sets the value of the manufacturer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setManufacturer(String value) {
        this.manufacturer = value;
    }

    /**
     * Gets the value of the modelYear property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getModelYear() {
        return modelYear;
    }

    /**
     * Sets the value of the modelYear property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setModelYear(Integer value) {
        this.modelYear = value;
    }

    /**
     * Gets the value of the licenceNr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLicenceNr() {
        return licenceNr;
    }

    /**
     * Sets the value of the licenceNr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLicenceNr(String value) {
        this.licenceNr = value;
    }

    /**
     * Gets the value of the licenceCountryCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLicenceCountryCd() {
        return licenceCountryCd;
    }

    /**
     * Sets the value of the licenceCountryCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLicenceCountryCd(String value) {
        this.licenceCountryCd = value;
    }

    /**
     * Gets the value of the availableForSaleInd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAvailableForSaleInd() {
        return availableForSaleInd;
    }

    /**
     * Sets the value of the availableForSaleInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAvailableForSaleInd(String value) {
        this.availableForSaleInd = value;
    }

    /**
     * Gets the value of the lienCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLienCd() {
        return lienCd;
    }

    /**
     * Sets the value of the lienCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLienCd(String value) {
        this.lienCd = value;
    }

    /**
     * Gets the value of the aquiredCompany property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAquiredCompany() {
        return aquiredCompany;
    }

    /**
     * Sets the value of the aquiredCompany property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAquiredCompany(String value) {
        this.aquiredCompany = value;
    }

    /**
     * Gets the value of the aquiredRefrnc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAquiredRefrnc() {
        return aquiredRefrnc;
    }

    /**
     * Sets the value of the aquiredRefrnc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAquiredRefrnc(String value) {
        this.aquiredRefrnc = value;
    }

    /**
     * Gets the value of the customerRefrnc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerRefrnc() {
        return customerRefrnc;
    }

    /**
     * Sets the value of the customerRefrnc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerRefrnc(String value) {
        this.customerRefrnc = value;
    }

    /**
     * Gets the value of the branchNr property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getBranchNr() {
        return branchNr;
    }

    /**
     * Sets the value of the branchNr property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setBranchNr(Integer value) {
        this.branchNr = value;
    }

    /**
     * Gets the value of the statusComment property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusComment() {
        return statusComment;
    }

    /**
     * Sets the value of the statusComment property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusComment(String value) {
        this.statusComment = value;
    }

    /**
     * Gets the value of the operationalStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperationalStatus() {
        return operationalStatus;
    }

    /**
     * Sets the value of the operationalStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperationalStatus(String value) {
        this.operationalStatus = value;
    }

    /**
     * Gets the value of the soldDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSoldDate() {
        return soldDate;
    }

    /**
     * Sets the value of the soldDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSoldDate(String value) {
        this.soldDate = value;
    }

}
