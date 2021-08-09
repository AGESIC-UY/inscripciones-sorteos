package uy.gub.imm.llamados.ejb;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import uy.gub.imm.llamados.dao.local.CupoConcursoAbiertoDAOLocal;
import uy.gub.imm.llamados.dao.local.InscripcionCupoConcursoAbiertoDAOLocal;
import uy.gub.imm.llamados.dao.local.PosicionSorteoDAOLocal;
import uy.gub.imm.llamados.dao.local.SorteoCupoConcursoAbriertoDAOLocal;
import uy.gub.imm.llamados.dto.CupoConcursoAbiertoDTO;
import uy.gub.imm.llamados.dto.SorteoCupoConcursoAbriertoDTO;
import uy.gub.imm.llamados.entity.CupoConcursoAbierto;
import uy.gub.imm.llamados.entity.InscripcionCupoConcursoAbierto;
import uy.gub.imm.llamados.entity.PosicionSorteo;
import uy.gub.imm.llamados.entity.SorteoCupoConcursoAbrierto;
import uy.gub.imm.llamados.exceptions.ConcursoAbiertoException;

@Stateless
public class SorteoCupoConcursoAbiertoEJB implements SorteoCupoConcursoAbiertoLocal {
	
	@EJB
	SorteoCupoConcursoAbriertoDAOLocal sorteoCupoConcursoAbriertoDAO;
	
	@EJB
	CupoConcursoAbiertoDAOLocal cupoConcursoAbiertoDAO;
	
	@EJB
	InscripcionCupoConcursoAbiertoDAOLocal inscripcionCupoConcursoAbiertoDAO;
	
	@EJB
	PosicionSorteoDAOLocal posicionSorteoDAO;

	@Override
	public void realizarSorteo(CupoConcursoAbiertoDTO cupoDTO ) throws ConcursoAbiertoException {
		
		CupoConcursoAbierto cupo = cupoConcursoAbiertoDAO.findById(cupoDTO.getId());
		SorteoCupoConcursoAbrierto sorteo = new SorteoCupoConcursoAbrierto();
		sorteo.setSemilla(cupoDTO.getSorteo().getSemilla());
		sorteo.setFechaSorteo(new Date());
		sorteo.setCupoConcursoAbierto(cupo);
		sorteoCupoConcursoAbriertoDAO.persist(sorteo);
		
		Map<String, Object> paramsInscriptos= new HashMap<String,Object>();
		paramsInscriptos.put("idCupo", cupoDTO.getId());
		List<InscripcionCupoConcursoAbierto> listaInscriptos = inscripcionCupoConcursoAbiertoDAO.findListByQuery("InscripcionCupoConcursoAbierto.findInscriptosByIdCupo", paramsInscriptos);
		int cantidadInscriptos = listaInscriptos.size();
		int[] arrayAleatorio = obtenerArrayNumerosAleatorios(cantidadInscriptos,sorteo.getSemilla());
		
		int i=0;
		for(InscripcionCupoConcursoAbierto inscripto:listaInscriptos){
			PosicionSorteo posicion= new PosicionSorteo();
			posicion.setInscripcionCupoConcursoAbierto(inscripto);
			posicion.setSorteoCupoConcursoAbrierto(sorteo);
			posicion.setPosicion(arrayAleatorio[i]);
			i++;
			posicionSorteoDAO.persist(posicion);
			
			
		}
	}
	
	private int[] obtenerArrayNumerosAleatorios(int cantidad,int semilla){
		int n=cantidad;  //numeros aleatorios
		int k=n;  //auxiliar;
		int[] numeros=new int[n];
		int[] resultado=new int[n];
		Random rnd=new Random(semilla);
		int res;
		
		
		//se rellena una matriz ordenada del 1 al 31(1..n)
		for(int i=0;i<n;i++){
		    numeros[i]=i+1;
		}
		
		for(int i=0;i<n;i++){
		    res=rnd.nextInt(k);            
		    resultado[i]=numeros[res];
		    numeros[res]=numeros[k-1];
		    k--;
		}
		
		return resultado;
		
	}

}
