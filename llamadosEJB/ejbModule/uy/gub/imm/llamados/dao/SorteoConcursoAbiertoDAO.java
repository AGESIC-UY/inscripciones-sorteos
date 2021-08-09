package uy.gub.imm.llamados.dao;

import javax.ejb.Stateless;
import uy.gub.imm.llamados.dao.local.SorteoCupoConcursoAbriertoDAOLocal;
import uy.gub.imm.llamados.entity.SorteoCupoConcursoAbrierto;

@Stateless
public class SorteoConcursoAbiertoDAO extends BaseDAO<SorteoCupoConcursoAbrierto> implements SorteoCupoConcursoAbriertoDAOLocal {

	public SorteoConcursoAbiertoDAO() {
		super(SorteoCupoConcursoAbrierto.class);
	}


}
