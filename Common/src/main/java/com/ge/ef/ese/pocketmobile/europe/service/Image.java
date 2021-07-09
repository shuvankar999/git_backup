
package com.ge.ef.ese.pocketmobile.europe.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for image complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="image">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="binarydata" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "image", propOrder = {
    "name",
    "binarydata"
})
public class Image {

    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected byte[] binarydata;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the binarydata property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getBinarydata() {
        return binarydata;
    }

    /**
     * Sets the value of the binarydata property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setBinarydata(byte[] value) {
        this.binarydata = value;
    }

}
