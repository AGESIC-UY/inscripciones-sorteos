package uy.gub.imm.llamados.managedbeans;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.Part;

import org.jboss.logging.Logger;

@ManagedBean
@ViewScoped
public class SorteoGenerico {
	
	private static final Logger log = Logger.getLogger(SorteoGenerico.class);
	
	private Part uploadedFile;
	String c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12,c13,c14,c15,c16,c17,c18,c19,c20,c21,c22,c23,c24,c25;
	
	
	public void saveFile(){
		c1=c2=c3=c4=c5=c6=c7=c8=c9=c10=c11=c12=c13=c14=c15=c16=c17=c18=c19=c20=c21=c22=c23=c24=c25=null;
		
		List<String> cabezales= new ArrayList<String>();
		
		try (
			InputStream fileContent = uploadedFile.getInputStream()) {
			String fileName = uploadedFile.getSubmittedFileName();

			String tmpDir=System.getProperty("java.io.tmpdir"); 
			log.info("Obtuve directorio temporal ......"+tmpDir);
			
	        String pahtDestino = tmpDir+"/"+fileName;
	        FileOutputStream fileOutputStream =new FileOutputStream(pahtDestino);
	        byte dataBuffer[] = new byte[1024];
			int bytesRead;
			while ((bytesRead = fileContent.read(dataBuffer, 0, 1024)) != -1) {
				fileOutputStream.write(dataBuffer, 0, bytesRead);
			}
			fileOutputStream.close();
			

			 BufferedReader br = null;
			 br =new BufferedReader(new FileReader(pahtDestino));
			 String line = br.readLine();
	         while (null!=line) {
	             String [] cabezal = line.split("\t");
	             for(int i=0;i<cabezal.length;i++){
	            	 
	             }
	             line = br.readLine();
	          }
	         br.close();

		
		}
	    catch (IOException e) {
	    	log.error("Ocurrio un error leyendo el archivo");
	    }

	}

	public Part getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(Part uploadedFile) {
		this.uploadedFile = uploadedFile;
	}
	
	

}
