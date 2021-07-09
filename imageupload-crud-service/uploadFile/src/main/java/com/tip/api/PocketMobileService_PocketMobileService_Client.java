
package com.tip.api;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import javax.xml.namespace.QName;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * This class was generated by Apache CXF 2.1.9
 * Thu Jun 22 15:39:24 IST 2017
 * Generated source version: 2.1.9
 * 
 */

public final class PocketMobileService_PocketMobileService_Client {

    private static final QName SERVICE_NAME = new QName("http://service.europe.pocketmobile.ese.ef.ge.com", "PocketMobileServiceService");

    private PocketMobileService_PocketMobileService_Client() {
    }

    public static void main(String args[]) throws Exception {
        URL wsdlURL = PocketMobileServiceService.WSDL_LOCATION;
        if (args.length > 0) { 
            File wsdlFile = new File(args[0]);
            try {
                if (wsdlFile.exists()) {
                    wsdlURL = wsdlFile.toURI().toURL();
                } else {
                    wsdlURL = new URL(args[0]);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        PocketMobileServiceService ss = new PocketMobileServiceService(wsdlURL, SERVICE_NAME);
        PocketMobileService port = ss.getPocketMobileService();

        {
            System.out.println("Invoking fetch...");
            String _fetch_sessionId = "";
            int _fetch_branchNr = 0;
            FetchInput _fetch_fetch = null;
            FetchResults _fetch__return = port.fetch(_fetch_sessionId, _fetch_branchNr, _fetch_fetch);
            System.out.println("fetch.result=" + _fetch__return);


        }
        {
            System.out.println("Invoking getDamangeDetails...");
            GetDamangeDetailsRequest _getDamangeDetails_parameters = null;
            GetDamangeDetailsResponse _getDamangeDetails__return = port.getDamangeDetails(_getDamangeDetails_parameters);
            System.out.println("getDamangeDetails.result=" + _getDamangeDetails__return);


        }
        {
            System.out.println("Invoking search...");
            Search _search_parameters = null;
            SearchResponse _search__return = port.search(_search_parameters);
            System.out.println("search.result=" + _search__return);


        }
        {
            System.out.println("Invoking getBranches...");
            SearchBranchesRequest _getBranches_parameters = null;
            SearchBranchesResponse _getBranches__return = port.getBranches(_getBranches_parameters);
            System.out.println("getBranches.result=" + _getBranches__return);


        }
        {
            System.out.println("Invoking documentUpload...");
            DocUploadRequest _documentUpload_parameters = null;
            DocUploadResponse _documentUpload__return = port.documentUpload(_documentUpload_parameters);
            System.out.println("documentUpload.result=" + _documentUpload__return);


        }
        {
            System.out.println("Invoking generateCheckInPDF...");
            GenerateCheckInPDFRequest _generateCheckInPDF_parameters = null;
            GenerateCheckInPDFResponse _generateCheckInPDF__return = port.generateCheckInPDF(_generateCheckInPDF_parameters);
            System.out.println("generateCheckInPDF.result=" + _generateCheckInPDF__return);


        }
        {
            System.out.println("Invoking getTranslations...");
            SearchTranslationsRequest _getTranslations_parameters = null;
            SearchTranslationsResponse _getTranslations__return = port.getTranslations(_getTranslations_parameters);
            System.out.println("getTranslations.result=" + _getTranslations__return);


        }
        {
            System.out.println("Invoking loginTest...");
            String _loginTest_password = "";
            String _loginTest_userId = "";
            AuthenticationTestResponse _loginTest__return = port.loginTest(_loginTest_password, _loginTest_userId);
            System.out.println("loginTest.result=" + _loginTest__return);


        }
        {
            System.out.println("Invoking getImage...");
            GetImageRequest _getImage_parameters = null;
            GetImageResponse _getImage__return = port.getImage(_getImage_parameters);
            System.out.println("getImage.result=" + _getImage__return);


        }
        {
            System.out.println("Invoking fetchStaticList...");
            String _fetchStaticList_sessionId = "";
            int _fetchStaticList_branchNr = 0;
            ListKey _fetchStaticList_key = null;
            FetchStaticListResults _fetchStaticList__return = port.fetchStaticList(_fetchStaticList_sessionId, _fetchStaticList_branchNr, _fetchStaticList_key);
            System.out.println("fetchStaticList.result=" + _fetchStaticList__return);


        }
        {
            System.out.println("Invoking checkOutSave...");
            String _checkOutSave_sessionId = "";
            String _checkOutSave_transactionId = "";
            int _checkOutSave_branchNr = 0;
            CheckOutSaveInput _checkOutSave_saveRequest = null;
            CheckOutSaveResults _checkOutSave__return = port.checkOutSave(_checkOutSave_sessionId, _checkOutSave_transactionId, _checkOutSave_branchNr, _checkOutSave_saveRequest);
            System.out.println("checkOutSave.result=" + _checkOutSave__return);


        }
        {
            System.out.println("Invoking getImageChunks...");
            SearchImageChunksRequest _getImageChunks_parameters = null;
            SearchImageChunksResponse _getImageChunks__return = port.getImageChunks(_getImageChunks_parameters);
            System.out.println("getImageChunks.result=" + _getImageChunks__return);


        }
        {
            System.out.println("Invoking login...");
            int _login_branchNr = 0;
            AuthenticationToken _login_authToken = null;
            AuthenticationResponse _login__return = port.login(_login_branchNr, _login_authToken);
            System.out.println("login.result=" + _login__return);


        }
        {
            System.out.println("Invoking imageUpload...");
            ImgUploadRequest _imageUpload_parameters = null;
            ImgUploadResponse _imageUpload__return = port.imageUpload(_imageUpload_parameters);
            System.out.println("imageUpload.result=" + _imageUpload__return);


        }
        {
            System.out.println("Invoking save...");
            String _save_sessionId = "";
            String _save_transactionId = "";
            int _save_branchNr = 0;
            SaveInput _save_saveRequest = null;
            SaveResults _save__return = port.save(_save_sessionId, _save_transactionId, _save_branchNr, _save_saveRequest);
            System.out.println("save.result=" + _save__return);


        }

        System.exit(0);
    }

}
