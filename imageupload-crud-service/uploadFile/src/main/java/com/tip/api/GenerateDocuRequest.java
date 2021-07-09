
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
 *         &lt;element name="intchKey" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="insType" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "intchKey",
    "insType"
})
@XmlRootElement(name = "generateDocuRequest")
public class GenerateDocuRequest {

    protected long intchKey;
    @XmlElement(required = true)
    protected String insType;

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
     * Gets the value of the insType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInsType() {
        return insType;
    }

    /**
     * Sets the value of the insType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInsType(String value) {
        this.insType = value;
    }

}
