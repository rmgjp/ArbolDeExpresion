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
 */

public class Main {

    public static void main(String[] args) {
        String Exp = "3+2";
        Busqueda buscar = new Busqueda();
        buscar.conversionPostfija(Exp);
    }
}
