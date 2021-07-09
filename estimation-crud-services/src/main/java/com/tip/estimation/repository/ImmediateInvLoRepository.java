package com.tip.estimation.repository;

import java.util.List;

import com.tip.estimation.model.RebillDetails;
import com.tip.estimation.model.Task;

@FunctionalInterface
public interface ImmediateInvLoRepository {
	public int[] saveRebillLineLo(List<Task> listOfTasks, RebillDetails rebillDetails);

}
