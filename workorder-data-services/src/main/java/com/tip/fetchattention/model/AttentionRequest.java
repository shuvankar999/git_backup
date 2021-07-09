package com.tip.fetchattention.model;

public class AttentionRequest {
    private int unitNr;
    private int langId;

    public int getUnitNr() {
        return unitNr;
    }

    public void setUnitNr(int unitNr) {
        this.unitNr = unitNr;
    }

    public int getLangId() {
        return langId;
    }

    public void setLangId(int langId) {
        this.langId = langId;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "AttentionRequest [unitNr=" + unitNr + ", langId=" + langId + "]";
    }


}
