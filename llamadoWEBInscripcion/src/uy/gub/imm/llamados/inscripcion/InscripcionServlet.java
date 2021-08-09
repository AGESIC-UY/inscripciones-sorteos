package uy.gub.imm.llamados.inscripcion;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;


@WebServlet
public class InscripcionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log= Logger.getLogger(InscripcionServlet.class);   
    public InscripcionServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("ACA......");
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}


}
