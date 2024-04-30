package ar.edu.unju.fi.ejercicio4.main;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import ar.edu.unju.fi.ejercicio4.constantes.Posicion;
import ar.edu.unju.fi.ejercicio4.model.Jugador;

public class Main {

	static final Scanner scanner = new Scanner(System.in);
	static final List<Jugador> jugadores = new ArrayList<>();

	public static void main(String[] args) {
		int opcion;

		do {
			mostrarMenu();
			opcion = seleccionarOpcion();
			switch (opcion) {
			case 1:
				altaJugador();
				pausa();
				break;
			case 2:
				mostrarJugadores();
				pausa();
				break;
			case 3:
				modificarPosicionJugador();
				pausa();
				break;
			case 4:
				eliminarJugador();
				pausa();
				break;
			case 5:
				System.out.println("*SALIENDO DEL PROGRAMA*");
				break;
			default:
				System.out.println("Opcion no valida");
				pausa();
			}
		} while (opcion != 5);
	}

	public static void mostrarMenu() {
		System.out.println("""
				Menu de opciones:
				1 - Dar de alta un jugador
				2 - Mostrar jugadores
				3 - Modificar la posicion de un jugador
				4 - Eliminar un jugador
				5 - Salir
				""");
	}

	public static int seleccionarOpcion() {
		int op;

		while (true) {
			try {
				System.out.print("Seleccione una opcion: ");
				op = Integer.parseInt(scanner.nextLine());
				return op;
			} catch (NumberFormatException e) {
				System.out.println("ERROR: Solo se permite el ingreso de numeros. Ingrese un numero de la lista");
			}
		}
	}

	public static void pausa() {
		System.out.println("Presione enter para continuar");
		scanner.nextLine();
	}

	public static void altaJugador() {
		try {
			System.out.print("Ingrese el nombre del jugador: ");
			String nombre = scanner.nextLine();

			System.out.print("Ingrese el apellido del jugador: ");
			String apellido = scanner.nextLine();

			System.out.print(
					"Ingrese la fecha de nacimiento (solamente en YYY-MM-DD, de otra forma no se permitira el registro): ");
			LocalDate fechaNacimiento = LocalDate.parse(scanner.nextLine());

			System.out.print("Ingrese la nacionalidad del jugador: ");
			String nacionalidad = scanner.nextLine();

			System.out.print("Ingrese la estatura (solamente el numero en metros, utilice . para los decimales): ");
			double estatura = Double.parseDouble(scanner.nextLine());

			System.out.print("Ingrese el peso (solamente el numero en kilogramos, utilice . para los decimales): ");
			double peso = Double.parseDouble(scanner.nextLine());

			System.out.println("Seleccione la posicion del jugador: ");
			for (int i = 0; i < Posicion.values().length; i++) {
				System.out.println((i + 1) + " - " + Posicion.values()[i]);
			}

			int posicionIndice = Integer.parseInt(scanner.nextLine()) - 1;
			if (posicionIndice < 0 || posicionIndice >= Posicion.values().length) {
				System.out.println("El dato no coincide con las posiciones disponibles");
				return;
			}

			Posicion posicion = Posicion.values()[posicionIndice];

			Jugador nuevoJugador = new Jugador(nombre, apellido, fechaNacimiento, nacionalidad, estatura, peso,
					posicion);
			jugadores.add(nuevoJugador);

			System.out.println("Jugador agregado");

		} catch (DateTimeParseException e) {
			System.out.println("ERROR: Fecha invalida. Ingrese la fecha solamente en formato YYYY-MM-DD");
		} catch (NumberFormatException e) {
			System.out.println("ERROR: El numero ingresado no es valido");
		} catch (InputMismatchException e) {
			System.out.println("ERROR: El numero ingresado no es valido");
		}
	}

	public static void mostrarJugadores() {
		if (jugadores.isEmpty()) {
			System.out.println("No hay jugadores");
		} else {
			System.out.println("Lista de jugadores: ");
			for (Jugador jugador : jugadores) {
				System.out.println(jugador.toString());
			}
		}
	}

	public static void modificarPosicionJugador() {
		if (jugadores.isEmpty()) {
			System.out.println("No hay jugadores");
			return;
		} else {
			try {
				System.out.print("Ingrese el nombre del jugador a modificar: ");
				String nombre = scanner.nextLine();

				System.out.print("Ingrese el apellido del jugador a modificar: ");
				String apellido = scanner.nextLine();

				Jugador jugadorParaModificar = null;

				for (Jugador jugador : jugadores) {
					if (jugador.getNombre().equalsIgnoreCase(nombre)
							&& jugador.getApellido().equalsIgnoreCase(apellido)) {
						jugadorParaModificar = jugador;
						break;
					}
				}

				if (jugadorParaModificar == null) {
					System.out.println("Jugador no encontrado");
					return;
				}

				System.out.println("Seleccione la nueva posicion del jugador: ");
				for (int i = 0; i < Posicion.values().length; i++) {
					System.out.println((i + 1) + " - " + Posicion.values()[i]);
				}

				int nuevaPosicionIndice = Integer.parseInt(scanner.nextLine()) - 1;
				if (nuevaPosicionIndice < 0 || nuevaPosicionIndice >= Posicion.values().length) {
					System.out.println("El dato no coincide con las posiciones disponibles");
					return;
				}

				Posicion nuevaPosicion = Posicion.values()[nuevaPosicionIndice];
				jugadorParaModificar.setPosicion(nuevaPosicion);

				System.out.println("Posicion del jugador modificada");

			} catch (NumberFormatException e) {
				System.out.println("ERROR: Ingrese numeros validos");
			}
		}
	}

	public static void eliminarJugador() {
		if (jugadores.isEmpty()) {
			System.out.println("No hay jugadores");
			return;
		} else {
			System.out.print("Ingrese el nombre del jugador a eliminar: ");
			String nombre = scanner.nextLine();

			System.out.print("Ingrese el apellido del jugador a eliminar: ");
			String apellido = scanner.nextLine();

			Jugador jugadorParaEliminar = null;

			for (Jugador jugador : jugadores) {
				if (jugador.getNombre().equalsIgnoreCase(nombre) && jugador.getApellido().equalsIgnoreCase(apellido)) {
					jugadorParaEliminar = jugador;
					break;
				}
			}

			if (jugadorParaEliminar == null) {
				System.out.println("Jugador no encontrado");
				return;
			}

			jugadores.remove(jugadorParaEliminar);
			System.out.println("Jugador eliminado");
			
		}
	}
}