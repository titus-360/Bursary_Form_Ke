package com.kerugoya_bursary.form

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.testcontainers.utility.TestcontainersConfiguration

@SpringBootTest
@Import(TestcontainersConfiguration::class)
class FormApplicationTests {

    @Test
    fun contextLoads() {
    }

}
