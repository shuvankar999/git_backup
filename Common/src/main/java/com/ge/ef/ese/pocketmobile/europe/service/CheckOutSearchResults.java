
package com.ge.ef.ese.pocketmobile.europe.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CheckOutSearchResults complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CheckOutSearchResults">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.europe.pocketmobile.ese.ef.ge.com}BaseReponse">
 *       &lt;sequence>
 *         &lt;element name="unitsList" type="{http://service.europe.pocketmobile.ese.ef.ge.com}ArrayOfReservedUnits"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CheckOutSearchResults", propOrder = {
    "unitsList"
})
public class CheckOutSearchResults
    extends BaseReponse
{

    @XmlElement(required = true, nillable = true)
    protected ArrayOfReservedUnits unitsList;

    /**
     * Gets the value of the unitsList property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfReservedUnits }
     *     
     */
    public ArrayOfReservedUnits getUnitsList() {
        return unitsList;
    }

    /**
     * Sets the value of the unitsList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfReservedUnits }
     *     
     */
    public void setUnitsList(ArrayOfReservedUnits value) {
        this.unitsList = value;
    }

}
