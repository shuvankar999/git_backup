
package com.ge.ef.ese.pocketmobile.europe.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AuthenticationTestResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AuthenticationTestResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.europe.pocketmobile.ese.ef.ge.com}BaseReponse">
 *       &lt;sequence>
 *         &lt;element name="authenticated" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AuthenticationTestResponse", propOrder = {
    "authenticated"
})
public class AuthenticationTestResponse
    extends BaseReponse
{

    protected boolean authenticated;

    /**
     * Gets the value of the authenticated property.
     * 
     */
    public boolean isAuthenticated() {
        return authenticated;
    }

    /**
     * Sets the value of the authenticated property.
     * 
     */
    public void setAuthenticated(boolean value) {
        this.authenticated = value;
    }

}
