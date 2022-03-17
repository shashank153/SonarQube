package com.rapipay.otpapi.services

import org.springframework.stereotype.Service

@Service
interface OtpService {
    abstract fun generateOtp(emailId: String, orderId: Int, channelName: String): String
    abstract fun validateOtp(emailId: String, orderId: Int, channelName: String, otp: Long): String
}