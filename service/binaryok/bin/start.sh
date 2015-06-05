#!/bin/bash -e

# This script is meant to be run inside Docker.
# You can run this as bin/start.sh from project root after a gradle build

if [ -d build/libs ]; then
    readonly INSTALL_DIR=build/libs
else
    readonly INSTALL_DIR=$(pwd)
fi

JAVA_OPTS="-d64 -Xmx1g -Xss512k -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -Duser.country=SE -Duser.language=sv \
           -Dsun.jnu.encoding=UTF-8 -Dfile.encoding=UTF-8 -Xnoagent"
COMPUTERNAME=dev
readonly LANG=sv_SE.UTF-8

if [ -n "$ENV" ]; then
    case $ENV in
        prod)
            JAVA_OPTS="${JAVA_OPTS} -javaagent:$INSTALL_DIR/newrelic.jar -Dnewrelic.environment=prod"
            PROFILE="--spring.profiles.active=prod"
            ;;
        stage)
            JAVA_OPTS="${JAVA_OPTS} -javaagent:$INSTALL_DIR/newrelic.jar -Dnewrelic.environment=stage"
            PROFILE="--spring.profiles.active=stage"
            ;;
        dev)
            JAVA_OPTS="${JAVA_OPTS}"
            PROFILE="--spring.profiles.active=dev,altsections"
            ;;
        *)
            echo "ENV must be prod, stage or dev"
            exit 1
            ;;
    esac
fi

CMD="java $JAVA_OPTS -jar $INSTALL_DIR/svtservice-svtse-api*.jar --server.port=8080 --logging.config=classpath:logback-json.xml $PROFILE"

echo "Starting SVT.SE API with command: $CMD"
exec $CMD
