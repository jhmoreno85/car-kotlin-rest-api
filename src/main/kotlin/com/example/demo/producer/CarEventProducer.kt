package com.example.demo.producer

import com.example.demo.config.TOPIC_NAME

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

import java.util.UUID

@Component
class CarEventProducer(private val kafkaTemplate: KafkaTemplate<String, String>) {

    fun sendMessage(message: String) {
        kafkaTemplate.send(TOPIC_NAME, UUID.randomUUID().toString(), message)
    }
}