import java.util.LinkedList;
import java.util.Scanner;

public class subMenu {
    
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

    private String leerTexto(Scanner sc, String mensaje) {
        System.out.println(mensaje);
        String texto = "";
        while (texto.isEmpty()) {
            texto = sc.nextLine().trim();
        }
        return texto;
    }

    public int leerEntero(Scanner sc, String mensaje) {
        while (true) {
            String texto = leerTexto(sc, mensaje);
            try {
                return Integer.parseInt(texto);
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un numero entero valido");
            }
        }
    }

    private float leerDecimal(Scanner sc, String mensaje) {
        while (true) {
            String texto = leerTexto(sc, mensaje).replace(',', '.');
            try {
                return Float.parseFloat(texto);
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un numero valido");
            }
        }
    }

    private boolean confirmar(Scanner sc, String mensaje) {
        int opt = leerEntero(sc, mensaje + " 1)Si 2)No");
        return opt == 1;
    }

    // =====================================================================
    //  GESTION DE CLIENTES
    // =====================================================================

    private Cliente buscarClientePorCedula(LinkedList<Cliente> lcl, String cedula) {
        for (Cliente c : lcl) {
            if (c.getCedula().equalsIgnoreCase(cedula)) {
                return c;
            }
        }
        return null;
    }

    private void mostrarCliente(Cliente c) {
        System.out.println("Cedula: " + c.getCedula());
        System.out.println("Nombre: " + c.getNombre());
        System.out.println("Apellido: " + c.getApellido());
        System.out.println("Telefono: " + c.getTelefono());
        System.out.println("Direccion: " + c.getDireccion());
        System.out.println("Licencia: " + c.getLicencia());
    }

    public LinkedList<Cliente> registrarCliente(LinkedList<Cliente> lcl, Scanner sc) {
        String cedula = leerTexto(sc, "Ingrese la cedula del cliente");

        if (buscarClientePorCedula(lcl, cedula) != null) {
            System.out.println("Ya existe un cliente con esa cedula");
            return lcl;
        }

        Cliente c = new Cliente();
        c.setCedula(cedula);
        c.setNombre(leerTexto(sc, "Ingrese el nombre del cliente"));
        c.setApellido(leerTexto(sc, "Ingrese el apellido del cliente"));
        c.setTelefono(leerTexto(sc, "Ingrese el telefono del cliente"));
        c.setDireccion(leerTexto(sc, "Ingrese la direccion del cliente"));
        c.setLicencia(leerTexto(sc, "Ingrese la licencia de conduccion del cliente"));
        lcl.add(c);

        System.out.println("Cliente registrado exitosamente");
        return lcl;
    }

    public LinkedList<Cliente> modificarCliente(LinkedList<Cliente> lcl, Scanner sc) {
        String cedula = leerTexto(sc, "Ingrese la cedula del cliente que desea modificar");
        Cliente c = buscarClientePorCedula(lcl, cedula);

        if (c == null) {
            System.out.println("Cliente no encontrado");
            return lcl;
        }

        System.out.println("Ingrese los nuevos datos (la cedula no se puede modificar)");
        c.setNombre(leerTexto(sc, "Ingrese el nombre del cliente"));
        c.setApellido(leerTexto(sc, "Ingrese el apellido del cliente"));
        c.setTelefono(leerTexto(sc, "Ingrese el telefono del cliente"));
        c.setDireccion(leerTexto(sc, "Ingrese la direccion del cliente"));
        c.setLicencia(leerTexto(sc, "Ingrese la licencia de conduccion del cliente"));

        System.out.println("Cliente modificado exitosamente");
        return lcl;
    }

    public LinkedList<Cliente> eliminarCliente(LinkedList<Cliente> lcl, LinkedList<ContratoRenting> lc, Scanner sc) {
        String cedula = leerTexto(sc, "Ingrese la cedula del cliente que desea eliminar");
        Cliente c = buscarClientePorCedula(lcl, cedula);

        if (c == null) {
            System.out.println("Cliente no encontrado");
            return lcl;
        }

        for (ContratoRenting ct : lc) {
            if (ct.getCedulaCliente().equalsIgnoreCase(cedula) && ct.getEstado() == 1) {
                System.out.println("No se puede eliminar: el cliente tiene un contrato activo");
                return lcl;
            }
        }

        if (confirmar(sc, "Seguro que desea eliminar a " + c.getNombre() + " " + c.getApellido() + "?")) {
            lcl.remove(c);
            System.out.println("Cliente eliminado exitosamente");
        } else {
            System.out.println("Operacion cancelada");
        }
        return lcl;
    }

    public void buscarCliente(LinkedList<Cliente> lcl, Scanner sc) {
        String cedula = leerTexto(sc, "Ingrese la cedula del cliente que desea consultar");
        Cliente c = buscarClientePorCedula(lcl, cedula);

        if (c == null) {
            System.out.println("Cliente no encontrado");
        } else {
            mostrarCliente(c);
        }
    }

    // =====================================================================
    //  GESTION DE VEHICULOS
    // =====================================================================

    private Vehiculo buscarVehiculoPorPlaca(LinkedList<Vehiculo> lv, String placa) {
        for (Vehiculo v : lv) {
            if (v.getPlaca().equalsIgnoreCase(placa)) {
                return v;
            }
        }
        return null;
    }

    private void mostrarVehiculo(Vehiculo v) {
        System.out.println("Placa: " + v.getPlaca());
        System.out.println("Marca: " + v.getMarca());
        System.out.println("Modelo: " + v.getModelo());
        System.out.println("Precio diario: " + v.getPrecioDiario());
        System.out.println("Estado: " + v.getEstado());

        if (v instanceof CarroSedan) {
            CarroSedan s = (CarroSedan) v;
            System.out.println("Tipo: Carro Sedan");
            System.out.println("Tipo de combustible: " + s.getTipoCombustible());
            System.out.println("Transmision: " + s.getTransmision());
        } else if (v instanceof CamionetaSUV) {
            CamionetaSUV s = (CamionetaSUV) v;
            System.out.println("Tipo: Camioneta / SUV");
            System.out.println("Traccion: " + s.getTraccion());
            System.out.println("Capacidad del maletero: " + s.getCapacidadMaletero());
        }
    }

    public LinkedList<Vehiculo> registrarVehiculo(LinkedList<Vehiculo> lv, Scanner sc) {
        String placa = leerTexto(sc, "Ingrese la placa del vehiculo");

        if (buscarVehiculoPorPlaca(lv, placa) != null) {
            System.out.println("Ya existe un vehiculo con esa placa");
            return lv;
        }

        int tipo = 0;
        while (tipo != 1 && tipo != 2) {
            tipo = leerEntero(sc, "Tipo de vehiculo: 1)Carro Sedan 2)Camioneta/SUV");
            if (tipo != 1 && tipo != 2) {
                System.out.println("Opcion no valida");
            }
        }

        Vehiculo v;
        if (tipo == 1) {
            CarroSedan s = new CarroSedan();
            s.setTipoCombustible(leerTexto(sc, "Ingrese el tipo de combustible"));
            s.setTransmision(leerTexto(sc, "Ingrese la transmision (manual/automatica)"));
            v = s;
        } else {
            CamionetaSUV s = new CamionetaSUV();
            s.setTraccion(leerTexto(sc, "Ingrese la traccion (4x2/4x4)"));
            s.setCapacidadMaletero(leerDecimal(sc, "Ingrese la capacidad del maletero (litros)"));
            v = s;
        }

        v.setPlaca(placa);
        v.setMarca(leerTexto(sc, "Ingrese la marca del vehiculo"));
        v.setModelo(leerEntero(sc, "Ingrese el modelo (anio) del vehiculo"));
        v.setPrecioDiario(leerDecimal(sc, "Ingrese el precio diario del alquiler"));
        v.setEstado("disponible");
        lv.add(v);

        System.out.println("Vehiculo registrado exitosamente");
        return lv;
    }

    public LinkedList<Vehiculo> modificarVehiculo(LinkedList<Vehiculo> lv, Scanner sc) {
        String placa = leerTexto(sc, "Ingrese la placa del vehiculo que desea modificar");
        Vehiculo v = buscarVehiculoPorPlaca(lv, placa);

        if (v == null) {
            System.out.println("Vehiculo no encontrado");
            return lv;
        }

        System.out.println("Ingrese los nuevos datos (la placa y el estado no se pueden modificar)");
        v.setMarca(leerTexto(sc, "Ingrese la marca del vehiculo"));
        v.setModelo(leerEntero(sc, "Ingrese el modelo (anio) del vehiculo"));
        v.setPrecioDiario(leerDecimal(sc, "Ingrese el precio diario del alquiler"));

        if (v instanceof CarroSedan) {
            CarroSedan s = (CarroSedan) v;
            s.setTipoCombustible(leerTexto(sc, "Ingrese el tipo de combustible"));
            s.setTransmision(leerTexto(sc, "Ingrese la transmision (manual/automatica)"));
        } else if (v instanceof CamionetaSUV) {
            CamionetaSUV s = (CamionetaSUV) v;
            s.setTraccion(leerTexto(sc, "Ingrese la traccion (4x2/4x4)"));
            s.setCapacidadMaletero(leerDecimal(sc, "Ingrese la capacidad del maletero (litros)"));
        }

        System.out.println("Vehiculo modificado exitosamente");
        return lv;
    }

    public LinkedList<Vehiculo> eliminarVehiculo(LinkedList<Vehiculo> lv, Scanner sc) {
        String placa = leerTexto(sc, "Ingrese la placa del vehiculo que desea eliminar");
        Vehiculo v = buscarVehiculoPorPlaca(lv, placa);

        if (v == null) {
            System.out.println("Vehiculo no encontrado");
            return lv;
        }

        if (v.getEstado().equalsIgnoreCase("alquilado")) {
            System.out.println("No se puede eliminar: el vehiculo se encuentra alquilado");
            return lv;
        }

        if (confirmar(sc, "Seguro que desea eliminar el vehiculo " + v.getPlaca() + "?")) {
            lv.remove(v);
            System.out.println("Vehiculo eliminado exitosamente");
        } else {
            System.out.println("Operacion cancelada");
        }
        return lv;
    }

    public void buscarVehiculo(LinkedList<Vehiculo> lv, Scanner sc) {
        String placa = leerTexto(sc, "Ingrese la placa del vehiculo que desea consultar");
        Vehiculo v = buscarVehiculoPorPlaca(lv, placa);

        if (v == null) {
            System.out.println("Vehiculo no encontrado");
        } else {
            mostrarVehiculo(v);
        }
    }

    // =====================================================================
    //  INFORME GENERAL
    // =====================================================================

    public void imprimirInforme(LinkedList<Cliente> lcl, LinkedList<Vehiculo> lv, LinkedList<ContratoRenting> lc) {
        System.out.println("\n========== INFORME GENERAL ==========");

        System.out.println("\n--- CLIENTES (" + lcl.size() + ") ---");
        if (lcl.isEmpty()) {
            System.out.println("No hay clientes registrados");
        }
        for (Cliente c : lcl) {
            System.out.println(c.getCedula() + " | " + c.getNombre() + " " + c.getApellido()
                    + " | Tel: " + c.getTelefono() + " | Licencia: " + c.getLicencia());
        }

        int disponibles = 0;
        int alquilados = 0;
        System.out.println("\n--- VEHICULOS (" + lv.size() + ") ---");
        if (lv.isEmpty()) {
            System.out.println("No hay vehiculos registrados");
        }
        for (Vehiculo v : lv) {
            String tipo = (v instanceof CarroSedan) ? "Sedan" : (v instanceof CamionetaSUV) ? "Camioneta/SUV" : "Vehiculo";
            System.out.println(v.getPlaca() + " | " + tipo + " | " + v.getMarca() + " " + v.getModelo()
                    + " | $" + v.getPrecioDiario() + "/dia | " + v.getEstado());
            if (v.getEstado().equalsIgnoreCase("alquilado")) {
                alquilados++;
            } else {
                disponibles++;
            }
        }

        int activos = 0;
        int finalizados = 0;
        float totalActivos = 0;
        float totalFinalizados = 0;
        System.out.println("\n--- CONTRATOS (" + lc.size() + ") ---");
        if (lc.isEmpty()) {
            System.out.println("No hay contratos registrados");
        }
        for (ContratoRenting ct : lc) {
            String estado = (ct.getEstado() == 1) ? "Activo" : "Finalizado";
            System.out.println(ct.getIdContrato() + " | Cliente: " + ct.getCedulaCliente()
                    + " | Vehiculo: " + ct.getPlacaVehiculo()
                    + " | " + ct.getFechaInicio() + " a " + ct.getFechaFin()
                    + " | " + ct.getTotalDias() + " dias | $" + ct.getValorTotal() + " | " + estado);
            if (ct.getEstado() == 1) {
                activos++;
                totalActivos += ct.getValorTotal();
            } else {
                finalizados++;
                totalFinalizados += ct.getValorTotal();
            }
        }

        System.out.println("\n--- RESUMEN ---");
        System.out.println("Total clientes: " + lcl.size());
        System.out.println("Vehiculos disponibles: " + disponibles + " | alquilados: " + alquilados);
        System.out.println("Contratos activos: " + activos + " | finalizados: " + finalizados);
        System.out.println("Valor de contratos activos: $" + totalActivos);
        System.out.println("Valor de contratos finalizados: $" + totalFinalizados);
        System.out.println("=====================================\n");
    }

}