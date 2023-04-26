package orderStatistic;

import util.Util;

/**
 * O quickselect eh um algoritmo baseado no quicksort para
 * descobrir/selectionar, em tempo linear, a k-esima estatistica de ordem
 * (k-esimo menor elemento) de um conjunto de dados.
 *
 * O quiskselect escolhe um elemento para ser o pivot e particiona o array
 * inicial em dois subarrays da mesma forma que o quicksort (elementos menores
 * que o pivot a esquerda do pivot e elementos maiores que o pivot a direita
 * dele). Entretanto, ao inves de chamar o quicksort recursivo nas duas metades,
 * o quickselect eh executado (recursivamente) apenas na metade que contem o
 * elemento que ele procura (o k-esimo menor elemento). Isso reduz a
 * complexidade de O(n.log n) para O(n)
 *
 * @author adalberto e campelo
 *
 */
public class QuickSelect<T extends Comparable<T>> {

	/**
	 * O algoritmo quickselect usa a mesma abordagem do quicksort para calcular o
	 * k-esimo menor elemento (k-esima estatistica de ordem) de um determinado
	 * array de dados comparaveis. Primeiro ele escolhe um elemento como o pivot
	 * e particiona os dados em duas partes, baseando-se no pivot (exatemente da
	 * mesma forma que o quicksort). Depois disso, ele chama recursivamente o
	 * mesmo algoritmo em apenas uma das metades (a que contem o k-esimo menor
	 * elemento). Isso reduz a complexidade de O(n.log n) para O(n).
	 *
	 * Caso o array seja vazio ou a ordem (posicao) do elemento desejado esteja
	 * fora do tamanho do array, o metodo deve retornar null.
	 *
	 *
	 * @param array
	 *            o array de dados a procurar o k-esimo menor elemento
	 *            este array normalmente nao esta ordenado
	 * @param k
	 *            a ordem do elemento desejado. 1 significa primeiro menor
	 *            elemento, 2 significa segundo menor elemento e assim por
	 *            diante
	 * @return
	 *
	 */

	private int partition(T[] array, int low, int high) {
		// Escolhe o pivot como o elemento do meio.
		int middle = low + (high - low) / 2;
		T pivot = array[middle];

		// Move o pivot para o final do array.
		Util.swap(array, middle, high);

		int i = low;
		// Particiona o array em dois subarrays.
		for (int j = low; j < high; j++) {
			if (array[j].compareTo(pivot) <= 0) {
				Util.swap(array, i, j);
				i++;
			}
		}

		// Move o pivot para a posição correta.
		Util.swap(array, i, high);

		// Retorna a posição do pivot.
		return i;
	}

	public T quickSelect(T[] array, int k) {
		if (array == null || array.length == 0 || k <= 0 || k > array.length) {
			return null; // Retorna null se o array for vazio ou se a ordem estiver fora do tamanho do array.
		}
		return quickSelect(array, k, 0, array.length - 1);
	}

	private T quickSelect(T[] array, int k, int low, int high) {
		if (low == high) { // Se o intervalo tem apenas um elemento, retorna esse elemento
			return array[low];
		}

		int pivotIndex = partition(array, low, high); // Encontra o índice do pivot após a partição

		if (k == pivotIndex + 1) { // Se o k-esimo menor elemento é o pivot, retorna o pivot
			return array[pivotIndex];
		} else if (k < pivotIndex + 1) { // Se o k-esimo menor elemento está na parte esquerda do pivot
			return quickSelect(array, k, low, pivotIndex - 1);
		} else {
			return quickSelect(array, k, pivotIndex + 1, high);
		}
	}
}