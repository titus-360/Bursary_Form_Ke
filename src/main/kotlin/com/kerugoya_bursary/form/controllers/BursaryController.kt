package com.kerugoya_bursary.form.controllers

import com.kerugoya_bursary.form.models.BursaryApplication
import com.kerugoya_bursary.form.services.BursaryService
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RequestMapping("kerugoya_bursary/bursary/")
@RestController
class BursaryController(
    private val service: BursaryService
) {

    @GetMapping("get-all-bursaries")
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

    @GetMapping("get-bursary-by-id/{id}")
    fun getBursaryById(@PathVariable id: Long): ResponseEntity<BursaryApplication> {
        return ResponseEntity.ok(service.getBursaryApplicationById(id))
    }

    @PostMapping("save-bursary")
    fun createBursary(
        @RequestBody bursaryApplication: BursaryApplication
    ): ResponseEntity<BursaryApplication> {
        return ResponseEntity.ok(service.createBursaryApplication(bursaryApplication))
    }

    @PutMapping("update-bursary")
    fun updateBursary(
        @RequestBody bursaryApplication: BursaryApplication
    ): ResponseEntity<BursaryApplication> {
        return ResponseEntity.ok(service.updateBursaryApplication(bursaryApplication))
    }

    @DeleteMapping("delete-bursary-by-id/{id}")
    fun deleteBursaryById(@PathVariable id: Long): ResponseEntity<Void> {
        service.deleteBursaryApplication(id)

        return ResponseEntity.noContent().build()
    }
}