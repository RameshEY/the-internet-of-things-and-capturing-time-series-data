package mqtt.demo.mqtt

import ch.qos.logback.classic.Logger
import mqtt.demo.MqttListenerService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.endpoint.MessageProducerSupport;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

@Configuration
class BeansConfiguration {
    @Autowired
    MqttListenerService mqttListenerService

    @Value('${mqtt.host}')
    String mqttHost

    @Value('${mqtt.topic}')
    String mqttTopic

    static Logger log = LoggerFactory.getLogger(BeansConfiguration.class)

    @Bean
    DefaultMqttPahoClientFactory mqttClientFactory() {
        log.info("MQTT host = ${mqttHost}")
        DefaultMqttPahoClientFactory defaultMqttPahoClientFactory = new DefaultMqttPahoClientFactory()
        defaultMqttPahoClientFactory.setServerURIs(mqttHost)
        return defaultMqttPahoClientFactory
    }

    @Bean
    MessageChannel mqttInputChannel() {
        return new DirectChannel()
    }

    @Bean
    MessageProducerSupport mqttInbound() {
        log.info("MQTT topic = ${mqttTopic}")
        println "--> ${mqttTopic}"
        MqttPahoMessageDrivenChannelAdapter adapter =
                new MqttPahoMessageDrivenChannelAdapter(
                UUID.randomUUID().toString(),
                mqttClientFactory(),
                mqttTopic
        );

        adapter.setCompletionTimeout(5000)
        adapter.setConverter(new DefaultPahoMessageConverter())
        adapter.setQos(0)
        adapter.setOutputChannel(mqttInputChannel())
        return adapter
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    MessageHandler stringHandler() {
        return new MessageHandler() {
            @Override
            void handleMessage(Message<?> message) throws MessagingException {
                println message.payload.toString()
                mqttListenerService.handleMessage(message)
            }
        }
    }
}
