package com.tip.estimation.model;

import java.math.BigDecimal;

public class EstnWotListObject {

    protected BigDecimal estimationId;
    protected Integer estnWOId;
    protected Integer estnWOTId;
    protected String groupCd;
    protected String subgroupCd;
    protected String activityCd;
    protected String failureCauseCd;
    protected String actionCd;
    protected String positionCd;
    protected Object targetTime;
    protected String reasonCd;
    protected String groupCdDesc;
    protected String subGroup;
    protected String activity;
    protected String failureCause;
    protected String action;
    protected String reason;
    protected String position;
    protected Double soldTime;
    protected BigDecimal labourRate;
    protected String labourTime;
    protected boolean showHide;
    protected String taskComments;
    protected BigDecimal wshpWPNr;
    protected Integer wshpWONr;
    protected Integer wshpWOTNr;
    protected boolean attribute;
    protected String isApproved;
    protected String isRejected;
    public String getReasonCd() {
		return reasonCd;
	}

	public void setReasonCd(String reasonCd) {
		this.reasonCd = reasonCd;
	}
    public BigDecimal getEstimationId() {
        return estimationId;
    }
    public void setEstimationId(BigDecimal value) {
        this.estimationId = value;
    }
    public Integer getEstnWOId() {
        return estnWOId;
    }
    public void setEstnWOId(Integer value) {
        this.estnWOId = value;
    }
    public Integer getEstnWOTId() {
        return estnWOTId;
    }
    public void setEstnWOTId(Integer value) {
        this.estnWOTId = value;
    }
    public String getGroupCd() {
        return groupCd;
    }
    public void setGroupCd(String value) {
        this.groupCd = value;
    }
    public String getSubgroupCd() {
        return subgroupCd;
    }
    public void setSubgroupCd(String value) {
        this.subgroupCd = value;
    }
    public String getActivityCd() {
        return activityCd;
    }
    public void setActivityCd(String value) {
        this.activityCd = value;
    }
    public String getFailureCauseCd() {
        return failureCauseCd;
    } 
    public void setFailureCauseCd(String value) {
        this.failureCauseCd = value;
    }
    public String getActionCd() {
        return actionCd;
    }
    public void setActionCd(String value) {
        this.actionCd = value;
    }
    public String getPositionCd() {
        return positionCd;
    }
    public void setPositionCd(String value) {
        this.positionCd = value;
    }
    public Object getTargetTime() {
        return targetTime;
    }
    public void setTargetTime(Object value) {
        this.targetTime = value;
    }
    public String getGroupCdDesc() {
        return groupCdDesc;
    }
    public void setGroupCdDesc(String value) {
        this.groupCdDesc = value;
    }
    public String getSubGroup() {
        return subGroup;
    }
    public void setSubGroup(String value) {
        this.subGroup = value;
    }
    public String getActivity() {
        return activity;
    }
    public void setActivity(String value) {
        this.activity = value;
    }
    public String getFailureCause() {
        return failureCause;
    }
    public void setFailureCause(String value) {
        this.failureCause = value;
    }
    public String getAction() {
        return action;
    }
    public void setAction(String value) {
        this.action = value;
    }
    public String getReason() {
        return reason;
    }
    public void setReason(String value) {
        this.reason = value;
    }
    public String getPosition() {
        return position;
    }
    public void setPosition(String value) {
        this.position = value;
    }
    public Double getSoldTime() {
        return soldTime;
    }
    public void setSoldTime(Double double1) {
        this.soldTime = double1;
    }
    public BigDecimal getLabourRate() {
        return labourRate;
    }
    public void setLabourRate(BigDecimal bigDecimal) {
        this.labourRate = bigDecimal;
    }
    public String getLabourTime() {
        return labourTime;
    }
    public void setLabourTime(String value) {
        this.labourTime = value;
    }
    public boolean getShowHide() {
        return showHide;
    }
    public void setShowHide(boolean flag) {
        this.showHide = flag;
    }
    public String getTaskComments() {
        return taskComments;
    }
    public void setTaskComments(String value) {
        this.taskComments = value==null?value:value.trim();
    }
    public BigDecimal getWshpWPNr() {
        return wshpWPNr;
    }
    public void setWshpWPNr(BigDecimal value) {
        this.wshpWPNr = value;
    }
    public Integer getWshpWONr() {
        return wshpWONr;
    }
    public void setWshpWONr(Integer value) {
        this.wshpWONr = value;
    }
    public Integer getWshpWOTNr() {
        return wshpWOTNr;
    }
    public void setWshpWOTNr(Integer value) {
        this.wshpWOTNr = value;
    }
    public boolean getAttribute() {
        return attribute;
    }
    public void setAttribute(boolean flag) {
        this.attribute = flag;
    }
    public String getIsApproved() {
        return isApproved;
    }
    public void setIsApproved(String value) {
        this.isApproved = value;
    }
    public String getIsRejected() {
        return isRejected;
    }
    public void setIsRejected(String value) {
        this.isRejected = value;
    }


   
}
