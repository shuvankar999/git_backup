
package com.ge.ef.ese.pocketmobile.europe.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Holder;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "PocketMobileService", targetNamespace = "http://service.europe.pocketmobile.ese.ef.ge.com")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface PocketMobileService {


    /**
     * 
     * @param checkInSearchReturn
     * @param search
     * @param checkOutSearchReturn
     * @param branchNr
     * @param sessionId
     */
    @WebMethod
    @RequestWrapper(localName = "search", targetNamespace = "http://service.europe.pocketmobile.ese.ef.ge.com", className = "com.ge.ef.ese.pocketmobile.europe.service.Search")
    @ResponseWrapper(localName = "searchResponse", targetNamespace = "http://service.europe.pocketmobile.ese.ef.ge.com", className = "com.ge.ef.ese.pocketmobile.europe.service.SearchResponse")
    public void search(
        @WebParam(name = "sessionId", targetNamespace = "http://service.europe.pocketmobile.ese.ef.ge.com")
        String sessionId,
        @WebParam(name = "branchNr", targetNamespace = "http://service.europe.pocketmobile.ese.ef.ge.com")
        int branchNr,
        @WebParam(name = "search", targetNamespace = "http://service.europe.pocketmobile.ese.ef.ge.com")
        SearchInput search,
        @WebParam(name = "checkInSearchReturn", targetNamespace = "http://service.europe.pocketmobile.ese.ef.ge.com", mode = WebParam.Mode.OUT)
        Holder<CheckInSearchReturn> checkInSearchReturn,
        @WebParam(name = "checkOutSearchReturn", targetNamespace = "http://service.europe.pocketmobile.ese.ef.ge.com", mode = WebParam.Mode.OUT)
        Holder<CheckOutSearchReturn> checkOutSearchReturn);

    /**
     * 
     * @param parameters
     * @return
     *     returns com.ge.ef.ese.pocketmobile.europe.service.SearchBranchesResponse
     */
    @WebMethod
    @WebResult(name = "searchBranchesResponse", targetNamespace = "http://service.europe.pocketmobile.ese.ef.ge.com", partName = "parametersResponse")
    @SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
    public SearchBranchesResponse getBranches(
        @WebParam(name = "searchBranchesRequest", targetNamespace = "http://service.europe.pocketmobile.ese.ef.ge.com", partName = "parameters")
        SearchBranchesRequest parameters);

    /**
     * 
     * @param parameters
     * @return
     *     returns com.ge.ef.ese.pocketmobile.europe.service.SearchTranslationsResponse
     */
    @WebMethod
    @WebResult(name = "searchTranslationsResponse", targetNamespace = "http://service.europe.pocketmobile.ese.ef.ge.com", partName = "parametersResponse")
    @SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
    public SearchTranslationsResponse getTranslations(
        @WebParam(name = "searchTranslationsRequest", targetNamespace = "http://service.europe.pocketmobile.ese.ef.ge.com", partName = "parameters")
        SearchTranslationsRequest parameters);

    /**
     * 
     * @param parameters
     * @return
     *     returns com.ge.ef.ese.pocketmobile.europe.service.SearchImageChunksResponse
     */
    @WebMethod
    @WebResult(name = "searchImageChunksResponse", targetNamespace = "http://service.europe.pocketmobile.ese.ef.ge.com", partName = "parametersResponse")
    @SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
    public SearchImageChunksResponse getImageChunks(
        @WebParam(name = "searchImageChunksRequest", targetNamespace = "http://service.europe.pocketmobile.ese.ef.ge.com", partName = "parameters")
        SearchImageChunksRequest parameters);

    /**
     * 
     * @param authToken
     * @param branchNr
     * @return
     *     returns com.ge.ef.ese.pocketmobile.europe.service.AuthenticationResponse
     */
    @WebMethod
    @WebResult(name = "loginReturn", targetNamespace = "http://service.europe.pocketmobile.ese.ef.ge.com")
    @RequestWrapper(localName = "login", targetNamespace = "http://service.europe.pocketmobile.ese.ef.ge.com", className = "com.ge.ef.ese.pocketmobile.europe.service.Login")
    @ResponseWrapper(localName = "loginResponse", targetNamespace = "http://service.europe.pocketmobile.ese.ef.ge.com", className = "com.ge.ef.ese.pocketmobile.europe.service.LoginResponse")
    public AuthenticationResponse login(
        @WebParam(name = "branchNr", targetNamespace = "http://service.europe.pocketmobile.ese.ef.ge.com")
        int branchNr,
        @WebParam(name = "authToken", targetNamespace = "http://service.europe.pocketmobile.ese.ef.ge.com")
        AuthenticationToken authToken);

    /**
     * 
     * @param password
     * @param userId
     * @return
     *     returns com.ge.ef.ese.pocketmobile.europe.service.AuthenticationTestResponse
     */
    @WebMethod
    @WebResult(name = "loginTestReturn", targetNamespace = "http://service.europe.pocketmobile.ese.ef.ge.com")
    @RequestWrapper(localName = "loginTest", targetNamespace = "http://service.europe.pocketmobile.ese.ef.ge.com", className = "com.ge.ef.ese.pocketmobile.europe.service.LoginTest")
    @ResponseWrapper(localName = "loginTestResponse", targetNamespace = "http://service.europe.pocketmobile.ese.ef.ge.com", className = "com.ge.ef.ese.pocketmobile.europe.service.LoginTestResponse")
    public AuthenticationTestResponse loginTest(
        @WebParam(name = "password", targetNamespace = "http://service.europe.pocketmobile.ese.ef.ge.com")
        String password,
        @WebParam(name = "userId", targetNamespace = "http://service.europe.pocketmobile.ese.ef.ge.com")
        String userId);

    /**
     * 
     * @param branchNr
     * @param sessionId
     * @param key
     * @return
     *     returns com.ge.ef.ese.pocketmobile.europe.service.FetchStaticListResults
     */
    @WebMethod
    @WebResult(name = "fetchStaticListReturn", targetNamespace = "http://service.europe.pocketmobile.ese.ef.ge.com")
    @RequestWrapper(localName = "fetchStaticList", targetNamespace = "http://service.europe.pocketmobile.ese.ef.ge.com", className = "com.ge.ef.ese.pocketmobile.europe.service.FetchStaticList")
    @ResponseWrapper(localName = "fetchStaticListResponse", targetNamespace = "http://service.europe.pocketmobile.ese.ef.ge.com", className = "com.ge.ef.ese.pocketmobile.europe.service.FetchStaticListResponse")
    public FetchStaticListResults fetchStaticList(
        @WebParam(name = "sessionId", targetNamespace = "http://service.europe.pocketmobile.ese.ef.ge.com")
        String sessionId,
        @WebParam(name = "branchNr", targetNamespace = "http://service.europe.pocketmobile.ese.ef.ge.com")
        int branchNr,
        @WebParam(name = "key", targetNamespace = "http://service.europe.pocketmobile.ese.ef.ge.com")
        ListKey key);

    /**
     * 
     * @param branchNr
     * @param sessionId
     * @param saveRequest
     * @param transactionId
     * @return
     *     returns com.ge.ef.ese.pocketmobile.europe.service.SaveResults
     */
    @WebMethod
    @WebResult(name = "saveReturn", targetNamespace = "http://service.europe.pocketmobile.ese.ef.ge.com")
    @RequestWrapper(localName = "save", targetNamespace = "http://service.europe.pocketmobile.ese.ef.ge.com", className = "com.ge.ef.ese.pocketmobile.europe.service.Save")
    @ResponseWrapper(localName = "saveResponse", targetNamespace = "http://service.europe.pocketmobile.ese.ef.ge.com", className = "com.ge.ef.ese.pocketmobile.europe.service.SaveResponse")
    public SaveResults save(
        @WebParam(name = "sessionId", targetNamespace = "http://service.europe.pocketmobile.ese.ef.ge.com")
        String sessionId,
        @WebParam(name = "transactionId", targetNamespace = "http://service.europe.pocketmobile.ese.ef.ge.com")
        String transactionId,
        @WebParam(name = "branchNr", targetNamespace = "http://service.europe.pocketmobile.ese.ef.ge.com")
        int branchNr,
        @WebParam(name = "saveRequest", targetNamespace = "http://service.europe.pocketmobile.ese.ef.ge.com")
        SaveInput saveRequest);

    /**
     * 
     * @param branchNr
     * @param sessionId
     * @param saveRequest
     * @param transactionId
     * @return
     *     returns com.ge.ef.ese.pocketmobile.europe.service.CheckOutSaveResults
     */
    @WebMethod
    @WebResult(name = "saveReturn", targetNamespace = "http://service.europe.pocketmobile.ese.ef.ge.com")
    @RequestWrapper(localName = "checkOutSave", targetNamespace = "http://service.europe.pocketmobile.ese.ef.ge.com", className = "com.ge.ef.ese.pocketmobile.europe.service.CheckOutSave")
    @ResponseWrapper(localName = "checkOutSaveResponse", targetNamespace = "http://service.europe.pocketmobile.ese.ef.ge.com", className = "com.ge.ef.ese.pocketmobile.europe.service.CheckOutSaveResponse")
    public CheckOutSaveResults checkOutSave(
        @WebParam(name = "sessionId", targetNamespace = "http://service.europe.pocketmobile.ese.ef.ge.com")
        String sessionId,
        @WebParam(name = "transactionId", targetNamespace = "http://service.europe.pocketmobile.ese.ef.ge.com")
        String transactionId,
        @WebParam(name = "branchNr", targetNamespace = "http://service.europe.pocketmobile.ese.ef.ge.com")
        int branchNr,
        @WebParam(name = "saveRequest", targetNamespace = "http://service.europe.pocketmobile.ese.ef.ge.com")
        CheckOutSaveInput saveRequest);

    /**
     * 
     * @param fetch
     * @param branchNr
     * @param sessionId
     * @return
     *     returns com.ge.ef.ese.pocketmobile.europe.service.FetchResults
     */
    @WebMethod
    @WebResult(name = "fetchReturn", targetNamespace = "http://service.europe.pocketmobile.ese.ef.ge.com")
    @RequestWrapper(localName = "fetch", targetNamespace = "http://service.europe.pocketmobile.ese.ef.ge.com", className = "com.ge.ef.ese.pocketmobile.europe.service.Fetch")
    @ResponseWrapper(localName = "fetchResponse", targetNamespace = "http://service.europe.pocketmobile.ese.ef.ge.com", className = "com.ge.ef.ese.pocketmobile.europe.service.FetchResponse")
    public FetchResults fetch(
        @WebParam(name = "sessionId", targetNamespace = "http://service.europe.pocketmobile.ese.ef.ge.com")
        String sessionId,
        @WebParam(name = "branchNr", targetNamespace = "http://service.europe.pocketmobile.ese.ef.ge.com")
        int branchNr,
        @WebParam(name = "fetch", targetNamespace = "http://service.europe.pocketmobile.ese.ef.ge.com")
        FetchInput fetch);

    /**
     * 
     * @param parameters
     * @return
     *     returns com.ge.ef.ese.pocketmobile.europe.service.DocUploadResponse
     */
    @WebMethod
    @WebResult(name = "docUploadResponse", targetNamespace = "http://service.europe.pocketmobile.ese.ef.ge.com", partName = "parametersResponse")
    @SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
    public DocUploadResponse documentUpload(
        @WebParam(name = "docUploadRequest", targetNamespace = "http://service.europe.pocketmobile.ese.ef.ge.com", partName = "parameters")
        DocUploadRequest parameters);

    /**
     * 
     * @param parameters
     * @return
     *     returns com.ge.ef.ese.pocketmobile.europe.service.ImgUploadResponse
     */
    @WebMethod
    @WebResult(name = "imgUploadResponse", targetNamespace = "http://service.europe.pocketmobile.ese.ef.ge.com", partName = "parametersResponse")
    @SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
    public ImgUploadResponse imageUpload(
        @WebParam(name = "imgUploadRequest", targetNamespace = "http://service.europe.pocketmobile.ese.ef.ge.com", partName = "parameters")
        ImgUploadRequest parameters);

    /**
     * 
     * @param parameters
     * @return
     *     returns com.ge.ef.ese.pocketmobile.europe.service.GetImageResponse
     */
    @WebMethod
    @WebResult(name = "getImageResponse", targetNamespace = "http://service.europe.pocketmobile.ese.ef.ge.com", partName = "parametersResponse")
    @SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
    public GetImageResponse getImage(
        @WebParam(name = "getImageRequest", targetNamespace = "http://service.europe.pocketmobile.ese.ef.ge.com", partName = "parameters")
        GetImageRequest parameters);

    /**
     * 
     * @param parameters
     * @return
     *     returns com.ge.ef.ese.pocketmobile.europe.service.GenerateCheckInPDFResponse
     */
    @WebMethod
    @WebResult(name = "generateCheckInPDFResponse", targetNamespace = "http://service.europe.pocketmobile.ese.ef.ge.com", partName = "parametersResponse")
    @SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
    public GenerateCheckInPDFResponse generateCheckInPDF(
        @WebParam(name = "generateCheckInPDFRequest", targetNamespace = "http://service.europe.pocketmobile.ese.ef.ge.com", partName = "parameters")
        GenerateCheckInPDFRequest parameters);

}
