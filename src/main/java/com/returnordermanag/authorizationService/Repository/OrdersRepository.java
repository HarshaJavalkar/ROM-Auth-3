package com.returnordermanag.authorizationService.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.returnordermanag.authorizationService.model.ProcessRequest;

public interface OrdersRepository extends JpaRepository<ProcessRequest, Integer> {
	List<ProcessRequest> findByUserName(String userName);
}
