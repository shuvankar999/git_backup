
package com.tip.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BranchList complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BranchList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BranchNr" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="BranchName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BranchList", propOrder = {
    "branchNr",
    "branchName"
})
public class BranchList {

    @XmlElement(name = "BranchNr")
    protected int branchNr;
    @XmlElement(name = "BranchName", required = true, nillable = true)
    protected String branchName;

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
     * Gets the value of the branchName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBranchName() {
        return branchName;
    }

    /**
     * Sets the value of the branchName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBranchName(String value) {
        this.branchName = value;
    }

}
