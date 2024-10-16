package com.example.demo.resource

import com.example.demo.model.Car
import com.example.demo.service.Crud

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/car", produces = [MediaType.APPLICATION_JSON_VALUE])
class CarControllerV1 (val carService: Crud<Car>) {

    @GetMapping("/{id}")
    fun get(@PathVariable id: Int): Car {
        return carService.get(id)
    }

    @GetMapping("")
    fun getAll(): List<Car> {
        return carService.getAll()
    }

    @PostMapping("", consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun save(@RequestBody car: Car): Car {
        return carService.save(car)
    }

    @PostMapping("/event", consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun saveEvent(@RequestBody car: Car) {
        carService.saveEvent(car)
    }

    @PutMapping("", consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun update(@RequestBody car: Car): Car {
        return carService.update(car)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int) {
        carService.delete(id)
    }
}