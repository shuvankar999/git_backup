//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.06.15 at 01:57:14 PM IST 
//


package com.tip.fetchwohistory.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.math.BigDecimal;


/**
 * <p>Java class for WorkOrderRequest complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="WorkOrderRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Work_Pack_Nr" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Wp_Opened_Date" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Group_Cd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Unit_Nr" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WorkOrderRequest", propOrder = {
        "workPackNr",
        "wpOpenedDate",
        "groupCd",
        "unitNr"
})
public class WorkOrderRequest {

    @XmlElement(name = "Work_Pack_Nr")
    protected BigDecimal workPackNr;
    @XmlElement(name = "Wp_Opened_Date")
    protected String wpOpenedDate;
    @XmlElement(name = "Group_Cd")
    protected String groupCd;
    @XmlElement(name = "Unit_Nr")
    protected Integer unitNr;

    /**
     * Gets the value of the workPackNr property.
     *
     * @return possible object is
     * {@link BigDecimal }
     */
    public BigDecimal getWorkPackNr() {
        return workPackNr;
    }

    /**
     * Sets the value of the workPackNr property.
     *
     * @param value allowed object is
     *              {@link BigDecimal }
     */
    public void setWorkPackNr(BigDecimal value) {
        this.workPackNr = value;
    }

    /**
     * Gets the value of the wpOpenedDate property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getWpOpenedDate() {
        return wpOpenedDate;
    }

    /**
     * Sets the value of the wpOpenedDate property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setWpOpenedDate(String value) {
        this.wpOpenedDate = value;
    }

    /**
     * Gets the value of the groupCd property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getGroupCd() {
        return groupCd;
    }

    /**
     * Sets the value of the groupCd property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setGroupCd(String value) {
        this.groupCd = value;
    }

    /**
     * Gets the value of the unitNr property.
     *
     * @return possible object is
     * {@link Integer }
     */
    public Integer getUnitNr() {
        return unitNr;
    }

    /**
     * Sets the value of the unitNr property.
     *
     * @param value allowed object is
     *              {@link Integer }
     */
    public void setUnitNr(Integer value) {
        this.unitNr = value;
    }

}
