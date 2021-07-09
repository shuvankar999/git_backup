
package com.tip.api;

import com.tip.api.CheckInSearchReturn;
import com.tip.api.CheckOutSearchReturn;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *       &lt;choice>
 *         &lt;element name="checkInSearchReturn" type="{http://service.europe.pocketmobile.ese.ef.ge.com}CheckInSearchReturn"/>
 *         &lt;element name="checkOutSearchReturn" type="{http://service.europe.pocketmobile.ese.ef.ge.com}CheckOutSearchReturn"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "checkInSearchReturn",
    "checkOutSearchReturn"
})
@XmlRootElement(name = "searchResponse")
public class SearchResponse {

    protected CheckInSearchReturn checkInSearchReturn;
    protected CheckOutSearchReturn checkOutSearchReturn;

    /**
     * Gets the value of the checkInSearchReturn property.
     * 
     * @return
     *     possible object is
     *     {@link CheckInSearchReturn }
     *     
     */
    public CheckInSearchReturn getCheckInSearchReturn() {
        return checkInSearchReturn;
    }

    /**
     * Sets the value of the checkInSearchReturn property.
     * 
     * @param value
     *     allowed object is
     *     {@link CheckInSearchReturn }
     *     
     */
    public void setCheckInSearchReturn(CheckInSearchReturn value) {
        this.checkInSearchReturn = value;
    }

    /**
     * Gets the value of the checkOutSearchReturn property.
     * 
     * @return
     *     possible object is
     *     {@link CheckOutSearchReturn }
     *     
     */
    public CheckOutSearchReturn getCheckOutSearchReturn() {
        return checkOutSearchReturn;
    }

    /**
     * Sets the value of the checkOutSearchReturn property.
     * 
     * @param value
     *     allowed object is
     *     {@link CheckOutSearchReturn }
     *     
     */
    public void setCheckOutSearchReturn(CheckOutSearchReturn value) {
        this.checkOutSearchReturn = value;
    }

}
