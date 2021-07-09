
package com.tip.api;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for BaseReponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BaseReponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="errorObject" type="{http://service.europe.pocketmobile.ese.ef.ge.com}ArrayOfErrorObject"/>
 *         &lt;element name="status" type="{http://service.europe.pocketmobile.ese.ef.ge.com}ServiceStatus"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BaseReponse", propOrder = {
    "errorObject",
    "status"
})
@XmlSeeAlso({
    CheckOutSaveResults.class,
    AuthenticationResponse.class,
    GetDamangeDetailsResults.class,
    FetchResults.class,
    FetchStaticListResults.class,
    AuthenticationTestResponse.class,
    SaveResults.class,
    SearchResults.class,
    CheckOutSearchResults.class,
    SearchImageChunksResponse.class,
    DocUploadResponse.class,
    GenerateDocuResponse.class,
    GenerateCheckInPDFResponse.class,
    ImgUploadResponse.class,
    GetImageResponse.class
})
public class BaseReponse {

    @XmlElement(required = true, nillable = true)
    protected ArrayOfErrorObject errorObject;
    @XmlElement(required = true)
    protected ServiceStatus status;

    /**
     * Gets the value of the errorObject property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfErrorObject }
     *     
     */
    public ArrayOfErrorObject getErrorObject() {
        return errorObject;
    }

    /**
     * Sets the value of the errorObject property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfErrorObject }
     *     
     */
    public void setErrorObject(ArrayOfErrorObject value) {
        this.errorObject = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceStatus }
     *     
     */
    public ServiceStatus getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceStatus }
     *     
     */
    public void setStatus(ServiceStatus value) {
        this.status = value;
    }

}
