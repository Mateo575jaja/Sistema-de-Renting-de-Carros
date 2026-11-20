import java.util.LinkedList;
import java.util.Scanner;

public class Menu {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        metodos m = new metodos();

        LinkedList<Cliente> lcl = new LinkedList<>();
        LinkedList<Vehiculo> lv = new LinkedList<>();
        LinkedList<ContratoRenting> lc = new LinkedList<>();

        int opcion;
        do {
            System.out.println("\n===== SISTEMA DE RENTING DE CARROS =====");
            System.out.println("1. Gestion de clientes");
            System.out.println("2. Gestion de vehiculos");
            System.out.println("3. Gestion de contratos de renting");
            System.out.println("4. Imprimir informe general");
            System.out.println("5. Salir del sistema");
            opcion = m.leerEntero(sc, "Seleccione una opcion");

            switch (opcion) {
                case 1:
                    menuClientes(m, sc, lcl, lc);
                    break;
                case 2:
                    menuVehiculos(m, sc, lv, lc);
                    break;
                case 3:
                    menuContratos(m, sc, lc, lv, lcl);
                    break;
                case 4:
                    m.imprimirInforme(lcl, lv, lc);
                    break;
                case 5:
                    System.out.println("Gracias por usar el sistema. Hasta pronto!");
                    break;
                default:
                    System.out.println("Opcion no valida, intente de nuevo");
            }
        } while (opcion != 5);

        sc.close();
    }

    private static void menuClientes(metodos m, Scanner sc, LinkedList<Cliente> lcl, LinkedList<ContratoRenting> lc) {
        int opcion;
        do {
            System.out.println("\n--- GESTION DE CLIENTES ---");
            System.out.println("1. Registrar cliente");
            System.out.println("2. Modificar cliente");
            System.out.println("3. Eliminar cliente");
            System.out.println("4. Buscar cliente");
            System.out.println("5. Volver al menu principal");
            opcion = m.leerEntero(sc, "Seleccione una opcion");

            switch (opcion) {
                case 1:
                    m.registrarCliente(lcl, sc);
                    break;
                case 2:
                    m.modificarCliente(lcl, sc);
                    break;
                case 3:
                    m.eliminarCliente(lcl, lc, sc);
                    break;
                case 4:
                    m.buscarCliente(lcl, sc);
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Opcion no valida, intente de nuevo");
            }
        } while (opcion != 5);
    }

    private static void menuVehiculos(metodos m, Scanner sc, LinkedList<Vehiculo> lv, LinkedList<ContratoRenting> lc) {
        int opcion;
        do {
            System.out.println("\n--- GESTION DE VEHICULOS ---");
            System.out.println("1. Registrar vehiculo");
            System.out.println("2. Modificar vehiculo");
            System.out.println("3. Eliminar vehiculo");
            System.out.println("4. Buscar vehiculo");
            System.out.println("5. Volver al menu principal");
            opcion = m.leerEntero(sc, "Seleccione una opcion");

            switch (opcion) {
                case 1:
                    m.registrarVehiculo(lv, sc);
                    break;
                case 2:
                    m.modificarVehiculo(lv, sc);
                    break;
                case 3:
                    m.eliminarVehiculo(lv, sc);
                    break;
                case 4:
                    m.buscarVehiculo(lv, sc);
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Opcion no valida, intente de nuevo");
            }
        } while (opcion != 5);
    }

    private static void menuContratos(metodos m, Scanner sc, LinkedList<ContratoRenting> lc,
            LinkedList<Vehiculo> lv, LinkedList<Cliente> lcl) {
        int opcion;
        do {
            System.out.println("\n--- GESTION DE CONTRATOS DE RENTING ---");
            System.out.println("1. Registrar nuevo contrato");
            System.out.println("2. Modificar contrato");
            System.out.println("3. Finalizar contrato (devolucion)");
            System.out.println("4. Buscar contrato");
            System.out.println("5. Volver al menu principal");
            opcion = m.leerEntero(sc, "Seleccione una opcion");

            switch (opcion) {
                case 1:
                    m.registrarContrato(lc, lv, lcl, sc);
                    break;
                case 2:
                    m.modificarContrato(lc, sc);
                    break;
                case 3:
                    m.finalizarContrato(lc, lv, sc);
                    break;
                case 4:
                    System.out.println(m.buscarContrato(lc, sc));
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Opcion no valida, intente de nuevo");
            }
        } while (opcion != 5);
    }
}