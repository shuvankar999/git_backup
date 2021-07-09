package com.tip.estimation.repository;

import java.util.List;

import com.tip.estimation.model.InvoiceBundleData;
import com.tip.estimation.model.RebillDetails;

@FunctionalInterface
public interface ImmediateInvBundleRepository {
	int[] saveRebillBundle(List<InvoiceBundleData> listOfBundle, RebillDetails rebillDetails);

}
