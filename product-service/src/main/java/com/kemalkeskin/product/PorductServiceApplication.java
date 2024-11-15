package com.kemalkeskin.product;

import com.kemalkeskin.product.core.exception.BusinessException;
import com.kemalkeskin.product.core.exception.ProblemDetails;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@SpringBootApplication
@RestControllerAdvice
@EnableFeignClients
public class PorductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PorductServiceApplication.class, args);
	}

	@Bean
	public ModelMapper getMapper(){return  new ModelMapper();}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ProblemDetails problemDetails(BusinessException businessException){
		ProblemDetails problemDetails=new ProblemDetails();
		problemDetails.setMessage(businessException.getMessage());
		return  problemDetails;
	}
}
