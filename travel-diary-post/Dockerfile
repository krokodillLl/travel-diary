FROM bellsoft/liberica-openjdk-alpine-musl:17

WORKDIR /opt

COPY target/*.jar travel-diary-post.jar

ENV JAVA_TOOL_OPTIONS -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005

CMD ["java","-jar","travel-diary-post.jar"]
