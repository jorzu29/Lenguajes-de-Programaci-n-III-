public abstract class Persona {
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
