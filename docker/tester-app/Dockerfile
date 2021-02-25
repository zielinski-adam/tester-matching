FROM openjdk:11.0.10-jre-slim

ENV HOME /home
WORKDIR $HOME

# Exposing port for host (app and debug)
EXPOSE 8080 5005
# Environment variable by which you can pass your own parameters
ENV JAVA_OPTIONS="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005"
COPY run.sh /
RUN chmod 777 /run.sh
# copy files from host into image (project classes last to leverage build cache)
COPY tester-app-*.jar /tester-app.jar

CMD ["bash", "/run.sh"]
