package com.tip.fetchchecklist.model;

public class MaintenanceInspectionDetails {

    private int maintenanceCode;

    private String maintenanceDescritpition;

    public int getMaintenanceCode() {
        return maintenanceCode;
    }

    public void setMaintenanceCode(int maintenanceCode) {
        this.maintenanceCode = maintenanceCode;
    }

    public String getMaintenanceDescritpition() {
        return maintenanceDescritpition;
    }

    public void setMaintenanceDescritpition(String maintenanceDescritpition) {
        this.maintenanceDescritpition = maintenanceDescritpition;
    }

    @Override
    public String toString() {
        return "MaintenanceInspectionDetails [maintenanceCode=" + maintenanceCode + ", maintenanceDescritpition="
                + maintenanceDescritpition + "]";
    }
}
