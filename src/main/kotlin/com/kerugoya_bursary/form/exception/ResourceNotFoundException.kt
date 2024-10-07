package com.kerugoya_bursary.form.exception

class ResourceNotFoundException(
    override val message: String
) : RuntimeException(message)