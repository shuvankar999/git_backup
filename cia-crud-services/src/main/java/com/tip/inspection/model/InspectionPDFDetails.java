//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.09.24 at 11:14:50 AM IST 
//


package com.tip.inspection.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for InspectionPDFDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="InspectionPDFDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Title" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="InspectedDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="InspectedType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="InspectedLocation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Longitude" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Latitude" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="InspectedDateHeader" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="InspectedTypeHeader" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="InspectedLocationHeader" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LongitudeHeader" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LatitudeHeader" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InspectionPDFDetails", propOrder = {
    "title",
    "inspectedDate",
    "inspectedType",
    "inspectedLocation",
    "longitude",
    "latitude",
    "inspectedDateHeader",
    "inspectedTypeHeader",
    "inspectedLocationHeader",
    "longitudeHeader",
    "latitudeHeader"
})
public class InspectionPDFDetails {

    @XmlElement(name = "Title")
    protected String title;
    @XmlElement(name = "InspectedDate")
    protected String inspectedDate;
    @XmlElement(name = "InspectedType")
    protected String inspectedType;
    @XmlElement(name = "InspectedLocation")
    protected String inspectedLocation;
    @XmlElement(name = "Longitude")
    protected String longitude;
    @XmlElement(name = "Latitude")
    protected String latitude;
    @XmlElement(name = "InspectedDateHeader")
    protected String inspectedDateHeader;
    @XmlElement(name = "InspectedTypeHeader")
    protected String inspectedTypeHeader;
    @XmlElement(name = "InspectedLocationHeader")
    protected String inspectedLocationHeader;
    @XmlElement(name = "LongitudeHeader")
    protected String longitudeHeader;
    @XmlElement(name = "LatitudeHeader")
    protected String latitudeHeader;

    /**
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(String value) {
        this.title = value;
    }

    /**
     * Gets the value of the inspectedDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInspectedDate() {
        return inspectedDate;
    }

    /**
     * Sets the value of the inspectedDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInspectedDate(String value) {
        this.inspectedDate = value;
    }

    /**
     * Gets the value of the inspectedType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInspectedType() {
        return inspectedType;
    }

    /**
     * Sets the value of the inspectedType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInspectedType(String value) {
        this.inspectedType = value;
    }

    /**
     * Gets the value of the inspectedLocation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInspectedLocation() {
        return inspectedLocation;
    }

    /**
     * Sets the value of the inspectedLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInspectedLocation(String value) {
        this.inspectedLocation = value;
    }

    /**
     * Gets the value of the longitude property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * Sets the value of the longitude property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLongitude(String value) {
        this.longitude = value;
    }

    /**
     * Gets the value of the latitude property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * Sets the value of the latitude property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLatitude(String value) {
        this.latitude = value;
    }

    /**
     * Gets the value of the inspectedDateHeader property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInspectedDateHeader() {
        return inspectedDateHeader;
    }

    /**
     * Sets the value of the inspectedDateHeader property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInspectedDateHeader(String value) {
        this.inspectedDateHeader = value;
    }

    /**
     * Gets the value of the inspectedTypeHeader property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInspectedTypeHeader() {
        return inspectedTypeHeader;
    }

    /**
     * Sets the value of the inspectedTypeHeader property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInspectedTypeHeader(String value) {
        this.inspectedTypeHeader = value;
    }

    /**
     * Gets the value of the inspectedLocationHeader property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInspectedLocationHeader() {
        return inspectedLocationHeader;
    }

    /**
     * Sets the value of the inspectedLocationHeader property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInspectedLocationHeader(String value) {
        this.inspectedLocationHeader = value;
    }

    /**
     * Gets the value of the longitudeHeader property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLongitudeHeader() {
        return longitudeHeader;
    }

    /**
     * Sets the value of the longitudeHeader property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLongitudeHeader(String value) {
        this.longitudeHeader = value;
    }

    /**
     * Gets the value of the latitudeHeader property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLatitudeHeader() {
        return latitudeHeader;
    }

    /**
     * Sets the value of the latitudeHeader property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLatitudeHeader(String value) {
        this.latitudeHeader = value;
    }

}
