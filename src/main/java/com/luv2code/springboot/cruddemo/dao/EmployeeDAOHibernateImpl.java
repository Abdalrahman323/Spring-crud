package com.luv2code.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springboot.cruddemo.entity.Employee;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {
  
	// define field for entitymanager
	private EntityManager entityManager;
	
	// set up consturctor injection
	@Autowired
	public EmployeeDAOHibernateImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager; 
	}
	
	
	@Override
	public List<Employee> findAll() {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// create a query
		Query<Employee> theQuery =
				currentSession.createQuery("from Employee",Employee.class);
				
		
		// execute query and get result list
		
		List<Employee> employess = theQuery.getResultList();
		
		
		// return the result
		
		return employess;
	}


	@Override
	public Employee findById(int theId) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		// get the employee
		Employee theEmployee =
				currentSession.get(Employee.class,theId);
		// return the employees
		return theEmployee;
	}


	@Override
	public void save(Employee theEmployee) {
		// get the current hibernate session
		  Session currentSession = entityManager.unwrap(Session.class);

        // save employee
		  currentSession.saveOrUpdate(theEmployee);
		  // #remember if id =0 then save/insert
		  // else update
	}


	@Override
	public void delete(int theId) {
		// get the current hibernate session
		  Session currentSession = entityManager.unwrap(Session.class);		
	
	  // delete object with primary ky
		  Query theQuery = 
				  currentSession.createQuery(
						  "delete from Employee where id =:employeeId");
		  theQuery.setParameter("employeeId", theId);
		  
		  theQuery.executeUpdate();
				  
	}

}
