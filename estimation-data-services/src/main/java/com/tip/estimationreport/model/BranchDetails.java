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
 * <p>Java class for BranchDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BranchDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BranchName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BranchAddr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BranchPhone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BranchFax" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BranchDetails", propOrder = {
    "branchName",
    "branchAddr",
    "branchPhone",
    "branchFax"
})
public class BranchDetails {

    @XmlElement(name = "BranchName")
    protected String branchName;
    @XmlElement(name = "BranchAddr")
    protected String branchAddr;
    @XmlElement(name = "BranchPhone")
    protected String branchPhone;
    @XmlElement(name = "BranchFax")
    protected String branchFax;

    /**
     * Gets the value of the branchName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBranchName() {
        return branchName;
    }

    /**
     * Sets the value of the branchName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBranchName(String value) {
        this.branchName = value;
    }

    /**
     * Gets the value of the branchAddr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBranchAddr() {
        return branchAddr;
    }

    /**
     * Sets the value of the branchAddr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBranchAddr(String value) {
        this.branchAddr = value;
    }

    /**
     * Gets the value of the branchPhone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBranchPhone() {
        return branchPhone;
    }

    /**
     * Sets the value of the branchPhone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBranchPhone(String value) {
        this.branchPhone = value;
    }

    /**
     * Gets the value of the branchFax property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBranchFax() {
        return branchFax;
    }

    /**
     * Sets the value of the branchFax property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBranchFax(String value) {
        this.branchFax = value;
    }

}
