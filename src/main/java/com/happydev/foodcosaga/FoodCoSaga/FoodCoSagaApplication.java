package com.happydev.foodcosaga.FoodCoSaga;

import org.axonframework.config.EventProcessingConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FoodCoSagaApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodCoSagaApplication.class, args);
	}

	@Autowired
	public void configure(EventProcessingConfigurer configurer) {
//		configurer.registerListenerInvocationErrorHandler(
//				"product",
//				configuration -> new ProductServiceEventsErrorHandler()
//		);
	}

}
