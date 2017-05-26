package com.products;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.products.*;


public class ProductServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public ProductServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		List<Product> lisOfStudent = StudentDataService.getStudentList();
		
		DataTableObject dataTableObject = new DataTableObject();
		dataTableObject.setAaData(lisOfStudent);
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(dataTableObject);
		out.print(json);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}

