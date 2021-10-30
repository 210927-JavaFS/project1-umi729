package com.revature.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.revature.models.Reimbursment;
import com.revature.utils.HibernateUtil;

class ReimbursmentDAOImplTest {

	static Reimbursment ticket;
	    private static Session session;
	     private Integer id;
	    @BeforeAll
	    public static void setup() {
	    	
	    	session = HibernateUtil.getSession();
	    	
	    }
	     
	    @AfterAll
	    public static void tearDown() {
	       session.close();
	    }
	     

	@Test
	void testFindAllRec() {
		 Query<Reimbursment> query = session.createQuery("from Reimbursment", Reimbursment.class);
		    List<Reimbursment> resultList = query.getResultList();
		     
		    assertFalse(resultList.isEmpty());
	}

	
	

	@Test
	void testAddNewRec() {
		  
			session.beginTransaction();
		     
			ticket  = new Reimbursment(50, "Travel", 85);
		    id= (Integer) session.save(ticket);
		     
		    session.getTransaction().commit();
		     
		    assertTrue(id > 0);
	}

	@Test
	void testUpdateRec() {
		System.out.println("Running testUpdate...");
	     
		session.beginTransaction();
	    ticket  = new Reimbursment(55, "Travel", 85);
	    id= (Integer) session.save(ticket);
	    session.getTransaction().commit();
	    
	    session.beginTransaction();
	    session.update(ticket);
	    session.getTransaction().commit();
	     
	    Reimbursment updatedrec = session.find(Reimbursment.class, id);
	     
	    assertEquals(55, updatedrec.getAmount());
	}
	
	@Test
	void testFindById() {
		session.beginTransaction();
	    ticket  = new Reimbursment(55, "Travel", 85);
	    id= (Integer) session.save(ticket);
	    session.getTransaction().commit();
		Reimbursment testR = session.find(Reimbursment.class, id);
	    assertEquals(85, testR.getReciptNo());    
	}


}
