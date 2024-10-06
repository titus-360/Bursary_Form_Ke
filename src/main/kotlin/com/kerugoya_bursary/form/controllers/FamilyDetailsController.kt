package com.kerugoya_bursary.form.controllers

import com.kerugoya_bursary.form.models.FamilyDetails
import com.kerugoya_bursary.form.services.FamilyDetailsService
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("kerugoya_bursary/family-details/")
class FamilyDetailsController(
    private val service: FamilyDetailsService
) {

    @GetMapping
    fun getAll(
        @PageableDefault(
            size = 50,
            page = 0,
            sort = ["id"],
            direction = Sort.Direction.DESC
        ) pageable: Pageable):ResponseEntity<List<FamilyDetails>>
     {
        return ResponseEntity.ok(service.getAllFamilyDetails(pageable).content)
    }

    @PostMapping
    fun create(
        @RequestBody familyDetails: FamilyDetails
    ): ResponseEntity<FamilyDetails> {
        return ResponseEntity.ok(service.createFamilyDetails(familyDetails))
    }

    @GetMapping("get-by-id/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<FamilyDetails> {
        return ResponseEntity.ok(service.getFamilyDetailsById(id))
    }

    @PutMapping("update")
    fun update(
        @RequestBody familyDetails: FamilyDetails
    ): ResponseEntity<FamilyDetails> {
        return ResponseEntity.ok(service.updateFamilyDetails(familyDetails))
    }

    @DeleteMapping("delete/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        service.deleteFamilyDetails(id)

        return ResponseEntity.noContent().build()
    }

}