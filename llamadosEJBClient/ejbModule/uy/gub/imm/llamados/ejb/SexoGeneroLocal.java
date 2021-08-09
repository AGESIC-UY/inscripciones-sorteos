package uy.gub.imm.llamados.ejb;

import java.util.List;

import javax.ejb.Local;

import uy.gub.imm.llamados.dto.SexoGeneroDTO;

@Local
public interface SexoGeneroLocal {
	
	public List<SexoGeneroDTO> obtenerListaSexoGenero();

}
