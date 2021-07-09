
package com.ge.ef.ese.pocketmobile.europe.service;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "PocketMobileServiceService", targetNamespace = "http://service.europe.pocketmobile.ese.ef.ge.com", wsdlLocation = "https://www.tip-europe.com/UAT/PMALSCheckInServices/PocketMobileService.wsdl")
public class PocketMobileServiceService
    extends Service
{

    private final static URL POCKETMOBILESERVICESERVICE_WSDL_LOCATION;
    private final static WebServiceException POCKETMOBILESERVICESERVICE_EXCEPTION;
    private final static QName POCKETMOBILESERVICESERVICE_QNAME = new QName("http://service.europe.pocketmobile.ese.ef.ge.com", "PocketMobileServiceService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("https://www.tip-europe.com/UAT/PMALSCheckInServices/PocketMobileService.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        POCKETMOBILESERVICESERVICE_WSDL_LOCATION = url;
        POCKETMOBILESERVICESERVICE_EXCEPTION = e;
    }

    public PocketMobileServiceService() {
        super(__getWsdlLocation(), POCKETMOBILESERVICESERVICE_QNAME);
    }

    public PocketMobileServiceService(WebServiceFeature... features) {
        super(__getWsdlLocation(), POCKETMOBILESERVICESERVICE_QNAME, features);
    }

    public PocketMobileServiceService(URL wsdlLocation) {
        super(wsdlLocation, POCKETMOBILESERVICESERVICE_QNAME);
    }

    public PocketMobileServiceService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, POCKETMOBILESERVICESERVICE_QNAME, features);
    }

    public PocketMobileServiceService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public PocketMobileServiceService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns PocketMobileService
     */
    @WebEndpoint(name = "PocketMobileService")
    public PocketMobileService getPocketMobileService() {
        return super.getPort(new QName("http://service.europe.pocketmobile.ese.ef.ge.com", "PocketMobileService"), PocketMobileService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns PocketMobileService
     */
    @WebEndpoint(name = "PocketMobileService")
    public PocketMobileService getPocketMobileService(WebServiceFeature... features) {
        return super.getPort(new QName("http://service.europe.pocketmobile.ese.ef.ge.com", "PocketMobileService"), PocketMobileService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (POCKETMOBILESERVICESERVICE_EXCEPTION!= null) {
            throw POCKETMOBILESERVICESERVICE_EXCEPTION;
        }
        return POCKETMOBILESERVICESERVICE_WSDL_LOCATION;
    }

}