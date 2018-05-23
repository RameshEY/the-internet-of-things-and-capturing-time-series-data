package mqtt.demo

import grails.gorm.transactions.Transactional
import org.influxdb.dto.Point
import org.influxdb.dto.Query
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.influxdb.DefaultInfluxDBTemplate

import java.util.concurrent.TimeUnit

@Transactional
class InfluxDBWriterService {

    @Autowired
    DefaultInfluxDBTemplate defaultInfluxDBTemplate

    def writeToInfluxDB(json) {
        Point point = Point.measurement("temperature")
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .tag("location", json.location)
                .addField("temperature", new Double(json.temperature))
                .build()


        defaultInfluxDBTemplate.write(point)
    }
}
