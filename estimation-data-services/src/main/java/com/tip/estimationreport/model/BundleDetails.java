//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.10.03 at 08:32:30 PM IST 
//


package com.tip.estimationreport.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BundleDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BundleDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Bundle_Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Group_Cd_Dsc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Sub_Group" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Activity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Action_Cd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MaintenanceAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Manufacturer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Supplier_Part_Nr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Fees" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BundleDetails", propOrder = {
    "bundleName",
    "groupCdDsc",
    "subGroup",
    "activity",
    "actionCd",
    "maintenanceAction",
    "manufacturer",
    "supplierPartNr",
    "fees"
})
public class BundleDetails {

    @XmlElement(name = "Bundle_Name")
    protected String bundleName;
    @XmlElement(name = "Group_Cd_Dsc")
    protected String groupCdDsc;
    @XmlElement(name = "Sub_Group")
    protected String subGroup;
    @XmlElement(name = "Activity")
    protected String activity;
    @XmlElement(name = "Action_Cd")
    protected String actionCd;
    @XmlElement(name = "MaintenanceAction")
    protected String maintenanceAction;
    @XmlElement(name = "Manufacturer")
    protected String manufacturer;
    @XmlElement(name = "Supplier_Part_Nr")
    protected String supplierPartNr;
    @XmlElement(name = "Fees")
    protected String fees;

    /**
     * Gets the value of the bundleName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBundleName() {
        return bundleName;
    }

    /**
     * Sets the value of the bundleName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBundleName(String value) {
        this.bundleName = value;
    }

    /**
     * Gets the value of the groupCdDsc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGroupCdDsc() {
        return groupCdDsc;
    }

    /**
     * Sets the value of the groupCdDsc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGroupCdDsc(String value) {
        this.groupCdDsc = value;
    }

    /**
     * Gets the value of the subGroup property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubGroup() {
        return subGroup;
    }

    /**
     * Sets the value of the subGroup property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubGroup(String value) {
        this.subGroup = value;
    }

    /**
     * Gets the value of the activity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActivity() {
        return activity;
    }

    /**
     * Sets the value of the activity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActivity(String value) {
        this.activity = value;
    }

    /**
     * Gets the value of the actionCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActionCd() {
        return actionCd;
    }

    /**
     * Sets the value of the actionCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActionCd(String value) {
        this.actionCd = value;
    }

    /**
     * Gets the value of the maintenanceAction property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaintenanceAction() {
        return maintenanceAction;
    }

    /**
     * Sets the value of the maintenanceAction property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaintenanceAction(String value) {
        this.maintenanceAction = value;
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
     * Gets the value of the supplierPartNr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSupplierPartNr() {
        return supplierPartNr;
    }

    /**
     * Sets the value of the supplierPartNr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSupplierPartNr(String value) {
        this.supplierPartNr = value;
    }

    /**
     * Gets the value of the fees property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFees() {
        return fees;
    }

    /**
     * Sets the value of the fees property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFees(String value) {
        this.fees = value;
    }

}
