
package com.tip.api;

import javax.xml.bind.annotation.*;


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
 *         &lt;element name="fetchReturn" type="{http://service.europe.pocketmobile.ese.ef.ge.com}FetchResults"/>
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
    "fetchReturn"
})
@XmlRootElement(name = "fetchResponse")
public class FetchResponse {

    @XmlElement(required = true)
    protected FetchResults fetchReturn;

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
