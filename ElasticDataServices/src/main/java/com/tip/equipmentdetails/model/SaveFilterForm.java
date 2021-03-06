//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.08.08 at 04:07:19 PM IST 
//


package com.tip.equipmentdetails.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SaveFilterForm complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SaveFilterForm">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ssoId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="filtergroupId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="filterId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rangeup" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rangedown" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="filtervalue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="filterSelected" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isAdvanced" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SaveFilterForm", propOrder = {
    "ssoId",
    "filtergroupId",
    "filterId",
    "rangeup",
    "rangedown",
    "filtervalue",
    "filterSelected",
    "isAdvanced"
})
public class SaveFilterForm {

    protected String ssoId;
    protected Integer filtergroupId;
    protected String filterId;
    protected String rangeup;
    protected String rangedown;
    protected String filtervalue;
    protected String filterSelected;
    protected String isAdvanced;

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
     * Gets the value of the filtergroupId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getFiltergroupId() {
        return filtergroupId;
    }

    /**
     * Sets the value of the filtergroupId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setFiltergroupId(Integer value) {
        this.filtergroupId = value;
    }

    /**
     * Gets the value of the filterId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFilterId() {
        return filterId;
    }

    /**
     * Sets the value of the filterId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFilterId(String value) {
        this.filterId = value;
    }

    /**
     * Gets the value of the rangeup property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRangeup() {
        return rangeup;
    }

    /**
     * Sets the value of the rangeup property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRangeup(String value) {
        this.rangeup = value;
    }

    /**
     * Gets the value of the rangedown property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRangedown() {
        return rangedown;
    }

    /**
     * Sets the value of the rangedown property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRangedown(String value) {
        this.rangedown = value;
    }

    /**
     * Gets the value of the filtervalue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFiltervalue() {
        return filtervalue;
    }

    /**
     * Sets the value of the filtervalue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFiltervalue(String value) {
        this.filtervalue = value;
    }

    /**
     * Gets the value of the filterSelected property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFilterSelected() {
        return filterSelected;
    }

    /**
     * Sets the value of the filterSelected property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFilterSelected(String value) {
        this.filterSelected = value;
    }

    /**
     * Gets the value of the isAdvanced property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsAdvanced() {
        return isAdvanced;
    }

    /**
     * Sets the value of the isAdvanced property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsAdvanced(String value) {
        this.isAdvanced = value;
    }

}
