package com.tip.fetchworkorder.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import com.tip.fetchworkorder.repository.FetchWorkorderRepository;

public class FetchWorkorderServiceImplTest {

    FetchWorkorderServiceImpl fetchWorkorderServiceImpl;

    @Mock
    FetchWorkorderRepository fetchWorkorderRepository;

    @Before
    public void beforesetup() {
        fetchWorkorderServiceImpl = new FetchWorkorderServiceImpl();
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(fetchWorkorderServiceImpl, "fetchWorkorderRepository", fetchWorkorderRepository);
    }

    @Test
    public void getWPackWorderTaskList() {
        //long workpack_nr = 2l;
        BigDecimal workpack_nr = BigDecimal.TEN;
        int language_id = 1;

        Map<String, Object> wPackWOrderTaskMap = new HashMap<String, Object>(0);
        List<Object> workPackList = new ArrayList<Object>(0);
        Map<String, Object> workPackObjectMap = new HashMap<String, Object>(0);
        workPackObjectMap.put("Asset_Number", 12);
        workPackObjectMap.put("Customer_Reference_Nr", "115561");
        workPackObjectMap.put("Registration_Nr", "56968684");
        workPackObjectMap.put("Work_Pack_Nr", BigDecimal.TEN);
        java.sql.Timestamp sqlDt = new Timestamp(46556456);
        workPackObjectMap.put("Require_Done_Date", sqlDt);
        workPackObjectMap.put("Total_Guide_Time", 2.6);
        workPackObjectMap.put("Customer_Nr", 24);
        workPackObjectMap.put("customer_name", "Rahul");
        workPackObjectMap.put("Chassis_Number", "251565");
        workPackObjectMap.put("WO_Cnt", 2);
        workPackObjectMap.put("Comments", "Comments");
        workPackObjectMap.put("Supplier_Id", 1514);
        workPackObjectMap.put("Supplier_Name", "sup name");
        workPackObjectMap.put("Driver_Waiting_Flag", "Y");
        workPackObjectMap.put("Status_Id", 1);
        workPackObjectMap.put("Status", "Logged In");
        workPackObjectMap.put("Actual_Time", 4.2);
        workPackObjectMap.put("Alert", "bnbj");
        workPackObjectMap.put("Stop_Reason_Cd", 14);
        workPackObjectMap.put("Stop_Reason_Desc", "Stop Reason desc");
        workPackObjectMap.put("Road_Worthy", "Yes");
        workPackObjectMap.put("TechnicianComments", "Yes");
        workPackObjectMap.put("MotDocDate", "Yes");
        workPackList.add(workPackObjectMap);

        wPackWOrderTaskMap.put("WPObjectlist", workPackList);

        List<Object> workOrderList = new ArrayList<Object>(0);
        Map<String, Object> workOrderObjectMap = new HashMap<String, Object>(0);
        workOrderObjectMap.put("Work_Pack_Nr", BigDecimal.TEN);
        workOrderObjectMap.put("Work_Order_Nr", 9198);
        workOrderObjectMap.put("Level1", "15");
        workOrderObjectMap.put("Group_Desc", "Y");
        workOrderObjectMap.put("WOT_Cnt", 4);
        workOrderObjectMap.put("Guide_Time", 2.6);
        workOrderObjectMap.put("Actual_Time", 2.4);
        workOrderObjectMap.put("Diff", 0.2);
        workOrderObjectMap.put("Workorder_Comments", "wrk comments");
        workOrderObjectMap.put("WO_Status", 2);
        workOrderObjectMap.put("WO_Status_Desc", "status desc");
        workOrderObjectMap.put("Reason_Cd", 14);
        workOrderObjectMap.put("Reason", "Reason");
        workOrderObjectMap.put("Validation_Status", "Y");
        workOrderObjectMap.put("Supplier_Id", 126156);
        workOrderObjectMap.put("Supplier_Name", "sup name");
        workOrderObjectMap.put("Payment_Vendor_Id", 442);
        workOrderObjectMap.put("Payment_Vendor_Currency_Cd", "cd");
        workOrderObjectMap.put("Purchase_Order_Nr", "5156");
        workOrderObjectMap.put("Alert", "Alert");
        workOrderList.add(workOrderObjectMap);

        wPackWOrderTaskMap.put("WOObjectlist", workOrderList);

        List<Object> workOrderTaskList = new ArrayList<Object>(0);
        Map<String, Object> workOrderTaskObjectMap = new HashMap<String, Object>(0);
        workOrderTaskObjectMap.put("Work_Pack_Nr", BigDecimal.TEN);
        workOrderTaskObjectMap.put("Work_Order_Nr", 9198);
        workOrderTaskObjectMap.put("Work_Order_Task_Nr", 1561);
        workOrderTaskObjectMap.put("WOT_Status_Id", 15);
        workOrderTaskObjectMap.put("WOT_Status_Desc", "status desc");
        workOrderTaskObjectMap.put("Level1", "Y");
        workOrderTaskObjectMap.put("Group_Desc", "grp desc");
        workOrderTaskObjectMap.put("Level2", "N");
        workOrderTaskObjectMap.put("Sub_Group", "dfnde");
        workOrderTaskObjectMap.put("Level3", "Y");
        workOrderTaskObjectMap.put("Activity", "Activity");
        workOrderTaskObjectMap.put("Failure_Cause_Desc", "Failure_Cause_Desc");
        workOrderTaskObjectMap.put("Failure", "Failure");
        workOrderTaskObjectMap.put("Action_Cd", "515");
        workOrderTaskObjectMap.put("Action", "Action");
        workOrderTaskObjectMap.put("Reason_Cd", "15");
        workOrderTaskObjectMap.put("Reason", "Reason");
        workOrderTaskObjectMap.put("Position_Id", "16");
        workOrderTaskObjectMap.put("Description", "Description");
        workOrderTaskObjectMap.put("Guide_Time", 2.6);
        workOrderTaskObjectMap.put("Actual_Time", 2.4);
        workOrderTaskObjectMap.put("Validation_Status", "Y");
        workOrderTaskObjectMap.put("Alert", "Alert");
        workOrderTaskList.add(workOrderTaskObjectMap);

        Map<String, Object> workOrderTaskObjectMap1 = new HashMap<String, Object>(0);
        workOrderTaskObjectMap1.put("Work_Pack_Nr", BigDecimal.TEN);
        workOrderTaskObjectMap1.put("Work_Order_Nr", 1515);
        workOrderTaskObjectMap1.put("Work_Order_Task_Nr", 1561);
        workOrderTaskObjectMap1.put("WOT_Status_Id", 15);
        workOrderTaskObjectMap1.put("WOT_Status_Desc", "status desc");
        workOrderTaskObjectMap1.put("Level1", "Y");
        workOrderTaskObjectMap1.put("Group_Desc", "grp desc");
        workOrderTaskObjectMap1.put("Level2", "N");
        workOrderTaskObjectMap1.put("Sub_Group", "dfnde");
        workOrderTaskObjectMap1.put("Level3", "Y");
        workOrderTaskObjectMap1.put("Activity", "Activity");
        workOrderTaskObjectMap1.put("Failure_Cause_Desc", "Failure_Cause_Desc");
        workOrderTaskObjectMap1.put("Failure", "Failure");
        workOrderTaskObjectMap1.put("Action_Cd", "515");
        workOrderTaskObjectMap1.put("Action", "Action");
        workOrderTaskObjectMap1.put("Reason_Cd", "15");
        workOrderTaskObjectMap1.put("Reason", "Reason");
        workOrderTaskObjectMap1.put("Position_Id", "16");
        workOrderTaskObjectMap1.put("Description", "Description");
        workOrderTaskObjectMap1.put("Guide_Time", 2.6);
        workOrderTaskObjectMap1.put("Actual_Time", 2.4);
        workOrderTaskObjectMap1.put("Validation_Status", "Y");
        workOrderTaskObjectMap1.put("Alert", "Alert");
        workOrderTaskList.add(workOrderTaskObjectMap1);

        wPackWOrderTaskMap.put("WOTObjectlist", workOrderTaskList);

        List<Object> partsObjectList = new ArrayList<Object>(0);
        Map<String, Object> partsObjectMap = new HashMap<String, Object>(0);
        partsObjectMap.put("Work_Pack_Nr", BigDecimal.TEN);
        partsObjectMap.put("Work_Order_Nr", 9198);
        partsObjectMap.put("Work_Order_Task_Nr", 1561);
        partsObjectMap.put("Part_Nr", "15");
        partsObjectMap.put("Supplier_Part_Nr", "648");
        partsObjectMap.put("OE_Part_Nr", "156");
        partsObjectMap.put("TIP_Part_Nr", "325");
        partsObjectMap.put("Parts_Qty", 3);
        partsObjectMap.put("Cost_Cd", "215");
        partsObjectMap.put("Cost_Sub_Cd", "1546");
        partsObjectMap.put("Repair_Id", "641");
        partsObjectMap.put("Position_Cd", "14");
        partsObjectMap.put("Reason", "Reason");
        partsObjectMap.put("Discount", BigDecimal.TEN);
        partsObjectMap.put("List_Price", BigDecimal.TEN);
        partsObjectMap.put("Cost_Price", BigDecimal.TEN);
        partsObjectMap.put("Part_Status", "Y");
        partsObjectList.add(partsObjectMap);

        Map<String, Object> partsObjectMap1 = new HashMap<String, Object>(0);
        partsObjectMap1.put("Work_Pack_Nr", BigDecimal.TEN);
        partsObjectMap1.put("Work_Order_Nr", 76415);
        partsObjectMap1.put("Work_Order_Task_Nr", null);
        partsObjectMap1.put("Part_Nr", "15");
        partsObjectMap1.put("Supplier_Part_Nr", "648");
        partsObjectMap1.put("OE_Part_Nr", "156");
        partsObjectMap1.put("TIP_Part_Nr", "325");
        partsObjectMap1.put("Parts_Qty", 3);
        partsObjectMap1.put("Cost_Cd", "215");
        partsObjectMap1.put("Cost_Sub_Cd", "1546");
        partsObjectMap1.put("Repair_Id", "641");
        partsObjectMap1.put("Position_Cd", "14");
        partsObjectMap1.put("Reason", "Reason");
        partsObjectMap1.put("Discount", BigDecimal.TEN);
        partsObjectMap1.put("List_Price", BigDecimal.TEN);
        partsObjectMap1.put("Cost_Price", BigDecimal.TEN);
        partsObjectMap1.put("Part_Status", "Y");
        partsObjectList.add(partsObjectMap1);

        Map<String, Object> partsObjectMap2 = new HashMap<String, Object>(0);
        partsObjectMap2.put("Work_Pack_Nr", BigDecimal.TEN);
        partsObjectMap2.put("Work_Order_Nr", 9198);
        partsObjectMap2.put("Work_Order_Task_Nr", null);
        partsObjectMap2.put("Part_Nr", "15");
        partsObjectMap2.put("Supplier_Part_Nr", "648");
        partsObjectMap2.put("OE_Part_Nr", "156");
        partsObjectMap2.put("TIP_Part_Nr", "325");
        partsObjectMap2.put("Parts_Qty", 3);
        partsObjectMap2.put("Cost_Cd", "215");
        partsObjectMap2.put("Cost_Sub_Cd", "1546");
        partsObjectMap2.put("Repair_Id", "641");
        partsObjectMap2.put("Position_Cd", "14");
        partsObjectMap2.put("Reason", "Reason");
        partsObjectMap2.put("Discount", BigDecimal.TEN);
        partsObjectMap2.put("List_Price", BigDecimal.TEN);
        partsObjectMap2.put("Cost_Price", BigDecimal.TEN);
        partsObjectMap2.put("Part_Status", "Y");
        partsObjectList.add(partsObjectMap2);

        Map<String, Object> partsObjectMap3 = new HashMap<String, Object>(0);
        partsObjectMap3.put("Work_Pack_Nr", BigDecimal.TEN);
        partsObjectMap3.put("Work_Order_Nr", 12378);
        partsObjectMap3.put("Work_Order_Task_Nr", 4456);
        partsObjectMap3.put("Part_Nr", "15");
        partsObjectMap3.put("Supplier_Part_Nr", "648");
        partsObjectMap3.put("OE_Part_Nr", "156");
        partsObjectMap3.put("TIP_Part_Nr", "325");
        partsObjectMap3.put("Parts_Qty", 3);
        partsObjectMap3.put("Cost_Cd", "215");
        partsObjectMap3.put("Cost_Sub_Cd", "1546");
        partsObjectMap3.put("Repair_Id", "641");
        partsObjectMap3.put("Position_Cd", "14");
        partsObjectMap3.put("Reason", "Reason");
        partsObjectMap3.put("Discount", BigDecimal.TEN);
        partsObjectMap3.put("List_Price", BigDecimal.TEN);
        partsObjectMap3.put("Cost_Price", BigDecimal.TEN);
        partsObjectMap3.put("Part_Status", "Y");
        partsObjectList.add(partsObjectMap3);

        wPackWOrderTaskMap.put("PartsObjectlist", partsObjectList);

        List<Object> technicianObjectList = new ArrayList<Object>(0);
        Map<String, Object> technicianObjectMap = new HashMap<String, Object>(0);
        technicianObjectMap.put("Technician_Id", "551515");
        technicianObjectMap.put("Technician_Name", "Rahul");
        technicianObjectMap.put("Level1", "15");
        technicianObjectList.add(technicianObjectMap);

        wPackWOrderTaskMap.put("TechObjectlist", technicianObjectList);
        
        List<Object> techCommentsObjectList = new ArrayList<Object>(0);
        Map<String, Object> techCommentsObjectMap = new HashMap<String, Object>(0);
        techCommentsObjectMap.put("Work_Order_Nr", 9198);
        techCommentsObjectMap.put("Work_Pack_Nr", BigDecimal.TEN);
        techCommentsObjectMap.put("Work_Order_Task_Nr", 1564);
        techCommentsObjectMap.put("InspectionType", "A");
        techCommentsObjectMap.put("RoadWorthy", "Y");
        techCommentsObjectMap.put("Technician_Comments", "comments");
        techCommentsObjectMap.put("Manager_Comments", "comments");
        techCommentsObjectList.add(techCommentsObjectMap);
        
        Map<String, Object> techCommentsObjectMap1 = new HashMap<String, Object>(0);
        techCommentsObjectMap1.put("Work_Order_Nr", 14154);
        techCommentsObjectMap1.put("Work_Pack_Nr", BigDecimal.TEN);
        techCommentsObjectMap1.put("Work_Order_Task_Nr", 1564);
        techCommentsObjectMap1.put("InspectionType", "A");
        techCommentsObjectMap1.put("RoadWorthy", "Y");
        techCommentsObjectMap1.put("Technician_Comments", "comments");
        techCommentsObjectMap1.put("Manager_Comments", "comments");
        techCommentsObjectList.add(techCommentsObjectMap1);
        wPackWOrderTaskMap.put("TechCommentsObject", techCommentsObjectList);
        
        List<Object> addTimeObjectList = new ArrayList<Object>(0);
        Map<String, Object> addTimeObjectMap =  new HashMap<String, Object>(0);
        addTimeObjectMap.put("Work_Order_Nr", 9198);
        addTimeObjectMap.put("Work_Order_Task_Nr", 1561);
        addTimeObjectMap.put("Work_Pack_Nr", BigDecimal.TEN);
        addTimeObjectMap.put("AddTimeReasonCd", 154);
        addTimeObjectMap.put("AddTimeReason", "");
        addTimeObjectMap.put("TimeAdded", 2.0);
        addTimeObjectMap.put("AddTimeComments", "");
        addTimeObjectMap.put("Work_Order_Task_Nr", 1561);
        addTimeObjectMap.put("Work_Order_Task_Nr", 1561);
        addTimeObjectList.add(addTimeObjectMap);
        
        Map<String, Object> addTimeObjectMap1 =  new HashMap<String, Object>(0);
        addTimeObjectMap1.put("Work_Order_Nr", 9198);
        addTimeObjectMap1.put("Work_Order_Task_Nr", 786);
        addTimeObjectMap1.put("Work_Pack_Nr", BigDecimal.TEN);
        addTimeObjectMap1.put("AddTimeReasonCd", 154);
        addTimeObjectMap1.put("AddTimeReason", "");
        addTimeObjectMap1.put("TimeAdded", 2.0);
        addTimeObjectMap1.put("AddTimeComments", "");
        addTimeObjectMap1.put("Work_Order_Task_Nr", 1561);
        addTimeObjectMap1.put("Work_Order_Task_Nr", 1561);
        addTimeObjectList.add(addTimeObjectMap1);
        
        Map<String, Object> addTimeObjectMap2 =  new HashMap<String, Object>(0);
        addTimeObjectMap2.put("Work_Order_Nr", 556);
        addTimeObjectMap2.put("Work_Order_Task_Nr", 1561);
        addTimeObjectMap2.put("Work_Pack_Nr", BigDecimal.TEN);
        addTimeObjectMap2.put("AddTimeReasonCd", 154);
        addTimeObjectMap2.put("AddTimeReason", "");
        addTimeObjectMap2.put("TimeAdded", 2.0);
        addTimeObjectMap2.put("AddTimeComments", "");
        addTimeObjectMap2.put("Work_Order_Task_Nr", 1561);
        addTimeObjectMap2.put("Work_Order_Task_Nr", 1561);
        addTimeObjectList.add(addTimeObjectMap2);
        
        Map<String, Object> addTimeObjectMap3 =  new HashMap<String, Object>(0);
        addTimeObjectMap3.put("Work_Order_Nr", 6456);
        addTimeObjectMap3.put("Work_Order_Task_Nr", 8486);
        addTimeObjectMap3.put("Work_Pack_Nr", BigDecimal.TEN);
        addTimeObjectMap3.put("AddTimeReasonCd", 154);
        addTimeObjectMap3.put("AddTimeReason", "");
        addTimeObjectMap3.put("TimeAdded", 2.0);
        addTimeObjectMap3.put("AddTimeComments", "");
        addTimeObjectMap3.put("Work_Order_Task_Nr", 1561);
        addTimeObjectMap3.put("Work_Order_Task_Nr", 1561);
        addTimeObjectList.add(addTimeObjectMap3);
        wPackWOrderTaskMap.put("AddtimeObjectlist", addTimeObjectList);       
        Mockito.when(fetchWorkorderRepository.getWPackWorderTaskList(workpack_nr, language_id)).thenReturn(wPackWOrderTaskMap);
        fetchWorkorderServiceImpl.getWPackWorderTaskList(workpack_nr, language_id);
        wPackWOrderTaskMap.remove("WPObjectlist");
        Mockito.when(fetchWorkorderRepository.getWPackWorderTaskList(workpack_nr, language_id)).thenReturn(wPackWOrderTaskMap);
        fetchWorkorderServiceImpl.getWPackWorderTaskList(workpack_nr, language_id);
    }
}
