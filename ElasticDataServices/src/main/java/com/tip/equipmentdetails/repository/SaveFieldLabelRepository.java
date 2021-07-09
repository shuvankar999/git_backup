package com.tip.equipmentdetails.repository;

import com.tip.equipmentdetails.model.SaveProfileSettingsRequest;

@FunctionalInterface
public interface SaveFieldLabelRepository {

	public boolean saveFieldLabelDetils(SaveProfileSettingsRequest saveProfileSettingsRequest);
}