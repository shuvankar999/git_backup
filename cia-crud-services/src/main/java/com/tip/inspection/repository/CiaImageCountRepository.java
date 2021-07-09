package com.tip.inspection.repository;

import java.math.BigDecimal;
import java.util.Map;
@FunctionalInterface
public interface CiaImageCountRepository {

	public Map<String, Object> fetchImageCount(String inspId, String inspType);

}
