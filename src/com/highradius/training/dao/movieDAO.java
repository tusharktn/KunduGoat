package com.highradius.training.dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.highradius.training.model.movie;




public class movieDAO {
	private static String driver = "com.mysql.cj.jdbc.Driver";
	private String jdbcURL = "jdbc:mysql://localhost:3306/sakila";
	private String jdbcUsername = "root";
	private String jdbcPassword = "root";



	

	public List<movie> selectAll(String start,String limit) throws Exception{
		 
		
			Class.forName(driver);
			Connection con= DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			System.out.print(start+"start");
			if(limit==null) {
				start="0";
				limit="10";
			}
			
			 String SELECT_ALL = "SELECT film.film_id,film.title,film.description,film.release_year,language.name,film.rating,film.special_features,film.director FROM film,LANGUAGE  WHERE film.language_id = language.language_id AND film.isDelete !=1 ORDER BY film_id DESC LIMIT "+start+","+limit ;
			 Statement st = con.createStatement();
			 
		
				
				
				// Step 3: Execute the query or update query
				ResultSet rs = st.executeQuery(SELECT_ALL);
				
				List<movie> movies = new ArrayList<>() ;
				// Step 4: Process the ResultSet object.
				while (rs.next()) {
					int id = rs.getInt("film_id");
					String title = rs.getString("title");
					String description = rs.getString("description");
					int releaseYear = rs.getInt("release_year");
					String language = rs.getString("name");
					String director = rs.getString("director");
					String rating = rs.getString("rating");
					String specialFeatures = rs.getString("special_features");
					
					
				    movies.add(new movie( id, title, description, releaseYear,language, director,rating, specialFeatures));
				}
				return movies;
			} 
	
	
	public void addData(movie newData) throws Exception {

		Class.forName(driver);
		Connection con = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

		String query = "INSERT INTO film  (title, DESCRIPTION, release_year,language_id, director,rating,special_features)"
				+ "VALUES(?, ?, ?, ?, ?, ?, ?)";

		PreparedStatement statement = con.prepareStatement(query);

		statement.setString(1, newData.getTitle());
		statement.setString(2, newData.getDescription());
		statement.setInt(3, newData.getReleaseYear());
		statement.setString(4, newData.getLanguage());
		statement.setString(5, newData.getDirector());
		statement.setString(6, newData.getRating());
		statement.setString(7,newData.getSpecialFeatures());
		
       
	

		statement.addBatch();
		statement.executeBatch();

	}
	
	
	public void editMovie(movie newData) throws Exception {

		Class.forName(driver);
		Connection con = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

		String query = "UPDATE film SET " + "title='" + newData.getTitle() + "' , " + "description='" +newData.getDescription() + "',"+"release_year='"+newData.getReleaseYear()+"',"+"language_id='"+newData.getLanguage()+"',"+"director='"+newData.getDirector()+"',"+"rating='"+newData.getRating()+"',"+"special_features='"+newData.getSpecialFeatures()+"' where film_id =" +newData.getId();

		PreparedStatement statement = con.prepareStatement(query);

//		statement.setString(1, newData.getTitle());
//		statement.setString(2, newData.getDescription());
//		statement.setInt(3, newData.getReleaseYear());
//		statement.setString(4, newData.getLanguage());
//		statement.setString(5, newData.getDirector());
//		statement.setString(6, newData.getRating());
//		statement.setString(7,newData.getSpecialFeatures());
//		
       
	

		statement.addBatch();
		statement.executeBatch();

	}
	
	
	public void deleteById(String id) throws Exception {
		Class.forName(driver);
		Connection con = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

		String query = "UPDATE film set isDelete=1 where film_id in" + "("+id+")";
		
//		for (int i = 0; i < id.length; i++) {
//			if (i == id.length - 1) {
//				query += id[i];
//			} else {
//				query += id[i] + ",";
//			}
//		}

		
     System.out.println("deleted");
		PreparedStatement statement = con.prepareStatement(query);

		statement.addBatch();
		statement.executeBatch();

	}
			
			
	







//	
//	public void Add(movie user) throws SQLException {
//		System.out.println(INSERT_USERS_SQL);
//		// try-with-resource statement will auto close the connection.
//		try (Connection connection = getConnection();
//				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
//			preparedStatement.setString(1, user.getName());
//			preparedStatement.setString(2, user.getEmail());
//			preparedStatement.setString(3, user.getCountry());
//			System.out.println(preparedStatement);
//			preparedStatement.executeUpdate();
//		} catch (SQLException e) {
//			printSQLException(e);
//		}
//	}



//	private void printSQLException(SQLException ex) {
//		for (Throwable e : ex) {
//			if (e instanceof SQLException) {
//				e.printStackTrace(System.err);
//				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
//				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
//				System.err.println("Message: " + e.getMessage());
//				Throwable t = ex.getCause();
//				while (t != null) {
//					System.out.println("Cause: " + t);
//					t = t.getCause();
//				}
//			}
//		}
//	}



}
