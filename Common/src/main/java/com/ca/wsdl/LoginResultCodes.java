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
 * <p>Java class for loginResultCodes.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="loginResultCodes"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="USER_CHALLENGE"/&gt;
 *     &lt;enumeration value="LOGIN_FAILED"/&gt;
 *     &lt;enumeration value="LOGIN_SUCCESS"/&gt;
 *     &lt;enumeration value="RESOURCE_NOT_PROTECTED"/&gt;
 *     &lt;enumeration value="SESSION_VALID"/&gt;
 *     &lt;enumeration value="SESSION_INVALID"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "loginResultCodes")
@XmlEnum
public enum LoginResultCodes {

    USER_CHALLENGE,
    LOGIN_FAILED,
    LOGIN_SUCCESS,
    RESOURCE_NOT_PROTECTED,
    SESSION_VALID,
    SESSION_INVALID;

    public String value() {
        return name();
    }

    public static LoginResultCodes fromValue(String v) {
        return valueOf(v);
    }

}