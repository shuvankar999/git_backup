//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.04.09 at 04:28:37 PM IST 
//


package com.tip.fetchtechnician.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TechnicianDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TechnicianDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Technician_Id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Technician_Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Technician_Status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="wpCount" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TechnicianDetails", propOrder = {
    "technicianId",
    "technicianName",
    "technicianStatus",
    "wpCount"
})
public class TechnicianDetails {

    @XmlElement(name = "Technician_Id")
    protected String technicianId;
    @XmlElement(name = "Technician_Name")
    protected String technicianName;
    @XmlElement(name = "Technician_Status")
    protected String technicianStatus;
    protected Integer wpCount;

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
     * Gets the value of the technicianName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTechnicianName() {
        return technicianName;
    }

    /**
     * Sets the value of the technicianName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTechnicianName(String value) {
        this.technicianName = value;
    }

    /**
     * Gets the value of the technicianStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTechnicianStatus() {
        return technicianStatus;
    }

    /**
     * Sets the value of the technicianStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTechnicianStatus(String value) {
        this.technicianStatus = value;
    }

    /**
     * Gets the value of the wpCount property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getWpCount() {
        return wpCount;
    }

    /**
     * Sets the value of the wpCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setWpCount(Integer value) {
        this.wpCount = value;
    }

}
