package com.tip.controller;

import java.math.BigDecimal;

/**
 * Created by 885155 on 6/22/2017.
 */
public class Image {
    protected BigDecimal workPacketNumber;
    protected int workOrderNumber;
    protected int workOrderTaskNumber;
    protected String imageType;
    protected String imageName;
    protected String imageLocation;
    protected int noOfImages;

    public BigDecimal getWorkPacketNumber() {
        return workPacketNumber;
    }

    public void setWorkPacketNumber(BigDecimal workPacketNumber) {
        this.workPacketNumber = workPacketNumber;
    }

    public int getWorkOrderNumber() {
        return workOrderNumber;
    }

    public void setWorkOrderNumber(int workOrderNumber) {
        this.workOrderNumber = workOrderNumber;
    }

    public int getWorkOrderTaskNumber() {
        return workOrderTaskNumber;
    }

    public void setWorkOrderTaskNumber(int workOrderTaskNumber) {
        this.workOrderTaskNumber = workOrderTaskNumber;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageLocation() {
        return imageLocation;
    }

    public void setImageLocation(String imageLocation) {
        this.imageLocation = imageLocation;
    }

    public int getNoOfImages() {
        return noOfImages;
    }

    public void setNoOfImages(int noOfImages) {
        this.noOfImages = noOfImages;
    }
}
