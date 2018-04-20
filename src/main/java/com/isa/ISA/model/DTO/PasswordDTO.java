package com.isa.ISA.model.DTO;

public class PasswordDTO {

	private String oldpass;
	
	private String newpass;
	
	private String newpass2;
	
	public PasswordDTO() {
		
	}

	public String getOldpass() {
		return oldpass;
	}

	public void setOldpass(String oldpass) {
		this.oldpass = oldpass;
	}

	public String getNewpass() {
		return newpass;
	}

	public void setNewpass(String newpass) {
		this.newpass = newpass;
	}

	public String getNewpass2() {
		return newpass2;
	}

	public void setNewpass2(String newpass2) {
		this.newpass2 = newpass2;
	}
	
	
}
