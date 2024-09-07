package com.example.demo.service

import com.example.demo.exception.NotFoundException
import com.example.demo.model.Car
import com.example.demo.repository.CarRepository

import org.springframework.stereotype.Service

@Service
class CarService(val carRepository: CarRepository) {

    fun get(id: Int): Car {
        return carRepository.findById(id)
            .map { Car(it.id?: -1, it.model, it.color, it.brand, it.plates, it.year) }
            .orElseThrow { NotFoundException("Car not found") }
    }

    fun getAll(): List<Car> {
        return carRepository.findAll()
            .map { Car(it.id?: -1, it.model, it.color, it.brand, it.plates, it.year) }
            .toList()
    }
}