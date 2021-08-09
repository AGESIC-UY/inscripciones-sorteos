package uy.gub.imm.llamados.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="SEXO_GENERO_CONC_AB")
public class SexoGenero {
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEXO_GENERO_CONC_AB_SEQ")
	@SequenceGenerator(name = "SEXO_GENERO_CONC_AB_SEQ", sequenceName = "SEXO_GENERO_CONC_AB_SEQ", allocationSize = 1)
	private Long id;
	
	@Column(name="DESCRIPCION")
	private String descripion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripion() {
		return descripion;
	}

	public void setDescripion(String descripion) {
		this.descripion = descripion;
	}
	
	


}
