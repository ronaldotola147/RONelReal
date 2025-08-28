package pe.edu.upeu.enums;

enum TIPO_PAGO{Efectivo,Credito,Transferencia, Yape}

enum Mes{Enero, Febrero, }

public class Pago {
    TIPO_PAGO tipo;
    Mes mes;
    double monto;
    String cuenta;
    double impuesto;

    public static void main(String[] args) {
        Pago p = new Pago();
        p.tipo = TIPO_PAGO.Efectivo;
        p.mes = Mes.Enero;
        p.monto = 100;
        p.cuenta = "156165 465565 456";
        p.impuesto = 10;

        System.out.println("Tipo de pago: "+ p.tipo);
        System.out.println("Mes de pago: "+ p.mes);
        System.out.println("Monto "+p.monto);
        System.out.println("Cuenta: "+ p.cuenta);
        System.out.println("Impuesto: "+ p.impuesto);

        for (TIPO_PAGO t : TIPO_PAGO.values()) {
            System.out.println("Tipo de pago:"+ t);
        }
    }


}
