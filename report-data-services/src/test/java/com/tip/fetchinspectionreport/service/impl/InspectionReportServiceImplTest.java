package com.tip.fetchinspectionreport.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import com.tip.fetchinspectionreport.model.InspectionReportRequest;
import com.tip.fetchinspectionreport.repository.InspectionReportRepository;

public class InspectionReportServiceImplTest {

	@Mock
	InspectionReportRepository inspectionReportRepository;

	InspectionReportServiceImpl inspectionReportServiceImpl;

	@Before
	public void beforesetup() {
		inspectionReportServiceImpl = new InspectionReportServiceImpl();
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(inspectionReportServiceImpl, "inspectionReportRepository",
				inspectionReportRepository);
		byte[] bytesArray = new byte[] { -119, 80, 78, 71, 13, 10, 26, 10, 0, 0, 0, 13, 73, 72, 68, 82, 0, 0, 0, 9, 0,
				0, 0, 9, 8, 6, 0, 0, 0, -32, -111, 6, 16, 0, 0, 0, 9, 112, 72, 89, 115, 0, 0, 11, 18, 0, 0, 11, 18, 1,
				-46, -35, 126, -4, 0, 0, 0, -98, 73, 68, 65, 84, 24, -45, 101, -112, -79, 13, 2, 49, 16, 4, -57, 22,
				-63, 103, 16, 66, 100, -121, 100, -90, -125, -93, 4, 58, -128, 18, 40, -123, 18, -8, 14, 32, -126, 16,
				119, -128, 59, 120, 119, -64, 75, 4, 4, 72, 38, -79, -59, 9, 54, -71, -45, 106, 110, -75, 58, 83, 74,
				-95, 41, -70, -48, 1, 29, -16, -106, -100, -98, -51, 55, -91, 20, -94, 11, 51, -32, 0, 108, -7, 42, 3,
				27, -55, -23, -34, -96, 27, 32, -4, 107, 4, -106, 54, -70, 48, 87, -64, 89, -91, 0, 76, -127, -99, 5,
				-68, -70, 92, 3, -3, 79, -38, -62, 2, 15, 101, 12, 21, 28, 52, 53, -87, -58, 88, -93, 125, -99, 78, 121,
				23, 43, 57, -67, -128, -67, -22, -128, -38, 123, -55, -23, 106, -38, -97, -94, 11, -85, 10, -5, 90, -31,
				36, 57, 29, 1, 62, 81, -71, 50, 67, -14, 127, -59, 119, 0, 0, 0, 0, 73, 69, 78, 68, -82, 66, 96, -126 };
		FileOutputStream fos;
		try {
			File f = new File("img.png");
			fos = new FileOutputStream("img.png");
			fos.write(bytesArray);
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getInspectionReport() {
		InspectionReportRequest inspectionReportRequest = new InspectionReportRequest();
		inspectionReportRequest.setLangId(1);
		inspectionReportRequest.setSsoId("501538672");
		inspectionReportRequest.setWorkOrderNr(1);
		inspectionReportRequest.setWorkOrderTaskNr(1);
		inspectionReportRequest.setWorkPackNr(BigDecimal.valueOf(7900181280l));
		Map<String, Object> returnMap = new HashMap<String, Object>(0);
		List<Object> inspTranslist = new ArrayList<Object>(0);
		HashMap<String, String> inspTranslistMap = new HashMap<String, String>(0);
		inspTranslistMap.put("HTrailerNr", "Trailer Nr");
		inspTranslistMap.put("HRegNr", "Registration Nr");
		inspTranslistMap.put("HServiceType", "Service Type");
		inspTranslistMap.put("HChassis", "Chassis");
		inspTranslistMap.put("HMileage", "Mileage");
		inspTranslistMap.put("HMOTdate", "MOT Due Date");
		inspTranslistMap.put("HJobdate", "Job Date");
		inspTranslistMap.put("HServiceable", "SERVICEABLE");
		inspTranslistMap.put("HNa", "NOT APPLICABLE");
		inspTranslistMap.put("HInspA", "Inspection A (Items 1 to 37)");
		inspTranslistMap.put("HInspB", "Inspection B (Items 1 to 39)");
		inspTranslistMap.put("HInspC", "Inspection C (Items 1 to 41");
		inspTranslistMap.put("HInspector", "Inspector");
		inspTranslistMap.put("HDate", "Date");
		inspTranslistMap.put("HSigned", "Singed");
		inspTranslistMap.put("Hmm", "mm");
		inspTranslistMap.put("Hpsi", "psi");
		inspTranslistMap.put("HFaxis", "Front Axis");
		inspTranslistMap.put("HCaxis", "Center Axis");
		inspTranslistMap.put("HRaxis", "Rear Axis");
		inspTranslistMap.put("HRoadworthy", "Roadworthy");
		inspTranslist.add(inspTranslistMap);

		returnMap.put("InspTranslist", inspTranslist);

		List<Object> inspHeaderlist = new ArrayList<Object>(0);
		HashMap<String, String> inspHeaderlistMap = new HashMap<String, String>(0);
		inspHeaderlistMap.put("serviceType", "A");
		inspHeaderlistMap.put("ReportLocation", "testPdf");
		inspHeaderlistMap.put("TIPLogo", "img.png");
		inspHeaderlistMap.put("CustLogo", "img.png");
		inspHeaderlistMap.put("InspReportText", "Name");
		inspHeaderlistMap.put("RoadWorthy", "Y");
		inspHeaderlist.add(inspHeaderlistMap);
		returnMap.put("InspHeaderlist", inspHeaderlist);

		List<Object> inspChecklist = new ArrayList<Object>(0);
		HashMap<String, String> inspChecklistMap = new HashMap<String, String>(0);
		inspChecklistMap.put("MaintCd", "030");
		inspChecklistMap.put("MaintDesc", "Braking System");
		inspChecklistMap.put("CheckListItem", "01");
		inspChecklistMap.put("Description", "Check operation of ABS / EBS system (38)");
		inspChecklistMap.put("Status", "P");

		HashMap<String, String> inspChecklistMap1 = new HashMap<String, String>(0);
		inspChecklistMap1.put("MaintCd", "030");
		inspChecklistMap1.put("MaintDesc", "Braking System");
		inspChecklistMap1.put("CheckListItem", "01");
		inspChecklistMap1.put("Description", "Check operation of ABS / EBS system (38)");
		inspChecklistMap1.put("Status", "NA");
		inspChecklist.add(inspChecklistMap1);

		HashMap<String, String> inspChecklistMap2 = new HashMap<String, String>(0);
		inspChecklistMap2.put("MaintCd", "030");
		inspChecklistMap2.put("MaintDesc", "Braking System");
		inspChecklistMap2.put("CheckListItem", "01");
		inspChecklistMap2.put("Description", "Check operation of ABS / EBS system (38)");
		inspChecklistMap2.put("Status", "F");
		inspChecklist.add(inspChecklistMap2);
		for (int i = 0; i < 36; i++) {
			inspChecklist.add(inspChecklistMap);
		}
		returnMap.put("InspChecklist", inspChecklist);

		List<Object> signlist = new ArrayList<Object>(0);
		HashMap<String, String> signlistMap = new HashMap<String, String>(0);
		signlistMap.put("SignType", "Signed");
		signlistMap.put("Confirmation", "Confirmation");
		signlistMap.put("TIPSignature", "");
		signlistMap.put("Name", "ManojKumar Karnati");
		signlist.add(signlistMap);

		HashMap<String, String> signlistMap1 = new HashMap<String, String>(0);
		signlistMap1.put("SignType", "Inspector");
		signlistMap1.put("Confirmation", "Confirmation");
		signlistMap1.put("TIPSignature", "");
		signlistMap1.put("Name", "ManojKumar Karnati");
		signlist.add(signlistMap1);
		returnMap.put("Signlist", signlist);

		List<Object> inspDefectlist = new ArrayList<Object>(0);
		HashMap<String, String> inspDefectlistMap = new HashMap<String, String>(0);
		inspDefectlistMap.put("", "");

		inspDefectlist.add(inspDefectlistMap);
		returnMap.put("InspDefectlist", inspDefectlist);

		List<Object> inspTyrelist = new ArrayList<Object>(0);
		HashMap<String, String> inspTyrelistMap = new HashMap<String, String>(0);
		inspTyrelistMap.put("TyreCd", "LI0");
		inspTyrelistMap.put("Depth", "6.0");
		inspTyrelistMap.put("PSI", "7.0");
		inspTyrelistMap.put("Make", "DUN");
		inspTyrelistMap.put("SerialNr", "test");
		inspTyrelistMap.put("TyreSize", "5");
		inspTyrelist.add(inspTyrelistMap);

		HashMap<String, String> inspTyrelistMap1 = new HashMap<String, String>(0);
		inspTyrelistMap1.put("TyreCd", "LO0");
		inspTyrelistMap1.put("Depth", "6.0");
		inspTyrelistMap1.put("PSI", "7.0");
		inspTyrelistMap1.put("Make", "DUN");
		inspTyrelistMap1.put("SerialNr", "test");
		inspTyrelistMap1.put("TyreSize", "5");
		inspTyrelist.add(inspTyrelistMap1);

		HashMap<String, String> inspTyrelistMap2 = new HashMap<String, String>(0);
		inspTyrelistMap2.put("TyreCd", "RI0");
		inspTyrelistMap2.put("Depth", "6.0");
		inspTyrelistMap2.put("PSI", "7.0");
		inspTyrelistMap2.put("Make", "DUN");
		inspTyrelistMap2.put("SerialNr", "test");
		inspTyrelistMap2.put("TyreSize", "5");
		inspTyrelist.add(inspTyrelistMap2);

		HashMap<String, String> inspTyrelistMap3 = new HashMap<String, String>(0);
		inspTyrelistMap3.put("TyreCd", "RO0");
		inspTyrelistMap3.put("Depth", "6.0");
		inspTyrelistMap3.put("PSI", "7.0");
		inspTyrelistMap3.put("Make", "DUN");
		inspTyrelistMap3.put("SerialNr", "test");
		inspTyrelistMap3.put("TyreSize", "5");
		inspTyrelist.add(inspTyrelistMap3);

		HashMap<String, String> inspTyrelistMap4 = new HashMap<String, String>(0);
		inspTyrelistMap4.put("TyreCd", "RI1");
		inspTyrelistMap4.put("Depth", "6.0");
		inspTyrelistMap4.put("PSI", "7.0");
		inspTyrelistMap4.put("Make", "DUN");
		inspTyrelistMap4.put("SerialNr", "test");
		inspTyrelistMap4.put("TyreSize", "5");
		inspTyrelist.add(inspTyrelistMap4);

		HashMap<String, String> inspTyrelistMap5 = new HashMap<String, String>(0);
		inspTyrelistMap5.put("TyreCd", "LO2");
		inspTyrelistMap5.put("Depth", "6.0");
		inspTyrelistMap5.put("PSI", "7.0");
		inspTyrelistMap5.put("Make", "DUN");
		inspTyrelistMap5.put("SerialNr", "test");
		inspTyrelistMap5.put("TyreSize", "5");
		inspTyrelist.add(inspTyrelistMap5);

		HashMap<String, String> inspTyrelistMap6 = new HashMap<String, String>(0);
		inspTyrelistMap6.put("TyreCd", "LI1");
		inspTyrelistMap6.put("Depth", "6.0");
		inspTyrelistMap6.put("PSI", "7.0");
		inspTyrelistMap6.put("Make", "DUN");
		inspTyrelistMap6.put("SerialNr", "test");
		inspTyrelistMap6.put("TyreSize", "5");
		inspTyrelist.add(inspTyrelistMap6);

		HashMap<String, String> inspTyrelistMap7 = new HashMap<String, String>(0);
		inspTyrelistMap7.put("TyreCd", "LO1");
		inspTyrelistMap7.put("Depth", "6.0");
		inspTyrelistMap7.put("PSI", "7.0");
		inspTyrelistMap7.put("Make", "DUN");
		inspTyrelistMap7.put("SerialNr", "test");
		inspTyrelistMap7.put("TyreSize", "5");
		inspTyrelist.add(inspTyrelistMap7);

		HashMap<String, String> inspTyrelistMap8 = new HashMap<String, String>(0);
		inspTyrelistMap8.put("TyreCd", "RI2");
		inspTyrelistMap8.put("Depth", "6.0");
		inspTyrelistMap8.put("PSI", "7.0");
		inspTyrelistMap8.put("Make", "DUN");
		inspTyrelistMap8.put("SerialNr", "test");
		inspTyrelistMap8.put("TyreSize", "5");
		inspTyrelist.add(inspTyrelistMap8);

		HashMap<String, String> inspTyrelistMap9 = new HashMap<String, String>(0);
		inspTyrelistMap9.put("TyreCd", "RO2");
		inspTyrelistMap9.put("Depth", "6.0");
		inspTyrelistMap9.put("PSI", "7.0");
		inspTyrelistMap9.put("Make", "DUN");
		inspTyrelistMap9.put("SerialNr", "test");
		inspTyrelistMap9.put("TyreSize", "5");
		inspTyrelist.add(inspTyrelistMap9);

		HashMap<String, String> inspTyrelistMap10 = new HashMap<String, String>(0);
		inspTyrelistMap10.put("TyreCd", "LI2");
		inspTyrelistMap10.put("Depth", "6.0");
		inspTyrelistMap10.put("PSI", "7.0");
		inspTyrelistMap10.put("Make", "DUN");
		inspTyrelistMap10.put("SerialNr", "test");
		inspTyrelistMap10.put("TyreSize", "5");
		inspTyrelist.add(inspTyrelistMap10);

		HashMap<String, String> inspTyrelistMap11 = new HashMap<String, String>(0);
		inspTyrelistMap11.put("TyreCd", "RO1");
		inspTyrelistMap11.put("Depth", "6.0");
		inspTyrelistMap11.put("PSI", "7.0");
		inspTyrelistMap11.put("Make", "DUN");
		inspTyrelistMap11.put("SerialNr", "test");
		inspTyrelistMap11.put("TyreSize", "5");
		inspTyrelist.add(inspTyrelistMap11);

		returnMap.put("InspTyrelist", inspTyrelist);

		List<Object> inspBrakelist = new ArrayList<Object>(0);
		HashMap<String, String> inspBrakelistMap = new HashMap<String, String>(0);
		inspBrakelistMap.put("BrakeCd", "L0");
		inspBrakelistMap.put("LiningRemPer", "6.0");
		inspBrakelistMap.put("LiningRemMM", null);

		inspBrakelist.add(inspBrakelistMap);

		HashMap<String, String> inspBrakelistMap1 = new HashMap<String, String>(0);
		inspBrakelistMap1.put("BrakeCd", "L1");
		inspBrakelistMap1.put("LiningRemPer", "6.0");
		inspBrakelistMap1.put("LiningRemMM", null);

		inspBrakelist.add(inspBrakelistMap1);

		HashMap<String, String> inspBrakelistMap2 = new HashMap<String, String>(0);
		inspBrakelistMap2.put("BrakeCd", "L2");
		inspBrakelistMap2.put("LiningRemPer", "6.0");
		inspBrakelistMap2.put("LiningRemMM", null);

		inspBrakelist.add(inspBrakelistMap2);

		HashMap<String, String> inspBrakelistMap3 = new HashMap<String, String>(0);
		inspBrakelistMap3.put("BrakeCd", "R0");
		inspBrakelistMap3.put("LiningRemPer", "6.0");
		inspBrakelistMap3.put("LiningRemMM", null);

		inspBrakelist.add(inspBrakelistMap3);

		HashMap<String, String> inspBrakelistMap4 = new HashMap<String, String>(0);
		inspBrakelistMap4.put("BrakeCd", "R1");
		inspBrakelistMap4.put("LiningRemPer", "6.0");
		inspBrakelistMap4.put("LiningRemMM", null);

		inspBrakelist.add(inspBrakelistMap4);

		HashMap<String, String> inspBrakelistMap5 = new HashMap<String, String>(0);
		inspBrakelistMap5.put("BrakeCd", "R2");
		inspBrakelistMap5.put("LiningRemPer", "6.0");
		inspBrakelistMap5.put("LiningRemMM", null);

		inspBrakelist.add(inspBrakelistMap5);
		returnMap.put("InspBrakelist", inspBrakelist);

		Mockito.when(inspectionReportRepository.getInspectionReport(inspectionReportRequest)).thenReturn(returnMap);
		try {
			inspectionReportServiceImpl.getInspectionReport(inspectionReportRequest);
		} catch (Exception e) {
		}

		List<Object> signlist1 = new ArrayList<Object>(0);
		signlistMap.put("Date", "10/08/2017");
		signlistMap.put("TIPSignature", "img.png");
		signlistMap1.put("Date", "10/08/2017");
		signlistMap1.put("TIPSignature", "img.png");
		signlist1.add(signlistMap);
		signlist1.add(signlistMap1);
		inspHeaderlistMap.put("serviceType", "B");
		inspHeaderlistMap.put("TIPLogo", "");
		inspHeaderlistMap.put("CustLogo", "");
		inspHeaderlistMap.put("RoadWorthy", "N");
		List<Object> inspHeaderlist1 = new ArrayList<Object>(0);
		inspHeaderlist1.add(inspHeaderlistMap);
		returnMap.put("InspHeaderlist", inspHeaderlist1);
		returnMap.put("Signlist", signlist1);

		List<Object> inspBrakelist1 = new ArrayList<Object>(0);
		inspBrakelistMap.put("LiningRemPer", null);
		inspBrakelistMap.put("LiningRemMM", "6.0");
		inspBrakelist1.add(inspBrakelistMap);

		inspBrakelistMap1.put("LiningRemPer", null);
		inspBrakelistMap1.put("LiningRemMM", "6.0");
		inspBrakelist1.add(inspBrakelistMap1);

		inspBrakelistMap2.put("LiningRemPer", null);
		inspBrakelistMap2.put("LiningRemMM", "6.0");
		inspBrakelist1.add(inspBrakelistMap2);

		inspBrakelistMap3.put("LiningRemPer", null);
		inspBrakelistMap3.put("LiningRemMM", "6.0");
		inspBrakelist1.add(inspBrakelistMap3);

		inspBrakelistMap4.put("LiningRemPer", null);
		inspBrakelistMap4.put("LiningRemMM", "6.0");
		inspBrakelist1.add(inspBrakelistMap4);

		inspBrakelistMap5.put("LiningRemPer", null);
		inspBrakelistMap5.put("LiningRemMM", "6.0");
		inspBrakelist1.add(inspBrakelistMap5);
		returnMap.put("InspBrakelist", inspBrakelist1);

		inspDefectlistMap.put("nr", "1");
		inspDefectlistMap.put("defect", "Brake");
		inspDefectlistMap.put("action", "Brake");
		inspDefectlistMap.put("actionBy", "Manoj Kumar");
		inspDefectlist.add(inspDefectlistMap);
		returnMap.put("InspDefectlist", inspDefectlist);

		Mockito.when(inspectionReportRepository.getInspectionReport(inspectionReportRequest)).thenReturn(returnMap);
		try {
			inspectionReportServiceImpl.getInspectionReport(inspectionReportRequest);
		} catch (Exception e) {
		}

		inspHeaderlistMap.put("serviceType", "C");
		inspHeaderlistMap.put("TIPLogo", "img2.png");
		inspHeaderlistMap.put("CustLogo", "img2.png");
		List<Object> inspHeaderlist2 = new ArrayList<Object>(0);
		inspHeaderlist2.add(inspHeaderlistMap);
		returnMap.put("InspHeaderlist", inspHeaderlist2);

		List<Object> inspBrakelist2 = new ArrayList<Object>(0);
		inspBrakelistMap.put("LiningRemPer", null);
		inspBrakelistMap.put("LiningRemMM", null);
		inspBrakelist2.add(inspBrakelistMap);

		inspBrakelistMap1.put("LiningRemPer", null);
		inspBrakelistMap1.put("LiningRemMM", null);
		inspBrakelist2.add(inspBrakelistMap1);

		inspBrakelistMap2.put("LiningRemPer", null);
		inspBrakelistMap2.put("LiningRemMM", null);
		inspBrakelist2.add(inspBrakelistMap2);

		inspBrakelistMap3.put("LiningRemPer", null);
		inspBrakelistMap3.put("LiningRemMM", null);
		inspBrakelist2.add(inspBrakelistMap3);

		inspBrakelistMap4.put("LiningRemPer", null);
		inspBrakelistMap4.put("LiningRemMM", null);
		inspBrakelist2.add(inspBrakelistMap4);

		inspBrakelistMap5.put("LiningRemPer", null);
		inspBrakelistMap5.put("LiningRemMM", null);
		inspBrakelist2.add(inspBrakelistMap5);
		returnMap.put("InspBrakelist", inspBrakelist2);

		List<Object> signlist2 = new ArrayList<Object>(0);
		signlistMap.put("TIPSignature", "img2.png");
		signlistMap1.put("TIPSignature", "img2.png");
		signlist2.add(signlistMap);
		signlist2.add(signlistMap1);
		returnMap.put("Signlist", signlist2);
		Mockito.when(inspectionReportRepository.getInspectionReport(inspectionReportRequest)).thenReturn(returnMap);
		try {
			inspectionReportServiceImpl.getInspectionReport(inspectionReportRequest);
		} catch (Exception e) {
		}

		inspHeaderlistMap.put("TIPLogo", null);
		inspHeaderlistMap.put("CustLogo", null);
		List<Object> inspHeaderlist3 = new ArrayList<Object>(0);
		inspHeaderlist3.add(inspHeaderlistMap);
		returnMap.put("InspHeaderlist", inspHeaderlist3);

		List<Object> signlist3 = new ArrayList<Object>(0);
		signlistMap.put("TIPSignature", "http://yiagcg/vjdvj/img.png");
		signlistMap1.put("TIPSignature", "http://yiagcg/vjdvj/img.png");
		signlist3.add(signlistMap);
		signlist3.add(signlistMap1);
		returnMap.put("Signlist", signlist3);
		Mockito.when(inspectionReportRepository.getInspectionReport(inspectionReportRequest)).thenReturn(returnMap);
		try {
			inspectionReportServiceImpl.getInspectionReport(inspectionReportRequest);
		} catch (Exception e) {
		}

		inspHeaderlistMap.put("TIPLogo", "http://yiagcg/vjdvj/img.png");
		inspHeaderlistMap.put("CustLogo", "http://yiagcg/vjdvj/img.png");
		inspHeaderlistMap.put("InspReportText", null);
		List<Object> inspHeaderlist4 = new ArrayList<Object>(0);
		inspHeaderlist4.add(inspHeaderlistMap);
		returnMap.put("InspHeaderlist", inspHeaderlist4);

		List<Object> signlist4 = new ArrayList<Object>(0);
		signlistMap.put("TIPSignature", "http://dev.apps.tipeurope.com/applogo/TIPLogo.png");
		signlistMap1.put("TIPSignature", "http://dev.apps.tipeurope.com/applogo/TIPLogo.png");
		signlist4.add(signlistMap);
		signlist4.add(signlistMap1);
		returnMap.put("Signlist", signlist4);
		Mockito.when(inspectionReportRepository.getInspectionReport(inspectionReportRequest)).thenReturn(returnMap);
		try {
			inspectionReportServiceImpl.getInspectionReport(inspectionReportRequest);
		} catch (Exception e) {
		}

		inspHeaderlistMap.put("TIPLogo", "http://dev.apps.tipeurope.com/applogo/TIPLogo.png");
		inspHeaderlistMap.put("CustLogo", "http://dev.apps.tipeurope.com/applogo/TIPLogo.png");
		inspHeaderlistMap.put("InspReportText", "");
		List<Object> inspHeaderlist5 = new ArrayList<Object>(0);
		inspHeaderlist5.add(inspHeaderlistMap);
		returnMap.put("InspHeaderlist", inspHeaderlist5);

		List<Object> signlist5 = new ArrayList<Object>(0);
		signlistMap.put("TIPSignature", null);
		signlistMap1.put("TIPSignature", null);
		signlist5.add(signlistMap);
		signlist5.add(signlistMap1);
		returnMap.put("Signlist", signlist5);
		Mockito.when(inspectionReportRepository.getInspectionReport(inspectionReportRequest)).thenReturn(returnMap);
		try {
			inspectionReportServiceImpl.getInspectionReport(inspectionReportRequest);
		} catch (Exception e) {
		}

		inspHeaderlistMap.put("ReportLocation", "op/hsc/cnsc/abc.pdf");
		List<Object> inspHeaderlist6 = new ArrayList<Object>(0);
		inspHeaderlist6.add(inspHeaderlistMap);
		returnMap.put("InspHeaderlist", inspHeaderlist6);
		try {
			inspectionReportServiceImpl.getInspectionReport(inspectionReportRequest);
		} catch (Exception e) {
		}
	}

	@After
	public void cleanUp() {
		File f = new File("img.png");
		if (f.exists()) {
			f.delete();
		}
	}
}
