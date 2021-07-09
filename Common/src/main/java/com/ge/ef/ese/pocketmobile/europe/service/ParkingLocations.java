
package com.ge.ef.ese.pocketmobile.europe.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ParkingLocations complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ParkingLocations">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="branchNr" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="companyNr" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="parkingLocationCd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="parkingLoctnDesc" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ParkingLocations", propOrder = {
    "branchNr",
    "companyNr",
    "parkingLocationCd",
    "parkingLoctnDesc"
})
public class ParkingLocations {

    @XmlElement(required = true)
    protected String branchNr;
    @XmlElement(required = true)
    protected String companyNr;
    @XmlElement(required = true)
    protected String parkingLocationCd;
    @XmlElement(required = true, nillable = true)
    protected String parkingLoctnDesc;

    /**
     * Gets the value of the branchNr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBranchNr() {
        return branchNr;
    }

    /**
     * Sets the value of the branchNr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBranchNr(String value) {
        this.branchNr = value;
    }

    /**
     * Gets the value of the companyNr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompanyNr() {
        return companyNr;
    }

    /**
     * Sets the value of the companyNr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompanyNr(String value) {
        this.companyNr = value;
    }

    /**
     * Gets the value of the parkingLocationCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParkingLocationCd() {
        return parkingLocationCd;
    }

    /**
     * Sets the value of the parkingLocationCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParkingLocationCd(String value) {
        this.parkingLocationCd = value;
    }

    /**
     * Gets the value of the parkingLoctnDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParkingLoctnDesc() {
        return parkingLoctnDesc;
    }

    /**
     * Sets the value of the parkingLoctnDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParkingLoctnDesc(String value) {
        this.parkingLoctnDesc = value;
    }

}
