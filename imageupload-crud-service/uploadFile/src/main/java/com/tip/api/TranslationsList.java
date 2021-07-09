
package com.tip.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TranslationsList complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TranslationsList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="LanguageId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="TextCd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Text" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="EntityCd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TranslationsList", propOrder = {
    "languageId",
    "textCd",
    "text",
    "entityCd"
})
public class TranslationsList {

    @XmlElement(name = "LanguageId")
    protected int languageId;
    @XmlElement(name = "TextCd", required = true, nillable = true)
    protected String textCd;
    @XmlElement(name = "Text", required = true, nillable = true)
    protected String text;
    @XmlElement(name = "EntityCd", required = true, nillable = true)
    protected String entityCd;

    /**
     * Gets the value of the languageId property.
     * 
     */
    public int getLanguageId() {
        return languageId;
    }

    /**
     * Sets the value of the languageId property.
     * 
     */
    public void setLanguageId(int value) {
        this.languageId = value;
    }

    /**
     * Gets the value of the textCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTextCd() {
        return textCd;
    }

    /**
     * Sets the value of the textCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTextCd(String value) {
        this.textCd = value;
    }

    /**
     * Gets the value of the text property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the value of the text property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setText(String value) {
        this.text = value;
    }

    /**
     * Gets the value of the entityCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntityCd() {
        return entityCd;
    }

    /**
     * Sets the value of the entityCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntityCd(String value) {
        this.entityCd = value;
    }

}
