#!/usr/bin/env bash
set -e
cd ${BASH_SOURCE%/*}

(cd tester-app && mvn clean package)
(cd tester-web && npm run ng build)
docker-compose build
