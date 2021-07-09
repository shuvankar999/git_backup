//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.09.26 at 07:08:28 PM IST 
//


package com.tip.inspection.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AddCiaAssestRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AddCiaAssestRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="equipmentNr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="branchNr" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="companyNr" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="licenceCountryCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="licenceNr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custEquipRefNr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="serialNr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="equipManufacturer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subManufacturer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="equipType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="noOfAxles" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="customerNr" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="singleTyre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="twinTyre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="catGrpCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="equipmentStatus" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="userEmail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AddCiaAssestRequest", propOrder = {
    "equipmentNr",
    "branchNr",
    "companyNr",
    "licenceCountryCd",
    "licenceNr",
    "custEquipRefNr",
    "serialNr",
    "equipManufacturer",
    "subManufacturer",
    "equipType",
    "noOfAxles",
    "customerNr",
    "singleTyre",
    "twinTyre",
    "catGrpCode",
    "equipmentStatus",
    "userEmail"
})
public class AddCiaAssestRequest {

    protected String equipmentNr;
    protected Integer branchNr;
    protected Integer companyNr;
    protected String licenceCountryCd;
    protected String licenceNr;
    protected String custEquipRefNr;
    protected String serialNr;
    protected String equipManufacturer;
    protected String subManufacturer;
    protected String equipType;
    protected Integer noOfAxles;
    protected Integer customerNr;
    protected String singleTyre;
    protected String twinTyre;
    protected String catGrpCode;
    protected Integer equipmentStatus;
    protected String userEmail;

    /**
     * Gets the value of the equipmentNr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEquipmentNr() {
        return equipmentNr;
    }

    /**
     * Sets the value of the equipmentNr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEquipmentNr(String value) {
        this.equipmentNr = value;
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
     * Gets the value of the companyNr property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCompanyNr() {
        return companyNr;
    }

    /**
     * Sets the value of the companyNr property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCompanyNr(Integer value) {
        this.companyNr = value;
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
     * Gets the value of the custEquipRefNr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustEquipRefNr() {
        return custEquipRefNr;
    }

    /**
     * Sets the value of the custEquipRefNr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustEquipRefNr(String value) {
        this.custEquipRefNr = value;
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
     * Gets the value of the equipManufacturer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEquipManufacturer() {
        return equipManufacturer;
    }

    /**
     * Sets the value of the equipManufacturer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEquipManufacturer(String value) {
        this.equipManufacturer = value;
    }

    /**
     * Gets the value of the subManufacturer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubManufacturer() {
        return subManufacturer;
    }

    /**
     * Sets the value of the subManufacturer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubManufacturer(String value) {
        this.subManufacturer = value;
    }

    /**
     * Gets the value of the equipType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEquipType() {
        return equipType;
    }

    /**
     * Sets the value of the equipType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEquipType(String value) {
        this.equipType = value;
    }

    /**
     * Gets the value of the noOfAxles property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNoOfAxles() {
        return noOfAxles;
    }

    /**
     * Sets the value of the noOfAxles property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNoOfAxles(Integer value) {
        this.noOfAxles = value;
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
     * Gets the value of the singleTyre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSingleTyre() {
        return singleTyre;
    }

    /**
     * Sets the value of the singleTyre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSingleTyre(String value) {
        this.singleTyre = value;
    }

    /**
     * Gets the value of the twinTyre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTwinTyre() {
        return twinTyre;
    }

    /**
     * Sets the value of the twinTyre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTwinTyre(String value) {
        this.twinTyre = value;
    }

    /**
     * Gets the value of the catGrpCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCatGrpCode() {
        return catGrpCode;
    }

    /**
     * Sets the value of the catGrpCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCatGrpCode(String value) {
        this.catGrpCode = value;
    }

    /**
     * Gets the value of the equipmentStatus property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getEquipmentStatus() {
        return equipmentStatus;
    }

    /**
     * Sets the value of the equipmentStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setEquipmentStatus(Integer value) {
        this.equipmentStatus = value;
    }

    /**
     * Gets the value of the userEmail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * Sets the value of the userEmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserEmail(String value) {
        this.userEmail = value;
    }

}