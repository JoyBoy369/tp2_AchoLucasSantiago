package ar.edu.unju.fi.ejercicio7.main;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import ar.edu.unju.fi.ejercicio5.model.Producto;

public class Main {

	static final Scanner scanner = new Scanner(System.in);
	static final List<Producto> listaProductos = new ArrayList<>();

	public static void main(String[] args) {
		int opcion;

		List<Producto> productosCargados = precargarProductos(listaProductos);

		do {
			mostrarMenu();
			opcion = seleccionarOpcion();
			switch (opcion) {
			case 1:
				mostrarProductos(productosCargados);
				pausa();
				break;
			case 2:
				mostrarProductosFaltantes(productosCargados);
				pausa();
				break;
			case 3:
				incrementarPrecios(productosCargados);
				pausa();
				break;
			case 4:
				mostrarProductosElectrohogar(productosCargados);
				pausa();
				break;
			case 5:
				ordenarProductosPrecioDescendente(productosCargados);
				pausa();
				break;
			case 6:
				mostrarProductosNombreMayuscula(productosCargados);
				pausa();
				break;
			case 7:
				System.out.println("*SALIENDO DEL PROGRAMA*");
				break;
			default:
				System.out.println("Opcion no valida");
				pausa();
			}
		} while (opcion != 7);
	}

	public static List<Producto> precargarProductos(List<Producto> productos) {
		if (productos.isEmpty()) {
			productos.add(new Producto("1", "Celular Samsung Argentino", 50000f, Producto.OrigenFabricacion.ARGENTINA,
					Producto.Categoria.TELEFONIA, true));
			productos.add(new Producto("2", "Notebook Argentina", 60000f, Producto.OrigenFabricacion.ARGENTINA,
					Producto.Categoria.INFORMATICA, false));
			productos.add(new Producto("3", "Microondas Argentino", 60000f, Producto.OrigenFabricacion.ARGENTINA,
					Producto.Categoria.ELECTROHOGAR, true));
			productos.add(new Producto("4", "Herramientas Argentinas", 60000f, Producto.OrigenFabricacion.ARGENTINA,
					Producto.Categoria.HERRAMIENTAS, false));
			productos.add(new Producto("5", "Celular Motorola Argentino", 60000f, Producto.OrigenFabricacion.ARGENTINA,
					Producto.Categoria.TELEFONIA, true));
			productos.add(new Producto("6", "Celular Nokia Argentino", 50000f, Producto.OrigenFabricacion.ARGENTINA,
					Producto.Categoria.TELEFONIA, false));
			productos.add(new Producto("7", "Celular Samsung Chino", 50000f, Producto.OrigenFabricacion.CHINA,
					Producto.Categoria.TELEFONIA, true));
			productos.add(new Producto("8", "Notebook China", 60000f, Producto.OrigenFabricacion.CHINA,
					Producto.Categoria.INFORMATICA, false));
			productos.add(new Producto("9", "Microondas Chino", 60000f, Producto.OrigenFabricacion.CHINA,
					Producto.Categoria.ELECTROHOGAR, true));
			productos.add(new Producto("10", "Celular Samsung Brasil", 50000f, Producto.OrigenFabricacion.BRASIL,
					Producto.Categoria.TELEFONIA, true));
			productos.add(new Producto("11", "Notebook Brasil", 60000f, Producto.OrigenFabricacion.BRASIL,
					Producto.Categoria.INFORMATICA, false));
			productos.add(new Producto("12", "Microondas Brasil", 60000f, Producto.OrigenFabricacion.BRASIL,
					Producto.Categoria.ELECTROHOGAR, true));
			productos.add(new Producto("13", "Celular Samsung Uruguayo", 50000f, Producto.OrigenFabricacion.URUGUAY,
					Producto.Categoria.TELEFONIA, true));
			productos.add(new Producto("14", "Notebook Uruguaya", 60000f, Producto.OrigenFabricacion.URUGUAY,
					Producto.Categoria.INFORMATICA, false));
			productos.add(new Producto("15", "Microondas Uruguayo", 60000f, Producto.OrigenFabricacion.URUGUAY,
					Producto.Categoria.ELECTROHOGAR, true));
		}
		return productos;
	}

	public static void mostrarMenu() {
		System.out.println("""
				Menu de opciones:
				1 - Mostrar productos
				2 - Mostrar productos faltantes
				3 - Incrementar precios en un 20% (esto solo mostrara los precios aumentados en un 20%, no los cambia)
				4 - Mostrar productos de ELECTROHOGAR con stock disponible
				5 - Ordenar productos por precio de mayor a menor
				6 - Mostrar productos con los nombres en mayusculas
				7 - Salir
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

	public static void mostrarProductos(List<Producto> productos) {
		System.out.println("Productos disponibles: ");
		Consumer<Producto> mostrarProducto = producto -> {
			if (producto.isEstado()) {
				System.out.println(producto);
			}
		};

		productos.forEach(mostrarProducto);
	}

	public static void mostrarProductosFaltantes(List<Producto> productos) {
		System.out.println("Productos sin stock: ");

		Predicate<Producto> sinStock = producto -> !producto.isEstado();
		productos.stream().filter(sinStock).forEach(producto -> System.out.println(producto));
	}

	public static void incrementarPrecios(List<Producto> productos) {

		Function<Producto, Producto> incrementarPrecio = producto -> {
			Producto productoIncrementado = new Producto(producto.getCodigo(), producto.getDescripcion(),
					producto.getPrecioUnitario() * 1.20f, producto.getOrigenFabricacion(), producto.getCategoria(),
					producto.isEstado());
			return productoIncrementado;
		};

		List<Producto> productosIncrementados = productos.stream().map(incrementarPrecio).collect(Collectors.toList());

		System.out.println("Productos con precios incrementados un 20%: ");
		productosIncrementados.forEach(System.out::println);
	}

	public static void mostrarProductosElectrohogar(List<Producto> productos) {
		System.out.println("Productos de Electrohogar disponibles: ");

		Predicate<Producto> electrohogarDisponible = producto -> producto
				.getCategoria() == Producto.Categoria.ELECTROHOGAR && producto.isEstado();

		productos.stream().filter(electrohogarDisponible).forEach(System.out::println);
	}

	public static void ordenarProductosPrecioDescendente(List<Producto> productos) {
		productos.sort(Comparator.comparing(Producto::getPrecioUnitario).reversed());
		System.out.println("Productos ordenados");
	}

	public static void mostrarProductosNombreMayuscula(List<Producto> productos) {
		System.out.println("Productos con sus nombres en mayuscula: ");
		
		Function<Producto, Producto> transformarNombre = producto -> {
			Producto productoTransformado = new Producto(producto.getCodigo(), producto.getDescripcion().toUpperCase(),
					producto.getPrecioUnitario(), producto.getOrigenFabricacion(), producto.getCategoria(), producto.isEstado());
			return productoTransformado;
		};

		productos.stream().map(transformarNombre).forEach(System.out::println);
	}

}