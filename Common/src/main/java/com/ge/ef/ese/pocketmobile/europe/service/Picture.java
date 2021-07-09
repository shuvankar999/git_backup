
package com.ge.ef.ese.pocketmobile.europe.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for picture complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="picture">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="picture" type="{http://service.europe.pocketmobile.ese.ef.ge.com}image"/>
 *         &lt;element name="comment" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="lineNr" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "picture", propOrder = {
    "picture",
    "comment",
    "lineNr",
    "description"
})
public class Picture {

    @XmlElement(required = true, nillable = true)
    protected Image picture;
    @XmlElement(required = true, nillable = true)
    protected String comment;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer lineNr;
    @XmlElement(required = true, nillable = true)
    protected String description;

    /**
     * Gets the value of the picture property.
     * 
     * @return
     *     possible object is
     *     {@link Image }
     *     
     */
    public Image getPicture() {
        return picture;
    }

    /**
     * Sets the value of the picture property.
     * 
     * @param value
     *     allowed object is
     *     {@link Image }
     *     
     */
    public void setPicture(Image value) {
        this.picture = value;
    }

    /**
     * Gets the value of the comment property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets the value of the comment property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComment(String value) {
        this.comment = value;
    }

    /**
     * Gets the value of the lineNr property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getLineNr() {
        return lineNr;
    }

    /**
     * Sets the value of the lineNr property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setLineNr(Integer value) {
        this.lineNr = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

}
