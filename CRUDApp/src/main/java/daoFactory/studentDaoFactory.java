package daoFactory;

import dao.IStudentDao;
import dao.StudentDaoImpl;

public class studentDaoFactory {

	private studentDaoFactory() {
	}

	private static IStudentDao studentdao = null;

	public static IStudentDao getStudentDao() {
		if (studentdao == null) {
			studentdao = new StudentDaoImpl();
		}
		  return studentdao;
	}
}
