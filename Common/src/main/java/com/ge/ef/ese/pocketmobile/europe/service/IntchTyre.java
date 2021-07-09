
package com.ge.ef.ese.pocketmobile.europe.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for IntchTyre complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IntchTyre">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="tyreCd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tyreInOutInd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="unitNr" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="intchKey" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="tyreProfileDepth" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="tyreMake" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tyreAtmosphere" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IntchTyre", propOrder = {
    "tyreCd",
    "tyreInOutInd",
    "unitNr",
    "intchKey",
    "tyreProfileDepth",
    "tyreMake",
    "tyreAtmosphere"
})
public class IntchTyre {

    @XmlElement(required = true)
    protected String tyreCd;
    @XmlElement(required = true)
    protected String tyreInOutInd;
    protected int unitNr;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long intchKey;
    @XmlElement(required = true, type = Float.class, nillable = true)
    protected Float tyreProfileDepth;
    @XmlElement(required = true, nillable = true)
    protected String tyreMake;
    @XmlElement(required = true, type = Float.class, nillable = true)
    protected Float tyreAtmosphere;

    /**
     * Gets the value of the tyreCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTyreCd() {
        return tyreCd;
    }

    /**
     * Sets the value of the tyreCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTyreCd(String value) {
        this.tyreCd = value;
    }

    /**
     * Gets the value of the tyreInOutInd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTyreInOutInd() {
        return tyreInOutInd;
    }

    /**
     * Sets the value of the tyreInOutInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTyreInOutInd(String value) {
        this.tyreInOutInd = value;
    }

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
     * Gets the value of the tyreProfileDepth property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getTyreProfileDepth() {
        return tyreProfileDepth;
    }

    /**
     * Sets the value of the tyreProfileDepth property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setTyreProfileDepth(Float value) {
        this.tyreProfileDepth = value;
    }

    /**
     * Gets the value of the tyreMake property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTyreMake() {
        return tyreMake;
    }

    /**
     * Sets the value of the tyreMake property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTyreMake(String value) {
        this.tyreMake = value;
    }

    /**
     * Gets the value of the tyreAtmosphere property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getTyreAtmosphere() {
        return tyreAtmosphere;
    }

    /**
     * Sets the value of the tyreAtmosphere property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setTyreAtmosphere(Float value) {
        this.tyreAtmosphere = value;
    }

}
