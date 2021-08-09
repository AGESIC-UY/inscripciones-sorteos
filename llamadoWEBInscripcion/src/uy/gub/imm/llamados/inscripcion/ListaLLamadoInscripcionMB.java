package uy.gub.imm.llamados.inscripcion;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.jboss.logging.Logger;

import uy.gub.imm.llamados.dto.ConcursoAbiertoDTO;
import uy.gub.imm.llamados.ejb.ConcursoAbiertoLocal;
import uy.gub.imm.llamados.exceptions.ConcursoAbiertoException;


@ManagedBean
@ViewScoped
public class ListaLLamadoInscripcionMB {
	
	private static final Logger log = Logger.getLogger(ListaLLamadoInscripcionMB.class);
	
	@EJB
	ConcursoAbiertoLocal concursoAbiertoEJB;
	
	private List<ConcursoAbiertoDTO> listaConcursosAbiertos;
	
	
	@PostConstruct
	public void init(){
		try {
			listaConcursosAbiertos =concursoAbiertoEJB.obtenerConcurosAbiertosVigentes();
		} catch (ConcursoAbiertoException e) {
		}
		
	}


	public List<ConcursoAbiertoDTO> getListaConcursosAbiertos() {
		return listaConcursosAbiertos;
	}


	public void setListaConcursosAbiertos(List<ConcursoAbiertoDTO> listaConcursosAbiertos) {
		this.listaConcursosAbiertos = listaConcursosAbiertos;
	}
	
	
	


}
