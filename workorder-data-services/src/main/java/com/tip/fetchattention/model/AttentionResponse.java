package com.tip.fetchattention.model;

import java.util.List;
import java.util.Map;

public class AttentionResponse {
    private ErrorCd errorCd;
    private List<Attention> attentionList;
    private Map<String, Object> attentionListMap;


    public ErrorCd getErrorCd() {
        return errorCd;
    }

    public void setErrorCd(ErrorCd errorCd) {
        this.errorCd = errorCd;
    }

    public List<Attention> getAttentionList() {
        return attentionList;
    }

    public void setAttentionList(List<Attention> attentionList) {
        this.attentionList = attentionList;
    }

    public Map<String, Object> getAttentionListMap() {
        return attentionListMap;
    }

    public void setAttentionListMap(Map<String, Object> attentionListMap) {
        this.attentionListMap = attentionListMap;
    }

}
