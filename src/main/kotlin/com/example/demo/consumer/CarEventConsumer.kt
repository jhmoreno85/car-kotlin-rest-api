package com.example.demo.consumer

import com.example.demo.config.GROUP_ID
import com.example.demo.config.TOPIC_NAME
import com.example.demo.repository.CarRepository
import com.example.demo.repository.dto.CarDto

import com.fasterxml.jackson.databind.ObjectMapper

import org.slf4j.LoggerFactory

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class CarEventConsumer(val carRepository: CarRepository, val mapper: ObjectMapper) {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    @KafkaListener(topics = [TOPIC_NAME], groupId = GROUP_ID)
    fun listener(message: String) {
        logger.info("Message received: [$message]")
        val car = mapper.readValue(message, CarDto::class.java)
        carRepository.save(CarDto(car.model, car.color, car.brand, car.plates, car.vin, car.year))
    }
}