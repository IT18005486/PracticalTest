package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/AppointmentsAPI")
public class AppointmentsAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Appointment appointmentObj = new Appointment();
       
    
    public AppointmentsAPI() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String output = appointmentObj.insertAppointment(request.getParameter("appointmentNumber"), request.getParameter("appointmentType"), request.getParameter("docID"), request.getParameter("docName"), request.getParameter("hospitalName"), request.getParameter("Desc"));
		response.getWriter().write(output);
		
		doGet(request, response);
	}

	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Map paras = getParasMap(request);
		String output = appointmentObj.updateAppointment(paras.get("hidAppointmentIDSave").toString(), paras.get("appointmentNumber").toString(),
				paras.get("appointmentType").toString(), paras.get("docID").toString(), paras.get("docName").toString(), paras.get("hospitalName"), paras.get("Desc").toString());
		response.getWriter().write(output);
	}

	
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Map paras = getParasMap(request);
		String output = appointmentObj.deleteAppointment(paras.get("appointmentID").toString());
		response.getWriter().write(output);
	}
	
	
	private static Map getParasMap(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
			scanner.close();

			String[] params = queryString.split("&");
			for (String param : params) {
				
				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}
		} catch (Exception e) {
		}
		return map;
	}
			
}



