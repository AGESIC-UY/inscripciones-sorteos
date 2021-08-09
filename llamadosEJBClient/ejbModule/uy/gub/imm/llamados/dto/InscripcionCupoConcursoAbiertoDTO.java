package uy.gub.imm.llamados.dto;

import java.util.Date;

import javax.validation.constraints.Past;

import uy.gub.imm.llamados.entity.InscripcionCupoConcursoAbierto;

public class InscripcionCupoConcursoAbiertoDTO {
	
	private Long id;
	
	private Integer ci;
	
	private Integer barra;
	
	private String primerNombre;

	private String segundoNombre;

	private String primerApellido;
	
	private String segundoApellido;
	
	private Date fechaNacimiento;
	
	private String telefono;
	
	private String celular;
	
	private String correoElectronico;
	
	private String direccion;
	
	private CupoConcursoAbiertoDTO cupoConcursoAbiertoDTO;
	
	private SexoGeneroDTO sexoGeneroDTO;
	
	@Past(message="Error en facha")
	private Date fechaInscripcion;
	
	private Integer numeroInscripcion;

	private String token;

	private boolean eliminada;
	
	private Date fechaEliminada;


	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCi() {
		return ci;
	}

	public void setCi(Integer ci) {
		this.ci = ci;
	}

	public Integer getBarra() {
		return barra;
	}

	public void setBarra(Integer barra) {
		this.barra = barra;
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

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public CupoConcursoAbiertoDTO getCupoConcursoAbiertoDTO() {
		return cupoConcursoAbiertoDTO;
	}

	public void setCupoConcursoAbiertoDTO(CupoConcursoAbiertoDTO cupoConcursoAbiertoDTO) {
		this.cupoConcursoAbiertoDTO = cupoConcursoAbiertoDTO;
	}
	
	public Date getFechaInscripcion() {
		return fechaInscripcion;
	}

	public void setFechaInscripcion(Date fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}

	public Integer getNumeroInscripcion() {
		return numeroInscripcion;
	}

	public void setNumeroInscripcion(Integer numeroInscripcion) {
		this.numeroInscripcion = numeroInscripcion;
	}

	public InscripcionCupoConcursoAbiertoDTO(InscripcionCupoConcursoAbierto inscripcion) {
		
		this.setBarra(inscripcion.getBarra());
		this.setCelular(inscripcion.getCelular());
		this.setCi(inscripcion.getCi());
		this.setCorreoElectronico(inscripcion.getCorreoElectronico());
		this.setFechaNacimiento(inscripcion.getFechaNacimiento());
		this.setPrimerApellido(inscripcion.getPrimerApellido());
		this.setPrimerNombre(inscripcion.getPrimerNombre());
		this.setSegundoApellido(inscripcion.getSegundoApellido());
		this.setSegundoNombre(inscripcion.getSegundoNombre());
		this.setTelefono(inscripcion.getTelefono());
		this.setDireccion(inscripcion.getDireccion());
		this.setNumeroInscripcion(inscripcion.getNumeroInscripcion());
		this.setFechaInscripcion(inscripcion.getFechaInscripcion());
		this.setToken(inscripcion.getToken());
		this.setEliminada(inscripcion.getEliminada());
		this.setId(inscripcion.getId());

	}

	public InscripcionCupoConcursoAbiertoDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public boolean getEliminada() {
		return eliminada;
	}

	public void setEliminada(boolean eliminada) {
		this.eliminada = eliminada;
	}

	public Date getFechaEliminada() {
		return fechaEliminada;
	}

	public void setFechaEliminada(Date fechaEliminada) {
		this.fechaEliminada = fechaEliminada;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public SexoGeneroDTO getSexoGeneroDTO() {
		return sexoGeneroDTO;
	}

	public void setSexoGeneroDTO(SexoGeneroDTO sexoGeneroDTO) {
		this.sexoGeneroDTO = sexoGeneroDTO;
	}
	
	
	
	
	


}
