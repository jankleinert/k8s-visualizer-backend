package com.openshift.k8svisualizer.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.openshift.k8svisualizer.helpers.PlatformObjectHelper;
import com.openshift.k8svisualizer.models.PlatformObject;
import com.openshift.k8svisualizer.models.PlatformObjectPod;


	// oc policy add-role-to-user view system:serviceaccount:visualizer:default where visualizer
	// is the project name


@CrossOrigin(origins = "*")
@RestController
public class APIController {
	
	@RequestMapping("/egg")
	public String easterEgg() {
		return "Every app needs an easter egg!!";
	}
	
	@RequestMapping("/objects")
	public List<PlatformObject> getPlatformObjects() {
		PlatformObjectHelper helper = new PlatformObjectHelper();
		return helper.getPlatformObjects();
	}

	@RequestMapping("/getRandomObject")
	public PlatformObject getRandomPlatformObject() {
		PlatformObjectHelper helper = new PlatformObjectHelper();
		return helper.getRandomPlatformObject();
	}

	@RequestMapping("/getPods")
	public List<PlatformObjectPod> getPods() {
		PlatformObjectHelper helper = new PlatformObjectHelper();
		return helper.getPods();
	}

	@RequestMapping("/getServices")
	public List<PlatformObject> getServices() {
		PlatformObjectHelper helper = new PlatformObjectHelper();
		return helper.getServices();
	}

	@RequestMapping("/getDeployments")
	public List<PlatformObject> getDeployments() {
		PlatformObjectHelper helper = new PlatformObjectHelper();
		return helper.getDeployments();
	}

	@CrossOrigin
	@RequestMapping("/deleteObject")
	public void deletePlatformObject(@RequestParam(value = "gameID") String gameID, 
							@RequestParam(value = "objectID") String objectID, 
							@RequestParam(value = "objectType") String objectType,
							@RequestParam(value = "objectName") String objectName) {

		PlatformObjectHelper helper = new PlatformObjectHelper();
		helper.deletePlatformObject(gameID, objectID, objectType, objectName);
	}
	
}