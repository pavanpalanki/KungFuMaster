package com.dbms.kungfu.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbms.kungfu.dao.FinanceDao;
import com.dbms.kungfu.entity.ServiceCatalogue;

@Service
public class FinanceServiceImpl implements FinanceService {
	
	@Autowired
	private FinanceDao financeDao;

	@Override
	@Transactional
	public List<ServiceCatalogue> getServiceCatalogue() {
		// TODO Auto-generated method stub
		
		List<ServiceCatalogue> serviceCatalogue = financeDao.getServiceCatalogue();
		
		return serviceCatalogue;
	}

}
