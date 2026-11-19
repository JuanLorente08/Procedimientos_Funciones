// Queda poner la casilla de final

import java.util.Random;
import java.util.Scanner;

public class Ejercicio1 {
    static Random aleat = new Random();
    static int filaAl = -1;
    static int colAl = -1;
    static int filaOficialYoda = -1;
    static int columnaOficialYoda = -1;
    static int MAX_FIL_YODA = 10;
    static int MAX_COL_YODA = 10;
    static int MAX_FIL_DM = 10;
    static int MAX_COL_DM = 10;
    static char YODA = 'Y';
    static char LIBRE = 'L';
    static char MURO = 'M';

    static void imprimir_Tablero(char[][] tableroYoda){
        for (int i = 0; i < MAX_FIL_YODA; i++) {
            for (int j = 0; j < MAX_COL_YODA; j++) {
                System.out.print(tableroYoda[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void rellenar_Libre(char[][] tableroYoda){
        for (int i = 0; i < MAX_FIL_YODA; i++) {
            for (int j = 0; j < MAX_COL_YODA; j++) {
                tableroYoda[i][j] = LIBRE;
            }
        }
    }

    static void Yoda(char[][] tableroYoda){
        for (int i = 0; i < 1; i++) {
            do{
                filaAl = aleat.nextInt(MAX_FIL_YODA);
                colAl = aleat.nextInt(MAX_COL_YODA);
            }while(tableroYoda[filaAl][colAl]!=LIBRE);
            tableroYoda[filaAl][colAl] = YODA;
        }
        // OFICIALIZO LAS POSICIONES DE MI YODA
        filaOficialYoda = filaAl;
        columnaOficialYoda = colAl;
    }

    static void Muro(char[][] tableroYoda){
        for (int i = 0; i < 10; i++) {
            do{
                filaAl = aleat.nextInt(MAX_FIL_YODA);
                colAl = aleat.nextInt(MAX_COL_YODA);
            }while(tableroYoda[filaAl][colAl]!=LIBRE);
            tableroYoda[filaAl][colAl] = MURO;
        }
    }


    public static void main(String[] args) {
        boolean yodaActive = true;
        boolean dmActive = false;

        // NO VOY A UTILIZAR FUNCIONES/PROCEDIMIENTOS
        char[][] tableroYoda = new char[MAX_FIL_YODA][MAX_COL_YODA];
        char[][] tableroDM = new char[MAX_FIL_DM][MAX_COL_DM];

        // tableroYoda
        char[][] tableroACTIVO =null;
        /*if((yodaActive==true) && (dmActive==false)){
            tableroACTIVO = tableroYoda;
        }else{
            tableroACTIVO = tableroDM;
        }*/
        if((yodaActive==true) && (dmActive==false)){

            yodaActive = false;
            dmActive = true;
        }else{

            yodaActive = true;
            dmActive = false;
        }

        rellenar_Libre(tableroYoda);

        // Variables
        Yoda(tableroYoda);
        Muro(tableroYoda);

        char VADER = 'V';
        for (int i = 0; i < 7; i++) {
            do{
                filaAl = aleat.nextInt(MAX_FIL_YODA);
                colAl = aleat.nextInt(MAX_COL_YODA);
            }while(tableroYoda[filaAl][colAl]!=LIBRE);
            tableroYoda[filaAl][colAl] = VADER;
        }
        char POCIMA = 'P';
        for (int i = 0; i < 5; i++) {
            do{
                filaAl = aleat.nextInt(MAX_FIL_YODA);
                colAl = aleat.nextInt(MAX_COL_YODA);
            }while(tableroYoda[filaAl][colAl]!=LIBRE);
            tableroYoda[filaAl][colAl] = POCIMA;
        }
        imprimir_Tablero(tableroYoda);
        // EMPIEZA EL JUEGO !!!
        int vidas = 10;
        Scanner lector = new Scanner(System.in);
        do {
            System.out.println("EMPIEZA EL JUEGO!!");
            System.out.println("Te mueves en WASD");
            System.out.println("Dime una letra de movimiento !!");
            char mov = lector.next().charAt(0);
            int filaAnteriorYoda = filaOficialYoda;
            int colAnteriorYoda = columnaOficialYoda;
            switch (mov){ // W, A, S o D
                case 'W':
                    filaOficialYoda = filaOficialYoda - 1;
                    break;
                case 'A':
                    columnaOficialYoda = columnaOficialYoda - 1;
                    break;
                case 'S':
                    filaOficialYoda = filaOficialYoda + 1;
                    break;
                case 'D':
                    columnaOficialYoda = columnaOficialYoda + 1;
                    break;
            }
            // Y SI YODA SE SALE DEL TABLERO ??????????????????
            if(
                    filaOficialYoda < 0 ||
                            filaOficialYoda > 9 ||
                            columnaOficialYoda < 0 ||
                            columnaOficialYoda > 9
            ){
                System.out.println("EHHHHHHHH!!!!!!!!!!!!");
                System.out.println("LÍMITE DE TABLERO!!!!!!");
                filaOficialYoda = filaAnteriorYoda;
                columnaOficialYoda = colAnteriorYoda;
            }else{
                if(tableroYoda[filaOficialYoda][columnaOficialYoda] == MURO){
                    System.out.println("NO PUEDES PASAR!!!!!");
                    filaOficialYoda = filaAnteriorYoda;
                    columnaOficialYoda = colAnteriorYoda;
                }else if(tableroYoda[filaOficialYoda][columnaOficialYoda] == POCIMA){
                    tableroYoda[filaOficialYoda][columnaOficialYoda] = YODA;
                    tableroYoda[filaAnteriorYoda][colAnteriorYoda] = LIBRE;
                    vidas++;
                }else if(tableroYoda[filaOficialYoda][columnaOficialYoda] == VADER){
                    tableroYoda[filaOficialYoda][columnaOficialYoda] = YODA;
                    tableroYoda[filaAnteriorYoda][colAnteriorYoda] = LIBRE;
                    vidas--;
                }else if(tableroYoda[filaOficialYoda][columnaOficialYoda] == LIBRE){
                    tableroYoda[filaOficialYoda][columnaOficialYoda] = YODA;
                    tableroYoda[filaAnteriorYoda][colAnteriorYoda] = LIBRE;
                }
            }
            imprimir_Tablero(tableroYoda);

        }while(vidas>0);
    }
}
