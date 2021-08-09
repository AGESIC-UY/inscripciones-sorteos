package uy.gub.imm.llamados.ejb;

import java.util.List;

import javax.ejb.Local;

import uy.gub.imm.llamados.dto.CupoConcursoAbiertoDTO;
import uy.gub.imm.llamados.exceptions.ConcursoAbiertoException;

@Local
public interface CupoConcursoAbiertoLocal {
	
	public List<CupoConcursoAbiertoDTO> obtenerCuposPorCodigoConcurso(String codigoConcurso) throws ConcursoAbiertoException;
	
	public List<CupoConcursoAbiertoDTO> obtenerCuposVigentesPorCodigoConcurso(String codigoConcurso) throws ConcursoAbiertoException;
	
	public CupoConcursoAbiertoDTO obtenerCupoPorCodigoCupo(String codigoCupo) throws ConcursoAbiertoException;
	
	public CupoConcursoAbiertoDTO obtenerCupoVigentePorCodigoCupo(String codigoCupo) throws ConcursoAbiertoException;

}
