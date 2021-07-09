
package com.ge.ef.ese.pocketmobile.europe.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FetchStaticListResults complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FetchStaticListResults">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.europe.pocketmobile.ese.ef.ge.com}BaseReponse">
 *       &lt;sequence>
 *         &lt;element name="accessoryCodes" type="{http://service.europe.pocketmobile.ese.ef.ge.com}ArrayOfAccessoryCode"/>
 *         &lt;element name="accessoryTranslations" type="{http://service.europe.pocketmobile.ese.ef.ge.com}ArrayOfTranslations"/>
 *         &lt;element name="branches" type="{http://service.europe.pocketmobile.ese.ef.ge.com}ArrayOfBranches"/>
 *         &lt;element name="categoryGroupTranslations" type="{http://service.europe.pocketmobile.ese.ef.ge.com}ArrayOfCategoryGroupTrans"/>
 *         &lt;element name="damageMatrix" type="{http://service.europe.pocketmobile.ese.ef.ge.com}ArrayOfDamageMatrix"/>
 *         &lt;element name="parkingLocations" type="{http://service.europe.pocketmobile.ese.ef.ge.com}ArrayOfParkingLocations"/>
 *         &lt;element name="serviceTypes" type="{http://service.europe.pocketmobile.ese.ef.ge.com}ArrayOfServiceTypes"/>
 *         &lt;element name="tyreBrandTranslations" type="{http://service.europe.pocketmobile.ese.ef.ge.com}ArrayOfTranslations"/>
 *         &lt;element name="tyreTranslations" type="{http://service.europe.pocketmobile.ese.ef.ge.com}ArrayOfTranslations"/>
 *         &lt;element name="serviceTypeTranslations" type="{http://service.europe.pocketmobile.ese.ef.ge.com}ArrayOfTranslations"/>
 *         &lt;element name="customerNameList" type="{http://service.europe.pocketmobile.ese.ef.ge.com}ArrayOfCustomerName"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FetchStaticListResults", propOrder = {
    "accessoryCodes",
    "accessoryTranslations",
    "branches",
    "categoryGroupTranslations",
    "damageMatrix",
    "parkingLocations",
    "serviceTypes",
    "tyreBrandTranslations",
    "tyreTranslations",
    "serviceTypeTranslations",
    "customerNameList"
})
public class FetchStaticListResults
    extends BaseReponse
{

    @XmlElement(required = true, nillable = true)
    protected ArrayOfAccessoryCode accessoryCodes;
    @XmlElement(required = true, nillable = true)
    protected ArrayOfTranslations accessoryTranslations;
    @XmlElement(required = true, nillable = true)
    protected ArrayOfBranches branches;
    @XmlElement(required = true, nillable = true)
    protected ArrayOfCategoryGroupTrans categoryGroupTranslations;
    @XmlElement(required = true, nillable = true)
    protected ArrayOfDamageMatrix damageMatrix;
    @XmlElement(required = true, nillable = true)
    protected ArrayOfParkingLocations parkingLocations;
    @XmlElement(required = true, nillable = true)
    protected ArrayOfServiceTypes serviceTypes;
    @XmlElement(required = true, nillable = true)
    protected ArrayOfTranslations tyreBrandTranslations;
    @XmlElement(required = true, nillable = true)
    protected ArrayOfTranslations tyreTranslations;
    @XmlElement(required = true, nillable = true)
    protected ArrayOfTranslations serviceTypeTranslations;
    @XmlElement(required = true, nillable = true)
    protected ArrayOfCustomerName customerNameList;

    /**
     * Gets the value of the accessoryCodes property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfAccessoryCode }
     *     
     */
    public ArrayOfAccessoryCode getAccessoryCodes() {
        return accessoryCodes;
    }

    /**
     * Sets the value of the accessoryCodes property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfAccessoryCode }
     *     
     */
    public void setAccessoryCodes(ArrayOfAccessoryCode value) {
        this.accessoryCodes = value;
    }

    /**
     * Gets the value of the accessoryTranslations property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfTranslations }
     *     
     */
    public ArrayOfTranslations getAccessoryTranslations() {
        return accessoryTranslations;
    }

    /**
     * Sets the value of the accessoryTranslations property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfTranslations }
     *     
     */
    public void setAccessoryTranslations(ArrayOfTranslations value) {
        this.accessoryTranslations = value;
    }

    /**
     * Gets the value of the branches property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfBranches }
     *     
     */
    public ArrayOfBranches getBranches() {
        return branches;
    }

    /**
     * Sets the value of the branches property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfBranches }
     *     
     */
    public void setBranches(ArrayOfBranches value) {
        this.branches = value;
    }

    /**
     * Gets the value of the categoryGroupTranslations property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfCategoryGroupTrans }
     *     
     */
    public ArrayOfCategoryGroupTrans getCategoryGroupTranslations() {
        return categoryGroupTranslations;
    }

    /**
     * Sets the value of the categoryGroupTranslations property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfCategoryGroupTrans }
     *     
     */
    public void setCategoryGroupTranslations(ArrayOfCategoryGroupTrans value) {
        this.categoryGroupTranslations = value;
    }

    /**
     * Gets the value of the damageMatrix property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfDamageMatrix }
     *     
     */
    public ArrayOfDamageMatrix getDamageMatrix() {
        return damageMatrix;
    }

    /**
     * Sets the value of the damageMatrix property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfDamageMatrix }
     *     
     */
    public void setDamageMatrix(ArrayOfDamageMatrix value) {
        this.damageMatrix = value;
    }

    /**
     * Gets the value of the parkingLocations property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfParkingLocations }
     *     
     */
    public ArrayOfParkingLocations getParkingLocations() {
        return parkingLocations;
    }

    /**
     * Sets the value of the parkingLocations property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfParkingLocations }
     *     
     */
    public void setParkingLocations(ArrayOfParkingLocations value) {
        this.parkingLocations = value;
    }

    /**
     * Gets the value of the serviceTypes property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfServiceTypes }
     *     
     */
    public ArrayOfServiceTypes getServiceTypes() {
        return serviceTypes;
    }

    /**
     * Sets the value of the serviceTypes property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfServiceTypes }
     *     
     */
    public void setServiceTypes(ArrayOfServiceTypes value) {
        this.serviceTypes = value;
    }

    /**
     * Gets the value of the tyreBrandTranslations property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfTranslations }
     *     
     */
    public ArrayOfTranslations getTyreBrandTranslations() {
        return tyreBrandTranslations;
    }

    /**
     * Sets the value of the tyreBrandTranslations property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfTranslations }
     *     
     */
    public void setTyreBrandTranslations(ArrayOfTranslations value) {
        this.tyreBrandTranslations = value;
    }

    /**
     * Gets the value of the tyreTranslations property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfTranslations }
     *     
     */
    public ArrayOfTranslations getTyreTranslations() {
        return tyreTranslations;
    }

    /**
     * Sets the value of the tyreTranslations property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfTranslations }
     *     
     */
    public void setTyreTranslations(ArrayOfTranslations value) {
        this.tyreTranslations = value;
    }

    /**
     * Gets the value of the serviceTypeTranslations property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfTranslations }
     *     
     */
    public ArrayOfTranslations getServiceTypeTranslations() {
        return serviceTypeTranslations;
    }

    /**
     * Sets the value of the serviceTypeTranslations property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfTranslations }
     *     
     */
    public void setServiceTypeTranslations(ArrayOfTranslations value) {
        this.serviceTypeTranslations = value;
    }

    /**
     * Gets the value of the customerNameList property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfCustomerName }
     *     
     */
    public ArrayOfCustomerName getCustomerNameList() {
        return customerNameList;
    }

    /**
     * Sets the value of the customerNameList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfCustomerName }
     *     
     */
    public void setCustomerNameList(ArrayOfCustomerName value) {
        this.customerNameList = value;
    }

}
