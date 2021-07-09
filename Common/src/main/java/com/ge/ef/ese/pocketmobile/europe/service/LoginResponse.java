
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
 *         &lt;element name="loginReturn" type="{http://service.europe.pocketmobile.ese.ef.ge.com}AuthenticationResponse"/>
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
    "loginReturn"
})
@XmlRootElement(name = "loginResponse")
public class LoginResponse {

    @XmlElement(required = true)
    protected AuthenticationResponse loginReturn;

    /**
     * Gets the value of the loginReturn property.
     * 
     * @return
     *     possible object is
     *     {@link AuthenticationResponse }
     *     
     */
    public AuthenticationResponse getLoginReturn() {
        return loginReturn;
    }

    /**
     * Sets the value of the loginReturn property.
     * 
     * @param value
     *     allowed object is
     *     {@link AuthenticationResponse }
     *     
     */
    public void setLoginReturn(AuthenticationResponse value) {
        this.loginReturn = value;
    }

}
