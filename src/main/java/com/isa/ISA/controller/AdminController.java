package com.isa.ISA.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.isa.ISA.model.Admin;
import com.isa.ISA.service.AdminService;

@RestController
public class AdminController {
	 @Autowired
	 private AdminService as;

	    @RequestMapping("/api/admins")
	    public List<Admin> getAllAdmins(){
	    	return as.getAllAdmins(); 
	    }

	    @RequestMapping(method = RequestMethod.DELETE, value = "/admin/{id}")
	    public ResponseEntity<Void> deleteAdmin(@RequestBody Admin a, @PathVariable Long id){
	        Admin admin = as.getAdmin(a.getId());
	        if(admin==null)
	        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    	as.deleteAdmin(a);
	        return new ResponseEntity<>(HttpStatus.OK);
	}
	    
	
}
