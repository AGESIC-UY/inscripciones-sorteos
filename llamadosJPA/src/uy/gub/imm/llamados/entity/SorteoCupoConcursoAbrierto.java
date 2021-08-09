package uy.gub.imm.llamados.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="SORTEO_CUPO_CONC_AB")
@NamedQueries({
})
public class SorteoCupoConcursoAbrierto {
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SORTEO_CUPO_CONC_AB_SEQ")
	@SequenceGenerator(name = "SORTEO_CUPO_CONC_AB_SEQ", sequenceName = "SORTEO_CUPO_CONC_AB_SEQ", allocationSize = 1)
	private Long id;

	@Column(name="SEMILLA")
	private int semilla;
	
	@Column(name="FECHA_SORTEO")
	private Date fechaSorteo;
	
	@OneToOne
	@JoinColumn(name = "ID_CUPO_CONC_AB")
	private CupoConcursoAbierto CupoConcursoAbierto;
	
	@OneToMany(mappedBy = "sorteoCupoConcursoAbrierto",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<PosicionSorteo> listaPosicionSorteo = new ArrayList<PosicionSorteo>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getSemilla() {
		return semilla;
	}

	public void setSemilla(int semilla) {
		this.semilla = semilla;
	}

	public Date getFechaSorteo() {
		return fechaSorteo;
	}

	public void setFechaSorteo(Date fechaSorteo) {
		this.fechaSorteo = fechaSorteo;
	}

	public CupoConcursoAbierto getCupoConcursoAbierto() {
		return CupoConcursoAbierto;
	}

	public void setCupoConcursoAbierto(CupoConcursoAbierto cupoConcursoAbierto) {
		CupoConcursoAbierto = cupoConcursoAbierto;
	}

	public List<PosicionSorteo> getListaPosicionSorteo() {
		return listaPosicionSorteo;
	}

	public void setListaPosicionSorteo(List<PosicionSorteo> listaPosicionSorteo) {
		this.listaPosicionSorteo = listaPosicionSorteo;
	}
	
	
	

}
