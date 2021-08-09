package uy.gub.imm.llamados.inscripcion;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletRequest;
//import org.jboss.logging.Logger;
import uy.gub.imm.llamados.dto.CupoConcursoAbiertoDTO;
import uy.gub.imm.llamados.ejb.CupoConcursoAbiertoLocal;
import uy.gub.imm.llamados.exceptions.ConcursoAbiertoException;

@ManagedBean
@ViewScoped
public class ListaCupoConcursoInscripcionMB {
	
	//private static final Logger log = Logger.getLogger(ListaCupoConcursoInscripcionMB.class);
	
	@EJB
	CupoConcursoAbiertoLocal cupoConcursoAbiertoEJB;
	
	private Boolean error;
	private String mensajeError;
	
	
	private List<CupoConcursoAbiertoDTO> listaCupoConcursoAbierto;

	@PostConstruct
	public void init(){

		String codigo= (String) ((ServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getParameter("cd");
		try {
			//listaCupoConcursoAbierto= cupoConcursoAbiertoEJB.obtenerCuposPorCodigoConcurso(codigo);
			listaCupoConcursoAbierto= cupoConcursoAbiertoEJB.obtenerCuposVigentesPorCodigoConcurso(codigo);
		} catch (ConcursoAbiertoException e) {
			error=true;
			mensajeError=e.getMessage();
		}
		
	}

	public List<CupoConcursoAbiertoDTO> getListaCupoConcursoAbierto() {
		return listaCupoConcursoAbierto;
	}

	public void setListaCupoConcursoAbierto(List<CupoConcursoAbiertoDTO> listaCupoConcursoAbierto) {
		this.listaCupoConcursoAbierto = listaCupoConcursoAbierto;
	}

	public Boolean getError() {
		return error;
	}

	public void setError(Boolean error) {
		this.error = error;
	}

	public String getMensajeError() {
		return mensajeError;
	}

	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}
	
	
	
	
	
	
}
