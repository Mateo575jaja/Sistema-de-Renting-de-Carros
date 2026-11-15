import java.util.LinkedList;
import java.util.Scanner;

public class metodos {
    
    public LinkedList<ContratoRenting> registrarContrato(LinkedList<ContratoRenting> lc,LinkedList<Vehiculo> lv,LinkedList<Cliente> lcl,Scanner sc){
        boolean continuar = true;

        while (continuar) {
            ContratoRenting c = new ContratoRenting();
            System.out.println("Ingrese la cedula del cliente");
            String cedula = sc.next();

            int encontrado1=0;
            for (Cliente cl : lcl) {
                if (cl.getCedula().equalsIgnoreCase(cedula)) {
                    encontrado1=1;
                    for (ContratoRenting ct : lc) {
                        if (ct.getCedulaCliente().equalsIgnoreCase(cedula)) {
                        System.out.println("El cliente ya tiene un vehiculo alquilado");
                        return lc;
                        }
                    }
                }
            }

            if (encontrado1==0) {
                System.out.println("Este cliente no fue encontrado");
                return lc;
            }

            c.setCedulaCliente(cedula);
            System.out.println("Ingrese la placa del vehiculo");
            String placa = sc.next();

            int encontrado = 0;
            for (Vehiculo v : lv) {
                if(v.getPlaca().equalsIgnoreCase(placa)){
                    encontrado=1;
                    if(v.getEstado().equalsIgnoreCase("alquilado")){
                        System.out.println("El vehiculo se encuentra alquilado");
                        return lc;
                    }
                    else{
                        v.setEstado("alquilado");
                    }
                }
            }
            if (encontrado==0) {
                System.out.println("Esta placa no fue encontrada");
                return lc;
            }

            c.setPlacaVehiculo(placa);
            System.out.println("Ingrese el ID del contrato");
            c.setIdContrato(sc.next());
            System.out.println("Ingrese la fecha de inicio del alquiler");
            c.setFechaInicio(sc.next());
            System.out.println("Ingrese la fecha de finalizacion del alquiler");
            c.setFechaFin(sc.next());
            System.out.println("Ingrese el total de dias de alquiler");
            c.setTotalDias(sc.nextInt());
            System.out.println("Ingrese el valor total del alquiler");
            c.setValorTotal(sc.nextFloat());
            c.setEstado(1);
            lc.add(c);

            System.out.println("Desea agregar otro registro 1)Si 2)No");
            int opt = sc.nextInt();

            if (opt == 2) {
                continuar=false;
            }

        }

        return lc;
    }

    public String buscarContrato (LinkedList<ContratoRenting> lc,Scanner sc){
        System.out.println("Ingrese el ID del contrato que desea consultar");
        String ID = sc.next();

        int encontrado=0;
        String mensaje="";

        for (ContratoRenting ct : lc) {
            if (ct.getIdContrato().equalsIgnoreCase(ID)) {
                encontrado=1;
                mensaje= "Contrato encontrado";
                System.out.println("ID Contrato: " +ct.getIdContrato() );
                System.out.println("Cedula cliente: " + ct.getCedulaCliente());
                System.out.println("Placa vehiculo: " + ct.getPlacaVehiculo());
                System.out.println("Fecha inicio: " + ct.getFechaInicio());
                System.out.println("Fecha finalizacion: " + ct.getFechaFin());
                System.out.println("Total dias: " + ct.getTotalDias());
                System.out.println("Valor total: " + ct.getValorTotal());
                if (ct.getEstado()==1) {
                    System.out.println("Estado: Activo");
                }
                else{
                    System.out.println("Estado: Finalizado");
                }
            }
        }

        if (encontrado==0) {
            mensaje = "Contrato no encontrado";
        }

        return mensaje;

    }

    
    public LinkedList<ContratoRenting> modificarContrato(LinkedList<ContratoRenting> lc,Scanner sc){
        System.out.println("Ingrese el ID del contrato que desea modificar");
        String ID = sc.next();
        int encontrado=0;
        for (ContratoRenting ct : lc) {
            if (ct.getIdContrato().equalsIgnoreCase(ID)) {
                encontrado=1;
                System.out.println("Ingrese la fecha de inicio del alquiler");
                ct.setFechaInicio(sc.next());
                System.out.println("Ingrese la fecha de finalizacion del alquiler");
                ct.setFechaFin(sc.next());
                System.out.println("Ingrese el total de dias de alquiler");
                ct.setTotalDias(sc.nextInt());
                System.out.println("Ingrese el valor total del alquiler");
                ct.setValorTotal(sc.nextFloat());
                System.out.println("Contrato modificado exitosamente");
            }
        }

        if (encontrado==0) {
            System.out.println("Contrato no encontrado");
        }

        return lc;
    }
    


    public LinkedList<ContratoRenting> finalizarContrato(LinkedList<ContratoRenting> lc,LinkedList<Vehiculo> lv,Scanner sc){
        System.out.println("Ingrese el ID del contrato que desea finalizar");
        String ID = sc.next();
        int encontrado=0;

        for (ContratoRenting ct : lc) {
            if (ct.getIdContrato().equalsIgnoreCase(ID)) {
                encontrado=1;

                if(ct.getEstado()!=0){
                    ct.setEstado(0);
                    for (Vehiculo v : lv) {
                        if (v.getPlaca().equalsIgnoreCase(ct.getPlacaVehiculo())) {
                            v.setEstado("disponible");
                        }
                    }
                    System.out.println("Contrato finalizado exitosamente");
                }
                else{
                    System.out.println("Este contrato esta finalizado");
                }
            }
        }

        if (encontrado==0) {
            System.out.println("Contrato no encontrado");
        }

        return lc;
    }
public boolean validar() {
        for (int i = 0; i < placa.length(); i++) {
            if (!Character.isLetterOrDigit(placa.charAt(i))) {
                return false;
            }
        }

        for (int i = 0; i < marca.length(); i++) {
            if (!Character.isLetter(marca.charAt(i))) {
                return false;
            }
        }

        if (precioDiario <= 0) {
            return false;
        }

        if (!estado.equalsIgnoreCase("disponible") && !estado.equalsIgnoreCase("alquilado")) {
            return false;
        }

        return true;
    }
}


