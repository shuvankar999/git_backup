/**
 * This program is proprietary material of a confidential nature of GE Equipment
 * Services. No copies of this program or parts of this program may be made
 * without written consent of GE Equipment Services.
 * 
 * @version 		: 1.0
 * @author 			: Tata Consultancy Services Ltd. 
 * Class 			: DaoService
 * Description 		: Dao Interface class for Audit and Login Services
 * Creation Date 	: 25-July-2008
 * History 			:
 * 
 * Date Modified by Description
 * ------------------------------------------------------------------------------------------------------
 * dd-mmm-yyyy <Name> [Multiline / Precise description]
 * 
 */
package com.document.upload.documentservice.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.document.upload.documentservice.data.SupplierDocUploadInput;


@Service
public  interface  DaoService {
	

//	private static DaoServiceImpl doaimpl;



	 public  String insertDocumentData(final SupplierDocUploadInput errInp);

    /** ********************* Audit START ******************************* */

    /**
     * This method is used to audit the request and return the transaction_id
     * 
     * @return - transaction_id
     */

   
    /**
     * This method is used to audit the response
     * 
     * @return - void
     */

   

    /** ********************* Audit END ******************************* */

    /** ********************** Login START ****************************** */

   

    
    /// Changed for CRs ////////////////////////////////////////////
    
   
    
    /// Changed for CRs ////////////////////////////////////////////
    /** ********************** Login END ****************************** */

    /**
     * ******************* Language Manager START ******************************
     */

    /**
     * This method is used to get the error description based on the input
     * branch Number
     * 
     * @return String - error description
     * 
     */

 

    /**
     * ******************** Language Manager END ******************************
     */
    
    
 

}
