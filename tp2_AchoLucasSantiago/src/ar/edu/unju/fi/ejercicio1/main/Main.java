package ar.edu.unju.fi.ejercicio1.main;

import ar.edu.unju.fi.ejercicio1.model.Producto;
import ar.edu.unju.fi.ejercicio1.model.Producto.Categoria;
import ar.edu.unju.fi.ejercicio1.model.Producto.OrigenFabricacion;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
 public static void main(String[] args) {
	 
     ArrayList<Producto> productos = new ArrayList<>();
     Scanner scanner = new Scanner(System.in);
     int opcion;

     do {
    	 System.out.println("--------------------------------------");
         System.out.println("Menú de opciones:");
         System.out.println("1 - Crear Producto");
         System.out.println("2 - Mostrar productos");
         System.out.println("3 - Modificar producto");
         System.out.println("4 - Salir");
         System.out.println("--------------------------------------");
         System.out.print("Seleccione una opción: ");

         try {
             opcion = scanner.nextInt();
             scanner.nextLine();

             switch (opcion) {
                 case 1:
                     crearProducto(productos, scanner);
                     break;
                 case 2:
                     mostrarProductos(productos);
                     break;
                 case 3:
                     modificarProducto(productos, scanner);
                     break;
                 case 4:
                     System.out.println("Saliendo...");
                     break;
                 default:
                     System.out.println("Opción inválida, por favor seleccione una opción del 1 al 4.");
             }
         } catch (InputMismatchException e) {
             System.out.println("Entrada inválida. Por favor ingrese un número.");
             scanner.nextLine();
             opcion = 0;
         }
     } while (opcion != 4);
     
     scanner.close();
 }

 private static void crearProducto(ArrayList<Producto> productos, Scanner scanner) {
	 
	 
     // Crear un nuevo producto //
	 System.out.println("Ingrese los detalles del nuevo producto:");

	    System.out.print("Código: ");
	    String codigo = scanner.nextLine();

	    System.out.print("Descripción: ");
	    String descripcion = scanner.nextLine();

	    double precioUnitario;
	    
	    while (true) {
	        try {
	            System.out.print("Precio unitario: ");
	            precioUnitario = scanner.nextDouble();
	            scanner.nextLine(); // Limpiar el buffer de entrada
	            break; // Salir del bucle si la entrada es válida
	        } catch (InputMismatchException e) {
	            System.out.println("Error: Por favor ingrese un número válido para el precio.");
	            scanner.nextLine(); // Limpiar el buffer de entrada
	        }
	    }
	    
	    // Seleccion de origen //
	    		OrigenFabricacion origenFabricacion = null;	    
	    		 while (origenFabricacion == null) {
	    			System.out.println("Seleccione el origen de fabricación:");
	 			    System.out.println("1 - Argentina");
	 			    System.out.println("2 - China");
	 			    System.out.println("3 - Brasil");
	 			    System.out.println("4 - Uruguay");
	 			    String opcionOrigen = scanner.nextLine();
	 			    
	 			    
	 			    switch (opcionOrigen) {
	 		        case "1":
	 		            origenFabricacion = OrigenFabricacion.ARGENTINA;
	 		            break;
	 		        case "2":
	 		            origenFabricacion = OrigenFabricacion.CHINA;
	 		            break;
	 		        case "3":
	 		            origenFabricacion = OrigenFabricacion.BRASIL;
	 		            break;
	 		        case "4":
	 		            origenFabricacion = OrigenFabricacion.URUGUAY;
	 		            break;
	 		        default:
	 		            System.out.println("Opción inválida, Eliga un pais de la lista");
	 		           
	 			    }
	    		 }
	    		 
	   	 // Seleccion de Categoria //
	    	Categoria categoria = null;
	    	
			while(categoria==null) {    
		    System.out.println("Seleccione la categoría:");
		    System.out.println("1 - Telefonía");
		    System.out.println("2 - Informática");
		    System.out.println("3 - Electro hogar");
		    System.out.println("4 - Herramientas");
		    
		    String opcionCategoria = scanner.nextLine();
		
		  
		    switch (opcionCategoria) {
		        case "1":
		            categoria = Categoria.TELEFONIA;
		            break;
		        case "2":
		            categoria = Categoria.INFORMATICA;
		            break;
		        case "3":
		            categoria = Categoria.ELECTROHOGAR;
		            break;
		        case "4":
		            categoria = Categoria.HERRAMIENTAS;
		            break;
		        default:
		            System.out.println("Opción inválida, Eliga una opcion de la lista");
		            
		    	}
			}
	 // Crear producto y agregarlo a la lista //
	    Producto nuevoProducto = new Producto(codigo, descripcion, precioUnitario, origenFabricacion, categoria);
	    productos.add(nuevoProducto);
	    System.out.println("Producto guardado :3");
	    
	

 }

 private static void mostrarProductos(ArrayList<Producto> productos) {
	    if (productos.isEmpty()) {
	        System.out.println("No hay productos para mostrar.");
	    } else {
	        System.out.println("Lista de productos:");
	        for (int i = 0; i < productos.size(); i++) {
	            Producto producto = productos.get(i);
	            System.out.println("--------------------------------------");
	            System.out.println("Producto " + (i + 1) + ":");
	            System.out.println("Código: " + producto.getCodigo());
	            System.out.println("Descripción: " + producto.getDescripcion());
	            System.out.println("Precio unitario: " + producto.getPrecioUnitario());
	            System.out.println("Origen de fabricación: " + producto.getOrigenFabricacion());
	            System.out.println("Categoría: " + producto.getCategoria());
	            System.out.println("--------------------------------------");
	        }
	    }
	}

 private static void modificarProducto(ArrayList<Producto> productos, Scanner scanner) {
	    if (productos.isEmpty()) {
	        System.out.println("No hay productos para modificar.");
	    } else {
	    	
	        // Mostrar la lista de productos para que el usuario seleccione uno
	        mostrarProductos(productos);

	        System.out.print("Seleccione el número del producto que desea modificar: ");
	        int indiceProducto = scanner.nextInt();
	        scanner.nextLine(); // Limpiar el buffer de entrada

	        // Verificar si el índice seleccionado es válido
	        if (indiceProducto < 1 || indiceProducto > productos.size()) {
	            System.out.println("Índice de producto inválido.");
	            return;
	        }

	        // Obtener el producto seleccionado
	        Producto producto = productos.get(indiceProducto - 1);

	        // Mostrar los detalles del producto seleccionado
	        System.out.println("--------------------------------------");
	        System.out.println("Detalles del producto seleccionado:");
	        System.out.println("Código: " + producto.getCodigo());
	        System.out.println("Descripción: " + producto.getDescripcion());
	        System.out.println("Precio unitario: " + producto.getPrecioUnitario());
	        System.out.println("Origen de fabricación: " + producto.getOrigenFabricacion());
	        System.out.println("Categoría: " + producto.getCategoria());
	        System.out.println("--------------------------------------");

	        // Mostrar las opciones de modificación al usuario
	        System.out.println("--------------------------------------");
	        System.out.println("Seleccione qué atributo desea modificar:");
	        System.out.println("1 - Descripción");
	        System.out.println("2 - Precio unitario");
	        System.out.println("3 - Origen de fabricación");
	        System.out.println("4 - Categoría");
	        System.out.println("--------------------------------------");

	        int opcionModificacion = scanner.nextInt();
	        scanner.nextLine();

	        switch (opcionModificacion) {
	            case 1:
	                System.out.print("Ingrese la nueva descripción: ");
	                String nuevaDescripcion = scanner.nextLine();
	                producto.setDescripcion(nuevaDescripcion);
	                break;
	            case 2:
	                double nuevoPrecioUnitario;
	                while (true) {
	                    try {
	                        System.out.print("Ingrese el nuevo precio unitario: ");
	                        nuevoPrecioUnitario = scanner.nextDouble();
	                        scanner.nextLine(); 
	                        break; // Salir del bucle si la entrada es válida
	                    } catch (InputMismatchException e) {
	                        System.out.println("Error: Por favor ingrese un número válido para el precio.");
	                        scanner.nextLine();
	                    }
	                }
	                producto.setPrecioUnitario(nuevoPrecioUnitario);
	                break;
	            case 3:
	                System.out.println("Seleccione el nuevo origen de fabricación:");
	                System.out.println("1 - Argentina");
	                System.out.println("2 - China");
	                System.out.println("3 - Brasil");
	                System.out.println("4 - Uruguay");
	                int opcionOrigen = scanner.nextInt();
	                scanner.nextLine();
	                OrigenFabricacion nuevoOrigenFabricacion = null;
	                switch (opcionOrigen) {
	                    case 1:
	                        nuevoOrigenFabricacion = OrigenFabricacion.ARGENTINA;
	                        break;
	                    case 2:
	                        nuevoOrigenFabricacion = OrigenFabricacion.CHINA;
	                        break;
	                    case 3:
	                        nuevoOrigenFabricacion = OrigenFabricacion.BRASIL;
	                        break;
	                    case 4:
	                        nuevoOrigenFabricacion = OrigenFabricacion.URUGUAY;
	                        break;
	                    default:
	                        System.out.println("Opción inválida, no se modificará el origen de fabricación.");
	                        break;
	                }
	                if (nuevoOrigenFabricacion != null) {
	                    producto.setOrigenFabricacion(nuevoOrigenFabricacion);
	                }
	                break;
	            case 4:
	                System.out.println("Seleccione la nueva categoría:");
	                System.out.println("1 - Telefonía");
	                System.out.println("2 - Informática");
	                System.out.println("3 - Electro hogar");
	                System.out.println("4 - Herramientas");
	                int opcionCategoria = scanner.nextInt();
	                scanner.nextLine();
	                Categoria nuevaCategoria = null;
	                switch (opcionCategoria) {
	                    case 1:
	                        nuevaCategoria = Categoria.TELEFONIA;
	                        break;
	                    case 2:
	                        nuevaCategoria = Categoria.INFORMATICA;
	                        break;
	                    case 3:
	                        nuevaCategoria = Categoria.ELECTROHOGAR;
	                        break;
	                    case 4:
	                        nuevaCategoria = Categoria.HERRAMIENTAS;
	                        break;
	                    default:
	                        System.out.println("Opción inválida, no se modificará la categoría.");
	                        break;
	                }
	                if (nuevaCategoria != null) {
	                    producto.setCategoria(nuevaCategoria);
	                }
	                break;
	            default:
	                System.out.println("Opción inválida, no se realizarán modificaciones.");
	                break;
	        }

	        System.out.println("Operacion Finalizada");
	    }
	}

}
