package uy.gub.imm.llamados.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="TIPO_CUPO_CONC_AB")

@NamedQueries({
	@NamedQuery(name="TipoCupoConcursoAbierto.findByCupoConcursoAbierto", 
				query="select tc from TipoCupoConcursoAbierto tc, CupoConcursoAbierto c where c.id=:id and c.tipoCupoConcursoAbierto.id=tc.id"),
})

public class TipoCupoConcursoAbierto {
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TIPO_CUPO_CONC_AB_SEQ")
	@SequenceGenerator(name = "TIPO_CUPO_CONC_AB_SEQ", sequenceName = "TIPO_CUPO_CONC_AB_SEQ", allocationSize = 1)
	private long id;
	
	@Column(name="CODIGO")
	private String codigo;

	@Column(name="DESCRIPCION")
	private String descripcion;

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
	
	
	
	
	

}
