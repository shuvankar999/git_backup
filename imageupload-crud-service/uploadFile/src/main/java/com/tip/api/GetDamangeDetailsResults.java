
package com.tip.api;

import com.tip.api.ArrayOfCheckoutDamages;
import com.tip.api.BaseReponse;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getDamangeDetailsResults complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getDamangeDetailsResults">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.europe.pocketmobile.ese.ef.ge.com}BaseReponse">
 *       &lt;sequence>
 *         &lt;element name="damageDetails" type="{http://service.europe.pocketmobile.ese.ef.ge.com}ArrayOfCheckoutDamages"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getDamangeDetailsResults", propOrder = {
    "damageDetails"
})
public class GetDamangeDetailsResults
    extends BaseReponse
{

    @XmlElement(required = true, nillable = true)
    protected ArrayOfCheckoutDamages damageDetails;

    /**
     * Gets the value of the damageDetails property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfCheckoutDamages }
     *     
     */
    public ArrayOfCheckoutDamages getDamageDetails() {
        return damageDetails;
    }

    /**
     * Sets the value of the damageDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfCheckoutDamages }
     *     
     */
    public void setDamageDetails(ArrayOfCheckoutDamages value) {
        this.damageDetails = value;
    }

}
