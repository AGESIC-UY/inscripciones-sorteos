package uy.gub.imm.llamados.dao;


import javax.ejb.Stateless;

import uy.gub.imm.llamados.dao.local.CupoConcursoAbiertoDAOLocal;
import uy.gub.imm.llamados.entity.CupoConcursoAbierto;

@Stateless
public class CupoConcursoAbiertoDAO extends BaseDAO<CupoConcursoAbierto> implements CupoConcursoAbiertoDAOLocal {
	
	public CupoConcursoAbiertoDAO() {
		super(CupoConcursoAbierto.class);
	}



}
