//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.01.29 at 05:15:24 PM IST 
//


package com.tip.assetimage.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DeleteAssetImageRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DeleteAssetImageRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="unit_nr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="app_cd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sso_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="asset_type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="imageType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="imageId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="imageName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DeleteAssetImageRequest", propOrder = {
    "unitNr",
    "appCd",
    "ssoId",
    "assetType",
    "imageType",
    "imageId",
    "imageName"
})
public class DeleteAssetImageRequest {

    @XmlElement(name = "unit_nr")
    protected String unitNr;
    @XmlElement(name = "app_cd")
    protected String appCd;
    @XmlElement(name = "sso_id")
    protected String ssoId;
    @XmlElement(name = "asset_type")
    protected String assetType;
    protected String imageType;
    protected Integer imageId;
    protected String imageName;

    /**
     * Gets the value of the unitNr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnitNr() {
        return unitNr;
    }

    /**
     * Sets the value of the unitNr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnitNr(String value) {
        this.unitNr = value;
    }

    /**
     * Gets the value of the appCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAppCd() {
        return appCd;
    }

    /**
     * Sets the value of the appCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAppCd(String value) {
        this.appCd = value;
    }

    /**
     * Gets the value of the ssoId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSsoId() {
        return ssoId;
    }

    /**
     * Sets the value of the ssoId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSsoId(String value) {
        this.ssoId = value;
    }

    /**
     * Gets the value of the assetType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAssetType() {
        return assetType;
    }

    /**
     * Sets the value of the assetType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAssetType(String value) {
        this.assetType = value;
    }

    /**
     * Gets the value of the imageType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImageType() {
        return imageType;
    }

    /**
     * Sets the value of the imageType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImageType(String value) {
        this.imageType = value;
    }

    /**
     * Gets the value of the imageId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getImageId() {
        return imageId;
    }

    /**
     * Sets the value of the imageId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setImageId(Integer value) {
        this.imageId = value;
    }

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

}