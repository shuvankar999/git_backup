//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.01.11 at 07:52:36 PM IST 
//


package com.tip.inspection.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Keys complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Keys">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Sso_Id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Public_K" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Private_K" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Keys", propOrder = {
    "ssoId",
    "publicK",
    "privateK"
})
public class Keys {

    @XmlElement(name = "Sso_Id")
    protected String ssoId;
    @XmlElement(name = "Public_K")
    protected String publicK;
    @XmlElement(name = "Private_K")
    protected String privateK;

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
     * Gets the value of the publicK property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPublicK() {
        return publicK;
    }

    /**
     * Sets the value of the publicK property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPublicK(String value) {
        this.publicK = value;
    }

    /**
     * Gets the value of the privateK property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrivateK() {
        return privateK;
    }

    /**
     * Sets the value of the privateK property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrivateK(String value) {
        this.privateK = value;
    }

}
