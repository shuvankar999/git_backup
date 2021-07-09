/*package com.tip.equipmentdetails.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tip.equipmentdetails.model.MultiCopyRequest;

@Repository
public interface SaveMultipleRepository extends CrudRepository<MultiCopyRequest, Long> {

	public boolean updateMultipleEquip(MultiCopyRequest multiCopyRequest);
	
	@Query("UPDATE OPSeqsp..Units SET Unit_Catgrp_Code = '', Unit_Operational_Status = '' WHERE Unit_Nr IN :desUnitList")
	public Integer updateMultipleEquipment(@Param("destinationUnits") List<Integer> desUnitList);
}*/