//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.01.10 at 07:56:11 PM IST 
//


package com.tip.saveequipment.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Form complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Form">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="label" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Specitm_Seq" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Unitsp_Comment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Db_Column_Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Form", propOrder = {
    "label",
    "value",
    "specitmSeq",
    "unitspComment",
    "dbColumnName"
})
public class Form {

    protected String label;
    protected String value;
    @XmlElement(name = "Specitm_Seq")
    protected Integer specitmSeq;
    @XmlElement(name = "Unitsp_Comment")
    protected String unitspComment;
    @XmlElement(name = "Db_Column_Name")
    protected String dbColumnName;

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
     * Gets the value of the specitmSeq property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSpecitmSeq() {
        return specitmSeq;
    }

    /**
     * Sets the value of the specitmSeq property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSpecitmSeq(Integer value) {
        this.specitmSeq = value;
    }

    /**
     * Gets the value of the unitspComment property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnitspComment() {
        return unitspComment;
    }

    /**
     * Sets the value of the unitspComment property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnitspComment(String value) {
        this.unitspComment = value;
    }

    /**
     * Gets the value of the dbColumnName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDbColumnName() {
        return dbColumnName;
    }

    /**
     * Sets the value of the dbColumnName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDbColumnName(String value) {
        this.dbColumnName = value;
    }

}
