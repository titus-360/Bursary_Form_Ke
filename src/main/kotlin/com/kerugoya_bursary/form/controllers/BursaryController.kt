package com.kerugoya_bursary.form.controllers

import com.kerugoya_bursary.form.dtos.BursaryApplicationDto
import com.kerugoya_bursary.form.models.BursaryApplication
import com.kerugoya_bursary.form.services.BursaryService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("bursaries")
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
    ): ResponseEntity<Page<BursaryApplication>> {
        return ResponseEntity.ok(service.getAllBursaryApplications(pageable))
    }

    @GetMapping("{id}")
    fun getBursaryById(@PathVariable id: Long): ResponseEntity<BursaryApplication> {
        return ResponseEntity.ok(service.getBursaryApplicationById(id))
    }

    @PostMapping
    fun createBursary(
        @RequestBody bursaryApplication: BursaryApplication
    ): ResponseEntity<BursaryApplication> {
        return ResponseEntity.ok(service.createBursaryApplication(bursaryApplication))
    }

    @PutMapping("{id}")
    fun updateBursary(
        @PathVariable id: Long,
        @RequestBody bursaryApplication: BursaryApplicationDto
    ): ResponseEntity<BursaryApplicationDto> {
        return ResponseEntity.ok(service.updateBursaryApplication(bursaryApplication))
    }

    @DeleteMapping("{id}")
    fun deleteBursaryById(@PathVariable id: Long): ResponseEntity<Void> {
        service.deleteBursaryApplication(id)

        return ResponseEntity.noContent().build()
    }
}
