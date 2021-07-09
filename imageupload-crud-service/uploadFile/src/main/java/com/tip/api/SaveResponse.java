
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
 *         &lt;element name="saveReturn" type="{http://service.europe.pocketmobile.ese.ef.ge.com}SaveResults"/>
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
@XmlRootElement(name = "saveResponse")
public class SaveResponse {

    @XmlElement(required = true)
    protected SaveResults saveReturn;

    /**
     * Gets the value of the saveReturn property.
     * 
     * @return
     *     possible object is
     *     {@link SaveResults }
     *     
     */
    public SaveResults getSaveReturn() {
        return saveReturn;
    }

    /**
     * Sets the value of the saveReturn property.
     * 
     * @param value
     *     allowed object is
     *     {@link SaveResults }
     *     
     */
    public void setSaveReturn(SaveResults value) {
        this.saveReturn = value;
    }

}
