
package Tarjetas;

public class Cuenta_Corriente extends Cuenta {

    public Cuenta_Corriente(int Numero, int Saldo) {
        super(Numero, Saldo);
    }

    @Override
    public int getSaldo() {
        return super.getSaldo() + 5000;
    }
}