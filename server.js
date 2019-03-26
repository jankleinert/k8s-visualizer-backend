const express = require('express')
const app = express()
const port = 8080


const Client = require('kubernetes-client').Client
const config = require('kubernetes-client').config
const k8sclient = new Client({ config: config.fromKubeconfig(), version: '1.11' })

app.get('/', (req, res) =>  {
    
    res.send('Hello World!')
});

app.get('/getPods', (req, res) =>  {
    thePods = [];
    k8sclient.api.v1.namespaces('hello-k8s').pods.get().then((response) => {
            console.log(response.body.items);
            for (var i = 0; i < response.body.items.length; i++) {
                thePods.push({'objectName': response.body.items[i].metadata.name, 'objectType': "POD", 'obectStatus': response.body.items[i].status.phase})
            }
            res.send(thePods);
    });
});

app.get('/getServices', (req, res) =>  {
    theServices = [];
    k8sclient.api.v1.namespaces('hello-k8s').services.get().then((response) => {
            console.log(response.body.items);
            for (var i = 0; i < response.body.items.length; i++) {
                theServices.push({'objectName': response.body.items[i].metadata.name, 'objectType': "SERVICE", 'obectStatus': ""})
            }
            res.send(theServices);
    });
});

app.get('/getDeployments', (req, res) =>  {
    theDeployments = [];
    k8sclient.apis.apps.v1beta1.namespaces('hello-k8s').deployments.get().then((response) => {
            console.log(response.body.items);
            for (var i = 0; i < response.body.items.length; i++) {
                theDeployments.push({'objectName': response.body.items[i].metadata.name, 'objectType': "DEPLOYMENT", 'obectStatus': ""})
            }
            res.send(theDeployments);
    });
});

app.listen(port, () => console.log(`Example app listening on port ${port}!`))