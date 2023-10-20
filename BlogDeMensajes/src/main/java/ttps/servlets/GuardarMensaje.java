package ttps.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ttps.clasesDAO.FactoryDAO;
import ttps.clasesDeObjetosDelSistema.Mensaje;
import ttps.clasesDeObjetosDelSistema.Usuario;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Servlet implementation class GuardarMensaje
 */
public class GuardarMensaje extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GuardarMensaje() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mensaje = (String) request.getParameter("mensaje");
		String persona = (String) request.getParameter("persona");
		
		System.out.println(mensaje + "-" + persona);
		Usuario usu = FactoryDAO.getUsuario().recuperarPorNombre(persona);	
		
		System.out.println(usu.getNombre() + "-" + usu.getId());
		
		if(usu != null) {
			//System.out.print(usu.getId());
			Mensaje msj = new Mensaje(mensaje,usu);
			System.out.println("Guarda?");
			FactoryDAO.getMensaje().guardar(msj);
			System.out.println("SAlio de guardar");
		}else {
			System.out.print("No existe dicho usu");
		}
		
		response.sendRedirect("/practica4/VisualizarMensajes");	
		
	}

}
