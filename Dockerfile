FROM openjdk:11

ARG SBT_VERSION=1.3.7

RUN \
  curl -L -o sbt-$SBT_VERSION.deb https://dl.bintray.com/sbt/debian/sbt-$SBT_VERSION.deb && \
  dpkg -i sbt-$SBT_VERSION.deb && \
  rm sbt-$SBT_VERSION.deb && \
  apt-get update && \
  apt-get install -y sbt libxrender1 libxtst6 libxi6

WORKDIR /monopoly
ADD . /monopoly
CMD sbt run

# docker build -t monopoly .    <- build Image Command
# docker run -ti --rm -e DISPLAY=(eigene IP):0.0 monopoly     <- run command (192.168.1.12)