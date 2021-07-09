
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
 *         &lt;element name="SSO" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "sso"
})
@XmlRootElement(name = "searchBranchesRequest")
public class SearchBranchesRequest {

    @XmlElement(name = "SSO")
    protected int sso;

    /**
     * Gets the value of the sso property.
     * 
     */
    public int getSSO() {
        return sso;
    }

    /**
     * Sets the value of the sso property.
     * 
     */
    public void setSSO(int value) {
        this.sso = value;
    }

}
