package uy.gub.imm.llamados.ejb;

import java.util.List;

import javax.ejb.Local;

import uy.gub.imm.llamados.dto.InscripcionCupoConcursoAbiertoDTO;
import uy.gub.imm.llamados.exceptions.ConcursoAbiertoException;

@Local
public interface InscriptosCupoConcursoAbiertoLocal {
	
	
	public List<InscripcionCupoConcursoAbiertoDTO> obtenerInscriptosPorIdCupo(long idCupo);
	
	public InscripcionCupoConcursoAbiertoDTO obtenerInscripcionPorIdInscripcion(long idCupo) throws ConcursoAbiertoException;
	
	public InscripcionCupoConcursoAbiertoDTO inscribirCupoConcursoAbierto(InscripcionCupoConcursoAbiertoDTO inscripcion)  throws ConcursoAbiertoException;
	
	public InscripcionCupoConcursoAbiertoDTO obtenerInscripcionCupoConcuroAbiertoPorToken(String token)  throws ConcursoAbiertoException;
	
	public void bajaInscripcionCupoConcuroAbiertoPorToken(String token)  throws ConcursoAbiertoException;

}
