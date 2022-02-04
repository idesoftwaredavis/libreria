package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.UsuarioController;

/**
 * Servlet implementation class ServletUsuarioRegister
 */
@WebServlet("/ServletUsuarioRegister")
public class ServletUsuarioRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletUsuarioRegister() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// objeto usuario controler para activar el metodo registrar del controlador
		UsuarioController  usuario = new UsuarioController();
		
		// Extraer campos del cliente
		String username = request.getParameter("username");
		String contrasena = request.getParameter("contrasena");
		String nombre = request.getParameter("nombre");
		String apellidos = request.getParameter("apellidos");
		String email = request.getParameter("email");
		double saldo = Double.parseDouble(request.getParameter("saldo"));
		Boolean premium = Boolean.parseBoolean(request.getParameter("premium"));
		
		// invoco metodo register y le paso por parametros los datos del cliente
		String usuarioStr = usuario.register(username, contrasena, nombre, apellidos, email, saldo, premium);
		
		// Objeto PrintWriter para poder mostrar la informacion a la vida.
		PrintWriter out = response.getWriter();
		// imprimo lo que quiero visualizar
		out.println(usuarioStr);
		// Limpio buffer
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
