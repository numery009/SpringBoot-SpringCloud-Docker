# Customer Account MySql
1) This microservice basically consume the customer-service-mysql by using HTTP/ Feign and Robbin.
2) Request is coming with/ without Zuul.
3) Dependencies - Web, Config-Client, Eureka-Client, Feign, Ribbon, Sleuth, Zipkin, Rabbit
4) @EnableFeignClients("com.customer.microservices.customeraccountmysql.customeraccountmysql"). We have to give the package    name where we need to do the scan.
5) @EnableDiscoveryClient (For registration the Eureka naming server)


# POM

<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.1.1.RELEASE</version>
		<relativePath /> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.customer.microservices.customeraccountmysql</groupId>
	<artifactId>customer-account-mysql</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>customer-account-mysql</name>
	<description>Demo project for Spring Boot</description>

	<properties>
		<java.version>1.8</java.version>
		<spring-cloud.version>Greenwich.RC2</spring-cloud.version>
	</properties>

	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-actuator</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-config</artifactId>
		</dependency>

		<!-- This is Eureka Client Dependency -->
		<!-- This will help to register to the Eureka Server -->
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
		</dependency>
		<!-- End -->


		<!-- To consume other micro service we need to add feign -->
		<!-- Then we need to add the following dependency -->
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-openfeign</artifactId>
		</dependency>
		<!-- End -->


		<!-- To consume different instance of micro service we need to add Ribbon -->
		<!-- Then we need to add the following dependency -->
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-ribbon</artifactId>
		</dependency>
		<!-- End -->

		<!-- This is for Distributed Tracing -->
		<!-- This will help to add a unique Id on the request -->
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-sleuth</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-zipkin</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.amqp</groupId>
			<artifactId>spring-rabbit</artifactId>
		</dependency>
		<!-- End -->

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
							<name>customer-account-mysql</name>
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
			<id>spring-milestones</id>
			<name>Spring Milestones</name>
			<url>https://repo.spring.io/milestone</url>
		</repository>
	</repositories>

</project>

# Properties

spring.application.name=customer-account-mysql
server.port=8100

# We do not need to provide all the Ribbon List of servers URL if we register the Micro Service in the Eureka Naming Server 
# Add the URL of the Consuming Micro Service we need to add the property here 
# customer-service-mysql.ribbon.listOfServers=http://localhost:8080, http://localhost:8081

# Register this Micro-Service in the Eureka naming Server
# We need to provide the URL of Eureka Server for localhost
# eureka.client.service-url.default-zone=http://localhost:8761

# We need to provide the URL of Eureka Server for Docker Container Name
#eureka.client.service-url.default-zone=http://netflix-eureka-naming-server:8761
eureka.client.serviceUrl.defaultZone=http://netflix-eureka-naming-server:8761/eureka/

# Following are the properties of Micro-Services
customer.accountNumber=100234765360
customer.accountType=true
customer.creditAmount=5000000.00

