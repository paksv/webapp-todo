FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .

RUN mvn -e -B dependency:resolve

COPY src ./src

RUN mvn -e -B package

FROM tomcat:10.1.19-jre21-temurin AS runtime

EXPOSE 8080

RUN rm -rf /usr/local/tomcat/webapps/* \
    && rm -rf /usr/local/tomcat/webapps.dist \
    && mkdir -p /usr/local/tomcat/webapps

ENV CATALINA_OPTS="-Dorg.apache.catalina.startup.VersionLoggerListener.log=false \
                   -Djava.security.egd=file:/dev/./urandom \
                   -Xms512M -Xmx1024M"

COPY --from=build /app/target/webapp-todo.war /usr/local/tomcat/webapps/ROOT.war

COPY lib/mysql-connector-java-8.0.30.jar /usr/local/tomcat/lib

CMD ["/usr/local/tomcat/bin/catalina.sh", "run"]