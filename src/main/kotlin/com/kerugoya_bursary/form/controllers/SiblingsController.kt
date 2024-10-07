package com.kerugoya_bursary.form.controllers

import com.kerugoya_bursary.form.models.Siblings
import com.kerugoya_bursary.form.services.SiblingsService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RequestMapping("kerugoya_bursary/siblings/")
@RestController
class SiblingsController (
    private val service: SiblingsService
){

    @GetMapping("get-all-siblings")
    fun getAllSiblings(
        @PageableDefault(
            size = 50,
            page = 0,
            sort = ["id"],
            direction = Sort.Direction.DESC
        ) pageable: Pageable
    ): ResponseEntity<Page<Siblings>> {
        return ResponseEntity.ok(service.getAllSiblings(pageable))
    }

    @PostMapping("save-siblings")
    fun createSibling(
        @RequestBody siblings: Siblings
    ): ResponseEntity<Siblings> {
        return ResponseEntity.ok(service.createSiblings(siblings))
    }

    @GetMapping("get-sibilinig-by-id/{id}")
    fun getSiblingById(@PathVariable id: Long): ResponseEntity<Siblings> {
        return ResponseEntity.ok(service.getSiblingsById(id))
    }

    @PutMapping("update-sibling")
    fun updateSibling(
        @RequestBody siblings: Siblings
    ): ResponseEntity<Siblings> {
        return ResponseEntity.ok(service.updateSiblings(siblings))
    }

    @DeleteMapping("delete-sibling-by-id/{id}")
    fun deleteSiblingById(@PathVariable id: Long): ResponseEntity<Void> {
        service.deleteSiblings(id)

        return ResponseEntity.noContent().build()
    }
}
