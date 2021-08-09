package uy.gub.imm.llamados.ejb;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.net.ssl.HttpsURLConnection;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import uy.gub.imm.llamados.dao.local.ConcursoAbiertoDAOLocal;
import uy.gub.imm.llamados.dao.local.CupoConcursoAbiertoDAOLocal;
import uy.gub.imm.llamados.dao.local.InscripcionCupoConcursoAbiertoDAOLocal;
import uy.gub.imm.llamados.dao.local.PosicionSorteoInscriptoDAOLocal;
import uy.gub.imm.llamados.dao.local.SexoGeneroDAOLocal;
import uy.gub.imm.llamados.dao.local.TipoCupoConcursoAbiertoDAOLocal;
import uy.gub.imm.llamados.dto.ConcursoAbiertoDTO;
import uy.gub.imm.llamados.dto.CupoConcursoAbiertoDTO;
import uy.gub.imm.llamados.dto.PosicionSorteoInscriptoDTO;
import uy.gub.imm.llamados.dto.SorteoCupoConcursoAbriertoDTO;
import uy.gub.imm.llamados.dto.TipoCupoConcursoAbiertoDTO;
import uy.gub.imm.llamados.entity.ConcursoAbierto;
import uy.gub.imm.llamados.entity.CupoConcursoAbierto;
import uy.gub.imm.llamados.entity.InscripcionCupoConcursoAbierto;
import uy.gub.imm.llamados.entity.PosicionSorteoInscripto;
import uy.gub.imm.llamados.entity.SexoGenero;
import uy.gub.imm.llamados.entity.TipoCupoConcursoAbierto;
import uy.gub.imm.llamados.exceptions.ConcursoAbiertoException;

@Stateless
public class CupoConcursoAbiertoEJB implements CupoConcursoAbiertoLocal {

	 @EJB
	 CupoConcursoAbiertoDAOLocal cupoConcursoAbiertoDAO;
	 
	 @EJB
	 ConcursoAbiertoDAOLocal concursoAbiertoDAO;
	 
	 @EJB
	 InscripcionCupoConcursoAbiertoDAOLocal inscripcionCupoConcursoAbiertoDAO;
	 
	 @EJB
	 TipoCupoConcursoAbiertoDAOLocal tipoCupoConcursoAbiertoDAO;
	 
	 @EJB
	 PosicionSorteoInscriptoDAOLocal posicionSorteoInscriptoDAO;
	
	 @EJB
	 SexoGeneroDAOLocal SexoGeneroDAO;	 
	@Override
	public List<CupoConcursoAbiertoDTO> obtenerCuposPorCodigoConcurso(String codigoConcurso) throws ConcursoAbiertoException {
		
		try {
			return obtenerCuposPorCodigoConcurso(codigoConcurso, false);
		} catch (ConcursoAbiertoException e) {
			throw new ConcursoAbiertoException(e.getMessage());
		}
	}

	@Override
	public List<CupoConcursoAbiertoDTO> obtenerCuposVigentesPorCodigoConcurso(String codigoConcurso)  throws ConcursoAbiertoException {
		try {
			return obtenerCuposPorCodigoConcurso(codigoConcurso, true);
		} catch (ConcursoAbiertoException e) {
			throw new ConcursoAbiertoException(e.getMessage());
		}
	}	

	private List<CupoConcursoAbiertoDTO> obtenerCuposPorCodigoConcurso(String codigoConcurso, boolean vigente) throws ConcursoAbiertoException {
		Map<String, Object> paramsConcurso= new HashMap<String,Object>();
		paramsConcurso.put("codigo", codigoConcurso);
		ConcursoAbierto concurso= concursoAbiertoDAO.findObjectByQuery("ConcursoAbierto.findByCodigo", paramsConcurso);
		
		if(concurso==null)
			throw new ConcursoAbiertoException("No existe concurso abierto con código "+codigoConcurso);
		
		if((!fechaActualEnRangoInscripcion(new Date(), concurso.getFechaDesde(), concurso.getFechaHasta())) && vigente)
			throw new ConcursoAbiertoException("El periodo de inscripción para el concurso "+concurso.getDescripcion()+" ha finalizado");
		
		Map<String, Object> params= new HashMap<String,Object>();
		params.put("codigoConcurso", codigoConcurso);

		List<CupoConcursoAbierto> listaCupoConcursoAbierto=cupoConcursoAbiertoDAO.findListByQuery("CupoConcursoAbierto.findByCodigoConcurso", params);
		
		List<CupoConcursoAbiertoDTO> listaCupoConcursoAbiertoDTO= new ArrayList<CupoConcursoAbiertoDTO>();
		
		for(CupoConcursoAbierto cupo:listaCupoConcursoAbierto){
			
			Map<String, Object> paramsTipoCupo= new HashMap<String,Object>();
			paramsTipoCupo.put("id", cupo.getId());
			TipoCupoConcursoAbierto tipoCupo = tipoCupoConcursoAbiertoDAO.findObjectByQuery("TipoCupoConcursoAbierto.findByCupoConcursoAbierto", paramsTipoCupo);
			
			

			Map<String, Object> paramsInscriptos= new HashMap<String,Object>();
			paramsInscriptos.put("idCupo", cupo.getId());
			List<InscripcionCupoConcursoAbierto> listaInscriptos = inscripcionCupoConcursoAbiertoDAO.findListByQuery("InscripcionCupoConcursoAbierto.findInscriptosByIdCupo", paramsInscriptos);
			CupoConcursoAbiertoDTO c = new CupoConcursoAbiertoDTO(cupo);
			c.setTipoCupoConcursoAbierto(new TipoCupoConcursoAbiertoDTO(tipoCupo));
			c.setCantidadInscriptos(listaInscriptos.size());
			listaCupoConcursoAbiertoDTO.add(c);
			
		}
		
		return listaCupoConcursoAbiertoDTO;

	}

	@Override
	public CupoConcursoAbiertoDTO obtenerCupoPorCodigoCupo(String codigoCupo) throws ConcursoAbiertoException {
		try {
			return obtenerCupoVigentePorCodigoCupo(codigoCupo,false);
		} catch (ConcursoAbiertoException e) {
			throw new ConcursoAbiertoException(e.getMessage());
		}
	}


	@Override
	public CupoConcursoAbiertoDTO obtenerCupoVigentePorCodigoCupo(String codigoCupo) throws ConcursoAbiertoException {

		try {
			return obtenerCupoVigentePorCodigoCupo(codigoCupo,true);
		} catch (ConcursoAbiertoException e) {
			throw new ConcursoAbiertoException(e.getMessage());
		}
	}
	
	private CupoConcursoAbiertoDTO obtenerCupoVigentePorCodigoCupo(String codigoCupo, boolean vigente) throws ConcursoAbiertoException {

		Map<String, Object> params= new HashMap<String,Object>();
		params.put("codigoCupo", codigoCupo);
		CupoConcursoAbierto cupo = cupoConcursoAbiertoDAO.findObjectByQuery("CupoConcursoAbierto.findByCodigoCupo", params);
		
		if(cupo==null)
			throw new ConcursoAbiertoException("No existe el cupo "+codigoCupo);
		if(!fechaActualEnRangoInscripcion(new Date(),cupo.getConcursoAbierto().getFechaDesde(), cupo.getConcursoAbierto().getFechaHasta()) && vigente)
			throw new ConcursoAbiertoException("El periodo de inscripción para el concurso "+cupo.getConcursoAbierto().getDescripcion()+" ha finalizado");

		
		CupoConcursoAbiertoDTO cupoDTO =new CupoConcursoAbiertoDTO(cupo);
		

		cupoDTO.setConcurso(new ConcursoAbiertoDTO(cupo.getConcursoAbierto()));
		

		if(cupo.getSorteoCupoConcursoAbrierto()!=null){
			
			cupoDTO.setSorteo(new SorteoCupoConcursoAbriertoDTO(cupo.getSorteoCupoConcursoAbrierto()));
			
			Map<String, Object> paramsSorteo= new HashMap<String,Object>();
			paramsSorteo.put("idSorteo", cupo.getSorteoCupoConcursoAbrierto().getId());
			List<PosicionSorteoInscripto> listaSorteo = posicionSorteoInscriptoDAO.findListByQuery("PosicionSorteoInscripto.findByIdSorteo", paramsSorteo);
			List<PosicionSorteoInscriptoDTO> listaSorteoDTO= new ArrayList<PosicionSorteoInscriptoDTO>();
			for(PosicionSorteoInscripto posicion: listaSorteo)
				listaSorteoDTO.add(new PosicionSorteoInscriptoDTO(posicion));
			cupoDTO.setListaSorteo(listaSorteoDTO);
		}
		else {
			cupoDTO.setSorteo(null);
			cupoDTO.setListaSorteo(null);
		}

		return cupoDTO;

	}
	
	private Integer obternerMaximoNumeroInscriocion(long idCupo){
		
		Map<String, Object> params= new HashMap<String,Object>();
		params.put("idCupo", idCupo);
		List<InscripcionCupoConcursoAbierto> listaInscriptos = inscripcionCupoConcursoAbiertoDAO.findListByQuery("InscripcionCupoConcursoAbierto.findMaxNumInscripcionByIdCupo", params);
		if (listaInscriptos==null || listaInscriptos.isEmpty() )
			return 1;
		else return listaInscriptos.get(0).getNumeroInscripcion()+1;
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

	
	//Para pruebas, luego borrar
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


}
