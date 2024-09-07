package com.example.demo.repository

import com.example.demo.repository.dto.CarDto

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CarDao: CrudRepository<CarDto, Int> {
}