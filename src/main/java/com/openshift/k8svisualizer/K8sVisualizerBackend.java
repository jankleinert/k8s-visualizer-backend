package com.openshift.k8svisualizer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class K8sVisualizerBackend {

	public static void main(String[] args) {
		SpringApplication.run(K8sVisualizerBackend.class, args);
	}
}
