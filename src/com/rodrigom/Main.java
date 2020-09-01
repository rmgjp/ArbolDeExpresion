package com.rodrigom;

import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Rodrigo Munguía Garrido
 * IS17110457
 * ITESI
 * Lenguajes Automatas 2
 *
 * Programa que lee una expresión aritmética y genera
 * su arbol de expresion junto con los 3 recorridos pertenecientes.
 *
 *
 */

public class Main {

    public static void main(String[] args) {
        //Lectura de la expresión aritmética.
        Nodo<String> arbol = new Nodo<>("10");
        arbol.agregarNodo("5");
        arbol.agregarNodo("15");
        arbol.agregarNodo("8");



        arbol.inOrden();
        System.out.println("");
        arbol.postOrden();
        System.out.println("");
        arbol.preOrden();

        /**
        String nombre="6+(2*4)-5";
        StringTokenizer tokens=new StringTokenizer(nombre,"[+,-]");
        while(tokens.hasMoreTokens()){
            System.out.println(tokens.nextToken());
        }
         */


    }
}
