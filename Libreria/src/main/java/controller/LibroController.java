package controller;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;

import beans.Libro;
import beans.Usuario;
import connection.DBConnection;

public class LibroController implements lLibroController {

	@Override
	public String listar(boolean ordenar, String orden) {
		Gson gson = new Gson();
		
		DBConnection con = new DBConnection();
		
		String sql = "Select * from libros";
		
		if (ordenar == true) {
			sql += " order by genero " + orden;
		}
		
		List<String> libros = new ArrayList<String>();
		
		try {
			Statement st = con.getConnection().createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String titulo = rs.getString("titulo");
				String genero  =rs.getString("genero");
				String autor = rs.getString("autor");
				int copias = rs.getInt("copias");
				boolean novedad = rs.getBoolean("novedad");
				
				Libro libro = new Libro(id,titulo,genero,autor,copias,novedad);
				
				libros.add(gson.toJson(libro));
				
				
			}
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}finally {
			con.desconectar();
		}
		
		return gson.toJson(libros);
	}

	@Override
	public String alquilar(int id, String username) {
		Timestamp fecha = new Timestamp(new Date().getTime());
		DBConnection db  = new DBConnection();
		
		String sql = "Insert into alquiler values ('"+id+"','"+username+"','"+fecha+"')";
		
		try {
			Statement st = db.getConnection().createStatement();
			st.executeUpdate(sql);
			
			String modificar = modificar(id);
			
			if(modificar == "true") {
				return "true";
			}
		}catch(Exception ex) {
			System.out.println(ex.toString());
		}finally {
			db.desconectar();
		}
		
		return "false";
	}

	@Override
	public String modificar(int id) {
		DBConnection db  = new DBConnection();
		String sql = "Update libros set copias = (copias - 1) where id = "+id;
		try {
			Statement st = db.getConnection().createStatement();
			st.executeUpdate(sql);
			
			return "true";
		}catch(Exception ex) {
			System.out.println(ex.toString());
		}finally {
			db.desconectar();
		}
		
		return "false";
	}
	
	

}
