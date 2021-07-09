
package com.ge.ef.ese.pocketmobile.europe.service;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ServiceStatus.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ServiceStatus">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="SUCCESS"/>
 *     &lt;enumeration value="GENERAL_ERROR"/>
 *     &lt;enumeration value="DATABASE_ERROR"/>
 *     &lt;enumeration value="INVALID_REQUEST"/>
 *     &lt;enumeration value="INVALID_SESSION_ID"/>
 *     &lt;enumeration value="IN_PROGRESS"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ServiceStatus")
@XmlEnum
public enum ServiceStatus {

    SUCCESS,
    GENERAL_ERROR,
    DATABASE_ERROR,
    INVALID_REQUEST,
    INVALID_SESSION_ID,
    IN_PROGRESS;

    public String value() {
        return name();
    }

    public static ServiceStatus fromValue(String v) {
        return valueOf(v);
    }

}
