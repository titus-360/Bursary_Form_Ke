package com.kerugoya_bursary.form

import org.springframework.boot.fromApplication
import org.springframework.boot.with


fun main(args: Array<String>) {
    fromApplication<FormApplication>().with(TestcontainersConfiguration::class).run(*args)
}
