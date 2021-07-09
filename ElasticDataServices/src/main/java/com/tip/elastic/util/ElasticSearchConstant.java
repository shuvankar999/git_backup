package com.tip.elastic.util;

public class ElasticSearchConstant {

	public static final String ENTITY_TYPE_ASSET = "Asset";
	public static final String ENTITY_TYPE_UNIT = "Unit";
	public static final String ENTITY_TYPE_CUSTOMER = "Customer";
	public static final String ENTITY_TYPE_ESTIMATION = "Estimation";
	public static final String ENTITY_TYPE_EQUIP = "Equipment";
	public static final String ENTITY_TYPE_SUPPLIER = "Supplier";
	
	public static final String ASSET_SCREEN_SEARCH = "AssetSearch";
	public static final String UNIT_SCREEN_SEARCH = "UnitSearch";
	public static final String CUSTOMER_SCREEN_SEARCH = "CustomerSearch";
	public static final String ESTIMATION_SCREEN_SEARCH = "EstimationSearch";
	public static final String ASSET_DASHBORAD_ON_BASIS_OF_BRANCH_ID = "AssetDashboard";
	public static final String SCREEN_KEY_COUNT = "Count";
	public static final String PROC_CUSTOMER_SEARCH_DEFAULT = "SISsfa..ES_customer_search_default";
	
	public static final String PROC_SAVE_EQUIP_DYNAMIC = "OPSeqsp..ES_ws_save_equip_dynamic_tab"; 
	public static final String PROC_SAVE_EQUIP_OTHER= "OPSals..ES_ws_save_equip_dynamic";
	public static final String PROC_SAVE_EQUIP_STATIC= "OPSeqsp..ES_ws_save_equip_static";
	public static final String PROC_SAVE_EQUIP_STATIC_STATUS = "OPSeqsp..ES_ws_save_equip_static_2";
	public static final String ERROR_CODE = "Error_Cd";
	
	
	public static final String ACCESSORIES = "Accessories";
	public static final String CAB = "Cab";
	public static final String ATTRIBUTE = "Attributes";
	
	
	public static final String ACCESSORIES_TAB = "OPSwshp..Wshp_TPU_Asset_Accessories";
	public static final String CAB_TAB = "OPSwshp..Equipment_Cab_Inspections";
	public static final String ATTRIBUTE_TAB = "OPSwshp..Equipment_Attributes";
    
	public static final String EXCEPTION_OCCURRED = "Exception occurred";
    public static final String EXCEPTION_OCCURRED_SAVE_FILTER = "Exception occurred while saving filter preferences";
	public static final String FILTER_AND_CONDITION = "AND";
	public static final String FILTER_OR_CONDITION = "OR";
	public static final String FILTER_EQUALS_OPERATION = "equals";
	public static final String FILTER_LIKE_OPERATION = "like";
	public static final String FILTER_RANGE_OPERATION = "range";
	public static final String FILTER_RANGE_OPERATION_SPECS = "rangespecs";
	public static final String FILTER_EQUALS_OPERATION_SPECS = "equalsspecs";
	public static final String FILTER_MULTI_FILTER_OPERATION = "multiFilter";
	public static final String FILTER_SEARCH_TEXT_OPERATION = "searchtext";
	public static final String PROC_CUSTOMER_SAVE_FILTER = "OPSeps..ES_save_customer_filters";
	public static final String PROC_SUPPLIER_FETCH_FILTER = "OPSeps..ES_fetch_Supplier_filters";
	public static final String PROC_SUPPLIER_SAVE_FILTER = "OPSeps..ES_save_supplier_filters";
	
	
	public static final String MODULE_NAME_SUPPLIER = "Supplier";
	public static final String MODULE_NAME_CUSTOMER = "Customer";
	public static final String MODULE_NAME_EQUIPMENT = "Equipment";
	
	public static final String ERROR_CD = "Error_Cd";
	
	
	
	/**
	 * Private constructor to prevent instantiation
	 */
	private ElasticSearchConstant(){}
}
