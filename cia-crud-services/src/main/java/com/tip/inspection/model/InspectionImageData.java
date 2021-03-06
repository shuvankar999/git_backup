//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.09.18 at 03:30:33 PM IST 
//


package com.tip.inspection.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for InspectionImageData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="InspectionImageData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Insp_Cd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Damage_Line_Nr" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Damage_Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Img_Type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Img_Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Img_Comment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Img_Count" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Img_Path" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InspectionImageData", propOrder = {
    "inspCd",
    "damageLineNr",
    "damageDescription",
    "imgType",
    "imgName",
    "imgComment",
    "imgCount",
    "imgPath"
})
public class InspectionImageData {

    @XmlElement(name = "Insp_Cd")
    protected String inspCd;
    @XmlElement(name = "Damage_Line_Nr")
    protected Integer damageLineNr;
    @XmlElement(name = "Damage_Description")
    protected String damageDescription;
    @XmlElement(name = "Img_Type")
    protected String imgType;
    @XmlElement(name = "Img_Name")
    protected String imgName;
    @XmlElement(name = "Img_Comment")
    protected String imgComment;
    @XmlElement(name = "Img_Count")
    protected Integer imgCount;
    @XmlElement(name = "Img_Path")
    protected String imgPath;

    /**
     * Gets the value of the inspCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInspCd() {
        return inspCd;
    }

    /**
     * Sets the value of the inspCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInspCd(String value) {
        this.inspCd = value;
    }

    /**
     * Gets the value of the damageLineNr property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDamageLineNr() {
        return damageLineNr;
    }

    /**
     * Sets the value of the damageLineNr property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDamageLineNr(Integer value) {
        this.damageLineNr = value;
    }

    /**
     * Gets the value of the damageDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDamageDescription() {
        return damageDescription;
    }

    /**
     * Sets the value of the damageDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDamageDescription(String value) {
        this.damageDescription = value;
    }

    /**
     * Gets the value of the imgType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImgType() {
        return imgType;
    }

    /**
     * Sets the value of the imgType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImgType(String value) {
        this.imgType = value;
    }

    /**
     * Gets the value of the imgName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImgName() {
        return imgName;
    }

    /**
     * Sets the value of the imgName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImgName(String value) {
        this.imgName = value;
    }

    /**
     * Gets the value of the imgComment property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImgComment() {
        return imgComment;
    }

    /**
     * Sets the value of the imgComment property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImgComment(String value) {
        this.imgComment = value;
    }

    /**
     * Gets the value of the imgCount property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getImgCount() {
        return imgCount;
    }

    /**
     * Sets the value of the imgCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setImgCount(Integer value) {
        this.imgCount = value;
    }

    /**
     * Gets the value of the imgPath property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImgPath() {
        return imgPath;
    }

    /**
     * Sets the value of the imgPath property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImgPath(String value) {
        this.imgPath = value;
    }

}
