package uy.gub.imm.llamados.managedbeans;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.mail.MessagingException;
import javax.servlet.ServletRequest;
import org.jboss.logging.Logger;
import uy.gub.imm.llamados.dto.CupoConcursoAbiertoDTO;
import uy.gub.imm.llamados.dto.InscripcionCupoConcursoAbiertoDTO;
import uy.gub.imm.llamados.dto.SorteoCupoConcursoAbriertoDTO;
import uy.gub.imm.llamados.ejb.CupoConcursoAbiertoLocal;
import uy.gub.imm.llamados.ejb.InscriptosCupoConcursoAbiertoLocal;
import uy.gub.imm.llamados.exceptions.ConcursoAbiertoException;
import uy.gub.imm.llamados.util.Constantes;
import uy.gub.imm.llamados.util.MensajePagina;

@ManagedBean
@ViewScoped
public class EnviarMensajeMB {
	
	private static final Logger log = Logger.getLogger(EnviarMensajeMB.class);

	@EJB
	CupoConcursoAbiertoLocal cupoConcursoAbiertoEJB;

	@EJB
	InscriptosCupoConcursoAbiertoLocal inscriptosCupoConcursoAbiertoEJB;
	
	private CupoConcursoAbiertoDTO cupoConcursoAbiertoDTO;

	private String codigo;
	
	private String mensajeCorreo;
	
	@PostConstruct
	public void init(){
		
		codigo= (String) ((ServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getParameter("cupo");
		try {
			cupoConcursoAbiertoDTO=cupoConcursoAbiertoEJB.obtenerCupoPorCodigoCupo(codigo);
			if(cupoConcursoAbiertoDTO.getSorteo()==null)
				cupoConcursoAbiertoDTO.setSorteo(new SorteoCupoConcursoAbriertoDTO());
		} catch (ConcursoAbiertoException e) {
			e.printStackTrace();
		}
	}
	
	public void enviarCorreos(){
		
		log.info("Enviando correos....");
		List<InscripcionCupoConcursoAbiertoDTO> listaInscriptos= inscriptosCupoConcursoAbiertoEJB.obtenerInscriptosPorIdCupo(cupoConcursoAbiertoDTO.getId());
		for(InscripcionCupoConcursoAbiertoDTO inscripto: listaInscriptos){
			String correo=inscripto.getCorreoElectronico();
			String asunto= "Notificacion, llamado abierto "+cupoConcursoAbiertoDTO.getConcurso().getDescripcion()+", cupo "+cupoConcursoAbiertoDTO.getDescripcion();
			/*
			 * CODIGO PARA ENVIO DE MAIL
			 * 
			 * 
			 * */
			
		}

		MensajePagina.enviarMensajeFACESMESSAGES("envioMensajesForm","Los mensajes se enviaron correctamente.");
		
	}

	public CupoConcursoAbiertoDTO getCupoConcursoAbiertoDTO() {
		return cupoConcursoAbiertoDTO;
	}

	public void setCupoConcursoAbiertoDTO(CupoConcursoAbiertoDTO cupoConcursoAbiertoDTO) {
		this.cupoConcursoAbiertoDTO = cupoConcursoAbiertoDTO;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getMensajeCorreo() {
		return mensajeCorreo;
	}

	public void setMensajeCorreo(String mensajeCorreo) {
		this.mensajeCorreo = mensajeCorreo;
	}
	
	
	
	
	
	
}
