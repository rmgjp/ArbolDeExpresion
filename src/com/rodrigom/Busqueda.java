package com.rodrigom;

import java.util.*;

/**
 * Rodrigo Munguía Garrido
 * IS17110457
 * ITESI
 * Lenguajes Automatas 2
 *
 * Programa que lee una expresión aritmética y genera
 * su árbol de expresion junto con los 3 recorridos pertenecientes.
 *
 */


public class Busqueda {

    Queue<String> colaSalida;
    Stack<String> Operadores;

    /**
     * Este método ayuda a clasificar el nivel de importancia que puede llegar a tener un nodo
     * asado en la jerarquía de operaciones de manera inversa, es decir, la suma y la resta tienen mayor
     * prioridad (en este caso prioridad 4), seguido de la multiplicación y division (prioridad 3),
     * después los exponenciales (prioridad 2) y por ultimo los números (prioridad 1), si bien estos últimos
     * no son un operadores, obtienen un nivel de importancia con fines de clasificación para el funcionamiento
     * del programa.
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
     * Este método convierte una expresion aritmética infija a postfija haciendo uso del algoritmo shunting yard
     * se hace uso de Colas y Pilas, en este caso la Cola serán los caracteres ordenados de manera postfija
     * la Pila es un almacenamiento temporal de los operadores para su posterior guardado en la Cola de salida
     * como se muestra a continuación.
     * Este método regresa una Cola basado en la clase Queue de Java.
     *
     * @param expresion
     */
    public Queue<String> conversionPostfija(String expresion) {

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
            if (Token.matches("[0-9]||[A-Za-z]+")) {
                colaSalida.add(Token);
            }
            /**
             * En caso contrario el token pasará por una serie de filtros para procesar su almacenamiento.
             * Primero se compara con los caracteres +-/*(
             */
            else if (Token.equals("+") || Token.equals("-") || Token.equals("*") || Token.equals("/") || Token.equals("%") || Token.equals("^") || Token.equals("(")) {
                //En caso de ser verdadero se verificara que la pila no este vacía
                if (!Operadores.isEmpty()) {
                    /**
                     * Se hace uso del método nivelImportancia para verificar el valor de importancia del operando actual y el que esta guardado
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
                         * de la pila, el operando en la punta de la pila se removerá de la pila y guardándose en la cola de salida y posteriormente el operando
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

            //En caso de que se haya encontrado un carácter de paréntesis cerrado, se borrarán de la pila todos los operadores hasta encontrar
            //el paréntesis abierto.
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
        return colaSalida;
    }

}
