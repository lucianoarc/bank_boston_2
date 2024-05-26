
package Tarjetas;

import java.util.Random;

public class Cuenta {
    private int Numero;
    private int Saldo;

    public Cuenta(int Numero, int Saldo) {
        this.Numero = Numero;
        this.Saldo = Saldo;
    }

    public int getNumero() {
        return Numero;
    }

    public void setNumero(int Numero) {
        this.Numero = Numero;
    }

    public int getSaldo() {
        return Saldo;
    }

    public void setSaldo(int Saldo) {
        this.Saldo = Saldo;
    }

    public void generarNumeroCuenta() {
        Random rand = new Random();
        this.Numero = rand.nextInt(900000000) + 100000000;
    }

    public void depositar(int monto) {
        this.Saldo += monto;
    }
    public void depositar() {
        int montoPredeterminado = 1000;  
        this.Saldo += montoPredeterminado;
    }

    public void girar(int monto) {
        if (monto <= this.Saldo) {
            this.Saldo -= monto;
        } else {
            System.out.println("Saldo insuficiente.");
        }
    }
}