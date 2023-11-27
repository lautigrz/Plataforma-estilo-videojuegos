package ar.edu.unlam.pb1.dominio;

public class Plataforma {

	private Usuario[] usuarios;
	private Juego[] juegos;

	public Plataforma() {
		this.usuarios = new Usuario[10];
		this.juegos = new Juego[10];
		this.inicializarPlataforma(); // :)
	}

	public Usuario buscarUsuarioConCorreo(String correo) {

		Usuario usuario = null;
		boolean seEncontroUsuario = false;
		int contador = 0;

		while (!seEncontroUsuario && contador < this.usuarios.length) {
			if (this.usuarios[contador] != null && this.usuarios[contador].getCorreo().equals(correo)) {
				usuario = this.usuarios[contador];
				seEncontroUsuario = true;
			}
			contador++;
		}
		return usuario;
	}

	public Usuario iniciarSesion(String correo, String contrasenia) {

		Usuario usuario = null;
		boolean datosValidos = false;
		int contador = 0;
		while (!datosValidos && contador < this.usuarios.length) {
			if (this.usuarios[contador] != null
					&& this.buscarUsuarioConCorreo(correo).equals(this.usuarios[contador])) {
				if (this.usuarios[contador].getContrasenia().equals(contrasenia)) {
					usuario = this.usuarios[contador];
					datosValidos = true;
				}
			}
			contador++;
		}
		return usuario;
	}

	public boolean esValidoEl(String correo) {

		int tieneCaracter = 0;
		boolean esValido = false;
		for (int i = 0; i < correo.length(); i++) {
			if (correo.charAt(i) == '@') {
				tieneCaracter = i;
			}
		}
		boolean terminaConCom = correo.substring(tieneCaracter + 1).equals(".com");

		if (tieneCaracter > 0 && terminaConCom) {
			esValido = true;
		}
		return esValido;
	}

	public boolean registrarUsuario(Usuario usuario) {

		boolean seRegistro = true;
		for (int i = 0; i < this.usuarios.length; i++) {
			if (this.usuarios[i] != null && this.usuarios[i].getCorreo().equals(usuario.getCorreo())) {
				seRegistro = false;
				break;
			}
		}
		if (seRegistro) {
			boolean espacioNull = false;
			int contador = 0;
			while (!espacioNull && contador < this.usuarios.length) {
				if (this.usuarios[contador] == null) {
					this.usuarios[contador] = usuario;
					espacioNull = true;
					seRegistro = true;
				}
				contador++;
			}
		}
		return seRegistro;
	}

	public Juego obtenerJuegoPorSuId(int id) {

		Juego juegoPorId = null;
		boolean seEncontroJuego = false;
		int contador = 0;
		while (!seEncontroJuego && contador < this.juegos.length) {
			if (this.juegos[contador] != null && this.juegos[contador].getId() == id) {
				juegoPorId = this.juegos[contador];
				seEncontroJuego = true;
			}
			contador++;
		}
		return juegoPorId;
	}

	public Juego[] getJuegos() {
		return this.juegos;
	}

	private Juego crearJuego(int id, String nombre, Categoria categoria) {
		return new Juego(id, nombre + " " + id, categoria);
	}

	private void inicializarPlataforma() {

		String nombre = "";
		Categoria categoria;

		for (int i = 0; i < this.juegos.length; i++) {

			if (i < 3) {
				nombre = "Aventura";
				categoria = Categoria.AVENTURA;
			} else if (i < 7) {
				nombre = "Accion";
				categoria = Categoria.ACCION;
			} else {
				nombre = "Deportes";
				categoria = Categoria.DEPORTES;
			}

			this.juegos[i] = crearJuego((i + 1), nombre, categoria);
		}

	}

}
