package com.tip.estimation.util;

public class EstimationConstant {
	
	
	 /**
     * Procedure Constants
     */
    public static final String PROC_ESTIMATION_UPD_CREATE = "OPSwshp..Estn_Estimation_Update";
    
    public static final String ERROR_CD = "Error_Cd";

	public static final String PROC_ESTIMATION_ADD_TO_PIPE = "Estn_Add_To_Pipeline";
	
	public static final String PROC_ESTIMATION_ADD_CUSTOMER_BUNDLE = "OPSwshp..Estn_Add_Cust_Bundle";
	
	public static final String PROC_ESTIMATION_SAVE_WO_WOT = "OPSwshp..Estn_WO_WOTask_Update";
	
	public static final String PROC_ESTIMATION_SAVE_OTHER_READING = "OPSwshp..Estn_Save_Other_Reading";
	
	public static final String PROC_ESTIMATION_SAVE_TYRE_READING = "OPSwshp..Estn_Save_Tyre_Reading";
	
	public static final String PROC_ESTIMATION_SAVE_BRAKE_READING = "OPSwshp..Estn_Save_Brake_Reading ";
	
	public static final String PROC_ESTIMATION_VOID_WOT = "OPSwshp..Estn_Void_WOTask"; 
	
	public static final String PROC_ESTIMATION_DELETE_PARTS = "OPSwshp..Estn_Delete_Parts";
	
	public static final String PROC_ESTIMATION_SAVE_PARTS_DETAILS = "OPSwshp..Estn_Save_Parts";
	
	
	
	//SaveEnrichScreen
	
	public static final String PROC_ENRICH_SAVE_NOTES_DETAILS = "OPSwshp..Estn_Enrich_Save_Notes";
	public static final String PROC_ENRICH_SAVE_WOWOT_DETAILS = "OPSwshp..Estn_Enrich_WO_WOTask_Update";
	public static final String PROC_ENRICH_SAVE_PARTS_DETAILS = "OPSwshp..Estn_Enrich_Save_Parts";
	public static final String PROC_ENRICH_SAVE_TYRE_SPECS = "OPSwshp..Estn_Enrich_Save_Tyre_Specs";
	public static final String PROC_ENRICH_SAVE_TYRE_SRVCS = "OPSwshp..Estn_Enrich_Save_Tyre_Srvcs";
	public static final String PROC_ENRICH_SAVE_CONSUMABLES = "OPSwshp..Estn_Enrich_Save_Consmbles";
	public static final String PROC_ENRICH_SAVE_BUNDLE_DETAILS = "OPSwshp..Estn_Enrich_Save_Bundle";
	public static final String PROC_ENRICH_SAVE_ADMIN_FEES = "OPSwshp..Estn_Enrich_Save_Fees";
	
	public static final String PROC_ESTIMATION_ADD_TRANSACTION = "OPSwshp..Estn_Add_Auth_Code";

	public static final String PROC_ESTIMATION_SAVE_SUPPL = "OPSwshp..Estn_Save_Supplementary";
	
    
	
	public static final String PROC_CREATE_SUPPL_HEADER = "OPSwshp..Estn_Save_Supplementary_Header";
	public static final String PROC_CREATE_SUPPL_LABOUR = "OPSwshp..Estn_Suppl_WO_WOTask_Update";
	public static final String PROC_CREATE_SUPPL_PARTS = "OPSwshp..Estn_Suppl_Save_Parts";

	
	/** Immediate Invoice rebill procedures */
	public static final String PROC_IMMEDIATE_INVC_SAVE_HEADER = "OPSwshp..Estn_Create_Rebill_Header";
	public static final String PROC_IMMEDIATE_INVC_SAVE_LO = "OPSwshp..Estn_Create_Lbr_Rebill_Lines";
	public static final String PROC_IMMEDIATE_INVC_SAVE_PARTS = "OPSwshp..Estn_Create_Part_Rebill_Lines";
	public static final String PROC_IMMEDIATE_INVC_SAVE_TYRE_SPECS = "OPSwshp..Estn_Create_TyreSpec_Rbl_Lns";
	public static final String PROC_IMMEDIATE_INVC_SAVE_TYRE_SERVICES = "OPSwshp..Estn_Create_TyreServ_Rbl_Lns";
	public static final String PROC_IMMEDIATE_INVC_SAVE_BUNDLE = "OPSwshp..Estn_Create_Bundle_Rbl_Lns";
	public static final String PROC_IMMEDIATE_INVC_SAVE_CONSUMABLES = "OPSwshp..Estn_Create_Consmbls_Rbl_Lns";
	public static final String PROC_IMMEDIATE_INVC_SAVE_REBILL = "OPSwshp..Estn_Work_Order_Rebill_Save";
	public static final String PROC_IMMEDIATE_INVC_GENERATE = "OPSinv..Estn_Paygo_Rebill_Invoicing";

	
    private EstimationConstant() {
        /**
         * Private constructor
         */
    }

}
