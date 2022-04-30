FROM tomcat:9.0-alpine

MAINTAINER Christian Vilca<christianvilca.dev@gmail.com>
LABEL author="Christian Vilca"

EXPOSE 8080

copy target/${WAR_FILE} /usr/local/tomcat/webapp/${WAR_FILE}