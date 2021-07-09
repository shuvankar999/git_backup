//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.06.27 at 06:46:42 PM IST 
//


package com.tip.supplier.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LabourStaticLinesObject complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LabourStaticLinesObject">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="supplierId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="groupId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="groupShortDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="groupDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subGroupId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="subGroupDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="itemId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="itemShortDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="itemDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="itemReq" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="itemValType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="itemOrder" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="itemDataType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="itemModule" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="itemValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isEnabled" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="valType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LabourStaticLinesObject", propOrder = {
    "supplierId",
    "groupId",
    "groupShortDesc",
    "groupDesc",
    "subGroupId",
    "subGroupDesc",
    "itemId",
    "itemShortDesc",
    "itemDesc",
    "itemReq",
    "itemValType",
    "itemOrder",
    "itemDataType",
    "itemModule",
    "itemValue",
    "isEnabled",
    "valType"
})
public class LabourStaticLinesObject {

    protected Integer supplierId;
    protected Integer groupId;
    protected String groupShortDesc;
    protected String groupDesc;
    protected Integer subGroupId;
    protected String subGroupDesc;
    protected Integer itemId;
    protected String itemShortDesc;
    protected String itemDesc;
    protected String itemReq;
    protected String itemValType;
    protected Integer itemOrder;
    protected String itemDataType;
    protected String itemModule;
    protected String itemValue;
    protected String isEnabled;
    protected String valType;

    /**
     * Gets the value of the supplierId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSupplierId() {
        return supplierId;
    }

    /**
     * Sets the value of the supplierId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSupplierId(Integer value) {
        this.supplierId = value;
    }

    /**
     * Gets the value of the groupId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getGroupId() {
        return groupId;
    }

    /**
     * Sets the value of the groupId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setGroupId(Integer value) {
        this.groupId = value;
    }

    /**
     * Gets the value of the groupShortDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGroupShortDesc() {
        return groupShortDesc;
    }

    /**
     * Sets the value of the groupShortDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGroupShortDesc(String value) {
        this.groupShortDesc = value;
    }

    /**
     * Gets the value of the groupDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGroupDesc() {
        return groupDesc;
    }

    /**
     * Sets the value of the groupDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGroupDesc(String value) {
        this.groupDesc = value;
    }

    /**
     * Gets the value of the subGroupId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSubGroupId() {
        return subGroupId;
    }

    /**
     * Sets the value of the subGroupId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSubGroupId(Integer value) {
        this.subGroupId = value;
    }

    /**
     * Gets the value of the subGroupDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubGroupDesc() {
        return subGroupDesc;
    }

    /**
     * Sets the value of the subGroupDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubGroupDesc(String value) {
        this.subGroupDesc = value;
    }

    /**
     * Gets the value of the itemId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getItemId() {
        return itemId;
    }

    /**
     * Sets the value of the itemId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setItemId(Integer value) {
        this.itemId = value;
    }

    /**
     * Gets the value of the itemShortDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItemShortDesc() {
        return itemShortDesc;
    }

    /**
     * Sets the value of the itemShortDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItemShortDesc(String value) {
        this.itemShortDesc = value;
    }

    /**
     * Gets the value of the itemDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItemDesc() {
        return itemDesc;
    }

    /**
     * Sets the value of the itemDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItemDesc(String value) {
        this.itemDesc = value;
    }

    /**
     * Gets the value of the itemReq property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItemReq() {
        return itemReq;
    }

    /**
     * Sets the value of the itemReq property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItemReq(String value) {
        this.itemReq = value;
    }

    /**
     * Gets the value of the itemValType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItemValType() {
        return itemValType;
    }

    /**
     * Sets the value of the itemValType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItemValType(String value) {
        this.itemValType = value;
    }

    /**
     * Gets the value of the itemOrder property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getItemOrder() {
        return itemOrder;
    }

    /**
     * Sets the value of the itemOrder property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setItemOrder(Integer value) {
        this.itemOrder = value;
    }

    /**
     * Gets the value of the itemDataType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItemDataType() {
        return itemDataType;
    }

    /**
     * Sets the value of the itemDataType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItemDataType(String value) {
        this.itemDataType = value;
    }

    /**
     * Gets the value of the itemModule property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItemModule() {
        return itemModule;
    }

    /**
     * Sets the value of the itemModule property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItemModule(String value) {
        this.itemModule = value;
    }

    /**
     * Gets the value of the itemValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItemValue() {
        return itemValue;
    }

    /**
     * Sets the value of the itemValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItemValue(String value) {
        this.itemValue = value;
    }

    /**
     * Gets the value of the isEnabled property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsEnabled() {
        return isEnabled;
    }

    /**
     * Sets the value of the isEnabled property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsEnabled(String value) {
        this.isEnabled = value;
    }

    /**
     * Gets the value of the valType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValType() {
        return valType;
    }

    /**
     * Sets the value of the valType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValType(String value) {
        this.valType = value;
    }

}
