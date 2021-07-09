
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
 *         &lt;element name="branchNr" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="authToken" type="{http://service.europe.pocketmobile.ese.ef.ge.com}AuthenticationToken"/>
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
    "branchNr",
    "authToken"
})
@XmlRootElement(name = "login")
public class Login {

    protected int branchNr;
    @XmlElement(required = true)
    protected AuthenticationToken authToken;

    /**
     * Gets the value of the branchNr property.
     * 
     */
    public int getBranchNr() {
        return branchNr;
    }

    /**
     * Sets the value of the branchNr property.
     * 
     */
    public void setBranchNr(int value) {
        this.branchNr = value;
    }

    /**
     * Gets the value of the authToken property.
     * 
     * @return
     *     possible object is
     *     {@link AuthenticationToken }
     *     
     */
    public AuthenticationToken getAuthToken() {
        return authToken;
    }

    /**
     * Sets the value of the authToken property.
     * 
     * @param value
     *     allowed object is
     *     {@link AuthenticationToken }
     *     
     */
    public void setAuthToken(AuthenticationToken value) {
        this.authToken = value;
    }

}
