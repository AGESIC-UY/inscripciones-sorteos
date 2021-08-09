package uy.gub.imm.llamados.dao;



import javax.ejb.Stateless;
import uy.gub.imm.llamados.dao.local.ConcursoAbiertoDAOLocal;
import uy.gub.imm.llamados.entity.ConcursoAbierto;

@Stateless
public class ConcursoAbiertoDAO extends BaseDAO<ConcursoAbierto> implements ConcursoAbiertoDAOLocal {

	public ConcursoAbiertoDAO() {
		super(ConcursoAbierto.class);
	}

}
