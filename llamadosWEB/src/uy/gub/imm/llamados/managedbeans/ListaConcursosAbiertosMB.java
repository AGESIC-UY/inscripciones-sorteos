package uy.gub.imm.llamados.managedbeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import uy.gub.imm.llamados.dto.ConcursoAbiertoDTO;
import uy.gub.imm.llamados.ejb.ConcursoAbiertoLocal;
import uy.gub.imm.llamados.exceptions.ConcursoAbiertoException;


@ManagedBean(name = "listaConcursosAbiertosMB", eager = true)
@ViewScoped
public class ListaConcursosAbiertosMB {
	
	
	@EJB
	ConcursoAbiertoLocal concursoAbiertoEJB;
	
	private List<ConcursoAbiertoDTO> listaConcursoAbierto;
	
	
	@PostConstruct
	public void init(){
		
		try {
			FacesContext fc = FacesContext.getCurrentInstance();
			Object o =fc.getExternalContext().getSessionMap().get("role");
			listaConcursoAbierto=concursoAbiertoEJB.obtenerConcurosAbiertos();
		} catch (ConcursoAbiertoException e) {

		}
		
	}
	
	public void verConcursoAbierto(){
		
	}


	public List<ConcursoAbiertoDTO> getListaConcursoAbierto() {
		return listaConcursoAbierto;
	}


	public void setListaConcursoAbierto(List<ConcursoAbiertoDTO> listaConcursoAbierto) {
		this.listaConcursoAbierto = listaConcursoAbierto;
	}
	
	
	
	

	

}
