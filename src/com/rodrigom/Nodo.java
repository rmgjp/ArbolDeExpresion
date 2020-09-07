package com.rodrigom;

/**
 * Rodrigo Munguía Garrido
 * IS17110457
 * ITESI
 * Lenguajes Automatas 2
 * Programa que lee una expresión aritmética y genera
 * su árbol de expresion junto con los 3 recorridos pertenecientes.
 */


/**
 * Clase que aloja los atributos de un nodo, asi como metodos que imprimen
 * sus recorridos y estructura del arbol.
 */

public class Nodo {

    // Declaración de atributos del nodo
    // Un nodo es conformado por un nodo hijo izquierdo y otro derecho
    // junto con el valor que aloja.
    private String valor;
    private Nodo nodoIzquierdo = null;
    private Nodo nodoDerecho = null;

    // Conjunto de getters y setters.

    public void setNodoIzquierdo(Nodo nodoIzquierdo) {
        this.nodoIzquierdo = nodoIzquierdo;
    }

    public void setNodoDerecho(Nodo nodoDerecho) {
        this.nodoDerecho = nodoDerecho;
    }

    public String getValor() {
        return valor;
    }

    public Nodo getNodoIzquierdo() {
        return nodoIzquierdo;
    }

    public Nodo getNodoDerecho() {
        return nodoDerecho;
    }


    /**
     * Constructor de la clase nodo.
     *
     * @param Valor
     */
    public Nodo(String Valor) {
        this.valor = Valor;
    }

    /**
     * Metodo de impresion de recorrido inoden.
     */
    public void InOrden() {
        //Se verifica la existencia del nodo izquierdo
        if (nodoIzquierdo != null) {
            nodoIzquierdo.InOrden();
        }
        //Impresion del valor del nodo actual.
        System.out.print(valor);
        //Se verifica la existencia del nodo derecho
        if (nodoDerecho != null) {
            nodoDerecho.InOrden();
        }
    }

    /**
     * Metodo de impresion de recorrido preorden.
     */
    public void PreOrden() {
        //Impresion del valor del nodo actual.
        System.out.print(valor);
        //Se verifica la existencia del nodo izquierdo
        if (nodoIzquierdo != null) {
            nodoIzquierdo.PreOrden();
        }
        //Se verifica la existencia del nodo derecho
        if (nodoDerecho != null) {
            nodoDerecho.PreOrden();
        }
    }

    /**
     * Metodo de impresion de recorrido postorden.
     */
    public void PostOrden() {
        //Se verifica la existencia del nodo izquierdo
        if (nodoIzquierdo != null) {
            nodoIzquierdo.PostOrden();
        }
        //Se verifica la existencia del nodo derecho
        if (nodoDerecho != null) {
            nodoDerecho.PostOrden();
        }
        //Impresion del valor del nodo actual.
        System.out.print(valor);
    }

    // Variable constante que simboliza el numero de caracteres entre cada nivel para su impresión
    static final int caracteres = 10;

    // Funcion de impresión del Arbol
    private static void imprimir(Nodo raiz, int espacio) {
        // Se verifica si el nodo existe, de caso contrario la función termina.
        if (raiz == null)
            return;

        // Inscrementamos los espaciones entre cada nivel
        espacio += caracteres;

        // Se imprime el lado derecho primero
        imprimir(raiz.nodoDerecho, espacio);

        // Se imprime el valor del nodo al cual se está apuntando
        System.out.print("\n");
        for (int i = caracteres; i < espacio; i++)
            System.out.print(" ");
        System.out.print(raiz.valor + "\n");

        // Se imprime el nodo izquierdo.
        imprimir(raiz.nodoIzquierdo, espacio);
    }

    // Método envoltorio para impresión del arbol
    public static void imprimirArbol(Nodo Raiz) {
        imprimir(Raiz, 0);
    }

}
