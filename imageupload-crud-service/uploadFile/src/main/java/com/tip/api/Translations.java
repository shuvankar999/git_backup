
package com.tip.api;

import com.tip.api.CategoryGroupTrans;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for Translations complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Translations">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="entityCd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="languageId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="languageName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="text" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="textCd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Translations", propOrder = {
    "entityCd",
    "languageId",
    "languageName",
    "text",
    "textCd"
})
@XmlSeeAlso({
    CategoryGroupTrans.class
})
public class Translations {

    @XmlElement(required = true)
    protected String entityCd;
    @XmlElement(required = true)
    protected String languageId;
    @XmlElement(required = true)
    protected String languageName;
    @XmlElement(required = true)
    protected String text;
    @XmlElement(required = true)
    protected String textCd;

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

    /**
     * Gets the value of the languageId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLanguageId() {
        return languageId;
    }

    /**
     * Sets the value of the languageId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLanguageId(String value) {
        this.languageId = value;
    }

    /**
     * Gets the value of the languageName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLanguageName() {
        return languageName;
    }

    /**
     * Sets the value of the languageName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLanguageName(String value) {
        this.languageName = value;
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

}
