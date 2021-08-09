package uy.gub.imm.llamados.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="V_POSICION_SORTEO")
@NamedQueries({
	@NamedQuery(name="PosicionSorteoInscripto.findByIdSorteo", query="select s from PosicionSorteoInscripto s where s.pk.idSorteo=:idSorteo"),
})
public class PosicionSorteoInscripto {
	
	@EmbeddedId
	private PosicionSorteoInscriptoPK pk;
	
	//POSICION, CI, CI_BARRA, PRIMER_NOMBRE, SEGUNDO_NOMBRE, PRIMER_APELLIDO, SEGUNDO_APELLLIDO, FECHA_NACIMIENTO,TELEFONO,CELULAR,CORREO,FECHA_INSCRIPCION
	@Column(name="POSICION")
	private int posicion;
	
	@Column(name="CI")
	private int ci;
	
	@Column(name="CI_BARRA")
	private int ciBarra;
	
	@Column(name="PRIMER_NOMBRE")
	private String primerNombre;
	
	@Column(name="SEGUNDO_NOMBRE")
	private String segundoNombre;
	
	@Column(name="PRIMER_APELLIDO")
	private String primerApellido;
	
	@Column(name="SEGUNDO_APELLLIDO")
	private String segundoApellido;

	@Column(name="FECHA_NACIMIENTO")
	private Date fechaNacimiento;
	
	@Column(name="TELEFONO")
	private String telefono;
	
	@Column(name="CELULAR")
	private String celular;
	
	@Column(name="CORREO")
	private String correo;
	
	@Column(name="FECHA_INSCRIPCION")
	private String fechaInscripcion;

	public PosicionSorteoInscriptoPK getPk() {
		return pk;
	}

	public void setPk(PosicionSorteoInscriptoPK pk) {
		this.pk = pk;
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
	
	
}
