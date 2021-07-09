//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.03.13 at 08:30:00 PM IST 
//


package com.tip.thirdpartyequip.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EquipmentCabResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EquipmentCabResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CabFuel" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="CabInspList" type="{http://www.example.org/EquipCab}CabInspObj" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EquipmentCabResponse", propOrder = {
    "cabFuel",
    "cabInspList"
})
public class EquipmentCabResponse {

    @XmlElement(name = "CabFuel")
    protected Double cabFuel;
    @XmlElement(name = "CabInspList", required = true)
    protected List<CabInspObj> cabInspList;

    /**
     * Gets the value of the cabFuel property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getCabFuel() {
        return cabFuel;
    }

    /**
     * Sets the value of the cabFuel property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setCabFuel(Double value) {
        this.cabFuel = value;
    }

    /**
     * Gets the value of the cabInspList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cabInspList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCabInspList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CabInspObj }
     * 
     * 
     */
    public List<CabInspObj> getCabInspList() {
        if (cabInspList == null) {
            cabInspList = new ArrayList<CabInspObj>();
        }
        return this.cabInspList;
    }

}
