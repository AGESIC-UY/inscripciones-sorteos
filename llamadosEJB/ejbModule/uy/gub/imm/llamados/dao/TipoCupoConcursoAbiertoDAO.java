package uy.gub.imm.llamados.dao;

import javax.ejb.Stateless;

import uy.gub.imm.llamados.dao.local.TipoCupoConcursoAbiertoDAOLocal;
import uy.gub.imm.llamados.entity.TipoCupoConcursoAbierto;

@Stateless
public class TipoCupoConcursoAbiertoDAO extends BaseDAO<TipoCupoConcursoAbierto> implements TipoCupoConcursoAbiertoDAOLocal {
	public TipoCupoConcursoAbiertoDAO() {
		// TODO Auto-generated constructor stub
		super(TipoCupoConcursoAbierto.class);
	}
}
