package com.rapipay.otpapi.email

import lombok.AllArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import javax.mail.MessagingException

@Service
@AllArgsConstructor
class EmailService:EmailSender {
    @Autowired
    private lateinit var javaMailSender: JavaMailSender

    @Async
    override fun send(to: String, email: String) {
        try{
            val message = javaMailSender.createMimeMessage()
            val helper = MimeMessageHelper(message, "utf-8")
            helper.setText(email)
            helper.setTo(to)
            helper.setSubject("OTP Verification for Demo Email Check")
            helper.setFrom("skshukl01@gmail.com")
            javaMailSender.send(message)
        }catch (exception: MessagingException){
            throw IllegalStateException("Failed to send email..")
        }
    }
}