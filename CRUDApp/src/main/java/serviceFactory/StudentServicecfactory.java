package serviceFactory;

import service.IStudentService;
import service.StudenttServiceImpl;

//Abstraction
public class StudentServicecfactory {

	//make constructor private to avoid Object creation
	private StudentServicecfactory() {
		
	}
	
	private static IStudentService studentservice = null;

	public static IStudentService getStudentService() {
		//Singleton pattern code
		if (studentservice == null) {
			studentservice = new StudenttServiceImpl();
		}
		return studentservice;
	}
}
