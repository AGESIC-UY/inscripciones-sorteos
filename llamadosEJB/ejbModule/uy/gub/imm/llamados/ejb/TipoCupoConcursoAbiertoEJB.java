package uy.gub.imm.llamados.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import uy.gub.imm.llamados.dao.local.TipoCupoConcursoAbiertoDAOLocal;
import uy.gub.imm.llamados.dto.TipoCupoConcursoAbiertoDTO;
import uy.gub.imm.llamados.entity.TipoCupoConcursoAbierto;

@Stateless
public class TipoCupoConcursoAbiertoEJB implements TipoCupoConcursoAbiertoLocal {

	
	@EJB
	TipoCupoConcursoAbiertoDAOLocal tipoCupoConcursoAbiertoDAO;
	
	@Override
	public List<TipoCupoConcursoAbiertoDTO> obtenerTiposDeCupo() {

		List<TipoCupoConcursoAbierto> listaTipoCupoConcursoAbierto = tipoCupoConcursoAbiertoDAO.findAll();
		List<TipoCupoConcursoAbiertoDTO> listaTipoCupoConcursoAbiertoDTO= new ArrayList<TipoCupoConcursoAbiertoDTO>();
		
		for(TipoCupoConcursoAbierto tipoCupoConcursoAbierto :listaTipoCupoConcursoAbierto ){
			listaTipoCupoConcursoAbiertoDTO.add(new TipoCupoConcursoAbiertoDTO(tipoCupoConcursoAbierto));
		}
		return listaTipoCupoConcursoAbiertoDTO;
	}

}
