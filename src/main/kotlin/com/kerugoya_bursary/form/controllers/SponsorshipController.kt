package com.kerugoya_bursary.form.controllers

import com.kerugoya_bursary.form.models.SponsorshipDetails
import com.kerugoya_bursary.form.services.SponsorshipDetailsService
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("kerugoya_bursary/sponsorship")
class SponsorshipController(
    private val service: SponsorshipDetailsService
) {

    @GetMapping
    fun getAll(
        @PageableDefault(
            size = 50,
            page = 0,
            sort = ["id"],
            direction = Sort.Direction.DESC
        ) pageable: Pageable
    ): ResponseEntity<List<SponsorshipDetails>> {
        return ResponseEntity.ok(service.getAllSponsorshipDetails(pageable).content)
    }

    @GetMapping("get-by-id/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<SponsorshipDetails> {
        return ResponseEntity.ok(service.getSponsorshipDetailsById(id))
    }

    @PostMapping
    fun create(
        @RequestBody sponsorshipDetails: SponsorshipDetails
    ): ResponseEntity<SponsorshipDetails> {
        return ResponseEntity.ok(service.createSponsorshipDetails(sponsorshipDetails))
    }

    @PutMapping("update")
    fun update(
        @RequestBody sponsorshipDetails: SponsorshipDetails
    ): ResponseEntity<SponsorshipDetails> {
        return ResponseEntity.ok(service.updateSponsorshipDetails(sponsorshipDetails))
    }

    @DeleteMapping("delete/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        service.deleteSponsorshipDetails(id)

        return ResponseEntity.noContent().build()
    }

}