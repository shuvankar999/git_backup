package com.tip.fetchworkorder.service.impl;

import com.tip.fetchworkorder.model.*;
import com.tip.fetchworkorder.repository.FetchWorkorderRepository;
import com.tip.fetchworkorder.repository.impl.FetchWorkorderRepositoryImpl;
import com.tip.fetchworkorder.service.FetchWorkorderService;
import com.tip.workorder.util.DateUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class FetchWorkorderServiceImpl implements FetchWorkorderService {

    static final Logger logger = LoggerFactory.getLogger(FetchWorkorderRepositoryImpl.class);

    @Autowired
    FetchWorkorderRepository fetchWorkorderRepository;

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public WorkPackResponse getWPackWorderTaskList(BigDecimal workPackNr, int languageId) {

        Map<String, Object> wPackWOrderTaskMap = fetchWorkorderRepository.getWPackWorderTaskList(workPackNr,
                languageId);

        WorkPackResponse workpackResponse = new WorkPackResponse();

        try {
            // Call to return corresponding WPObjectlist

            List<Object> workPackList = (List<Object>) wPackWOrderTaskMap.get("WPObjectlist");
            Iterator workPackListIterator = workPackList.iterator();

            while (workPackListIterator.hasNext()) {

                Map<String, Object> workPackObjectMap = (Map<String, Object>) workPackListIterator.next();
                workpackResponse.setAssetNumber((Integer) workPackObjectMap.get("Asset_Number"));
                workpackResponse.setCustomerReferenceNr((String) workPackObjectMap.get("Customer_Reference_Nr"));
                workpackResponse.setRegistrationNr((String) workPackObjectMap.get("Registration_Nr"));
                workpackResponse.setWorkPackNr((BigDecimal) workPackObjectMap.get("Work_Pack_Nr"));
                workpackResponse.setRequireDoneDate(
                        DateUtility.dateToString((Date) workPackObjectMap.get("Required_Done_Date")));
                workpackResponse.setTotalGuideTime((Double) workPackObjectMap.get("Total_Guide_Time"));
                workpackResponse.setCustomerNr((Integer) workPackObjectMap.get("Customer_Nr"));
                workpackResponse.setCustomerName((String) workPackObjectMap.get("Customer_Name"));
                workpackResponse.setChassisNumber((String) workPackObjectMap.get("Chassis_Number"));
                workpackResponse.setWoCnt((Integer) workPackObjectMap.get("WO_Cnt"));
                workpackResponse.setComments((String) workPackObjectMap.get("Comments"));
                workpackResponse.setSupplierId((Integer) workPackObjectMap.get("Supplier_Id"));
                workpackResponse.setSupplierName((String) workPackObjectMap.get("Supplier_Name"));
                workpackResponse.setDriverWaitingFlag((String) workPackObjectMap.get("Driver_Waiting_Flag"));
                workpackResponse.setStatusId((Integer) workPackObjectMap.get("Status_Id"));
                workpackResponse.setStatus((String) workPackObjectMap.get("Status"));
                workpackResponse.setActualTime((Double) workPackObjectMap.get("Actual_Time"));
                workpackResponse.setAlert((String) workPackObjectMap.get("Alert"));
                workpackResponse.setStopReasonCd((Integer) workPackObjectMap.get("Stop_Reason_Cd"));
                workpackResponse.setStopReasonDesc((String) workPackObjectMap.get("Stop_Reason_Desc"));
                workpackResponse.setRoadWorthy((String) workPackObjectMap.get("Road_Worthy"));
                workpackResponse.setTechnicianComments((String) workPackObjectMap.get("TechnicianComments"));
                workpackResponse.setMotDocDate((String) workPackObjectMap.get("MotDocDate"));
                workpackResponse.setTotalPhotos((Integer) workPackObjectMap.get("TotalPhotos"));


                
                //Call to return unMactchedParts
                

				
                List<Object> partsObjectList = (List<Object>) wPackWOrderTaskMap.get("PartsObjectlist");
                Iterator partsObjectListIterator = partsObjectList.iterator();

                while (partsObjectListIterator.hasNext()) {

                    Parts unMatchedPart = new Parts();

                    Map<String, Object> partsObjectMap = (Map<String, Object>) partsObjectListIterator
                            .next();

                    Integer tempWotNrUnmatchedPart = (Integer) partsObjectMap.get("Work_Order_Task_Nr");
                    if(tempWotNrUnmatchedPart==null){

                        	unMatchedPart.setWorkPackNr((BigDecimal) partsObjectMap.get("Work_Pack_Nr"));
                        	unMatchedPart.setWorkOrderNr((Integer) partsObjectMap.get("Work_Order_Nr"));
                        	unMatchedPart.setWorkOrderTaskNr((Integer) partsObjectMap.get("Work_Order_Task_Nr"));
                        	unMatchedPart.setPartNr((String) partsObjectMap.get("Part_Nr"));
                        	unMatchedPart.setSupplierPartNr((String) partsObjectMap.get("Supplier_Part_Nr"));
                        	unMatchedPart.setOePartNr((String) partsObjectMap.get("OE_Part_Nr"));
                        	unMatchedPart.setTipPartNr((String) partsObjectMap.get("TIP_Part_Nr"));
                        	unMatchedPart.setPartsQty((Integer) partsObjectMap.get("Parts_Qty"));
                        	unMatchedPart.setCostCd((String) partsObjectMap.get("Cost_Cd"));
                        	unMatchedPart.setCostSubCd((String) partsObjectMap.get("Cost_Sub_Cd"));
                        	unMatchedPart.setRepairId((String) partsObjectMap.get("Repair_Id"));
                        	unMatchedPart.setPositionCd((String) partsObjectMap.get("Position_Cd"));
                        	unMatchedPart.setReason((String) partsObjectMap.get("Reason"));
                        	unMatchedPart.setDiscount((BigDecimal) partsObjectMap.get("Discount"));
                        	unMatchedPart.setListPrice((BigDecimal) partsObjectMap.get("List_Price"));
                        	unMatchedPart.setCostPrice((BigDecimal) partsObjectMap.get("Cost_Price"));
                        	unMatchedPart.setPartStatus((String) partsObjectMap.get("Part_Status"));

                        	workpackResponse.getUnMatchedPartList().add(unMatchedPart);
                    }
                }
                
                
                // Call to return corresponding WOObjectlist

                List<Object> workOrderList = (List<Object>) wPackWOrderTaskMap.get("WOObjectlist");
                Iterator workOrderListIterator = workOrderList.iterator();

                while (workOrderListIterator.hasNext()) {

                    WorkOrder workOrder = new WorkOrder();

                    Map<String, Object> workOrderObjectMap = (Map<String, Object>) workOrderListIterator.next();

                    workOrder.setWorkPackNr((BigDecimal) workOrderObjectMap.get("Work_Pack_Nr"));
                    workOrder.setWorkOrderNr((Integer) workOrderObjectMap.get("Work_Order_Nr"));
                    workOrder.setLevel1((String) workOrderObjectMap.get("Level1"));
                    workOrder.setGroupDesc((String) workOrderObjectMap.get("Group_Desc"));
                    workOrder.setWotCnt((Integer) workOrderObjectMap.get("WOT_Cnt"));
                    workOrder.setGuideTime((Double) workOrderObjectMap.get("Guide_Time"));
                    workOrder.setActualTime((Double) workOrderObjectMap.get("Actual_Time"));
                    workOrder.setDiff((Double) workOrderObjectMap.get("Diff"));
                    workOrder.setWorkorderComments((String) workOrderObjectMap.get("Workorder_Comments"));
                    workOrder.setWoStatus((Integer) workOrderObjectMap.get("WO_Status"));
                    workOrder.setWoStatusDesc((String) workOrderObjectMap.get("WO_Status_Desc"));
                    workOrder.setReasonCd((Integer) workOrderObjectMap.get("Reason_Cd"));
                    workOrder.setReason((String) workOrderObjectMap.get("Reason"));
                    workOrder.setValidationStatus((String) workOrderObjectMap.get("Validation_Status"));
                    workOrder.setSupplierId((Integer) workOrderObjectMap.get("Supplier_Id"));
                    workOrder.setSupplierName((String) workOrderObjectMap.get("Supplier_Name"));
                    workOrder.setPaymentVendorId((Integer) workOrderObjectMap.get("Payment_Vendor_Id"));
                    workOrder.setPaymentVendorCurrencyCd((String) workOrderObjectMap.get("Payment_Vendor_Currency_Cd"));
                    workOrder.setPurchaseOrderNr((String) workOrderObjectMap.get("Purchase_Order_Nr"));
                    workOrder.setAlert((String) workOrderObjectMap.get("Alert"));

                    
                    
                    /*Call to return corresponding TechCommentsObjectList*/
                   
                    
                    List<Object> techCommentsObjectList = (List<Object>) wPackWOrderTaskMap.get("TechCommentsObject");
                    Iterator techCommentsObjectListIterator = techCommentsObjectList.iterator();
                    while (techCommentsObjectListIterator.hasNext()) {

                    	TechCommentsObject techCommentsObject = new TechCommentsObject();

                        Map<String, Object> techCommentsObjectMap = (Map<String, Object>) techCommentsObjectListIterator
                                .next();
                        Integer tempWoNr = (Integer) techCommentsObjectMap.get("Work_Order_Nr");
                        if (tempWoNr.equals(workOrder.getWorkOrderNr())) {
                        	
                        	techCommentsObject.setWorkPackNr((BigDecimal) techCommentsObjectMap.get("Work_Pack_Nr"));
                        	techCommentsObject.setWorkOrderNr((Integer) techCommentsObjectMap.get("Work_Order_Nr"));
                        	techCommentsObject
                                    .setWorkOrderTaskNr((Integer) techCommentsObjectMap.get("Work_Order_Task_Nr"));
                        	techCommentsObject.setInspectionType((String) techCommentsObjectMap.get("InspectionType"));
                        	techCommentsObject.setRoadWorthy((String) techCommentsObjectMap.get("RoadWorthy"));
                        	techCommentsObject.setTechnicianComments((String) techCommentsObjectMap.get("Technician_Comments"));
                        	techCommentsObject.setManagerComments((String) techCommentsObjectMap.get("Manager_Comments"));
                        	
                        	workOrder.getTechCommentsObjectList().add(techCommentsObject);
                        }
                        
                    }
                    
                    /*Call to return corresponding WOTObjectlist*/

                    List<Object> workOrderTaskList = (List<Object>) wPackWOrderTaskMap.get("WOTObjectlist");
                    Iterator workOrderTaskListIterator = workOrderTaskList.iterator();
                    while (workOrderTaskListIterator.hasNext()) {

                        WorkOrderTask workOrderTask = new WorkOrderTask();

                        Map<String, Object> workOrderTaskObjectMap = (Map<String, Object>) workOrderTaskListIterator
                                .next();
                        Integer tempWo_Nr = (Integer) workOrderTaskObjectMap.get("Work_Order_Nr");
                        if (tempWo_Nr.equals(workOrder.getWorkOrderNr())) {

                            workOrderTask.setWorkPackNr((BigDecimal) workOrderTaskObjectMap.get("Work_Pack_Nr"));
                            workOrderTask.setWorkOrderNr((Integer) workOrderTaskObjectMap.get("Work_Order_Nr"));
                            workOrderTask
                                    .setWorkOrderTaskNr((Integer) workOrderTaskObjectMap.get("Work_Order_Task_Nr"));
                            workOrderTask.setWotStatusId((Integer) workOrderTaskObjectMap.get("WOT_Status_Id"));
                            workOrderTask.setWotStatusDesc((String) workOrderTaskObjectMap.get("WOT_Status_Desc"));
                            workOrderTask.setLevel1((String) workOrderTaskObjectMap.get("Level1"));
                            workOrderTask.setGroupDesc((String) workOrderTaskObjectMap.get("Group_Desc"));
                            workOrderTask.setLevel2((String) workOrderTaskObjectMap.get("Level2"));
                            workOrderTask.setSubGroup((String) workOrderTaskObjectMap.get("Sub_Group"));
                            workOrderTask.setLevel3((String) workOrderTaskObjectMap.get("Level3"));
                            workOrderTask.setActivity((String) workOrderTaskObjectMap.get("Activity"));
                            workOrderTask
                                    .setFailureCauseDesc((String) workOrderTaskObjectMap.get("Failure_Cause_Desc"));
                            workOrderTask.setFailureCause((String) workOrderTaskObjectMap.get("Failure_Cause"));
                            workOrderTask.setActionCd((String) workOrderTaskObjectMap.get("Action_Cd"));
                            workOrderTask.setAction((String) workOrderTaskObjectMap.get("Action"));
                            workOrderTask.setReasonCd((String) workOrderTaskObjectMap.get("Reason_Cd"));
                            workOrderTask.setReason((String) workOrderTaskObjectMap.get("Reason"));
                            workOrderTask.setPositionId((String) workOrderTaskObjectMap.get("Position_Id"));
                            workOrderTask.setDescription((String) workOrderTaskObjectMap.get("Description"));
                            workOrderTask.setGuideTime((Double) workOrderTaskObjectMap.get("Guide_Time"));
                            workOrderTask.setActualTime((Double) workOrderTaskObjectMap.get("Actual_Time"));
                            workOrderTask.setValidationStatus((String) workOrderTaskObjectMap.get("Validation_Status"));
                            workOrderTask.setAlert((String) workOrderTaskObjectMap.get("Alert"));
                            workOrderTask.setStopReasonCd((Integer) workOrderTaskObjectMap.get("Stop_Reason_Cd"));
                            workOrderTask.setStopReasonDesc((String) workOrderTaskObjectMap.get("Stop_Reason_Desc"));
							workOrderTask.setActualTimeComments((String) workOrderTaskObjectMap.get("Actual_Time_Comments"));
							workOrderTask.setWorkDoneBy((String) workOrderTaskObjectMap.get("WorkDoneBy"));
							
                            // Call to return corresponding PartsObjectlist

                            Iterator matchedPartsObjectListIterator = partsObjectList.iterator();

                            while (matchedPartsObjectListIterator.hasNext()) {

                                Parts matchedParts = new Parts();

                                Map<String, Object> matchedPartsObjectMap = (Map<String, Object>) matchedPartsObjectListIterator
                                        .next();

                                Integer tempWoNrPart = (Integer) matchedPartsObjectMap.get("Work_Order_Nr");
                                Integer tempWotNrPart = (Integer) matchedPartsObjectMap.get("Work_Order_Task_Nr");
                                if(tempWotNrPart!=null){
                                    if (tempWoNrPart.equals(workOrderTask.getWorkOrderNr())
                                            && tempWotNrPart.equals(workOrderTask.getWorkOrderTaskNr())) {

                                    	matchedParts.setWorkPackNr((BigDecimal) matchedPartsObjectMap.get("Work_Pack_Nr"));
                                    	matchedParts.setWorkOrderNr((Integer) matchedPartsObjectMap.get("Work_Order_Nr"));
                                    	matchedParts.setWorkOrderTaskNr((Integer) matchedPartsObjectMap.get("Work_Order_Task_Nr"));
                                    	matchedParts.setPartNr((String) matchedPartsObjectMap.get("Part_Nr"));
                                    	matchedParts.setSupplierPartNr((String) matchedPartsObjectMap.get("Supplier_Part_Nr"));
                                    	matchedParts.setOePartNr((String) matchedPartsObjectMap.get("OE_Part_Nr"));
                                    	matchedParts.setTipPartNr((String) matchedPartsObjectMap.get("TIP_Part_Nr"));
                                    	matchedParts.setPartsQty((Integer) matchedPartsObjectMap.get("Parts_Qty"));
                                    	matchedParts.setCostCd((String) matchedPartsObjectMap.get("Cost_Cd"));
                                    	matchedParts.setCostSubCd((String) matchedPartsObjectMap.get("Cost_Sub_Cd"));
                                    	matchedParts.setRepairId((String) matchedPartsObjectMap.get("Repair_Id"));
                                    	matchedParts.setPositionCd((String) matchedPartsObjectMap.get("Position_Cd"));
                                    	matchedParts.setReason((String) matchedPartsObjectMap.get("Reason"));
                                    	matchedParts.setDiscount((BigDecimal) matchedPartsObjectMap.get("Discount"));
                                    	matchedParts.setListPrice((BigDecimal) matchedPartsObjectMap.get("List_Price"));
                                    	matchedParts.setCostPrice((BigDecimal) matchedPartsObjectMap.get("Cost_Price"));
                                    	matchedParts.setPartStatus((String) matchedPartsObjectMap.get("Part_Status"));

                                        workOrderTask.getPartsList().add(matchedParts);
                                    }
                                }
                            }

                            // Call to return corresponding AddtimeObjectlist

                            List<Object> addTimeObjectList = (List<Object>) wPackWOrderTaskMap.get("AddtimeObjectlist");
                            Iterator addTimeObjectListIterator = addTimeObjectList.iterator();

                            while (addTimeObjectListIterator.hasNext()) {

                                AddTimeObject addTimeObject = new AddTimeObject();

                                Map<String, Object> addTimeObjectMap = (Map<String, Object>) addTimeObjectListIterator
                                        .next();

                                Integer tempWoNrTime = (Integer) addTimeObjectMap.get("Work_Order_Nr");
                                Integer tempWotNrTime = (Integer) addTimeObjectMap.get("Work_Order_Task_Nr");
                                if (tempWoNrTime.equals(workOrderTask.getWorkOrderNr())
                                        && tempWotNrTime.equals(workOrderTask.getWorkOrderTaskNr())) {

                                    addTimeObject.setWorkPackNr((BigDecimal) addTimeObjectMap.get("Work_Pack_Nr"));
                                    addTimeObject.setWorkOrderNr((Integer) addTimeObjectMap.get("Work_Order_Nr"));
                                    addTimeObject
                                            .setWorkOrderTaskNr((Integer) addTimeObjectMap.get("Work_Order_Task_Nr"));
                                    addTimeObject.setAddTimeReasonCd((Integer) addTimeObjectMap.get("AddTimeReasonCd"));
                                    addTimeObject.setAddTimeReason((String) addTimeObjectMap.get("AddTimeReason"));
                                    addTimeObject.setTimeAdded((Double) addTimeObjectMap.get("TimeAdded"));
                                    addTimeObject.setAddTimeComments((String) addTimeObjectMap.get("AddTimeComments"));

                                    workOrderTask.getAddtimeList().add(addTimeObject);
                                }
                            }

                            workOrder.getWorkOrderTaskList().add(workOrderTask);
                        }
                    }

                    workpackResponse.getWorkOrderList().add(workOrder);

                }

                // Call to return corresponding TechObjectlist

                List<Object> technicianObjectList = (List<Object>) wPackWOrderTaskMap.get("TechObjectlist");
                Iterator technicianObjListIterator = technicianObjectList.iterator();

                while (technicianObjListIterator.hasNext()) {

                    Technician technician = new Technician();

                    Map<String, Object> technicianObjectMap = (Map<String, Object>) technicianObjListIterator.next();

                    technician.setTechnicianId((String) technicianObjectMap.get("Technician_Id"));
                    technician.setTechnicianName((String) technicianObjectMap.get("Technician_Name"));
                    technician.setPrimaryFlag((String) technicianObjectMap.get("Primary_Flag"));
                    
                    workpackResponse.getTechnicianList().add(technician);

                }

            }
        } catch (Exception e) {
            StackTraceElement stackTraceElement = e.getStackTrace()[0];
            logger.error("An error occurrs while mapping the data with response class" + e);
            logger.error("At line:	" + stackTraceElement.getClassName() + ":" + stackTraceElement.getLineNumber());
        }
        return workpackResponse;
    }

}
