package com.tip.inspection.repository;

import java.util.Map;
@FunctionalInterface
public interface ErrorCodeRepository {

	public Map<String, Object> getErrorCode();

}
