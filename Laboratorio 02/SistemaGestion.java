public class SistemaGestion {
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
