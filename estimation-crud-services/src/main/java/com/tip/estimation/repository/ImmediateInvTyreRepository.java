package com.tip.estimation.repository;

import java.util.List;

import com.tip.estimation.model.InvoiceTyreService;
import com.tip.estimation.model.RebillDetails;
import com.tip.estimation.model.Tyre;

public interface ImmediateInvTyreRepository {
	int[] saveRebillTyreSpecs(List<Tyre> listOfTyreSpecs, RebillDetails rebillDetails);
	int[] saveRebillTyreServices(List<InvoiceTyreService> listOfTyreServices, RebillDetails rebillDetails);
}
