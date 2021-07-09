//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.06.21 at 05:48:35 PM IST 
//


package com.tip.estimation.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EstnTyreAttributeResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EstnTyreAttributeResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TyreSpeclist" type="{http://www.example.org/FetchEstnTyreAttribute}TyreSpecsObject" maxOccurs="unbounded"/>
 *         &lt;element name="TyreServicelist" type="{http://www.example.org/FetchEstnTyreAttribute}TyreServiceObject" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EstnTyreAttributeResponse", propOrder = {
    "tyreSpeclist",
    "tyreServicelist"
})
public class EstnTyreAttributeResponse {

    @XmlElement(name = "TyreSpeclist", required = true)
    protected List<TyreSpecsObject> tyreSpeclist;
    @XmlElement(name = "TyreServicelist", required = true)
    protected List<TyreServiceObject> tyreServicelist;

    /**
     * Gets the value of the tyreSpeclist property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tyreSpeclist property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTyreSpeclist().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TyreSpecsObject }
     * 
     * 
     */
    public List<TyreSpecsObject> getTyreSpeclist() {
        if (tyreSpeclist == null) {
            tyreSpeclist = new ArrayList<TyreSpecsObject>();
        }
        return this.tyreSpeclist;
    }

    /**
     * Gets the value of the tyreServicelist property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tyreServicelist property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTyreServicelist().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TyreServiceObject }
     * 
     * 
     */
    public List<TyreServiceObject> getTyreServicelist() {
        if (tyreServicelist == null) {
            tyreServicelist = new ArrayList<TyreServiceObject>();
        }
        return this.tyreServicelist;
    }

}