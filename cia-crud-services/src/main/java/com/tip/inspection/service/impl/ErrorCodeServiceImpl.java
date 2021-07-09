package com.tip.inspection.service.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tip.inspection.repository.ErrorCodeRepository;
import com.tip.inspection.service.ErrorCodeService;

@Service
@Transactional
public class ErrorCodeServiceImpl implements ErrorCodeService {

	private static final Logger logger = LoggerFactory.getLogger(ErrorCodeServiceImpl.class);

	@Autowired
	ErrorCodeRepository errorCodeRepository;

	@Override
	public void getFile() throws IOException {
		Map<String, String> errorCodeMap = new HashMap<>();
		Map<String, Object> resultMap = errorCodeRepository.getErrorCode();
		if (null != resultMap) {
			for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
				if ("#result-set-1".equalsIgnoreCase(entry.getKey())) {
					@SuppressWarnings("unchecked")
					List<Map<String, Object>> list1 = (List<Map<String, Object>>) entry.getValue();
					errorCodeMap = setErrorCodeData(list1);
				}
			}
			setDataInPropertyFile(errorCodeMap);
		}
		logger.info(new Date() + " Added data in property file");
	}

	@SuppressWarnings("unchecked")
	private Map<String, String> setErrorCodeData(List<Map<String, Object>> list1) {
		Map<String, String> textMap = new HashMap<>();
		@SuppressWarnings("rawtypes")
		List checkList = new ArrayList();
		for (Map<String, Object> objMap : list1) {

			if (!checkList.contains(objMap.get("Lang_Cd"))) {
				checkList.add(objMap.get("Lang_Cd"));
				for (Map<String, Object> objMap1 : list1) {
					if (((String) objMap.get("Lang_Cd")).equalsIgnoreCase((String) objMap1.get("Lang_Cd"))) {
						textMap.put((String) objMap.get("Lang_Cd") + "." + (String) objMap1.get("errorCode"),
								(String) objMap1.get("errorDesc"));
					}

				}
			}
		}

		return textMap;
	}

	private void setDataInPropertyFile(Map<String, String> errorCodeMap) throws IOException {
		File f = new File("src/main/resources/ErrorCode.properties");
		FileWriter file = new FileWriter(f.getAbsolutePath());
		try {
			String[] errorDesc = errorCodeMap.toString().replace("{", "").replace("}", "").split(",");
			for (String obj : errorDesc) {
				file.write(obj.trim());
				file.write("\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			file.flush();
			file.close();
		}
	}

}