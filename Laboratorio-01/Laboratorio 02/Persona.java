public class Profesor extends Persona {
	private Curso curso;
		
	public Profesor(String id, String nom, String em, Curso cur) {
		super(id, nom, em);
		this.curso = cur;
	}
	
	@Override
	public String mostrarAtributos() {
		return "Nombre de Profesor : " + getNombre() + " | Curso asignado: " + curso.getNombreCurso();
	}
	
}
