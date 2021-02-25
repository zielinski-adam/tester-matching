#!/usr/bin/env bash
set -e
cd ${BASH_SOURCE%/*}

JAVA_OPTIONS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005"
java ${JAVA_OPTIONS} -jar "/tester-app.jar"
