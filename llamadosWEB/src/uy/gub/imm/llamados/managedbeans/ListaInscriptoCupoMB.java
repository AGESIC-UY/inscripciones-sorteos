package uy.gub.imm.llamados.managedbeans;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletRequest;
import org.jboss.logging.Logger;


import uy.gub.imm.llamados.dto.CupoConcursoAbiertoDTO;
import uy.gub.imm.llamados.dto.InscripcionCupoConcursoAbiertoDTO;
import uy.gub.imm.llamados.ejb.CupoConcursoAbiertoLocal;
import uy.gub.imm.llamados.ejb.InscriptosCupoConcursoAbiertoLocal;
import uy.gub.imm.llamados.exceptions.ConcursoAbiertoException;
import uy.gub.imm.llamados.util.FuncionesUtiles;

@ManagedBean
@ViewScoped
public class ListaInscriptoCupoMB {
	
	private static final Logger log = Logger.getLogger(ListaInscriptoCupoMB.class);
			
	@EJB
	CupoConcursoAbiertoLocal cupoConcursoAbiertoEJB;
	
	@EJB
	InscriptosCupoConcursoAbiertoLocal inscriptosCupoConcursoAbiertoEJB;
	
	private CupoConcursoAbiertoDTO cupoConcursoAbiertoDTO;
	
	
	private List<InscripcionCupoConcursoAbiertoDTO> listaInscriptos;

	@PostConstruct
	public void init(){
		
		String codigo= (String) ((ServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getParameter("cupo");
		try {
			cupoConcursoAbiertoDTO=cupoConcursoAbiertoEJB.obtenerCupoPorCodigoCupo(codigo);
		} catch (ConcursoAbiertoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		listaInscriptos=inscriptosCupoConcursoAbiertoEJB.obtenerInscriptosPorIdCupo(cupoConcursoAbiertoDTO.getId());
		

		
	}
	
	public void reenviarMailInscripcion(Long idInscripcion){

		try {
			
			InscripcionCupoConcursoAbiertoDTO inscripcion = inscriptosCupoConcursoAbiertoEJB.obtenerInscripcionPorIdInscripcion(idInscripcion);
			FuncionesUtiles.enviarCorreoAltaInscripcion(inscripcion);
			
		} catch (ConcursoAbiertoException e) {

		}
	}
	
	public List<InscripcionCupoConcursoAbiertoDTO> getListaInscriptos() {
		return listaInscriptos;
	}

	public void setListaInscriptos(List<InscripcionCupoConcursoAbiertoDTO> listaInscriptos) {
		this.listaInscriptos = listaInscriptos;
	}

	public CupoConcursoAbiertoDTO getCupoConcursoAbiertoDTO() {
		return cupoConcursoAbiertoDTO;
	}

	public void setCupoConcursoAbiertoDTO(CupoConcursoAbiertoDTO cupoConcursoAbiertoDTO) {
		this.cupoConcursoAbiertoDTO = cupoConcursoAbiertoDTO;
	}
	
	

	
}
