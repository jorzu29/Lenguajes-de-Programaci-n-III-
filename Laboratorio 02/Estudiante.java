public class Estudiante extends Persona {
	private String matricula;
	
	private static int contadorEstudiantes = 0;
	
	public Estudiante(String id, String nom, String em, String mat) {
		super(id, nom, em);
		this.matricula = mat;
		contadorEstudiantes++;
	}
	
	public static int getContadorEstudiante() {
		return contadorEstudiantes;
	}
	
	@Override
	public String mostrarAtributos() {
		return "Nombre de Estudiante: " + getNombre() + " | Matricula: " + matricula;
	}
}
