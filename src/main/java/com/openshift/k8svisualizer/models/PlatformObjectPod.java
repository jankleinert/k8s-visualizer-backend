package com.openshift.k8svisualizer.models;

public class PlatformObjectPod extends PlatformObject {

	private String objectStatus;

	
	public PlatformObjectPod(String objectID, String objectName, String objectType, String objectStatus) {
		super(objectID, objectName, objectType);
		this.objectStatus = objectStatus;
	}
	
	public String getObjectStatus() {
		return objectStatus;
	}
	public void setObjectStatus(String objectStatus) {
		this.objectStatus = objectStatus;
	}
}
