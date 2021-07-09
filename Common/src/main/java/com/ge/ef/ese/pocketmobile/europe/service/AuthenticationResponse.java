
package com.ge.ef.ese.pocketmobile.europe.service;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AuthenticationResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AuthenticationResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.europe.pocketmobile.ese.ef.ge.com}BaseReponse">
 *       &lt;sequence>
 *         &lt;element name="authToken" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="authenticated" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="applAccess" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AuthenticationResponse", propOrder = {
    "authToken",
    "authenticated",
    "applAccess"
})
public class AuthenticationResponse
    extends BaseReponse
{

    @XmlElement(required = true, nillable = true)
    protected String authToken;
    protected boolean authenticated;
    @XmlElement(nillable = true)
    protected List<String> applAccess;

    /**
     * Gets the value of the authToken property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthToken() {
        return authToken;
    }

    /**
     * Sets the value of the authToken property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthToken(String value) {
        this.authToken = value;
    }

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

    /**
     * Gets the value of the applAccess property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the applAccess property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getApplAccess().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getApplAccess() {
        if (applAccess == null) {
            applAccess = new ArrayList<String>();
        }
        return this.applAccess;
    }

}
