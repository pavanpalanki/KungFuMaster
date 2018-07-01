package com.dbms.kungfu.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dbms.kungfu.entity.ServiceCatalogue;
import com.dbms.kungfu.entity.Student;

@Repository
public class FinanceDaoImpl implements FinanceDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<ServiceCatalogue> getServiceCatalogue() {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<ServiceCatalogue> theQuery = currentSession.
				createQuery("from ServiceCatalogue", ServiceCatalogue.class);
		
		List<ServiceCatalogue> serviceCatalogue = theQuery.getResultList();

		return serviceCatalogue;
	}

}
