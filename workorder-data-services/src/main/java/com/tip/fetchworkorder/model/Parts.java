//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.03.16 at 05:09:13 PM IST 
//


package com.tip.fetchworkorder.model;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Parts complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Parts">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Work_Pack_Nr" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Work_Order_Nr" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Work_Order_Task_Nr" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Part_Nr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Supplier_Part_Nr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Oe_Part_Nr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Tip_Part_Nr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Parts_Qty" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Cost_Cd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Cost_Sub_Cd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Repair_Id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Position_Cd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Reason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Discount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="List_Price" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Cost_Price" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Part_Status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Parts", propOrder = {
    "workPackNr",
    "workOrderNr",
    "workOrderTaskNr",
    "partNr",
    "supplierPartNr",
    "oePartNr",
    "tipPartNr",
    "partsQty",
    "costCd",
    "costSubCd",
    "repairId",
    "positionCd",
    "reason",
    "discount",
    "listPrice",
    "costPrice",
    "partStatus"
})
public class Parts {

    @XmlElement(name = "Work_Pack_Nr")
    protected BigDecimal workPackNr;
    @XmlElement(name = "Work_Order_Nr")
    protected Integer workOrderNr;
    @XmlElement(name = "Work_Order_Task_Nr")
    protected Integer workOrderTaskNr;
    @XmlElement(name = "Part_Nr")
    protected String partNr;
    @XmlElement(name = "Supplier_Part_Nr")
    protected String supplierPartNr;
    @XmlElement(name = "Oe_Part_Nr")
    protected String oePartNr;
    @XmlElement(name = "Tip_Part_Nr")
    protected String tipPartNr;
    @XmlElement(name = "Parts_Qty")
    protected Integer partsQty;
    @XmlElement(name = "Cost_Cd")
    protected String costCd;
    @XmlElement(name = "Cost_Sub_Cd")
    protected String costSubCd;
    @XmlElement(name = "Repair_Id")
    protected String repairId;
    @XmlElement(name = "Position_Cd")
    protected String positionCd;
    @XmlElement(name = "Reason")
    protected String reason;
    @XmlElement(name = "Discount")
    protected BigDecimal discount;
    @XmlElement(name = "List_Price")
    protected BigDecimal listPrice;
    @XmlElement(name = "Cost_Price")
    protected BigDecimal costPrice;
    @XmlElement(name = "Part_Status")
    protected String partStatus;

    /**
     * Gets the value of the workPackNr property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getWorkPackNr() {
        return workPackNr;
    }

    /**
     * Sets the value of the workPackNr property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setWorkPackNr(BigDecimal value) {
        this.workPackNr = value;
    }

    /**
     * Gets the value of the workOrderNr property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getWorkOrderNr() {
        return workOrderNr;
    }

    /**
     * Sets the value of the workOrderNr property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setWorkOrderNr(Integer value) {
        this.workOrderNr = value;
    }

    /**
     * Gets the value of the workOrderTaskNr property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getWorkOrderTaskNr() {
        return workOrderTaskNr;
    }

    /**
     * Sets the value of the workOrderTaskNr property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setWorkOrderTaskNr(Integer value) {
        this.workOrderTaskNr = value;
    }

    /**
     * Gets the value of the partNr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartNr() {
        return partNr;
    }

    /**
     * Sets the value of the partNr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartNr(String value) {
        this.partNr = value;
    }

    /**
     * Gets the value of the supplierPartNr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSupplierPartNr() {
        return supplierPartNr;
    }

    /**
     * Sets the value of the supplierPartNr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSupplierPartNr(String value) {
        this.supplierPartNr = value;
    }

    /**
     * Gets the value of the oePartNr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOePartNr() {
        return oePartNr;
    }

    /**
     * Sets the value of the oePartNr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOePartNr(String value) {
        this.oePartNr = value;
    }

    /**
     * Gets the value of the tipPartNr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipPartNr() {
        return tipPartNr;
    }

    /**
     * Sets the value of the tipPartNr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipPartNr(String value) {
        this.tipPartNr = value;
    }

    /**
     * Gets the value of the partsQty property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPartsQty() {
        return partsQty;
    }

    /**
     * Sets the value of the partsQty property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPartsQty(Integer value) {
        this.partsQty = value;
    }

    /**
     * Gets the value of the costCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCostCd() {
        return costCd;
    }

    /**
     * Sets the value of the costCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCostCd(String value) {
        this.costCd = value;
    }

    /**
     * Gets the value of the costSubCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCostSubCd() {
        return costSubCd;
    }

    /**
     * Sets the value of the costSubCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCostSubCd(String value) {
        this.costSubCd = value;
    }

    /**
     * Gets the value of the repairId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRepairId() {
        return repairId;
    }

    /**
     * Sets the value of the repairId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRepairId(String value) {
        this.repairId = value;
    }

    /**
     * Gets the value of the positionCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPositionCd() {
        return positionCd;
    }

    /**
     * Sets the value of the positionCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPositionCd(String value) {
        this.positionCd = value;
    }

    /**
     * Gets the value of the reason property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReason() {
        return reason;
    }

    /**
     * Sets the value of the reason property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReason(String value) {
        this.reason = value;
    }

    /**
     * Gets the value of the discount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDiscount() {
        return discount;
    }

    /**
     * Sets the value of the discount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDiscount(BigDecimal value) {
        this.discount = value;
    }

    /**
     * Gets the value of the listPrice property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getListPrice() {
        return listPrice;
    }

    /**
     * Sets the value of the listPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setListPrice(BigDecimal value) {
        this.listPrice = value;
    }

    /**
     * Gets the value of the costPrice property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCostPrice() {
        return costPrice;
    }

    /**
     * Sets the value of the costPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCostPrice(BigDecimal value) {
        this.costPrice = value;
    }

    /**
     * Gets the value of the partStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartStatus() {
        return partStatus;
    }

    /**
     * Sets the value of the partStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartStatus(String value) {
        this.partStatus = value;
    }

}