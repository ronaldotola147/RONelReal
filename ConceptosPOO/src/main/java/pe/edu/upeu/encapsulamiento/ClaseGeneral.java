package pe.edu.upeu.encapsulamiento;

public class ClaseGeneral {
    public static void main(String[] args) {
        Persona p = new Persona(); // p=objeto
        //p.nombre= "David";
       // p.edad=25;
        p.setNombre("Ronaldo");//encapsulamiento
        p.setEdad(20);//encapsulamiento
        p.setApellido("Tola");//no se esta aplicando encapsulamiento


        p.saludo();

        Trabajador t = new Trabajador();
        t.setNombre("Ubaldo");
        t.setEdad(20);
        t.setApellido("mamni");
        t.setArea("Sistemas");
        t.setGenero('M');
        System.out.println(t);




    }


}
