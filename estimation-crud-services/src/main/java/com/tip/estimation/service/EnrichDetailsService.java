package com.tip.estimation.service;

import com.tip.estimation.model.EnrichResponse;
import com.tip.estimation.model.SaveEnrichDetails;


@FunctionalInterface
public interface EnrichDetailsService {

public EnrichResponse saveEnrichDetails(SaveEnrichDetails saveEnrichDetail);

}
