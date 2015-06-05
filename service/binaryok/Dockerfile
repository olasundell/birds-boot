FROM ubuntu
MAINTAINER ola.sundell@gmail.com

# Add a repo where Oracle JDK can be found.
ENV DEBIAN_FRONTEND noninteractive
RUN echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | sudo /usr/bin/debconf-set-selections

RUN echo "deb http://ppa.launchpad.net/webupd8team/java/ubuntu trusty main" | tee /etc/apt/sources.list.d/webupd8team-java.list && \
        echo "deb-src http://ppa.launchpad.net/webupd8team/java/ubuntu trusty main" | tee -a /etc/apt/sources.list.d/webupd8team-java.list && \
        apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv-keys EEA14886

RUN apt-get update && apt-get install -y oracle-java8-installer

# Auto-accept the Oracle JDK license

ADD build/libs /app
RUN chown -R daemon:daemon /app
RUN chmod 400 /app/*
RUN chmod 500 /app/start.sh

EXPOSE 8080
WORKDIR /app
USER daemon

CMD /app/start.sh
