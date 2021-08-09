package uy.gub.imm.llamados.ejb;

import java.util.List;

import javax.ejb.Local;

import uy.gub.imm.llamados.dto.ConcursoAbiertoDTO;
import uy.gub.imm.llamados.exceptions.ConcursoAbiertoException;

@Local
public interface ConcursoAbiertoLocal {
	
	public void altaConcursoAbierto(ConcursoAbiertoDTO concursoDTO) throws ConcursoAbiertoException;
	
	public List<ConcursoAbiertoDTO> obtenerConcurosAbiertos() throws ConcursoAbiertoException;
	
	public List<ConcursoAbiertoDTO> obtenerConcurosAbiertosVigentes() throws ConcursoAbiertoException;

	public ConcursoAbiertoDTO obtenerConcuroAbiertoPorCodigo(String codigo) throws ConcursoAbiertoException;
	
	public void editarConcuroAbiertoPorCodigo(ConcursoAbiertoDTO concursoDTO) throws ConcursoAbiertoException;



}
