//package com.returnordermanag.authorizationService.service;
//
//import java.util.List;
//
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//
//import com.returnordermanag.authorizationService.model.ProcessRequest;
//
//
//@FeignClient(name = "componentProcessing", url = "http://localhost:8081/")
//public interface ordersFeignClient {
//	@GetMapping(value = "/orders/{username}")
//	List<ProcessRequest> getOrders(@PathVariable String username);
//}
//
//
