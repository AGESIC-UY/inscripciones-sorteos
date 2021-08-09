package uy.gub.imm.llamados.inscripcion;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.mail.MessagingException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;
import org.jboss.logging.Logger;

import uy.gub.imm.llamados.dto.CupoConcursoAbiertoDTO;
import uy.gub.imm.llamados.dto.InscripcionCupoConcursoAbiertoDTO;
import uy.gub.imm.llamados.dto.SexoGeneroDTO;
import uy.gub.imm.llamados.ejb.CupoConcursoAbiertoLocal;
import uy.gub.imm.llamados.ejb.InscriptosCupoConcursoAbiertoLocal;
import uy.gub.imm.llamados.ejb.SexoGeneroLocal;
import uy.gub.imm.llamados.exceptions.ConcursoAbiertoException;
import uy.gub.imm.llamados.util.FuncionesUtiles;
import uy.gub.imm.llamados.utilidades.VerifyRecaptcha;


@ManagedBean
@ViewScoped
public class InscripcionMB{
	


	private static Logger log= Logger.getLogger(InscripcionMB.class);
	
	@EJB
	CupoConcursoAbiertoLocal cupoConcursoAbiertoEJB;
	@EJB
	InscriptosCupoConcursoAbiertoLocal inscriptosCupoConcursoAbiertoEJB;
	@EJB
	SexoGeneroLocal sexoGeneroEJB;
	
	private String codigoCupo;
	private String ciString;
	private InscripcionCupoConcursoAbiertoDTO inscripcion;
	private List<SexoGeneroDTO> listaSexoGenero;
	private long idSexoGeneroSeleccionado;   

	private boolean error;
	private String mensajeError;
	
	private String rederizarEnForm="formError,messages,messagesInferior,primerNombreId,segundoNombreId,primerApellidoId,segundoApellidoId,ciId,"+
								   "fechaNacimientoId,sexoGeneroId,telefonoContactoId,telefonoCelularId,correoElectronicoId,direccionContactoId";

	private String claveSitioWeb;
	private String claveSitioSecreta;
	
	@PostConstruct
	public void init(){
		
		String ambiente="LOCALHOST";
		try {
			ambiente=FuncionesUtiles.obtenerAmbiente();
			log.info("Se obtuvo el ambiente para la inscripicon");
		} catch (ConcursoAbiertoException e1) {
			log.error("Ocurrio un error al obtener el ambiente para la inscripcion. "+e1.getMessage());
		}
		
		if(ambiente.equalsIgnoreCase("LOCALHOST")){
			log.info("Cargo claves localhost");
			claveSitioWeb="xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
			claveSitioSecreta="xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
		}
		if(ambiente.equalsIgnoreCase("DESA")){
			log.info("Cargo claves desa");
			claveSitioWeb="xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
			claveSitioSecreta="xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
		}
		if(ambiente.equalsIgnoreCase("TEST")){
			log.info("Cargo claves test");
			claveSitioWeb="xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
			claveSitioSecreta="xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
		}
		if(ambiente.equalsIgnoreCase("PROD")){
			log.info("Cargo claves prod");
			claveSitioWeb="xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
			claveSitioSecreta="xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
	}
		
		error=false;
		
		this.codigoCupo= (String) ((ServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getParameter("cupo");
		this.inscripcion = new InscripcionCupoConcursoAbiertoDTO();
			try {
				CupoConcursoAbiertoDTO c =cupoConcursoAbiertoEJB.obtenerCupoVigentePorCodigoCupo(codigoCupo);
				log.info("Se obtuvo el cupo para el codgio "+codigoCupo);
				inscripcion.setCupoConcursoAbiertoDTO(c);
				listaSexoGenero = sexoGeneroEJB.obtenerListaSexoGenero();
			} catch (ConcursoAbiertoException e) {
				log.error("Ocurrio un error al obtener el cupo para el coddigo "+codigoCupo+". "+e.getMessage());
				error=true;
				mensajeError=e.getMessage();
			}
	}
	
	public String inscribirse(){
		
	
		String gRecaptchaResponse = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("g-recaptcha-response");
		boolean verificacionCaptcha=false; 
		try {
			verificacionCaptcha=VerifyRecaptcha.verify(gRecaptchaResponse,claveSitioSecreta);
		} catch (IOException e3) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage("formICL", new FacesMessage(FacesMessage.SEVERITY_ERROR,null, "El captcha no se pudo verificar por favor inténtelo nuevamente"));
			log.error("Ocurrio un error al verificar el captcha. "+e3.getMessage());
			return null;
		}
		if(!verificacionCaptcha){
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage("formICL", new FacesMessage(FacesMessage.SEVERITY_ERROR,null, "El captcha no se pudo verificar por favor inténtelo nuevamente"));
			return null;
		}

		String[] parts = ciString.split("-");
		inscripcion.setCi(Integer.valueOf(parts[0]));
		inscripcion.setBarra(Integer.valueOf(parts[1]));
		
		for(SexoGeneroDTO s: listaSexoGenero){
			if(idSexoGeneroSeleccionado==s.getId())
				inscripcion.setSexoGeneroDTO(new SexoGeneroDTO(idSexoGeneroSeleccionado, s.getDescripcion()));
		}
		
		Integer numumerInscripcion=null;
		try {
			inscripcion = inscriptosCupoConcursoAbiertoEJB.inscribirCupoConcursoAbierto(inscripcion);
			log.info("Se inscribio la ci "+inscripcion.getCi()+" al cupo "+inscripcion.getCupoConcursoAbiertoDTO().getId());
		} catch (ConcursoAbiertoException e1) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage("formICL", new FacesMessage(FacesMessage.SEVERITY_ERROR,null, e1.getMessage()));
			log.error("Ocurrio un error al inscribir la ci "+inscripcion.getCi()+" al cupo "+inscripcion.getCupoConcursoAbiertoDTO().getId()+". "+e1.getMessage());
			return null;
		}
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		session.setAttribute("INSCRIPCION",inscripcion );
		try {
			FuncionesUtiles.enviarCorreoAltaInscripcion(inscripcion);
		} catch (ConcursoAbiertoException e) {
			log.error("Ocurrion un error al enviar el correo de confirmacion. "+e.getMessage());
		} catch (Exception e) {
			log.error("Ocurrion un error al enviar el correo de confirmacion. "+e.getMessage());
		}
		return "/pages/datosInscripcion?faces-redirect=true";
	}
	
	//GETTERS AND SETTERS	
	public String getCodigoCupo() {
		return codigoCupo;
	}

	public void setCodigoCupo(String codigoCupo) {
		this.codigoCupo = codigoCupo;
	}

	public InscripcionCupoConcursoAbiertoDTO getInscripcion() {
		return inscripcion;
	}

	public void setInscripcion(InscripcionCupoConcursoAbiertoDTO inscripcion) {
		this.inscripcion = inscripcion;
	}

	public String getCiString() {
		return ciString;
	}

	public void setCiString(String ciString) {
		this.ciString = ciString;
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
	
	public List<SexoGeneroDTO> getListaSexoGenero() {
		return listaSexoGenero;
	}

	public void setListaSexoGenero(List<SexoGeneroDTO> listaSexoGenero) {
		this.listaSexoGenero = listaSexoGenero;
	}

	public long getIdSexoGeneroSeleccionado() {
		return idSexoGeneroSeleccionado;
	}

	public void setIdSexoGeneroSeleccionado(long idSexoGeneroSeleccionado) {
		this.idSexoGeneroSeleccionado = idSexoGeneroSeleccionado;
	}



	public String getRederizarEnForm() {
		return rederizarEnForm;
	}

	public void setRederizarEnForm(String rederizarEnForm) {
		this.rederizarEnForm = rederizarEnForm;
	}



	public String getClaveSitioWeb() {
		return claveSitioWeb;
	}



	public void setClaveSitioWeb(String claveSitioWeb) {
		this.claveSitioWeb = claveSitioWeb;
	}



	public String getClaveSitioSecreta() {
		return claveSitioSecreta;
	}



	public void setClaveSitioSecreta(String claveSitioSecreta) {
		this.claveSitioSecreta = claveSitioSecreta;
	}

	
	
}
