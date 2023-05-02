package problems;

public class FloorBinarySearchImpl implements Floor {

	@Override
	public Integer floor(Integer[] array, Integer x) {
		int left = 0;
        int right = array.length - 1;
        Integer floor = null;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (array[mid].compareTo(x) == 0) {
                return array[mid];
            } else if (array[mid] < x) {
                floor = array[mid];
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return floor;
    }

}
