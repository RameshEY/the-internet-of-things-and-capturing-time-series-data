package mqtt.demo.influxdb

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.influxdb.DefaultInfluxDBTemplate
import org.springframework.data.influxdb.InfluxDBConnectionFactory
import org.springframework.data.influxdb.InfluxDBProperties

@Configuration
@EnableConfigurationProperties(InfluxDBProperties.class)
class InfluxDbConfig {

    @Bean
    public InfluxDBConnectionFactory connectionFactory(final InfluxDBProperties properties) {
        return new InfluxDBConnectionFactory(properties);
    }

    @Bean
    public DefaultInfluxDBTemplate defaultTemplate(final InfluxDBConnectionFactory connectionFactory) {
        return new DefaultInfluxDBTemplate(connectionFactory);
    }

}
