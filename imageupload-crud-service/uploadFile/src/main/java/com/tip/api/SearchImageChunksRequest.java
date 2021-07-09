
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
 *         &lt;element name="ImageName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="BaseCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Parts" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "imageName",
    "baseCode",
    "parts"
})
@XmlRootElement(name = "searchImageChunksRequest")
public class SearchImageChunksRequest {

    @XmlElement(name = "ImageName", required = true)
    protected String imageName;
    @XmlElement(name = "BaseCode", required = true)
    protected String baseCode;
    @XmlElement(name = "Parts", required = true, nillable = true)
    protected String parts;

    /**
     * Gets the value of the imageName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImageName() {
        return imageName;
    }

    /**
     * Sets the value of the imageName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImageName(String value) {
        this.imageName = value;
    }

    /**
     * Gets the value of the baseCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBaseCode() {
        return baseCode;
    }

    /**
     * Sets the value of the baseCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBaseCode(String value) {
        this.baseCode = value;
    }

    /**
     * Gets the value of the parts property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParts() {
        return parts;
    }

    /**
     * Sets the value of the parts property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParts(String value) {
        this.parts = value;
    }

}
