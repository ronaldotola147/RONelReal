package pe.edu.upeu.library;

public class Main {
    public static void main(String[] args) {
        //declaramos arrays bidimensional
        int[][] matriz = new int[3][3];
        //asignar valores
        int contador=0;
        for (int i = 0; i < 3; i++) {//fila
            for (int j = 0; j < 3; j++) {//columna
                matriz[i][j] =contador++;
                contador++;
            }
        }
        System.out.println("Matriz A:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(matriz[i][j] + "\t");
            }
            System.out.println();
        }
    }
}