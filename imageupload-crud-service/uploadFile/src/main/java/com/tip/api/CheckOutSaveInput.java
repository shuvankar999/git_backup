
package com.tip.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CheckOutSaveInput complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CheckOutSaveInput">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="interchange" type="{http://service.europe.pocketmobile.ese.ef.ge.com}CheckOutSaveInterchange"/>
 *         &lt;element name="intchTyre" type="{http://service.europe.pocketmobile.ese.ef.ge.com}ArrayOfIntchTyre"/>
 *         &lt;element name="accessories" type="{http://service.europe.pocketmobile.ese.ef.ge.com}ArrayOfAccessories"/>
 *         &lt;element name="checkoutDamages" type="{http://service.europe.pocketmobile.ese.ef.ge.com}ArrayOfCheckoutDamages"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CheckOutSaveInput", propOrder = {
    "interchange",
    "intchTyre",
    "accessories",
    "checkoutDamages"
})
public class CheckOutSaveInput {

    @XmlElement(required = true)
    protected CheckOutSaveInterchange interchange;
    @XmlElement(required = true, nillable = true)
    protected ArrayOfIntchTyre intchTyre;
    @XmlElement(required = true, nillable = true)
    protected ArrayOfAccessories accessories;
    @XmlElement(required = true, nillable = true)
    protected ArrayOfCheckoutDamages checkoutDamages;

    /**
     * Gets the value of the interchange property.
     * 
     * @return
     *     possible object is
     *     {@link CheckOutSaveInterchange }
     *     
     */
    public CheckOutSaveInterchange getInterchange() {
        return interchange;
    }

    /**
     * Sets the value of the interchange property.
     * 
     * @param value
     *     allowed object is
     *     {@link CheckOutSaveInterchange }
     *     
     */
    public void setInterchange(CheckOutSaveInterchange value) {
        this.interchange = value;
    }

    /**
     * Gets the value of the intchTyre property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfIntchTyre }
     *     
     */
    public ArrayOfIntchTyre getIntchTyre() {
        return intchTyre;
    }

    /**
     * Sets the value of the intchTyre property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfIntchTyre }
     *     
     */
    public void setIntchTyre(ArrayOfIntchTyre value) {
        this.intchTyre = value;
    }

    /**
     * Gets the value of the accessories property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfAccessories }
     *     
     */
    public ArrayOfAccessories getAccessories() {
        return accessories;
    }

    /**
     * Sets the value of the accessories property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfAccessories }
     *     
     */
    public void setAccessories(ArrayOfAccessories value) {
        this.accessories = value;
    }

    /**
     * Gets the value of the checkoutDamages property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfCheckoutDamages }
     *     
     */
    public ArrayOfCheckoutDamages getCheckoutDamages() {
        return checkoutDamages;
    }

    /**
     * Sets the value of the checkoutDamages property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfCheckoutDamages }
     *     
     */
    public void setCheckoutDamages(ArrayOfCheckoutDamages value) {
        this.checkoutDamages = value;
    }

}
