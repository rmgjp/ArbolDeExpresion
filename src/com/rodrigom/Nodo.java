package com.rodrigom;

public class Nodo<T> {

    private T valor;
    private Boolean esPrincipal = false;
    private Nodo<T> nodoIzquierdo = null;
    private Nodo<T> nodoDerecho = null;

    /**
     * Constructor de la clase nodo.
     * @param Valor
     */
    public Nodo(T Valor){
        this.valor = Valor;
    }

    public Nodo(T Valor, Boolean Principal){
        this.valor = Valor;
        esPrincipal = Principal;
    }

    /**
     * Getter que devuelve el valor del nodo.
     * @return
     */
    public T getValor(){
        return this.valor;
    }

    /**
     * Este metodo ayuda a clasificar el nivel de importancia que puede llegar a tener un nodo
     * basado en la jerarquia de operaciones de manera inversa, es decir, la suma y la resta tienen mayor
     * prioridad (en este caso prioridad 4), seguido de la multiplicación y division (prioridad 3),
     * despues los exponenciales (prioridad 2) y por ultimo los numeros (prioridad 1), si bien estos ultimos
     * no son un operadores, obtienen un nivel de importancia con fines de clasificación para el funcionamiento
     * del programa.
     * @return
     */
    public int getValorImportancia(){
        if(valor.toString().matches("[+,-]]")){
            return 4;
        }
        if(valor.toString().matches("[*,/]]")){
            return 3;
        }
        if(valor.toString().matches("['^']")){
            return 2;
        }
        else{
            return 1;
        }
    }

    /**
     * Este metodo agrega valor a los nodos hijo (u hojas) para la generación de un arbol.
     * Primero genera un nuevo objeto nodo para agregarle el valor y obtener el valor de importancia que tiene.
     * el funcionamiento es muy similar al de un arbol binario pero con unos cambios que se presentan a continuación.
     * @param valor
     */
    public void agregarNodo(T valor){
        //Declaración e inicialización de la hoja
        Nodo<T> nodoNuevo = new Nodo<>(valor);
        /**Para evitar que el nodo raíz principal sea remplazada (que en este caso se generará con la jerarquia antes mencionada)
         * se verifica por medio de una variable booleana si es la raíz en caso de que sea verdadera solo se agregara el nodo izquierdo
         * sino se verificara si el nodo tiene una importancia menor y en caso de que sea así se remplazará.
         */

        if(Integer.parseInt(valor.toString()) < Integer.parseInt(getValor().toString())){
                //verificamos y en caso de no ser nulo, se agregará como hoja.
                if(nodoIzquierdo != null){
                    nodoIzquierdo.agregarNodo(valor);

                }
                //en caso de que sea así se agregará directamente como hoja hermana
                else {
                    nodoIzquierdo = new Nodo<>(valor);
                }

        }
        //lo mismo a lo anterior pero sin la verificación de nodo raíz.
        else {
            if(nodoDerecho != null){
                nodoDerecho.agregarNodo(valor);
            }
            else {
                nodoDerecho = new Nodo<>(valor);
            }
        }
    }

    public T inOrden(){
        if(nodoIzquierdo != null){
            nodoIzquierdo.inOrden();
        }
        System.out.print(valor);
        if(nodoDerecho != null){
            nodoDerecho.inOrden();
        }
        return valor;
    }

    public T preOrden(){
        System.out.print(valor);
        if(nodoIzquierdo != null){
            nodoIzquierdo.preOrden();
        }
        if(nodoDerecho != null){
            nodoDerecho.preOrden();
        }
        return valor;
    }

    public T postOrden(){
        if(nodoIzquierdo != null){
            nodoIzquierdo.postOrden();
        }
        if(nodoDerecho != null){
            nodoDerecho.postOrden();
        }
        System.out.print(valor);
        return valor;
    }


}
