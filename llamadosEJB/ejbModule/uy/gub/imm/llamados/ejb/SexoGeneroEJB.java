package uy.gub.imm.llamados.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import uy.gub.imm.llamados.dao.local.SexoGeneroDAOLocal;
import uy.gub.imm.llamados.dto.SexoGeneroDTO;
import uy.gub.imm.llamados.entity.SexoGenero;

@Stateless
public class SexoGeneroEJB implements SexoGeneroLocal{
	
	@EJB
	SexoGeneroDAOLocal sexoGenero;

	@Override
	public List<SexoGeneroDTO> obtenerListaSexoGenero() {
		
		List<SexoGenero> listaSexoGenero = sexoGenero.findAll();
		
		List<SexoGeneroDTO>listaSexoGeneroDTO=null;
		
		if(listaSexoGenero!=null && listaSexoGenero.size()>0){
			listaSexoGeneroDTO= new ArrayList<SexoGeneroDTO>();
			for (SexoGenero sexoGenero : listaSexoGenero){
				listaSexoGeneroDTO.add(new SexoGeneroDTO(sexoGenero.getId(),sexoGenero.getDescripion()));
			}
		}
		return listaSexoGeneroDTO;
	}

}
