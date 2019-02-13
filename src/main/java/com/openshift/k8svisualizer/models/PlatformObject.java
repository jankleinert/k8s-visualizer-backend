package com.openshift.k8svisualizer.models;

public class PlatformObject {

	private String objectID;
	private String objectName;
	private String objectType;
	private String objectStatus;
	
	public PlatformObject(String objectID, String objectName, String objectType, String objectStatus) {
		this.objectID = objectID;
		this.objectName = objectName;
		this.objectType = objectType;
		this.objectStatus = objectStatus;
	}
	public String getObjectID() {
		return objectID;
	}
	public void setObjectID(String objectID) {
		this.objectID = objectID;
	}
	public String getObjectType() {
		return objectType;
	}
	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}
	public String getObjectName() {
		return objectName;
	}
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}
	public String getObjectStatus() {
		return objectStatus;
	}
	public void setObjectStatus(String objectStatus) {
		this.objectStatus = objectStatus;
	}
}
