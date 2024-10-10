package com.kerugoya_bursary.form.controllers

import com.kerugoya_bursary.form.dtos.ParentDetailsDto
import com.kerugoya_bursary.form.models.ParentDetails
import com.kerugoya_bursary.form.services.ParentDetailsService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("parents-details")
class ParentsDetailsController(
    private val service: ParentDetailsService
) {

    @GetMapping
    fun getAllParents(
        @PageableDefault(
            size = 50,
            page = 0,
            sort = ["id"],
            direction = Sort.Direction.DESC
        ) pageable: Pageable
    ): ResponseEntity<Page<ParentDetails>> {
        return ResponseEntity.ok(service.getAllParentDetails(pageable))
    }

    @PostMapping
    fun createParent(
        @RequestBody parentDetails: ParentDetails
    ): ResponseEntity<ParentDetails> {
        return ResponseEntity.ok(service.createParentDetails(parentDetails))
    }

    @GetMapping("{id}")
    fun getParentsById(@PathVariable id: Long): ResponseEntity<ParentDetails> {
        return ResponseEntity.ok(service.getParentDetailsById(id))
    }

    @PutMapping("{id}")
    fun updateParents(
        @PathVariable id: Long,
        @RequestBody parentDetailsDto: ParentDetailsDto
    ): ResponseEntity<ParentDetails> {
        return ResponseEntity.ok(service.updateParentDetails(id, parentDetailsDto))
    }

    @DeleteMapping("{id}")
    fun deleteParentById(@PathVariable id: Long): ResponseEntity<Void> {
        service.deleteParentDetails(id)

        return ResponseEntity.noContent().build()
    }
}
