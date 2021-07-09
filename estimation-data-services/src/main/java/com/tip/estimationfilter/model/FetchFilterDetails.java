//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.07.30 at 08:10:09 PM IST 
//


package com.tip.estimationfilter.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FetchFilterDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FetchFilterDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ssoId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="searchText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fromIndex" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="indexSize" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="filterList" type="{http://www.example.org/FetchFilterService}MultiFilters" maxOccurs="unbounded"/>
 *         &lt;element name="filterForms" type="{http://www.example.org/FetchFilterService}OtherFilters" maxOccurs="unbounded"/>
 *         &lt;element name="extraFiltersObjList" type="{http://www.example.org/FetchFilterService}ExtraFilter" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FetchFilterDetails", propOrder = {
    "ssoId",
    "searchText",
    "fromIndex",
    "indexSize",
    "filterList",
    "filterForms",
    "extraFiltersObjList"
})
public class FetchFilterDetails {

    protected String ssoId;
    protected String searchText;
    protected Integer fromIndex;
    protected Integer indexSize;
    @XmlElement(required = true)
    protected List<MultiFilters> filterList;
    @XmlElement(required = true)
    protected List<OtherFilters> filterForms;
    @XmlElement(required = true)
    protected List<ExtraFilter> extraFiltersObjList;

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
     * Gets the value of the searchText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSearchText() {
        return searchText;
    }

    /**
     * Sets the value of the searchText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSearchText(String value) {
        this.searchText = value;
    }

    /**
     * Gets the value of the fromIndex property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getFromIndex() {
        return fromIndex;
    }

    /**
     * Sets the value of the fromIndex property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setFromIndex(Integer value) {
        this.fromIndex = value;
    }

    /**
     * Gets the value of the indexSize property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIndexSize() {
        return indexSize;
    }

    /**
     * Sets the value of the indexSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIndexSize(Integer value) {
        this.indexSize = value;
    }

    /**
     * Gets the value of the filterList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the filterList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFilterList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MultiFilters }
     * 
     * 
     */
    public List<MultiFilters> getFilterList() {
        if (filterList == null) {
            filterList = new ArrayList<MultiFilters>();
        }
        return this.filterList;
    }

    /**
     * Gets the value of the filterForms property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the filterForms property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFilterForms().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OtherFilters }
     * 
     * 
     */
    public List<OtherFilters> getFilterForms() {
        if (filterForms == null) {
            filterForms = new ArrayList<OtherFilters>();
        }
        return this.filterForms;
    }

    /**
     * Gets the value of the extraFiltersObjList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the extraFiltersObjList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExtraFiltersObjList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ExtraFilter }
     * 
     * 
     */
    public List<ExtraFilter> getExtraFiltersObjList() {
        if (extraFiltersObjList == null) {
            extraFiltersObjList = new ArrayList<ExtraFilter>();
        }
        return this.extraFiltersObjList;
    }

}