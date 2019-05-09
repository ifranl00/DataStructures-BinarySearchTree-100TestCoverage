package ule.edi.tree;


import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * Árbol binario de búsqueda (binary search tree, BST).
 * 
 * El código fuente está en UTF-8, y la constante 
 * EMPTY_TREE_MARK definida en AbstractTreeADT del
 * proyecto API debería ser el símbolo de conjunto vacío: ∅
 * 
 * Si aparecen caracteres "raros", es porque
 * el proyecto no está bien configurado en Eclipse para
 * usar esa codificación de caracteres.
 *
 * En el toString() que está ya implementado en AbstractTreeADT
 * se usa el formato:
 * 
 * 		Un árbol vacío se representa como "∅". Un árbol no vacío
 * 		como "{(información raíz), sub-árbol 1, sub-árbol 2, ...}".
 * 
 * 		Por ejemplo, {A, {B, ∅, ∅}, ∅} es un árbol binario con 
 * 		raíz "A" y un único sub-árbol, a su izquierda, con raíz "B".
 * 
 * El método render() también representa un árbol, pero con otro
 * formato; por ejemplo, un árbol {M, {E, ∅, ∅}, {S, ∅, ∅}} se
 * muestra como:
 * 
 * M
 * |  E
 * |  |  ∅
 * |  |  ∅
 * |  S
 * |  |  ∅
 * |  |  ∅
 * 
 * Cualquier nodo puede llevar asociados pares (clave,valor) para
 * adjuntar información extra. Si es el caso, tanto toString() como
 * render() mostrarán los pares asociados a cada nodo.
 * 
 * Con {@link #setTag(String, Object)} se inserta un par (clave,valor)
 * y con {@link #getTag(String)} se consulta.
 * 
 * 
 * Con <T extends Comparable<? super T>> se pide que exista un orden en
 * los elementos. Se necesita para poder comparar elementos al insertar.
 * 
 * Si se usara <T extends Comparable<T>> sería muy restrictivo; en
 * su lugar se permiten tipos que sean comparables no sólo con exactamente
 * T sino también con tipos por encima de T en la herencia.
 * 
 * @param <T>
 *            tipo de la información en cada nodo, comparable.
 */
public class BinarySearchTreeADTImpl<T extends Comparable<? super T>> extends
		AbstractBinaryTreeADT<T> {

	/**
	 * Devuelve el árbol binario de búsqueda izquierdo.
	 */
	protected BinarySearchTreeADTImpl<T> getLeftBST() {
		//	El atributo leftSubtree es de tipo AbstractBinaryTreeADT<T> pero
		//	aquí se sabe que es además de búsqueda binario
		//
		return (BinarySearchTreeADTImpl<T>) leftSubtree;
	}

	private void setLeftBST(BinarySearchTreeADTImpl<T> left) {
		this.leftSubtree = left;
	}
	
	/**
	 * Devuelve el árbol binario de búsqueda derecho.
	 */
	protected BinarySearchTreeADTImpl<T> getRightBST() {
		return (BinarySearchTreeADTImpl<T>) rightSubtree;
	}

	private void setRightBST(BinarySearchTreeADTImpl<T> right) {
		this.rightSubtree = right;
	}
	
	/**
	 * Árbol BST vacío
	 */
	public BinarySearchTreeADTImpl() {
		
		setContent(null);
		
		setLeftBST(null);
		setRightBST(null);
	}

	private BinarySearchTreeADTImpl<T> emptyBST() {
		return new BinarySearchTreeADTImpl<T>();
	}
	
	/**
	 * Inserta todos los elementos de una colección en el árbol.
	 * 
	 * No se permiten elementos null.
	 * 
	 * @param elements
	 *            valores a insertar.
	 */
	public void insert(Collection<T> elements) {
		//	O todos o ninguno; si alguno es 'null', ni siquiera se comienza a insertar
		//TODO Implementar el método
		
	}

	/**
	 * Inserta todos los elementos de un array en el árbol.
	 * 
	 * No se permiten elementos null.
	 * 
	 * @param elements elementos a insertar.
	 */
	public void insert(T ... elements) {
		//	O todos o ninguno; si alguno es 'null', ni siquiera se comienza a insertar
	    // TODO Implementar el método
		
	}
	
	/**
	 * Inserta de forma recursiva (como hoja) un nuevo elemento en el árbol de búsqueda.
	 * 
	 * No se permiten elementos null. Si el elemento ya existe en el árbol NO lo inserta.
	 * 
	 * @param element
	 *            valor a insertar.
	 */
	public void insert(T element) {
		//	No se admiten null
		if (element == null) {
			throw new IllegalArgumentException("No se aceptan elementos nulos");
		}
		//	TODO Implementar el método
	}
	
	
	
	/**
	 * Elimina los elementos de la colección del árbol.
	 */
	public void withdraw(Collection<T> elements) {
        //		O todos o ninguno; si alguno es 'null', no se eliminará ningún elemento
	    // TODO Implementar el método
	}

	/**
	 * Elimina los valores en un array del árbol.
	 */
	public void withdraw(T ... elements) {
	    //		O todos o ninguno; si alguno es 'null', no se eliminará ningún elemento
	    // TODO Implementar el método
	}
	
	/**
	 * Elimina un elemento del árbol.
	 * 
	 * @throws NoSuchElementException si el elemento a eliminar no está en el árbol           
	 */
	public void withdraw(T element) {
		// 	Si el elemento tiene dos hijos, se tomará el criterio de sustituir el elemento por el mayor de sus menores y eliminar el mayor de los menores.
		// TODO Implementar el método
	}
	
	/**
	 * Devuelve el sub-árbol indicado. (para tests)
	 * path será el camino para obtener el sub-arbol. Está formado por 0 y 1.
	 * Si se codifica "bajar por la izquierda" como "0" y
	 * "bajar por la derecha" como "1", el camino desde un 
	 * nodo N hasta un nodo M (en uno de sus sub-árboles) será la
	 * cadena de 0s y 1s que indica cómo llegar desde N hasta M.
     *
     * Se define también el camino vacío desde un nodo N hasta
     * él mismo, como cadena vacía.
	 * 
	 * Si el subarbol no existe lanzará la excepción NoSuchElementException.
	 * 
	 * @param path
	 * @return
	 * @throws NoSuchElementException si el subarbol no existe
	 */
	public BinarySearchTreeADTImpl<T> getSubtreeWithPath(String path) {
		//TODO implementar el método
		
		return null;
		
	}	
	
	/**
	 * Acumula en orden descendente, una lista con los pares 'padre-hijo' en este árbol.
	 * 
	 * Por ejemplo, sea un árbol "A":
	 * 
	 * {10, {5, {2, ∅, ∅}, ∅}, {20, ∅, {30, ∅, ∅}}}
	 * 
     * 10
     * |  5
     * |  |  2
     * |  |  |  ∅
     * |  |  |  ∅
     * |  |  ∅
     * |  20
     * |  |  ∅
     * |  |  30
     * |  |  |  ∅
     * |  |  |  ∅
     * 
	 * el resultado sería una lista de cadenas:
	 * 
	 * 	[(20,30), (10,20), (10,5), (5,2)]
	 * 
	 * y además quedaría etiquetado como:
	 * 
	 *  {10 [(descend, 3)], 
	 *       {5 [(descend, 4)], {2 [(descend, 5)], ∅, ∅}, ∅}, 
	 *       {20 [(descend, 2)], ∅, {30 [(descend, 1)], ∅, ∅}}}
	 * 
	 * @param buffer lista con el resultado.
	 */
	public void parentChildPairsTagDescend(List<String> buffer) {
	
		// TODO Implementar el método
	}
		
	
	
	/**
	 * Importante: Solamente se debe recorrer el árbol una vez
	 * 
	 * Comprueba si los elementos de la lista coinciden con algún camino desde la raiz.
	 * Además, si existe algún camino que coincida con los elementos de la lista, los etiqueta en el árbol,
	 * numerandolos empezando por la raiz como 1.
	 * 
	 * Por ejemplo, el árbol
	 * 
	 * {50, {30, {10, ∅, ∅}, {40, ∅, ∅}}, {80, {60, ∅, ∅}, ∅}}
	 * 
	 * si path = [50, 30, 10]
	 * 
	 * devolvería true y el árbol quedaría así etiquetado:
	 * 
	 * {50 [(path, 1)], {30 [(path, 2)], {10 [(path, 3)], ∅, ∅}, {40, ∅, ∅}}, {80, {60, ∅, ∅}, ∅}}
	 * 
	 * Para el mismo árbol, si path es [50, 40]  devolvería true y el árbol quedaría así etiquetado:
	 * {50 [(path, 1)], {30, {10, ∅, ∅}, {40 [(path, 2)], ∅, ∅}}, {80, {60, ∅, ∅}, ∅}}
	 * 
	 * Para el mismo árbol, si path es [50, 80]  devolvería false y el árbol no se etiqueta:
	 * {50, {30, {10, ∅, ∅}, {40, ∅, ∅}}, {80, {60, ∅, ∅}, ∅}}
	 * 
	 * 
	 * @return  true si los elementos de la lista coinciden con algún camino desde la raiz,  falso si no es así
	 */
	public boolean isPathIn(List<T> path) {
		//	TODO Implementar método
		return false;
	}

	
	/**
	 * 
	 * Etiqueta cada nodo con su posición en el recorrido en anchura, con la etiqueta "width"
	 * 
	 *  Por ejemplo, el árbol
	 * 
	 * {50, {30, {10, ∅, ∅}, {40, ∅, ∅}}, {80, {60, ∅, ∅}, ∅}}
	 * 
	 *  queda etiquetado como 
	 * 
	 *   {50 [(width, 1)], 
                 {30 [(width, 2)], {10 [(width, 4)], ∅, ∅},{40 [(width, 5)], ∅, ∅}},
                 {80 [(width, 3)], {60 [(width, 6)], ∅, ∅}, ∅}}
	 * 

	 */	
	public void tagWidth(){
		//	TODO Implementar método
	}
	
	
	/**	
	 * Devuelve un iterador que recorre los elementos del arbol en inorden (de menor a mayor)
	 * 
	 * Por ejemplo, con el árbol
	 * 
	 * 		{50, {30, {10, ∅, ∅}, {40, ∅, ∅}}, {80, {60, ∅, ∅}, ∅}}
	 * 
	 * y devolvería el iterador que recorrería los ndos en el orden: 10, 30, 40, 50, 60, 80
	 * 
	 * 		
	 * 
	 * @return iterador para el recorrido inorden o ascendente
	 */
	public Iterator<T> iteratorInorden() {
		//	TODO Implementar método
		return null;
	}	
	
}

