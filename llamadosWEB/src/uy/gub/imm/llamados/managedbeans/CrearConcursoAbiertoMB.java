package uy.gub.imm.llamados.managedbeans;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import uy.gub.imm.llamados.dto.ConcursoAbiertoDTO;
import uy.gub.imm.llamados.dto.CupoConcursoAbiertoDTO;
import uy.gub.imm.llamados.dto.TipoCupoConcursoAbiertoDTO;
import uy.gub.imm.llamados.ejb.ConcursoAbiertoLocal;
import uy.gub.imm.llamados.ejb.TipoCupoConcursoAbiertoLocal;
import uy.gub.imm.llamados.exceptions.ConcursoAbiertoException;
import uy.gub.imm.llamados.util.MensajePagina;
import org.jboss.logging.Logger;


@ManagedBean
@ViewScoped
public class CrearConcursoAbiertoMB {
	
	private static final Logger log = Logger.getLogger(CrearConcursoAbiertoMB.class);
	
	@EJB
	TipoCupoConcursoAbiertoLocal tipoCupoConcursoAbierto;
	
	@EJB
	ConcursoAbiertoLocal concursoAbiertoEJB;
	
	private ConcursoAbiertoDTO concursoAbierto; 
	
	private List<TipoCupoConcursoAbiertoDTO> listaTipoCupoConcursoAbierto;
	
	private List<CupoConcursoAbiertoDTO> listaCupoConcursoAbierto;
	

	

	
	@PostConstruct
	public void init(){

		concursoAbierto= new ConcursoAbiertoDTO();
		listaTipoCupoConcursoAbierto= tipoCupoConcursoAbierto.obtenerTiposDeCupo();
	
	}
	
	public String altaLlamado(){
		
		
		if(!existenErrores()){
			listaCupoConcursoAbierto = new ArrayList<CupoConcursoAbiertoDTO>();
			CupoConcursoAbiertoDTO cupo;
			for(TipoCupoConcursoAbiertoDTO tipoCupo : listaTipoCupoConcursoAbierto){
				if(tipoCupo.isSeleccionado()){
					cupo= new CupoConcursoAbiertoDTO((long)0, concursoAbierto.getCodigo()+"("+tipoCupo.getCodigo()+")", concursoAbierto.getCodigo()+"("+tipoCupo.getCodigo()+")", null, tipoCupo);
					listaCupoConcursoAbierto.add(cupo);
				}
			}
			concursoAbierto.setListaCupoConcursoAbiertoDTO(listaCupoConcursoAbierto);
			try {
				concursoAbiertoEJB.altaConcursoAbierto(concursoAbierto);
				MensajePagina.enviarMensajeFACESMESSAGES("altaFomr", "Se dio de alta al concurso abierto");
			} catch (ConcursoAbiertoException e) {
				log.info("Ocurrió un error al intentar dar de alta el llamado: "+e.getMessage());
				MensajePagina.enviarMensajeSEVERITYERROR("altaForm", "Ocurrió un error al intentar dar de alta el llamado: "+e.getMessage());
				return null;

				
			}
			
		}
		return null;
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
	
	
	
	private boolean codigoFormatoCorrecto(String codigo){
		return true;
	}
	
	private boolean existenErrores(){
		
		//Chequeo codigo
		if(!codigoFormatoCorrecto(concursoAbierto.getCodigo())){
			MensajePagina.enviarMensajeSEVERITYERROR("altaForm:codigo", "El formato del codigo no es correcto");
			return true;
		}
		
		//Chequeo fecha
		if(concursoAbierto.getFechaDesde().compareTo(concursoAbierto.getFechaHasta())>0){

			MensajePagina.enviarMensajeSEVERITYERROR("altaForm:fechaHasta", "La fecha de inicio debe ser menor a la fecha de fin");
			return true;
		}
		
		if(concursoAbierto.getFechaHasta().before(new Date())){
			log.error("La fecha de fin de inscripcion es anterior a la fecha de hoy");
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
			String strHoy = dateFormat.format(new Date());  
			MensajePagina.enviarMensajeSEVERITYERROR("altaForm:fechaHasta", "La fecha de fin debe ser mayor a "+strHoy);
			return true;
		}
		
		//Chequeo que se halla seleccionado por lo menos un cupo
		if(!existeCupoSeleccionado()){
			MensajePagina.enviarMensajeSEVERITYERROR("altaForm:tc", "Debe seleccionar al menos un tipo de cupo");
			return true;
		}
		return false;
	}
	
	
	private boolean existeCupoSeleccionado(){
		
		for(TipoCupoConcursoAbiertoDTO tipoCupo : this.listaTipoCupoConcursoAbierto){
			if(tipoCupo.isSeleccionado())
				return true;
		}
		return false;
	}
	
	
	
	
	
		

}
