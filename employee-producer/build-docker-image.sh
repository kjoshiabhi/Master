#!/bin/bash
echo "About to new build Docker image for Employee Service"
echo Docker repo is ${docker.repo}
echo Docker image name is ${docker.image.name}
docker build --tag ${docker.repo}/${docker.image.name} .
docker push ${docker.repo}/${docker.image.name}
docker service rm ${docker.servicename}
docker service create --name ${docker.servicename} \
  --network services \
  --label se.telenor.service.type=services \
  --label se.telenor.service.group=customer \
  --container-label se.telenor.service.type=services \
  --container-label se.telenor.service.group=customer \
  --constraint='node.role == worker' \
  --constraint='node.hostname != dmgrintd01.bredband.local' \
  --constraint='node.hostname != dmgrintd01.bredband.local' \
  --restart-max-attempts=5 \
  --limit-cpu 4 \
  --limit-memory 2048m \
  ${docker.repo}/${docker.image.name} -Djava.net.preferIPv4Stack=true