package com.rapipay.otpapi.services

import com.rapipay.otpapi.dao.OtpRepository
import com.rapipay.otpapi.email.EmailService
import com.rapipay.otpapi.entity.UserEntity
import com.rapipay.otpapi.exceptions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class OtpServiceImpl:OtpService{
    @Autowired
    private lateinit var repo: OtpRepository
    @Autowired
    private lateinit var emailService: EmailService

    private fun generationTime():Long{
        return System.currentTimeMillis()
    }

    override fun generateOtp(emailId: String, orderId: Int, channelName: String): String {
        val user:UserEntity = UserEntity()
        user.emailId = emailId
        user.orderId = orderId
        user.channelName = channelName
        user.otp = otpGeneration()
        user.count = 0;
        user.timeStamp = generationTime()
        repo.save(user)
        emailService.send(user.emailId, "Your otp is: ${user.otp}. This otp will going to be expired in 2 min.")
        return "Your otp is ${user.otp}. This will expired after 2 min..."
    }

    override fun validateOtp(emailId: String, orderId: Int, channelName: String, otp: Long): String {
        val user:UserEntity = repo.findById(orderId).orElseThrow(){
            OrderIdNotFoundException("The order id $orderId not found!!")
        }
        val foundOtp: Long = user.otp
        if((System.currentTimeMillis() - user.timeStamp) > 120000 || !user.otpActive){
            user.otpActive = false
            repo.save(user)
            throw OtpExpiredException("Your OTP is expired!! Please re-generate the new OTP.")
        }
        if(otp != foundOtp && user.count < 3 ) {
            val remainingCount:Int = (user.count) + 1
            user.count = remainingCount
            repo.save(user)
            throw InvalidOtpException("Please enter correct otp..You have ${3 - remainingCount} attempts left!!")
        }
        else if(user.emailId != emailId) throw InvalidEmailIdException("The email-id for the order-id $orderId is not correct!!")
        else if(user.channelName != channelName) throw InvalidChannelException("Channel is different!!")
        else if(user.count >= 3 || user.count < 0) throw OtpExpiredException("You have exhausted the limit..Please regenerate the otp")
        user.otpActive = false
        repo.save(user)
        return "Otp verified successfully!!"
    }

    fun otpGeneration(): Long {
        val random: Random = Random
        return 100000 + random.nextLong(999999)
    }
}