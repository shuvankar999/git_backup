
package com.tip.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CheckOutSaveInterchange complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CheckOutSaveInterchange">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="unitNr" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="customerNr" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="companyNr" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="branchNr" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="intchOneWayBranchNr" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="intchOneWayCompanyNr" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="intchOutDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="intchOutKM" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="intchOutKMSecond" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="intchOutElectricHours" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="intchOutDieselHours" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="intchOutDownloadTime" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="intchOutDriverName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="intchOutDriverLicense" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="intchOutDriverCompany" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="intchOutParkingLocation" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ssoId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="intchOutDieselLitres" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="intchOutDriverComments" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "CheckOutSaveInterchange", propOrder = {
    "unitNr",
    "customerNr",
    "companyNr",
    "branchNr",
    "intchOneWayBranchNr",
    "intchOneWayCompanyNr",
    "intchOutDate",
    "intchOutKM",
    "intchOutKMSecond",
    "intchOutElectricHours",
    "intchOutDieselHours",
    "intchOutDownloadTime",
    "intchOutDriverName",
    "intchOutDriverLicense",
    "intchOutDriverCompany",
    "intchOutParkingLocation",
    "ssoId",
    "intchOutDieselLitres",
    "intchOutDriverComments",
    "timezoneOffset"
})
public class CheckOutSaveInterchange {

    protected int unitNr;
    protected int customerNr;
    protected int companyNr;
    protected int branchNr;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer intchOneWayBranchNr;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer intchOneWayCompanyNr;
    @XmlElement(required = true, nillable = true)
    protected String intchOutDate;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer intchOutKM;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer intchOutKMSecond;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer intchOutElectricHours;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer intchOutDieselHours;
    @XmlElement(required = true, nillable = true)
    protected String intchOutDownloadTime;
    @XmlElement(required = true, nillable = true)
    protected String intchOutDriverName;
    @XmlElement(required = true, nillable = true)
    protected String intchOutDriverLicense;
    @XmlElement(required = true, nillable = true)
    protected String intchOutDriverCompany;
    @XmlElement(required = true, nillable = true)
    protected String intchOutParkingLocation;
    @XmlElement(required = true)
    protected String ssoId;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer intchOutDieselLitres;
    @XmlElement(required = true, nillable = true)
    protected String intchOutDriverComments;
    @XmlElement(required = true)
    protected String timezoneOffset;

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
     * Gets the value of the companyNr property.
     * 
     */
    public int getCompanyNr() {
        return companyNr;
    }

    /**
     * Sets the value of the companyNr property.
     * 
     */
    public void setCompanyNr(int value) {
        this.companyNr = value;
    }

    /**
     * Gets the value of the branchNr property.
     * 
     */
    public int getBranchNr() {
        return branchNr;
    }

    /**
     * Sets the value of the branchNr property.
     * 
     */
    public void setBranchNr(int value) {
        this.branchNr = value;
    }

    /**
     * Gets the value of the intchOneWayBranchNr property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIntchOneWayBranchNr() {
        return intchOneWayBranchNr;
    }

    /**
     * Sets the value of the intchOneWayBranchNr property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIntchOneWayBranchNr(Integer value) {
        this.intchOneWayBranchNr = value;
    }

    /**
     * Gets the value of the intchOneWayCompanyNr property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIntchOneWayCompanyNr() {
        return intchOneWayCompanyNr;
    }

    /**
     * Sets the value of the intchOneWayCompanyNr property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIntchOneWayCompanyNr(Integer value) {
        this.intchOneWayCompanyNr = value;
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
     * Gets the value of the intchOutKM property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIntchOutKM() {
        return intchOutKM;
    }

    /**
     * Sets the value of the intchOutKM property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIntchOutKM(Integer value) {
        this.intchOutKM = value;
    }

    /**
     * Gets the value of the intchOutKMSecond property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIntchOutKMSecond() {
        return intchOutKMSecond;
    }

    /**
     * Sets the value of the intchOutKMSecond property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIntchOutKMSecond(Integer value) {
        this.intchOutKMSecond = value;
    }

    /**
     * Gets the value of the intchOutElectricHours property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIntchOutElectricHours() {
        return intchOutElectricHours;
    }

    /**
     * Sets the value of the intchOutElectricHours property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIntchOutElectricHours(Integer value) {
        this.intchOutElectricHours = value;
    }

    /**
     * Gets the value of the intchOutDieselHours property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIntchOutDieselHours() {
        return intchOutDieselHours;
    }

    /**
     * Sets the value of the intchOutDieselHours property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIntchOutDieselHours(Integer value) {
        this.intchOutDieselHours = value;
    }

    /**
     * Gets the value of the intchOutDownloadTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIntchOutDownloadTime() {
        return intchOutDownloadTime;
    }

    /**
     * Sets the value of the intchOutDownloadTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIntchOutDownloadTime(String value) {
        this.intchOutDownloadTime = value;
    }

    /**
     * Gets the value of the intchOutDriverName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIntchOutDriverName() {
        return intchOutDriverName;
    }

    /**
     * Sets the value of the intchOutDriverName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIntchOutDriverName(String value) {
        this.intchOutDriverName = value;
    }

    /**
     * Gets the value of the intchOutDriverLicense property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIntchOutDriverLicense() {
        return intchOutDriverLicense;
    }

    /**
     * Sets the value of the intchOutDriverLicense property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIntchOutDriverLicense(String value) {
        this.intchOutDriverLicense = value;
    }

    /**
     * Gets the value of the intchOutDriverCompany property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIntchOutDriverCompany() {
        return intchOutDriverCompany;
    }

    /**
     * Sets the value of the intchOutDriverCompany property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIntchOutDriverCompany(String value) {
        this.intchOutDriverCompany = value;
    }

    /**
     * Gets the value of the intchOutParkingLocation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIntchOutParkingLocation() {
        return intchOutParkingLocation;
    }

    /**
     * Sets the value of the intchOutParkingLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIntchOutParkingLocation(String value) {
        this.intchOutParkingLocation = value;
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
     * Gets the value of the intchOutDieselLitres property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIntchOutDieselLitres() {
        return intchOutDieselLitres;
    }

    /**
     * Sets the value of the intchOutDieselLitres property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIntchOutDieselLitres(Integer value) {
        this.intchOutDieselLitres = value;
    }

    /**
     * Gets the value of the intchOutDriverComments property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIntchOutDriverComments() {
        return intchOutDriverComments;
    }

    /**
     * Sets the value of the intchOutDriverComments property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIntchOutDriverComments(String value) {
        this.intchOutDriverComments = value;
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
