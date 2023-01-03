package com.example.requestservice.service

import com.example.requestservice.model.Product
import com.example.requestservice.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(private val productRepository: ProductRepository) {
    fun all(): Iterable<Product> = productRepository.findAll()

    fun get(id: Long): Product = productRepository.findById(id).orElse(null)

    fun add(product: Product): Product = productRepository.save(product)

    fun edit(id: Long, product: Product): Product =
        productRepository.save(product.copy(id = id))
    // Сохраняем копию объекта с указанным id в БД.
    // Идиоматика Kotlin говорит что НЕ изменяемый
    // - всегда лучше чем изменяемый (никто не поправит значение в другом потоке)
    // и предлагает метод copy для копирования объектов (специальных классов для хранения данных) с возможностью замены значений

    fun remove(id: Long) = productRepository.deleteById(id)
}