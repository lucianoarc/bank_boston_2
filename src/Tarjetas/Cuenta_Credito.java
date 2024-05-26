
package Tarjetas;

public class Cuenta_Credito extends Cuenta {

    public Cuenta_Credito(int Numero, int Saldo) {
        super(Numero, Saldo);
    }

    @Override
    public int getSaldo() {
        return super.getSaldo() + 50000;
    }
}