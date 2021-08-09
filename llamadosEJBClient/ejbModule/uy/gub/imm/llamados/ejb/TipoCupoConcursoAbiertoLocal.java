package uy.gub.imm.llamados.ejb;

import java.util.List;

import javax.ejb.Local;

import uy.gub.imm.llamados.dto.TipoCupoConcursoAbiertoDTO;

@Local
public interface TipoCupoConcursoAbiertoLocal {
	
	public List<TipoCupoConcursoAbiertoDTO> obtenerTiposDeCupo();

}
