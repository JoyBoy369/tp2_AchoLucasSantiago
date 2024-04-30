package ar.edu.unju.fi.ejercicio2.main;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import ar.edu.unju.fi.ejercicio2.constantes.Mes;
import ar.edu.unju.fi.ejercicio2.model.Efemeride;

public class Main {
    public static void main(String[] args) {
        ArrayList<Efemeride> efemerides = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        int opcion;
        do {
        	System.out.println("--------------------------------------");
            System.out.println("Menú de acciones:");
            System.out.println("1 – Crear efeméride");
            System.out.println("2 – Mostrar efemérides");
            System.out.println("3 – Eliminar efeméride");
            System.out.println("4 – Modificar efeméride");
            System.out.println("5 – Salir");
            System.out.println("--------------------------------------");
            System.out.print("Ingrese su opción: ");
           
            try {
                opcion = scanner.nextInt();
                switch (opcion) {
                    case 1:
                        crearEfemeride(efemerides, scanner);
                        break;
                    case 2:
                        mostrarEfemerides(efemerides);
                        break;
                    case 3:
                        eliminarEfemeride(efemerides, scanner);
                        break;
                    case 4:
                        modificarEfemeride(efemerides, scanner);
                        break;
                    case 5:
                        System.out.println("Saliendo...");
                        break;
                    default:
                    	System.out.println("--------------------------------------");
                        System.out.println("Opción inválida. Por favor, elija una opción válida.");
                        System.out.println("--------------------------------------");
                }
            } catch (InputMismatchException e) {
            	System.out.println("--------------------------------------");
                System.out.println("Por favor, ingrese un número válido.");
                System.out.println("--------------------------------------");
                scanner.nextLine();
                opcion = 0; 
            }
        } while (opcion != 5);
    }

    public static void crearEfemeride(ArrayList<Efemeride> efemerides, Scanner scanner) {
        System.out.println("Crear efemeride:");
        try {
            System.out.print("Ingrese el código: ");
            String codigo = scanner.next();
            System.out.print("Ingrese el número correspondiente al mes (1-12): ");
            int numMes = scanner.nextInt();
            while (numMes < 1 || numMes > 12) {
                System.out.println("Número de mes inválido. Ingrese un número entre 1 y 12.");
                System.out.println("-------------------------------------------------------");
                System.out.print("Ingrese el número correspondiente al mes (1-12): ");
                
                numMes = scanner.nextInt();
            }
            
            Mes mes = Mes.values()[numMes - 1];
            
            System.out.print("Ingrese el día: ");
            int dia = scanner.nextInt();
            System.out.print("Ingrese el detalle: ");
            String detalle = scanner.next();

            Efemeride nuevaEfemeride = new Efemeride(codigo, mes, dia, detalle);
            efemerides.add(nuevaEfemeride);
            System.out.println("Efeméride creada exitosamente.");
        } catch (InputMismatchException e) {
            System.out.println("Por favor, ingrese un valor válido para el mes y el día.");
            scanner.nextLine(); 
        }
    }

    public static void mostrarEfemerides(ArrayList<Efemeride> efemerides) {
    	System.out.println("--------------------------------------");
        System.out.println("Mostrar efemerides:");
        System.out.println("--------------------------------------");
        if (efemerides.isEmpty()) {
            System.out.println("No hay efemerides para mostrar.");
        } else {
            for (Efemeride efemeride : efemerides) {
                System.out.println(efemeride.getCodigo() + " - " + efemeride.getMes() + " " + efemeride.getDia() + ": " + efemeride.getDetalle());
            }
        }
    }

    public static void eliminarEfemeride(ArrayList<Efemeride> efemerides, Scanner scanner) {
        if (efemerides.isEmpty()) {
            System.out.println("No hay efemerides para eliminar.");
            return;
        }

        mostrarEfemerides(efemerides);
        System.out.print("Ingrese el código de la efeméride que desea eliminar: ");
        String codigo = scanner.next();
        boolean eliminada = false;
        for (Efemeride efemeride : efemerides) {
            if (efemeride.getCodigo().equals(codigo)) {
                efemerides.remove(efemeride);
                eliminada = true;
                System.out.println("Efeméride eliminada exitosamente.");
                break;
            }
        }
        if (!eliminada) {
            System.out.println("No se encontró ninguna efeméride con ese código.");
            System.out.println("--------------------------------------");
        }
    }

    public static void modificarEfemeride(ArrayList<Efemeride> efemerides, Scanner scanner) {
        if (efemerides.isEmpty()) {
            System.out.println("No hay efemérides para modificar.");
            System.out.println("--------------------------------------");
            return;
        }

        mostrarEfemerides(efemerides);
        System.out.print("Ingrese el código de la efeméride que desea modificar: ");
        String codigo = scanner.next();
        boolean encontrada = false;
        for (Efemeride efemeride : efemerides) {
            if (efemeride.getCodigo().equals(codigo)) {
                System.out.println("Modificando efeméride:");
                System.out.println("--------------------------------------");
                try {
                    System.out.print("Ingrese el nuevo código: ");
                    String nuevoCodigo = scanner.next();
                    System.out.print("Ingrese el número correspondiente al nuevo mes (1-12): ");
                    int numMes = scanner.nextInt();
                    while (numMes < 1 || numMes > 12) {
                        System.out.println("Número de mes inválido. Ingrese un número entre 1 y 12.");
                        System.out.println("-------------------------------------------------------");
                        System.out.print("Ingrese el número correspondiente al nuevo mes (1-12): ");
                        numMes = scanner.nextInt();
                    }
                    Mes nuevoMes = Mes.values()[numMes - 1];
                    System.out.print("Ingrese el nuevo día: ");
                    int nuevoDia = scanner.nextInt();
                    System.out.print("Ingrese el nuevo detalle: ");
                    String nuevoDetalle = scanner.next();

                    efemeride.setCodigo(nuevoCodigo);
                    efemeride.setMes(nuevoMes);
                    efemeride.setDia(nuevoDia);
                    efemeride.setDetalle(nuevoDetalle);

                    encontrada = true;
                    System.out.println("--------------------------------------");
                    System.out.println("Efeméride modificada exitosamente.");
                } catch (InputMismatchException e) {
                	System.out.println("--------------------------------------");
                    System.out.println("Por favor, ingrese un valor válido para el mes y el día.");
                    scanner.nextLine();
                }
                break;
            }
        }
        if (!encontrada) {
            System.out.println("No se encontró ninguna efeméride con ese código.");
        }
    }
}
