ARG FROM_IMAGE
FROM ${FROM_IMAGE} AS jre

RUN apk update && \
    apk upgrade && \
    apk add --no-cache ca-certificates openssl && \
    rm -rf /var/cache/apk/*

FROM jre AS builder
RUN Java -version
ARG JRE_FILE
COPY build/libs/${JRE_FILE} ${JRE_FILE}
# extract the JAR into its layers:
RUN java -Djarmode=layertools -jar ${JRE_FILE} extract && \
    rm -rf ${JRE_FILE}

FROM jre AS app

RUN adduser -S appuser

ENV JAVA_OPTS=""
ENV SPRING_PROFILE="default"

WORKDIR /app

COPY --from=builder dependencies/ ./
COPY --from=builder spring-boot-loader/ ./
COPY --from=builder snapshot-dependencies/ ./
COPY --from=builder application/ ./

ENTRYPOINT exec java $JAVA_OPTS \
    --add-opens=java.base/java.text=ALL-UNNAMED \
    --add-opens=java.base/java.lang=ALL-UNNAMED \
    --add-opens=java.base/java.util=ALL-UNNAMED \
    -Dspring.profiles.active=$SPRING_PROFILE \
    org.springframework.boot.loader.JarLauncher
USER appuser

#### explain --add-opens=java.base/java.text=ALL-UNNAMED java commandline argument #########
# The --add-opens option is used to open specific packages in the Java base module to all unnamed modules
# like legacy or third party libaries that may not be modularized.

# Java platform module system (JPMS) introduced in java 9, is a major change that allows developers
# to orgnize code into modules, which can encapsulate packages and control visibility.


