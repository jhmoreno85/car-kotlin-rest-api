package com.example.demo.service

import com.example.demo.exception.NotFoundException
import com.example.demo.model.Car
import com.example.demo.producer.CarEventProducer
import com.example.demo.repository.CarRepository
import com.example.demo.repository.dto.CarDto

import com.fasterxml.jackson.databind.ObjectMapper

import org.springframework.stereotype.Service

@Service
class CarService(val carRepository: CarRepository, val carEventProducer: CarEventProducer, val mapper: ObjectMapper) : Crud<Car> {

    override fun get(id: Int): Car {
        return carRepository.findById(id)
            .map { Car(it.id ?: -1, it.model, it.color, it.brand, it.plates, it.vin, it.year) }
            .orElseThrow { NotFoundException("Car not found") }
    }

    override fun getAll(): List<Car> {
        return carRepository.findAll()
            .map { Car(it.id ?: -1, it.model, it.color, it.brand, it.plates, it.vin, it.year) }
            .toList()
    }

    override fun save(car: Car): Car {
        val result = carRepository.save(CarDto(car.model, car.color, car.brand, car.plates, car.vin, car.year))
        car.id = result.id ?: throw IllegalStateException("ID was not created")
        return car
    }

    override fun saveEvent(car: Car) {
        carEventProducer.sendMessage(mapper.writeValueAsString(car))
    }

    override fun update(car: Car): Car {
        val carDto = carRepository.findById(car.id.toInt())
            .orElseThrow { IllegalArgumentException("Car doesn't exist") }
        carDto.model = car.model
        carDto.brand = car.brand
        carDto.color = car.color
        carDto.plates = car.plates
        carDto.vin = car.vin
        carDto.year = car.year
        carRepository.save(carDto)
        return car
    }

    override fun delete(id: Int) {
        carRepository.findById(id)
            .ifPresentOrElse({ carRepository.deleteById(id) }, { throw NotFoundException("Car not found") })
    }
}