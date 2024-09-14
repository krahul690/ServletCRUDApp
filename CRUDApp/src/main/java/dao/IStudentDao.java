package dao;

import dto.Student;

public interface IStudentDao {

	//operation should be implemented
	
	public String addStudent(Student student);
	
	public Student searchStudent(Integer sid);
	
	
	public String deleteStudent(Integer sid);

	public String updateStudent(Student student);
	
}
