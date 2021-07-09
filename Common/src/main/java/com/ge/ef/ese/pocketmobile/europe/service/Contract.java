
package com.ge.ef.ese.pocketmobile.europe.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Contract complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Contract">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="leaseType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="earlyTerm" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="netNet" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fleetCare" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tyreCare" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="mdp" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="maintanenceResposibility" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="typeOfFleetCareCoverage" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="isSwapUnit" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="isNotReeferUnit" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Contract", propOrder = {
    "leaseType",
    "earlyTerm",
    "netNet",
    "fleetCare",
    "tyreCare",
    "mdp",
    "maintanenceResposibility",
    "typeOfFleetCareCoverage",
    "isSwapUnit",
    "isNotReeferUnit"
})
public class Contract {

    @XmlElement(required = true)
    protected String leaseType;
    @XmlElement(required = true, nillable = true)
    protected String earlyTerm;
    @XmlElement(required = true)
    protected String netNet;
    @XmlElement(required = true, nillable = true)
    protected String fleetCare;
    @XmlElement(required = true, nillable = true)
    protected String tyreCare;
    @XmlElement(required = true, nillable = true)
    protected String mdp;
    @XmlElement(required = true, nillable = true)
    protected String maintanenceResposibility;
    @XmlElement(required = true, nillable = true)
    protected String typeOfFleetCareCoverage;
    @XmlElement(required = true)
    protected String isSwapUnit;
    @XmlElement(required = true)
    protected String isNotReeferUnit;

    /**
     * Gets the value of the leaseType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLeaseType() {
        return leaseType;
    }

    /**
     * Sets the value of the leaseType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLeaseType(String value) {
        this.leaseType = value;
    }

    /**
     * Gets the value of the earlyTerm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEarlyTerm() {
        return earlyTerm;
    }

    /**
     * Sets the value of the earlyTerm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEarlyTerm(String value) {
        this.earlyTerm = value;
    }

    /**
     * Gets the value of the netNet property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNetNet() {
        return netNet;
    }

    /**
     * Sets the value of the netNet property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNetNet(String value) {
        this.netNet = value;
    }

    /**
     * Gets the value of the fleetCare property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFleetCare() {
        return fleetCare;
    }

    /**
     * Sets the value of the fleetCare property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFleetCare(String value) {
        this.fleetCare = value;
    }

    /**
     * Gets the value of the tyreCare property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTyreCare() {
        return tyreCare;
    }

    /**
     * Sets the value of the tyreCare property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTyreCare(String value) {
        this.tyreCare = value;
    }

    /**
     * Gets the value of the mdp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMdp() {
        return mdp;
    }

    /**
     * Sets the value of the mdp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMdp(String value) {
        this.mdp = value;
    }

    /**
     * Gets the value of the maintanenceResposibility property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaintanenceResposibility() {
        return maintanenceResposibility;
    }

    /**
     * Sets the value of the maintanenceResposibility property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaintanenceResposibility(String value) {
        this.maintanenceResposibility = value;
    }

    /**
     * Gets the value of the typeOfFleetCareCoverage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTypeOfFleetCareCoverage() {
        return typeOfFleetCareCoverage;
    }

    /**
     * Sets the value of the typeOfFleetCareCoverage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTypeOfFleetCareCoverage(String value) {
        this.typeOfFleetCareCoverage = value;
    }

    /**
     * Gets the value of the isSwapUnit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsSwapUnit() {
        return isSwapUnit;
    }

    /**
     * Sets the value of the isSwapUnit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsSwapUnit(String value) {
        this.isSwapUnit = value;
    }

    /**
     * Gets the value of the isNotReeferUnit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsNotReeferUnit() {
        return isNotReeferUnit;
    }

    /**
     * Sets the value of the isNotReeferUnit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsNotReeferUnit(String value) {
        this.isNotReeferUnit = value;
    }

}
