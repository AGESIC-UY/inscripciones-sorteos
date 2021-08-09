package uy.gub.imm.llamados.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;
import org.jboss.logging.Logger;
import uy.gub.imm.llamados.dto.InscripcionCupoConcursoAbiertoDTO;
import uy.gub.imm.llamados.exceptions.ConcursoAbiertoException;


public class FuncionesUtiles {
	
	private static final Logger log = Logger.getLogger(FuncionesUtiles.class);
	
	static public String obtenerAmbiente() throws ConcursoAbiertoException{
		try {
			String path =  File.separator+"properties"+File.separator+"llamados"+File.separator+"llamados.properties";
			String fileName = System.getProperty("jboss.server.config.dir")+path;
			log.info("Se obtuvo el path para el archivo de properties: "+path);
			FileInputStream fis = new FileInputStream(fileName);
			Properties prop=new Properties();
			prop.load(fis);
			String ambiente=prop.getProperty("ambiente");
			log.info("Se obtuvo el ambiente: "+ambiente);
			return ambiente;
		} catch (FileNotFoundException e) {
			log.error("Ocurrió un error obtener el ambiente:"+e.getMessage());
			throw new ConcursoAbiertoException("Ocurrió un error obtener el ambiente");
		} catch (IOException e) {
			log.error("Ocurrió un error obtener el ambiente:"+e.getMessage());
			throw new ConcursoAbiertoException("Ocurrió un error obtener el ambiente");
		} catch (Exception e) {
			log.error("Ocurrió un error obtener el ambiente:"+e.getMessage());
			throw new ConcursoAbiertoException("Ocurrió un error obtener el ambiente");
		}

	}

	static public void enviarCorreoAltaInscripcion(InscripcionCupoConcursoAbiertoDTO inscripcion) throws ConcursoAbiertoException {
		
		String mensajeCorreo=FuncionesUtiles.generarMensaje(Constantes.CORREO_INSCRIPCION, generarMapAltaLlamado(inscripcion));
		/*
		 * CODIGO PARA ENVIAR CORREO
		 * */
	}

	static public void enviarCorreoBajaInscripcion(InscripcionCupoConcursoAbiertoDTO inscripcion) throws ConcursoAbiertoException {
		
		String mensajeCorreo=generarMensaje(Constantes.CORREO_BAJA_INSCRIPCION, generarMapBajaLlamado(inscripcion));
		/*
		 * CODIGO PARA ENVIAR CORREO
		 * */
	}
	
	static public Map<String,String> generarMapAltaLlamado(InscripcionCupoConcursoAbiertoDTO inscripcion){
		
		Map<String, String> params= new HashMap<String, String>();

		//Datos cabezal incripcion
		params.put(":descripcion", inscripcion.getCupoConcursoAbiertoDTO().getConcurso().getDescripcion());
		params.put(":cupo", inscripcion.getCupoConcursoAbiertoDTO().getDescripcion());
		params.put(":numeroInscripcion", inscripcion.getNumeroInscripcion().toString());
		
		//Datos ingresados
		params.put(":primerNombre", inscripcion.getPrimerNombre());
		params.put(":segundoNombre", inscripcion.getSegundoNombre());
		params.put(":primerApellido", inscripcion.getPrimerApellido());
		params.put(":segundoApellido", inscripcion.getSegundoApellido());
		params.put(":sexoGenero",inscripcion.getSexoGeneroDTO().getDescripcion());
		params.put(":fechaNacimiento", dateToStringDDMMYYYY(inscripcion.getFechaNacimiento()));
		params.put(":telefonoContacto", inscripcion.getTelefono());
		params.put(":telefonoCelular", inscripcion.getCelular());
		params.put(":correoElectronico", inscripcion.getCorreoElectronico());
		params.put("direccionContacto", inscripcion.getDireccion());

		//Url baja
		params.put(":url", generarUrlBaja(inscripcion.getToken()));
		
		return params;
	}
	
	static private Map<String,String> generarMapBajaLlamado(InscripcionCupoConcursoAbiertoDTO inscripcion){
		
		Map<String, String> params= new HashMap<String, String>();
		params.put(":descripcion", inscripcion.getCupoConcursoAbiertoDTO().getConcurso().getDescripcion());
		params.put(":cupo", inscripcion.getCupoConcursoAbiertoDTO().getCodigo());
		params.put(":numeroInscripcion", inscripcion.getNumeroInscripcion().toString());
		return params;
	}	

	static public String dateToStringDDMMYYYY(Date fecha){
		 DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
        String strDate = dateFormat.format(fecha); 
        return strDate;
	}
	
	private static String generarUrlBaja(String token){
		String url=null;
		try {
			url = obtenerUrlAplicacionInscripcion()+"pages/bajaLlamado.xhtml?token="+token;
		} catch (ConcursoAbiertoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return url;
	}
	
	public static String obtenerUrlAplicacionInscripcion() throws ConcursoAbiertoException{
		String url="";
		String ambiente;
		try {
			ambiente = obtenerAmbiente();
			if(ambiente.equalsIgnoreCase("LOCALHOST"))
				url="http://localhost:8080/app/llamados/";
			if(ambiente.equalsIgnoreCase("DESA"))
				url="https://desa-internet.imm.gub.uy/app/llamados/";
			if(ambiente.equalsIgnoreCase("TEST"))
				url="https://test-internet.imm.gub.u/app/llamados/";	
			if(ambiente.equalsIgnoreCase("PROD"))
				url="https://mi.montevideo.gub.uy/app/llamados/";	
			return url;
		} catch (ConcursoAbiertoException e) {
			throw new ConcursoAbiertoException("Ocurrió un error obtener el ambiente");
		}

	}

	public static String generarMensaje(String mensaje, Map<String, String> params){
		
		String m=mensaje;
		for (Entry<String, String> entry : params.entrySet()) {
			if(entry.getValue()!=null)
				m=m.replace(entry.getKey(),  entry.getValue());
			else m=m.replace(entry.getKey(),  "");
		}
		return m;
	}
}
