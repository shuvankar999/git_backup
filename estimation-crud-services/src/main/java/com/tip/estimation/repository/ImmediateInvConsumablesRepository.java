package com.tip.estimation.repository;

import java.util.List;

import com.tip.estimation.model.Consumables;
import com.tip.estimation.model.RebillDetails;

@FunctionalInterface
public interface ImmediateInvConsumablesRepository {
	int[] saveRebillConsumables(List<Consumables> listOfConsumables, RebillDetails rebillDetails);

}
