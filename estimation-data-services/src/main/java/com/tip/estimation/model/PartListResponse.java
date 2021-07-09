//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.05.31 at 06:14:07 PM IST 
//


package com.tip.estimation.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PartListResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PartListResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="EstnPartsCountList" type="{http://www.example.org/PartList}PartsCountObject" maxOccurs="unbounded"/>
 *         &lt;element name="EstnPartsResponseList" type="{http://www.example.org/PartList}PartsResponseObject" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PartListResponse", propOrder = {
    "estnPartsCountList",
    "estnPartsResponseList"
})
public class PartListResponse {

    @XmlElement(name = "EstnPartsCountList", required = true)
    protected List<PartsCountObject> estnPartsCountList;
    @XmlElement(name = "EstnPartsResponseList", required = true)
    protected List<PartsResponseObject> estnPartsResponseList;

    /**
     * Gets the value of the estnPartsCountList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the estnPartsCountList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEstnPartsCountList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PartsCountObject }
     * 
     * 
     */
    public List<PartsCountObject> getEstnPartsCountList() {
        if (estnPartsCountList == null) {
            estnPartsCountList = new ArrayList<PartsCountObject>();
        }
        return this.estnPartsCountList;
    }

    /**
     * Gets the value of the estnPartsResponseList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the estnPartsResponseList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEstnPartsResponseList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PartsResponseObject }
     * 
     * 
     */
    public List<PartsResponseObject> getEstnPartsResponseList() {
        if (estnPartsResponseList == null) {
            estnPartsResponseList = new ArrayList<PartsResponseObject>();
        }
        return this.estnPartsResponseList;
    }

}