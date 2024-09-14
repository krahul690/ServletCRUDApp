package dao;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbconnection.Dbutil;
import dto.Student;

//DaoLayer 
//Persistence logic using JDBC API

public class StudentDaoImpl implements IStudentDao {

	Connection conn = null;
	PreparedStatement pstmt = null;
	Student student = null;

	@Override
	public String addStudent(Student student) {
		String sqlInsertquery = "insert into student(`name`,`age`,`Address`) values(?,?,?)";
		try {
			conn = Dbutil.getConnection();
			if (conn != null)
				pstmt = conn.prepareStatement(sqlInsertquery);

			if (pstmt != null) {
				pstmt.setString(1, student.getName());
				pstmt.setInt(2, student.getSage());
				pstmt.setString(3, student.getAddress());

				int rowwAffected = pstmt.executeUpdate();
				// System.out.println(rowwAffected+"record is inserted");
				if (rowwAffected == 1)
					return "success";
			}

		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}

		return "failed";
	}

	@Override
	public Student searchStudent(Integer sid) {
		String sqlselectquery = "select * from student where id=?";
		try {
			conn = Dbutil.getConnection();
			if (conn != null) {
				pstmt = conn.prepareStatement(sqlselectquery);
			}
			if (pstmt != null) {
				pstmt.setInt(1, sid);
				ResultSet rs = pstmt.executeQuery();

				if (rs != null && rs.next()) {
					student = new Student();
					student.setSid(rs.getInt(1));
					student.setName(rs.getString(2));
					student.setSage(rs.getInt(3));
					student.setAddress(rs.getString(4));

				}
				
				//rs.refreshRow();
			}

		} catch (SQLException | IOException e) {

			e.printStackTrace();
		}
		return student;
	}

	@Override
	public String updateStudent(Student student) {
		String sqlselectquery = "UPDATE student SET Name = ?, age = ?, address = ? WHERE id = ?";
		try {
			conn = Dbutil.getConnection();
			if (conn != null) {
				pstmt = conn.prepareStatement(sqlselectquery);
			}
			if (pstmt != null) {
				// Set the values for Name, Age, Address and the ID
				pstmt.setString(1, student.getName());
				pstmt.setInt(2, student.getSage()); // This should be index 2 for age
				pstmt.setString(3, student.getAddress());
				pstmt.setInt(4, student.getSid()); // Set the id for WHERE clause

				int rowAffected = pstmt.executeUpdate();
				if (rowAffected == 1) {
					return "success";
				} else {
					return "not found";
				}
			}

		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

//		String sqlselectquery = "UPDATE student SET Name = ?, age = ?, address = ? WHERE id = ?";
//		try {
//			conn = Dbutil.getConnection();
//			if (conn != null) {
//				pstmt = conn.prepareStatement(sqlselectquery);
//			}
//			if (pstmt != null) {
//				pstmt.setString(1, student.getName());
//				pstmt.setInt(1, student.getSage());
//				pstmt.setString(3, student.getAddress());
//				
//				int rowaffected = pstmt.executeUpdate();
//				if(rowaffected ==1) {
//					return "success";
//				}else {
//					return "not found";
//				}
//
//			}
//
//		} catch (SQLException | IOException e) {
//
//			e.printStackTrace();
//		}
//		return null;
	

	@Override
	public String deleteStudent(Integer sid) {
		String sqlDeletequery = "delete from student where id=?";
		try {
			conn = Dbutil.getConnection();
			if (conn != null)
				pstmt = conn.prepareStatement(sqlDeletequery);

			if (pstmt != null) {
				pstmt.setInt(1, sid);
				int rowwAffected = pstmt.executeUpdate();
				// System.out.println(rowwAffected+"record is inserted");
				if (rowwAffected == 1) {
					return "success";
				} else {
					return "not found";
				}
			}

		} catch (SQLException  e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "failed";
	}

}
