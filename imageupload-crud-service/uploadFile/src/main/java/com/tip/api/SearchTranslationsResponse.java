
package com.tip.api;

import com.tip.api.ArrayOfTranslationsList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *       &lt;choice>
 *         &lt;element name="getTranslationsReturn" type="{http://service.europe.pocketmobile.ese.ef.ge.com}ArrayOfTranslationsList"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "getTranslationsReturn"
})
@XmlRootElement(name = "searchTranslationsResponse")
public class SearchTranslationsResponse {

    protected ArrayOfTranslationsList getTranslationsReturn;

    /**
     * Gets the value of the getTranslationsReturn property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfTranslationsList }
     *     
     */
    public ArrayOfTranslationsList getGetTranslationsReturn() {
        return getTranslationsReturn;
    }

    /**
     * Sets the value of the getTranslationsReturn property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfTranslationsList }
     *     
     */
    public void setGetTranslationsReturn(ArrayOfTranslationsList value) {
        this.getTranslationsReturn = value;
    }

}
