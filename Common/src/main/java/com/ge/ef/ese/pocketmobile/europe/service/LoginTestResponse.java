
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
 *         &lt;element name="loginTestReturn" type="{http://service.europe.pocketmobile.ese.ef.ge.com}AuthenticationTestResponse"/>
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
    "loginTestReturn"
})
@XmlRootElement(name = "loginTestResponse")
public class LoginTestResponse {

    @XmlElement(required = true)
    protected AuthenticationTestResponse loginTestReturn;

    /**
     * Gets the value of the loginTestReturn property.
     * 
     * @return
     *     possible object is
     *     {@link AuthenticationTestResponse }
     *     
     */
    public AuthenticationTestResponse getLoginTestReturn() {
        return loginTestReturn;
    }

    /**
     * Sets the value of the loginTestReturn property.
     * 
     * @param value
     *     allowed object is
     *     {@link AuthenticationTestResponse }
     *     
     */
    public void setLoginTestReturn(AuthenticationTestResponse value) {
        this.loginTestReturn = value;
    }

}
