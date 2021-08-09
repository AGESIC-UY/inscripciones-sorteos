package uy.gub.imm.llamados.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="CONCURSOS_ABIERTOS")
@NamedQueries({
	@NamedQuery(name="ConcursoAbierto.findByCodigo", query="select c from ConcursoAbierto c where c.codigo  like :codigo"),
	/*Se utiliza para verificar que al actualizar el concurso no exista otro concurso distinto con el mismo codigo*/
	@NamedQuery(name="ConcursoAbierto.findByCodigoAndDiffId", query="select c from ConcursoAbierto c where (c.codigo  like :codigo) and (c.id <> :id)"),
	@NamedQuery(name="ConcursoAbierto.findConcursoAbiertosVigentes", query="select c from ConcursoAbierto c where c.fechaDesde<= :hoy and c.fechaHasta>= :hoy"),
	@NamedQuery(name="ConcursoAbierto.findConcursoAbiertosByCodigoCupo", query="select l from ConcursoAbierto l, CupoConcursoAbierto c where c.concursoAbierto.id=l.id and c.codigo=:codigo"),
	@NamedQuery(name="ConcursoAbierto.findConcursoAbiertosByIdCupo", query="select c.concursoAbierto from CupoConcursoAbierto c where c.id=:idCupo")



})
public class ConcursoAbierto {
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONCURSOS_ABIERTOS_SEQ")
	@SequenceGenerator(name = "CONCURSOS_ABIERTOS_SEQ", sequenceName = "CONCURSOS_ABIERTOS_SEQ", allocationSize = 1)
	private Long id;
	
	@Column(name="CODIGO")
	private String codigo;

	@Column(name="DESCRIPCION")
	private String descripcion;
	
	@Column(name="EDAD_TOPE")
	private Integer edadTope;

	@Column(name="FECHA_DESDE")
	@Temporal(TemporalType.DATE)
	private Date fechaDesde;

	@Column(name="FECHA_HASTA")
	@Temporal(TemporalType.DATE)
	private Date fechaHasta;
	
	
	@OneToMany(mappedBy = "concursoAbierto",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<CupoConcursoAbierto> listaCupos = new ArrayList<CupoConcursoAbierto>();

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

	public List<CupoConcursoAbierto> getListaCupos() {
		return listaCupos;
	}

	public void setListaCupos(List<CupoConcursoAbierto> listaCupos) {
		this.listaCupos = listaCupos;
	}

	public Integer getEdadTope() {
		return edadTope;
	}

	public void setEdadTope(Integer edadTope) {
		this.edadTope = edadTope;
	}
	



}
