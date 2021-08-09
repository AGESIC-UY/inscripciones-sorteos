package uy.gub.imm.llamados.dao;

import javax.ejb.Stateless;

import uy.gub.imm.llamados.dao.local.PosicionSorteoDAOLocal;
import uy.gub.imm.llamados.entity.PosicionSorteo;
@Stateless
public class PosicionSorteoDAO extends BaseDAO<PosicionSorteo> implements PosicionSorteoDAOLocal{
	
	public PosicionSorteoDAO() {
		super(PosicionSorteo.class);
	}	


}
