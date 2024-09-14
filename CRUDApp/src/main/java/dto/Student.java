package dto;

import java.io.Serializable;

public class Student implements Serializable { 
	
	
	private static final long serialVersionUID = 1L;
	private Integer sid;
	private String name;
	private Integer sage;
	private String Address;
	
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getSage() {
		return sage;
	}
	public void setSage(Integer sage) {
		this.sage = sage;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	@Override
	public String toString() {
		return "Student [sid=" + sid + ", name=" + name + ", sage=" + sage + ", Address=" + Address + "]";
	}
	 // Custom method to display student details in a formatted way
    public String displayStudentDetails() {
        return "Student Details:\n" +
               "----------------\n" +
               "ID       : " + sid + "\n" +
               "Name     : " + name + "\n" +
               "Age      : " + sage + "\n" +
               "Address  : " + Address + "\n";
    }
	
	
	
	
}
