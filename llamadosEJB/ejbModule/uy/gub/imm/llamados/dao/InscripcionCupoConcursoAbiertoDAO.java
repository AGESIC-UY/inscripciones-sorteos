package uy.gub.imm.llamados.dao;

import javax.ejb.Stateless;
import uy.gub.imm.llamados.dao.local.InscripcionCupoConcursoAbiertoDAOLocal;
import uy.gub.imm.llamados.entity.InscripcionCupoConcursoAbierto;

@Stateless
public class InscripcionCupoConcursoAbiertoDAO extends BaseDAO<InscripcionCupoConcursoAbierto> implements InscripcionCupoConcursoAbiertoDAOLocal {

	public InscripcionCupoConcursoAbiertoDAO() {
		super(InscripcionCupoConcursoAbierto.class);
	}	

}
