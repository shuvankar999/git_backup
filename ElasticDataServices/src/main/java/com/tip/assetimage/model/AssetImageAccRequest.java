//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.03.19 at 05:26:13 PM IST 
//


package com.tip.assetimage.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AssetImageAccRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AssetImageAccRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="equipment_nr" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="App_Cd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="asset_type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="imageType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="imageLoc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="seq" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ssoId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AssetImageAccRequest", propOrder = {
    "equipmentNr",
    "appCd",
    "assetType",
    "imageType",
    "code",
    "imageLoc",
    "seq",
    "ssoId"
})
public class AssetImageAccRequest {

    @XmlElement(name = "equipment_nr")
    protected Integer equipmentNr;
    @XmlElement(name = "App_Cd")
    protected String appCd;
    @XmlElement(name = "asset_type")
    protected String assetType;
    protected String imageType;
    protected String code;
    protected String imageLoc;
    protected String seq;
    protected String ssoId;

    /**
     * Gets the value of the equipmentNr property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getEquipmentNr() {
        return equipmentNr;
    }

    /**
     * Sets the value of the equipmentNr property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setEquipmentNr(Integer value) {
        this.equipmentNr = value;
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
     * Gets the value of the code property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the value of the code property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCode(String value) {
        this.code = value;
    }

    /**
     * Gets the value of the imageLoc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImageLoc() {
        return imageLoc;
    }

    /**
     * Sets the value of the imageLoc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImageLoc(String value) {
        this.imageLoc = value;
    }

    /**
     * Gets the value of the seq property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeq() {
        return seq;
    }

    /**
     * Sets the value of the seq property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeq(String value) {
        this.seq = value;
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

}
