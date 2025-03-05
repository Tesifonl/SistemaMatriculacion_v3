package org.iesalandalus.programacion.matriculacion.modelo.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Alumno implements Comparable<Alumno>  {
	
	private static final String ER_TELEFONO="[0-9]{9}";
	private static final String ER_CORREO="[a-zA-Z1-9áéíóú.]*[@]{1}[a-zA-Z]{1,15}[.]{1}[a-zA-Z]*";;
	private static final String ER_DNI="[0-9]{8}[a-zA-Z]{1}";
	public static final String FORMATO_FECHA="dd/MM/yyyy";
	private static final String ER_NIA="[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ]{4}[0-9]{3}";
	private static final int MIN_EDAD_ALUMNADO=16;
	
	private DateTimeFormatter formatoFechaString = DateTimeFormatter.ofPattern(FORMATO_FECHA);
	
	private String nombre;
	private String telefono;
	private String correo;
	private String dni;
	private LocalDate fechaNacimiento;
	private String nia;
	
	
	public Alumno (String nombre, String dni, String correo, String telefono, LocalDate fechaNacimiento) {
		setNombre(nombre);
		setDni(dni);
		setCorreo(correo);
		setTelefono(telefono);
		setFechaNacimiento(fechaNacimiento);
		setNia();
	}
	
	public Alumno (Alumno alumno) {
		
		if (alumno == null) { 
			throw new NullPointerException("ERROR: No es posible copiar un alumno nulo.");}
		else {
			setNombre(alumno.getNombre());
			setDni(alumno.getDni());
			setCorreo(alumno.getCorreo());
			setTelefono(alumno.getTelefono());
			setFechaNacimiento(alumno.getFechaNacimiento());
			setNia(alumno.getNia());
		}
	}


	public String getNia() {
		return nia;
	}


	private void setNia(String nia) {
		if (nia == null) { 
			throw new NullPointerException("ERROR: El NIA de un alumno no puede ser nulo.");}
		else if (nia.trim().equals("")) { 
			throw new IllegalArgumentException("ERROR: El NIA del alumno no tiene un formato valido.");}
		else if (!nia.matches(ER_NIA)) {
			throw new IllegalArgumentException("ERROR: El NIA del  alumno no tiene un formato valido");}
		else {this.nia=nia;}
	}
	
	public void setNia() {
		String letrasNombre="";
		String numerosDni="";
		
		letrasNombre=getNombre().substring(0, 4).toLowerCase();
		numerosDni=getDni().substring(5, 8);
		nia=letrasNombre+numerosDni;
	}
	
	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		if (nombre==null) { 
			throw new NullPointerException("ERROR: El nombre de un alumno no puede ser nulo.");}
		if (nombre.trim().equals("")) {
			throw new IllegalArgumentException("ERROR: El nombre de un alumno no puede estar vacío.");}
		
		this.nombre = formateaNombre(nombre);
		
	}
	
	private String formateaNombre(String nombre) {
		
		String[] formatoNombre=nombre.split(" ");
		nombre="";
		
		for (int i=0;i<formatoNombre.length;i++) 
		if(formatoNombre[i].equals("")) {
			formatoNombre[i].trim();}	
		else {formatoNombre[i]=formatoNombre[i].substring(0, 1).toUpperCase()+formatoNombre[i].substring(1).toLowerCase();
			if (i<formatoNombre.length-1) {nombre=nombre+formatoNombre[i]+ " ";}
			else {nombre=nombre+formatoNombre[i];}
			}
		return nombre;}


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		if (telefono == null) { 
			throw new NullPointerException("ERROR: El teléfono de un alumno no puede ser nulo.");}
		else if (telefono.trim().equals("")) { 
			throw new IllegalArgumentException("ERROR: El teléfono del alumno no tiene un formato válido.");}
		else if (!telefono.matches(ER_TELEFONO)) {
			throw new IllegalArgumentException("ERROR: El teléfono del alumno no tiene un formato válido.");}
		else {this.telefono = telefono;}
	}


	public String getCorreo() {
		return correo;
	}


	public void setCorreo(String correo) {
		if (correo==null) {
			throw new NullPointerException("ERROR: El correo de un alumno no puede ser nulo.");}
		else if (correo.trim().equals("")) { 
			throw new IllegalArgumentException("ERROR: El correo del alumno no tiene un formato válido.");}
		else if (!correo.trim().matches(ER_CORREO)) {
			throw new IllegalArgumentException("ERROR: El correo del alumno no tiene un formato válido.");}
		else {this.correo = correo;}
	}


	public String getDni() {
		return dni;
	}


	public void setDni(String dni) {
		if (dni== null) {
			throw new NullPointerException("ERROR: El dni de un alumno no puede ser nulo.");}
		else if (!dni.trim().matches(ER_DNI)) {
			throw new IllegalArgumentException("ERROR: El dni del alumno no tiene un formato válido.");}
		else if (comprobarLetraDni(dni)==true){
			this.dni = dni;}
		else {
			throw new IllegalArgumentException("ERROR: La letra del dni del alumno no es correcta.");}
	}

	private boolean comprobarLetraDni(String dni) {
		
		int resto=0;
		char letra = 0;
		String letrasDni="TRWAGMYFPDXBNJZSQVHLCKE";

		String numerado=dni.substring(0, 8);
		String dni2=dni.substring(0,8)+dni.substring(8).toUpperCase();
		int numeracion= Integer.parseInt(numerado);
		resto=numeracion % 23;
		letra=letrasDni.charAt(resto);
		
		if (letra==dni2.charAt(8)) {return true;}
		else {return false;}
	}
	
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}


	private void setFechaNacimiento(LocalDate fechaNacimiento) {
		
		if (fechaNacimiento==null) {
			throw new NullPointerException("ERROR: La fecha de nacimiento de un alumno no puede ser nula.");}
		else if(LocalDate.now().getYear()-fechaNacimiento.getYear()<MIN_EDAD_ALUMNADO){
			throw new IllegalArgumentException("ERROR: La edad del alumno debe ser mayor o igual a 16 años.");
		}
		else {this.fechaNacimiento=fechaNacimiento;}
	}
	

	
	private String getIniciales() {
		String ini="";
		nombre=formateaNombre(nombre);
		String [] iniciales=nombre.split(" ");
		for (int i=0;i<iniciales.length;i++)
		{iniciales[i]=iniciales[i].substring(0,1);	
		ini=ini+iniciales[i];}
		return ini;
	}


	@Override
	public int hashCode() {
		return Objects.hash(dni);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alumno other = (Alumno) obj;
		return Objects.equals(dni, other.dni);
	}

	@Override
	public String toString() {
		
		return "Número de Identificación del Alumnado (NIA)="+nia+" nombre="+nombre+" ("+getIniciales() +"), DNI="+dni+", correo="+correo+", teléfono="+telefono+", fecha nacimiento="+fechaNacimiento.format(formatoFechaString);
		
	}
	
	
	public String imprimir() {
		return toString();
	}

	@Override
	public int compareTo(Alumno o) {
		// TODO Auto-generated method stub
		return this.nombre.compareTo(o.nombre); 
	
	}
	
	


}
