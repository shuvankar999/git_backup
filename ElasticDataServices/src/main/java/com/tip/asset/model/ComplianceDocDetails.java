//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.08.02 at 08:37:14 PM IST 
//


package com.tip.asset.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ComplianceDocDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ComplianceDocDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="inspDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="inspectionType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="InspDocName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="inspDocLoc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ComplianceDocDetails", propOrder = {
    "inspDate",
    "inspectionType",
    "inspDocName",
    "inspDocLoc"
})
public class ComplianceDocDetails {

    protected String inspDate;
    protected String inspectionType;
    @XmlElement(name = "InspDocName")
    protected String inspDocName;
    protected String inspDocLoc;

    /**
     * Gets the value of the inspDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInspDate() {
        return inspDate;
    }

    /**
     * Sets the value of the inspDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInspDate(String value) {
        this.inspDate = value;
    }

    /**
     * Gets the value of the inspectionType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInspectionType() {
        return inspectionType;
    }

    /**
     * Sets the value of the inspectionType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInspectionType(String value) {
        this.inspectionType = value;
    }

    /**
     * Gets the value of the inspDocName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInspDocName() {
        return inspDocName;
    }

    /**
     * Sets the value of the inspDocName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInspDocName(String value) {
        this.inspDocName = value;
    }

    /**
     * Gets the value of the inspDocLoc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInspDocLoc() {
        return inspDocLoc;
    }

    /**
     * Sets the value of the inspDocLoc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInspDocLoc(String value) {
        this.inspDocLoc = value;
    }

}
