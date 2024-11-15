package com.kemalkeskin.order;

import com.kemalkeskin.order.core.exception.BusinessException;
import com.kemalkeskin.order.core.exception.ProblemDetails;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@SpringBootApplication
@EnableFeignClients
@RestControllerAdvice
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ProblemDetails handlerProblemDetails(BusinessException businessException){
		ProblemDetails problemDetails=new ProblemDetails(businessException.getMessage());
		return problemDetails;
	}
}
