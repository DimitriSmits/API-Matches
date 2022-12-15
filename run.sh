#!/bin/bash
docker run -it --rm -p 8081:8081 --volume=$(pwd)/gitignore/docker-maven-cache:/app/.m2 gamerdates-api-auth
