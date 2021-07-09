//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.09.27 at 04:10:38 PM IST 
//


package com.tip.estimation.model;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for InvoiceTyreService complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="InvoiceTyreService">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="EstWoId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="EstWotId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="tyreService" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="charges" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="tyreServicegrpId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="tyreServicesubgrpId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="tyreServiceItemId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InvoiceTyreService", propOrder = {
    "estWoId",
    "estWotId",
    "tyreService",
    "charges",
    "tyreServicegrpId",
    "tyreServicesubgrpId",
    "tyreServiceItemId"
})
public class InvoiceTyreService {

    @XmlElement(name = "EstWoId")
    protected Integer estWoId;
    @XmlElement(name = "EstWotId")
    protected Integer estWotId;
    protected String tyreService;
    protected BigDecimal charges;
    protected Integer tyreServicegrpId;
    protected Integer tyreServicesubgrpId;
    protected Integer tyreServiceItemId;

    /**
     * Gets the value of the estWoId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getEstWoId() {
        return estWoId;
    }

    /**
     * Sets the value of the estWoId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setEstWoId(Integer value) {
        this.estWoId = value;
    }

    /**
     * Gets the value of the estWotId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getEstWotId() {
        return estWotId;
    }

    /**
     * Sets the value of the estWotId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setEstWotId(Integer value) {
        this.estWotId = value;
    }

    /**
     * Gets the value of the tyreService property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTyreService() {
        return tyreService;
    }

    /**
     * Sets the value of the tyreService property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTyreService(String value) {
        this.tyreService = value;
    }

    /**
     * Gets the value of the charges property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCharges() {
        return charges;
    }

    /**
     * Sets the value of the charges property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCharges(BigDecimal value) {
        this.charges = value;
    }

    /**
     * Gets the value of the tyreServicegrpId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTyreServicegrpId() {
        return tyreServicegrpId;
    }

    /**
     * Sets the value of the tyreServicegrpId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTyreServicegrpId(Integer value) {
        this.tyreServicegrpId = value;
    }

    /**
     * Gets the value of the tyreServicesubgrpId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTyreServicesubgrpId() {
        return tyreServicesubgrpId;
    }

    /**
     * Sets the value of the tyreServicesubgrpId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTyreServicesubgrpId(Integer value) {
        this.tyreServicesubgrpId = value;
    }

    /**
     * Gets the value of the tyreServiceItemId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTyreServiceItemId() {
        return tyreServiceItemId;
    }

    /**
     * Sets the value of the tyreServiceItemId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTyreServiceItemId(Integer value) {
        this.tyreServiceItemId = value;
    }

}
