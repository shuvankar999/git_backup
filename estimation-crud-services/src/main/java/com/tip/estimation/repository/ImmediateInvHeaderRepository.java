package com.tip.estimation.repository;

import com.tip.estimation.model.HeaderData;
import com.tip.estimation.model.RebillDetails;

@FunctionalInterface
public interface ImmediateInvHeaderRepository {
	public RebillDetails saveRebillHeader(HeaderData headerData);

}
