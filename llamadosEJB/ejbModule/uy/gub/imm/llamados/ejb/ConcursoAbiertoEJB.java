package uy.gub.imm.llamados.ejb;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import uy.gub.imm.llamados.dao.local.ConcursoAbiertoDAOLocal;
import uy.gub.imm.llamados.dao.local.CupoConcursoAbiertoDAOLocal;
import uy.gub.imm.llamados.dao.local.TipoCupoConcursoAbiertoDAOLocal;
import uy.gub.imm.llamados.dto.ConcursoAbiertoDTO;
import uy.gub.imm.llamados.dto.CupoConcursoAbiertoDTO;
import uy.gub.imm.llamados.entity.ConcursoAbierto;
import uy.gub.imm.llamados.entity.CupoConcursoAbierto;
import uy.gub.imm.llamados.entity.TipoCupoConcursoAbierto;
import uy.gub.imm.llamados.exceptions.ConcursoAbiertoException;


@Stateless
public class ConcursoAbiertoEJB  implements ConcursoAbiertoLocal {
	

	@EJB
	ConcursoAbiertoDAOLocal concursoAbiertoDAO;
	
	@EJB
	CupoConcursoAbiertoDAOLocal CupoConcursoAbiertoDAO;
	
	@EJB
	TipoCupoConcursoAbiertoDAOLocal tipoCupoConcursoAbiertoDAO;
	
	
	@Override
	public void altaConcursoAbierto(ConcursoAbiertoDTO concursoDTO) throws ConcursoAbiertoException {
		
		if(existeConcursoAbiertoCodigo(concursoDTO.getCodigo())){
			throw new ConcursoAbiertoException("Existe un llamado con código "+concursoDTO.getCodigo());
		}
		
		
		
		ConcursoAbierto concurso = new ConcursoAbierto();
		concurso.setCodigo(concursoDTO.getCodigo());
		concurso.setDescripcion(concursoDTO.getDescripcion());
		concurso.setEdadTope(concursoDTO.getEdadTope());
		concurso.setFechaDesde(concursoDTO.getFechaDesde());
		concurso.setFechaHasta(concursoDTO.getFechaHasta());
		
		concursoAbiertoDAO.persist(concurso);
		
		
		for(CupoConcursoAbiertoDTO cupoDTO: concursoDTO.getListaCupoConcursoAbiertoDTO()){
			
			CupoConcursoAbierto cupo = new CupoConcursoAbierto();
			cupo.setCodigo(cupoDTO.getCodigo());
			cupo.setDescripcion(cupoDTO.getDescripcion());
			cupo.setPlazas(cupoDTO.getPlazas());
			
			TipoCupoConcursoAbierto tipoCupo= tipoCupoConcursoAbiertoDAO.findById(cupoDTO.getTipoCupoConcursoAbierto().getId());
			
			cupo.setTipoCupoConcursoAbierto(tipoCupo);
			cupo.setConcursoAbierto(concurso);
			CupoConcursoAbiertoDAO.persist(cupo);
			
		}

		
	}
	
	public boolean existeConcursoAbiertoCodigo(String codigo){
		
		Map<String, Object> params= new HashMap<String, Object>();
		params.put("codigo", codigo);
		if(concursoAbiertoDAO.findObjectByQuery("ConcursoAbierto.findByCodigo", params)!=null)
			return true;
		else
			return false;
	}

	@Override
	public List<ConcursoAbiertoDTO> obtenerConcurosAbiertos() throws ConcursoAbiertoException {
		
		List<ConcursoAbierto> listaConcursosAbiertos = concursoAbiertoDAO.findAll();
		
		List<ConcursoAbiertoDTO> listaConcursosAbiertosDTO= new ArrayList<ConcursoAbiertoDTO>();
		
		for(ConcursoAbierto concursoAbierto: listaConcursosAbiertos){
			listaConcursosAbiertosDTO.add(new ConcursoAbiertoDTO(concursoAbierto));
		}
		
		return listaConcursosAbiertosDTO;
	}

	@Override
	public ConcursoAbiertoDTO obtenerConcuroAbiertoPorCodigo(String codigo) throws ConcursoAbiertoException {

		
		Map<String, Object> params= new HashMap<String,Object>();
		params.put("codigo", codigo);
		ConcursoAbierto concurso= concursoAbiertoDAO.findObjectByQuery("ConcursoAbierto.findByCodigo",params );
		if(concurso!=null)
			return new ConcursoAbiertoDTO(concurso);
		return null;
	}

	@Override
	public void editarConcuroAbiertoPorCodigo(ConcursoAbiertoDTO concursoDTO) throws ConcursoAbiertoException {
		
		//Chequeos
			//1- Que no exista otra llamado con el mismo codigo

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("codigo", concursoDTO.getCodigo());
		params.put("id", concursoDTO.getId());
		
		ConcursoAbierto concurso=concursoAbiertoDAO.findObjectByQuery("ConcursoAbierto.findByCodigoAndDiffId", params);
		
		if(concurso!=null)
			throw new ConcursoAbiertoException("Existe otro concurso abierto con codigo "+concursoDTO.getCodigo());
			//Fin chequeo 1-
			
			//2- Chequeo que todos los cupos tengan distinto codigo
		List<String> codigos = new ArrayList<String>();
		for(CupoConcursoAbiertoDTO c: concursoDTO.getListaCupoConcursoAbiertoDTO())
			codigos.add(c.getCodigo());
		Set<String> sc = new HashSet<>();
		sc.addAll(codigos);
		if(codigos.size()>sc.size())
			throw new ConcursoAbiertoException("Existe más de un cupo con el mismo codigo ");
			//Fin 2-
			
		
		
			//3- Chequeo que no existan cupo con igual codigo existentes
		for(CupoConcursoAbiertoDTO cupoDTO: concursoDTO.getListaCupoConcursoAbiertoDTO()){
			params = new HashMap<String, Object>();
			params.put("codigo", cupoDTO.getCodigo());
			params.put("id", cupoDTO.getId());
			CupoConcursoAbierto cupo= CupoConcursoAbiertoDAO.findObjectByQuery("CupoConcursoAbierto.findByCodigoAndDiffId", params);
			if (cupo!=null)
				throw new ConcursoAbiertoException("Existe otro cupo con codigo "+cupoDTO.getCodigo());
		}
		//Fin 3-
		
		
		
		
		concurso = concursoAbiertoDAO.findById(concursoDTO.getId());
		concurso.setCodigo(concursoDTO.getCodigo());
		concurso.setDescripcion(concursoDTO.getDescripcion());
		concurso.setEdadTope(concursoDTO.getEdadTope());
		concurso.setFechaDesde(concursoDTO.getFechaDesde());
		concurso.setFechaHasta(concursoDTO.getFechaHasta());
		
		CupoConcursoAbierto cupo;
		for(CupoConcursoAbiertoDTO cupoDTO : concursoDTO.getListaCupoConcursoAbiertoDTO() ){
			if(cupoDTO.getId()!=0)
				cupo=CupoConcursoAbiertoDAO.findById(cupoDTO.getId());
			else {
				cupo = new CupoConcursoAbierto();
				TipoCupoConcursoAbierto tipoCupo= tipoCupoConcursoAbiertoDAO.findById(cupoDTO.getTipoCupoConcursoAbierto().getId());
				cupo.setConcursoAbierto(concurso);
				cupo.setTipoCupoConcursoAbierto(tipoCupo);
				
			}
			cupo.setCodigo(cupoDTO.getCodigo());
			cupo.setDescripcion(cupoDTO.getDescripcion());
			cupo.setPlazas(cupoDTO.getPlazas());
			CupoConcursoAbiertoDAO.persist(cupo);


		}
		concursoAbiertoDAO.persist(concurso);
		
	}

	@Override
	public List<ConcursoAbiertoDTO> obtenerConcurosAbiertosVigentes() throws ConcursoAbiertoException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("hoy", new Date());
		List<ConcursoAbierto> listaConcursosAbiertos = concursoAbiertoDAO.findListByQuery("ConcursoAbierto.findConcursoAbiertosVigentes", params);

		List<ConcursoAbiertoDTO> listaConcursosAbiertosDTO= new ArrayList<ConcursoAbiertoDTO>();
		
		for(ConcursoAbierto concursoAbierto: listaConcursosAbiertos){
			listaConcursosAbiertosDTO.add(new ConcursoAbiertoDTO(concursoAbierto));
		}
		
		return listaConcursosAbiertosDTO;
	}

}
