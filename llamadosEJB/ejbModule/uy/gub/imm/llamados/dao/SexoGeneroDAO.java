package uy.gub.imm.llamados.dao;

import javax.ejb.Stateless;
import uy.gub.imm.llamados.dao.local.SexoGeneroDAOLocal;
import uy.gub.imm.llamados.entity.SexoGenero;

@Stateless
public class SexoGeneroDAO extends BaseDAO<SexoGenero> implements SexoGeneroDAOLocal {

	public SexoGeneroDAO(){
		super(SexoGenero.class);
	}
}
