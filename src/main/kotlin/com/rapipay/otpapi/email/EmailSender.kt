package com.rapipay.otpapi.email

interface EmailSender {
   fun send(to:String, email: String)
}