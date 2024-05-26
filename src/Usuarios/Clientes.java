
package Usuarios;

import Tarjetas.Cuenta;
import java.util.ArrayList;


public class Clientes {
private String Rut;    
private String Nombre;
private String Apellido;
private ArrayList<Cuenta> cuentas;

    public Clientes(String Rut, String Nombre, String Apellido) {
        this.Rut = Rut;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.cuentas = new ArrayList<>();
    }

 public static boolean validarRut(String Rut) {                                   
         return Rut.matches("\\d{1,2}\\.\\d{3}\\.\\d{3}-[\\dkK]");
    }
       public String getRut() {
        return Rut;
    }
    public void setRut(String Rut) {
       if (validarRut(Rut)) {
       this.Rut = Rut;
       } else {
       throw new IllegalArgumentException("RUT inválido");
        }
    }

    public static boolean validarNombre(String nombre) {                          
    return !nombre.isEmpty();
}
    
    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public static boolean validarApellido(String Apellido) {                    
        return !Apellido.isEmpty();
    }
    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }
    public void agregarCuenta(Cuenta cuenta) {
        this.cuentas.add(cuenta);
    }
    public ArrayList<Cuenta> getCuentas() {
    return this.cuentas;
}
}
