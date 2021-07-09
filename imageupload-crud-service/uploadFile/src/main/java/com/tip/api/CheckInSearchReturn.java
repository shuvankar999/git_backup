
package com.tip.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CheckInSearchReturn complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CheckInSearchReturn">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="searchReturn" type="{http://service.europe.pocketmobile.ese.ef.ge.com}SearchResults"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CheckInSearchReturn", propOrder = {
    "searchReturn"
})
public class CheckInSearchReturn {

    @XmlElement(required = true)
    protected SearchResults searchReturn;

    /**
     * Gets the value of the searchReturn property.
     * 
     * @return
     *     possible object is
     *     {@link SearchResults }
     *     
     */
    public SearchResults getSearchReturn() {
        return searchReturn;
    }

    /**
     * Sets the value of the searchReturn property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchResults }
     *     
     */
    public void setSearchReturn(SearchResults value) {
        this.searchReturn = value;
    }

}
