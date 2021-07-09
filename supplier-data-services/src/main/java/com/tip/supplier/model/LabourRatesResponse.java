//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.06.27 at 06:46:42 PM IST 
//


package com.tip.supplier.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LabourRatesResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LabourRatesResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="LabourDynamicLinesList" type="{http://www.example.org/LabourRates}LabourDynamicLinesObject" maxOccurs="unbounded"/>
 *         &lt;element name="LabourStaticLinesList" type="{http://www.example.org/LabourRates}LabourStaticLinesObject" maxOccurs="unbounded"/>
 *         &lt;element name="ContractDataList" type="{http://www.example.org/LabourRates}LabourStaticLinesObject" maxOccurs="unbounded"/>
 *         &lt;element name="ActiveContractDocumentList" type="{http://www.example.org/LabourRates}ActiveContractDocumentObject" maxOccurs="unbounded"/>
 *         &lt;element name="ArchivedContractDocumentList" type="{http://www.example.org/LabourRates}ActiveContractDocumentObject" maxOccurs="unbounded"/>
 *         &lt;element name="labourLastUpdatedList" type="{http://www.example.org/LabourRates}labourLastUpdatedObject" maxOccurs="unbounded"/>
 *         &lt;element name="LabourRateStatusResponseList" type="{http://www.example.org/LabourRates}LabourRateStatusObject" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LabourRatesResponse", propOrder = {
    "labourDynamicLinesList",
    "labourStaticLinesList",
    "contractDataList",
    "activeContractDocumentList",
    "archivedContractDocumentList",
    "labourLastUpdatedList",
    "labourRateStatusResponseList"
})
public class LabourRatesResponse {

    @XmlElement(name = "LabourDynamicLinesList", required = true)
    protected List<LabourDynamicLinesObject> labourDynamicLinesList;
    @XmlElement(name = "LabourStaticLinesList", required = true)
    protected List<LabourStaticLinesObject> labourStaticLinesList;
    @XmlElement(name = "ContractDataList", required = true)
    protected List<LabourStaticLinesObject> contractDataList;
    @XmlElement(name = "ActiveContractDocumentList", required = true)
    protected List<ActiveContractDocumentObject> activeContractDocumentList;
    @XmlElement(name = "ArchivedContractDocumentList", required = true)
    protected List<ActiveContractDocumentObject> archivedContractDocumentList;
    @XmlElement(required = true)
    protected List<LabourLastUpdatedObject> labourLastUpdatedList;
    @XmlElement(name = "LabourRateStatusResponseList", required = true)
    protected List<LabourRateStatusObject> labourRateStatusResponseList;

    /**
     * Gets the value of the labourDynamicLinesList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the labourDynamicLinesList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLabourDynamicLinesList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LabourDynamicLinesObject }
     * 
     * 
     */
    public List<LabourDynamicLinesObject> getLabourDynamicLinesList() {
        if (labourDynamicLinesList == null) {
            labourDynamicLinesList = new ArrayList<LabourDynamicLinesObject>();
        }
        return this.labourDynamicLinesList;
    }

    /**
     * Gets the value of the labourStaticLinesList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the labourStaticLinesList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLabourStaticLinesList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LabourStaticLinesObject }
     * 
     * 
     */
    public List<LabourStaticLinesObject> getLabourStaticLinesList() {
        if (labourStaticLinesList == null) {
            labourStaticLinesList = new ArrayList<LabourStaticLinesObject>();
        }
        return this.labourStaticLinesList;
    }

    /**
     * Gets the value of the contractDataList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the contractDataList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContractDataList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LabourStaticLinesObject }
     * 
     * 
     */
    public List<LabourStaticLinesObject> getContractDataList() {
        if (contractDataList == null) {
            contractDataList = new ArrayList<LabourStaticLinesObject>();
        }
        return this.contractDataList;
    }

    /**
     * Gets the value of the activeContractDocumentList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the activeContractDocumentList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getActiveContractDocumentList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ActiveContractDocumentObject }
     * 
     * 
     */
    public List<ActiveContractDocumentObject> getActiveContractDocumentList() {
        if (activeContractDocumentList == null) {
            activeContractDocumentList = new ArrayList<ActiveContractDocumentObject>();
        }
        return this.activeContractDocumentList;
    }

    /**
     * Gets the value of the archivedContractDocumentList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the archivedContractDocumentList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getArchivedContractDocumentList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ActiveContractDocumentObject }
     * 
     * 
     */
    public List<ActiveContractDocumentObject> getArchivedContractDocumentList() {
        if (archivedContractDocumentList == null) {
            archivedContractDocumentList = new ArrayList<ActiveContractDocumentObject>();
        }
        return this.archivedContractDocumentList;
    }

    /**
     * Gets the value of the labourLastUpdatedList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the labourLastUpdatedList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLabourLastUpdatedList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LabourLastUpdatedObject }
     * 
     * 
     */
    public List<LabourLastUpdatedObject> getLabourLastUpdatedList() {
        if (labourLastUpdatedList == null) {
            labourLastUpdatedList = new ArrayList<LabourLastUpdatedObject>();
        }
        return this.labourLastUpdatedList;
    }

    /**
     * Gets the value of the labourRateStatusResponseList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the labourRateStatusResponseList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLabourRateStatusResponseList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LabourRateStatusObject }
     * 
     * 
     */
    public List<LabourRateStatusObject> getLabourRateStatusResponseList() {
        if (labourRateStatusResponseList == null) {
            labourRateStatusResponseList = new ArrayList<LabourRateStatusObject>();
        }
        return this.labourRateStatusResponseList;
    }

}
