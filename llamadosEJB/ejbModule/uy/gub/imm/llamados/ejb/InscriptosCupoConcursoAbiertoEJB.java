package uy.gub.imm.llamados.ejb;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.jboss.logging.Logger;

import uy.gub.imm.llamados.dao.ConcursoAbiertoDAO;
import uy.gub.imm.llamados.dao.local.ConcursoAbiertoDAOLocal;
import uy.gub.imm.llamados.dao.local.CupoConcursoAbiertoDAOLocal;
import uy.gub.imm.llamados.dao.local.InscripcionCupoConcursoAbiertoDAOLocal;
import uy.gub.imm.llamados.dao.local.SexoGeneroDAOLocal;
import uy.gub.imm.llamados.dto.ConcursoAbiertoDTO;
import uy.gub.imm.llamados.dto.CupoConcursoAbiertoDTO;
import uy.gub.imm.llamados.dto.InscripcionCupoConcursoAbiertoDTO;
import uy.gub.imm.llamados.dto.SexoGeneroDTO;
import uy.gub.imm.llamados.entity.ConcursoAbierto;
import uy.gub.imm.llamados.entity.CupoConcursoAbierto;
import uy.gub.imm.llamados.entity.InscripcionCupoConcursoAbierto;
import uy.gub.imm.llamados.entity.SexoGenero;
import uy.gub.imm.llamados.exceptions.ConcursoAbiertoException;


@Stateless
public class InscriptosCupoConcursoAbiertoEJB implements InscriptosCupoConcursoAbiertoLocal {
	
	private static Logger log= Logger.getLogger(InscriptosCupoConcursoAbiertoEJB.class);
	
	
	@EJB
	InscripcionCupoConcursoAbiertoDAOLocal inscripcionCupoConcursoAbiertoDAO;
	
	@EJB
	CupoConcursoAbiertoDAOLocal cupoConcursoAbiertoDAO; 
	
	@EJB
	ConcursoAbiertoDAOLocal concursoAbiertoDAO;
	
	@EJB
	SexoGeneroDAOLocal sexoGeneroDAOLocal;
 
	@Override
	public List<InscripcionCupoConcursoAbiertoDTO> obtenerInscriptosPorIdCupo(long idCupo) {
		
		
		Map<String, Object> params= new HashMap<String,Object>();
		params.put("idCupo", idCupo);
		List<InscripcionCupoConcursoAbierto> listaInscriptos = inscripcionCupoConcursoAbiertoDAO.findListByQuery("InscripcionCupoConcursoAbierto.findInscriptosByIdCupo", params);
		
		List<InscripcionCupoConcursoAbiertoDTO> listaInscriptosDTO = new ArrayList<InscripcionCupoConcursoAbiertoDTO>(); 
		
		for(InscripcionCupoConcursoAbierto inscripto:listaInscriptos){
			listaInscriptosDTO.add(new InscripcionCupoConcursoAbiertoDTO(inscripto));
		}
		return listaInscriptosDTO;
	}

	@Override
	public InscripcionCupoConcursoAbiertoDTO inscribirCupoConcursoAbierto(InscripcionCupoConcursoAbiertoDTO inscripcionDTO) throws ConcursoAbiertoException {
		
		
		//Verifico si ya esta inscripto en el cupo
		Map<String, Object> params= new HashMap<String,Object>();
		params.put("idCupo", inscripcionDTO.getCupoConcursoAbiertoDTO().getId());
		ConcursoAbierto concursoAbierto=concursoAbiertoDAO.findObjectByQuery("ConcursoAbierto.findConcursoAbiertosByIdCupo", params);
		
		if(!fechaActualEnRangoInscripcion(new Date(),concursoAbierto.getFechaDesde(), concursoAbierto.getFechaHasta()))
			throw new ConcursoAbiertoException("La fecha actual no se encuentra dentro del periodo de inscripción");
		
		List<CupoConcursoAbierto>listaCupos=concursoAbierto.getListaCupos();
		
		for(CupoConcursoAbierto cupo:listaCupos){
			Map<String, Object> params2= new HashMap<String,Object>();
			params2.put("idCupo", cupo.getId());
			params2.put("ci", inscripcionDTO.getCi());
			List<InscripcionCupoConcursoAbierto> listaInscripto=inscripcionCupoConcursoAbiertoDAO.findListByQuery("InscripcionCupoConcursoAbierto.findInscriptosNoEliminadaByIdCupoAndCi", params2);
			if(listaInscripto!=null && listaInscripto.size()>0){
				log.info("La ci "+inscripcionDTO.getCi()+ " se encuentra inscripta al concurso "+concursoAbierto.getCodigo()+" para el cupo "+cupo.getCodigo()+".");
				throw new ConcursoAbiertoException("La ci "+inscripcionDTO.getCi()+ " se encuentra inscripta al concurso "+concursoAbierto.getCodigo()+" para el cupo "+cupo.getCodigo()+".");
			}
		}

		CupoConcursoAbierto cupo = cupoConcursoAbiertoDAO.findById(inscripcionDTO.getCupoConcursoAbiertoDTO().getId());
		SexoGenero sexoGenero= sexoGeneroDAOLocal.findById(inscripcionDTO.getSexoGeneroDTO().getId());
		
		InscripcionCupoConcursoAbierto inscripcion = new InscripcionCupoConcursoAbierto();
		inscripcion.setCupoConcursoAbierto(cupo);
		inscripcion.setSexoGenero(sexoGenero);
		inscripcion.setBarra(inscripcionDTO.getBarra());
		inscripcion.setCelular(inscripcionDTO.getCelular());
		inscripcion.setCi(inscripcionDTO.getCi());
		inscripcion.setCorreoElectronico(inscripcionDTO.getCorreoElectronico());
		inscripcion.setFechaNacimiento(inscripcionDTO.getFechaNacimiento());
		inscripcion.setPrimerApellido(inscripcionDTO.getPrimerApellido());
		inscripcion.setPrimerNombre(inscripcionDTO.getPrimerNombre());
		inscripcion.setSegundoApellido(inscripcionDTO.getSegundoApellido());
		inscripcion.setSegundoNombre(inscripcionDTO.getSegundoNombre());
		inscripcion.setTelefono(inscripcionDTO.getTelefono());
		inscripcion.setDireccion(inscripcionDTO.getDireccion());
		inscripcion.setNumeroInscripcion(obternerMaximoNumeroInscriocion(inscripcionDTO.getCupoConcursoAbiertoDTO().getId()));
		inscripcion.setFechaInscripcion(new Date());
		inscripcion.setToken(generarStringRandom(30));
		inscripcion.setEliminada(false);
		inscripcionCupoConcursoAbiertoDAO.persist(inscripcion);
		inscripcionDTO.setNumeroInscripcion(inscripcion.getNumeroInscripcion());
		inscripcionDTO.setToken(inscripcion.getToken());
		return inscripcionDTO;
	}
	
	public InscripcionCupoConcursoAbiertoDTO obtenerInscripcionCupoConcuroAbiertoPorToken(String token) throws ConcursoAbiertoException {
		
		Map<String, Object> params= new HashMap<String,Object>();
		params.put("token", token);
		InscripcionCupoConcursoAbierto inscripcion= inscripcionCupoConcursoAbiertoDAO.findObjectByQuery("InscripcionCupoConcursoAbierto.findInscripcionByToken", params);

		if(inscripcion==null)
			throw new ConcursoAbiertoException("No se encontró ninguna inscripción con la información ingresada.");
			
	
		ConcursoAbierto concurso = inscripcion.getCupoConcursoAbierto().getConcursoAbierto();
		if(!fechaActualEnRangoInscripcion(new Date(), concurso.getFechaDesde(), concurso.getFechaHasta()))
			throw new ConcursoAbiertoException("La fecha actual no se encuentra dentro del periodo de inscripción");
		
		InscripcionCupoConcursoAbiertoDTO inscripcionDTO= new InscripcionCupoConcursoAbiertoDTO();
		inscripcionDTO.setBarra(inscripcion.getBarra());
		inscripcionDTO.setCelular(inscripcion.getCelular());
		inscripcionDTO.setCi(inscripcion.getCi());
		inscripcionDTO.setCorreoElectronico(inscripcion.getCorreoElectronico());
		inscripcionDTO.setFechaNacimiento(inscripcion.getFechaNacimiento());
		inscripcionDTO.setPrimerApellido(inscripcion.getPrimerApellido());
		inscripcionDTO.setPrimerNombre(inscripcion.getPrimerNombre());
		inscripcionDTO.setSegundoApellido(inscripcion.getSegundoApellido());
		inscripcionDTO.setSegundoNombre(inscripcion.getSegundoNombre());
		inscripcionDTO.setTelefono(inscripcion.getTelefono());
		inscripcionDTO.setDireccion(inscripcion.getDireccion());
		inscripcionDTO.setNumeroInscripcion(inscripcion.getNumeroInscripcion());
		inscripcionDTO.setFechaInscripcion(inscripcion.getFechaInscripcion());
		inscripcionDTO.setToken(inscripcion.getToken());
		inscripcionDTO.setEliminada(inscripcion.getEliminada());
		CupoConcursoAbiertoDTO cupoDTO= new CupoConcursoAbiertoDTO(inscripcion.getCupoConcursoAbierto());
		cupoDTO.setConcurso(new ConcursoAbiertoDTO(inscripcion.getCupoConcursoAbierto().getConcursoAbierto()));
		inscripcionDTO.setCupoConcursoAbiertoDTO(cupoDTO);
		inscripcionDTO.setSexoGeneroDTO(new SexoGeneroDTO(inscripcion.getSexoGenero().getId(), inscripcion.getSexoGenero().getDescripion()));
		return inscripcionDTO;
		
		
	}

	public void bajaInscripcionCupoConcuroAbiertoPorToken(String token) throws ConcursoAbiertoException {

		Map<String, Object> params= new HashMap<String,Object>();
		params.put("token", token);
		InscripcionCupoConcursoAbierto inscripcion= inscripcionCupoConcursoAbiertoDAO.findObjectByQuery("InscripcionCupoConcursoAbierto.findInscripcionByToken", params);
		
		if(inscripcion==null)
			throw new ConcursoAbiertoException("No se encontró ninguna inscripción con la información ingresada.");
			
	
		ConcursoAbierto concurso = inscripcion.getCupoConcursoAbierto().getConcursoAbierto();
		if(!fechaActualEnRangoInscripcion(new Date(), concurso.getFechaDesde(), concurso.getFechaHasta()))
			throw new ConcursoAbiertoException("La fecha actual no se encuentra dentro del periodo de inscripción");
		
		inscripcion.setEliminada(true);
		inscripcion.setFechaEliminada(new Date());
		inscripcionCupoConcursoAbiertoDAO.persist(inscripcion);
		
	}

	private Integer obternerMaximoNumeroInscriocion(long idCupo){
		
		Map<String, Object> params= new HashMap<String,Object>();
		params.put("idCupo", idCupo);
		List<InscripcionCupoConcursoAbierto> listaInscriptos = inscripcionCupoConcursoAbiertoDAO.findListByQuery("InscripcionCupoConcursoAbierto.findMaxNumInscripcionByIdCupo", params);
		if (listaInscriptos==null || listaInscriptos.isEmpty() )
			return 1;
		else return listaInscriptos.get(0).getNumeroInscripcion()+1;
	}
	
	private String generarStringRandom(int lenght){
		StringBuffer buffer = new StringBuffer();
		Random random = new Random();
		char[] chars = new char[56];
		int i = 0;

		/**
		 * Elimino del random los caracteres O y I mayúsculas, l minuscula y el
		 * 0 para evitar confusiones con el usuario. Noutiliza caracteres
		 * reservados como ñ u acentos.
		 **/
		for (int n = '1'; n <= '9'; chars[i++] = (char) n, n++)
			;
		for (int n = 'a'; n <= 'k'; chars[i++] = (char) n, n++)
			;
		for (int n = 'o'; n <= 'z'; chars[i++] = (char) n, n++)
			;
		for (int n = 'A'; n <= 'H'; chars[i++] = (char) n, n++)
			;
		for (int n = 'J'; n <= 'N'; chars[i++] = (char) n, n++)
			;
		for (int n = 'P'; n <= 'Z'; chars[i++] = (char) n, n++)
			;
		for (int j = 0; j < lenght; j++) {
			buffer.append(chars[random.nextInt(chars.length)]);
		}
		return buffer.toString();
	}

	private boolean fechaActualEnRangoInscripcion(Date fechaActual,Date fechaInicio, Date fechaFin){
		
		Calendar cActual = Calendar.getInstance();
		cActual.setTime(fechaActual);
		Calendar cInicio = Calendar.getInstance();
		cInicio.setTime(fechaInicio);
		Calendar cFin= Calendar.getInstance();
		cFin.setTime(fechaFin);
		return (cActual.after(cInicio) && cActual.before(cFin));
		
	}

	@Override
	public InscripcionCupoConcursoAbiertoDTO obtenerInscripcionPorIdInscripcion(long idInscripcion)
			throws ConcursoAbiertoException {
		log.info("");
		InscripcionCupoConcursoAbierto inscripcion= inscripcionCupoConcursoAbiertoDAO.findById(idInscripcion);

		InscripcionCupoConcursoAbiertoDTO inscripcionDTO= new InscripcionCupoConcursoAbiertoDTO(inscripcion);
		
		CupoConcursoAbiertoDTO cupoDTO = new CupoConcursoAbiertoDTO(inscripcion.getCupoConcursoAbierto());
		ConcursoAbiertoDTO concursoDTO = new ConcursoAbiertoDTO(inscripcion.getCupoConcursoAbierto().getConcursoAbierto());
		cupoDTO.setConcurso(concursoDTO);
		
		inscripcionDTO.setCupoConcursoAbiertoDTO(cupoDTO);
		
		SexoGeneroDTO sexoDTO= new SexoGeneroDTO(inscripcion.getSexoGenero().getId(), inscripcion.getSexoGenero().getDescripion());
		inscripcionDTO.setSexoGeneroDTO(sexoDTO);
		
		return inscripcionDTO;
	}


}
