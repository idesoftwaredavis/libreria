package controller;

import java.sql.ResultSet;

import java.sql.Statement;

import connection.DBConnection;
import beans.Usuario;
import com.google.gson.Gson;
public class UsuarioController implements IUsuarioController {

	@Override
	public String login(String username, String contrasena) {
		Gson gson = new Gson();
		// objeto conexion
		DBConnection con = new DBConnection();
		// Query
		String sql = "Select * from usuarios where username = '"+username+"' and contrasena = '"+contrasena+"'";
		
		try {
			Statement st = con.getConnection().createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()) {
				String nombre = rs.getString("nombre");
				String apellidos = rs.getString("apellidos");
				String email = rs.getString("email");
				double saldo = rs.getDouble("saldo");
				boolean premium = rs.getBoolean("premium");
				
				Usuario usuario = new Usuario(username,contrasena,nombre,apellidos,email,saldo,premium);
				return gson.toJson(usuario);
			}
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}finally {
			con.desconectar();
		}
		
		return "false";
	}

	@Override
	public String register(String username, String contrasena, String nombre, String apellidos, String email,
			double saldo, Boolean premium) {
		// Objeto gson para enviar json
		Gson gson = new Gson();
		
		DBConnection con = new DBConnection();
		String sql = "Insert into usuarios values('" + username + "', '" +contrasena+"','"+ nombre+"','"+apellidos+"','"+email+"',"+saldo+","+premium+")";
		
		try {
			Statement st = con.getConnection().createStatement();
			st.executeUpdate(sql);
			
			// objeto usuario que contiene los datos enviados por el cliente
			Usuario usuario = new Usuario(username,contrasena,nombre,apellidos,email,saldo,premium);
			
			
			st.close();
			// Devuelvo en formato json el objeto usuario.
			return gson.toJson(usuario);
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			return "false";
		}finally {
			con.desconectar();
		}
	}

	@Override
	public String pedir(String username) {
		//Objeto gson para devolver los datos en formato JSON
		Gson gson = new Gson();
		
		DBConnection db = new DBConnection();
		
		String sql = "Select * from usuarios where username = '" + username +"'";
		
		try {
			Statement st = db.getConnection().createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()) {
				String contrasena = rs.getString("contrasena");
				String nombre = rs.getString("nombre");
				String apellidos = rs.getString("apellidos");
				String email = rs.getString("email");
				double saldo = rs.getDouble("saldo");
				boolean premium = rs.getBoolean("premium");
				
				Usuario user = new Usuario(username,contrasena,nombre,apellidos,email,saldo,premium);
				System.out.println(user);
				return gson.toJson(user);
				
			}
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}finally {
			db.desconectar();
		}
		return "false";
	}

	@Override
	public String restarDinero(String username, double nuevoSaldo) {
		DBConnection db = new DBConnection();
		
		String sql = "update usuarios set saldo = "+ nuevoSaldo+" where username ='"+username+"'";
		
		
		try {
			Statement st = db.getConnection().createStatement();
			st.executeUpdate(sql);
				
			return "true";	
			
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}finally {
			db.desconectar();
		}
		return "false";
	}
	
}
