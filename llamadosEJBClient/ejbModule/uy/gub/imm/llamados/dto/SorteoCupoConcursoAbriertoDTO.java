package uy.gub.imm.llamados.dto;

import java.util.Date;
import uy.gub.imm.llamados.entity.SorteoCupoConcursoAbrierto;


public class SorteoCupoConcursoAbriertoDTO {
	
	private Long id;
	private Integer semilla;
	private Date fechaSorteo;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getSemilla() {
		return semilla;
	}
	public void setSemilla(Integer semilla) {
		this.semilla = semilla;
	}
	public Date getFechaSorteo() {
		return fechaSorteo;
	}
	public void setFechaSorteo(Date fechaSorteo) {
		this.fechaSorteo = fechaSorteo;
	}

	public SorteoCupoConcursoAbriertoDTO() {
		super();
	}
	
	public SorteoCupoConcursoAbriertoDTO(SorteoCupoConcursoAbrierto sorteo) {
		
		this.id=sorteo.getId();
		this.fechaSorteo=sorteo.getFechaSorteo();
		this.semilla=sorteo.getSemilla();
	}
	

}
