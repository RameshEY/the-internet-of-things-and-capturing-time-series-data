package mqtt.demo

import grails.gorm.transactions.Transactional
import groovy.json.JsonSlurper
import org.influxdb.InfluxDB
import org.influxdb.InfluxDBFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.messaging.Message

@Transactional
class MqttListenerService {
    
    @Value('${influxdb.enable}')
    Boolean influxdbEnable

    InfluxDBWriterService influxDBWriterService
    JsonSlurper jsonSlurper = new JsonSlurper()

    def handleMessage(Message message) {
        String payload = message.payload.toString()
        if(influxdbEnable) {
            influxDBWriterService.writeToInfluxDB(jsonSlurper.parseText(payload))
        } else {
            println(payload)
        }
    }
}
