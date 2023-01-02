package com.example.requestservice

import org.junit.FixMethodOrder
import org.junit.jupiter.api.Test
import org.junit.runners.MethodSorters
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class RequestServiceApplicationTests {


    private val baseUrl = "http://localhost:8080/products/"
    private val jsonContentType = MediaType(MediaType.APPLICATION_JSON.type, MediaType.APPLICATION_JSON.subtype)
    @Autowired
    private lateinit var mockMvc: MockMvc

//    @Autowired
//    private lateinit var webAppContext: WebApplicationContext

//    @Before // Этот метод будет запущен перед каждым тестом
//    fun before() {
//        mockMvc = webAppContextSetup(webAppContext).build() // Создаем объект с контекстом придожения
//    }

    @Test
    fun `1 - Get empty list of products`() { // Так можно красиво называть методы
        val request = get(baseUrl).contentType(jsonContentType) // Создаем GET запрос по адресу http://localhost:8080/products/ с http заголовком Content-Type: application/json

        mockMvc.perform(request) // Выполняем запрос
            .andExpect(status().isOk) // Ожидаем http статус 200 OK
            .andExpect(content().json("[]", true)) // ожидаем пустой JSON массив в теле ответа
    }
    // Далее по аналогии
    @Test
    fun `2 - Add first product`() {
        val passedJsonString = """
            {
                "name": "iPhone 4S",
                "description": "Mobile phone by Apple"
            }
        """.trimIndent()

        val request = post(baseUrl).contentType(jsonContentType).content(passedJsonString)

        val resultJsonString = """
            {
                "name": "iPhone 4S",
                "description": "Mobile phone by Apple",
                "id": 1
            }
        """.trimIndent()

        mockMvc.perform(request)
            .andExpect(status().isCreated)
            .andExpect(content().json(resultJsonString, true))
    }

    @Test
    fun `3 - Update first product`() {
        val passedJsonString = """
            {
                "name": "iPhone 4S",
                "description": "Smart phone by Apple"
            }
        """.trimIndent()

        val request = put(baseUrl + "1").contentType(jsonContentType).content(passedJsonString)

        val resultJsonString = """
            {
                "name": "iPhone 4S",
                "description": "Smart phone by Apple",
                "id": 1
            }
        """.trimIndent()

        mockMvc.perform(request)
            .andExpect(status().isOk)
            .andExpect(content().json(resultJsonString, true))
    }

    @Test
    fun `4 - Get first product`() {
        val request = get(baseUrl + "2").contentType(jsonContentType)

        val resultJsonString = """
            {
                "name": "iPhone 4S",
                "description": "Mobile phone by Apple",
                "id": 1
            }
        """.trimIndent()

        mockMvc.perform(request)
            .andExpect(status().isFound)
            .andExpect(content().json(resultJsonString, true))
    }

    @Test
    fun `5 - Get list of products, with one product`() {
        val request = get(baseUrl).contentType(jsonContentType)

        val resultJsonString = """
            [
                {
                    "name": "iPhone 4S",
                    "description": "Smart phone by Apple",
                    "id": 1
                }
            ]
        """.trimIndent()

        mockMvc.perform(request)
            .andExpect(status().isOk)
            .andExpect(content().json(resultJsonString, true))
    }

    @Test
    fun `6 - Delete first product`() {
        val request = delete(baseUrl + "1").contentType(jsonContentType)

        mockMvc.perform(request).andExpect(status().isOk)
    }

}
