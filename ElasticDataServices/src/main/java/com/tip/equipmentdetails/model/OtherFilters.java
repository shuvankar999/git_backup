//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.06.14 at 01:59:24 PM IST 
//


package com.tip.equipmentdetails.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OtherFilters complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OtherFilters">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="filterLabel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="filterCd" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="filterForm" type="{http://www.example.org/FetchFilterDetails}FilterForm" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OtherFilters", propOrder = {
    "filterLabel",
    "filterCd",
    "filterForm"
})
public class OtherFilters {

    protected String filterLabel;
    protected Integer filterCd;
    @XmlElement(required = true)
    protected List<FilterForm> filterForm;

    /**
     * Gets the value of the filterLabel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFilterLabel() {
        return filterLabel;
    }

    /**
     * Sets the value of the filterLabel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFilterLabel(String value) {
        this.filterLabel = value;
    }

    /**
     * Gets the value of the filterCd property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getFilterCd() {
        return filterCd;
    }

    /**
     * Sets the value of the filterCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setFilterCd(Integer value) {
        this.filterCd = value;
    }

    /**
     * Gets the value of the filterForm property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the filterForm property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFilterForm().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FilterForm }
     * 
     * 
     */
    public List<FilterForm> getFilterForm() {
        if (filterForm == null) {
            filterForm = new ArrayList<FilterForm>();
        }
        return this.filterForm;
    }

}
