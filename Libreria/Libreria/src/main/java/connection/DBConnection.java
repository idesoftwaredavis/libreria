package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	static String bd = "Libreria";
	static String port = "3306";
	static String login = "root";
	static String password = "";
	static String url = "jdbc:mysql://localhost:"+port+"/"+bd;
	
	Connection connection = null;
	
	public DBConnection() {
		try {
			// registro de driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			// conexion fisica a partir del url,login y contraseña
			connection = DriverManager.getConnection(url,login,password);
			
			if (connection == null) {
				System.out.println("La conexion " + bd + " ha fallado");
				
			}else {
				System.out.println("La conexion a "+ bd + " ha sido satisfactoria");
			}
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}

	}
	
	// obtener la conexion
	public Connection getConnection() {
		return connection;
	}
	
	// desconecto driver
	public void desconectar() {
		connection = null;
	}
}
