package com.tip.fetchattention.model;

import java.util.Date;

public class Attention {
    private String category;
    private Date dueDate;
    private String dueOverDue;
    private String comments;
    private int workPackNr;
    private int workOrderNr;

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the dueDate
     */
    public Date getDueDate() {
        return dueDate;
    }

    /**
     * @param dueDate the dueDate to set
     */
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * @return the dueOverDue
     */
    public String getDueOverDue() {
        return dueOverDue;
    }

    /**
     * @param dueOverDue the dueOverDue to set
     */
    public void setDueOverDue(String dueOverDue) {
        this.dueOverDue = dueOverDue;
    }

    /**
     * @return the comments
     */
    public String getComments() {
        return comments;
    }

    /**
     * @param comments the comments to set
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * @return the workPackNr
     */
    public int getWorkPackNr() {
        return workPackNr;
    }

    /**
     * @param workPackNr the workPackNr to set
     */
    public void setWorkPackNr(int workPackNr) {
        this.workPackNr = workPackNr;
    }

    /**
     * @return the workOrderNr
     */
    public int getWorkOrderNr() {
        return workOrderNr;
    }

    /**
     * @param workOrderNr the workOrderNr to set
     */
    public void setWorkOrderNr(int workOrderNr) {
        this.workOrderNr = workOrderNr;
    }

    @Override
    public String toString() {
        return "Attention [category=" + category + ", dueDate=" + dueDate + ", dueOverDue=" + dueOverDue + ", comments="
                + comments + ", workPackNr=" + workPackNr + ", workOrderNr=" + workOrderNr + "]";
    }

}
