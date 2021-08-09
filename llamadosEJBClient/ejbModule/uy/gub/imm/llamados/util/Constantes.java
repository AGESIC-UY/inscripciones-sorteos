package uy.gub.imm.llamados.util;

public class Constantes {
	
	static final public String CORREO_INSCRIPCION=
			"<div> "+
					"  Estimado/a ciudadano/a,<br /><br />En relaci&oacute;n a su solicitud: \"Inscripci&oacute;n a llamado abierto :descripcion para el cupo :cupo\", N&deg; de inscripcion: :numeroInscripcion, "+
					"  confirmamos el ingreso de la misma con los siguientes valores:<br /><br /> "+
					"  <fieldset> "+
					"      <legend><span class=>Datos de la inscripci&oacute;n</span> </legend> "+
					"      <div> "+
					"        <div> "+
					"          <label>Primer nombre:</label> "+
					"          :primerNombre "+
					"        </div> "+
					"        <div> "+
					"            <label>Segundo nombre:</label> "+
					"            :segundoNombre "+
					"        </div> "+
					"        <div> "+
					"            <label>Primer apellido:</label> "+
					"            :primerApellido "+
					"        </div> "+
					"        <div> "+
					"            <label>Segundo apellido:</label> "+
					"            :segundoApellido "+
					"        </div> "+
					"        <div> "+
					"            <label>Fecha de nacimiento:</label> "+
					"            :fechaNacimiento "+
					"        </div> "+
					"        <div> "+
					"            <label>Sexo/G&eacute;nero:</label> "+
					"            :sexoGenero "+
					"        </div> "+
					"        <div> "+
					"            <label>Tel&eacute;fono de contacto:</label> "+
					"            :telefonoContacto "+
					"        </div> "+
					"        <div> "+
					"            <label>Tel&eacute;fono celular:</label> "+
					"            :telefonoCelular"+
					"        </div> "+
					"        <div> "+
					"            <label>Correo Electr&oacute;nico:</label> "+
					"            :correoElectronico "+
					"        </div> "+
					"        <div> "+
					"            <label>Direcci&oacute;n de contacto (de Montevideo):</label> "+
					"            :direccionContacto "+
					"        </div>         "+
					"    </div> "+
					"    </fieldset> "+
					"	 <br/>"+
					"	 <fieldset> "+
					"    	<legend><span class=>Baja inscripci&oacute;n</span> </legend> "+
					"        <div> "+
					"            <label>Link para baja de la inscripci&oacute;n:</label> "+
					"            :url "+
					"        </div>"+
					"	 </fieldset>"+	
					"   <br />-----------------------------------------------------------------------------------------------------------------------------------------------------<br />Este "+
					"  mensaje ha sido enviado en forma autom&aacute;tica, por favor no lo responda. "+
					"</div> ";

	static final public String CORREO_BAJA_INSCRIPCION=
			"<div> "+
					"  Estimado/a ciudadano/a,<br /><br />En relaci&oacute;n a su solicitud: \"Baja a llamado abierto :descripcion para el cupo :cupo\", N&deg; de inscripcion: :numeroInscripcion, "+
					"  confirmamos que la misma se ralizao con &eacute;xito<br/><br/> "+
			"</div>";

	static final public String PIE_CORREO=
		    "<div data-marker=\"__QUOTED_TEXT__\">"+
		    	    "  <br />"+
		    	    "  <div>"+
		    	    "    <hr />"+
		    	    "    <p style=\"text-align: justify\">"+
		    	    "      <span style=\"color: #999999\">"+
		    	    "        El presente correo electr&oacute;nico y cualquier posible archivo"+
		    	    "        adjunto esta dirigido &uacute;nicamente al destinatario del mismo y"+
		    	    "        contiene informaci&oacute;n que puede ser confidencial. Si Ud. no es"+
		    	    "        el destinatario correcto por favor notifique al remitente"+
		    	    "        respondiendo este mensaje y elimine inmediatamente de su sistema, el"+
		    	    "        correo electr&oacute;nico y los posibles archivos adjuntos al mismo."+
		    	    "        Esta prohibida cualquier utilizaci&oacute;n, difusi&oacute;n o copia"+
		    	    "        de este correo electr&oacute;nico por cualquier persona o entidad"+
		    	    "        que no sean las especificas destinatarias del mensaje. La"+
		    	    "        Intendencia de Montevideo no acepta ninguna responsabilidad con"+
		    	    "        respecto a cualquier comunicaci&oacute;n que haya sido emitida"+
		    	    "        incumpliendo nuestra normativa municipal, as&iacute; como lo"+
		    	    "        previsto en la Ley 18.331 de Protecci&oacute;n de Datos Personales y"+
		    	    "        la Ley 18.381 de Acceso a la Informaci&oacute;n Publica.</span>"+
		    	    "    </p>"+
		    	    "    <span style=\"color: #999999\"><span></span></span>"+
		    	    "    <p style=\"text-align: justify\" align='3D\"justify\"'>"+
		    	    "      <span style=\"color: #999999\""+
		    	    "        >This e-mail and any attachment is confidential and is intended"+
		    	    "        solely for the addressee(s). If you are not intended recipient"+
		    	    "        please inform the sender immediately, answering this e-mail and"+
		    	    "        delete it as well as the attached files. Any use, circulation or"+
		    	    "        copy of this e-mail by any person or entity that is not the specific"+
		    	    "        addressee(s) is prohibited. The City Council of Montevideo is not"+
		    	    "        responsible for any communication emitted without respecting our"+
		    	    "        Organization Policy or the Data Protection Act and Habeas Data"+
		    	    "        Action No. 18.331 and the Information Access Act N 18.381.</span>"+
		    	    "    </p>"+
		    	    "  </div>"+
		    	    "  <br /><br />"+
		    	    "</div>";


}
