
package com.ge.ef.ese.pocketmobile.europe.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ServiceTypes complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceTypes">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="blockOnHire" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="serviceTypeCd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="serviceTypeCompare" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="serviceTypeDatatype" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="serviceTypeDesc" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="serviceTypeOnIntch" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceTypes", propOrder = {
    "blockOnHire",
    "serviceTypeCd",
    "serviceTypeCompare",
    "serviceTypeDatatype",
    "serviceTypeDesc",
    "serviceTypeOnIntch"
})
public class ServiceTypes {

    @XmlElement(required = true, nillable = true)
    protected String blockOnHire;
    @XmlElement(required = true, nillable = true)
    protected String serviceTypeCd;
    @XmlElement(required = true, nillable = true)
    protected String serviceTypeCompare;
    @XmlElement(required = true, nillable = true)
    protected String serviceTypeDatatype;
    @XmlElement(required = true, nillable = true)
    protected String serviceTypeDesc;
    @XmlElement(required = true, nillable = true)
    protected String serviceTypeOnIntch;

    /**
     * Gets the value of the blockOnHire property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBlockOnHire() {
        return blockOnHire;
    }

    /**
     * Sets the value of the blockOnHire property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBlockOnHire(String value) {
        this.blockOnHire = value;
    }

    /**
     * Gets the value of the serviceTypeCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceTypeCd() {
        return serviceTypeCd;
    }

    /**
     * Sets the value of the serviceTypeCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceTypeCd(String value) {
        this.serviceTypeCd = value;
    }

    /**
     * Gets the value of the serviceTypeCompare property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceTypeCompare() {
        return serviceTypeCompare;
    }

    /**
     * Sets the value of the serviceTypeCompare property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceTypeCompare(String value) {
        this.serviceTypeCompare = value;
    }

    /**
     * Gets the value of the serviceTypeDatatype property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceTypeDatatype() {
        return serviceTypeDatatype;
    }

    /**
     * Sets the value of the serviceTypeDatatype property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceTypeDatatype(String value) {
        this.serviceTypeDatatype = value;
    }

    /**
     * Gets the value of the serviceTypeDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceTypeDesc() {
        return serviceTypeDesc;
    }

    /**
     * Sets the value of the serviceTypeDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceTypeDesc(String value) {
        this.serviceTypeDesc = value;
    }

    /**
     * Gets the value of the serviceTypeOnIntch property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceTypeOnIntch() {
        return serviceTypeOnIntch;
    }

    /**
     * Sets the value of the serviceTypeOnIntch property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceTypeOnIntch(String value) {
        this.serviceTypeOnIntch = value;
    }

}
