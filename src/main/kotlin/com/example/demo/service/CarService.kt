package com.example.demo.service

import com.example.demo.exception.NotFoundException
import com.example.demo.model.Car
import com.example.demo.repository.CarRepository
import com.example.demo.repository.dto.CarDto

import org.springframework.stereotype.Service

@Service
class CarService(val carRepository: CarRepository) {

    fun get(id: Int): Car {
        return carRepository.findById(id)
            .map { Car(it.id ?: -1, it.model, it.color, it.brand, it.plates, it.year) }
            .orElseThrow { NotFoundException("Car not found") }
    }

    fun getAll(): List<Car> {
        return carRepository.findAll()
            .map { Car(it.id ?: -1, it.model, it.color, it.brand, it.plates, it.year) }
            .toList()
    }

    fun save(car: Car): Car {
        val result = carRepository.save(CarDto(car.model, car.color, car.brand, car.plates, car.year))
        car.id = result.id ?: throw IllegalStateException("ID was not created")
        return car
    }

    fun update(car: Car): Car {
        val carDto = carRepository.findById(car.id.toInt())
            .orElseThrow { IllegalArgumentException("Car doesn't exist") }
        carDto.model = car.model
        carDto.brand = car.brand
        carDto.color = car.color
        carDto.plates = car.plates
        carDto.year = car.year
        carRepository.save(carDto)
        return car
    }
}