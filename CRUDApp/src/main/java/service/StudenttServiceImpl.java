package service;

import dao.IStudentDao;
import daoFactory.studentDaoFactory;
import dto.Student;

//service layer get data from dao(testApp)
public class StudenttServiceImpl implements IStudentService {

	private IStudentDao stdDao;
	private IStudentDao Student;

	@Override
	public String addStudent(Student student) {
//		stdService = StudentServicecfactory.getStudentService();
//		if (stdService != null)
//			return stdService.addStudent(name, sage, Address); // get data from controller
//		return "failed";/
		stdDao = studentDaoFactory.getStudentDao();
		return stdDao.addStudent(student);
	}

	@Override
	public dto.Student searchStudent(Integer sid) {
		Student = studentDaoFactory.getStudentDao();
		return Student.searchStudent(sid);
	}

	@Override
	public String updateStudent(dto.Student student) {
		stdDao = studentDaoFactory.getStudentDao();
		return stdDao.updateStudent(student);

	}

	@Override
	public String deleteStudent(Integer sid) {
		stdDao = studentDaoFactory.getStudentDao();
		return stdDao.deleteStudent(sid);

	}

}
