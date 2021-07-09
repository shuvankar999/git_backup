
package com.tip.api;

import com.tip.api.ArrayOfBranchList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *       &lt;choice>
 *         &lt;element name="getBranchesReturn" type="{http://service.europe.pocketmobile.ese.ef.ge.com}ArrayOfBranchList"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "getBranchesReturn"
})
@XmlRootElement(name = "searchBranchesResponse")
public class SearchBranchesResponse {

    protected ArrayOfBranchList getBranchesReturn;

    /**
     * Gets the value of the getBranchesReturn property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfBranchList }
     *     
     */
    public ArrayOfBranchList getGetBranchesReturn() {
        return getBranchesReturn;
    }

    /**
     * Sets the value of the getBranchesReturn property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfBranchList }
     *     
     */
    public void setGetBranchesReturn(ArrayOfBranchList value) {
        this.getBranchesReturn = value;
    }

}
