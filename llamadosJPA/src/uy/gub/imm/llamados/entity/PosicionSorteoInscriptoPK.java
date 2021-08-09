package uy.gub.imm.llamados.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PosicionSorteoInscriptoPK implements Serializable{
	
	@Column(name="ID_SORTEO")
	private long idSorteo;
	
	@Column(name="NUMERO_INSCRIPCION")
	private int numeroInscripcion;

	public long getIdSorteo() {
		return idSorteo;
	}

	public void setIdSorteo(long idSorteo) {
		this.idSorteo = idSorteo;
	}

	public int getNumeroInscripcion() {
		return numeroInscripcion;
	}

	public void setNumeroInscripcion(int numeroInscripcion) {
		this.numeroInscripcion = numeroInscripcion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idSorteo ^ (idSorteo >>> 32));
		result = prime * result + numeroInscripcion;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PosicionSorteoInscriptoPK other = (PosicionSorteoInscriptoPK) obj;
		if (idSorteo != other.idSorteo)
			return false;
		if (numeroInscripcion != other.numeroInscripcion)
			return false;
		return true;
	}
	
	

	

}
