package ru.liga.demo.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfiguration {

    @Bean
    public NewTopic demoTopic() {
        return new NewTopic("camunda-demo", 1, (short) 1);
    }

    @Bean
    public NewTopic testTopic() {
        return TopicBuilder.name("test-topic").build();
    }
}
