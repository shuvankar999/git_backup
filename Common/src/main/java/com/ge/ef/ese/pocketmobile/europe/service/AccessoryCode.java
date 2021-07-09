
package com.ge.ef.ese.pocketmobile.europe.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AccessoryCode complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AccessoryCode">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="accssryCd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="accssryDesc" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="accssryType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AccessoryCode", propOrder = {
    "accssryCd",
    "accssryDesc",
    "accssryType"
})
public class AccessoryCode {

    @XmlElement(required = true)
    protected String accssryCd;
    @XmlElement(required = true, nillable = true)
    protected String accssryDesc;
    @XmlElement(required = true, nillable = true)
    protected String accssryType;

    /**
     * Gets the value of the accssryCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccssryCd() {
        return accssryCd;
    }

    /**
     * Sets the value of the accssryCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccssryCd(String value) {
        this.accssryCd = value;
    }

    /**
     * Gets the value of the accssryDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccssryDesc() {
        return accssryDesc;
    }

    /**
     * Sets the value of the accssryDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccssryDesc(String value) {
        this.accssryDesc = value;
    }

    /**
     * Gets the value of the accssryType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccssryType() {
        return accssryType;
    }

    /**
     * Sets the value of the accssryType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccssryType(String value) {
        this.accssryType = value;
    }

}
