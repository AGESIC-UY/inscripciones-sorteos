package uy.gub.imm.llamados.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="INSC_CUPO_CONC_ABIERTOS")
@NamedQueries({
	@NamedQuery(name="InscripcionCupoConcursoAbierto.findInscriptosByIdCupo", query="select i from InscripcionCupoConcursoAbierto i where i.cupoConcursoAbierto.id=:idCupo and i.eliminada=false"),
	@NamedQuery(name="InscripcionCupoConcursoAbierto.findInscriptosNoEliminadaByIdCupoAndCi", query="select i from InscripcionCupoConcursoAbierto i where i.cupoConcursoAbierto.id=:idCupo and i.ci=:ci and i.eliminada=false"),
	@NamedQuery(name="InscripcionCupoConcursoAbierto.findMaxNumInscripcionByIdCupo", query="select i from InscripcionCupoConcursoAbierto i where i.numeroInscripcion = (select max(i2.numeroInscripcion) from InscripcionCupoConcursoAbierto i2 where i2.cupoConcursoAbierto.id=:idCupo ) and  i.cupoConcursoAbierto.id=:idCupo"),
	@NamedQuery(name="InscripcionCupoConcursoAbierto.findInscripcionByToken", query="select i from InscripcionCupoConcursoAbierto i where i.token=:token and i.eliminada=false"),
})
public class InscripcionCupoConcursoAbierto {
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INSC_CUPO_CONC_AB_SEQ")
	@SequenceGenerator(name = "INSC_CUPO_CONC_AB_SEQ", sequenceName = "INSC_CUPO_CONC_AB_SEQ", allocationSize = 1)
	private Long id;
	
	@Column(name="CI")
	private Integer ci;
	
	@Column(name="CI_BARRA")
	private Integer barra;
	
	@Column(name="PRIMER_NOMBRE")
	private String primerNombre;

	@Column(name="SEGUNDO_NOMBRE")
	private String segundoNombre;

	@Column(name="PRIMER_APELLIDO")
	private String primerApellido;
	
	@Column(name="SEGUNDO_APELLIDO")
	private String segundoApellido;
	
	@Column(name="FECHA_NACIMIENTO")
	private Date fechaNacimiento;
	
	@Column(name="TELEFONO_CONTACTO")
	private String telefono;
	
	@Column(name="TELEFONO_CELULAR")
	private String celular;
	
	@Column(name="CORREO_ELECTRONICO")
	private String correoElectronico;
	
	@Column(name="DIRECCION")
	private String direccion;
	
	@ManyToOne
	@JoinColumn(name = "ID_CUPO_CONC_AB")
	private CupoConcursoAbierto cupoConcursoAbierto;
	
	@ManyToOne
	@JoinColumn(name = "ID_SEXO_GENERO")
	private SexoGenero sexoGenero;
	
	@Column(name="FECHA_INSCRIPCION")
	private Date fechaInscripcion;
	
	@Column(name="NUMERO_INSCRIOPCION")
	private Integer numeroInscripcion;
	
	@Column(name="TOKEN")
	private String token;
	
	@Column(name="ELIMINADA")
	private boolean eliminada;
	
	@Column(name="FECHA_ELIMINADA")
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

	public CupoConcursoAbierto getCupoConcursoAbierto() {
		return cupoConcursoAbierto;
	}

	public void setCupoConcursoAbierto(CupoConcursoAbierto cupoConcursoAbierto) {
		this.cupoConcursoAbierto = cupoConcursoAbierto;
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

	public SexoGenero getSexoGenero() {
		return sexoGenero;
	}

	public void setSexoGenero(SexoGenero sexoGenero) {
		this.sexoGenero = sexoGenero;
	}
	
	

}
