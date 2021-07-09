
package com.tip.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CheckOutSaveResults complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CheckOutSaveResults">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.europe.pocketmobile.ese.ef.ge.com}BaseReponse">
 *       &lt;sequence>
 *         &lt;element name="intchKey" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CheckOutSaveResults", propOrder = {
    "intchKey"
})
public class CheckOutSaveResults
    extends BaseReponse
{

    protected long intchKey;

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

}
