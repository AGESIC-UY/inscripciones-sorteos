package uy.gub.imm.llamados.managedbeans;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletRequest;

import uy.gub.imm.llamados.dto.ConcursoAbiertoDTO;
import uy.gub.imm.llamados.dto.CupoConcursoAbiertoDTO;
import uy.gub.imm.llamados.dto.SorteoCupoConcursoAbriertoDTO;
import uy.gub.imm.llamados.ejb.ConcursoAbiertoLocal;
import uy.gub.imm.llamados.ejb.CupoConcursoAbiertoLocal;
import uy.gub.imm.llamados.ejb.SorteoCupoConcursoAbiertoLocal;
import uy.gub.imm.llamados.exceptions.ConcursoAbiertoException;
import uy.gub.imm.llamados.util.MensajePagina;

@ManagedBean
@ViewScoped
public class SorteoMB {
	
	@EJB
	CupoConcursoAbiertoLocal cupoConcursoAbiertoEJB;
	
	@EJB
	SorteoCupoConcursoAbiertoLocal sorteCupoConcursoAbiertoEJB;
	
	 @EJB
	 ConcursoAbiertoLocal concursoAbiertoEJB;
	
	private CupoConcursoAbiertoDTO cupoConcursoAbiertoDTO;
	
	
	
	//private SorteoCupoConcursoAbriertoDTO sorteoCupoConcursoAbrierto;
	
	private String codigo;

	
	
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
	
	
	public void realizarSorteo(){
		try {
			Date fechaFin=cupoConcursoAbiertoDTO.getConcurso().getFechaHasta();
			Date hoy = new Date();
			if(hoy.before(fechaFin)){
				MensajePagina.enviarMensajeSEVERITYERROR("altaForm:btnSorteo", "El  periodo de incripcion esta vigente");
				return;
			}
			sorteCupoConcursoAbiertoEJB.realizarSorteo(cupoConcursoAbiertoDTO);
			cupoConcursoAbiertoDTO=cupoConcursoAbiertoEJB.obtenerCupoPorCodigoCupo(codigo);
			
		} catch (ConcursoAbiertoException e) {
			e.printStackTrace();
		}
		
	}

	


	public CupoConcursoAbiertoDTO getCupoConcursoAbiertoDTO() {
		return cupoConcursoAbiertoDTO;
	}

	public void setCupoConcursoAbiertoDTO(CupoConcursoAbiertoDTO cupoConcursoAbiertoDTO) {
		this.cupoConcursoAbiertoDTO = cupoConcursoAbiertoDTO;
	}
	
/*	public SorteoCupoConcursoAbriertoDTO getSorteoCupoConcursoAbrierto() {
		return sorteoCupoConcursoAbrierto;
	}

	public void setSorteoCupoConcursoAbrierto(SorteoCupoConcursoAbriertoDTO sorteoCupoConcursoAbrierto) {
		this.sorteoCupoConcursoAbrierto = sorteoCupoConcursoAbrierto;
	}*/




	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	
}
