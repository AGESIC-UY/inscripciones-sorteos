package uy.gub.imm.llamados.entity;

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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="POSICION_SORTEO")
@NamedQueries({
	@NamedQuery(name="PosicionSorteo.findByCodigoCupo", query="select p from PosicionSorteo p where p.sorteoCupoConcursoAbrierto.CupoConcursoAbierto.codigo= :codigoCupo"),
})
public class PosicionSorteo {
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POSICION_SORTEO_SEQ")
	@SequenceGenerator(name = "POSICION_SORTEO_SEQ", sequenceName = "POSICION_SORTEO_SEQ", allocationSize = 1)
	private Long id;
	
	@Column(name="POSICION")
	private int posicion;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_SORTEO_CUPO_CONC_AB")
	private SorteoCupoConcursoAbrierto sorteoCupoConcursoAbrierto;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_INSC_CUPO_CONC_AB")
	private InscripcionCupoConcursoAbierto inscripcionCupoConcursoAbierto;
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getPosicion() {
		return posicion;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

	public SorteoCupoConcursoAbrierto getSorteoCupoConcursoAbrierto() {
		return sorteoCupoConcursoAbrierto;
	}

	public void setSorteoCupoConcursoAbrierto(SorteoCupoConcursoAbrierto sorteoCupoConcursoAbrierto) {
		this.sorteoCupoConcursoAbrierto = sorteoCupoConcursoAbrierto;
	}

	public InscripcionCupoConcursoAbierto getInscripcionCupoConcursoAbierto() {
		return inscripcionCupoConcursoAbierto;
	}

	public void setInscripcionCupoConcursoAbierto(InscripcionCupoConcursoAbierto inscripcionCupoConcursoAbierto) {
		this.inscripcionCupoConcursoAbierto = inscripcionCupoConcursoAbierto;
	}
	
	
	
	
	
	
	
	

}
