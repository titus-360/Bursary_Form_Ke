package com.kerugoya_bursary.form.controllers

import com.kerugoya_bursary.form.models.BursaryApplication
import com.kerugoya_bursary.form.services.BursaryService
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RequestMapping("kerugoya_bursary/bursary")
@RestController
class BursaryController(
    private val service: BursaryService
) {

    @GetMapping
    fun getBursary(
        @PageableDefault(
            size = 50,
            page = 0,
            sort = ["id"],
            direction = Sort.Direction.DESC
        ) pageable: Pageable
    ): ResponseEntity<List<BursaryApplication>>
    {
        return ResponseEntity.ok(service.getAllBursaryApplications(pageable).content)
    }

    @GetMapping("get-by-id/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<BursaryApplication> {
        return ResponseEntity.ok(service.getBursaryApplicationById(id))
    }

    @PostMapping
    fun create(
        @RequestBody bursaryApplication: BursaryApplication
    ): ResponseEntity<BursaryApplication> {
        return ResponseEntity.ok(service.createBursaryApplication(bursaryApplication))
    }

    @PutMapping("update")
    fun update(
        @RequestBody bursaryApplication: BursaryApplication
    ): ResponseEntity<BursaryApplication> {
        return ResponseEntity.ok(service.updateBursaryApplication(bursaryApplication))
    }

    @DeleteMapping("delete/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        service.deleteBursaryApplication(id)

        return ResponseEntity.noContent().build()
    }
}