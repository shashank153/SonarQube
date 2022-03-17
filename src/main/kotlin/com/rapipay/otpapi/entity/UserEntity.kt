package com.rapipay.otpapi.entity

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.sql.Time
import java.util.*
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
class UserEntity {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    var orderId: Int = 0
    lateinit var emailId:String
     var channelName:String = ""
     var count:Int = 0
     var otp:Long = 0
     var otpActive:Boolean = true
    var timeStamp:Long = 0
}