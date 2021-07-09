
package com.ge.ef.ese.pocketmobile.europe.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Unit complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Unit">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="handheldActivated" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="unitNr" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="unitPhysicalCompanyNr" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="unitPhysicalBranchNr" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="unitOperationalStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="unitCatgrpCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="unitSerialNr" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="unitManufacturer" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="unitModelYear" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="unitLicenceNr" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="unitLicenceCountryCd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="unitCd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="unitCustomerRefrnc" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="unitInOutStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="unitStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="unitStatusComment" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="unitLastParkLocCd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="unitLastIntchKey" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="unitLastDateOut" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="unitLastKm" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="unitLastKmSecond" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="unitLastElectricHours" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="unitLastDieselHours" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="unitLastDieselLitres" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Unit", propOrder = {
    "handheldActivated",
    "unitNr",
    "unitPhysicalCompanyNr",
    "unitPhysicalBranchNr",
    "unitOperationalStatus",
    "unitCatgrpCode",
    "unitSerialNr",
    "unitManufacturer",
    "unitModelYear",
    "unitLicenceNr",
    "unitLicenceCountryCd",
    "unitCd",
    "unitCustomerRefrnc",
    "unitInOutStatus",
    "unitStatus",
    "unitStatusComment",
    "unitLastParkLocCd",
    "unitLastIntchKey",
    "unitLastDateOut",
    "unitLastKm",
    "unitLastKmSecond",
    "unitLastElectricHours",
    "unitLastDieselHours",
    "unitLastDieselLitres"
})
public class Unit {

    @XmlElement(required = true, nillable = true)
    protected String handheldActivated;
    protected int unitNr;
    protected int unitPhysicalCompanyNr;
    protected int unitPhysicalBranchNr;
    @XmlElement(required = true)
    protected String unitOperationalStatus;
    @XmlElement(required = true)
    protected String unitCatgrpCode;
    @XmlElement(required = true, nillable = true)
    protected String unitSerialNr;
    @XmlElement(required = true, nillable = true)
    protected String unitManufacturer;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer unitModelYear;
    @XmlElement(required = true, nillable = true)
    protected String unitLicenceNr;
    @XmlElement(required = true, nillable = true)
    protected String unitLicenceCountryCd;
    @XmlElement(required = true)
    protected String unitCd;
    @XmlElement(required = true, nillable = true)
    protected String unitCustomerRefrnc;
    @XmlElement(required = true, nillable = true)
    protected String unitInOutStatus;
    @XmlElement(required = true, nillable = true)
    protected String unitStatus;
    @XmlElement(required = true, nillable = true)
    protected String unitStatusComment;
    @XmlElement(required = true, nillable = true)
    protected String unitLastParkLocCd;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long unitLastIntchKey;
    @XmlElement(required = true)
    protected String unitLastDateOut;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer unitLastKm;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer unitLastKmSecond;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer unitLastElectricHours;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer unitLastDieselHours;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer unitLastDieselLitres;

    /**
     * Gets the value of the handheldActivated property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHandheldActivated() {
        return handheldActivated;
    }

    /**
     * Sets the value of the handheldActivated property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHandheldActivated(String value) {
        this.handheldActivated = value;
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
     * Gets the value of the unitPhysicalCompanyNr property.
     * 
     */
    public int getUnitPhysicalCompanyNr() {
        return unitPhysicalCompanyNr;
    }

    /**
     * Sets the value of the unitPhysicalCompanyNr property.
     * 
     */
    public void setUnitPhysicalCompanyNr(int value) {
        this.unitPhysicalCompanyNr = value;
    }

    /**
     * Gets the value of the unitPhysicalBranchNr property.
     * 
     */
    public int getUnitPhysicalBranchNr() {
        return unitPhysicalBranchNr;
    }

    /**
     * Sets the value of the unitPhysicalBranchNr property.
     * 
     */
    public void setUnitPhysicalBranchNr(int value) {
        this.unitPhysicalBranchNr = value;
    }

    /**
     * Gets the value of the unitOperationalStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnitOperationalStatus() {
        return unitOperationalStatus;
    }

    /**
     * Sets the value of the unitOperationalStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnitOperationalStatus(String value) {
        this.unitOperationalStatus = value;
    }

    /**
     * Gets the value of the unitCatgrpCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnitCatgrpCode() {
        return unitCatgrpCode;
    }

    /**
     * Sets the value of the unitCatgrpCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnitCatgrpCode(String value) {
        this.unitCatgrpCode = value;
    }

    /**
     * Gets the value of the unitSerialNr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnitSerialNr() {
        return unitSerialNr;
    }

    /**
     * Sets the value of the unitSerialNr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnitSerialNr(String value) {
        this.unitSerialNr = value;
    }

    /**
     * Gets the value of the unitManufacturer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnitManufacturer() {
        return unitManufacturer;
    }

    /**
     * Sets the value of the unitManufacturer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnitManufacturer(String value) {
        this.unitManufacturer = value;
    }

    /**
     * Gets the value of the unitModelYear property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getUnitModelYear() {
        return unitModelYear;
    }

    /**
     * Sets the value of the unitModelYear property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setUnitModelYear(Integer value) {
        this.unitModelYear = value;
    }

    /**
     * Gets the value of the unitLicenceNr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnitLicenceNr() {
        return unitLicenceNr;
    }

    /**
     * Sets the value of the unitLicenceNr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnitLicenceNr(String value) {
        this.unitLicenceNr = value;
    }

    /**
     * Gets the value of the unitLicenceCountryCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnitLicenceCountryCd() {
        return unitLicenceCountryCd;
    }

    /**
     * Sets the value of the unitLicenceCountryCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnitLicenceCountryCd(String value) {
        this.unitLicenceCountryCd = value;
    }

    /**
     * Gets the value of the unitCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnitCd() {
        return unitCd;
    }

    /**
     * Sets the value of the unitCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnitCd(String value) {
        this.unitCd = value;
    }

    /**
     * Gets the value of the unitCustomerRefrnc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnitCustomerRefrnc() {
        return unitCustomerRefrnc;
    }

    /**
     * Sets the value of the unitCustomerRefrnc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnitCustomerRefrnc(String value) {
        this.unitCustomerRefrnc = value;
    }

    /**
     * Gets the value of the unitInOutStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnitInOutStatus() {
        return unitInOutStatus;
    }

    /**
     * Sets the value of the unitInOutStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnitInOutStatus(String value) {
        this.unitInOutStatus = value;
    }

    /**
     * Gets the value of the unitStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnitStatus() {
        return unitStatus;
    }

    /**
     * Sets the value of the unitStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnitStatus(String value) {
        this.unitStatus = value;
    }

    /**
     * Gets the value of the unitStatusComment property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnitStatusComment() {
        return unitStatusComment;
    }

    /**
     * Sets the value of the unitStatusComment property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnitStatusComment(String value) {
        this.unitStatusComment = value;
    }

    /**
     * Gets the value of the unitLastParkLocCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnitLastParkLocCd() {
        return unitLastParkLocCd;
    }

    /**
     * Sets the value of the unitLastParkLocCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnitLastParkLocCd(String value) {
        this.unitLastParkLocCd = value;
    }

    /**
     * Gets the value of the unitLastIntchKey property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getUnitLastIntchKey() {
        return unitLastIntchKey;
    }

    /**
     * Sets the value of the unitLastIntchKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setUnitLastIntchKey(Long value) {
        this.unitLastIntchKey = value;
    }

    /**
     * Gets the value of the unitLastDateOut property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnitLastDateOut() {
        return unitLastDateOut;
    }

    /**
     * Sets the value of the unitLastDateOut property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnitLastDateOut(String value) {
        this.unitLastDateOut = value;
    }

    /**
     * Gets the value of the unitLastKm property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getUnitLastKm() {
        return unitLastKm;
    }

    /**
     * Sets the value of the unitLastKm property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setUnitLastKm(Integer value) {
        this.unitLastKm = value;
    }

    /**
     * Gets the value of the unitLastKmSecond property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getUnitLastKmSecond() {
        return unitLastKmSecond;
    }

    /**
     * Sets the value of the unitLastKmSecond property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setUnitLastKmSecond(Integer value) {
        this.unitLastKmSecond = value;
    }

    /**
     * Gets the value of the unitLastElectricHours property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getUnitLastElectricHours() {
        return unitLastElectricHours;
    }

    /**
     * Sets the value of the unitLastElectricHours property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setUnitLastElectricHours(Integer value) {
        this.unitLastElectricHours = value;
    }

    /**
     * Gets the value of the unitLastDieselHours property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getUnitLastDieselHours() {
        return unitLastDieselHours;
    }

    /**
     * Sets the value of the unitLastDieselHours property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setUnitLastDieselHours(Integer value) {
        this.unitLastDieselHours = value;
    }

    /**
     * Gets the value of the unitLastDieselLitres property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getUnitLastDieselLitres() {
        return unitLastDieselLitres;
    }

    /**
     * Sets the value of the unitLastDieselLitres property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setUnitLastDieselLitres(Integer value) {
        this.unitLastDieselLitres = value;
    }

}
