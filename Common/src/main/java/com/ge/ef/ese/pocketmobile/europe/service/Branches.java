
package com.ge.ef.ese.pocketmobile.europe.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Branches complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Branches">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="branchAddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="branchDefCy" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="branchDefLang" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="branchEmail" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="branchNr" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="companyNr" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Branches", propOrder = {
    "branchAddress",
    "branchDefCy",
    "branchDefLang",
    "branchEmail",
    "branchNr",
    "companyNr"
})
public class Branches {

    @XmlElement(required = true)
    protected String branchAddress;
    @XmlElement(required = true)
    protected String branchDefCy;
    @XmlElement(required = true, nillable = true)
    protected String branchDefLang;
    @XmlElement(required = true, nillable = true)
    protected String branchEmail;
    @XmlElement(required = true, nillable = true)
    protected String branchNr;
    @XmlElement(required = true, nillable = true)
    protected String companyNr;

    /**
     * Gets the value of the branchAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBranchAddress() {
        return branchAddress;
    }

    /**
     * Sets the value of the branchAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBranchAddress(String value) {
        this.branchAddress = value;
    }

    /**
     * Gets the value of the branchDefCy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBranchDefCy() {
        return branchDefCy;
    }

    /**
     * Sets the value of the branchDefCy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBranchDefCy(String value) {
        this.branchDefCy = value;
    }

    /**
     * Gets the value of the branchDefLang property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBranchDefLang() {
        return branchDefLang;
    }

    /**
     * Sets the value of the branchDefLang property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBranchDefLang(String value) {
        this.branchDefLang = value;
    }

    /**
     * Gets the value of the branchEmail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBranchEmail() {
        return branchEmail;
    }

    /**
     * Sets the value of the branchEmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBranchEmail(String value) {
        this.branchEmail = value;
    }

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

}
