package uy.gub.imm.llamados.inscripcion;


import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletRequest;
import org.jboss.logging.Logger;
import uy.gub.imm.llamados.dto.InscripcionCupoConcursoAbiertoDTO;
import uy.gub.imm.llamados.ejb.InscriptosCupoConcursoAbiertoLocal;
import uy.gub.imm.llamados.exceptions.ConcursoAbiertoException;
import uy.gub.imm.llamados.util.FuncionesUtiles;


@ManagedBean(name="bajaInscripcionMB")
@ViewScoped
public class BajaInscripcionMB {
	
	private static Logger log = Logger.getLogger(BajaInscripcionMB.class);
	
	@EJB
	InscriptosCupoConcursoAbiertoLocal inscriptosCupoConcursoAbiertoEJB;
	
	private String token;
	
	public InscripcionCupoConcursoAbiertoDTO inscripcion;
	
	public boolean error;
	
	public String mensajeError;
	

	@PostConstruct
	public void init(){

		this.token= (String) ((ServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getParameter("token");
		log.info("Se obtuvo el token :"+token);
		try {
			this.inscripcion=inscriptosCupoConcursoAbiertoEJB.obtenerInscripcionCupoConcuroAbiertoPorToken(token);
			error=false;
			log.info("Se obtuvo la informacion de la inscripcion para el token "+token);
		} catch (ConcursoAbiertoException e) {
			log.error("Ocurrio un error obteniendo la informacion para la inscripcion del token : "+token+" "+e.getMessage());
			error= true;
			mensajeError= e.getMessage();

		}
		
	}
	
	public void eliminarInscipcion(){
		

		try {
			log.info("Iniciando borrado del llamado para el token "+token);
			inscriptosCupoConcursoAbiertoEJB.bajaInscripcionCupoConcuroAbiertoPorToken(token);
			try {
				FuncionesUtiles.enviarCorreoBajaInscripcion(inscripcion);
			} catch (ConcursoAbiertoException e) {
				log.error("Ocurrio un error al enviar el correo de confirmacion "+e.getMessage());
			} catch (Exception e) {
				log.error("Ocurrio un error al enviar el correo de confirmacion "+e.getMessage());
			}
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage("mensajesErrores", new FacesMessage(FacesMessage.SEVERITY_INFO,null, "La baja se realizó con éxito"));
			
		} catch (ConcursoAbiertoException e) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage("mensajesErrores", new FacesMessage(FacesMessage.SEVERITY_ERROR ,null, e.getMessage()));
			log.error("Ocurrio un error al intentar darse de baja al concurso"+e.getMessage());
		}
		
	}
	
	//GETTERS AND SETTERS 
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public InscripcionCupoConcursoAbiertoDTO getInscripcion() {
		return inscripcion;
	}

	public void setInscripcion(InscripcionCupoConcursoAbiertoDTO inscripcion) {
		this.inscripcion = inscripcion;
	}
	
	public boolean getError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public String getMensajeError() {
		return mensajeError;
	}

	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}


}
