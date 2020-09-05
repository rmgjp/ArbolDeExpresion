package com.rodrigom;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Rodrigo Munguía Garrido
 * IS17110457
 * ITESI
 * Lenguajes Automatas 2
 * <p>
 * Programa que lee una expresión aritmética y genera
 * su árbol de expresion junto con los 3 recorridos pertenecientes.
 */


public class Busqueda {

    Queue<String> colaSalida;
    Stack<String> Operadores;

    /**
     * Este método es una herramienta para clasificar los strings por los cuales es sobrecargado
     * dependiendo si el string contiene operadores u operandos este regresará un calor especifico
     * los valores son una representacion de la jerarquia de operadores donde + y - son los de menor
     * prioridad, seguido de * y / y finalmente de ^, para hacer funcionar el algoritmo tambien se agregó
     * como salida por default -1 para todo aquel string que no contenga un operador.
     * @param valor
     * @return
     */
    public int nivelImportancia(String valor) {
        switch (valor.toCharArray()[0]) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        }
        return -1;
    }


    /**
     * En este metodo se sobrecarga con una expresion aritmetica provocando una inicialización para la
     * estructura colaSalida la cual alojará la expresion aritmetica infija convertida a una expresion artimetica a
     * prefija, para ello se necesita una pila (Operadores) y un objeto que ayude separar cada elemento de la expresion.
     *
     * Para la conversion se utilizará una version modificada del algoritmo Shunting-yard. Esta version necesita una
     * expresion aritmetica escrita de manera inversa para funcionar.
     *
     * @param expresion
     * @return
     */
    public Queue<String> convertirExp(String expresion) {
        //Inicializacion de las estructuras de datos.
        colaSalida = new LinkedList<>();
        Operadores = new Stack<>();
        //Este objeto guarda todos los elementos por separado de la expresion guardandolos en una tabla de tokens.
        StringTokenizer Tokens = new StringTokenizer(expresion, ")+-*/%^(", true);

        //Se invierte la expresion alojandola en un arreglo de Strings.
        String[] TokenInverso = new String[Tokens.countTokens()];
        int i = Tokens.countTokens();

        while (Tokens.hasMoreTokens()) {
            TokenInverso[i - 1] = Tokens.nextToken();
            i--;
        }

        //Posteriormente, los parentesis deben ser corregidos, es decir cada ) debe ser ( y cada ) debe ser (
        for (int it = 0; it < TokenInverso.length; it++) {
            if (TokenInverso[it] == "(") {
                TokenInverso[it] = ")";
                it++;
            } else if (TokenInverso[it] == ")") {
                TokenInverso[it] = "(";
                it++;
            }
        }

        //Aplicacion del algoritmo
        for (String Token : TokenInverso) {
            Apilar(Token);
        }

        //Finalmente los operandos residuales deben agregarse a la expresión.
        while (!Operadores.empty()) {
            colaSalida.add(Operadores.pop());
        }

        //Para formato de salida, la expresión debe invertise una vez mas para dar la expresion final.
        List<String> Reversa = colaSalida.stream().collect(Collectors.toList());
        Collections.reverse(Reversa);

        //Retorno de la expresión procesada.
        return new LinkedList<>(Reversa);
    }

    /**Este metodo es una transcripcion del algoritmo Shunting-yard con algunas modificaciones ya que originalmente ese
     * algoritmo se utiliza para convertir expresiones infijas a postfijas.
     * @param Token
     */
    private void Apilar(String Token) {
        //Se revisa el nivel de importancia del Token actual.
        if (nivelImportancia(Token) > 0) {
            //Si la pila Operadores no esta vacia y el nivel de importancia del elemento en la punta es mayor al token actual
            while (Operadores.isEmpty() == false && nivelImportancia(Operadores.peek()) >= nivelImportancia(Token)) {
                //Se añade a la expresion el operador guardado en la pila
                colaSalida.add(Operadores.pop());
            }
            //Finalmente se guarda el Token actual en la pila.
            Operadores.push(Token);
        } else if (Token.equals(")")) {
            //Si el token es un parentesis cerrado, todos los operadores guardados en la pila formaran parte de la expresion
            //hasta llegar al parentes abierto.
            String Temp = Operadores.pop();
            while (!Temp.equals("(")) {
                colaSalida.add(Temp);
                Temp = Operadores.pop();
            }
            //Si es un parentesis abierto se guarda en la pila, este sera eliminado hasta que se encuentre un parentesis cerrado
        }
        else if (Token.equals("(")) {
            Operadores.push(Token);

        }
        //Si el nivel de la importancia es menor a 0 entonces se trata de un operando.
        else {
            colaSalida.add(Token);
        }
    }
}

