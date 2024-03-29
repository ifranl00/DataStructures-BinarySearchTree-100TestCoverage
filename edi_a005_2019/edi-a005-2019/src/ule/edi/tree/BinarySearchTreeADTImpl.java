package ule.edi.tree;


import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;

import jdk.nashorn.internal.ir.BinaryNode;

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
		
		boolean isSomeoneNull = false;
		
		for (T t : elements) {
			
			if(t == null) {
				
				isSomeoneNull = true;
			}
		}
		
		if(isSomeoneNull == false) {
			
			for (T t1 : elements) {
				
				insert(t1);
			}
		}
		
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
		
		boolean isSomeoneNull = false;
		
		for (T t0 : elements) {
			
			if(t0 == null) {
				
				isSomeoneNull = true;
			
			}
		}
		
		if(isSomeoneNull == false) {
			
			for (T t1 : elements) {
				
				insert(t1);
			}
		}
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
		
			
		if(isEmpty()) {
			
			emptyBST(); //creamos un nodo vacío
			
			setContent(element);
			setLeftBST(emptyBST());
			setRightBST(emptyBST());
			
		}else { 
			
			if(this.content.equals(element) == false) {
			
					if(this.content.compareTo(element) < 0) { //bajamos por la derecha

						getRightBST().insert(element);
						
					}else if(this.content.compareTo(element) > 0) { //bajamos por la izquierda
						
						getLeftBST().insert(element);
					}
			}
		}
	}
	
	
	
	/**
	 * Elimina los elementos de la colección del árbol.
	 */
	public void withdraw(Collection<T> elements) {
        //		O todos o ninguno; si alguno es 'null', no se eliminará ningún elemento
		boolean isSomeoneNull = false;
		
		for (T t : elements) {
			
			if(t == null) {
				
				isSomeoneNull = true;
			}
		}
		
		if(isSomeoneNull == false) {
			
			for (T t1 : elements) {
				
				withdraw(t1);
			}
		}
	}

	/**
	 * Elimina los valores en un array del árbol.
	 */
	public void withdraw(T ... elements) {
	    //		O todos o ninguno; si alguno es 'null', no se eliminará ningún elemento
		boolean isSomeoneNull = false;
		
		for (T t0 : elements) {
			
			if(t0 == null) {
				
				isSomeoneNull = true;
			
			}
		}
		
		if(isSomeoneNull == false) {
			
			for (T t1 : elements) {
				
				withdraw(t1);
			}
		}
	}
	
	
	/**
	 * Elimina un elemento del árbol.
	 * 
	 * @throws NoSuchElementException si el elemento a eliminar no está en el árbol           
	 */
	public void withdraw(T element) {
		// 	Si el elemento tiene dos hijos, se tomará el criterio de sustituir el elemento por el mayor de sus menores y eliminar el mayor de los menores.
		
		
		if(isEmpty() == false) {
			
			if(this.content.equals(element) == false) {
				
				if(this.content.compareTo(element) < 0) { //bajamos por la derecha

					getRightBST().withdraw(element);
					
				}else if(this.content.compareTo(element) > 0) { //bajamos por la izquierda
					
					getLeftBST().withdraw(element);
				}
				
			}else { //borramos el nodo pero hay tres casos
				
				if(isLeaf() == true) {
					
					this.setContent(null);
					this.setLeftBST(null);
					this.setRightBST(null);
					
				}else if((getLeftBST().isEmpty() == true && getRightBST().isEmpty() == false)) { //tiene un solo hijo a la derecha
					
					this.setContent(getRightBST().content);
					
					this.setLeftBST(this.getRightBST().getLeftBST());
					this.setRightBST(this.getRightBST().getRightBST());
					
				}else if((getLeftBST().isEmpty() == false && getRightBST().isEmpty() == true)) { //tiene un solo hijo a la izquierda
					
					this.setContent(getLeftBST().content);
					 
					this.setRightBST(this.getLeftBST().getRightBST());
					this.setLeftBST(this.getLeftBST().getLeftBST());
					
				}else { //si tiene 2 hijos
					
					BinarySearchTreeADTImpl<T> aux = this.getLeftBST();
					
					while(aux.getRightBST().isEmpty() == false) {
						
						aux = aux.getRightBST();
					}
					
					this.content = aux.content;
					this.getLeftBST().withdraw(aux.content);
				}
				
				
			}
		
		}else if ((isEmpty() == true)){
			
			throw new NoSuchElementException();
		}
	}
	
	private BinarySearchTreeADTImpl<T> getSubtreeWithPathRec(String path, int index) {
			
		if(isEmpty() == false) {
			if(index < path.length()){
			
				if(path.charAt(index) == '1') {
			
					return this.getRightBST().getSubtreeWithPathRec(path, index+1);
				
				}else {
				
					return this.getLeftBST().getSubtreeWithPathRec(path, index+1);
				}
			
			}
		}else {
			
			throw new NoSuchElementException();
		}
		return this;
		
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
		
		if(isEmpty() == true) {
			
			throw new NoSuchElementException();
			
		}else if(path == "" || path == null){
			
			return this;
			
		}else {
		
			return getSubtreeWithPathRec(path, 0);
	
			}
		}
	
	public void parentChildPairsTagDescend(List<String> buffer) {
		
		int [] i = {0};
			
		parentChildPairsTagDescendRec(buffer, i);
		
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
     *
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
	private void parentChildPairsTagDescendRec(List<String> buffer, int [] i) {
			
		if(isEmpty() == false) {
			 //bajamos por la derecha mientras tenga hijo derecho

				getRightBST().parentChildPairsTagDescendRec(buffer, i);
				i[0]++;
				this.setTag("descend", i[0]);
				
				if(this.getRightBST().isEmpty() != true) { //tiene un hijo por la izq
					
					buffer.add("("+this.content + ", " + this.getRightBST().content + ")");
				}
				
				if(this.getLeftBST().isEmpty() != true) {
					
					buffer.add("(" + this.content + ", " + this.getLeftBST().content + ")");
		
				}
			
			//bajamos por la izquierda
			
			getLeftBST().parentChildPairsTagDescendRec(buffer,i);
		
		}
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
	 * Para el mismo árbol, si path es [50, 40]  devolvería false y el árbol no quedaría así etiquetado:
	 * {50 [(path, 1)], {30, {10, ∅, ∅}, {40 [(path, 2)], ∅, ∅}}, {80, {60, ∅, ∅}, ∅}}
	 * 
	 * Para el mismo árbol, si path es [50, 80]  devolvería true y el árbol  se etiqueta:
	 * {50, {30, {10, ∅, ∅}, {40, ∅, ∅}}, {80, {60, ∅, ∅}, ∅}}
	 * 
	 * 
	 * @return  true si los elementos de la lista coinciden con algún camino desde la raiz,  falso si no es así
	 */
	
	private boolean isPathInRec(List<T> path, int index, int countTag) {
		
		boolean isPath = false;
		
		if(index < path.size()) {
			
			if(path.get(index).equals(this.content) == true && index+1 < path.size()) {
				
				 //si hay mas elementos que mirar
				countTag++;
				
					if(path.get(index+1).compareTo(this.content) > 0) { //miramos cual es el siguiente para bajar por la izq o por la derch
						
						isPath = this.getRightBST().isPathInRec(path,index+1,countTag);
						
		
					}else if(path.get(index+1).compareTo(this.content) < 0) {
						
						isPath = this.getLeftBST().isPathInRec(path, index+1,countTag);
					}
					
					
			}else if(path.get(index).equals(this.content) == true && index+1 >= path.size()) {
				countTag++;
				isPath = true;
				
			}else { //se ha encontrado un elemento diferente en el camino
				
				isPath = false;
			}
		}
		
		if(isPath == true) {
			
			this.setTag("path", countTag);
		}
		
		return isPath;

	}
	
	
	public boolean isPathIn(List<T> path) {
		
		if(isEmpty() == true) {
			
			return false;
			
		}else {
			
			return isPathInRec(path, 0,0);
		}
		
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
		
		if(isEmpty() == false ) {
			
			LinkedList<BinarySearchTreeADTImpl<T>> nodeQueue = new LinkedList<BinarySearchTreeADTImpl<T>>();
	
			nodeQueue.add(this);//encolamos la raiz en la cola
			int pos = 1;
			
			while(nodeQueue.isEmpty() == false) {
				
				
					
					BinarySearchTreeADTImpl<T> elem = nodeQueue.get(0);
					
					nodeQueue.remove(0);
					elem.setTag("width", pos);
					pos++;
					
					if(elem.getLeftBST().isEmpty() != true) {
						
						nodeQueue.addLast(elem.getLeftBST());
					}
					
					if(elem.getRightBST().isEmpty() != true) {
						
						nodeQueue.addLast(elem.getRightBST());
					}
				
			}
			
			
		}
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
	 *rama izq raiz y derech
	 * 
	 * @return iterador para el recorrido inorden o ascendente
	 */
	
	private class IteratorInorden implements Iterator<T> {
		
		private BinarySearchTreeADTImpl<T> actual;
		Stack<BinarySearchTreeADTImpl<T>> nodesList;
		
		public IteratorInorden(BinarySearchTreeADTImpl<T> raiz) {
			
			actual = raiz;
			nodesList = new Stack<BinarySearchTreeADTImpl<T>>();
			
			while(actual.isEmpty() == false) {
				
				nodesList.push(actual);
				actual = actual.getLeftBST();
			}
			
		}
		
		@Override
		public boolean hasNext() {
			
			boolean hasNext = false;
			
			if(nodesList.isEmpty() == false) {
				
				hasNext = true;
			}
			
			return hasNext;
		}
		
		@Override
		public T next() {
			
			T e = null;
			
			if(hasNext() == false) {
				
				throw new NoSuchElementException();
				
			}else {
				
				BinarySearchTreeADTImpl<T> aux = nodesList.pop();
				e = aux.content;
				
				if(aux.getRightBST().isEmpty() == false) {
					
					aux = aux.getRightBST();
					
					while(aux.isEmpty() == false) {
						
						nodesList.push(aux);
						aux = aux.getLeftBST();
					}
				}
			}
			return e;
		}
		
		@Override  
		public void remove() {
			
			throw new UnsupportedOperationException();
		}
		       
		
	};
	
	
	
	public Iterator<T> iteratorInorden() {
		
		return new IteratorInorden(this);
	}	
	
}

