//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.05.09 at 03:00:31 PM IST 
//


package com.tip.estimation.model;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CustomerBundleObj complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CustomerBundleObj">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="estimationId" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="bundleName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dnaCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="maintenanceAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="manufacturerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="manufacturer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="supplierPartNr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fee" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="ssoId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="errorCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CustomerBundleObj", propOrder = {
    "estimationId",
    "bundleName",
    "dnaCode",
    "maintenanceAction",
    "manufacturerId",
    "manufacturer",
    "supplierPartNr",
    "fee",
    "ssoId",
    "errorCd"
})
public class CustomerBundleObj {

    protected BigDecimal estimationId;
    protected String bundleName;
    protected String dnaCode;
    protected String maintenanceAction;
    protected String manufacturerId;
    protected String manufacturer;
    protected String supplierPartNr;
    protected Double fee;
    protected String ssoId;
    protected String errorCd;

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
     * Gets the value of the dnaCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDnaCode() {
        return dnaCode;
    }

    /**
     * Sets the value of the dnaCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDnaCode(String value) {
        this.dnaCode = value;
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
     * Gets the value of the manufacturerId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getManufacturerId() {
        return manufacturerId;
    }

    /**
     * Sets the value of the manufacturerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setManufacturerId(String value) {
        this.manufacturerId = value;
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
     * Gets the value of the fee property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getFee() {
        return fee;
    }

    /**
     * Sets the value of the fee property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setFee(Double value) {
        this.fee = value;
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
     * Gets the value of the errorCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorCd() {
        return errorCd;
    }

    /**
     * Sets the value of the errorCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorCd(String value) {
        this.errorCd = value;
    }

}