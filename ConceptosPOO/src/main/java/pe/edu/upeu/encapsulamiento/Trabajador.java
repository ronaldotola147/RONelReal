package pe.edu.upeu.encapsulamiento;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Data
//@Getter
//@Setter

public class Trabajador {
    private String nombre;
    private int edad;
    private String apellido;
    private String area;
    private char genero;

    //ajpra isa,ps ña ñob reroa lombok

    @Override
    public String toString() {
        return "Tiene las siguiente caracteristicas: \n"+
                "Nombre: "+nombre+"\n"+
                "Apellido: "+apellido+"\n";
    }
}
