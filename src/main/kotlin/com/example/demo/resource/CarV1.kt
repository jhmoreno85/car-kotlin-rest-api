package com.example.demo.resource

import com.example.demo.model.Car
import com.example.demo.service.CarService

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/car")
class CarV1 (val carService: CarService) {

    @GetMapping("/{id}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun get(@PathVariable id: Int): Car {
        return carService.get(id)
    }

    @GetMapping("", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAll(): List<Car> {
        return carService.getAll()
    }
}