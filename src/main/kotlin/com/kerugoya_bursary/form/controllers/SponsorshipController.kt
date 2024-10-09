package com.kerugoya_bursary.form.controllers

import com.kerugoya_bursary.form.dtos.SponsorshipDetailsDto
import com.kerugoya_bursary.form.models.SponsorshipDetails
import com.kerugoya_bursary.form.services.SponsorshipDetailsService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("sponsorships")
class SponsorshipController(
    private val service: SponsorshipDetailsService
) {

    @GetMapping
    fun getAllSponsors(
        @PageableDefault(
            size = 50,
            page = 0,
            sort = ["id"],
            direction = Sort.Direction.DESC
        ) pageable: Pageable
    ): ResponseEntity<Page<SponsorshipDetails>> {
        return ResponseEntity.ok(service.getAllSponsorshipDetails(pageable))
    }

    @GetMapping("{id}")
    fun getSponsorById(@PathVariable id: Long): ResponseEntity<SponsorshipDetails> {
        return ResponseEntity.ok(service.getSponsorshipDetailsById(id))
    }

    @PostMapping
    fun createSponsor(
        @RequestBody sponsorshipDetails: SponsorshipDetails
    ): ResponseEntity<SponsorshipDetails> {
        return ResponseEntity.ok(service.createSponsorshipDetails(sponsorshipDetails))
    }

    @PutMapping("{id}")
    fun updateSponsor(
        @PathVariable id: Long,
        @RequestBody sponsorshipDetailsDto: SponsorshipDetailsDto
    ): ResponseEntity<SponsorshipDetailsDto> {
        return ResponseEntity.ok(service.updateSponsorshipDetails(id, sponsorshipDetailsDto))
    }

    @DeleteMapping("{id}")
    fun deleteSponsorsById(@PathVariable id: Long): ResponseEntity<Void> {
        service.deleteSponsorshipDetails(id)

        return ResponseEntity.noContent().build()
    }

}
