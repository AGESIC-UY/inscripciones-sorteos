package uy.gub.imm.llamados.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="CUPO_CONCURSOS_ABIERTOS")
@NamedQueries({
	@NamedQuery(name="CupoConcursoAbierto.findByCodigoConcurso", query="select c from CupoConcursoAbierto c where c.concursoAbierto.codigo like :codigoConcurso"),
	/*Se utiliza para verificar que al actualizar el codigo no exista en otro cupo distinto*/
	@NamedQuery(name="CupoConcursoAbierto.findByCodigoAndDiffId", query="select c from CupoConcursoAbierto c where (c.codigo like :codigo) and (c.id <> :id)"),
	@NamedQuery(name="CupoConcursoAbierto.findByCodigoCupo", query="select c from CupoConcursoAbierto c where c.codigo like :codigoCupo"),


})
public class CupoConcursoAbierto {
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUPO_CONCURSOS_ABIERTOS_SEQ")
	@SequenceGenerator(name = "CUPO_CONCURSOS_ABIERTOS_SEQ", sequenceName = "CUPO_CONCURSOS_ABIERTOS_SEQ", allocationSize = 1)
	private long id;
	
	@Column(name="CODIGO")
	private String codigo;

	@Column(name="DESCRIPCION")
	private String descripcion;
	
	@Column(name="PLAZAS")
	private Integer plazas;
	
	@ManyToOne
	@JoinColumn(name = "ID_CONCURSOS_ABIERTOS")
	private ConcursoAbierto concursoAbierto;
	
	@ManyToOne
	@JoinColumn(name = "ID_TIPO_CUPO_CONC_AB")
	private TipoCupoConcursoAbierto tipoCupoConcursoAbierto;
	
	@OneToMany(mappedBy = "cupoConcursoAbierto",fetch=FetchType.LAZY)
	private List<InscripcionCupoConcursoAbierto> listaInscriptos= new ArrayList<InscripcionCupoConcursoAbierto>();
	
	@OneToOne(mappedBy = "CupoConcursoAbierto")
	private SorteoCupoConcursoAbrierto sorteoCupoConcursoAbrierto;


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

	public ConcursoAbierto getConcursoAbierto() {
		return concursoAbierto;
	}

	public void setConcursoAbierto(ConcursoAbierto concursoAbierto) {
		this.concursoAbierto = concursoAbierto;
	}

	public TipoCupoConcursoAbierto getTipoCupoConcursoAbierto() {
		return tipoCupoConcursoAbierto;
	}

	public void setTipoCupoConcursoAbierto(TipoCupoConcursoAbierto tipoCupoConcursoAbierto) {
		this.tipoCupoConcursoAbierto = tipoCupoConcursoAbierto;
	}

	public List<InscripcionCupoConcursoAbierto> getListaInscriptos() {
		return listaInscriptos;
	}

	public void setListaInscriptos(List<InscripcionCupoConcursoAbierto> listaInscriptos) {
		this.listaInscriptos = listaInscriptos;
	}

	public SorteoCupoConcursoAbrierto getSorteoCupoConcursoAbrierto() {
		return sorteoCupoConcursoAbrierto;
	}

	public void setSorteoCupoConcursoAbrierto(SorteoCupoConcursoAbrierto sorteoCupoConcursoAbrierto) {
		this.sorteoCupoConcursoAbrierto = sorteoCupoConcursoAbrierto;
	}

}
