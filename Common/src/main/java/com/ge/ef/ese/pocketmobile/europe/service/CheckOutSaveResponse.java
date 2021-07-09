
package com.ge.ef.ese.pocketmobile.europe.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="saveReturn" type="{http://service.europe.pocketmobile.ese.ef.ge.com}CheckOutSaveResults"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "saveReturn"
})
@XmlRootElement(name = "checkOutSaveResponse")
public class CheckOutSaveResponse {

    @XmlElement(required = true)
    protected CheckOutSaveResults saveReturn;

    /**
     * Gets the value of the saveReturn property.
     * 
     * @return
     *     possible object is
     *     {@link CheckOutSaveResults }
     *     
     */
    public CheckOutSaveResults getSaveReturn() {
        return saveReturn;
    }

    /**
     * Sets the value of the saveReturn property.
     * 
     * @param value
     *     allowed object is
     *     {@link CheckOutSaveResults }
     *     
     */
    public void setSaveReturn(CheckOutSaveResults value) {
        this.saveReturn = value;
    }

}
