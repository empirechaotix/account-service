build:
	docker build -t empirechaotix/account-service:0.1.0 .
	docker push empirechaotix/account-service:0.1.0
create:
	oc new-project account-service
deploy:
	oc project account-service
	oc apply -f openshift/Deployment.yml
# 	oc apply -f openshift/Gateway.yml
	oc apply -f openshift/Service.yml
undeploy:
	oc project account-service
	oc delete -f openshift/Deployment.yml
# 	oc delete -f openshift/Gateway.yml
	oc delete -f openshift/Service.yml