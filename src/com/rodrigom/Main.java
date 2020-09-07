package com.rodrigom;

import java.util.Scanner;


/**
 * Rodrigo Munguía Garrido
 * IS17110457
 * ITESI
 * Lenguajes Automatas 2
 * Programa que lee una expresión aritmética y genera
 * su arbol de expresion junto con los 3 recorridos pertenecientes.
 */

public class Main {

    public static void main(String[] args) {
        // Declaración e inicialización del objeto scanner que permite la lectura de datos.
        Scanner Entrada = new Scanner(System.in);
        // Declaracion del objeto busqueda que convierte la expresión en post orden.
        Busqueda Conversion = new Busqueda();
        // Declaración del objeto ArbolExpresion el cual generará la estructura Arbol de expresion haciendo uso de las
        // estructuras de datos Nodo.
        ArbolExpresion ArbolExp = new ArbolExpresion();

        //Lectura y limpieza de la entrada
        System.out.print("Ingrese la expresión aritmetica inorden: ");
        String Exp = Entrada.nextLine().replaceAll(" ", "");
        //Retorno de la entrada procesada.
        String[] Array = Conversion.convertirExp(Exp);
        //Creación y retorno del Arbol de expresión
        Nodo Arbol = ArbolExp.construirArbol(Array);

        //Salida de datos.
        System.out.println("\nInorden");
        Arbol.InOrden();
        System.out.println("\nPreOrden");
        Arbol.PreOrden();
        System.out.println("\nPostOrden");
        Arbol.PostOrden();
        System.out.println("\n");

        Nodo.imprimirArbol(Arbol);
    }
}
