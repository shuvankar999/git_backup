//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.05.05 at 11:32:29 PM BST 
//


package com.ca.wsdl;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for authorizationResultCodes.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="authorizationResultCodes"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="AUTHORIZED"/&gt;
 *     &lt;enumeration value="NOTAUTHORIZED"/&gt;
 *     &lt;enumeration value="NOT_PROTECTED"/&gt;
 *     &lt;enumeration value="NOT_CONNECTED"/&gt;
 *     &lt;enumeration value="SERVER_ERROR"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "authorizationResultCodes")
@XmlEnum
public enum AuthorizationResultCodes {

    AUTHORIZED,
    NOTAUTHORIZED,
    NOT_PROTECTED,
    NOT_CONNECTED,
    SERVER_ERROR;

    public String value() {
        return name();
    }

    public static AuthorizationResultCodes fromValue(String v) {
        return valueOf(v);
    }

}
