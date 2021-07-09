
package com.tip.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CheckoutDamages complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CheckoutDamages">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="intch_Key" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="unit_Nr" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="line_Nr" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="work_Order_Nr" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="x_Pos" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="y_Pos" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="sketch_Type" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="device_Serial_Nr" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="is_Used_In_CI" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CheckoutDamages", propOrder = {
    "intchKey",
    "unitNr",
    "lineNr",
    "workOrderNr",
    "description",
    "xPos",
    "yPos",
    "sketchType",
    "deviceSerialNr",
    "isUsedInCI"
})
public class CheckoutDamages {

    @XmlElement(name = "intch_Key")
    protected long intchKey;
    @XmlElement(name = "unit_Nr")
    protected int unitNr;
    @XmlElement(name = "line_Nr")
    protected int lineNr;
    @XmlElement(name = "work_Order_Nr", required = true, type = Long.class, nillable = true)
    protected Long workOrderNr;
    @XmlElement(required = true, nillable = true)
    protected String description;
    @XmlElement(name = "x_Pos", required = true, type = Integer.class, nillable = true)
    protected Integer xPos;
    @XmlElement(name = "y_Pos", required = true, type = Integer.class, nillable = true)
    protected Integer yPos;
    @XmlElement(name = "sketch_Type", required = true, nillable = true)
    protected String sketchType;
    @XmlElement(name = "device_Serial_Nr", required = true, nillable = true)
    protected String deviceSerialNr;
    @XmlElement(name = "is_Used_In_CI", required = true, nillable = true)
    protected String isUsedInCI;

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
     *     {@link Long }
     *     
     */
    public Long getWorkOrderNr() {
        return workOrderNr;
    }

    /**
     * Sets the value of the workOrderNr property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setWorkOrderNr(Long value) {
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

    /**
     * Gets the value of the xPos property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getXPos() {
        return xPos;
    }

    /**
     * Sets the value of the xPos property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setXPos(Integer value) {
        this.xPos = value;
    }

    /**
     * Gets the value of the yPos property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getYPos() {
        return yPos;
    }

    /**
     * Sets the value of the yPos property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setYPos(Integer value) {
        this.yPos = value;
    }

    /**
     * Gets the value of the sketchType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSketchType() {
        return sketchType;
    }

    /**
     * Sets the value of the sketchType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSketchType(String value) {
        this.sketchType = value;
    }

    /**
     * Gets the value of the deviceSerialNr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeviceSerialNr() {
        return deviceSerialNr;
    }

    /**
     * Sets the value of the deviceSerialNr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeviceSerialNr(String value) {
        this.deviceSerialNr = value;
    }

    /**
     * Gets the value of the isUsedInCI property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsUsedInCI() {
        return isUsedInCI;
    }

    /**
     * Sets the value of the isUsedInCI property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsUsedInCI(String value) {
        this.isUsedInCI = value;
    }

}
