//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.10.03 at 08:32:30 PM IST 
//


package com.tip.estimationreport.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CustomerDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CustomerDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CustName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CustAddr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CustPhone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CustFax" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CustEmail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CustomerDetails", propOrder = {
    "custName",
    "custAddr",
    "custPhone",
    "custFax",
    "custEmail"
})
public class CustomerDetails {

    @XmlElement(name = "CustName")
    protected String custName;
    @XmlElement(name = "CustAddr")
    protected String custAddr;
    @XmlElement(name = "CustPhone")
    protected String custPhone;
    @XmlElement(name = "CustFax")
    protected String custFax;
    @XmlElement(name = "CustEmail")
    protected String custEmail;

    /**
     * Gets the value of the custName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustName() {
        return custName;
    }

    /**
     * Sets the value of the custName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustName(String value) {
        this.custName = value;
    }

    /**
     * Gets the value of the custAddr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustAddr() {
        return custAddr;
    }

    /**
     * Sets the value of the custAddr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustAddr(String value) {
        this.custAddr = value;
    }

    /**
     * Gets the value of the custPhone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustPhone() {
        return custPhone;
    }

    /**
     * Sets the value of the custPhone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustPhone(String value) {
        this.custPhone = value;
    }

    /**
     * Gets the value of the custFax property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustFax() {
        return custFax;
    }

    /**
     * Sets the value of the custFax property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustFax(String value) {
        this.custFax = value;
    }

    /**
     * Gets the value of the custEmail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustEmail() {
        return custEmail;
    }

    /**
     * Sets the value of the custEmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustEmail(String value) {
        this.custEmail = value;
    }

}
