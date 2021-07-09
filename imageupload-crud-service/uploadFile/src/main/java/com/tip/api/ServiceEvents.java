
package com.tip.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ServiceEvents complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceEvents">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="serviceTypeCd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="unitNr" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="serviceEnteredDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="serviceEventScheduledDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="serviceEventScheduledReadin" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="serviceEventStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="serviceEventRemark" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="serviceEventDocumentRef" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="serviceTypeRevisitAfter" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="serviceEventDoneDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="serviceEventDoneReading" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceEvents", propOrder = {
    "serviceTypeCd",
    "unitNr",
    "serviceEnteredDate",
    "serviceEventScheduledDate",
    "serviceEventScheduledReadin",
    "serviceEventStatus",
    "serviceEventRemark",
    "serviceEventDocumentRef",
    "serviceTypeRevisitAfter",
    "serviceEventDoneDate",
    "serviceEventDoneReading"
})
public class ServiceEvents {

    @XmlElement(required = true)
    protected String serviceTypeCd;
    protected int unitNr;
    @XmlElement(required = true)
    protected String serviceEnteredDate;
    @XmlElement(required = true, nillable = true)
    protected String serviceEventScheduledDate;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer serviceEventScheduledReadin;
    @XmlElement(required = true)
    protected String serviceEventStatus;
    @XmlElement(required = true, nillable = true)
    protected String serviceEventRemark;
    @XmlElement(required = true, nillable = true)
    protected String serviceEventDocumentRef;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer serviceTypeRevisitAfter;
    @XmlElement(required = true, nillable = true)
    protected String serviceEventDoneDate;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer serviceEventDoneReading;

    /**
     * Gets the value of the serviceTypeCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceTypeCd() {
        return serviceTypeCd;
    }

    /**
     * Sets the value of the serviceTypeCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceTypeCd(String value) {
        this.serviceTypeCd = value;
    }

    /**
     * Gets the value of the unitNr property.
     * 
     */
    public int getUnitNr() {
        return unitNr;
    }

    /**
     * Sets the value of the unitNr property.
     * 
     */
    public void setUnitNr(int value) {
        this.unitNr = value;
    }

    /**
     * Gets the value of the serviceEnteredDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceEnteredDate() {
        return serviceEnteredDate;
    }

    /**
     * Sets the value of the serviceEnteredDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceEnteredDate(String value) {
        this.serviceEnteredDate = value;
    }

    /**
     * Gets the value of the serviceEventScheduledDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceEventScheduledDate() {
        return serviceEventScheduledDate;
    }

    /**
     * Sets the value of the serviceEventScheduledDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceEventScheduledDate(String value) {
        this.serviceEventScheduledDate = value;
    }

    /**
     * Gets the value of the serviceEventScheduledReadin property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getServiceEventScheduledReadin() {
        return serviceEventScheduledReadin;
    }

    /**
     * Sets the value of the serviceEventScheduledReadin property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setServiceEventScheduledReadin(Integer value) {
        this.serviceEventScheduledReadin = value;
    }

    /**
     * Gets the value of the serviceEventStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceEventStatus() {
        return serviceEventStatus;
    }

    /**
     * Sets the value of the serviceEventStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceEventStatus(String value) {
        this.serviceEventStatus = value;
    }

    /**
     * Gets the value of the serviceEventRemark property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceEventRemark() {
        return serviceEventRemark;
    }

    /**
     * Sets the value of the serviceEventRemark property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceEventRemark(String value) {
        this.serviceEventRemark = value;
    }

    /**
     * Gets the value of the serviceEventDocumentRef property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceEventDocumentRef() {
        return serviceEventDocumentRef;
    }

    /**
     * Sets the value of the serviceEventDocumentRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceEventDocumentRef(String value) {
        this.serviceEventDocumentRef = value;
    }

    /**
     * Gets the value of the serviceTypeRevisitAfter property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getServiceTypeRevisitAfter() {
        return serviceTypeRevisitAfter;
    }

    /**
     * Sets the value of the serviceTypeRevisitAfter property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setServiceTypeRevisitAfter(Integer value) {
        this.serviceTypeRevisitAfter = value;
    }

    /**
     * Gets the value of the serviceEventDoneDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceEventDoneDate() {
        return serviceEventDoneDate;
    }

    /**
     * Sets the value of the serviceEventDoneDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceEventDoneDate(String value) {
        this.serviceEventDoneDate = value;
    }

    /**
     * Gets the value of the serviceEventDoneReading property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getServiceEventDoneReading() {
        return serviceEventDoneReading;
    }

    /**
     * Sets the value of the serviceEventDoneReading property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setServiceEventDoneReading(Integer value) {
        this.serviceEventDoneReading = value;
    }

}
