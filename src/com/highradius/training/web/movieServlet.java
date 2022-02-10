package com.highradius.training.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import com.highradius.training.dao.movieDAO;
import com.highradius.training.model.movie;

/**
 * Servlet implementation class movieServlet
 */
//@WebServlet("/data")
public class movieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public movieServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
			String action = request.getServletPath();
			System.out.println(action);

			try {
				switch (action) {
				
				case "/ADD":
					add(request, response);
					break;
				case "/edit":
					editData(request, response);
					break;
				case "/selectAll":
					listMovie(request, response);
						break;	
				case "/delete":
					delete(request, response);
					
						break;
				
				default:
					//listMovie(request, response);
					break;
				}
			} catch (SQLException ex) {
				throw new ServletException(ex);
			} catch (Exception e) {
				 
				e.printStackTrace();
			}
	
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}
		
		

		public void listMovie(HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			
			movieDAO db = new movieDAO();
			PrintWriter out = response.getWriter();
			
			String start= request.getParameter("start");
			String limit= request.getParameter("limit");
			System.out.println(limit);
			List<movie> listMovie = db.selectAll(start,limit);
			System.out.print(start);

			Gson gs = new Gson();
			
			String jsonData = gs.toJson(listMovie);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			out.write("{total:1010,items:"+jsonData+"}");
			out.close();
			

			
		}

		
		public void add(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			PrintWriter out = response.getWriter();
			movieDAO db = new movieDAO();

			try {

				String title = request.getParameter("title");
				String description = request.getParameter("description");
				String releaseYear =request.getParameter("releaseYear");
				String language = request.getParameter("language");
				String rating = request.getParameter("rating");
				String director = request.getParameter("director");
				String specialFeatures = request.getParameter("specialFeatures");
				

				movie newData = new movie(title,description,Integer.parseInt(releaseYear),language,rating,director,specialFeatures);

				
				System.out.println(title);
				
				newData.setTitle(title);
				newData.setDescription(description);
				newData.setReleaseYear(Integer.parseInt(releaseYear));
				newData.setLanguage(language);
				newData.setRating(rating);
				newData.setDirector(director);
				newData.setSpecialFeatures(specialFeatures);
				

				db.addData(newData);

				Gson gs = new Gson();

				String jsonData = gs.toJson(newData);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				out.write(jsonData);
				out.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		
		public void editData(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			PrintWriter out = response.getWriter();
			movieDAO db = new movieDAO();

			try {
				String id = request.getParameter("id");
				String title = request.getParameter("title");
				String description = request.getParameter("description");
				String releaseYear =request.getParameter("releaseYear");
				String language = request.getParameter("language");
				String rating = request.getParameter("rating");
				String director = request.getParameter("director");
				String specialFeatures = request.getParameter("specialFeatures");
				

				movie newData = new movie(Integer.parseInt(id),title,description,Integer.parseInt(releaseYear),language,director,rating,specialFeatures);

				
				System.out.println(title);
				
				newData.setTitle(title);
				newData.setDescription(description);
				newData.setReleaseYear(Integer.parseInt(releaseYear));
				newData.setLanguage(language);
				newData.setRating(rating);
				newData.setDirector(director);
				newData.setSpecialFeatures(specialFeatures);
				

				db.editMovie(newData);

				Gson gs = new Gson();

				String jsonData = gs.toJson(newData);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				out.write(jsonData);
				out.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		


		private void delete(HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			PrintWriter out = response.getWriter();
			movieDAO db = new movieDAO();
			System.out.println("deleteServlet");
			String c= request.getParameter("del");
			System.out.println(c);
		
		try {

//			long length = Long.parseLong(request.getParameter("length"));
//			String[] a = new String[(int) length];
//			long i = 0;
//			System.out.println(length);
//			while(i < length) {
//				long key = i + 1;
//				String getId = request.getParameter(key+"");
//				a[(int) i] = getId;
//				i++;
//			}
			
//         System.out.println("Items selected = "+ a.length);
			
			db.deleteById(c);
			
			out.close();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		
		}


	

}
