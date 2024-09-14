package service;

import dto.Student;

public interface IStudentService {

	//operation should be implemented
	
		//public String addStudent(String name,Integer sage,String Address);
	public String addStudent(Student student);
		
		public Student searchStudent(Integer sid);
		
//		public String updateStudent(Integer sid,String name,Integer sage,String Address);
		public String updateStudent(Student student);
		
		public String deleteStudent(Integer sid);
		
}
