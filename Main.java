package com.company;

import java.util.Random;
import java.util.Scanner;

public class Main {
    static char[][] tablero ;
    static String[][] jugador;
    static int i = 0;
    static int d=0;
    public static void main(String[] args) {
        jugador= new String[20][4];
	    menu(i);
    }
    public static void menu(int id){

        Scanner sc = new Scanner(System.in);
        System.out.println("**********************");
        System.out.println("* 1. Jugar           *");
        System.out.println("* 2. Historial       *");
        System.out.println("* 3. Salir           *");
        System.out.println("**********************");

        try {
            int opciones = sc.nextInt();
            switch (opciones) {
                case 1 -> creacionJugador(id);
                case 2 -> imprimirUsuario(id);
                case 3 -> System.out.println("Adios");
                default -> {
                    System.out.println("ingrese la opcion corespondientes");
                    menu(id);
                }
            }
        }catch (Exception IE){
            System.out.println("Falla");
            menu(id);

        }
    }
    public static void creacionJugador(int id){
        System.out.println("Nombre:");
        Scanner nombreUsuario = new Scanner(System.in);
        String nombre=nombreUsuario.next();
        System.out.println("Edad: ");
        Scanner edadUsuario = new Scanner(System.in);
        String edad= edadUsuario.next();
        jugador[id][0]= nombre;
        jugador[id][1]= edad;
        jugador[id][2]= Integer.toString(0);
        jugador[id][3]= Integer.toString(0);
        id++;
        Scanner medida= new Scanner(System.in);
        System.out.println("La medida del juego:");
        d=medida.nextInt();
        tablero(id,d);
        }
    public static void imprimirUsuario(int id){
        System.out.println("Jugadores");
        for(int i=0;i< id;i++){
            System.out.println("******************************");
            for (int j = 0; j<=jugador[i].length;j++){
                if(j==0){
                    System.out.println("Nombre: "+jugador[i][j]);
                }if(j==1){
                    System.out.println("Edad: "+jugador[i][j]);
                }if(j==2){
                    System.out.println("Punteo: "+jugador[i][j]);
                }if(j==3) {
                    System.out.println("Movimiento: "+jugador[i][j]);
                }
            }
            System.out.println("******************************");
        }
        menu(id);

    }
    public static void tablero(int id,int medida){
       tablero = new char[medida][medida];
       for(int i=0;i<medida;i++){
               if(i==0){
                   for(int j =0;j<medida;j++){
                       tablero[j][i]='*';

                   }
               }else if(i==(medida-1)){
                   for(int j =0;j<medida;j++) {
                       tablero[j][i] = '*';
                   }
               }else {
                   for (int j = 0; j < medida; j++) {
                       if (j == 0) {
                           tablero[j][i] = '*';
                       }else if (j == medida-1) {
                           tablero[j][i] = '*';
                       } else {
                           tablero[j][i] = ' ';
                       }
                   }
               }

       }
       crearParedes(medida);
       crearProta();
       crearVillano(medida);
       for(int i=0;i<3;i++){
           crearComidaDeDiez(medida);
           crearComidaDeQuitz(medida);
       }
       puntos(id,0,10);
       horaDeJugar(id,medida,1,1);
    }
    public static void crearParedes(int dimension){
        Random rd= new Random();
        int paredX=rd.nextInt(1,dimension);
        int paredY= rd.nextInt(1,dimension);

        if(tablero[paredX][paredY]==' '){
            tablero[paredY][paredX] = '*';
            if(tablero[paredX+1][paredY]==' ') {
                tablero[paredY+1][paredX] = '*';
                if(tablero[paredX+2][paredY]==' ') {
                    tablero[paredY+2][paredX] = '*';
                }
            }else if(tablero[paredX][paredY+1]==' '){
                tablero[paredY][paredX+1] = '*';
                if(tablero[paredX+1][paredY+1]==' ') {
                    tablero[paredY+1][paredX+1] = '*';
                }
            }
            else{
                crearParedes(dimension);
            }
        }else {
            crearParedes(dimension);
        }
    }
    public static void crearProta(){
        int pX=1;
        int pY=1;
        tablero[pX][pY]='V';
    }
    public static void crearVillano(int dimension){
        Random rd= new Random();
        int villanoX=rd.nextInt(1,dimension);
        int villanoY= rd.nextInt(1,dimension);

        if(tablero[villanoX][villanoY]==' '){
            tablero[villanoX][villanoY] = '#';
        }else {
            crearVillano(dimension);
        }
    }
    public static void crearComidaDeDiez(int dimension){
        Random rd= new Random();
        int comidaX=rd.nextInt(1,dimension);
        int comidaY= rd.nextInt(1,dimension);

        if(tablero[comidaX][comidaY]==' '){
            tablero[comidaX][comidaY] = '@';
        }else {
            crearComidaDeDiez(dimension);
        }
    }
    public static void crearComidaDeQuitz(int dimension){
        Random rd= new Random();
        int comidaX=rd.nextInt(1,dimension);
        int comidaY= rd.nextInt(1,dimension);

        if(tablero[comidaX][comidaY]==' '){
            tablero[comidaX][comidaY] = '$';
        }else {
            crearComidaDeQuitz(dimension);
        }
    }
    public static void elChampion(int id,int dimension,int x0,int yo){
        if(Integer.parseInt(jugador[id-1][2])>95) {
            System.out.println("***********");
            System.out.println("*         *");
            System.out.println("* Ganaste *");
            System.out.println("*         *");
            System.out.println("***********");
            Scanner sc = new Scanner(System.in);
            sc.next();
            menu(id);
        }if(Integer.parseInt(jugador[id-1][2])<0) {
            System.out.println("*************");
            System.out.println("*           *");
            System.out.println("* GAME OVER *");
            System.out.println("*           *");
            System.out.println("*************");
            menu(id);
        
        }
        else{horaDeJugar(id,dimension,x0,yo);}
    }
    public static void horaDeJugar(int id,int dimension,int xo,int yo){
        imprimirTablero(id,
                dimension);
        String valor =jugador[id-1][3];
        int desplazamiento = Integer.parseInt(valor);
        System.out.println("controles    menu");
        System.out.println("    w          m ");
        System.out.println("   asd           ");
        Scanner movimiento= new Scanner(System.in);
        String direccion = movimiento.nextLine();
        if(Integer.parseInt(jugador[id-1][2])>95){
            System.out.println("Ganaste");
            menu(id);
        }else{

            switch (direccion) {
                case "w" -> {
                    desplazamiento = desplazamiento + 1;
                    valor = Integer.toString(desplazamiento);
                    jugador[id - 1][3] = valor;
                    movimiento(id, dimension, xo, yo, xo - 1, yo);
                }
                case "a" -> {
                    desplazamiento = desplazamiento + 1;
                    valor = Integer.toString(desplazamiento);
                    jugador[id - 1][3] = valor;
                    movimiento(id, dimension, xo, yo, xo, yo - 1);
                }
                case "s" -> {
                    desplazamiento = desplazamiento + 1;
                    valor = Integer.toString(desplazamiento);
                    jugador[id - 1][3] = valor;
                    movimiento(id, dimension, xo, yo, xo + 1, yo);
                }
                case "d" -> {
                    desplazamiento = desplazamiento + 1;
                    valor = Integer.toString(desplazamiento);
                    jugador[id - 1][3] = valor;
                    movimiento(id, dimension, xo, yo, xo, yo + 1);
                }
                case "m" -> menu(id);
                default -> horaDeJugar(id, dimension, xo, yo);
            }
        }

    }
    public static void puntos(int id,int puntos,int adicion){
        puntos=puntos+adicion;
        jugador[id-1][2]= Integer.toString(puntos);
    }
    public static void movimiento(int id,int dimension,int xo, int yo, int xf, int yf){
        int a = Integer.parseInt(jugador[id-1][2]);
        if(tablero[xf][yf]=='*'){
            System.out.println("No eres superman para atravezar paredes");
            horaDeJugar(id,dimension,xo,yo);
        }if (tablero[xf][yf]==' '){
            tablero[xo][yo]=' ';
            tablero[xf][yf]='v';
            elChampion(id,dimension,xf,yf);

        }if(tablero[xf][yf]=='#'){
            tablero[xo][yo]=' ';
            tablero[xf][yf]='v';
            puntos(id,a,-10);
            crearVillano(dimension);
            elChampion(id,dimension,xf,yf);

        }if(tablero[xf][yf]=='$'){
            tablero[xo][yo]=' ';
            tablero[xf][yf]='v';
            puntos(id,a,15);
            crearComidaDeQuitz(dimension);
            elChampion(id,dimension,xf,yf);
        }if(tablero[xf][yf]=='@'){
            tablero[xo][yo]=' ';
            tablero[xf][yf]='v';
            puntos(id,a,10);
            crearComidaDeDiez(dimension);
            elChampion(id,dimension,xf,yf);
        }

    }
    public static void imprimirTablero(int id,int d){
        System.out.println("----------------------");
            for(int i=0;i<4;i++){
                if (i == 0) {
                    System.out.println("Nombre:"+jugador[id-1][i]);
                }if (i==2){
                    System.out.println("Punteo:"+jugador[id-1][i]);
                }if(i==3){
                    System.out.println("Movimiento:"+jugador[id-1][i]);
                }

            }
        System.out.println("----------------------");
            for(int i=0;i< d;i++){
                for (int j=0;j<d;j++){
                    System.out.print(" "+tablero[i][j]);
                }
                System.out.println();
            }
    }

}
