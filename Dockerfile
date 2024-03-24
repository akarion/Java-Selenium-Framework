
FROM maven:3.8.1-openjdk-11

WORKDIR /test

COPY pom.xml .
COPY src ./src

RUN apt-get update && apt-get install -y google-chrome-stable && wget -q -O /tmp/chromedriver.zip https://chromedriver.storage.googleapis.com/2.41/chromedriver_linux64.zip && unzip /tmp/chromedriver.zip chromedriver -d /usr/local/bin/
RUN mvn dependency:go-offline

RUN mvn clean package -DskipTests

CMD ["mvn", "test"]