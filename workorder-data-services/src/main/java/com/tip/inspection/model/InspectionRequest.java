package com.tip.inspection.model;

public class InspectionRequest {

    private String unitNr;

    /**
     * @return the unitNr
     */
    public String getUnitNr() {
        return unitNr;
    }

    /**
     * @param unitNr the unitNr to set
     */
    public void setUnitNr(String unitNr) {
        this.unitNr = unitNr;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "InspectionRequest [unitNr=" + unitNr + "]";
    }


}
