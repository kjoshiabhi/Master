#!/bin/sh
ARTIFACTS_DIR=/home/docker/jenkins/services/employee-producer
JOB_NAME=$1
APP_SERVER=$2
echo JOB_NAME is $JOB_NAME
echo APP_SERVER is $APP_SERVER
echo Lets clean old resources in the artifacts directory for server - $APP_SERVER
ssh docker@$APP_SERVER "rm $ARTIFACTS_DIR/*"
echo Lets start with moving artifacts to - $APP_SERVER
scp /app/jenkins/jenkinshome/workspace/$JOB_NAME/employee-producer/target/*.jar docker@$APP_SERVER:$ARTIFACTS_DIR
scp /app/jenkins/jenkinshome/workspace/$JOB_NAME/employee-producer/target/classes/docker/* docker@$APP_SERVER:$ARTIFACTS_DIR
ssh docker@$APP_SERVER "chmod 777 $ARTIFACTS_DIR/build-docker-image.sh"
ssh docker@$APP_SERVER "chmod 777 /$ARTIFACTS_DIR/Dockerfile"
ssh docker@$APP_SERVER "source ~/.bash_profile;cd $ARTIFACTS_DIR/;./build-docker-image.sh"
echo Done with deploy-service.sh for employee-producer