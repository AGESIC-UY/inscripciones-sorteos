package uy.gub.imm.llamados.dao;

import javax.ejb.Stateless;
import uy.gub.imm.llamados.dao.local.PosicionSorteoInscriptoDAOLocal;
import uy.gub.imm.llamados.entity.PosicionSorteoInscripto;

@Stateless
public class PosicionSorteoInscriptoDAO extends BaseDAO<PosicionSorteoInscripto> implements PosicionSorteoInscriptoDAOLocal {

	public PosicionSorteoInscriptoDAO() {
		super(PosicionSorteoInscripto.class);
	}



	
}
