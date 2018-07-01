package com.dbms.kungfu.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dbms.kungfu.entity.ServiceCatalogue;

@Service
public interface FinanceService {

	List<ServiceCatalogue> getServiceCatalogue();

}
