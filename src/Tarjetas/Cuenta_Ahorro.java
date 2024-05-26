
package Tarjetas;


public class Cuenta_Ahorro extends Cuenta{

    public Cuenta_Ahorro(int Numero, int Saldo) {
        super(Numero, Saldo);
    }
    @Override
    public int getSaldo(){
    return super.getSaldo()+ 10000;
    }
}
