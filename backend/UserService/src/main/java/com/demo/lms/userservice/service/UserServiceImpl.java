package com.demo.lms.userservice.service;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.lms.userservice.model.User;
import com.demo.lms.userservice.repo.UserRepository;



@Service
public class UserServiceImpl implements IUserService
{
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public List<User> getAllUsers()
	{
		List<User> userList =(List<User>)userRepo.findAll();
		if(userList!=null && userList.size()>0)
		{
			return userList;
		}
		return null;
	}
	
	@Override
	public User addUser(User user)
	{
		boolean userExists =userRepo.existsByUsername(user.getUsername());
		if(!userExists) {
			User u = userRepo.save(user);
			if(u!=null)
			{
				return userRepo.save(user);
				
			}
			else
				return null;
		}
		else {
			return null;
		}
	}

	@Override
	public boolean validateUserName(Map<String,String> user)
	{
		return userRepo.existsByUsername(user.get("username"));
		
	}
	@Override
	public User validateUser(String username, String password) {
		// TODO Auto-generated method stub
		User user = userRepo.validateUser(username, password);
		//System.out.println("user"+user.getUsername());
		if(user!=null)
		{
			return user;
		}
		
		return null;
	}
	

}
