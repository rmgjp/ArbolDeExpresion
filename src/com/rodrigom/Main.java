package com.rodrigom;

import com.sun.source.tree.ExpressionTree;

import java.beans.Expression;
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
 */

public class Main {

    public static void main(String[] args) {
        Scanner Entrada = new Scanner(System.in);
        Busqueda Conversion = new Busqueda();
        ArbolExpresion ArbolExp = new ArbolExpresion();



        System.out.print("Ingrese la expresión aritmetica: ");
        String Exp = Entrada.nextLine().replaceAll(" ", "");


        Nodo Raiz = ArbolExp.construirArbol(Conversion.convertirExp(Exp).toArray(String[]::new));

        System.out.println("lol");

    }
}
