package com.tip.fetchinspectionreport.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tip.fetchinspectionreport.model.BrakeObject;
import com.tip.fetchinspectionreport.model.FetchAxleRequest;
import com.tip.fetchinspectionreport.model.InspAllHeaders;
import com.tip.fetchinspectionreport.model.InspCheckObject;
import com.tip.fetchinspectionreport.model.InspSignObject;
import com.tip.fetchinspectionreport.model.InspectionReportRequest;
import com.tip.fetchinspectionreport.model.ResponseObject;
import com.tip.fetchinspectionreport.model.TyreObject;
import com.tip.fetchinspectionreport.repository.FetchAxleRepository;
import com.tip.fetchinspectionreport.repository.InspectionReportRepository;
import com.tip.fetchinspectionreport.service.InspectionReportService;
import com.tip.report.util.InspectionReportConstant;
import com.tip.report.util.InspectionReportPdf;
import com.tip.report.util.ReeferInspectionReportPdf;
import com.tip.report.util.SwapbodyInspectionReportPdf;
import com.tip.report.util.TailLiftInspectionReportPdf;
import com.tip.report.util.TankerInspectionReportPdf;

@Service
@Transactional
public class InspectionReportServiceImpl implements InspectionReportService {
	public static final String DEPTH = "depth";
	public static final String PSI = "psi";
	public static final String TRAILER_NR = "TrailerNr";
	public static final String ASSET_TYPE = "assetType";
	public static final String MAINT_DESC = "MaintDesc";
	public static final String LINING_REM_PER = "LiningRemPer";
	public static final String LINING_REM_MM = "LiningRemMM";
	public static final String TYRE_CD = "TyreCd";
	public static final String BRAKE_CD = "BrakeCd";
	static final Logger logger = LoggerFactory.getLogger(InspectionReportServiceImpl.class);

	@Autowired
	InspectionReportRepository inspectionReportRepository;

	@Autowired
	FetchAxleRepository fetchAxleRepository;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ResponseObject getInspectionReport(InspectionReportRequest inspectionReportRequest) {
		ResponseObject responseObj = null;

		Map<String, Object> returnMap = inspectionReportRepository.getInspectionReport(inspectionReportRequest);

		List<Object> inspTranslist = (List<Object>) returnMap.get("InspTranslist");
		List<Object> inspHeaderlist = (List<Object>) returnMap.get("InspHeaderlist");
		List<Object> inspChecklist = (List<Object>) returnMap.get("InspChecklist");
		List<Object> signlist = (List<Object>) returnMap.get("Signlist");
		List<Object> inspDefectlist = (List<Object>) returnMap.get("InspDefectlist");
		List<Object> inspTyrelist = (List<Object>) returnMap.get("InspTyrelist");
		List<Object> inspBrakelist = (List<Object>) returnMap.get("InspBrakelist");
		InspAllHeaders inspAllHeaders = new InspAllHeaders();
		Map<String, String> inspHeaderMap = (Map<String, String>) inspHeaderlist.get(0);
		int noOfAxles = fetNoOfAxles(inspHeaderMap);
		setAllInspHeaders(inspTranslist,inspAllHeaders,inspHeaderMap);

		LinkedHashMap<String, List<InspCheckObject>> inspCheckObjectMapTemp = new LinkedHashMap<>();
		setCheckList(inspChecklist,inspCheckObjectMapTemp);
		
		TyreObject tyreObject = new TyreObject();
		setTyreList(inspTyrelist,tyreObject);
		
		BrakeObject brakeObject = new BrakeObject();
		setBrakeList(inspBrakelist,brakeObject);

		InspSignObject signObject = new InspSignObject();
		setSignObject(signlist,signObject);

		if (inspHeaderMap.get(ASSET_TYPE) != null) {
			if (InspectionReportConstant.REEFER_INSPECTION_REPORT.equalsIgnoreCase(inspHeaderMap.get(ASSET_TYPE))) {
				ReeferInspectionReportPdf inspectionReportPdf = new ReeferInspectionReportPdf();
				responseObj = inspectionReportPdf.createPdf(inspAllHeaders, inspHeaderMap, inspCheckObjectMapTemp,
						inspDefectlist, signObject);
			} else if (InspectionReportConstant.TANKER_INSPECTION_REPORT
					.equalsIgnoreCase(inspHeaderMap.get(ASSET_TYPE))) {
				TankerInspectionReportPdf inspectionReportPdf = new TankerInspectionReportPdf();
				responseObj = inspectionReportPdf.createPdf(inspAllHeaders, inspHeaderMap, inspCheckObjectMapTemp, inspDefectlist, signObject);
			} else if (InspectionReportConstant.TAILLIFT_INSPECTION_REPORT
					.equalsIgnoreCase(inspHeaderMap.get(ASSET_TYPE))) {
				TailLiftInspectionReportPdf inspectionReportPdf = new TailLiftInspectionReportPdf();
				responseObj = inspectionReportPdf.createPdf(inspAllHeaders, inspHeaderMap, inspCheckObjectMapTemp, signObject);
			} else if (InspectionReportConstant.SWAPBODY_INSPECTION_REPORT
					.equalsIgnoreCase(inspHeaderMap.get(ASSET_TYPE))) {
				SwapbodyInspectionReportPdf inspectionReportPdf = new SwapbodyInspectionReportPdf();
				responseObj = inspectionReportPdf.createPdf(inspAllHeaders, inspHeaderMap, inspCheckObjectMapTemp,
						inspDefectlist, signObject);
			}
		} else {
			InspectionReportPdf inspectionReportPdf = new InspectionReportPdf();
			responseObj = inspectionReportPdf.createPdf(inspAllHeaders, inspHeaderMap, inspCheckObjectMapTemp,
					inspDefectlist, tyreObject, brakeObject, signObject, noOfAxles);
		}
		return responseObj;
	}
	
	private String getUnitNo(Map<String, String> inspHeaderMap){
		String unitNr;
		if (inspHeaderMap.get(TRAILER_NR).contains("/")) {
			unitNr = inspHeaderMap.get(TRAILER_NR).split("/")[0];
		} else if (inspHeaderMap.get(TRAILER_NR).contains("\\")) {
			unitNr = inspHeaderMap.get(TRAILER_NR).split("\\")[0];
		} else {
			unitNr = inspHeaderMap.get(TRAILER_NR);
		}
		return unitNr;
	}
	
	private int fetNoOfAxles(Map<String, String> inspHeaderMap){
		int noOfAxles = 0;
		FetchAxleRequest fetchAxleRequest = new FetchAxleRequest();
		if (inspHeaderMap.get(TRAILER_NR) != null && inspHeaderMap.get(ASSET_TYPE) == null) {
			String unitNr = getUnitNo(inspHeaderMap);
			fetchAxleRequest.setUnitNr(Integer.parseInt(unitNr));
			Object axles = fetchAxleRepository.fetchNoOfAxleForAsset(fetchAxleRequest);
			if (axles != null && !"".equals(axles)) {
				noOfAxles = Integer.parseInt(axles.toString());
			}
		}
		return noOfAxles;
	}
	
	private void setSignObject(List<Object> signlist, InspSignObject signObject){
		Iterator signlistIterator = signlist.iterator();
		while (signlistIterator.hasNext()) {
			Map<String, Object> inspSignObjectMap = (Map<String, Object>) signlistIterator.next();
			if ("Inspector".equalsIgnoreCase((String) inspSignObjectMap.get("SignType"))) {
				signObject.setDateInspector(
						inspSignObjectMap.get("Date") != null ? (String) inspSignObjectMap.get("Date") : "");
				signObject.setNameInspector((String) inspSignObjectMap.get("Name"));
				signObject.setTipSignInspector((String) inspSignObjectMap.get("TIPSignature"));
				signObject.setTipInspectorconfirmation((String) inspSignObjectMap.get("Confirmation"));
			} else if ("Signed".equalsIgnoreCase((String) inspSignObjectMap.get("SignType"))) {
				signObject.setDateSigned(
						inspSignObjectMap.get("Date") != null ? (String) inspSignObjectMap.get("Date") : "");
				signObject.setNameSigned((String) inspSignObjectMap.get("Name"));
				signObject.setTipSignSigned((String) inspSignObjectMap.get("TIPSignature"));
				signObject.setTipSignedconfirmation((String) inspSignObjectMap.get("Confirmation"));
			}
		}
	}
	
	private void setAllInspHeaders(List<Object> inspTranslist, InspAllHeaders inspAllHeaders, Map<String, String> inspHeaderMap){
		Iterator inspTranslistIterator = inspTranslist.iterator();
		while (inspTranslistIterator.hasNext()) {
			Map<String, Object> inspTranslistMap = (Map<String, Object>) inspTranslistIterator.next();
			inspAllHeaders.setTrailerNr((String) inspTranslistMap.get("HTrailerNr"));
			inspAllHeaders.setChassis((String) inspTranslistMap.get("HChassis"));
			inspAllHeaders.setJobDate((String) inspTranslistMap.get("HJobdate"));
			inspAllHeaders.setRegistrationNr((String) inspTranslistMap.get("HRegNr"));
			inspAllHeaders.setMileage((String) inspTranslistMap.get("HMileage"));
			inspAllHeaders.setJobSheetNr((String) inspTranslistMap.get("HJobShtNr"));
			inspAllHeaders.setServiceType((String) inspTranslistMap.get("HServiceType"));
			inspAllHeaders.setMotDueDate((String) inspTranslistMap.get("HMOTdate"));
			inspAllHeaders.setOperator((String) inspTranslistMap.get("HOperator"));
			inspAllHeaders.setServicable((String) inspTranslistMap.get("HServiceable"));
			inspAllHeaders.setDefect((String) inspTranslistMap.get("HDefect"));
			inspAllHeaders.setNotApplicable((String) inspTranslistMap.get("HNa"));
			inspAllHeaders.setInspector((String) inspTranslistMap.get("HInspector"));
			inspAllHeaders.setSigned((String) inspTranslistMap.get("HSigned"));
			inspAllHeaders.setDate((String) inspTranslistMap.get("HDate"));
			inspAllHeaders.setNr((String) inspTranslistMap.get("HNr"));
			inspAllHeaders.setDefectsHeader((String) inspTranslistMap.get("HDefects"));
			inspAllHeaders.setAction((String) inspTranslistMap.get("HActions"));
			inspAllHeaders.setActionBy((String) inspTranslistMap.get("HActionBy"));
			inspAllHeaders.setFrontAxis((String) inspTranslistMap.get("HFaxis"));
			inspAllHeaders.setCenterAxis((String) inspTranslistMap.get("HCaxis"));
			inspAllHeaders.setCenter1Axis((String) inspTranslistMap.get("HC1axle"));
			inspAllHeaders.setCenter2Axis((String) inspTranslistMap.get("HC2axle"));
			inspAllHeaders.setCenter3Axis((String) inspTranslistMap.get("HC3axle"));
			inspAllHeaders.setRearAxis((String) inspTranslistMap.get("HRaxis"));
			inspAllHeaders.setMm((String) inspTranslistMap.get("Hmm"));
			inspAllHeaders.setPsi((String) inspTranslistMap.get("Hpsi"));
			inspAllHeaders.setRoadWorthy((String) inspTranslistMap.get("HRoadworthy"));
			inspAllHeaders.setPressureTestDate((String) inspTranslistMap.get("HPressuretestdate"));
			inspAllHeaders.setInspTankReefSwap((String) inspTranslistMap.get("InspTankReefSwap"));
			inspAllHeaders.setFridgeManufacturer((String) inspTranslistMap.get("HFridgemanuf"));
			inspAllHeaders.setFridgeModel((String) inspTranslistMap.get("HFridgemodel"));
			inspAllHeaders.setFridgeSrNo((String) inspTranslistMap.get("HFridgeSerialNr"));
			inspAllHeaders.setDateOfMfg((String) inspTranslistMap.get("HDateofmanuf"));
			inspAllHeaders.setTailLiftManufacturer((String) inspTranslistMap.get("HTailliftmanuf"));
			inspAllHeaders.setTailLiftModel((String) inspTranslistMap.get("HTailliftmodel"));
			inspAllHeaders.setTailLiftSrNo((String) inspTranslistMap.get("HTaillifSerialNr"));

			if (("A").equalsIgnoreCase((String) inspHeaderMap.get("serviceType"))) {
				inspAllHeaders.setServiceTypeDesc((String) inspTranslistMap.get("HInspA"));
			} else if (("B").equalsIgnoreCase((String) inspHeaderMap.get("serviceType"))) {
				inspAllHeaders.setServiceTypeDesc((String) inspTranslistMap.get("HInspB"));
			} else {
				inspAllHeaders.setServiceTypeDesc((String) inspTranslistMap.get("HInspC"));
			}
		}
	}
	
	private void setCheckList(List<Object> inspChecklist, LinkedHashMap<String, List<InspCheckObject>> inspCheckObjectMapTemp){
		Iterator inspChecklistIterator = inspChecklist.iterator();
		while (inspChecklistIterator.hasNext()) {
			InspCheckObject inspCheckObject = new InspCheckObject();
			Map<String, Object> inspCheckObjectMap = (Map<String, Object>) inspChecklistIterator.next();
			inspCheckObject.setMaintCd((String) inspCheckObjectMap.get("MaintCd"));
			inspCheckObject.setMaintDesc((String) inspCheckObjectMap.get(MAINT_DESC));
			inspCheckObject.setCheckListItem((String) inspCheckObjectMap.get("CheckListItem"));
			inspCheckObject.setDescription((String) inspCheckObjectMap.get("Description"));
			inspCheckObject.setStatus((String) inspCheckObjectMap.get("Status"));
			inspCheckObject.setUnits((String) inspCheckObjectMap.get("Units"));
			if (inspCheckObjectMapTemp.containsKey((String) inspCheckObjectMap.get(MAINT_DESC))) {
				inspCheckObjectMapTemp.get((String) inspCheckObjectMap.get(MAINT_DESC)).add(inspCheckObject);
			} else {
				List<InspCheckObject> inspChecklistTemp = new ArrayList<>();
				inspChecklistTemp.add(inspCheckObject);
				inspCheckObjectMapTemp.put((String) inspCheckObjectMap.get(MAINT_DESC), inspChecklistTemp);
			}
		}
	}
	
	private void setTyreList(List<Object> inspTyrelist, TyreObject tyreObject){
		Iterator inspTyrelistIterator = inspTyrelist.iterator();
		while (inspTyrelistIterator.hasNext()) {
			Map<String, Object> tyreObjectMap = (Map<String, Object>) inspTyrelistIterator.next();
			setFirstAxleTyre(tyreObjectMap, tyreObject);
			setSecondAxleTyre(tyreObjectMap, tyreObject);
			setThirdAxleTyre(tyreObjectMap, tyreObject);
			setFourthAxleTyre(tyreObjectMap, tyreObject);
			setFifthAxleTyre(tyreObjectMap, tyreObject);
			setSpareTyre(tyreObjectMap, tyreObject);
		}
	}

	private void setFirstAxleTyre(Map<String, Object> tyreObjectMap,TyreObject tyreObject){
		if (("LI0").equalsIgnoreCase((String) tyreObjectMap.get(TYRE_CD))) {
			tyreObject.setDepthLI1((String) tyreObjectMap.get(DEPTH));
			tyreObject.setPsiLI1((String) tyreObjectMap.get(PSI));
		} else if (("LO0").equalsIgnoreCase((String) tyreObjectMap.get(TYRE_CD))) {
			tyreObject.setDepthLO1((String) tyreObjectMap.get(DEPTH));
			tyreObject.setPsiLO1((String) tyreObjectMap.get(PSI));
		} else if (("RI0").equalsIgnoreCase((String) tyreObjectMap.get(TYRE_CD))) {
			tyreObject.setDepthRI1((String) tyreObjectMap.get(DEPTH));
			tyreObject.setPsiRI1((String) tyreObjectMap.get(PSI));
		} else if (("RO0").equalsIgnoreCase((String) tyreObjectMap.get(TYRE_CD))) {
			tyreObject.setDepthRO1((String) tyreObjectMap.get(DEPTH));
			tyreObject.setPsiRO1((String) tyreObjectMap.get(PSI));
		}
	}
	
	private void setSecondAxleTyre(Map<String, Object> tyreObjectMap,TyreObject tyreObject){
		if (("LI1").equalsIgnoreCase((String) tyreObjectMap.get(TYRE_CD))) {
			tyreObject.setDepthLI2((String) tyreObjectMap.get(DEPTH));
			tyreObject.setPsiLI2((String) tyreObjectMap.get(PSI));
		} else if (("LO1").equalsIgnoreCase((String) tyreObjectMap.get(TYRE_CD))) {
			tyreObject.setDepthLO2((String) tyreObjectMap.get(DEPTH));
			tyreObject.setPsiLO2((String) tyreObjectMap.get(PSI));
		} else if (("RI1").equalsIgnoreCase((String) tyreObjectMap.get(TYRE_CD))) {
			tyreObject.setDepthRI2((String) tyreObjectMap.get(DEPTH));
			tyreObject.setPsiRI2((String) tyreObjectMap.get(PSI));
		} else if (("RO1").equalsIgnoreCase((String) tyreObjectMap.get(TYRE_CD))) {
			tyreObject.setDepthRO2((String) tyreObjectMap.get(DEPTH));
			tyreObject.setPsiRO2((String) tyreObjectMap.get(PSI));
		} 
	}
	
	private void setThirdAxleTyre(Map<String, Object> tyreObjectMap,TyreObject tyreObject){
		if (("LI2").equalsIgnoreCase((String) tyreObjectMap.get(TYRE_CD))) {
			tyreObject.setDepthLI3((String) tyreObjectMap.get(DEPTH));
			tyreObject.setPsiLI3((String) tyreObjectMap.get(PSI));
		} else if (("LO2").equalsIgnoreCase((String) tyreObjectMap.get(TYRE_CD))) {
			tyreObject.setDepthLO3((String) tyreObjectMap.get(DEPTH));
			tyreObject.setPsiLO3((String) tyreObjectMap.get(PSI));
		} else if (("RI2").equalsIgnoreCase((String) tyreObjectMap.get(TYRE_CD))) {
			tyreObject.setDepthRI3((String) tyreObjectMap.get(DEPTH));
			tyreObject.setPsiRI3((String) tyreObjectMap.get(PSI));
		} else if (("RO2").equalsIgnoreCase((String) tyreObjectMap.get(TYRE_CD))) {
			tyreObject.setDepthRO3((String) tyreObjectMap.get(DEPTH));
			tyreObject.setPsiRO3((String) tyreObjectMap.get(PSI));
		}
	}
	
	private void setFourthAxleTyre(Map<String, Object> tyreObjectMap,TyreObject tyreObject){
		if (("LI3").equalsIgnoreCase((String) tyreObjectMap.get(TYRE_CD))) {
			tyreObject.setDepthLI4((String) tyreObjectMap.get(DEPTH));
			tyreObject.setPsiLI4((String) tyreObjectMap.get(PSI));
		} else if (("LO3").equalsIgnoreCase((String) tyreObjectMap.get(TYRE_CD))) {
			tyreObject.setDepthLO4((String) tyreObjectMap.get(DEPTH));
			tyreObject.setPsiLO4((String) tyreObjectMap.get(PSI));
		} else if (("RI3").equalsIgnoreCase((String) tyreObjectMap.get(TYRE_CD))) {
			tyreObject.setDepthRI4((String) tyreObjectMap.get(DEPTH));
			tyreObject.setPsiRI4((String) tyreObjectMap.get(PSI));
		} else if (("RO3").equalsIgnoreCase((String) tyreObjectMap.get(TYRE_CD))) {
			tyreObject.setDepthRO4((String) tyreObjectMap.get(DEPTH));
			tyreObject.setPsiRO4((String) tyreObjectMap.get(PSI));
		}
	}
	
	private void setFifthAxleTyre(Map<String, Object> tyreObjectMap,TyreObject tyreObject){
		if (("LI4").equalsIgnoreCase((String) tyreObjectMap.get(TYRE_CD))) {
			tyreObject.setDepthLI5((String) tyreObjectMap.get(DEPTH));
			tyreObject.setPsiLI5((String) tyreObjectMap.get(PSI));
		} else if (("LO4").equalsIgnoreCase((String) tyreObjectMap.get(TYRE_CD))) {
			tyreObject.setDepthLO5((String) tyreObjectMap.get(DEPTH));
			tyreObject.setPsiLO5((String) tyreObjectMap.get(PSI));
		} else if (("RI4").equalsIgnoreCase((String) tyreObjectMap.get(TYRE_CD))) {
			tyreObject.setDepthRI5((String) tyreObjectMap.get(DEPTH));
			tyreObject.setPsiRI5((String) tyreObjectMap.get(PSI));
		} else if (("RO4").equalsIgnoreCase((String) tyreObjectMap.get(TYRE_CD))) {
			tyreObject.setDepthRO5((String) tyreObjectMap.get(DEPTH));
			tyreObject.setPsiRO5((String) tyreObjectMap.get(PSI));
		}
	}
	
	private void setSpareTyre(Map<String, Object> tyreObjectMap, TyreObject tyreObject) {
		if (("SP1").equalsIgnoreCase((String) tyreObjectMap.get(TYRE_CD))) {
			tyreObject.setDepthSP1((String) tyreObjectMap.get(DEPTH));
			tyreObject.setPsiSP1((String) tyreObjectMap.get(PSI));
		} else if (("SP2").equalsIgnoreCase((String) tyreObjectMap.get(TYRE_CD))) {
			tyreObject.setDepthSP2((String) tyreObjectMap.get(DEPTH));
			tyreObject.setPsiSP2((String) tyreObjectMap.get(PSI));
		}
	}
	
	private void setBrakeList(List<Object> inspBrakelist, BrakeObject brakeObject){
		Iterator inspBrakelistIterator = inspBrakelist.iterator();
		
		while (inspBrakelistIterator.hasNext()) {
			Map<String, Object> brakeObjectMap = (Map<String, Object>) inspBrakelistIterator.next();
			setLeftAxleBrake(brakeObjectMap, brakeObject);
			setRightAxleBrake(brakeObjectMap, brakeObject);	
		}	
	}
	
	private void setLeftAxleBrake(Map<String, Object> brakeObjectMap,BrakeObject brakeObject){
		if (("L0").equalsIgnoreCase((String) brakeObjectMap.get(BRAKE_CD))) {
			brakeObject.setPerLO1((String) brakeObjectMap.get(LINING_REM_PER));
			brakeObject.setMmLO1((String) brakeObjectMap.get(LINING_REM_MM));
		} else if (("L1").equalsIgnoreCase((String) brakeObjectMap.get(BRAKE_CD))) {
			brakeObject.setPerLO2((String) brakeObjectMap.get(LINING_REM_PER));
			brakeObject.setMmLO2((String) brakeObjectMap.get(LINING_REM_MM));
		} else if (("L2").equalsIgnoreCase((String) brakeObjectMap.get(BRAKE_CD))) {
			brakeObject.setPerLO3((String) brakeObjectMap.get(LINING_REM_PER));
			brakeObject.setMmLO3((String) brakeObjectMap.get(LINING_REM_MM));
		}  else if (("L3").equalsIgnoreCase((String) brakeObjectMap.get(BRAKE_CD))) {
			brakeObject.setPerLO4((String) brakeObjectMap.get(LINING_REM_PER));
			brakeObject.setMmLO4((String) brakeObjectMap.get(LINING_REM_MM));
		} else if (("L4").equalsIgnoreCase((String) brakeObjectMap.get(BRAKE_CD))) {
			brakeObject.setPerLO5((String) brakeObjectMap.get(LINING_REM_PER));
			brakeObject.setMmLO5((String) brakeObjectMap.get(LINING_REM_MM));
		}
	}
	
	private void setRightAxleBrake(Map<String, Object> brakeObjectMap,BrakeObject brakeObject){
		if (("R0").equalsIgnoreCase((String) brakeObjectMap.get(BRAKE_CD))) {
			brakeObject.setPerRO1((String) brakeObjectMap.get(LINING_REM_PER));
			brakeObject.setMmRO1((String) brakeObjectMap.get(LINING_REM_MM));
		} else if (("R1").equalsIgnoreCase((String) brakeObjectMap.get(BRAKE_CD))) {
			brakeObject.setPerRO2((String) brakeObjectMap.get(LINING_REM_PER));
			brakeObject.setMmRO2((String) brakeObjectMap.get(LINING_REM_MM));
		} else if (("R2").equalsIgnoreCase((String) brakeObjectMap.get(BRAKE_CD))) {
			brakeObject.setPerRO3((String) brakeObjectMap.get(LINING_REM_PER));
			brakeObject.setMmRO3((String) brakeObjectMap.get(LINING_REM_MM));
		} else if (("R3").equalsIgnoreCase((String) brakeObjectMap.get(BRAKE_CD))) {
			brakeObject.setPerRO4((String) brakeObjectMap.get(LINING_REM_PER));
			brakeObject.setMmRO4((String) brakeObjectMap.get(LINING_REM_MM));
		} else if (("R4").equalsIgnoreCase((String) brakeObjectMap.get(BRAKE_CD))) {
			brakeObject.setPerRO5((String) brakeObjectMap.get(LINING_REM_PER));
			brakeObject.setMmRO5((String) brakeObjectMap.get(LINING_REM_MM));
		}
	}
}
