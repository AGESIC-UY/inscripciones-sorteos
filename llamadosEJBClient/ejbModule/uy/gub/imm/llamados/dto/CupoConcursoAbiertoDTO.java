package uy.gub.imm.llamados.dto;

import java.util.List;

import uy.gub.imm.llamados.entity.CupoConcursoAbierto;

public class CupoConcursoAbiertoDTO {
	

	private long id;
	
	private String codigo;

	private String descripcion;
	
	private Integer plazas;
	
	private TipoCupoConcursoAbiertoDTO tipoCupoConcursoAbierto;
	
	private ConcursoAbiertoDTO concurso;
	
	private SorteoCupoConcursoAbriertoDTO sorteo;
	
	private List<PosicionSorteoInscriptoDTO> listaSorteo;

	
	private int cantidadInscriptos;

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public Integer getPlazas() {
		return plazas;
	}

	public void setPlazas(Integer plazas) {
		this.plazas = plazas;
	}

	public TipoCupoConcursoAbiertoDTO getTipoCupoConcursoAbierto() {
		return tipoCupoConcursoAbierto;
	}

	public void setTipoCupoConcursoAbierto(TipoCupoConcursoAbiertoDTO tipoCupoConcursoAbierto) {
		this.tipoCupoConcursoAbierto = tipoCupoConcursoAbierto;
	}

	public ConcursoAbiertoDTO getConcurso() {
		return concurso;
	}

	public void setConcurso(ConcursoAbiertoDTO concurso) {
		this.concurso = concurso;
	}

	public CupoConcursoAbiertoDTO(long id, String codigo, String descripcion, Integer plazas,
			TipoCupoConcursoAbiertoDTO tipoCupoConcursoAbierto) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.plazas = plazas;
		this.tipoCupoConcursoAbierto = tipoCupoConcursoAbierto;
	}

	public CupoConcursoAbiertoDTO(CupoConcursoAbierto cupo ) {
		super();
		this.id = cupo.getId();
		this.codigo = cupo.getCodigo();
		this.descripcion = cupo.getDescripcion();
		this.plazas = cupo.getPlazas();
	}

	public CupoConcursoAbiertoDTO() {
		super();
	}

	public int getCantidadInscriptos() {
		return cantidadInscriptos;
	}

	public void setCantidadInscriptos(int cantidadInscriptos) {
		this.cantidadInscriptos = cantidadInscriptos;
	}

	public List<PosicionSorteoInscriptoDTO> getListaSorteo() {
		return listaSorteo;
	}

	public void setListaSorteo(List<PosicionSorteoInscriptoDTO> listaSorteo) {
		this.listaSorteo = listaSorteo;
	}

	public SorteoCupoConcursoAbriertoDTO getSorteo() {
		return sorteo;
	}

	public void setSorteo(SorteoCupoConcursoAbriertoDTO sorteo) {
		this.sorteo = sorteo;
	}
	
	
	
	
	
	
	
	

}
