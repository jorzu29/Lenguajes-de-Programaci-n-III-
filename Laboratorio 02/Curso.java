public class Curso {
	private String codigo;
	private String nombreCurso;
	private Escuela escuela;
	private Profesor profesor;
	private Estudiante[] listaEstudiantes;
	private int cantidadMatriculados;
	
	public static final int capacidadMaxima = 20;
	
	public Curso(String cod, String nom, Escuela esc) {
		this.codigo = cod;
		this.nombreCurso = nom;
		this.escuela = esc;
		this.listaEstudiantes = new Estudiante[capacidadMaxima];
		this.cantidadMatriculados = 0;
	}
	
	public void asignarProfesor(Profesor prof) {
		this.profesor = prof;
	}
	
	public void inscribirAlumno(Estudiante estudiante) {
		if (cantidadMatriculados >= capacidadMaxima) {
			System.out.println("El curso de " + nombreCurso + " está lleno :(");
		}
		
		else {
			listaEstudiantes[cantidadMatriculados] = estudiante;
			cantidadMatriculados++;
			System.out.println("La matricula se ha realizado con éxito!!");
		}
	}
	
	public boolean disponibilidadCurso() {
		return cantidadMatriculados < capacidadMaxima;
	}
	
	public int getCantidadMatriculados() {
		return cantidadMatriculados;
	}
	
	public Estudiante[] getListaEstudiantes() {
		return listaEstudiantes;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getNombreCurso() {
		return nombreCurso;
	}

	public Escuela getEscuela() {
		return escuela;
	}

	public Profesor getProfesor() {
		return profesor;
	}


}
