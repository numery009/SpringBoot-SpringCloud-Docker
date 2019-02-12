
# Spring Cloud Config Server

Spring Cloud Config Server uses to maintain the celtralize the Configuration. Following steps we need to follow--

1) We need to create a local repositoty same like Micro-Services name. In example we create a folder "customer-service". 
   In this folder we have created for differnt file for each enviroment. (Dev, Test, Stage, Prod)
2) We have a GitHub reposioty in the name "Github-Repository". And commit that folder on the GIthub.
3) Finally we have created a Spring-Boot Application with the dependecy and configure the properties file.
4) Dependncies we have uses here Config_server, Devtools, RabbitMQ.
5) If any change will happen in the Local to Github repository. The reflection will not come up immediately to all the micro    services instance. That's why RabbitMQ dependency has been use.
6) @EnableConfigServer annotation on the main server to Enable the Config Server.
  




POM File--

<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>

	<groupId>com.microservices.springcloudconfigserver</groupId>
	<artifactId>spring-cloud-config-server</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<packaging>jar</packaging>

	<name>spring-cloud-config-server</name>
	<description>Demo project for Spring Boot</description>

	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<!-- <version>2.1.0.M2</version> -->
		<version>2.1.1.RELEASE</version>
		<relativePath /> <!-- lookup parent from repository -->
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
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-config-server</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
		</dependency>

		<!-- <dependency> <groupId>org.springframework.cloud</groupId> <artifactId>spring-cloud-starter-bus-amqp</artifactId> 
			</dependency> -->

		<!-- we have to use the rabbit amqp to come up the reflection -->
		<!-- to all the micro services on production time -->
		<dependency>
			<groupId>org.springframework.amqp</groupId>
			<artifactId>spring-rabbit</artifactId>
		</dependency>
		<!-- End -->

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
							<name>spring-cloud-config-server</name>
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

# Properties File

--Application Name
spring.application.name=spring-cloud-config-server
-- Server port
server.port=8888

-- spring.cloud.config.server.git.uri=file:///home/numery/Desktop/SpringMIcroService/git-localconfig-repo
-- Github URL to connect remote Repository
spring.cloud.config.server.git.uri=https://github.com/numery009/Github-Repository.git
-- Serch path in the remote Repository
spring.cloud.config.server.git.search-paths=customer-service

# Docker File

FROM openjdk:8u181-jdk-stretch

MAINTAINER Numery Zaber "support@softwaredeveloper.com"

EXPOSE 8888

COPY maven/spring-cloud-config-server-0.0.1-SNAPSHOT.jar spring-cloud-config-server.jar 

CMD ["java","-jar","spring-cloud-config-server.jar"]



