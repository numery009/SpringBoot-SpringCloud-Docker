# Netfilx-Zull-API-Gateway

Fetaures of the API Gateway--
1) Authentication, Authorization and Security.
2) Rate Limites (i.e. For Specific Client need to allow creatain numbers of call per hr)
3) Fault Tolerance (i.e. not breaking for error and giving a message)
4) Service Aggregation- External consumer who wants to call 15 different services as per a single request. It's better to intregate
   all services and provide one service call for the extranal consumer.
   

Netflix provides the implementation of Zuul. 3 steps to set up a Zuul Server.
1) Create a component for Zuul API Gateway Server.
2) 2nd thing what should the Zuul API do to intercept the request.
3) 3rd thing to make sure right request go through the Zuul API.

Dependencies- Zuul, Eureka Discovery, Actuator and Devtools.

Main method we need to add the @EnableZuulProxy, @EnableDiscoveryClient and Add the Zuul Logging Filter by the Zuul Filter.


# POM File

<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>

	<groupId>com.in28minutes.microservices</groupId>
	<artifactId>netflix-zuul-api-gateway-server</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<packaging>jar</packaging>

	<name>netflix-zuul-api-gateway-server</name>
	<description>Demo project for Spring Boot</description>

	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<!-- <version>2.1.0.M2</version> -->
		<version>2.1.1.RELEASE</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>

	<properties>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
		<project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
		<java.version>1.8</java.version>
		<!-- <spring-cloud.version>Greenwich.BUILD-SNAPSHOT</spring-cloud.version> -->
		<spring-cloud.version>Greenwich.RC2</spring-cloud.version>
	</properties>

	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-actuator</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-zuul</artifactId>
		</dependency>

		<!-- This is for Distributed Tracing -->
		<!-- This will help to add a unique Id on the request -->
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-sleuth</artifactId>
		</dependency>
		<!-- End -->
		
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-zipkin</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.amqp</groupId>
			<artifactId>spring-rabbit</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>

	<dependencyManagement>
		<dependencies>
			<dependency>
				<groupId>org.springframework.cloud</groupId>
				<artifactId>spring-cloud-dependencies</artifactId>
				<version>${spring-cloud.version}</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>
		</dependencies>
	</dependencyManagement>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
			<plugin>
				<groupId>io.fabric8</groupId>
				<artifactId>docker-maven-plugin</artifactId>
				<version>0.28.0</version>

				<configuration>
					<!-- <dockerHost>http://127.0.0.1:2375</dockerHost> -->
					<dockerHost>unix:///var/run/docker.sock</dockerHost>
					<!-- this is for Mac and Amazon Linux -->
					<!-- <dockerHost>unix:///var/run/docker.sock</dockerHost> -->

					<verbose>true</verbose>

					<!-- Needed if pushing to DockerHub: preferred to store these in local 
						environment (see the course) -->
					<!-- <authConfig> <username>YOUR-USERNAME</username> <password>YOUR-PASSWORD</password> 
						</authConfig> -->

					<images>
						<image>
							<name>netflix-zuul-api-gateway-server</name>
							<build>
								<dockerFileDir>${project.basedir}/src/main/docker/</dockerFileDir>

								<!--copies Jar to the maven directory (uses Assembly system) -->
								<assembly>
									<descriptorRef>artifact</descriptorRef>
								</assembly>
								<tags>
									<tag>latest</tag>
								</tags>
							</build>
						</image>
					</images>
				</configuration>
				<executions>
					<execution>
						<phase>package</phase>
						<goals>
							<goal>build</goal>
						</goals>
					</execution>
				</executions>
			</plugin>
		</plugins>
	</build>

	<repositories>
		<repository>
			<id>spring-snapshots</id>
			<name>Spring Snapshots</name>
			<url>https://repo.spring.io/snapshot</url>
			<snapshots>
				<enabled>true</enabled>
			</snapshots>
		</repository>
		<repository>
			<id>spring-milestones</id>
			<name>Spring Milestones</name>
			<url>https://repo.spring.io/milestone</url>
			<snapshots>
				<enabled>false</enabled>
			</snapshots>
		</repository>
	</repositories>

	<pluginRepositories>
		<pluginRepository>
			<id>spring-snapshots</id>
			<name>Spring Snapshots</name>
			<url>https://repo.spring.io/snapshot</url>
			<snapshots>
				<enabled>true</enabled>
			</snapshots>
		</pluginRepository>
		<pluginRepository>
			<id>spring-milestones</id>
			<name>Spring Milestones</name>
			<url>https://repo.spring.io/milestone</url>
			<snapshots>
				<enabled>false</enabled>
			</snapshots>
		</pluginRepository>
	</pluginRepositories>


</project>

# Application Properties

-- Name of the Application
spring.application.name=netflix-zuul-api-gateway-server
-- Port of the Server
server.port=8765

-- Register the Zuul API Server in the Eureka naming Server in the local machine
#eureka.client.serviceUrl.defaultZone=http://localhost:8761

-- Register the Zuul API Server in the Eureka naming Server in the Docker Container
eureka.client.serviceUrl.defaultZone=http://netflix-eureka-naming-server:8761/eureka/


# Docker File

FROM openjdk:8u181-jdk-stretch

MAINTAINER Numery Zaber "support@softwaredeveloper.com"

EXPOSE 8765

COPY maven/netflix-zuul-api-gateway-server-0.0.1-SNAPSHOT.jar netflix-zuul-api-gateway-server.jar 

CMD ["java","-jar","netflix-zuul-api-gateway-server.jar"]
