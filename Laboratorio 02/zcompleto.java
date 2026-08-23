abstract class Persona {
	private String id;
	private String nombre;
	private String email;

	public Persona (String id, String nom, String em) {
		this.id = id;
		this.nombre = nom;
		this.email = em;
	}

	public String getId () {
		return id;
	}

	public String getNombre () {
		return nombre;
	}

	public String getEmail () {
		return email;
	}

	public abstract String mostrarAtributos();
}
class Estudiante extends Persona {
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
class Profesor extends Persona {
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
class Escuela {
	private String nombreEscuela;

	public Escuela(String nom) {
		this.nombreEscuela = nom;
	}

	public String getNombreEscuela() {
		return nombreEscuela;
	}
}
class Curso {
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
class SistemaGestion {
	private static final int maximoCursos = 30;
	private Curso[] listaCursos;
	private  int cantidadCursos;

	public SistemaGestion() {
		this.listaCursos = new Curso[maximoCursos];
		this.cantidadCursos = 0;
	}

	public void agregarCurso(Curso curso) {
		if (cantidadCursos >= maximoCursos) {
			System.out.println("Se alcanzó el máximo de cursos :O");
		}
		else {
			listaCursos[cantidadCursos] = curso;
			cantidadCursos++;
			System.out.println("El curso se agregó correctamente");
		}
	}

	public void mostrarCursosDisponibles() {
		System.out.println("=================== Cursos Disponibles ===================");
		for (int i = 0; i < cantidadCursos; i++) {
			Curso cur = listaCursos[i];
			if (cur.disponibilidadCurso()) {
				System.out.println(cur.getCodigo() + " | " + cur.getNombreCurso() + " | Matriculados: " + cur.getCantidadMatriculados());
			}
		}
	}

	public int getCantidadCursos() {
		return cantidadCursos;
	}

	public Curso[] getlistaCursos() {
		return listaCursos;
	}
}
class Main {

	public static void main(String[] args) {
		SistemaGestion sistema = new SistemaGestion();

		Escuela escuelaIngSistemas = new Escuela("Ingeniería de Sistemas");
		Escuela escuelaArq = new Escuela("Arquitectura");

		Curso curso1 = new Curso("CS001","Lenguajes de Programación",escuelaIngSistemas);
		Curso curso2 = new Curso("CS002","Introducción a la arquitectura",escuelaArq);

		Profesor prof1 = new Profesor("P001", "Juan Soto", "sotog@gmail.com", curso1);
		Profesor prof2 = new Profesor("P002", "Armando Casas", "arCas@gmail.com", curso2);

		Estudiante est1 = new Estudiante("E001", "Jorge Zúñiga", "jorzu@gmail.com", "2025");
		Estudiante est2 = new Estudiante("E002", "Kevin Tacoma", "kevinTac@gmail.com", "2025");

		sistema.agregarCurso(curso1);
		sistema.agregarCurso(curso2);

		curso1.asignarProfesor(prof1);
		curso2.asignarProfesor(prof2);

		curso1.inscribirAlumno(est1);
		curso1.inscribirAlumno(est2);

		Persona[] personas = new Persona[3];
		personas[0] = prof1;
		personas[1] = est1;
		personas[2] = est2;

		System.out.println("=== Mostar Atributos ==="); //Demuesta que el polimorfismo funciona :D
		for (int i = 0; i < personas.length; i++) {
			System.out.println(personas[i].mostrarAtributos());
		}

		System.out.println();
		sistema.mostrarCursosDisponibles();

		System.out.println("\n=== Estudiantes en " + curso1.getNombreCurso() + " ==="); // Mostrar Estudiantes matriculados
		Estudiante[] alumnosMatriculados = curso1.getListaEstudiantes();

		for (int i = 0; i < curso1.getCantidadMatriculados(); i++) {
			System.out.println("- " + alumnosMatriculados[i].getNombre());
		}
	}
}
