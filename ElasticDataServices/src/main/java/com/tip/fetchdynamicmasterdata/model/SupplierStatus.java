//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.06.01 at 09:11:15 PM IST 
//


package com.tip.fetchdynamicmasterdata.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SupplierStatus complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SupplierStatus">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Ref_Id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Ref_Id_Desc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Ref_Id_Reserved" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SupplierStatus", propOrder = {
    "refId",
    "refIdDesc",
    "refIdReserved"
})
public class SupplierStatus {

    @XmlElement(name = "Ref_Id")
    protected Integer refId;
    @XmlElement(name = "Ref_Id_Desc")
    protected String refIdDesc;
    @XmlElement(name = "Ref_Id_Reserved")
    protected String refIdReserved;

    /**
     * Gets the value of the refId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRefId() {
        return refId;
    }

    /**
     * Sets the value of the refId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRefId(Integer value) {
        this.refId = value;
    }

    /**
     * Gets the value of the refIdDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRefIdDesc() {
        return refIdDesc;
    }

    /**
     * Sets the value of the refIdDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRefIdDesc(String value) {
        this.refIdDesc = value;
    }

    /**
     * Gets the value of the refIdReserved property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRefIdReserved() {
        return refIdReserved;
    }

    /**
     * Sets the value of the refIdReserved property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRefIdReserved(String value) {
        this.refIdReserved = value;
    }

}
