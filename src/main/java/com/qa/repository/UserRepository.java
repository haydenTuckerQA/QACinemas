package com.qa.repository;

import static javax.transaction.Transactional.TxType.REQUIRED;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.qa.domain.Role;
import com.qa.domain.User;
import com.qa.utility.JSONUtil;

public class UserRepository implements IUserRepository {
	
	@Inject
	private JSONUtil util;
	
	@PersistenceContext(unitName = "primary")
	private EntityManager manager;
	
	@Transactional(REQUIRED)
	public String addAdmin(String admin) {
		User aAdmin = util.getObjectForJSON(admin, User.class);
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(aAdmin.getPassword().getBytes());
			byte[] bytes = md.digest();
			StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            aAdmin.setPassword(sb.toString());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "{\"message\": \"Password could not be added\"}";
		}
		
		aAdmin.setRole(Role.ADMIN);
		manager.persist(aAdmin);   
		return "{\"message\": \"admin sucessfully added\"}";
	}
	
	@Transactional(REQUIRED)
	public String deleteAdmin(String username) {
		User admin = findUser(username);
		
		if (admin != null) {
			manager.remove(admin);
		}
		return util.getJSONForObject(admin);
	}
	
	public String getAdmin(String username) {
		return util.getJSONForObject(findUser(username));
	}
	
	public String getAllAdmins() {
		TypedQuery<User> query = manager.createQuery("SELECT u FROM User u", User.class);
		return util.getJSONForObject(query.getResultList());
	}
	
	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

	public void setUtil(JSONUtil util) {
		this.util = util;
	}

	private User findUser(String username) {
		TypedQuery<User> query = manager.createQuery("SELECT u FROM User u WHERE username = :username", User.class);
		query.setParameter("username", username);
		return query.getSingleResult();
	}
}
