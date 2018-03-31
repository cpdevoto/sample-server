#!/usr/bin/env sh

cd /opt/wantify

echo java $JAVA_OPTS -DDATABASE_HOST=$DATABASE_HOST -DDATABASE_PORT=$DATABASE_PORT -DDATABASE_NAME=$DATABASE_NAME -DDATABASE_USERNAME=$DATABASE_USERNAME -DDATABASE_PASSWORD=$DATABASE_PASSWORD -Dninja.external.configuration=application.conf -jar wantify-server-$WANTIFY_VERSION.jar

java $JAVA_OPTS -DDATABASE_HOST=$DATABASE_HOST -DDATABASE_PORT=$DATABASE_PORT -DDATABASE_NAME=$DATABASE_NAME -DDATABASE_USERNAME=$DATABASE_USERNAME -DDATABASE_PASSWORD=$DATABASE_PASSWORD -Dninja.external.configuration=application.conf -jar wantify-server-$WANTIFY_VERSION.jar