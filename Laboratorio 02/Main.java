public class Main {

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
