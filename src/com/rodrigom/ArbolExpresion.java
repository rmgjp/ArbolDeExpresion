package com.rodrigom;

import java.util.Stack;

/**
 * Rodrigo Munguía Garrido
 * IS17110457
 * ITESI
 * Lenguajes Automatas 2
 * Programa que lee una expresión aritmética y genera
 * su árbol de expresion junto con los 3 recorridos pertenecientes.
 */


/**
 * Clase que genera un arbol de expresión con base a un arreglo
 * que aloja cada uno de los elementos de la expresión.
 * Requiere la clase nodo para funcionar.
 */
public class ArbolExpresion {

    // Pila que alojará los nodos para procesamiento.
    Stack<Nodo> Arbol;

    /**
     * Metodo de construcción del arbol de expresión, requiere la expresión en
     * postfija y regresa un objeto tipo nodo el cual es la raíz principal del
     * arbol de expresión.
     *
     * @param expresion
     * @return
     */
    public Nodo construirArbol(String[] expresion) {
        Arbol = new Stack<>();

        // Se recorre los valores del arreglo alojandolos en una variable temporal
        for (String token : expresion) {
            // Se verifica si el token o valor del arreglo es un operador
            if (esOperador(token)) {
                //En caso de ser verdadero se crea un objeto con el valor
                Nodo Raiz = new Nodo(token);

                // Y si desapilan los 2 ultimos operadores para posteriormente
                // agregarlos como hojas en el nodo

                //Se verifica si la pila no esta vacia para agregar el nodo al lado derecho
                if (!Arbol.isEmpty()) {
                    Raiz.setNodoDerecho(Arbol.pop());
                }

                //Se verifica si la pila no esta vacia para agregar el nodo al lado izquierdo
                if (!Arbol.isEmpty()) {
                    Raiz.setNodoIzquierdo(Arbol.pop());
                }

                //Finalmente se apila el nodo
                Arbol.push(Raiz);
            }
            //Si es un operando automaticamente se apila
            else {
                Nodo Hoja = new Nodo(token);
                Arbol.push(Hoja);
            }
        }

        //Finalmente solo debe quedar un nodo en la pila el cual es el nodo raíz del arbol.
        return Arbol.pop();
    }

    //Metodo que verifica si es un operador, si es regresa true sino regresa false.
    private boolean esOperador(String caracter) {
        if (caracter.equals("+") || caracter.equals("-") || caracter.equals("/") ||
                caracter.equals("*") || caracter.equals("^")) {
            return true;
        } else {
            return false;
        }
    }
}
