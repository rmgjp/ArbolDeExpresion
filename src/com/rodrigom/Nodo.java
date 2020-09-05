package com.rodrigom;

public class Nodo {

    private String valor;
    private Nodo nodoIzquierdo = null;
    private Nodo nodoDerecho = null;

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
     * @param Valor
     */
    public Nodo(String Valor){
        this.valor = Valor;
    }
}
