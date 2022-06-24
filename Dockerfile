# The first line is alwasy FROM that defines a base image
FROM tomcat:8.0-jre8

# Adding info about who created this image
LABEL maintainer="Andrew H"

# What do we do with the WAR file with respect to TOMCAT's webapps directory
ADD target/employee-servlet-app-original.war /usr/local/tomcat/webapps
# The webapps directory contains the app that tomcat serves

# Expose Port 8080 from the container
EXPOSE 8080

# CMD instruction specifies what to run when the container is running
# In our case the tomcat server is started by running a shell script
CMD ["catalina.sh", "run"]