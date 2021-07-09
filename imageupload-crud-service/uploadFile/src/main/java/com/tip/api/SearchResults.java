
package com.tip.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SearchResults complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SearchResults">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.europe.pocketmobile.ese.ef.ge.com}BaseReponse">
 *       &lt;sequence>
 *         &lt;element name="accessories" type="{http://service.europe.pocketmobile.ese.ef.ge.com}ArrayOfAccessories"/>
 *         &lt;element name="customer" type="{http://service.europe.pocketmobile.ese.ef.ge.com}Customer"/>
 *         &lt;element name="intchTyre" type="{http://service.europe.pocketmobile.ese.ef.ge.com}ArrayOfIntchTyre"/>
 *         &lt;element name="interchange" type="{http://service.europe.pocketmobile.ese.ef.ge.com}Interchange"/>
 *         &lt;element name="reservations" type="{http://service.europe.pocketmobile.ese.ef.ge.com}ArrayOfReservations"/>
 *         &lt;element name="serviceEvents" type="{http://service.europe.pocketmobile.ese.ef.ge.com}ArrayOfServiceEvents"/>
 *         &lt;element name="unit" type="{http://service.europe.pocketmobile.ese.ef.ge.com}Unit"/>
 *         &lt;element name="contract" type="{http://service.europe.pocketmobile.ese.ef.ge.com}Contract"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SearchResults", propOrder = {
    "accessories",
    "customer",
    "intchTyre",
    "interchange",
    "reservations",
    "serviceEvents",
    "unit",
    "contract"
})
public class SearchResults
    extends BaseReponse
{

    @XmlElement(required = true, nillable = true)
    protected ArrayOfAccessories accessories;
    @XmlElement(required = true, nillable = true)
    protected Customer customer;
    @XmlElement(required = true, nillable = true)
    protected ArrayOfIntchTyre intchTyre;
    @XmlElement(required = true, nillable = true)
    protected Interchange interchange;
    @XmlElement(required = true, nillable = true)
    protected ArrayOfReservations reservations;
    @XmlElement(required = true, nillable = true)
    protected ArrayOfServiceEvents serviceEvents;
    @XmlElement(required = true)
    protected Unit unit;
    @XmlElement(required = true)
    protected Contract contract;

    /**
     * Gets the value of the accessories property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfAccessories }
     *     
     */
    public ArrayOfAccessories getAccessories() {
        return accessories;
    }

    /**
     * Sets the value of the accessories property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfAccessories }
     *     
     */
    public void setAccessories(ArrayOfAccessories value) {
        this.accessories = value;
    }

    /**
     * Gets the value of the customer property.
     * 
     * @return
     *     possible object is
     *     {@link Customer }
     *     
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Sets the value of the customer property.
     * 
     * @param value
     *     allowed object is
     *     {@link Customer }
     *     
     */
    public void setCustomer(Customer value) {
        this.customer = value;
    }

    /**
     * Gets the value of the intchTyre property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfIntchTyre }
     *     
     */
    public ArrayOfIntchTyre getIntchTyre() {
        return intchTyre;
    }

    /**
     * Sets the value of the intchTyre property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfIntchTyre }
     *     
     */
    public void setIntchTyre(ArrayOfIntchTyre value) {
        this.intchTyre = value;
    }

    /**
     * Gets the value of the interchange property.
     * 
     * @return
     *     possible object is
     *     {@link Interchange }
     *     
     */
    public Interchange getInterchange() {
        return interchange;
    }

    /**
     * Sets the value of the interchange property.
     * 
     * @param value
     *     allowed object is
     *     {@link Interchange }
     *     
     */
    public void setInterchange(Interchange value) {
        this.interchange = value;
    }

    /**
     * Gets the value of the reservations property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfReservations }
     *     
     */
    public ArrayOfReservations getReservations() {
        return reservations;
    }

    /**
     * Sets the value of the reservations property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfReservations }
     *     
     */
    public void setReservations(ArrayOfReservations value) {
        this.reservations = value;
    }

    /**
     * Gets the value of the serviceEvents property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfServiceEvents }
     *     
     */
    public ArrayOfServiceEvents getServiceEvents() {
        return serviceEvents;
    }

    /**
     * Sets the value of the serviceEvents property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfServiceEvents }
     *     
     */
    public void setServiceEvents(ArrayOfServiceEvents value) {
        this.serviceEvents = value;
    }

    /**
     * Gets the value of the unit property.
     * 
     * @return
     *     possible object is
     *     {@link Unit }
     *     
     */
    public Unit getUnit() {
        return unit;
    }

    /**
     * Sets the value of the unit property.
     * 
     * @param value
     *     allowed object is
     *     {@link Unit }
     *     
     */
    public void setUnit(Unit value) {
        this.unit = value;
    }

    /**
     * Gets the value of the contract property.
     * 
     * @return
     *     possible object is
     *     {@link Contract }
     *     
     */
    public Contract getContract() {
        return contract;
    }

    /**
     * Sets the value of the contract property.
     * 
     * @param value
     *     allowed object is
     *     {@link Contract }
     *     
     */
    public void setContract(Contract value) {
        this.contract = value;
    }

}
