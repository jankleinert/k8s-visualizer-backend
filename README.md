# k8s-visualizer-backend
This is a Java Spring Boot app that interacts with the Kubernetes and OpenShift APIs. It's a stripped down, non-game version of https://github.com/openshift-evangelists/Wild-West-Backend.

# How to deploy on OpenShift
```
$ mvn package
$ odo project create visualizer
$ oc policy add-role-to-user view system:serviceaccount:visualizer:default 
```
(where visualizer is the project name)

```
$ odo app create visualizer
$ odo create java backend --binary=target/k8svisualizer-1.0.jar
$ odo push
$ odo url create --port 8080
```

# API endpoints
/getPods
/getServices
