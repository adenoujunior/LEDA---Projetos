package orderStatistic;

import java.util.Arrays;
import util.Util;

/**
 * Uma implementacao da interface KLargest que usa estatisticas de ordem para 
 * retornar um array com os k maiores elementos de um conjunto de dados/array.
 * 
 * A k-esima estatistica de ordem de um conjunto de dados eh o k-esimo menor
 * elemento desse conjunto. Por exemplo, considere o array [4,8,6,9,12,1]. 
 * A 3a estatistica de ordem eh 6, a 6a estatistica de ordem eh 12.
 * 
 * Note que, para selecionar os k maiores elementos, pode-se pegar todas as 
 * estatisticas de ordem maiores que k. 
 * 
 * Requisitos do algoritmo:
 * - DEVE ser in-place. Porem, voce pode modificar o array original.
 *   Voce pode criar ainda um array de tamanho k que vai armazenar apenas
 *   os elementos a serem retornados.
 * - Voce DEVE usar alguma ideia de algoritmo de ordenacao visto em sala 
 *   para calcular estatisticas de ordem.
 * - Se a entrada for invalida, deve-se retornar um array vazio (por exemplo,
 *   ao solicitar os 5 maiores elementos em um array que soh contem 3 elementos).
 *   Este metodo NUNCA deve retornar null.
 * 
 * @author campelo and adalberto
 *
 * @param <T>
 */
public class KLargestOrderStatisticsImpl<T extends Comparable<T>> implements KLargest<T>{

	@Override
	public T[] getKLargest(T[] array, int k) {
		 if (array == null || k < 1 || k > array.length) {
	            return Arrays.copyOf(array, 0);
	        }

	        T[] kLargest = Arrays.copyOf(array, k);
	        for (int i = k / 2 - 1; i >= 0; i--) {
	            heapifyMax(kLargest, k, i);
	        }

	        for (int i = k; i < array.length; i++) {
	            if (array[i].compareTo(kLargest[0]) > 0) {
	                kLargest[0] = array[i];
	                heapifyMax(kLargest, k, 0);
	            }
	        }

	        return kLargest;
	}

	/**
	 * Metodo que retorna a k-esima estatistica de ordem de um array, usando
	 * a ideia de algum algoritmo de ordenacao visto em sala. Caso nao exista 
	 * a k-esima estatistica de ordem, entao retorna null.
	 * 
	 * Obs: o valor de k deve ser entre 1 e N.
	 * 
	 * @param array
	 * @param k
	 * @return
	 */
	public T orderStatistics(T[] array, int k){
		if (array == null || k < 1 || k > array.length) {
            return null;
        }

        return orderStatistics(array, 0, array.length - 1, k);		
	}
	
	private T orderStatistics(T[] array, int left, int right, int k) {
        if (left == right) {
            return array[left];
        }

        int pivotIndex = partition(array, left, right);
        int pivotRank = pivotIndex - left + 1;

        if (k == pivotRank) {
            return array[pivotIndex];
        } else if (k < pivotRank) {
            return orderStatistics(array, left, pivotIndex - 1, k);
        } else {
            return orderStatistics(array, pivotIndex + 1, right, k - pivotRank);
        }
    }
	
	private int partition(T[] array, int left, int right) {
        int pivotIndex = left + (right - left) / 2;
        T pivotValue = array[pivotIndex];

        Util.swap(array, pivotIndex, right);

        int storeIndex = left;
        for (int i = left; i < right; i++) {
            if (array[i].compareTo(pivotValue) < 0) {
                Util.swap(array, i, storeIndex);
                storeIndex++;
            }
        }

        Util.swap(array, storeIndex, right);
        return storeIndex;
    }

    private void heapifyMax(T[] array, int heapSize, int rootIndex) {
        int leftIndex = 2 * rootIndex + 1;
        int rightIndex = 2 * rootIndex + 2;

        int largestIndex = rootIndex;
        if (leftIndex < heapSize && array[leftIndex].compareTo(array[largestIndex]) > 0) {
            largestIndex = leftIndex;
        }

        if (rightIndex < heapSize && array[rightIndex].compareTo(array[largestIndex]) > 0) {
            largestIndex = rightIndex;
        }

        if (largestIndex != rootIndex) {
            Util.swap(array, rootIndex, largestIndex);
            heapifyMax(array, heapSize, largestIndex);
        }
    }
	
}
