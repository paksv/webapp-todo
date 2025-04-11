FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .

RUN mvn -e -B dependency:resolve

COPY src ./src

RUN mvn -e -B package

FROM tomcat:10.1.19-jre21-temurin AS runtime

RUN apt-get update && apt-get install -y curl \
    && rm -rf /var/lib/apt/lists/* \
    && rm -rf /usr/local/tomcat/webapps/* \
    && rm -rf /usr/local/tomcat/webapps.dist \
    && mkdir -p /usr/local/tomcat/webapps

RUN groupadd -r appuser && useradd -r -g appuser appuser

RUN chown -R appuser:appuser /usr/local/tomcat

USER appuser

ENV CATALINA_OPTS="-Dorg.apache.catalina.startup.VersionLoggerListener.log=false \
                   -Djava.security.egd=file:/dev/./urandom \
                   -Xms512M -Xmx1024M"

COPY --from=build /app/target/webapp-todo.war /usr/local/tomcat/webapps/ROOT.war

COPY lib/mysql-connector-java-8.0.30.jar /usr/local/tomcat/lib

EXPOSE 8080

HEALTHCHECK --interval=10s --timeout=5s --start-period=30s --retries=3 \
  CMD curl -f http://localhost:8080 || exit 1

CMD ["/usr/local/tomcat/bin/catalina.sh", "run"]
