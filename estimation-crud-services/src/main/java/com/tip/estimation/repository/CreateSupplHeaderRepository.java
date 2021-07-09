package com.tip.estimation.repository;

import java.math.BigDecimal;

import com.tip.estimation.model.VersionObject;

@FunctionalInterface
public interface CreateSupplHeaderRepository {
	public VersionObject saveHeaderSuppl(BigDecimal estimationId, String ssoId, Integer supplementary);

}
