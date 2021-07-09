package com.tip.workorder.util;

public class WorkOrderConstants {

    /**
     * Data Source Constants
     */
    public static final String DATASOURCE_CONFIGURATION = "datasource.configuration";


    /**
     * Procedure Constants
     */
    public static final String PROC_FETCH_PIPELINE_LIST = "Wshp_Resrc_Pipeline_List";
    public static final String PROC_FETCH_INSPECTION = "Wshp_get_Inspection";
    public static final String PROC_FETCH_INSPECTION_CHECKLIST = "Wshp_Fetch_Rem_Insp_Items";
    public static final String PROC_FETCH_WP_WO_WOT = "Wshp_Fetch_WP_WO_Task_Details";
    public static final String PROC_FETCH_MAINTAINENCE_INSPECTION_CHECKLIST = "Wshp_Get_Maint_Insp_List";
    public static final String PROC_FETCH_WP_EXTRA_HRS = "Wshp_Fetch_WP_Extra_Hours";
    public static final String PROC_FETCH_WP_RPLAN = "OPSwshp..Wshp_Fetch_Resrc_WP_Details";
    
    public static final String ERROR_CD = "Error_Cd";

    private WorkOrderConstants() {
        /**
         * Private constructor
         */
    }

}
