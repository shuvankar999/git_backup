package com.tip.inspection.service;

@FunctionalInterface
public interface CiaImageCountService {
	public int getImageCount(String inspId, String inspType);
}
