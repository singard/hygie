#!/bin/su root
${javaPathLinux} -jar -Xmx1024m ivtracer-install-checker-core-${project.version}.jar --logging.config=logback.xml --spring.config.name=application,mapping --spring.config.location=./
