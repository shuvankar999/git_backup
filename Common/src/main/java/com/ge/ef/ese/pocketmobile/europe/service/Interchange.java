
package com.ge.ef.ese.pocketmobile.europe.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Interchange complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Interchange">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="intchKey" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="companyNr" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="branchNr" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="intchNr" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="intchType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="intchOutParkingLocation" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="mstrlsNr" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="rateNr" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="customerNr" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="unitNr" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
 *         &lt;element name="intchInCompanyNr" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="intchInBranchNr" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="intchInDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="intchInKM" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="intchInKMSecond" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="intchInElectricHours" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="intchInDieselHours" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="intchInDownloadTime" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="intchInDriverName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="intchInDriverLicense" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="intchAdjKM" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="intchAdjElectricHours" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="intchAdjDieselHours" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="intchRemarks" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="intchOutDriverCompany" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="intchInDriverCompany" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ratesMaintrspRungearInd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ratesMaintrspTyresInd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ratesMaintrspReeferInd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ratesMaintCertfctRenwlInd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ratesMaintBrakeCapInd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ratesMaintTyreCapInd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ratesMaintTestsInd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ratesInsrncPlanCd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ratesInsrncMartmCovrg" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fleetCare" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tyreCare" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="mdp" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="intchFreeDays" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="intchTermCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="leasePeriodCd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="intchExpectedReturnDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="roadTaxIndicator" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="intchInParkingLocation" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="intchVoidTime" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="intchOutDieselLitres" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Interchange", propOrder = {
    "intchKey",
    "companyNr",
    "branchNr",
    "intchNr",
    "intchType",
    "intchOutParkingLocation",
    "mstrlsNr",
    "rateNr",
    "customerNr",
    "unitNr",
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
    "intchInCompanyNr",
    "intchInBranchNr",
    "intchInDate",
    "intchInKM",
    "intchInKMSecond",
    "intchInElectricHours",
    "intchInDieselHours",
    "intchInDownloadTime",
    "intchInDriverName",
    "intchInDriverLicense",
    "intchAdjKM",
    "intchAdjElectricHours",
    "intchAdjDieselHours",
    "intchRemarks",
    "intchOutDriverCompany",
    "intchInDriverCompany",
    "ratesMaintrspRungearInd",
    "ratesMaintrspTyresInd",
    "ratesMaintrspReeferInd",
    "ratesMaintCertfctRenwlInd",
    "ratesMaintBrakeCapInd",
    "ratesMaintTyreCapInd",
    "ratesMaintTestsInd",
    "ratesInsrncPlanCd",
    "ratesInsrncMartmCovrg",
    "fleetCare",
    "tyreCare",
    "mdp",
    "intchFreeDays",
    "intchTermCount",
    "leasePeriodCd",
    "intchExpectedReturnDate",
    "roadTaxIndicator",
    "intchInParkingLocation",
    "intchVoidTime",
    "intchOutDieselLitres"
})
public class Interchange {

    protected long intchKey;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer companyNr;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer branchNr;
    protected int intchNr;
    @XmlElement(required = true)
    protected String intchType;
    @XmlElement(required = true, nillable = true)
    protected String intchOutParkingLocation;
    protected int mstrlsNr;
    protected int rateNr;
    protected int customerNr;
    protected int unitNr;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer intchOneWayBranchNr;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer intchOneWayCompanyNr;
    @XmlElement(required = true)
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
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer intchInCompanyNr;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer intchInBranchNr;
    @XmlElement(required = true, nillable = true)
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
    protected String intchInDownloadTime;
    @XmlElement(required = true, nillable = true)
    protected String intchInDriverName;
    @XmlElement(required = true, nillable = true)
    protected String intchInDriverLicense;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer intchAdjKM;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer intchAdjElectricHours;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer intchAdjDieselHours;
    @XmlElement(required = true, nillable = true)
    protected String intchRemarks;
    @XmlElement(required = true, nillable = true)
    protected String intchOutDriverCompany;
    @XmlElement(required = true, nillable = true)
    protected String intchInDriverCompany;
    @XmlElement(required = true, nillable = true)
    protected String ratesMaintrspRungearInd;
    @XmlElement(required = true, nillable = true)
    protected String ratesMaintrspTyresInd;
    @XmlElement(required = true, nillable = true)
    protected String ratesMaintrspReeferInd;
    @XmlElement(required = true, nillable = true)
    protected String ratesMaintCertfctRenwlInd;
    @XmlElement(required = true, nillable = true)
    protected String ratesMaintBrakeCapInd;
    @XmlElement(required = true, nillable = true)
    protected String ratesMaintTyreCapInd;
    @XmlElement(required = true, nillable = true)
    protected String ratesMaintTestsInd;
    @XmlElement(required = true, nillable = true)
    protected String ratesInsrncPlanCd;
    @XmlElement(required = true, nillable = true)
    protected String ratesInsrncMartmCovrg;
    @XmlElement(required = true, nillable = true)
    protected String fleetCare;
    @XmlElement(required = true, nillable = true)
    protected String tyreCare;
    @XmlElement(required = true, nillable = true)
    protected String mdp;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer intchFreeDays;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer intchTermCount;
    @XmlElement(required = true, nillable = true)
    protected String leasePeriodCd;
    @XmlElement(required = true, nillable = true)
    protected String intchExpectedReturnDate;
    @XmlElement(required = true, nillable = true)
    protected String roadTaxIndicator;
    @XmlElement(required = true, nillable = true)
    protected String intchInParkingLocation;
    @XmlElement(required = true, nillable = true)
    protected String intchVoidTime;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer intchOutDieselLitres;

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
     * Gets the value of the companyNr property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCompanyNr() {
        return companyNr;
    }

    /**
     * Sets the value of the companyNr property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCompanyNr(Integer value) {
        this.companyNr = value;
    }

    /**
     * Gets the value of the branchNr property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getBranchNr() {
        return branchNr;
    }

    /**
     * Sets the value of the branchNr property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setBranchNr(Integer value) {
        this.branchNr = value;
    }

    /**
     * Gets the value of the intchNr property.
     * 
     */
    public int getIntchNr() {
        return intchNr;
    }

    /**
     * Sets the value of the intchNr property.
     * 
     */
    public void setIntchNr(int value) {
        this.intchNr = value;
    }

    /**
     * Gets the value of the intchType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIntchType() {
        return intchType;
    }

    /**
     * Sets the value of the intchType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIntchType(String value) {
        this.intchType = value;
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
     * Gets the value of the intchInDownloadTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIntchInDownloadTime() {
        return intchInDownloadTime;
    }

    /**
     * Sets the value of the intchInDownloadTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIntchInDownloadTime(String value) {
        this.intchInDownloadTime = value;
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
     * Gets the value of the intchAdjKM property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIntchAdjKM() {
        return intchAdjKM;
    }

    /**
     * Sets the value of the intchAdjKM property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIntchAdjKM(Integer value) {
        this.intchAdjKM = value;
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
     * Gets the value of the intchRemarks property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIntchRemarks() {
        return intchRemarks;
    }

    /**
     * Sets the value of the intchRemarks property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIntchRemarks(String value) {
        this.intchRemarks = value;
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
     * Gets the value of the ratesMaintrspRungearInd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRatesMaintrspRungearInd() {
        return ratesMaintrspRungearInd;
    }

    /**
     * Sets the value of the ratesMaintrspRungearInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRatesMaintrspRungearInd(String value) {
        this.ratesMaintrspRungearInd = value;
    }

    /**
     * Gets the value of the ratesMaintrspTyresInd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRatesMaintrspTyresInd() {
        return ratesMaintrspTyresInd;
    }

    /**
     * Sets the value of the ratesMaintrspTyresInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRatesMaintrspTyresInd(String value) {
        this.ratesMaintrspTyresInd = value;
    }

    /**
     * Gets the value of the ratesMaintrspReeferInd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRatesMaintrspReeferInd() {
        return ratesMaintrspReeferInd;
    }

    /**
     * Sets the value of the ratesMaintrspReeferInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRatesMaintrspReeferInd(String value) {
        this.ratesMaintrspReeferInd = value;
    }

    /**
     * Gets the value of the ratesMaintCertfctRenwlInd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRatesMaintCertfctRenwlInd() {
        return ratesMaintCertfctRenwlInd;
    }

    /**
     * Sets the value of the ratesMaintCertfctRenwlInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRatesMaintCertfctRenwlInd(String value) {
        this.ratesMaintCertfctRenwlInd = value;
    }

    /**
     * Gets the value of the ratesMaintBrakeCapInd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRatesMaintBrakeCapInd() {
        return ratesMaintBrakeCapInd;
    }

    /**
     * Sets the value of the ratesMaintBrakeCapInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRatesMaintBrakeCapInd(String value) {
        this.ratesMaintBrakeCapInd = value;
    }

    /**
     * Gets the value of the ratesMaintTyreCapInd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRatesMaintTyreCapInd() {
        return ratesMaintTyreCapInd;
    }

    /**
     * Sets the value of the ratesMaintTyreCapInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRatesMaintTyreCapInd(String value) {
        this.ratesMaintTyreCapInd = value;
    }

    /**
     * Gets the value of the ratesMaintTestsInd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRatesMaintTestsInd() {
        return ratesMaintTestsInd;
    }

    /**
     * Sets the value of the ratesMaintTestsInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRatesMaintTestsInd(String value) {
        this.ratesMaintTestsInd = value;
    }

    /**
     * Gets the value of the ratesInsrncPlanCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRatesInsrncPlanCd() {
        return ratesInsrncPlanCd;
    }

    /**
     * Sets the value of the ratesInsrncPlanCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRatesInsrncPlanCd(String value) {
        this.ratesInsrncPlanCd = value;
    }

    /**
     * Gets the value of the ratesInsrncMartmCovrg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRatesInsrncMartmCovrg() {
        return ratesInsrncMartmCovrg;
    }

    /**
     * Sets the value of the ratesInsrncMartmCovrg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRatesInsrncMartmCovrg(String value) {
        this.ratesInsrncMartmCovrg = value;
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
     * Gets the value of the tyreCare property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTyreCare() {
        return tyreCare;
    }

    /**
     * Sets the value of the tyreCare property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTyreCare(String value) {
        this.tyreCare = value;
    }

    /**
     * Gets the value of the mdp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMdp() {
        return mdp;
    }

    /**
     * Sets the value of the mdp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMdp(String value) {
        this.mdp = value;
    }

    /**
     * Gets the value of the intchFreeDays property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIntchFreeDays() {
        return intchFreeDays;
    }

    /**
     * Sets the value of the intchFreeDays property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIntchFreeDays(Integer value) {
        this.intchFreeDays = value;
    }

    /**
     * Gets the value of the intchTermCount property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIntchTermCount() {
        return intchTermCount;
    }

    /**
     * Sets the value of the intchTermCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIntchTermCount(Integer value) {
        this.intchTermCount = value;
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
     * Gets the value of the intchExpectedReturnDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIntchExpectedReturnDate() {
        return intchExpectedReturnDate;
    }

    /**
     * Sets the value of the intchExpectedReturnDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIntchExpectedReturnDate(String value) {
        this.intchExpectedReturnDate = value;
    }

    /**
     * Gets the value of the roadTaxIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoadTaxIndicator() {
        return roadTaxIndicator;
    }

    /**
     * Sets the value of the roadTaxIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoadTaxIndicator(String value) {
        this.roadTaxIndicator = value;
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
     * Gets the value of the intchVoidTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIntchVoidTime() {
        return intchVoidTime;
    }

    /**
     * Sets the value of the intchVoidTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIntchVoidTime(String value) {
        this.intchVoidTime = value;
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

}
