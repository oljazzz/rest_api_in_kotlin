package com.example.requestservice.controller

import com.example.requestservice.model.Product
import com.example.requestservice.service.ProductService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("products")
class ProductController(private val productService: ProductService) {
    @GetMapping // Говорим что экшен принимает GET запрос без параметров в url
    fun index() =
        productService.all() // И возвращает результат метода all нашего сервиса. Функциональная запись с выводом типа

    @PostMapping // Экшен принимает POST запрос без параметров в url
    @ResponseStatus(HttpStatus.CREATED) // Указываем специфический HttpStatus при успешном ответе
    fun create(@RequestBody product: Product) =
        productService.add(product) // Принимаем объект Product из тела запроса и передаем его в метод add нашего сервиса

    @GetMapping("{id}") // Тут мы говорим что это GET запрос с параметром в url (http://localhost/products/{id})
    @ResponseStatus(HttpStatus.FOUND)
    fun read(@PathVariable id: Long) =
        productService.get(id) // Сообщаем что наш id типа Long и передаем его в метод get сервиса

    @PutMapping("{id}")
    fun update(@PathVariable id: Long, @RequestBody product: Product) = productService.edit(
        id,
        product
    ) // Здесь мы принимаем один параметр из url, второй из тела PUT запроса и отдаем их методу edit

    @DeleteMapping("{id}")
    fun delete(@PathVariable id: Long) = productService.remove(id)


}