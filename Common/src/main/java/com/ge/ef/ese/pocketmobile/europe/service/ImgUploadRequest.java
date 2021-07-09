
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
 *         &lt;element name="intchKey" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="insType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="noOfPics" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="picType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="signOrDD" type="{http://service.europe.pocketmobile.ese.ef.ge.com}image"/>
 *         &lt;element name="damagePic" type="{http://service.europe.pocketmobile.ese.ef.ge.com}picture"/>
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
    "insType",
    "noOfPics",
    "picType",
    "signOrDD",
    "damagePic"
})
@XmlRootElement(name = "imgUploadRequest")
public class ImgUploadRequest {

    protected long intchKey;
    @XmlElement(required = true)
    protected String insType;
    @XmlElement(required = true)
    protected String noOfPics;
    @XmlElement(required = true)
    protected String picType;
    @XmlElement(required = true, nillable = true)
    protected Image signOrDD;
    @XmlElement(required = true, nillable = true)
    protected Picture damagePic;

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

    /**
     * Gets the value of the noOfPics property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNoOfPics() {
        return noOfPics;
    }

    /**
     * Sets the value of the noOfPics property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNoOfPics(String value) {
        this.noOfPics = value;
    }

    /**
     * Gets the value of the picType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPicType() {
        return picType;
    }

    /**
     * Sets the value of the picType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPicType(String value) {
        this.picType = value;
    }

    /**
     * Gets the value of the signOrDD property.
     * 
     * @return
     *     possible object is
     *     {@link Image }
     *     
     */
    public Image getSignOrDD() {
        return signOrDD;
    }

    /**
     * Sets the value of the signOrDD property.
     * 
     * @param value
     *     allowed object is
     *     {@link Image }
     *     
     */
    public void setSignOrDD(Image value) {
        this.signOrDD = value;
    }

    /**
     * Gets the value of the damagePic property.
     * 
     * @return
     *     possible object is
     *     {@link Picture }
     *     
     */
    public Picture getDamagePic() {
        return damagePic;
    }

    /**
     * Sets the value of the damagePic property.
     * 
     * @param value
     *     allowed object is
     *     {@link Picture }
     *     
     */
    public void setDamagePic(Picture value) {
        this.damagePic = value;
    }

}
