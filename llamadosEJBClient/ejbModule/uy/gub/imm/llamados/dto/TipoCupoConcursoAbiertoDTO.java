package uy.gub.imm.llamados.dto;

import uy.gub.imm.llamados.entity.TipoCupoConcursoAbierto;

public class TipoCupoConcursoAbiertoDTO {

	private long id;
	
	private String codigo;

	private String descripcion;
	
	private boolean seleccionado;

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
	
	public boolean isSeleccionado() {
		return seleccionado;
	}

	public void setSeleccionado(boolean seleccionado) {
		this.seleccionado = seleccionado;
	}

	public TipoCupoConcursoAbiertoDTO(long id, String codigo, String descripcion,boolean seleccionado) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.seleccionado=seleccionado;
	}

	public TipoCupoConcursoAbiertoDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public TipoCupoConcursoAbiertoDTO( TipoCupoConcursoAbierto tipoCupoConcursoAbierto) {
		
		this.id=tipoCupoConcursoAbierto.getId();
		this.codigo=tipoCupoConcursoAbierto.getCodigo();
		this.descripcion=tipoCupoConcursoAbierto.getDescripcion();
	}
	

}
