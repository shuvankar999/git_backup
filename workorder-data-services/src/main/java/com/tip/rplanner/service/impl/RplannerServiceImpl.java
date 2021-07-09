package com.tip.rplanner.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tip.rplanner.model.AssignedTechnicianObject;
import com.tip.rplanner.model.AssignedWpObject;
import com.tip.rplanner.model.ResourcePlannerRequest;
import com.tip.rplanner.model.ResourcePlannerResponse;
import com.tip.rplanner.model.TechnicianBreakObject;
import com.tip.rplanner.model.TechnicianObject;
import com.tip.rplanner.repository.RplannerRepository;
import com.tip.rplanner.service.RplannerService;
import com.tip.workorder.util.ShortNameUtil;

@Service
@Transactional
public class RplannerServiceImpl implements RplannerService {

	@Autowired
	RplannerRepository rPlannerRepository;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public ResourcePlannerResponse getResourcePlannerDetails(ResourcePlannerRequest resourcePlannerRequest){
		
		ResourcePlannerResponse resourcePlannerResponse = new ResourcePlannerResponse();
		
		Map<String, Object> rplannerResponseMap = rPlannerRepository.getResourcePlannerDetails(resourcePlannerRequest);
		
		/* Create a Tech List
		 * 
		 */
		
       
		
		
		
		/* Set Rplanner Response list 
         * 
         * */
		List<Object> rPlannerList = (List<Object>) rplannerResponseMap.get("Techlist");
        Iterator rPlannerListIterator = rPlannerList.iterator();

        while (rPlannerListIterator.hasNext()) {

        	TechnicianObject technicianObject = new TechnicianObject();
            Map<String, Object> technicianListMap = (Map<String, Object>) rPlannerListIterator.next();
            technicianObject.setTechnicianId((String)technicianListMap.get("Technician_Id"));
            technicianObject.setTechnicianName((String)technicianListMap.get("Technician_Name"));
            if(null!=technicianObject.getTechnicianName()){
            	technicianObject.setTechnicianShortname(ShortNameUtil.getShortName(technicianObject.getTechnicianName()));
    		}
            technicianObject.setStatus((String)technicianListMap.get("Status"));
            technicianObject.setUserRole((String)technicianListMap.get("UserRole"));
            technicianObject.setImage((String)technicianListMap.get("Image"));
            technicianObject.setBranch((String)technicianListMap.get("Branch"));
            if(technicianListMap.get("Language")!=null){
                technicianObject.setLanguage((Integer)technicianListMap.get("Language"));
            }
            technicianObject.setManagerComments((String)technicianListMap.get("ManagerComments"));
            technicianObject.setWpCount((Integer)technicianListMap.get("wpCount"));
            
            /*Set Assigned corresponding workpackList object
             * 
             * 
             * */
            
            List<Object> assignedObjectList = (List<Object>) rplannerResponseMap.get("AssignedObjectlist");
            Iterator assignedObjectListIterator = assignedObjectList.iterator();
            
            while (assignedObjectListIterator.hasNext()) {
                Map<String, Object> assignedWpMap = (Map<String, Object>) assignedObjectListIterator.next();
                String tempTechId = (String)assignedWpMap.get("technicianId");
                if(tempTechId.trim().equalsIgnoreCase(technicianObject.getTechnicianId().trim())){

                	AssignedWpObject assignedWpObject = new AssignedWpObject();
                	
                	assignedWpObject.setWorkPackNr((BigDecimal)assignedWpMap.get("workPackNr"));
                    assignedWpObject.setTechnicianId((String)assignedWpMap.get("technicianId"));
                    assignedWpObject.setGroups((String)assignedWpMap.get("groups"));
                    assignedWpObject.setAssetNumber((String)assignedWpMap.get("assetNumber"));
                    assignedWpObject.setCustomerRefNr((String)assignedWpMap.get("customerRefNr"));
                    assignedWpObject.setRegistrationNr((String)assignedWpMap.get("registrationNr"));
                    assignedWpObject.setRequiredDoneDate((String)assignedWpMap.get("requiredDoneDate"));
                    if(assignedWpMap.get("totalTargetTime")!=null){
                    	assignedWpObject.setTotalTargetTime((Double)assignedWpMap.get("totalTargetTime"));
                    }
                    if(assignedWpMap.get("customerNr")!=null){
                    	assignedWpObject.setCustomerNr((Integer)assignedWpMap.get("customerNr"));
                    }
                    assignedWpObject.setCustomerName((String)assignedWpMap.get("customerName"));
                    assignedWpObject.setChassisNumber((String)assignedWpMap.get("chassisNumber"));
                    if(assignedWpMap.get("workOrderCnt")!=null){
                    	assignedWpObject.setWorkOrderCnt((Integer)assignedWpMap.get("workOrderCnt"));
                    }
                    assignedWpObject.setComments((String)assignedWpMap.get("Comments"));
                    if(assignedWpMap.get("supplierId")!=null){
                    	assignedWpObject.setSupplierId((Integer)assignedWpMap.get("supplierId"));
                    }
                    
                    assignedWpObject.setDriverWaiting((String)assignedWpMap.get("driverWaiting"));
                    if(assignedWpMap.get("workPackStatusId")!=null){
                    	assignedWpObject.setWorkPackStatusId((Integer)assignedWpMap.get("workPackStatusId"));
                    }
                    assignedWpObject.setWorkPackStatus((String)assignedWpMap.get("workPackStatus"));
                    assignedWpObject.setAlert((String)assignedWpMap.get("alert"));
                    assignedWpObject.setJobStartTime((String)assignedWpMap.get("jobStartTime"));
                    assignedWpObject.setJobEndTime((String)assignedWpMap.get("jobEndTime"));
                    if(assignedWpMap.get("extraTime")!=null){
                    	assignedWpObject.setExtraTime((Double)assignedWpMap.get("extraTime"));
                    }
                    assignedWpObject.setAssignedTime((String)assignedWpMap.get("assignedTime"));
                    assignedWpObject.setPrimarytechnician((String)assignedWpMap.get("primarytechnician"));
                    if(assignedWpMap.get("assignWPPriority")!=null){
                    	assignedWpObject.setAssignWPPriority((Integer)assignedWpMap.get("assignWPPriority"));
                    }
                    assignedWpObject.setAddTimeFlag((String)assignedWpMap.get("addTimeFlag"));
                    if(assignedWpMap.get("timeAdded")!=null){
                    	assignedWpObject.setTimeAdded((Double)assignedWpMap.get("timeAdded"));
                    }
                    assignedWpObject.setExtraTimeFlag((String)assignedWpMap.get("extraTimeFlag"));
                    assignedWpObject.setStatus((String)assignedWpMap.get("status"));
                    
                    
                    /*Assigned corresponding techList object
                     * 
                     * 
                     * */
                    
                    if("Y".equalsIgnoreCase(assignedWpObject.getGroups())){

                    	List<Object> assignedObjWpNoList = (List<Object>) rplannerResponseMap.get("AssignedObjectlist");
                        Iterator assignedObjWpNoListIterator = assignedObjWpNoList.iterator();

                        while (assignedObjWpNoListIterator.hasNext()) {
                        	Map<String, Object> assignedWpNoMap = (Map<String, Object>) assignedObjWpNoListIterator.next();
                        	BigDecimal tempAssignedWpNo = (BigDecimal)assignedWpNoMap.get("workPackNr");
                        	String tempAssWTechId = (String)assignedWpNoMap.get("technicianId");
                        	if(assignedWpObject.getWorkPackNr().equals(tempAssignedWpNo) && !assignedWpObject.getTechnicianId().trim().equalsIgnoreCase(tempAssWTechId.trim())){
                        		
                        		List<Object> tempTechList = (List<Object>) rplannerResponseMap.get("Techlist");
                                Iterator tempTechListIterator = tempTechList.iterator();

                                while (tempTechListIterator.hasNext()) {
                                	
                                    AssignedTechnicianObject assignedTechnicianObject = new AssignedTechnicianObject();
                                	Map<String, Object> assgTechListMap = (Map<String, Object>) tempTechListIterator.next();
                                	String tempTId = (String)assgTechListMap.get("Technician_Id");
                                	if(tempTId.trim().equalsIgnoreCase(tempAssWTechId.trim())){
                                		assignedTechnicianObject.setTechnicianId((String)assgTechListMap.get("Technician_Id"));
                                		assignedTechnicianObject.setTechnicianName((String)assgTechListMap.get("Technician_Name"));
                                		if(null!=assignedTechnicianObject.getTechnicianName()){
                                			assignedTechnicianObject.setTechnicianShortname(ShortNameUtil.getShortName(assignedTechnicianObject.getTechnicianName()));
                                		}
                                		
                                		assignedTechnicianObject.setStatus((String)assgTechListMap.get("Status"));
                                		assignedTechnicianObject.setUserRole((String)assgTechListMap.get("UserRole"));
                                		assignedTechnicianObject.setImage((String)assgTechListMap.get("Image"));
                                		assignedTechnicianObject.setBranch((String)assgTechListMap.get("Branch"));
                                        if(assgTechListMap.get("Language")!=null){
                                        	assignedTechnicianObject.setLanguage((Integer)assgTechListMap.get("Language"));
                                        }
                                        assignedTechnicianObject.setManagerComments((String)assgTechListMap.get("ManagerComments"));
                                        assignedTechnicianObject.setWpCount((Integer)assgTechListMap.get("wpCount"));
                                        
                                		assignedWpObject.getAssignedTechnicianList().add(assignedTechnicianObject);
                                	}
                                }
                        	}
                        }
                    }else{
                    	assignedWpObject.getAssignedTechnicianList().add(null);
                    }
                    
                    technicianObject.getAssignedWpList().add(assignedWpObject);
                }
            }
            

            /* Assigned corresponding break details
             * 
             * */
            
            
            List<Object> breakDetailsList = (List<Object>) rplannerResponseMap.get("Techbreakdetails");
            Iterator breakDetailsListIterator = breakDetailsList.iterator();

            while (breakDetailsListIterator.hasNext()) {

            	TechnicianBreakObject technicianBreakObject = new TechnicianBreakObject();
                Map<String, Object> breakDetailsMap = (Map<String, Object>) breakDetailsListIterator.next();
                String tempTechIdBreakDetail = (String)breakDetailsMap.get("technicianId");
                if(!tempTechIdBreakDetail.isEmpty() && tempTechIdBreakDetail.trim().equalsIgnoreCase(technicianObject.getTechnicianId().trim())){
                	
                	technicianBreakObject.setTechnicianId(tempTechIdBreakDetail);
                	technicianBreakObject.setBreakType((String)breakDetailsMap.get("breakType"));
                	technicianBreakObject.setBreakStartTime((String)breakDetailsMap.get("breakStartTime"));
                	technicianBreakObject.setBreakEndTime((String)breakDetailsMap.get("breakEndTime"));
                	
                	technicianObject.getBreakDetailsList().add(technicianBreakObject);
                }
            }
            resourcePlannerResponse.getTechnicianList().add(technicianObject);
        }

		return resourcePlannerResponse;
	}
}
