package ar.edu.unlam.pb1.dominio;

public class Usuario {

	private String nombre;
	private String apellido;
	private String correo;
	private String contrasenia;
	private Juego[] juego;

	public Usuario(String nombre, String apellido, String correo, String contrasenia) {

		this.juego = new Juego[1000];
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.contrasenia = contrasenia;

	}

	public boolean agregarAMisJuegos(Juego juego) {

		boolean seAgrego = false;
		int contador = 0;
		while (!seAgrego && contador < this.juego.length) {
			if (this.juego[contador] == null && !this.tengoElJuegoDe(juego.getId())) {
				this.juego[contador] = juego;
				seAgrego = true;
			}
			contador++;

		}
		return seAgrego;
	}

	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", apellido=" + apellido + "]";
	}

	public boolean tengoElJuegoDe(int id) {

		boolean estaElJuego = false;
		int contador = 0;
		while (!estaElJuego && contador < this.juego.length) {
			if (this.juego[contador] != null && this.juego[contador].getId() == id) {
				estaElJuego = true;
			}
			contador++;
		}
		return estaElJuego;
	}

	public Juego obtenerJuegoMasJugadoPorCategoria(Categoria categoria) {

		Juego juegoMasJugado = null;
		double horaMayor = 0;

		for (int i = 0; i < this.juego.length; i++) {
			if (this.juego[i] != null && this.juego[i].getCategoria().equals(categoria)) {
				if (this.juego[i].getCantidadHorasJugadas() > horaMayor) {

					horaMayor = this.juego[i].getCantidadHorasJugadas();
					juegoMasJugado = this.juego[i];

				}
			}
		}

		return juegoMasJugado;
	}

	public Juego[] obtenerJuegosOrdenadosPorCategoria() {
		Juego[] ordenadoCategoria = new Juego[this.juego.length];
		for (int i = 0; i < this.juego.length; i++) {
			for (int a = 0; a < this.juego.length - 1; a++) {
				if (this.juego[a].getCategoria().compareTo(this.juego[a + 1].getCategoria()) > 0) {
					Juego ordenado = this.juego[i];
					ordenadoCategoria[a] = this.juego[a + 1];
					ordenadoCategoria[a + 1] = ordenado;
				}
			}

		}
		return ordenadoCategoria;
	}

	public void jugarAlJuegoDe(int id) {

		boolean seAgregoHora = false;
		int contador = 0;
		while (!seAgregoHora && contador < this.juego.length) {
			if (this.juego[contador] != null && this.juego[contador].getId() == id) {
				this.juego[contador].setCantidadHorasJugadas(1.5);
				seAgregoHora = true;
			}
			contador++;
		}

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public Juego[] getJuego() {
		return juego;
	}

	public void setJuego(Juego[] juego) {
		this.juego = juego;
	}

}
