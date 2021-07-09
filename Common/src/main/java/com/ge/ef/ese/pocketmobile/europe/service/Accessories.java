
package com.ge.ef.ese.pocketmobile.europe.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Accessories complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Accessories">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="unitNr" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="accssryInOutInd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="accssryCd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="intchKey" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="accssryCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="accssryDamageInd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="accssryRemarks" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Accessories", propOrder = {
    "unitNr",
    "accssryInOutInd",
    "accssryCd",
    "intchKey",
    "accssryCount",
    "accssryDamageInd",
    "accssryRemarks"
})
public class Accessories {

    protected int unitNr;
    @XmlElement(required = true)
    protected String accssryInOutInd;
    @XmlElement(required = true)
    protected String accssryCd;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long intchKey;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer accssryCount;
    @XmlElement(required = true, nillable = true)
    protected String accssryDamageInd;
    @XmlElement(required = true, nillable = true)
    protected String accssryRemarks;

    /**
     * Gets the value of the unitNr property.
     * 
     */
    public int getUnitNr() {
        return unitNr;
    }

    /**
     * Sets the value of the unitNr property.
     * 
     */
    public void setUnitNr(int value) {
        this.unitNr = value;
    }

    /**
     * Gets the value of the accssryInOutInd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccssryInOutInd() {
        return accssryInOutInd;
    }

    /**
     * Sets the value of the accssryInOutInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccssryInOutInd(String value) {
        this.accssryInOutInd = value;
    }

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
     * Gets the value of the intchKey property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIntchKey() {
        return intchKey;
    }

    /**
     * Sets the value of the intchKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIntchKey(Long value) {
        this.intchKey = value;
    }

    /**
     * Gets the value of the accssryCount property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAccssryCount() {
        return accssryCount;
    }

    /**
     * Sets the value of the accssryCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAccssryCount(Integer value) {
        this.accssryCount = value;
    }

    /**
     * Gets the value of the accssryDamageInd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccssryDamageInd() {
        return accssryDamageInd;
    }

    /**
     * Sets the value of the accssryDamageInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccssryDamageInd(String value) {
        this.accssryDamageInd = value;
    }

    /**
     * Gets the value of the accssryRemarks property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccssryRemarks() {
        return accssryRemarks;
    }

    /**
     * Sets the value of the accssryRemarks property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccssryRemarks(String value) {
        this.accssryRemarks = value;
    }

}
