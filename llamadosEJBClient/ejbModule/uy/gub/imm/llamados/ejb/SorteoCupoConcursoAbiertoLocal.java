package uy.gub.imm.llamados.ejb;

import javax.ejb.Local;

import uy.gub.imm.llamados.dto.CupoConcursoAbiertoDTO;
import uy.gub.imm.llamados.exceptions.ConcursoAbiertoException;

@Local
public interface SorteoCupoConcursoAbiertoLocal {
	
	public void realizarSorteo(CupoConcursoAbiertoDTO cupo) throws ConcursoAbiertoException;

}
