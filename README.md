# k8s-visualizer-backend
This is a Java Spring Boot app that interacts with the Kubernetes and OpenShift APIs. It's a stripped down, non-game version of https://github.com/openshift-evangelists/Wild-West-Backend.

# How to deploy on OpenShift using the web console

Create a new project  
Create a service account "default" for that project with view permissions  
Add to Project > Browse Catalog   
Select Red Hat OpenJDK 8  
Click Next  
Select version 8, give the application a name, enter https://github.com/jankleinert/k8s-visualizer-backend as the repo URL  
Click Create   


# How to deploy on OpenShift using odo
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
