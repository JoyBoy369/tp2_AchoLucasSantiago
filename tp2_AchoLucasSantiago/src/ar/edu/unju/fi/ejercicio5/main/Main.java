package ar.edu.unju.fi.ejercicio5.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ar.edu.unju.fi.ejercicio5.model.PagoEfectivo;
import ar.edu.unju.fi.ejercicio5.model.PagoTarjeta;
import ar.edu.unju.fi.ejercicio5.model.Producto;


public class Main {

	public static void main(String[] args) {
		byte opcion;
		String codigoProducto;
		String respuesta;
		boolean carga = false;
		double montoTotal = 0;
		Scanner scanner = new Scanner(System.in);

		List<Producto> listaProductos = new ArrayList<>();
		List<Producto> listaCompra = new ArrayList<>();

		Producto producto = new Producto();
		// cargar productos ala lista //
		cargarProductos(listaProductos);
		

		do {
			System.out.println("");
			System.out.println("***Compras***");
			System.out.println("1-Mostrar productos");
			System.out.println("2-Realizar compra");
			System.out.println("3-Salir");
			try {// control de opciones menu principal
				System.out.print("Elija una opcion: ");
				opcion = scanner.nextByte();
				if (opcion < 1 || opcion > 3) {
					opcion = 0;
					throw new IllegalArgumentException("Opcion invalida, debe elegir una opcion del 1 al 3");
				}
			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
				scanner.nextLine();
				opcion = 0;
			}
			switch (opcion) {
			case 1:// mostrar productos
				for (Producto prod : listaProductos) {
					if (prod.isEstado() == true) {
						System.out.println(prod);
					}
				}
				break;
			case 2:// realizar compra
				do {
					try {// control de ingreso de codigo
						System.out.print("Ingrese codigo del producto: ");
						codigoProducto = scanner.next();
						for (int i = 0; i < listaProductos.size(); i++) {
							producto = listaProductos.get(i);
							if (producto.getCodigo().equals(codigoProducto)) {
								listaCompra.add(producto); // agrego el producto a la lista de compras
								montoTotal += producto.getPrecioUnitario();
							}
						}
					} catch (Exception e) {
						System.out.println("Error: " + e.getMessage());
						codigoProducto = "";
						scanner.nextLine();
					}
					try {
						do {
							System.out.print("Desea cargar otro producto(S/N):  ");
							respuesta = scanner.next();
							if (respuesta.equalsIgnoreCase("S")) {
								carga = true;
							} else if (respuesta.equalsIgnoreCase("N")) {
								carga = false;
							} else {
								System.out.println("Respuesta no válida. Por favor, ingrese S o N.");
							}
						} while (!respuesta.equalsIgnoreCase("S") && !respuesta.equalsIgnoreCase("N"));
					} catch (Exception e) {
						System.out.println("Error: " + e.getMessage());
						respuesta = "";
						scanner.nextLine();
					}
				} while (carga == true);
				System.out.println("***Opciones de pago***");
				System.out.println("1-Pago efectivo");
				System.out.println("2-Pago con tarjeta");
				do {
					try {
						System.out.print("Ingrese opción: ");
						opcion = scanner.nextByte();
						if (opcion < 1 || opcion > 2) {
							opcion = 0;
							throw new IllegalArgumentException("Opcion invalida, debe elegir una opcion del 1 al 2");
						}
					} catch (Exception e) {
						System.out.println("Error: " + e.getMessage());
						scanner.nextLine();
						opcion = 0;
					}
				} while (opcion == 0);
				do {
					switch (opcion) {
					case 1:// pago efectivo
						PagoEfectivo pagoEfectivo = new PagoEfectivo();
						pagoEfectivo.setMontoPagado(montoTotal);
						pagoEfectivo.imprimirRecibo();
						break;
					case 2:// pago tarjeta
						PagoTarjeta pagoTarjeta = new PagoTarjeta();
						pagoTarjeta.setNumeroTarjeta(4021123454786010l);
						pagoTarjeta.setMontoPagado(montoTotal);
						pagoTarjeta.imprimirRecibo();
						break;
					default:
						System.out.println("Opción invalida");
						break;
					}
				} while (opcion != 1 && opcion != 2);
				break;
			case 3:
				System.out.println("Saliendo...");
				break;
			default:
				System.out.println("Opción invalida");
				break;
			}
		} while (opcion != 3);
		scanner.close();
	}
	// Método para cargar productos al ArrayList
    public static void cargarProductos(List<Producto> listaProductos) {
        listaProductos.add(new Producto("001", "Teléfono móvil", 500.0, Producto.OrigenFabricacion.ARGENTINA, Producto.Categoria.TELEFONIA, true));
        listaProductos.add(new Producto("002", "Laptop", 1000.0, Producto.OrigenFabricacion.CHINA, Producto.Categoria.INFORMATICA, true));
        listaProductos.add(new Producto("003", "Aspiradora", 200.0, Producto.OrigenFabricacion.URUGUAY, Producto.Categoria.ELECTROHOGAR, false));
        listaProductos.add(new Producto("004", "Tablet", 300.0, Producto.OrigenFabricacion.BRASIL, Producto.Categoria.INFORMATICA, true));
        listaProductos.add(new Producto("005", "Licuadora", 50.0, Producto.OrigenFabricacion.ARGENTINA, Producto.Categoria.ELECTROHOGAR, false));
        listaProductos.add(new Producto("006", "Martillo", 10.0, Producto.OrigenFabricacion.URUGUAY, Producto.Categoria.HERRAMIENTAS, true));
        listaProductos.add(new Producto("007", "Monitor", 200.0, Producto.OrigenFabricacion.CHINA, Producto.Categoria.INFORMATICA, false));
        listaProductos.add(new Producto("008", "Plancha", 30.0, Producto.OrigenFabricacion.ARGENTINA, Producto.Categoria.ELECTROHOGAR, true));
        listaProductos.add(new Producto("009", "Taladro", 80.0, Producto.OrigenFabricacion.URUGUAY, Producto.Categoria.HERRAMIENTAS, true));
        listaProductos.add(new Producto("010", "Silla", 70.0, Producto.OrigenFabricacion.BRASIL, Producto.Categoria.ELECTROHOGAR, false));
        listaProductos.add(new Producto("011", "Router", 50.0, Producto.OrigenFabricacion.CHINA, Producto.Categoria.INFORMATICA, true));
        listaProductos.add(new Producto("012", "Alicate", 15.0, Producto.OrigenFabricacion.ARGENTINA, Producto.Categoria.HERRAMIENTAS, true));
        listaProductos.add(new Producto("013", "Teclado", 30.0, Producto.OrigenFabricacion.CHINA, Producto.Categoria.INFORMATICA, true));
        listaProductos.add(new Producto("014", "Cocina", 400.0, Producto.OrigenFabricacion.ARGENTINA, Producto.Categoria.ELECTROHOGAR, true));
        listaProductos.add(new Producto("015", "Teléfono fijo", 40.0, Producto.OrigenFabricacion.URUGUAY, Producto.Categoria.TELEFONIA, true));
    }
}