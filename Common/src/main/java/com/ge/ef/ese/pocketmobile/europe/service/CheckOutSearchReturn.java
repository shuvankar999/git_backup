
package com.ge.ef.ese.pocketmobile.europe.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CheckOutSearchReturn complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CheckOutSearchReturn">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="searchReturn" type="{http://service.europe.pocketmobile.ese.ef.ge.com}CheckOutSearchResults"/>
 *         &lt;element name="fetchReturn" type="{http://service.europe.pocketmobile.ese.ef.ge.com}FetchResults"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CheckOutSearchReturn", propOrder = {
    "searchReturn",
    "fetchReturn"
})
public class CheckOutSearchReturn {

    protected CheckOutSearchResults searchReturn;
    protected FetchResults fetchReturn;

    /**
     * Gets the value of the searchReturn property.
     * 
     * @return
     *     possible object is
     *     {@link CheckOutSearchResults }
     *     
     */
    public CheckOutSearchResults getSearchReturn() {
        return searchReturn;
    }

    /**
     * Sets the value of the searchReturn property.
     * 
     * @param value
     *     allowed object is
     *     {@link CheckOutSearchResults }
     *     
     */
    public void setSearchReturn(CheckOutSearchResults value) {
        this.searchReturn = value;
    }

    /**
     * Gets the value of the fetchReturn property.
     * 
     * @return
     *     possible object is
     *     {@link FetchResults }
     *     
     */
    public FetchResults getFetchReturn() {
        return fetchReturn;
    }

    /**
     * Sets the value of the fetchReturn property.
     * 
     * @param value
     *     allowed object is
     *     {@link FetchResults }
     *     
     */
    public void setFetchReturn(FetchResults value) {
        this.fetchReturn = value;
    }

}
