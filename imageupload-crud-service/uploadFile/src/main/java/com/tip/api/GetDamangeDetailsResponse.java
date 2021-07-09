
package com.tip.api;

import javax.xml.bind.annotation.*;


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
 *         &lt;element name="getDamangeDetailsReturn" type="{http://service.europe.pocketmobile.ese.ef.ge.com}getDamangeDetailsResults"/>
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
    "getDamangeDetailsReturn"
})
@XmlRootElement(name = "getDamangeDetailsResponse")
public class GetDamangeDetailsResponse {

    @XmlElement(required = true)
    protected GetDamangeDetailsResults getDamangeDetailsReturn;

    /**
     * Gets the value of the getDamangeDetailsReturn property.
     * 
     * @return
     *     possible object is
     *     {@link GetDamangeDetailsResults }
     *     
     */
    public GetDamangeDetailsResults getGetDamangeDetailsReturn() {
        return getDamangeDetailsReturn;
    }

    /**
     * Sets the value of the getDamangeDetailsReturn property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetDamangeDetailsResults }
     *     
     */
    public void setGetDamangeDetailsReturn(GetDamangeDetailsResults value) {
        this.getDamangeDetailsReturn = value;
    }

}
