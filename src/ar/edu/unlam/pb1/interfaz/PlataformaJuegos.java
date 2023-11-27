package ar.edu.unlam.pb1.interfaz;

import java.util.Scanner;

import ar.edu.unlam.pb1.dominio.Categoria;
import ar.edu.unlam.pb1.dominio.Juego;
import ar.edu.unlam.pb1.dominio.MenuPrincipal;
import ar.edu.unlam.pb1.dominio.MenuUsuario;
import ar.edu.unlam.pb1.dominio.Plataforma;
import ar.edu.unlam.pb1.dominio.Usuario;

public class PlataformaJuegos {
	private static final int SALIR = 99;
	private static Scanner teclado = new Scanner(System.in);

	public static void main(String[] args) {

		Plataforma plataforma = new Plataforma();
		MenuPrincipal opcionMenuPrincipal = null;
		int numeroIngresado = 0;

		do {
			mostrarPorPantalla("\nHola! Que deseas hacer en la plataforma de juegos?");
			mostrarMenuPrincipal();

			numeroIngresado = ingresarNumeroEntero("\n\nIngrese opcion:");
			opcionMenuPrincipal = obtenerOpcionDeMenuPrincipal(numeroIngresado);

			switch (opcionMenuPrincipal) {
			case INICIAR_SESION:
				iniciarSesionEn(plataforma);
				break;
			case REGISTRARME:
				registrarme(plataforma);
				break;
			case SALIR:
				break;
			}

		} while (opcionMenuPrincipal != MenuPrincipal.SALIR);
		mostrarPorPantalla("Programa finalizado");
	}

	private static void registrarme(Plataforma plataforma) {

		String nombre = ingresarString("Ingrese su nombre:");
		String apellido = ingresarString("Ingrese su apellido");
		String correo = ingresarString("Ingrese su correo");
		String contrasenia = ingresarString("Cree una contrasenia:");
		Usuario usuario = new Usuario(nombre, apellido, correo, contrasenia);
		boolean seRegistro = plataforma.registrarUsuario(usuario);
		boolean correoValido = plataforma.esValidoEl(correo);

		if (seRegistro && correoValido) {
			mostrarPorPantalla("Registro exitoso!");
		} else {
			mostrarPorPantalla("Error y/o correo ya existente");
		}
	}

	private static void iniciarSesionEn(Plataforma plataforma) {
		mostrarPorPantalla("\n\nIniciemos sesion!");

		String correo = ingresarString("Ingrese correo");
		String contrasenia = ingresarString("Ingrese contrasenia");
		Usuario inicioSesion = plataforma.iniciarSesion(correo, contrasenia);
		if (inicioSesion != null) {
			mostrarPorPantalla("Ingreso exitoso!");
			menuUsuario(inicioSesion, plataforma);
		} else {
			mostrarPorPantalla("Correo y/o contrasenia invalidas");
		}

	}

	private static void menuUsuario(Usuario usuario, Plataforma plataforma) {
		mostrarPorPantalla("Inicio exitoso!/n");
		MenuUsuario opcionMenuUsuario = null;
		int numeroIngresado = 0;

		do {
			mostrarMenuUsuario();
			numeroIngresado = ingresarNumeroEntero("\n\nIngrese opcion:");
			opcionMenuUsuario = obtenerOpcionDeMenuUsuario(numeroIngresado);
			int opcion = 0;

			switch (opcionMenuUsuario) {
			case MIS_JUEGOS:

				Juego[] usuarioJuegos = usuario.getJuego();
				mostrarJuegos(usuarioJuegos);
				int ingresarID = 0;
				do {
					ingresarID = ingresarNumeroEntero("Ingrese numero de ID para jugar o 99 para salir");
					usuario.jugarAlJuegoDe(ingresarID);
				} while (ingresarID != 99);
				break;
			case TIENDA:

				do {
					mostrarJuegos(plataforma.getJuegos());
					mostrarMenuTienda();
					opcion = ingresarNumeroEntero("\nIngrese opcion:");

					if (opcion >= 1 && opcion <= 10) {
						Juego usuarioJuego = plataforma.obtenerJuegoPorSuId(opcion);
						boolean seAgrego = usuario.agregarAMisJuegos(usuarioJuego);
						if (seAgrego) {
							mostrarPorPantalla("Juego agregado!");
						} else {
							mostrarPorPantalla("El juego no pudo ser agregado porque ya existe en la biblioteca.");
						}
					}

				} while (opcion != SALIR);

				break;
			case JUEGO_MAS_JUGADO_POR_CATEGORIA:

				String categoriaMasJugado = ingresarString(
						"Para que categoria desea saber cual es el juego mas jugada?");
				Categoria categoria = Categoria.valueOf(categoriaMasJugado.toUpperCase());
				Juego juego = usuario.obtenerJuegoMasJugadoPorCategoria(categoria);
				if (juego != null) {
					mostrarPorPantalla("Categoria mas jugada");
					mostrarJuego(juego);
					mostrarPorPantalla("Horas jugadas:" + juego.getCantidadHorasJugadas());
				} else {
					mostrarPorPantalla("Categoria no encontrado");
				}
				break;
			case SALIR:
				break;
			}

		} while (!opcionMenuUsuario.equals(MenuUsuario.SALIR));

	}

	private static void mostrarJuego(Juego juego) {

		if (juego != null) {
			mostrarPorPantalla("\n" + juego.toString());
		} else {
			mostrarPorPantalla("\nSin juego");
		}
	}

	private static void mostrarJuegos(Juego[] juegos) {

		for (int i = 0; i < juegos.length; i++) {
			if (juegos[i] != null) {
				mostrarPorPantalla("\n" + juegos[i].toString());
			}
		}
	}

	private static MenuPrincipal obtenerOpcionDeMenuPrincipal(int numeroIngresado) {
		return MenuPrincipal.values()[numeroIngresado - 1];
	}

	private static MenuUsuario obtenerOpcionDeMenuUsuario(int numeroIngresado) {
		return MenuUsuario.values()[numeroIngresado - 1];
	}

	private static int ingresarNumeroEntero(String mensaje) {
		mostrarPorPantalla(mensaje);
		return teclado.nextInt();
	}

	private static String ingresarString(String mensaje) {
		mostrarPorPantalla(mensaje);
		return teclado.next();
	}

	private static void mostrarMenuTienda() {
		mostrarPorPantalla("\n\nIngrese el ID del juego que desea adquirir o 99 para salir:");
	}

	private static void mostrarMenuUsuario() {
		mostrarPorPantalla("\n\n1) Mis juegos\n2) Tienda\n3) Juego mas jugado por categoria\n4) Salir");
	}

	private static void mostrarMenuPrincipal() {
		mostrarPorPantalla("\n\n1) Iniciar sesion\n2) Registrame");
	}

	private static void mostrarPorPantalla(String mensaje) {
		System.out.println(mensaje);
	}

}
