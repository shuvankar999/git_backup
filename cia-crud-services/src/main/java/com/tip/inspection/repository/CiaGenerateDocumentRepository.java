package com.tip.inspection.repository;

import java.math.BigDecimal;
import java.util.Map;

@FunctionalInterface
public interface CiaGenerateDocumentRepository {
	public Map<String,Object> getDocumentData(BigDecimal inspCd);
}
