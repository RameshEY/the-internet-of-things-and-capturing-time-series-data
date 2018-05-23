# General

## Prerequisites

* Docker installed
* Docker Compose installed
* Mosquitto installed (needed for the Mosquitto Tools)
    * `brew install mosquitto`
* MQTT Client installed for example MQTTBOX / MQTT.FX
* RevealMD installed for the presentation (in slides execute ./present.sh)
    * `npm install -g reveal-md`
* For the certificates in the AWS IOT demo, create a thing and download the certificates and place in step4

### Build the required Docker images

* Execute `./build-docker-images`

This will build the following images:

* **Grails-MQTT-Demo**: Docker image containing a Grails app that connects to MQTT and InfluxDB

---

## Step 1a: Run Mosquitto Broker

* Start Mosquitto using docker-compose > dc up
* Show the logging from the container
* Start an MQTT Client (MQTTBOX) and connect
* Show the logging from the container and explain what has happened we have connected

## Step 1b: Publish & Subscribe

* Start Mosquitto using docker-compose > dc up
* Show the logging from the container
* Start the MQTT subscriber
* Start the MQTT publisher
* Show the logging from the container and explain what has happened when we conncted

## Step 2: SpringBoot/Grails and MQTT

* Start Mosquitto/Grails using docker-compose > dc up
* Start the MQTT publisher
* Show the data received by Grails

## Step 3: AWS IoT - Publish & Subscribe

* Show the AWS Console/IOT
* Start the MQTT subscriber
* Start the MQTT publisher

## Step 4: Insert data** using Grails/SpringBoot

* Start Mosquitto/Grails/InfluxDB using docker-compose > dc up
* Exec inside the container that runs InfluxDB
  * docker exec -it influxdb bash
  * influx -username root -password root -database iotdata
* Show the contents of an empty database
* Start the MQTT publisher
* Show that data has been written
* Exec inside the container that runs InfluxDB
  * docker exec -it influxdb bash
    * SELECT (*) FROM temperature
    * SELECT (*) FROM temperature GROUP BY time(5s)
    * SELECT (*) FROM temperature GROUP BY time(5s),*
    * SELECT (*) INTO test FROM temperature GROUP BY time(5s),*

## Step 5: Grafana - InfluxDB

* Start Mosquitto/Grails/InfluxDB/Grafana using docker-compose > dc up
* Connect to InfluxDB and build a Graphs