
package com.ge.ef.ese.pocketmobile.europe.service;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ListKey.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ListKey">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="AccessoryCode"/>
 *     &lt;enumeration value="AccessoryTranslations"/>
 *     &lt;enumeration value="Branches"/>
 *     &lt;enumeration value="CategoryGroupTranslations"/>
 *     &lt;enumeration value="DamageMatrix"/>
 *     &lt;enumeration value="ParkingLocations"/>
 *     &lt;enumeration value="ServiceTypes"/>
 *     &lt;enumeration value="TyreBrandsTranslations"/>
 *     &lt;enumeration value="TyreTranslations"/>
 *     &lt;enumeration value="ServiceTypeTranslations"/>
 *     &lt;enumeration value="CustomerNameList"/>
 *     &lt;enumeration value="All"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ListKey")
@XmlEnum
public enum ListKey {

    @XmlEnumValue("AccessoryCode")
    ACCESSORY_CODE("AccessoryCode"),
    @XmlEnumValue("AccessoryTranslations")
    ACCESSORY_TRANSLATIONS("AccessoryTranslations"),
    @XmlEnumValue("Branches")
    BRANCHES("Branches"),
    @XmlEnumValue("CategoryGroupTranslations")
    CATEGORY_GROUP_TRANSLATIONS("CategoryGroupTranslations"),
    @XmlEnumValue("DamageMatrix")
    DAMAGE_MATRIX("DamageMatrix"),
    @XmlEnumValue("ParkingLocations")
    PARKING_LOCATIONS("ParkingLocations"),
    @XmlEnumValue("ServiceTypes")
    SERVICE_TYPES("ServiceTypes"),
    @XmlEnumValue("TyreBrandsTranslations")
    TYRE_BRANDS_TRANSLATIONS("TyreBrandsTranslations"),
    @XmlEnumValue("TyreTranslations")
    TYRE_TRANSLATIONS("TyreTranslations"),
    @XmlEnumValue("ServiceTypeTranslations")
    SERVICE_TYPE_TRANSLATIONS("ServiceTypeTranslations"),
    @XmlEnumValue("CustomerNameList")
    CUSTOMER_NAME_LIST("CustomerNameList"),
    @XmlEnumValue("All")
    ALL("All");
    private final String value;

    ListKey(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ListKey fromValue(String v) {
        for (ListKey c: ListKey.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
