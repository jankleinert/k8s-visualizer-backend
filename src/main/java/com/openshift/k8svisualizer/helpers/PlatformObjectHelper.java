package com.openshift.k8svisualizer.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import com.openshift.k8svisualizer.models.PlatformObject;
import com.openshift.k8svisualizer.models.PlatformObjectPod;
import io.fabric8.kubernetes.api.model.extensions.Deployment;
import io.fabric8.kubernetes.api.model.PersistentVolumeClaim;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.Service;
import io.fabric8.openshift.api.model.Build;
import io.fabric8.openshift.api.model.BuildConfig;
import io.fabric8.openshift.api.model.DeploymentConfig;
import io.fabric8.openshift.api.model.Route;
import io.fabric8.openshift.client.DefaultOpenShiftClient;
import io.fabric8.openshift.client.OpenShiftClient;

public class PlatformObjectHelper {

	private OpenShiftClient client;
	private String labelName;
	private String labelValue;

	public PlatformObjectHelper() {
		client = new DefaultOpenShiftClient();
		// Could make these configurable in the future
		labelName = "run";
		labelValue = "hello-k8s";
	}

	public List<PlatformObject> getPlatformObjects() {

		ArrayList<PlatformObject> platformObjects = new ArrayList<>();
		platformObjects.addAll(this.getPods());
		platformObjects.addAll(this.getBuilds());
		platformObjects.addAll(this.getDeploymentConfigs());
		platformObjects.addAll(this.getBuildConfigs());
		platformObjects.addAll(this.getPVs());
		platformObjects.addAll(this.getServices());
		platformObjects.addAll(this.getRoutes());
		platformObjects.addAll(this.getDeployments());

		return platformObjects;

	}

	public PlatformObject getRandomPlatformObject() {
		List<PlatformObject> theObjects = this.getPlatformObjects();
		

		return theObjects.get(new Random().nextInt(theObjects.size()));
	}

	public List<PlatformObjectPod> getPods() {
		String shortName;
		String[] result;
		ArrayList<PlatformObjectPod> thePods = new ArrayList<>();
		List<Pod> pods = client.pods().withLabel(labelName, labelValue).list().getItems();
		for (Pod currPod : pods) {
			result = currPod.getMetadata().getName().split("-");
			if (result.length > 2) {
				shortName = labelValue + "..." + result[result.length - 1];
			} else {
				shortName = labelValue;
			}
			
			thePods.add(new PlatformObjectPod(currPod.getMetadata().getUid(), shortName, "POD", currPod.getStatus().getPhase()));
		}
		return thePods;
	}

	private List<PlatformObject> getBuilds() {
		ArrayList<PlatformObject> theBuilds = new ArrayList<>();

		List<Build> builds = client.builds().list().getItems();
		for (Build currBuild : builds) {
			theBuilds.add(
					new PlatformObject(currBuild.getMetadata().getUid(), currBuild.getMetadata().getName(), "BUILD"));
		}

		return theBuilds;
	}

	private List<PlatformObject> getDeploymentConfigs() {
		ArrayList<PlatformObject> theDeployments = new ArrayList<>();
		List<DeploymentConfig> deploymentConfigs = client.deploymentConfigs().list().getItems();
		for (DeploymentConfig currConfig : deploymentConfigs) {
			theDeployments.add(new PlatformObject(currConfig.getMetadata().getUid(), currConfig.getMetadata().getName(),
					"DEPLOYMENT_CONFIG"));
		}

		return theDeployments;
	}

	private List<PlatformObject> getBuildConfigs() {
		ArrayList<PlatformObject> theList = new ArrayList<>();
		List<BuildConfig> theItems = client.buildConfigs().list().getItems();
		for (BuildConfig currConfig : theItems) {
			theList.add(new PlatformObject(currConfig.getMetadata().getUid(), currConfig.getMetadata().getName(),
					"BUILD_CONFIG"));
		}
		return theList;
	}

	private List<PlatformObject> getPVs() {
		ArrayList<PlatformObject> theList = new ArrayList<>();
		List<PersistentVolumeClaim> theItems = client.persistentVolumeClaims().list().getItems();
		for (PersistentVolumeClaim currConfig : theItems) {
			theList.add(
					new PlatformObject(currConfig.getMetadata().getUid(), currConfig.getMetadata().getName(), "PVC"));
		}
		return theList;
	}

	public List<PlatformObject> getServices() {
		ArrayList<PlatformObject> theList = new ArrayList<>();
		List<Service> theItems = client.services().withLabel(labelName, labelValue).list().getItems();
		for (Service currConfig : theItems) {
			theList.add(new PlatformObject(currConfig.getMetadata().getUid(), currConfig.getMetadata().getName(),
					"SERVICE"));
		}
		return theList;
	}

	public List<PlatformObject> getDeployments() {
		ArrayList<PlatformObject> theList = new ArrayList<>();
		List<Deployment> theItems = client.extensions().deployments().withLabel(labelName, labelValue).list().getItems();
		for (Deployment currConfig : theItems) {
			theList.add(new PlatformObject(currConfig.getMetadata().getUid(), currConfig.getMetadata().getName(),
					"DEPLOYMENT"));
		}
		return theList;
	}
	private List<PlatformObject> getRoutes() {
		ArrayList<PlatformObject> theList = new ArrayList<>();
		List<Route> theItems = client.routes().list().getItems();
		for (Route currConfig : theItems) {
			theList.add(
					new PlatformObject(currConfig.getMetadata().getUid(), currConfig.getMetadata().getName(), "ROUTE"));
		}
		return theList;
	}

	public void deletePlatformObject(String gameID, String objectID, String objectType, String objectName) {

		// right now only delete things we can recover from
		switch (objectType) {
			case "POD":
				client.pods().withName(objectName).delete();
				break;
			case "BUILD":
				client.builds().withName(objectName).delete();
				break;
		}
		
	}

}
