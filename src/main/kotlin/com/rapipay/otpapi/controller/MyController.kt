package com.rapipay.otpapi.controller

import com.rapipay.otpapi.services.OtpService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class MyController{
    @Autowired
    private lateinit var service:OtpService

    @GetMapping("/otp-api")
    fun homepage():String{
        return "Welcome to rapipay\nOur new api is OTP generation api"
    }

    @PostMapping("/otp-api/generate-otp")
    fun generateOTP(@RequestParam emailId:String, @RequestParam orderId:Int, @RequestParam channelName:String):String{
        return service.generateOtp(emailId, orderId, channelName)
    }

    @GetMapping("/otp-api/validate-otp")
    fun validateOtp(@RequestParam emailId: String, @RequestParam orderId: Int, @RequestParam channelName: String, @RequestParam otp:Long):String{
        return service.validateOtp(emailId, orderId, channelName, otp)
    }
}