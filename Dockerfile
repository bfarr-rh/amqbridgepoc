FROM openjdk:7-jdk-alpine
#FROM jboss/base-jdk:7

ADD jboss-fuse-full-6.1.0.redhat-379.zip .
RUN unzip jboss-fuse-full-6.1.0.redhat-379.zip -d /opt/.
ADD users.properties /opt/jboss-fuse-6.1.0.redhat-379/etc/.
ADD activemq.xml /opt/jboss-fuse-6.1.0.redhat-379/etc/.
EXPOSE 8181 8101 1099 44444 61616 1883 5672 61613 61617 8883 5671 61614 9091
WORKDIR /opt/jboss-fuse-6.1.0.redhat-379
CMD  /opt/jboss-fuse-6.1.0.redhat-379/bin/fuse server
