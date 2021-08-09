package uy.gub.imm.llamados.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import uy.gub.imm.llamados.entity.ConcursoAbierto;

public class ConcursoAbiertoDTO {
	

	private Long id;
	
	private String codigo;

	private String descripcion;
	
	private Integer edadTope;

	private Date fechaDesde;

	private Date fechaHasta;
	
	private List<CupoConcursoAbiertoDTO> listaCupoConcursoAbiertoDTO;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getEdadTope() {
		return edadTope;
	}

	public void setEdadTope(Integer edadTope) {
		this.edadTope = edadTope;
	}

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public List<CupoConcursoAbiertoDTO> getListaCupoConcursoAbiertoDTO() {
		return listaCupoConcursoAbiertoDTO;
	}

	public void setListaCupoConcursoAbiertoDTO(List<CupoConcursoAbiertoDTO> listaCupoConcursoAbiertoDTO) {
		this.listaCupoConcursoAbiertoDTO = listaCupoConcursoAbiertoDTO;
	}

	public ConcursoAbiertoDTO(Long id, String codigo, String descripcion, Integer edadTope, Date fechaDesde,
			Date fechaHasta, List<CupoConcursoAbiertoDTO> listaCupoConcursoAbiertoDTO) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.edadTope = edadTope;
		this.fechaDesde = fechaDesde;
		this.fechaHasta = fechaHasta;
		this.listaCupoConcursoAbiertoDTO = listaCupoConcursoAbiertoDTO;
	}

	public ConcursoAbiertoDTO() {
		super();
		this.listaCupoConcursoAbiertoDTO= new ArrayList<CupoConcursoAbiertoDTO>();
	}
	
	public ConcursoAbiertoDTO(ConcursoAbierto concurso){
		
		this.codigo=concurso.getCodigo();
		this.descripcion=concurso.getDescripcion();
		this.edadTope=concurso.getEdadTope();
		this.fechaDesde=concurso.getFechaDesde();
		this.fechaHasta=concurso.getFechaHasta();
		this.id=concurso.getId();
		
	}
	
	
	

}
