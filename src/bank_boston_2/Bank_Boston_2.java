
package bank_boston_2;
import Usuarios.Clientes;
import Tarjetas.Cuenta;
import Tarjetas.Cuenta_Ahorro;
import Tarjetas.Cuenta_Corriente;
import Tarjetas.Cuenta_Credito;
import java.util.Scanner;
import java.util.ArrayList;



public class Bank_Boston_2 {
static ArrayList<Clientes> Clienteslist = new ArrayList<>();
static ArrayList<Cuenta> Cuentaslist = new ArrayList<>();
    


public static void Menu(){                                                  
    Scanner Teclado=new Scanner(System.in);
    int opciones=0;
        do{
            System.out.println("1.- Creacion Del Cliente");
            System.out.println("2.- Ver Datos Del Cliente");
            System.out.println("3.- Creacion De Cuentas");
            System.out.println("4.- Depositar");
            System.out.println("5.- Girar");
            System.out.println("6.- Consultar saldo");
            System.out.println("7.- Salir");
            opciones=Teclado.nextInt();
                switch (opciones){
                case 1:
                    Registro_Del_Cliente(Teclado);
                    break;
                case 2:
                    Ver_Datos_Del_Cliente(Teclado);
                    break;
                case 3:
                    Creacion_de_cuentas(Teclado);
                    break;
                case 4:
                    Depositar(Teclado);
                    break;
                case 5:
                    Girar(Teclado);
                    break;
                case 6:
                    ConsultarSaldo(Teclado);
                    break;
                case 7:
                    System.out.println("!! Hasta luego que tengas un buen dia !!");
                    break;
                default:
                    System.out.println("Opcion invalida");
                    System.out.println("");
                    break;
                    }
        } while(opciones<1||opciones>7);                                        
        
    }

public static void Registro_Del_Cliente(Scanner Teclado){                        
                                                                     
        String Rut;
        String Nombre;
        String Apellido;
    
do {
    System.out.println("Ingrese su RUT (EJ: 12.345.678-9)");
    Rut= Teclado.next();
    Teclado.nextLine();

    if (!Clientes.validarRut(Rut)) {                                            
        System.out.println("El RUT ingresado es invalido. Por favor, ingrese un RUT valido.");
        System.out.println("");
    }
} while (!Clientes.validarRut(Rut));

    boolean nombreValido = false;
do {
        System.out.println("Ingrese su Nombre");
        Nombre = Teclado.nextLine();
        nombreValido = Clientes.validarNombre(Nombre);
    if (!nombreValido) {
        System.out.println("El nombre no puede estar vacio. Por favor, intentelo de nuevo.");
        System.out.println("");
    }
} while (!nombreValido);

    boolean apellidoValido = false;

do {
        System.out.println("Ingrese su Apellido ");
        Apellido = Teclado.nextLine();
        apellidoValido = Clientes.validarApellido(Apellido);
    if (!apellidoValido) {
        System.out.println("El apellido no puede estar vacio. Por favor, intentelo de nuevo.");
        System.out.println("");
    }
} while (!apellidoValido);

    Clientes clienteNuevo = new Clientes(Rut, Nombre, Apellido);
    Clienteslist.add(clienteNuevo);                                           
    System.out.println("Cliente registrado exitosamente");
    System.out.println("");
    Menu();                                                                     
}

public static void Ver_Datos_Del_Cliente(Scanner Teclado) {
    System.out.println("Ingrese el RUT del cliente:");
    String rutBusqueda = Teclado.next();
    boolean encontrado = false;

    for (Clientes cliente : Clienteslist) {
        if (cliente.getRut().equals(rutBusqueda)) {
            encontrado = true;
            System.out.println("Datos del cliente:");
            System.out.println("RUT: " + cliente.getRut());
            System.out.println("Nombre: " + cliente.getNombre());
            System.out.println("Apellido: " + cliente.getApellido());

            
            ArrayList<Cuenta> cuentasCliente = cliente.getCuentas();
            if (!cuentasCliente.isEmpty()) {
                System.out.println("Cuentas asociadas:");
                for (Cuenta cuenta : cuentasCliente) {
                    System.out.println("--------------------");
                    if (cuenta instanceof Cuenta_Corriente) {
                        System.out.println("Tipo de Cuenta: Cuenta Corriente");
                    } else if (cuenta instanceof Cuenta_Ahorro) {
                        System.out.println("Tipo de Cuenta: Cuenta de Ahorro");
                    } else if (cuenta instanceof Cuenta_Credito) {
                        System.out.println("Tipo de Cuenta: Cuenta de Crédito");
                    }
                    System.out.println("Numero de Cuenta: " + cuenta.getNumero());
                    System.out.println("Saldo: " + cuenta.getSaldo() + " pesos");
                }
                System.out.println("--------------------");
            } else {
                System.out.println("El cliente no tiene cuentas asociadas.");
            }

            break;
        }
    }

    if (!encontrado) {
        System.out.println("Cliente no encontrado.");
    }

    System.out.println("");
    Menu();
}

public static void Creacion_de_cuentas(Scanner Teclado) {
    int opciones = 0;
    do{
    System.out.println(" Que tipo de cuenta necesitas ");
    System.out.println("1.- Cuenta corriente");
    System.out.println("2.- Cuenta de ahorro");
    System.out.println("3.- Cuenta credito");
    System.out.println("4.- Volver al inicio");
    opciones = Teclado.nextInt();
    System.out.println("");
    switch (opciones) {
        case 1:
            crearCuenta(Teclado, "corriente");
            break;
        case 2:
            crearCuenta(Teclado, "ahorro");
            break;
        case 3:
            crearCuenta(Teclado, "credito");
            break;
        case 4:
            Menu();
            break;
        default:
            System.out.println("!!Opcion invalida!!");
            break;
    }
} while(opciones<1||opciones>4);
}

public static void crearCuenta(Scanner Teclado, String tipoCuenta) {
    System.out.println("Ingrese su RUT:");
    String rutCliente = Teclado.next();
    Teclado.nextLine();

    Clientes clienteAsociado = null;
    // Buscar el cliente correspondiente en la lista de clientes
    for (Clientes cliente : Clienteslist) {
        if (cliente.getRut().equals(rutCliente)) {
            clienteAsociado = cliente;
            break;
        }
    }

    if (clienteAsociado != null) {
        // Asociar la nueva cuenta con el cliente
        Cuenta nuevaCuenta = null;
        switch (tipoCuenta) {
            case "corriente":
                nuevaCuenta = new Cuenta_Corriente(0, 0);
                break;
            case "ahorro":
                nuevaCuenta = new Cuenta_Ahorro(0, 0);
                break;
            case "credito":
                nuevaCuenta = new Cuenta_Credito(0, 0);
                break;
            default:
                System.out.println("Tipo de cuenta inválido");
                break;
        }

        if (nuevaCuenta != null) {
            nuevaCuenta.generarNumeroCuenta();
            Cuentaslist.add(nuevaCuenta);
            clienteAsociado.agregarCuenta(nuevaCuenta);
            System.out.println("Cuenta creada exitosamente para el cliente con RUT: " + rutCliente);
        }
    } else {
        System.out.println("Cliente con RUT " + rutCliente + " no encontrado.");
    }

    System.out.println("");
    Menu();
}

public static void Depositar(Scanner Teclado) {
    System.out.println("Ingrese el RUT del cliente:");
    String rutCliente = Teclado.next();
    Clientes clienteAsociado = null;
    for (Clientes cliente : Clienteslist) {
        if (cliente.getRut().equals(rutCliente)) {
            clienteAsociado = cliente;
            break;
        }
    }
    if (clienteAsociado != null) {
        ArrayList<Cuenta> cuentasCliente = clienteAsociado.getCuentas();
        if (!cuentasCliente.isEmpty()) {
            System.out.println("Cuentas asociadas:");
            for (int i = 0; i < cuentasCliente.size(); i++) {
                Cuenta cuenta = cuentasCliente.get(i);
                System.out.println((i + 1) + ". Numero de Cuenta: " + cuenta.getNumero() + " - Saldo: " + cuenta.getSaldo() + " pesos");
            }
            System.out.println("Seleccione la cuenta a la que desea depositar:");
            int seleccion = Teclado.nextInt();
            if (seleccion > 0 && seleccion <= cuentasCliente.size()) {
                Cuenta cuentaSeleccionada = cuentasCliente.get(seleccion - 1);
                boolean montoValido = false;
                while (!montoValido) {
                    System.out.println("Ingrese el monto a depositar (o presione 0 para depositar el monto predeterminado):");
                    int monto = Teclado.nextInt();
                    if (monto < 0) {
                        System.out.println("El monto ingresado no es valido. Por favor, ingrese un monto positivo.");
                    } else {
                        if (monto == 0) {
                            cuentaSeleccionada.depositar();
                        } else {
                            cuentaSeleccionada.depositar(monto);
                        }
                        montoValido = true;
                        System.out.println("Monto depositado exitosamente. Nuevo saldo: " + cuentaSeleccionada.getSaldo());
                    }
                }
            } else {
                System.out.println("Seleccion inválida.");
            }
        } else {
            System.out.println("El cliente no tiene cuentas asociadas.");
        }
    } else {
        System.out.println("Cliente no encontrado.");
    }
    System.out.println("");
    Menu();
}
public static void Girar(Scanner Teclado) {
    System.out.println("Ingrese el RUT del cliente:");
    String rutCliente = Teclado.next();
    Clientes clienteAsociado = null;
    for (Clientes cliente : Clienteslist) {
        if (cliente.getRut().equals(rutCliente)) {
            clienteAsociado = cliente;
            break;
        }
    }
    if (clienteAsociado != null) {
        ArrayList<Cuenta> cuentasCliente = clienteAsociado.getCuentas();
        if (!cuentasCliente.isEmpty()) {
            System.out.println("Cuentas asociadas:");
            for (int i = 0; i < cuentasCliente.size(); i++) {
                Cuenta cuenta = cuentasCliente.get(i);
                System.out.println((i + 1) + ". Numero de Cuenta: " + cuenta.getNumero() + " - Saldo: " + cuenta.getSaldo() + " pesos");
            }
            System.out.println("Seleccione la cuenta de la que desea girar:");
            int seleccion = Teclado.nextInt();
            if (seleccion > 0 && seleccion <= cuentasCliente.size()) {
                Cuenta cuentaSeleccionada = cuentasCliente.get(seleccion - 1);
                boolean montoValido = false;
                while (!montoValido) {
                    System.out.println("Ingrese el monto a girar:");
                    int monto = Teclado.nextInt();
                    if (monto < 0) {
                        System.out.println("El monto ingresado no es válido. Por favor, ingrese un monto positivo.");
                    } else if (monto > cuentaSeleccionada.getSaldo()) {
                        System.out.println("Saldo insuficiente. Ingrese un monto menor o igual al saldo disponible.");
                    } else {
                        cuentaSeleccionada.girar(monto);
                        montoValido = true;
                        System.out.println("Monto girado exitosamente. Nuevo saldo: " + cuentaSeleccionada.getSaldo());
                    }
                }
            } else {
                System.out.println("Seleccion inválida.");
            }
        } else {
            System.out.println("El cliente no tiene cuentas asociadas.");
        }
    } else {
        System.out.println("Cliente no encontrado.");
    }
    System.out.println("");
    Menu();
}
public static void ConsultarSaldo(Scanner Teclado) {
    System.out.println("Ingrese el RUT del cliente:");
    String rutCliente = Teclado.next();
    Clientes clienteAsociado = null;
    for (Clientes cliente : Clienteslist) {
        if (cliente.getRut().equals(rutCliente)) {
            clienteAsociado = cliente;
            break;
        }
    }
    if (clienteAsociado != null) {
        ArrayList<Cuenta> cuentasCliente = clienteAsociado.getCuentas();
        if (!cuentasCliente.isEmpty()) {
            System.out.println("Cuentas asociadas:");
            for (int i = 0; i < cuentasCliente.size(); i++) {
                Cuenta cuenta = cuentasCliente.get(i);
                System.out.println((i + 1) + ". Numero de Cuenta: " + cuenta.getNumero() + " - Saldo: " + cuenta.getSaldo() + " pesos");
            }
            System.out.println("Seleccione la cuenta para consultar el saldo:");
            int seleccion = Teclado.nextInt();
            if (seleccion > 0 && seleccion <= cuentasCliente.size()) {
                Cuenta cuentaSeleccionada = cuentasCliente.get(seleccion - 1);
                System.out.println("Saldo de la cuenta: " + cuentaSeleccionada.getSaldo());
            } else {
                System.out.println("Seleccion invalida.");
            }
        } else {
            System.out.println("El cliente no tiene cuentas asociadas.");
        }
    } else {
        System.out.println("Cliente no encontrado.");
    }
    System.out.println("");
    Menu();
}
    public static void main(String[] args) {
        System.out.println("Bienvenido al Bank_Boston");
        System.out.println("");
        Menu();
    }
}
