package com.tip.tyrereading.model;

public class TyreImage {

	private String imageName;
	
	private String imageURL;
	
	/**
	 * @return the imageName
	 */
	public String getImageName() {
		return imageName;
	}
	
	/**
	 * @param imageName the imageName to set
	 */
	
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	
	/**
	 * @return the imageURL
	 */
	public String getImageURL() {
		return imageURL;
	}
	
	/**
	 * @param imageURL the imageURL to set
	 */
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TyreImage [imageName=" + imageName + ", imageURL=" + imageURL + "]";
	}
	
}
