package com.rapipay.otpapi.exceptions

import java.util.*

class ErrorDetail() {
    private lateinit var timeStamp: Date
    private  lateinit var message:String
    private lateinit var details:String
    constructor(timestamp: Date, message: String?, description: String) : this() {
        this.timeStamp = timeStamp
        this.message = message!!
        this.details = description
    }
}