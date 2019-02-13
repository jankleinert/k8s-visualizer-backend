package com.openshift.k8svisualizer.models;

public class PlatformObject {

	public String objectID;
	public String objectName;
	public String objectType;

	
	public PlatformObject(String objectID, String objectName, String objectType) {
		this.objectID = objectID;
		this.objectName = objectName;
		this.objectType = objectType;

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
}
