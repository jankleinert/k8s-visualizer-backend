package com.openshift.k8svisualizer.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.openshift.k8svisualizer.helpers.PlatformObjectHelper;
//import com.openshift.k8svisualizer.models.Game;
//import com.openshift.k8svisualizer.models.Score;
import com.openshift.k8svisualizer.models.PlatformObject;


	// oc policy add-role-to-user view system:serviceaccount:wildwest:default where wildwest
	// is the project name

	// To enable destructrive mode, a different permission needs to be added 
	// oc policy add-role-to-user edit system:serviceaccount:wildwest:default

	
@CrossOrigin(origins = "*")
@RestController
public class APIController {
	@Autowired
//	private GameController gameController;

/*	@RequestMapping("/score")
	public Score getScore(@RequestParam(value = "gameID") String gameID) {
		return this.gameController.getGame(gameID).getScore();
	}

	@RequestMapping("/createGame")
	public Game getScore() {
		return gameController.createGame();
	}*/

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
	public List<PlatformObject> getPods() {
		PlatformObjectHelper helper = new PlatformObjectHelper();
		return helper.getPods();
	}

	

	@RequestMapping("/getServices")
	public List<PlatformObject> getServices() {
		PlatformObjectHelper helper = new PlatformObjectHelper();
		return helper.getServices();
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