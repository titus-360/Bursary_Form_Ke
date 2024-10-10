package com.kerugoya_bursary.form.controllers

import com.kerugoya_bursary.form.dtos.FamilyDetailsDto
import com.kerugoya_bursary.form.models.FamilyDetails
import com.kerugoya_bursary.form.services.FamilyDetailsService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("family-details")
class FamilyDetailsController(
    private val service: FamilyDetailsService
) {

    @GetMapping
    fun getAllFamilyDetails(
        @PageableDefault(
            size = 50,
            page = 0,
            sort = ["id"],
            direction = Sort.Direction.DESC
        ) pageable: Pageable
    ): ResponseEntity<Page<FamilyDetails>> {
        return ResponseEntity.ok(service.getAllFamilyDetails(pageable))
    }

    @PostMapping
    fun createFamilyDetail(
        @RequestBody familyDetails: FamilyDetails
    ): ResponseEntity<FamilyDetails> {
        return ResponseEntity.ok(service.createFamilyDetails(familyDetails))
    }

    @GetMapping("{id}")
    fun getFamilyDetailById(@PathVariable id: Long): ResponseEntity<FamilyDetails> {
        return ResponseEntity.ok(service.getFamilyDetailsById(id))
    }

    @PutMapping("{id}")
    fun updateFamilyDetail(
        @PathVariable id: Long,
        @RequestBody familyDetailsDto: FamilyDetailsDto
    ): ResponseEntity<FamilyDetailsDto> {
        return ResponseEntity.ok(service.updateFamilyDetails(id, familyDetailsDto))
    }

    @DeleteMapping("{id}")
    fun deleteFamilyDetailById(@PathVariable id: Long): ResponseEntity<Void> {
        service.deleteFamilyDetails(id)

        return ResponseEntity.noContent().build()
    }

}
