package com.tip.asset.util;

import java.util.Arrays;
import java.util.List;

public class AssetConstants {

	/**
	 * Data Source Constants
	 */
	public static final String DATASOURCE_CONFIGURATION = "datasource.configuration";

	/**
	 * Procedure Constants
	 */
	public static final String PROC_FETCH_EQUIP_DETAILS = "OPSeqsp..ES_fetch_equipment_details";
	public static final String PROC_FETCH_REFRESH_EQUIP_DETAILS = "OPSeqsp..ES_fetch_equip_details_temp";
	public static final String PROC_FETCH_ALL_PROCEDURES = "TIPism..ES_mds_key_procs";
	public static final String PROC_FETCH_EQUIPMENT_DETAILS = "Wshp_Fetch_Equipment_Details";
	public static final String ERROR_CD = "Error_Cd";
	public static final String PROC_FETCH_ASSET_IMAGE_REMOTE_LOCATION = "OPSwshp..Get_Image_Location";
	public static final String PROC_FETCH_ASSET_IMAGES = "OPSeqsp..ES_fetch_equipment_images_docs";
	public static final String PROC_FETCH_FILTER_DETAILS = "OPSeps..ES_fetch_filters";
	public static final String PROC_FETCH_EQUIP_SEARCH_DEFAULT = "OPSeqsp..ES_equipment_search_default";
	public static final String PROC_SAVE_FILTER_FORM_DETAILS = "OPSeps..ES_save_profile_filters";
	public static final String PROC_SAVE_FIELD_LABEL = "OPSeps..ES_save_profile_column_filters";
	
	public static final String STANDARD_IMAGES = "Standard";
	public static final String ADDITIONAL_IMAGES = "Additional";
	public static final String TIP_ASSET_TYPE = "TIPAssets";
	public static final String ERROR_CODE = "SUCCESS";
	
	public static final String FILTER_AND_CONDITION = "AND";
	public static final String FILTER_OR_CONDITION = "OR";
	public static final String FILTER_EQUALS_OPERATION = "equals";
	public static final String FILTER_LIKE_OPERATION = "like";
	public static final String FILTER_RANGE_OPERATION = "range";
	public static final String FILTER_RANGE_OPERATION_SPECS = "rangespecs";
	public static final String FILTER_EQUALS_OPERATION_SPECS = "equalsspecs";
	public static final String FILTER_LIKE_OPERATION_SPECS = "likespecs";
	public static final String FILTER_MULTI_FILTER_OPERATION = "multiFilter";
	public static final String FILTER_SEARCH_TEXT_OPERATION = "searchtext";

	public static final String QUERY_TO_FETCH_TIP_ASSET_IMAGES = "select Image_Group as ImageGroup, a.Image_Type as ImageType,"
			+" c.Text as ImageLable,Unit_Nr as AssetNr, \"TIP\" as AssetType,Picture as Picture"
			+ " from OPSimg..Remarketing_Unit_Images a, OPSimg..Asset_Image_Types b,OPSals..Doc_Text c"
			+ " where Unit_Nr = ? and a.Image_Type *= b.Image_Type_Cd and b.Image_Type_Cd *= c.Text_Cd and c.Entity_Cd = 'IMAGE_TYPES' "
			+ " and c.Language_Id = ?";
	
	public static final String QUERY_TO_FETCH_TP_ASSET_IMAGES = "select Image_Group as ImageGroup, a.Image_Type as ImageType,"
			+" c.Text as ImageLable,TPU_Asset_Nr as AssetNr, \"TIP\" as AssetType,Picture as Picture" 
			+" from OPSwshp..Wshp_TPU_Asset_Images a, OPSimg..Asset_Image_Types b,OPSals..Doc_Text c"
			+" where TPU_Asset_Nr = ? and a.Image_Type *= b.Image_Type_Cd and b.Image_Type_Cd *= c.Text_Cd and c.Entity_Cd = 'IMAGE_TYPES'" 
			+" and c.Language_Id = ?";

	public static final String PROC_CUSTOMER_FETCH_FILTER = "OPSeps..ES_fetch_Customer_filters ";
	public static final String PROC_CUSTOMER_LIST_FETCH = "OPSwshp..Wshp_Fetch_Paygo_Customer_Data";
	
	public static final List<String> ListOfUnitsCol = Arrays.asList("Unit_Catgrp_Code","Unit_Serial_Nr","Unit_Model_Year","Unit_Operational_Status","Unit_Manufacturer","Catgr_Id","Unit_Category_Cd","Unit_Group_Cd","Unit_Cost_Last_Refrnc","Unit_Aquired_Company","Unit_Aquired_Refrnc","Unit_Customer_Refrnc","Unit_Comment");
	public static final List<String> ListOfUnitsDynamicCol = Arrays.asList("Unit_Last_Park_Loc_Cd","Unit_Status","Unit_Status_Comment");
	public static final String UNITS_TAB = "OPSeqsp..Units";
	public static final String UNITS_DYNAMIC_TAB = "OPSals..Unit_Dynamics";
	public static final String PROC_UNITS_COPY_MULTIPLE_ALL = "OPSeqsp..EB_unit_spec_search_Saves3";

	private AssetConstants() {
	}
}
