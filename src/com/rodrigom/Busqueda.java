package com.rodrigom;

import java.util.*;

/**
 * Rodrigo Munguía Garrido
 * IS17110457
 * ITESI
 * Lenguajes Automatas 2
 * Programa que lee una expresión aritmética y genera
 * su árbol de expresion junto con los 3 recorridos pertenecientes.
 */

/**
 * La clase busqueda busca cada uno de los elementos de una expresión aritmetica inorden
 * para procesarla y convertirla en post orden. La salida del metodo principal de esta clase
 * es un arreglo de strings que representan la expresion aritmetica de forma postorden.
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
     *
     * @param valor
     * @return
     */
    public int nivelImportancia(String valor) {
        if (valor.equals("+") || valor.equals("-")) {
            return 3;
        } else if (valor.equals("*") || valor.equals("/")) {
            return 2;
        } else {
            return 1;
        }
    }

    /**
     * En este metodo se sobrecarga con una expresion aritmetica provocando una inicialización para la
     * estructura colaSalida la cual alojará la expresion aritmetica infija convertida a una expresion artimetica a
     * prefija, para ello se necesita una pila (Operadores) y un objeto que ayude separar cada elemento de la expresion.
     * <p>
     * Para la conversion se utilizará una version modificada del algoritmo Shunting-yard. Esta version necesita una
     * expresion aritmetica escrita de manera inversa para funcionar.
     *
     * @param expresion
     * @return
     */
    public String[] convertirExp(String expresion) {
//Inicialización de la cola que guardará los caracteres ordenados de manera postfija.
        colaSalida = new LinkedList<>();
        //Inicialización de la pila que guardará temporalmente los operadores.
        Operadores = new Stack<>();
        //Inicializacion de un objeto de tipo StringTokenizer en el que este caso, divide la expresión
        //en tokens usando como limitadores operadores aritmeticos (incluyendo potencias) y parentesis de agrupamiento.
        StringTokenizer Tokens = new StringTokenizer(expresion, ")+-*/%^(", true);


        //Se recorre la lista de tokens que el StringTokenizer regresó
        while (Tokens.hasMoreTokens()) {
            //El token actual se guarda en una variable.
            String Token = Tokens.nextToken();
            /**
             * El token actual se compara con numeros y letras haciendo uso de una expresión regular,
             * en caso de ser verdadero se guarda directamente en la cola de salida.
             */
            if (Token.matches("[0-9]+||[A-Za-z]+")) {
                colaSalida.add(Token);
            } else if (Token.equals("+") || Token.equals("-") || Token.equals("*") || Token.equals("/") || Token.equals("%") || Token.equals("^") || Token.equals("(")) {
                //En caso de ser verdadero se verificara que la pila no este vacia
                if (!Operadores.isEmpty()) {
                    /**
                     * Se hace uso del metodo nivelImportancia para verificar el valor de importancia del operando actual y el que esta guardado
                     * en el tope de la pila.
                     */
                    if (nivelImportancia(Operadores.peek()) == nivelImportancia(Token)) {
                        /**
                         * En caso de que sea el mismo, se verificara si ambos son el operando ^, este es una excepción ya que se verifica de derecha a izquierda
                         * por lo tanto si hay 2 ^ seguidos se guardaran directamente en la pila.
                         */
                        if (Token.equals("^") && Operadores.peek().equals("^")) {
                            Operadores.push(Token);
                        }
                        /**
                         * En caso contrario si los dos operadores tanto como el operador actual guardado en la variable y el que está en la punta
                         * de la pila, el operando en la punta de la pila se removerá de la pila y guardandose en la cola de salida y posteriormente el operando
                         * actual guardado en la variable se guardará en la pila.
                         */
                        else {
                            colaSalida.add(Operadores.pop());
                            Operadores.push(Token);
                        }
                    }
                    /**
                     * En caso de que sean niveles de importancia diferentes, el operador guardado en la variable se guardará directamente en la pila.
                     */
                    else {
                        Operadores.push(Token);
                    }
                }
                //En caso de que la pila este vacía se agrega directamente en la pila.
                else {
                    Operadores.push(Token);
                }
            }


            //En caso de que se haya encontrado un caracter de parentesis cerrado, se borrarán de la pila todos los operadores hasta encontrar
            //el parentesis abierto.
            else if (Token.equals(")")) {
                String temp;
                do {
                    temp = Operadores.pop();
                    if (!temp.equals("(")) {
                        colaSalida.add(temp);
                    }
                } while (!temp.equals("("));
            }
        }
        //Finalmente, todos los operadores residuales en la pila se guardan en la cola de salida.
        while (!Operadores.empty()) {
            colaSalida.add(Operadores.pop());
        }
        return colaSalida.toArray(String[]::new);
    }


}






