
# Customer-Service-Mysql 
  1) This is a crud of customer micro service. It fetch's database(Mysql) connection and propeties from Config-Server.
  2) To Connect to the Config Server we need to make the peroperties file name bootstrap.properties.
  3) Dependencies we have use here Actuator, Data-Jpa, Web, Sping-Cloud-Config, Nelflix- Hystrix, Devtools, MySQL-Connector-        Java, Netflix Eureka Client, Spring Cloud Sleuth, Spring Cloud Zipkin, Spring Rabbit.
  4) Use @EnableHystrix and @EnableDiscoveryClient to enable the Hystrix and Connect to Eureka Naming Client.
  
  
# POM File
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
	<groupId>com.customer.microservices.customerservicemysql</groupId>
	<artifactId>customer-service-mysql</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>customer-service-mysql</name>
	<description>Demo project for Spring Boot</description>

	<properties>
		<java.version>1.8</java.version>
		<!-- <spring-cloud.version>Greenwich.RC2</spring-cloud.version> -->
		<spring-cloud.version>Greenwich.RC2</spring-cloud.version>
	</properties>

	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-actuator</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-config</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<!-- <scope>runtime</scope> -->
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>

		<!-- This is Eureka Client Dependency -->
		<!-- This will help to register to the Eureka Server -->
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
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
							<name>customer-service-mysql</name>
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


# bootstrap.properties--

--To fetch the properties from the Config Server we need to change
--the name of properties file from application.properties to bootstrap.properties 


--Application Name : spring.application.name=customer-service-mysql

--Default port 8080 for any Boot Service : server.port=8080


--Register this Micro-Service in the Eureka naming Server. We need to provide the URL of Eureka Server: eureka.client.service-url.default-zone=http://localhost:8761/eureka




--connection to the Spring Cloud Config: spring.cloud.config.uri=http://localhost:8888
--configure the profile: spring.profiles.active=dev



