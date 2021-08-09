package uy.gub.imm.llamados.inscripcion;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import uy.gub.imm.llamados.dto.InscripcionCupoConcursoAbiertoDTO;
import uy.gub.imm.llamados.exceptions.ConcursoAbiertoException;
import uy.gub.imm.llamados.util.FuncionesUtiles;

@ManagedBean
@ViewScoped
public class ResultadoMB {
	
	private String resultado;
	private InscripcionCupoConcursoAbiertoDTO inscripcion;
	private String urlBaja;

	@PostConstruct
	public void init(){
		
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		try {
			
			inscripcion=(InscripcionCupoConcursoAbiertoDTO)session.getAttribute("INSCRIPCION");
			try {
				String ambiente=FuncionesUtiles.obtenerAmbiente();
				if (ambiente.equalsIgnoreCase("LOCALHOST"))
					setUrlBaja("https://localhost:8443/app/llamados/pages/bajaLlamado.xhtml?token="+inscripcion.getToken());
				if (ambiente.equalsIgnoreCase("DESA"))
					setUrlBaja("https://localhost:8443/app/llamados/pages/bajaLlamado.xhtml?token="+inscripcion.getToken());
				if (ambiente.equalsIgnoreCase("TEST"))
					setUrlBaja("https://localhost:8443/app/llamados/pages/bajaLlamado.xhtml?token="+inscripcion.getToken());
				if (ambiente.equalsIgnoreCase("PROD"))
					setUrlBaja("https://localhost:8443/app/llamados/pages/bajaLlamado.xhtml?token="+inscripcion.getToken());
			} catch (Exception e) {
			}
			
		} catch (Exception e) {
			resultado="No existe dato";
		}
	}
	
	
	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}


	public InscripcionCupoConcursoAbiertoDTO getInscripcion() {
		return inscripcion;
	}


	public void setInscripcion(InscripcionCupoConcursoAbiertoDTO inscripcion) {
		this.inscripcion = inscripcion;
	}
	
	public String getCiString(){
		return inscripcion.getCi().toString()+"-"+inscripcion.getBarra();
	}


	public String getUrlBaja() {
		return urlBaja;
	}


	public void setUrlBaja(String urlBaja) {
		this.urlBaja = urlBaja;
	}
	
	
	

}
