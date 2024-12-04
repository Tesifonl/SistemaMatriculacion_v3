package org.iesalandalus.programacion.matriculacion.negocio;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.matriculacion.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.dominio.CicloFormativo;

public class CiclosFormativos {
	
	private CicloFormativo [] coleccionCiclosFormativos;
	private int tamano;
	private int capacidad;
	
	public CiclosFormativos (int capacidad) {
		if (capacidad>0) {
			coleccionCiclosFormativos=new CicloFormativo [capacidad];
		}
		else {
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		}
	}
	
	public CicloFormativo[] get() {
		CicloFormativo[] copia=copiaProfundaAlumnos();
		return copia;
	}
	
	private CicloFormativo[] copiaProfundaAlumnos() {
		CicloFormativo[]copiaAlumnos=new CicloFormativo[coleccionCiclosFormativos.length];
		
		for(int i=0;i<coleccionCiclosFormativos.length;i++) {
			if(coleccionCiclosFormativos[i]!=null) {copiaAlumnos[i]= new CicloFormativo(coleccionCiclosFormativos[i]);
			}
			else {
				copiaAlumnos[i]=null;
			}
		}
		return copiaAlumnos;
	}

	public int getTamano() {
		int tamano=0;
		
		for (int i=0;i<coleccionCiclosFormativos.length;i++) {
			if(coleccionCiclosFormativos[i]!=null) {tamano++;}
		}
		
		return tamano;
	}

	
	public int getCapacidad() {
		capacidad=coleccionCiclosFormativos.length;
		
		return capacidad;
	}
	
	public void insertar(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
		boolean noEncontrado=false;
		boolean encontrado=false;
	
		
		if (cicloFormativo!=null) {
			for(int i=0;i<coleccionCiclosFormativos.length;i++) {
				if(coleccionCiclosFormativos[i]!=null && coleccionCiclosFormativos[i].equals(cicloFormativo)) {
					noEncontrado=false;
					encontrado=true;
				}
				else {
					noEncontrado=true;
				}
			}
			
			if (noEncontrado==true && encontrado==false && getTamano()<getCapacidad()) {
				coleccionCiclosFormativos[getTamano()]=cicloFormativo;		
			}
			
			else if(encontrado==true) {
				throw new OperationNotSupportedException("ERROR: Ya existe un ciclo formativo con ese código.");	
			}
			else {
				throw new OperationNotSupportedException("ERROR: No se aceptan más ciclos formativos.");
			}
		}
		else {
			throw new NullPointerException("ERROR: No se puede insertar un ciclo formativo nulo.");
		}
	}
	
	private int buscarIndice(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
		int indice=0;
		
		if (cicloFormativo!=null) {
			for(int i=0;i<coleccionCiclosFormativos.length;i++) {
				if(coleccionCiclosFormativos[i]!=null && coleccionCiclosFormativos[i].equals(cicloFormativo)) {
					indice=i;
				}
				else {
					throw new OperationNotSupportedException("Ya existe este ciclo formativo");
				}
				
			}
		}
		else {
			throw new NullPointerException("Se ha recibido un ciclo formativo nulo");
		}
		return indice;
	}
	
	private boolean tamanoSuperado(int indice) {
		boolean superado=false;
		
		if (indice>getTamano()) {
			superado=true;
			}
		return superado;
	}
	
	private boolean capacidadSuperada(int indice) {
		boolean superado=false;
		
		if(indice>getCapacidad()) {
			superado=true;
		}
		return superado;
	}
	
	
	public CicloFormativo buscar(CicloFormativo cicloFormativo) {
		boolean encontrado=false;
		boolean otro=false;
		int indice=0;
		CicloFormativo copiaAlumno=null;
		
		if(cicloFormativo!=null) {
			for (int i =0;i<coleccionCiclosFormativos.length;i++) {
				if (coleccionCiclosFormativos[i]!=null && coleccionCiclosFormativos[i].equals(cicloFormativo)) {
					encontrado=true;
					indice=i;
					copiaAlumno=new CicloFormativo(coleccionCiclosFormativos[indice]);
				}
				else {
					otro=true;
				}
			}	
				
			if(encontrado==true) {
				return copiaAlumno;
				
			}else {return null;}
				
		}
		else {
			throw new NullPointerException("ciclo formativo recibido nulo");
		}
	}
	
	public void borrar(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
		boolean encontrado=false;
		boolean otro=false;
		int indice=0;
		
		if(cicloFormativo!=null) {
			for (int i =0;i<coleccionCiclosFormativos.length;i++) {
				if (coleccionCiclosFormativos[i]!=null && coleccionCiclosFormativos[i].equals(cicloFormativo)) {
					encontrado=true;
					indice=i;
				}
				else {
					otro=false;
				}
			}
			if (encontrado==true) {
				coleccionCiclosFormativos[indice]=null;
				desplazarUnaPosicionHaciaIzquiera(indice);
			}
			else {
				throw new OperationNotSupportedException("ERROR: No existe ningún ciclo formativo como el indicado.");
			}
		}
		else {
			throw new NullPointerException("ERROR: No se puede borrar un ciclo formativo nulo.");
		}
	}
	
	private void desplazarUnaPosicionHaciaIzquiera(int indice) {
		
		for (int i =indice;i<coleccionCiclosFormativos.length-1;i++) {
			coleccionCiclosFormativos[i]=coleccionCiclosFormativos[i+1];
			}
			coleccionCiclosFormativos[coleccionCiclosFormativos.length-1]=null;

	}
}
