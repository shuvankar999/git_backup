
package com.ge.ef.ese.pocketmobile.europe.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Damages complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Damages">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="intchKey" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="unitNr" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="lineNr" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="workOrderNr" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Damages", propOrder = {
    "intchKey",
    "unitNr",
    "lineNr",
    "workOrderNr",
    "description"
})
public class Damages {

    protected long intchKey;
    protected int unitNr;
    protected int lineNr;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer workOrderNr;
    @XmlElement(required = true, nillable = true)
    protected String description;

    /**
     * Gets the value of the intchKey property.
     * 
     */
    public long getIntchKey() {
        return intchKey;
    }

    /**
     * Sets the value of the intchKey property.
     * 
     */
    public void setIntchKey(long value) {
        this.intchKey = value;
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
     * Gets the value of the lineNr property.
     * 
     */
    public int getLineNr() {
        return lineNr;
    }

    /**
     * Sets the value of the lineNr property.
     * 
     */
    public void setLineNr(int value) {
        this.lineNr = value;
    }

    /**
     * Gets the value of the workOrderNr property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getWorkOrderNr() {
        return workOrderNr;
    }

    /**
     * Sets the value of the workOrderNr property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setWorkOrderNr(Integer value) {
        this.workOrderNr = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

}
