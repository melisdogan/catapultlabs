FROM openjdk:11

ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \
    SERVER_PORT=80

# add directly the war
ADD *.jar /app.jar

EXPOSE ${SERVER_PORT}
CMD java -jar /app.jar