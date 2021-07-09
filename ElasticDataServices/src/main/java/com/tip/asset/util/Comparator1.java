package com.tip.asset.util;

import java.util.Comparator;

import org.springframework.stereotype.Component;

import com.tip.equipmentdetails.model.DropOption;

@Component
public class Comparator1 implements Comparator<DropOption> {
	@Override
	    public int compare(DropOption o1, DropOption o2) {

	        return o1.getName().compareTo( o2.getName() );
	    }
	}
