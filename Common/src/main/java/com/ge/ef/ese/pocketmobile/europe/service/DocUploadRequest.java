
package com.ge.ef.ese.pocketmobile.europe.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="intchKey" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="inspectionType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="document" type="{http://service.europe.pocketmobile.ese.ef.ge.com}Document"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "intchKey",
    "inspectionType",
    "document"
})
@XmlRootElement(name = "docUploadRequest")
public class DocUploadRequest {

    protected long intchKey;
    @XmlElement(required = true)
    protected String inspectionType;
    @XmlElement(required = true)
    protected Document document;

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
     * Gets the value of the inspectionType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInspectionType() {
        return inspectionType;
    }

    /**
     * Sets the value of the inspectionType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInspectionType(String value) {
        this.inspectionType = value;
    }

    /**
     * Gets the value of the document property.
     * 
     * @return
     *     possible object is
     *     {@link Document }
     *     
     */
    public Document getDocument() {
        return document;
    }

    /**
     * Sets the value of the document property.
     * 
     * @param value
     *     allowed object is
     *     {@link Document }
     *     
     */
    public void setDocument(Document value) {
        this.document = value;
    }

}
