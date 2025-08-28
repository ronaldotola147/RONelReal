package pe.edu.upeu.claseinterna;

public class ClaseExterna {
    int a,b;

    class ClaseInterna1{
        double r;
        double sumar(){
            r=a+b;
            return r;
        }

    }
    class ClaseInterna2{
        double r;
        double resta(){
            r=a-b;
            return r;

        }

    }

    public static void main(String[] args) {
        ClaseExterna c = new ClaseExterna();
        c.a=10;
        c.b=5;
        ClaseInterna1 cii=c.new ClaseInterna1();
        cii.sumar();
        System.out.println(cii.sumar());

        ClaseInterna2 cii2=c.new ClaseInterna2();
        cii2.resta();
        System.out.println(cii2.r);

    }
}
class ClaseExterna3{

}