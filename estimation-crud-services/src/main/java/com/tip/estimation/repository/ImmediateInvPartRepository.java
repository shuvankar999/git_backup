package com.tip.estimation.repository;

import java.util.List;

import com.tip.estimation.model.Part;
import com.tip.estimation.model.RebillDetails;

@FunctionalInterface
public interface ImmediateInvPartRepository {
	int[] saveRebillParts(List<Part> listOfParts, RebillDetails rebillDetails);

}
