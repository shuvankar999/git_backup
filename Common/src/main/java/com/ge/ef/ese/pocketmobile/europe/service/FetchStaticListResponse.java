
package com.ge.ef.ese.pocketmobile.europe.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 *       &lt;sequence>
 *         &lt;element name="fetchStaticListReturn" type="{http://service.europe.pocketmobile.ese.ef.ge.com}FetchStaticListResults"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "fetchStaticListReturn"
})
@XmlRootElement(name = "fetchStaticListResponse")
public class FetchStaticListResponse {

    @XmlElement(required = true)
    protected FetchStaticListResults fetchStaticListReturn;

    /**
     * Gets the value of the fetchStaticListReturn property.
     * 
     * @return
     *     possible object is
     *     {@link FetchStaticListResults }
     *     
     */
    public FetchStaticListResults getFetchStaticListReturn() {
        return fetchStaticListReturn;
    }

    /**
     * Sets the value of the fetchStaticListReturn property.
     * 
     * @param value
     *     allowed object is
     *     {@link FetchStaticListResults }
     *     
     */
    public void setFetchStaticListReturn(FetchStaticListResults value) {
        this.fetchStaticListReturn = value;
    }

}
