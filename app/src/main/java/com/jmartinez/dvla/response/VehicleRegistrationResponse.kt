package com.jmartinez.dvla.response

data class VehicleRegistrationResponse(
    val co2Emissions: Int,
    val colour: String,
    val dateOfLastV5CIssued: String,
    val engineCapacity: Int,
    val euroStatus: String,
    val fuelType: String,
    val make: String,
    val markedForExport: Boolean,
    val monthOfFirstRegistration: String,
    val motStatus: String,
    val registrationNumber: String,
    val revenueWeight: Int,
    val taxDueDate: String,
    val taxStatus: String,
    val typeApproval: String,
    val wheelplan: String,
    val yearOfManufacture: Int
)