
package com.tip.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CategoryGroupTrans complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CategoryGroupTrans">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.europe.pocketmobile.ese.ef.ge.com}Translations">
 *       &lt;sequence>
 *         &lt;element name="catgrType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CategoryGroupTrans", propOrder = {
    "catgrType"
})
public class CategoryGroupTrans
    extends Translations
{

    @XmlElement(required = true, nillable = true)
    protected String catgrType;

    /**
     * Gets the value of the catgrType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCatgrType() {
        return catgrType;
    }

    /**
     * Sets the value of the catgrType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCatgrType(String value) {
        this.catgrType = value;
    }

}
