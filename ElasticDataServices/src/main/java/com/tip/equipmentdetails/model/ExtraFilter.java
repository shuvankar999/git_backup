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
 * <p>Java class for ExtraFilter complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ExtraFilter">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="filterLabel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="filterCd" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="label" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="labelCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dropDownOptions" type="{http://www.example.org/FetchFilterDetails}DropOption" maxOccurs="unbounded"/>
 *         &lt;element name="elasticDbColumn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExtraFilter", propOrder = {
    "filterLabel",
    "filterCd",
    "label",
    "value",
    "type",
    "labelCd",
    "dropDownOptions",
    "elasticDbColumn"
})
public class ExtraFilter {

    protected String filterLabel;
    protected Integer filterCd;
    protected String label;
    protected String value;
    protected String type;
    protected String labelCd;
    @XmlElement(required = true)
    protected List<DropOption> dropDownOptions;
    protected String elasticDbColumn;

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
     * Gets the value of the label property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLabel() {
        return label;
    }

    /**
     * Sets the value of the label property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLabel(String value) {
        this.label = value;
    }

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the labelCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLabelCd() {
        return labelCd;
    }

    /**
     * Sets the value of the labelCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLabelCd(String value) {
        this.labelCd = value;
    }

    /**
     * Gets the value of the dropDownOptions property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dropDownOptions property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDropDownOptions().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DropOption }
     * 
     * 
     */
    public List<DropOption> getDropDownOptions() {
        if (dropDownOptions == null) {
            dropDownOptions = new ArrayList<DropOption>();
        }
        return this.dropDownOptions;
    }

    /**
     * Gets the value of the elasticDbColumn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getElasticDbColumn() {
        return elasticDbColumn;
    }

    /**
     * Sets the value of the elasticDbColumn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setElasticDbColumn(String value) {
        this.elasticDbColumn = value;
    }

}
