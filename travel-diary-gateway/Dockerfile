FROM bellsoft/liberica-openjdk-alpine-musl:17

WORKDIR /opt

COPY build/libs/*.jar travel-diary-gateway.jar

RUN apk --no-cache add curl

ENV JAVA_TOOL_OPTIONS -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005

CMD ["java","-jar","travel-diary-gateway.jar"]
