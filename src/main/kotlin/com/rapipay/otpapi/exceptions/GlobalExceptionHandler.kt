package com.rapipay.otpapi.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import java.util.*

@ControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(OrderIdNotFoundException::class)
    fun handleOrderIdNotFoundException(exception: OrderIdNotFoundException, request:WebRequest):ResponseEntity<Any>{
        val error = ErrorDetail(Date(), exception.message, request.getDescription(false))
        return ResponseEntity(error, HttpStatus.NOT_FOUND)
    }
    @ExceptionHandler(InvalidOtpException::class)
    fun handleOrderIdNotFoundException(exception: InvalidOtpException, request:WebRequest):ResponseEntity<*>{
        val error = ErrorDetail(Date(), exception.message, request.getDescription(false))
        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }
    @ExceptionHandler(OtpExpiredException::class)
    fun handleOrderIdNotFoundException(exception: OtpExpiredException, request:WebRequest):ResponseEntity<*>{
        val error = ErrorDetail(Date(), exception.message, request.getDescription(false))
        return ResponseEntity(error, HttpStatus.IM_USED)
    }
    @ExceptionHandler(InvalidEmailIdException::class)
    fun handleOrderIdNotFoundException(exception: InvalidEmailIdException, request:WebRequest):ResponseEntity<*>{
        val error = ErrorDetail(Date(), exception.message, request.getDescription(false))
        return ResponseEntity(error, HttpStatus.UNAUTHORIZED)
    }
    @ExceptionHandler(InvalidChannelException::class)
    fun handleOrderIdNotFoundException(exception: InvalidChannelException, request:WebRequest):ResponseEntity<*>{
        val error = ErrorDetail(Date(), exception.message, request.getDescription(false))
        return ResponseEntity(error, HttpStatus.UNAUTHORIZED)
    }

}