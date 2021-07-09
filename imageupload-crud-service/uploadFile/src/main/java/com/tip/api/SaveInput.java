
package com.tip.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SaveInput complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SaveInput">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="interchange" type="{http://service.europe.pocketmobile.ese.ef.ge.com}SaveInterchange"/>
 *         &lt;element name="intchTyre" type="{http://service.europe.pocketmobile.ese.ef.ge.com}ArrayOfIntchTyre"/>
 *         &lt;element name="damages" type="{http://service.europe.pocketmobile.ese.ef.ge.com}ArrayOfDamages"/>
 *         &lt;element name="accessories" type="{http://service.europe.pocketmobile.ese.ef.ge.com}ArrayOfAccessories"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SaveInput", propOrder = {
    "interchange",
    "intchTyre",
    "damages",
    "accessories"
})
public class SaveInput {

    @XmlElement(required = true)
    protected SaveInterchange interchange;
    @XmlElement(required = true, nillable = true)
    protected ArrayOfIntchTyre intchTyre;
    @XmlElement(required = true, nillable = true)
    protected ArrayOfDamages damages;
    @XmlElement(required = true, nillable = true)
    protected ArrayOfAccessories accessories;

    /**
     * Gets the value of the interchange property.
     * 
     * @return
     *     possible object is
     *     {@link SaveInterchange }
     *     
     */
    public SaveInterchange getInterchange() {
        return interchange;
    }

    /**
     * Sets the value of the interchange property.
     * 
     * @param value
     *     allowed object is
     *     {@link SaveInterchange }
     *     
     */
    public void setInterchange(SaveInterchange value) {
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
     * Gets the value of the damages property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfDamages }
     *     
     */
    public ArrayOfDamages getDamages() {
        return damages;
    }

    /**
     * Sets the value of the damages property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfDamages }
     *     
     */
    public void setDamages(ArrayOfDamages value) {
        this.damages = value;
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

}
