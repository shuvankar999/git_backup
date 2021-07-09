
package com.ge.ef.ese.pocketmobile.europe.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SaveInterchange complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SaveInterchange">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="intchKey" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="rateNr" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="mstrlsNr" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="leasePeriodCd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="unitNr" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="customerNr" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="intchOutDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="intchInCompanyNr" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="intchInBranchNr" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="intchInDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="intchInKM" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="intchInKMSecond" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="intchInElectricHours" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="intchInDieselHours" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="intchInDriverName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="intchInDriverLicense" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="intchAdjKm" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="intchAdjElectricHours" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="intchAdjDieselHours" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="intchInDriverCompany" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="intchInParkingLocation" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fleetCare" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ssoId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="intchInDieselLitres" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="intchInDriverComments" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="timezoneOffset" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SaveInterchange", propOrder = {
    "intchKey",
    "rateNr",
    "mstrlsNr",
    "leasePeriodCd",
    "unitNr",
    "customerNr",
    "intchOutDate",
    "intchInCompanyNr",
    "intchInBranchNr",
    "intchInDate",
    "intchInKM",
    "intchInKMSecond",
    "intchInElectricHours",
    "intchInDieselHours",
    "intchInDriverName",
    "intchInDriverLicense",
    "intchAdjKm",
    "intchAdjElectricHours",
    "intchAdjDieselHours",
    "intchInDriverCompany",
    "intchInParkingLocation",
    "fleetCare",
    "ssoId",
    "intchInDieselLitres",
    "intchInDriverComments",
    "timezoneOffset"
})
public class SaveInterchange {

    protected long intchKey;
    protected int rateNr;
    protected int mstrlsNr;
    @XmlElement(required = true)
    protected String leasePeriodCd;
    protected int unitNr;
    protected int customerNr;
    @XmlElement(required = true)
    protected String intchOutDate;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer intchInCompanyNr;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer intchInBranchNr;
    @XmlElement(required = true)
    protected String intchInDate;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer intchInKM;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer intchInKMSecond;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer intchInElectricHours;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer intchInDieselHours;
    @XmlElement(required = true, nillable = true)
    protected String intchInDriverName;
    @XmlElement(required = true, nillable = true)
    protected String intchInDriverLicense;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer intchAdjKm;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer intchAdjElectricHours;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer intchAdjDieselHours;
    @XmlElement(required = true, nillable = true)
    protected String intchInDriverCompany;
    @XmlElement(required = true, nillable = true)
    protected String intchInParkingLocation;
    @XmlElement(required = true, nillable = true)
    protected String fleetCare;
    @XmlElement(required = true)
    protected String ssoId;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer intchInDieselLitres;
    @XmlElement(required = true, nillable = true)
    protected String intchInDriverComments;
    @XmlElement(required = true)
    protected String timezoneOffset;

    /**
     * Gets the value of the intchKey property.
     * 
     */
    public long getIntchKey() {
        return intchKey;
    }

    /**
     * Sets the value of the intchKey property.
     * 
     */
    public void setIntchKey(long value) {
        this.intchKey = value;
    }

    /**
     * Gets the value of the rateNr property.
     * 
     */
    public int getRateNr() {
        return rateNr;
    }

    /**
     * Sets the value of the rateNr property.
     * 
     */
    public void setRateNr(int value) {
        this.rateNr = value;
    }

    /**
     * Gets the value of the mstrlsNr property.
     * 
     */
    public int getMstrlsNr() {
        return mstrlsNr;
    }

    /**
     * Sets the value of the mstrlsNr property.
     * 
     */
    public void setMstrlsNr(int value) {
        this.mstrlsNr = value;
    }

    /**
     * Gets the value of the leasePeriodCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLeasePeriodCd() {
        return leasePeriodCd;
    }

    /**
     * Sets the value of the leasePeriodCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLeasePeriodCd(String value) {
        this.leasePeriodCd = value;
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
     * Gets the value of the customerNr property.
     * 
     */
    public int getCustomerNr() {
        return customerNr;
    }

    /**
     * Sets the value of the customerNr property.
     * 
     */
    public void setCustomerNr(int value) {
        this.customerNr = value;
    }

    /**
     * Gets the value of the intchOutDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIntchOutDate() {
        return intchOutDate;
    }

    /**
     * Sets the value of the intchOutDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIntchOutDate(String value) {
        this.intchOutDate = value;
    }

    /**
     * Gets the value of the intchInCompanyNr property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIntchInCompanyNr() {
        return intchInCompanyNr;
    }

    /**
     * Sets the value of the intchInCompanyNr property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIntchInCompanyNr(Integer value) {
        this.intchInCompanyNr = value;
    }

    /**
     * Gets the value of the intchInBranchNr property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIntchInBranchNr() {
        return intchInBranchNr;
    }

    /**
     * Sets the value of the intchInBranchNr property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIntchInBranchNr(Integer value) {
        this.intchInBranchNr = value;
    }

    /**
     * Gets the value of the intchInDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIntchInDate() {
        return intchInDate;
    }

    /**
     * Sets the value of the intchInDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIntchInDate(String value) {
        this.intchInDate = value;
    }

    /**
     * Gets the value of the intchInKM property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIntchInKM() {
        return intchInKM;
    }

    /**
     * Sets the value of the intchInKM property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIntchInKM(Integer value) {
        this.intchInKM = value;
    }

    /**
     * Gets the value of the intchInKMSecond property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIntchInKMSecond() {
        return intchInKMSecond;
    }

    /**
     * Sets the value of the intchInKMSecond property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIntchInKMSecond(Integer value) {
        this.intchInKMSecond = value;
    }

    /**
     * Gets the value of the intchInElectricHours property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIntchInElectricHours() {
        return intchInElectricHours;
    }

    /**
     * Sets the value of the intchInElectricHours property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIntchInElectricHours(Integer value) {
        this.intchInElectricHours = value;
    }

    /**
     * Gets the value of the intchInDieselHours property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIntchInDieselHours() {
        return intchInDieselHours;
    }

    /**
     * Sets the value of the intchInDieselHours property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIntchInDieselHours(Integer value) {
        this.intchInDieselHours = value;
    }

    /**
     * Gets the value of the intchInDriverName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIntchInDriverName() {
        return intchInDriverName;
    }

    /**
     * Sets the value of the intchInDriverName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIntchInDriverName(String value) {
        this.intchInDriverName = value;
    }

    /**
     * Gets the value of the intchInDriverLicense property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIntchInDriverLicense() {
        return intchInDriverLicense;
    }

    /**
     * Sets the value of the intchInDriverLicense property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIntchInDriverLicense(String value) {
        this.intchInDriverLicense = value;
    }

    /**
     * Gets the value of the intchAdjKm property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIntchAdjKm() {
        return intchAdjKm;
    }

    /**
     * Sets the value of the intchAdjKm property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIntchAdjKm(Integer value) {
        this.intchAdjKm = value;
    }

    /**
     * Gets the value of the intchAdjElectricHours property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIntchAdjElectricHours() {
        return intchAdjElectricHours;
    }

    /**
     * Sets the value of the intchAdjElectricHours property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIntchAdjElectricHours(Integer value) {
        this.intchAdjElectricHours = value;
    }

    /**
     * Gets the value of the intchAdjDieselHours property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIntchAdjDieselHours() {
        return intchAdjDieselHours;
    }

    /**
     * Sets the value of the intchAdjDieselHours property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIntchAdjDieselHours(Integer value) {
        this.intchAdjDieselHours = value;
    }

    /**
     * Gets the value of the intchInDriverCompany property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIntchInDriverCompany() {
        return intchInDriverCompany;
    }

    /**
     * Sets the value of the intchInDriverCompany property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIntchInDriverCompany(String value) {
        this.intchInDriverCompany = value;
    }

    /**
     * Gets the value of the intchInParkingLocation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIntchInParkingLocation() {
        return intchInParkingLocation;
    }

    /**
     * Sets the value of the intchInParkingLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIntchInParkingLocation(String value) {
        this.intchInParkingLocation = value;
    }

    /**
     * Gets the value of the fleetCare property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFleetCare() {
        return fleetCare;
    }

    /**
     * Sets the value of the fleetCare property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFleetCare(String value) {
        this.fleetCare = value;
    }

    /**
     * Gets the value of the ssoId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSsoId() {
        return ssoId;
    }

    /**
     * Sets the value of the ssoId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSsoId(String value) {
        this.ssoId = value;
    }

    /**
     * Gets the value of the intchInDieselLitres property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIntchInDieselLitres() {
        return intchInDieselLitres;
    }

    /**
     * Sets the value of the intchInDieselLitres property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIntchInDieselLitres(Integer value) {
        this.intchInDieselLitres = value;
    }

    /**
     * Gets the value of the intchInDriverComments property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIntchInDriverComments() {
        return intchInDriverComments;
    }

    /**
     * Sets the value of the intchInDriverComments property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIntchInDriverComments(String value) {
        this.intchInDriverComments = value;
    }

    /**
     * Gets the value of the timezoneOffset property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimezoneOffset() {
        return timezoneOffset;
    }

    /**
     * Sets the value of the timezoneOffset property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimezoneOffset(String value) {
        this.timezoneOffset = value;
    }

}
