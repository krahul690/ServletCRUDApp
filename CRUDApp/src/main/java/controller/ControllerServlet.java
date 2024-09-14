package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Student;
import service.IStudentService;
import serviceFactory.StudentServicecfactory;

@WebServlet("/ControllerServlet/*")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("uri is" + request.getRequestURL());
		System.out.println("path is" + request.getPathInfo());

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		IStudentService stdService = StudentServicecfactory.getStudentService();

		if (request.getRequestURI().endsWith("add-section")) {

			// reading a data
			String name = request.getParameter("name");
			String age = request.getParameter("age");
			String address = request.getParameter("address");

			Student stu = new Student();
			stu.setName(name);
			stu.setSage(Integer.parseInt(age));
			stu.setAddress(address);
			String status = stdService.addStudent(stu);

			// Using JavaScript to show a popup message
			out.println("<html>");
			out.println("<head>");
			out.println("<script type='text/javascript'>");
			if (status.equals("success")) {
				out.println("alert('Student added successfully!');");
			} else {
				out.println("alert('Failed to add student.');");
			}
			out.println("</script>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h2>Processing Completed</h2>");
			out.println("</body>");
			out.println("</html>");
			out.close();
		} else if (request.getRequestURI().endsWith("search-section")) {
			String sid = request.getParameter("sid");

			// Search for the student by id
			Student stu = stdService.searchStudent(Integer.parseInt(sid));

			// Start HTML response
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Search Result</title>");

			// Add Bootstrap CSS
			out.println(
					"<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' rel='stylesheet'>");

			out.println("</head>");
			out.println("<body class='bg-light'>");
			out.println("<div class='container mt-5'>");
			out.println("<h2 class='text-center mb-4'>Search Result</h2>");

			// Check if student is found
			if (stu != null) {
				out.println("<table class='table table-striped table-bordered'>");
				out.println("<thead class='thead-dark'>");
				out.println("<tr>");
				out.println("<th>Student ID</th>");
				out.println("<th>Name</th>");
				out.println("<th>Age</th>");
				out.println("<th>Address</th>");
				out.println("</tr>");
				out.println("</thead>");
				out.println("<tbody>");

				// Display student details in the table
				out.println("<tr>");
				out.println("<td>" + stu.getSid() + "</td>");
				out.println("<td>" + stu.getName() + "</td>");
				out.println("<td>" + stu.getSage() + "</td>");
				out.println("<td>" + stu.getAddress() + "</td>");
				out.println("</tr>");
				out.println("</tbody>");
				out.println("</table>");
			} else {
				// If no student found, show an alert message
				out.println("<div class='alert alert-warning text-center' role='alert'>");
				out.println("No student found with ID: " + sid);
				out.println("</div>");
			}

			// Closing tags for the container and body
			out.println("</div>");
			out.println("</body>");
			out.println("</html>");
	
		} else if (request.getRequestURI().endsWith("update-section")) {
			// Step 1: Retrieve the student ID from the form
			String sid = request.getParameter("updateId");

			// Step 2: Check if the ID is valid
			if (sid != null && !sid.isEmpty()) {
				// Step 3: Get student details by ID
				Student student = stdService.searchStudent(Integer.parseInt(sid));

				if (student != null) {
					// Step 4: Show a form to update student details with pre-filled current data
					out.println("<html>");
					out.println("<head>");
					out.println("<title>Update Student</title>");
					out.println(
							"<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' rel='stylesheet'>");
					out.println("</head>");
					out.println("<body>");

					out.println("<div class='container mt-5'>");
					out.println("<h2>Update Student</h2>");
					out.println("<form action='./ControllerServlet/submit-update' method='POST'>");

					// Hidden field to store student ID
					out.println("<input type='hidden' name='sid' value='" + student.getSid() + "'>");

					// Name field
					out.println("<div class='form-group mb-3'>");
					out.println("<label for='name'>Name</label>");
					out.println("<input type='text' class='form-control' name='name' value='" + student.getName()
							+ "' required>");
					out.println("</div>");

					// Age field
					out.println("<div class='form-group mb-3'>");
					out.println("<label for='age'>Age</label>");
					out.println("<input type='number' class='form-control' name='age' value='" + student.getSage()
							+ "' required>");
					out.println("</div>");

					// Address field
					out.println("<div class='form-group mb-3'>");
					out.println("<label for='address'>Address</label>");
					out.println("<input type='text' class='form-control' name='address' value='" + student.getAddress()
							+ "' required>");
					out.println("</div>");

					// Submit button
					out.println("<button type='submit' class='btn btn-primary'>Submit</button>");
					out.println("</form>");
					out.println("</div>");

					out.println("</body>");
					out.println("</html>");
				} else {
					// If no student is found, show an error message
					out.println("<html><body>");
					out.println("<div class='container mt-5'>");
					out.println("<div class='alert alert-danger'>Student not found with ID: " + sid + "</div>");
					out.println("</div>");
					out.println("</body></html>");
				}
			} else {
				// If no ID is provided, show an error message
				out.println("<html><body>");
				out.println("<div class='container mt-5'>");
				out.println("<div class='alert alert-danger'>Please enter a valid ID to update.</div>");
				out.println("</div>");
				out.println("</body></html>");
			}
		}
		else if (request.getRequestURI().endsWith("submit-update")) {
		    // Step 1: Retrieve updated data from the form
		    String sid = request.getParameter("sid");
		    String name = request.getParameter("name");
		    String age = request.getParameter("age");
		    String address = request.getParameter("address");

		    // Step 2: Create a new Student object and set updated values
		    Student student = new Student();
		    student.setSid(Integer.parseInt(sid));
		    student.setName(name);
		    student.setSage(Integer.parseInt(age));
		    student.setAddress(address);

		    // Step 3: Call the service layer to update the student
		    String status = stdService.updateStudent(student);

		    // Step 4: Show confirmation message using Bootstrap
		    out.println("<html>");
		    out.println("<head>");
		    out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' rel='stylesheet'>");
		    out.println("</head>");
		    out.println("<body>");
		    
		    out.println("<div class='container mt-5'>");
		    
		    if (status.equals("success")) {
		        out.println("<div class='alert alert-success'>Student updated successfully!</div>");
		    } else {
		        out.println("<div class='alert alert-danger'>Failed to update student.</div>");
		    }
		    
		    out.println("</div>");
		    out.println("</body>");
		    out.println("</html>");
		}

		else if(request.getRequestURI().endsWith("delete-section")) {
		    // Step 1: Retrieve student ID from the form
		    String sid = request.getParameter("iddelete");

		    // Step 2: Call the delete service method
		    String status = stdService.deleteStudent(Integer.parseInt(sid));

		    // Step 3: Provide feedback to the user using Bootstrap
		    out.println("<html>");
		    out.println("<head>");
		    out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' rel='stylesheet'>");
		    out.println("</head>");
		    out.println("<body>");
		    
		    out.println("<div class='container mt-5'>");
		    
		    // Step 4: Display result messages based on deletion status
		    if(status.equals("success")) {
		        out.println("<div class='alert alert-success'>Student with ID " + sid + " deleted successfully!</div>");
		    } else if(status.equals("failure")) {
		        out.println("<div class='alert alert-danger'>Failed to delete student with ID " + sid + ". Please try again.</div>");
		    } else {
		        out.println("<div class='alert alert-warning'>Student with ID " + sid + " not found.</div>");
		    }
		    
		    out.println("<a href='./ControllerServlet/add-section' class='btn btn-primary mt-3'>Go Back</a>");
		    out.println("</div>");
		    
		    out.println("</body>");
		    out.println("</html>");
		}


	}

}
