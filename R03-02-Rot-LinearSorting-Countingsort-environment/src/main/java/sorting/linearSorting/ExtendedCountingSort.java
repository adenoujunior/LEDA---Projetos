package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos:
 * - Alocar o tamanho minimo possivel para o array de contadores (C)
 * - Ser capaz de ordenar arrays contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

	/**
	 * Ordena um array do tipo T utilizando o algoritmo de Counting Sort estendido.
	 * O algoritmo é capaz de ordenar arrays que contenham números negativos.
	 *
	 * @param array       O array a ser ordenado.
	 * @param leftIndex   O índice esquerdo do subarray.
	 * @param rightIndex  O índice direito do subarray.
	 */
	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		// Verificação da validade dos parâmetros de entrada.
		if (array == null || array.length <= 1 || leftIndex >= rightIndex) {
            return; // Retorna imediatamente se o vetor estiver vazio ou com tamanho 1, ou se leftIndex >= rightIndex.
        }
		
		// Encontrando o maior e o menor valor no array.
        int max = array[leftIndex];
        int min = array[leftIndex];
        for (int i = leftIndex + 1; i <= rightIndex; i++) {
            if (array[i] > max) {
                max = array[i];
            }
            if (array[i] < min) {
                min = array[i];
            }
        }

        // Criando o array de contadores com tamanho mínimo possível.
        int range = max - min + 1;
        int[] count = new int[range];

        // Contando a frequência dos elementos no array
        for (int i = leftIndex; i <= rightIndex; i++) {
            count[array[i] - min]++;
        }

        // Atualizando o array original com os elementos ordenados.
        int arrayIndex = leftIndex;
        for (int i = 0; i < range; i++) {
            while (count[i] > 0) {
                array[arrayIndex++] = i + min;
                count[i]--;
            }
        }
    }
}




