package uy.gub.imm.llamados.dto;

import java.util.Date;

import uy.gub.imm.llamados.entity.PosicionSorteoInscripto;

public class PosicionSorteoInscriptoDTO {
	
	private long idSorteo;
	
	private int numeroInscripcion;
	
	private int posicion;
	
	private int ci;
	
	private int ciBarra;
	
	private String primerNombre;
	
	private String segundoNombre;
	
	private String primerApellido;
	
	private String segundoApellido;

	private Date fechaNacimiento;
	
	private String telefono;
	
	private String celular;
	
	private String correo;
	
	private String fechaInscripcion;

	public long getIdSorteo() {
		return idSorteo;
	}

	public void setIdSorteo(long idSorteo) {
		this.idSorteo = idSorteo;
	}

	public int getNumeroInscripcion() {
		return numeroInscripcion;
	}

	public void setNumeroInscripcion(int numeroInscripcion) {
		this.numeroInscripcion = numeroInscripcion;
	}

	public int getPosicion() {
		return posicion;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

	public int getCi() {
		return ci;
	}

	public void setCi(int ci) {
		this.ci = ci;
	}

	public int getCiBarra() {
		return ciBarra;
	}

	public void setCiBarra(int ciBarra) {
		this.ciBarra = ciBarra;
	}

	public String getPrimerNombre() {
		return primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getSegundoNombre() {
		return segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getFechaInscripcion() {
		return fechaInscripcion;
	}

	public void setFechaInscripcion(String fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}

	public PosicionSorteoInscriptoDTO() {
		super();
	}
	
	public PosicionSorteoInscriptoDTO(PosicionSorteoInscripto posicionSorteoInscripto){
		this.celular=posicionSorteoInscripto.getCelular();
		this.ci=posicionSorteoInscripto.getCi();
		this.ciBarra=posicionSorteoInscripto.getCiBarra();
		this.correo=posicionSorteoInscripto.getCorreo();
		this.fechaInscripcion=posicionSorteoInscripto.getFechaInscripcion();
		this.fechaNacimiento=posicionSorteoInscripto.getFechaNacimiento();
		this.idSorteo=posicionSorteoInscripto.getPk().getIdSorteo();
		this.numeroInscripcion=posicionSorteoInscripto.getPk().getNumeroInscripcion();
		this.posicion=posicionSorteoInscripto.getPosicion();
		this.primerApellido=posicionSorteoInscripto.getPrimerApellido();
		this.primerNombre=posicionSorteoInscripto.getPrimerNombre();
		this.segundoApellido=posicionSorteoInscripto.getSegundoApellido();
		this.segundoNombre=posicionSorteoInscripto.getSegundoNombre();
		this.telefono=posicionSorteoInscripto.getTelefono();
	}
	
	
	



}
