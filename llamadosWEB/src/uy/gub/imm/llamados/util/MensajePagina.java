package uy.gub.imm.llamados.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class MensajePagina {
	
	public static void enviarMensajeSEVERITYERROR(String tag,String detail){
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(tag, new FacesMessage(FacesMessage.SEVERITY_ERROR,null, detail));
	}
	
	public static void enviarMensajeFACESMESSAGES(String tag,String detail){
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(tag, new FacesMessage(FacesMessage.SEVERITY_INFO,null, detail));
	}

}
