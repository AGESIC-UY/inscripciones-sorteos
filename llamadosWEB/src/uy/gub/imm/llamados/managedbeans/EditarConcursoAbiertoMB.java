package uy.gub.imm.llamados.managedbeans;


import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletRequest;

import org.jboss.logging.Logger;

import sun.util.logging.resources.logging;
import uy.gub.imm.llamados.dto.ConcursoAbiertoDTO;
import uy.gub.imm.llamados.dto.CupoConcursoAbiertoDTO;
import uy.gub.imm.llamados.dto.TipoCupoConcursoAbiertoDTO;
import uy.gub.imm.llamados.ejb.ConcursoAbiertoLocal;
import uy.gub.imm.llamados.ejb.CupoConcursoAbiertoLocal;
import uy.gub.imm.llamados.ejb.TipoCupoConcursoAbiertoLocal;
import uy.gub.imm.llamados.exceptions.ConcursoAbiertoException;
import uy.gub.imm.llamados.util.MensajePagina;

@ManagedBean
@ViewScoped
public class EditarConcursoAbiertoMB {
	
	private static Logger log= Logger.getLogger(EditarConcursoAbiertoMB.class);

	@EJB
	ConcursoAbiertoLocal concursoAbiertoEJB;
	@EJB
	CupoConcursoAbiertoLocal CupoConcursoAbiertoEJB;
	@EJB
	TipoCupoConcursoAbiertoLocal tipoCupoConcursoAbiertoEJB;
	@EJB
	CupoConcursoAbiertoLocal cupoConcursoAbiertoEJB;
	

	private ConcursoAbiertoDTO concursoAbierto;
	private List<TipoCupoConcursoAbiertoDTO> listaTipoCupoConcursoAbierto;
	private String codigo=null;


	
	@PostConstruct
	public void init(){

		codigo= (String) ((ServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getParameter("concurso");
		initPage(codigo);
		
	}
	
	
	private void initPage(String codigo){
		try {
			concursoAbierto=concursoAbiertoEJB.obtenerConcuroAbiertoPorCodigo(codigo);
			concursoAbierto.setListaCupoConcursoAbiertoDTO(CupoConcursoAbiertoEJB.obtenerCuposPorCodigoConcurso(codigo));
			
			listaTipoCupoConcursoAbierto= new ArrayList<TipoCupoConcursoAbiertoDTO>();
			List<Long> idTipoCupoConcurso = this.obtenerIdsTipoCupoConcurso(concursoAbierto);
			for(TipoCupoConcursoAbiertoDTO tipoCupo: tipoCupoConcursoAbiertoEJB.obtenerTiposDeCupo()){
				if(!idTipoCupoConcurso.contains((Long)tipoCupo.getId())){
					listaTipoCupoConcursoAbierto.add(tipoCupo);
				}
			}

		} catch (ConcursoAbiertoException e) {
			MensajePagina.enviarMensajeSEVERITYERROR("editForm", "Ocurrió un error al obtener el llamado con código "+codigo);
		}
	}
	
	public void actualizarConcursoAbierto(){
		
	try {
			
			for (CupoConcursoAbiertoDTO cupo : concursoAbierto.getListaCupoConcursoAbiertoDTO()){
				log.info(cupo.getCodigo());
				CupoConcursoAbiertoDTO cupoConcursoAbiertoDTO=cupoConcursoAbiertoEJB.obtenerCupoPorCodigoCupo(cupo.getCodigo());
				if(cupoConcursoAbiertoDTO.getSorteo()!=null){
					MensajePagina.enviarMensajeSEVERITYERROR("editForm", "No se puede modificar el concurso. El cupo "+cupo.getCodigo()+" ya fue sorteado");
					return ;
				}
					
			}
		
			for(TipoCupoConcursoAbiertoDTO tipoCupo : listaTipoCupoConcursoAbierto){
				if(tipoCupo.isSeleccionado()){
					CupoConcursoAbiertoDTO cupo= new CupoConcursoAbiertoDTO((long)0, concursoAbierto.getCodigo()+"("+tipoCupo.getCodigo()+")", concursoAbierto.getCodigo()+"("+tipoCupo.getCodigo()+")", null, tipoCupo);
					concursoAbierto.getListaCupoConcursoAbiertoDTO().add(cupo);
				}
			}
			concursoAbiertoEJB.editarConcuroAbiertoPorCodigo(concursoAbierto);
			MensajePagina.enviarMensajeFACESMESSAGES("editForm", "El llamado se actualizó con éxito");
			concursoAbierto=concursoAbiertoEJB.obtenerConcuroAbiertoPorCodigo(concursoAbierto.getCodigo());
			concursoAbierto.setListaCupoConcursoAbiertoDTO(CupoConcursoAbiertoEJB.obtenerCuposPorCodigoConcurso(concursoAbierto.getCodigo()));
			initPage(codigo);
			
			
		} catch (ConcursoAbiertoException e) {
			MensajePagina.enviarMensajeSEVERITYERROR("editForm", e.getMessage());
		
		}
	}
	
	private List<Long> obtenerIdsTipoCupoConcurso(ConcursoAbiertoDTO concurso){
		
		List<Long> resultado = new ArrayList<Long>();
		for(CupoConcursoAbiertoDTO cupo: concurso.getListaCupoConcursoAbiertoDTO())
			resultado.add(cupo.getTipoCupoConcursoAbierto().getId());
		return resultado;
	}
	
	public boolean getExisteCupoParaAgregar(){
		return (listaTipoCupoConcursoAbierto.size()>0);
	}
	
	public ConcursoAbiertoDTO getConcursoAbierto() {
		return concursoAbierto;
	}

	public void setConcursoAbierto(ConcursoAbiertoDTO concursoAbierto) {
		this.concursoAbierto = concursoAbierto;
	}

	public List<TipoCupoConcursoAbiertoDTO> getListaTipoCupoConcursoAbierto() {
		return listaTipoCupoConcursoAbierto;
	}

	public void setListaTipoCupoConcursoAbierto(List<TipoCupoConcursoAbiertoDTO> listaTipoCupoConcursoAbierto) {
		this.listaTipoCupoConcursoAbierto = listaTipoCupoConcursoAbierto;
	}
	
	
}
