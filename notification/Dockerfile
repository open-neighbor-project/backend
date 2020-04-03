FROM openliberty/open-liberty:kernel-java8-openj9-ubi
ARG VERSION=1.0
ARG REVISION=SNAPSHOT
LABEL \
  org.opencontainers.image.authors="Your Name" \
  org.opencontainers.image.vendor="Open Liberty" \
  org.opencontainers.image.url="local" \
  org.opencontainers.image.source="https://github.com/open-neighbor-project/backend/notification" \
  org.opencontainers.image.version="$VERSION" \
  org.opencontainers.image.revision="$REVISION" \
  vendor="Open Liberty" \
  name="notification" \
  version="$VERSION-$REVISION" \
  summary="The notification microservice from the Open Neighbor project" \
  description="This image contains the notification microservice running with the Open Liberty runtime."
COPY --chown=1001:0 src/main/liberty/config /config/
COPY --chown=1001:0 target/notification.war /config/apps
RUN configure.sh