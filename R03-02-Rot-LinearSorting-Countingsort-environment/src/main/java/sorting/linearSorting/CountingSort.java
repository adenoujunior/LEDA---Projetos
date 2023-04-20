package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala.
 *
 * Procure evitar desperdício de memória: AO INVÉS de alocar o array de contadores
 * com um tamanho arbitrariamente grande (por exemplo, com o maior valor de entrada possível),
 * aloque este array com o tamanho sendo o máximo inteiro presente no array a ser ordenado.
 *
 * Seu algoritmo deve assumir que o array de entrada nao possui numeros negativos,
 * ou seja, possui apenas numeros inteiros positivos e o zero.
 *
 */
public class CountingSort extends AbstractSorting<Integer> {

	@Override
    public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if (array == null || leftIndex < 0 || rightIndex >= array.length || leftIndex >= rightIndex) {
            return;
        }

        // Encontrando o maior e o menor elemento do array
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

        // Criando um array auxiliar para contar a frequência dos elementos
        int range = max - min + 1;
        int[] count = new int[range];

        // Contando a frequência dos elementos no array original
        for (int i = leftIndex; i <= rightIndex; i++) {
            count[array[i] - min]++;
        }

        // Atualizando o array original com os elementos ordenados
        int arrayIndex = leftIndex;
        for (int i = 0; i < range; i++) {
            while (count[i] > 0) {
                array[arrayIndex++] = i + min;
                count[i]--;
            }
        }
    }
}
