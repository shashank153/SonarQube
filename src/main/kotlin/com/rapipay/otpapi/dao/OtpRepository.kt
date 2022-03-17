package com.rapipay.otpapi.dao

import com.rapipay.otpapi.entity.UserEntity
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories
import org.springframework.stereotype.Repository

@Repository
@EnableMongoRepositories
interface OtpRepository:MongoRepository<UserEntity, Int> {

}