package uy.gub.imm.llamados.dao.local;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

@Local
public interface BaseDAOLocal <E>{
	
	public void merge(E entity);
	public void persist(E entity);
	public void flush();
	public void remove(E entity);
	public E findById(long id);
	public List<E> findListByQuery(String query, Map<String, Object> params);
	public E findObjectByQuery(String query, Map<String, Object> params);
	public List<E> findAll();
	
	

}
