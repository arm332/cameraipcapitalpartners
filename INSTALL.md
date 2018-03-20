# INSTALL.md

Setup initial programming environment and deploy a hello world application.

## Setup

### Install Java 7 (Google App Engine dependency)

### Install Eclipse IDE for Java EE Developers (Mars)

### Install Google Plugin for Eclipse

- Eclipse . Help > Install New Software
- https://dl.google.com/eclipse/plugin/4.5
- Google App Engine Engine Maven Integration
- Google Plugin for Eclipse 4.4/4.5
- Google App Engine Engine Java SDK

### Create a new Google App Engine server

- Window > Show View > Servers
- Right Click > New > Server
- Google > Google App Engine
- Next > Next > Finish
	
### Create a new Maven Project

- File > New > Maven Project
- Create a simple project (skip archetype selection)
- Next
- Group Id: com.example
- Artifact Id: myappid
- Packaging: war
- Finish

### Generate Deployment Descriptor Stub

- Project Explorer > Right-click project > Java EE Tools > Generate Deployment Descriptor Stub

### Check Project Facets

- Project Explorer > Right-click project > Properties > Project Facets
- Dynamic Web Module 2.5
- Java 1.7
- JavaScript 1.0
- Apply > Ok

### Edit pom.xml
	
	<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
		<modelVersion>4.0.0</modelVersion>
		<groupId>com.example</groupId>
		<artifactId>myappid</artifactId>
		<version>0.0.1-SNAPSHOT</version>
		<packaging>war</packaging>
		<prerequisites>
			<maven>3.1.0</maven>
		</prerequisites>
		<dependencies>
			<dependency>
				<groupId>junit</groupId>
				<artifactId>junit</artifactId>
				<version>4.12</version>
				<scope>test</scope>
			</dependency>
			<dependency>
				<groupId>javax.servlet</groupId>
				<artifactId>servlet-api</artifactId>
				<version>2.5</version>
				<scope>provided</scope>
			</dependency>
			<dependency>
				<groupId>javax.servlet</groupId>
				<artifactId>jstl</artifactId>
				<version>1.2</version>
			</dependency>
			<dependency>
				<groupId>com.google.appengine</groupId>
				<artifactId>appengine-api-1.0-sdk</artifactId>
				<version>1.9.30</version>
			</dependency>
		</dependencies>
		<build>
			<plugins>
				<plugin>
					<artifactId>maven-compiler-plugin</artifactId>
					<version>3.3</version>
					<configuration>
						<source>1.7</source>
						<target>1.7</target>
					</configuration>
				</plugin>
				<plugin>
					<artifactId>maven-war-plugin</artifactId>
					<version>2.6</version>
					<configuration>
						<failOnMissingWebXml>false</failOnMissingWebXml>
					</configuration>
				</plugin>
			</plugins>
		</build>
	</project>

### Update project with Maven

- Project Explorer > Right-click project > Maven > Update Project...

### Add Google App Engine runtime

- Project > Properties > Targeted Runtimes > Google App Engine
- Apply > OK

### Create Google App Engine configuration file at /src/main/webapp/WEB-INF/appengine-web.xml

	<?xml version="1.0" encoding="utf-8"?>
	<appengine-web-app xmlns="http://appengine.google.com/ns/1.0">
	  <application>myappid</application>
	  <version>1</version>
	
	  <!--
	    Allows App Engine to send multiple requests to one instance in parallel:
	  -->
	  <threadsafe>true</threadsafe>
	
	  <!-- Configure java.util.logging -->
	  <system-properties>
	    <property name="java.util.logging.config.file" value="WEB-INF/logging.properties"/>
	  </system-properties>
	
	  <!--
	    HTTP Sessions are disabled by default. To enable HTTP sessions specify:
	
	      <sessions-enabled>true</sessions-enabled>
	
	    It's possible to reduce request latency by configuring your application to
	    asynchronously write HTTP session data to the datastore:
	
	      <async-session-persistence enabled="true" />
	
	    With this feature enabled, there is a very small chance your app will see
	    stale session data. For details, see
	    https://cloud.google.com/appengine/docs/java/config/appconfig#Java_appengine_web_xml_Enabling_sessions
	  -->
	
	</appengine-web-app>

### Create logging configuration file at /src/main/webapp/WEB-INF/logging.properties

	# A default java.util.logging configuration.
	# (All App Engine logging is through java.util.logging by default).
	#
	# To use this configuration, copy it into your application's WEB-INF
	# folder and add the following to your appengine-web.xml:
	#
	# <system-properties>
	#   <property name="java.util.logging.config.file" value="WEB-INF/logging.properties"/>
	# </system-properties>
	#
	
	# Set the default logging level for all loggers to WARNING
	.level = WARNING

### Create a new JSP file at /ser/main/webapp/index.jsp

	<html>
	<body>
	<h1>Hello World!</h1>
	</body>
	</html>

### Build project (if necessary)

- Project > Build Project

### Run the project on the server

- Right click on project > Run as > Run on Server
- Google App Engine on localhost
- http://localhost:8888/

### Deploying a project to Google App Engine

- Window > Show View > Servers
- Right click on the server > Google App Engine > Deploy to Remote Server
- http://myappid.appspot.com/

## Update to Google Cloud Tools

### Install Eclipse IDE for Java EE Developers, version 4.6 or later

	https://www.eclipse.org/downloads/
	
### Install Google Cloud Tools for Eclipse plugin

	Open Eclipse, select Help > Eclipse Marketplace... and search for Google Cloud.

### Install Google Cloud SDk

	curl https://sdk.cloud.google.com | bash

### Install Google AppEngine components

	gcloud components install app-engine-java

### Import as a Maven project

### Convert if necessarie

### Run as > AppEngine
 
## References

- https://cloud.google.com/eclipse/docs/creating-new-webapp?hl=pt-br
- https://cloud.google.com/eclipse/docs/running-and-debugging?hl=pt-br
- https://stackoverflow.com/q/31037279
- https://stackoverflow.com/a/22294554

