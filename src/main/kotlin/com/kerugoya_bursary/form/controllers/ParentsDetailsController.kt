package com.kerugoya_bursary.form.controllers

import com.kerugoya_bursary.form.models.ParentDetails
import com.kerugoya_bursary.form.services.ParentDetailsService
import com.kerugoya_bursary.form.services.serviceImp.ParentDetailsServiceImp
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("kerugoya_bursary/parentsDetails")
class ParentsDetailsController(
    private val service: ParentDetailsService
) {

    @GetMapping
    fun getAll(
        @PageableDefault(
            size = 50,
            page = 0,
            sort = ["id"],
            direction = Sort.Direction.DESC
        ) pageable: Pageable
    ): ResponseEntity<List<ParentDetails>> {
        return ResponseEntity.ok(service.getAllParentDetails(pageable).content)
    }

    @PostMapping
    fun create(
        @RequestBody parentDetails: ParentDetails
    ): ResponseEntity<ParentDetails> {
        return ResponseEntity.ok(service.createParentDetails(parentDetails))
    }

    @GetMapping("get-by-id/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<ParentDetails> {
        return ResponseEntity.ok(service.getParentDetailsById(id))
    }

    @PutMapping("update")
    fun update(
        @RequestBody parentDetails: ParentDetails
    ): ResponseEntity<ParentDetails> {
        return ResponseEntity.ok(service.updateParentDetails(parentDetails))
    }

    @DeleteMapping("delete/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        service.deleteParentDetails(id)

        return ResponseEntity.noContent().build()
    }
}