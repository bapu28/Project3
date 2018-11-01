/**
 * 
 */

/**
 * @author shaha
 *
 */
public class VSort {

    private int arraySize;
    private DataArray dataArray;


    public VSort(DataArray array) {
        arraySize = array.size();
        this.dataArray = array;
    }


    public void sort() {
        this.quickSort(0, arraySize - 1, 3);
    }


    private void quickSort(int left, int right, int div) {
        int length = right - left;
        int third = length / div;

        int a1 = left + third;
        int a2 = right - third;

        if (dataArray.getKey(a1) < dataArray.getKey(a2)) {
            dataArray.swap(a1, left);
            dataArray.swap(a2, right);
        }
        else {
            dataArray.swap(a1, right);
            dataArray.swap(a2, left);
        }

        int pivot1 = dataArray.getKey(left);
        int pivot2 = dataArray.getKey(right);

        // get pointers
        int less = left + 1;
        int great = right - 1;

        // sorting
        for (int k = less; k <= great; k++) {
            if (dataArray.getKey(k) < pivot1) {
                dataArray.swap(k, less++);
            }
            else if (dataArray.getKey(k) > pivot2) {
                while (k < great && dataArray.getKey(great) > pivot2) {
                    great--;
                }

                dataArray.swap(k, great--);

                if (dataArray.getKey(k) < pivot1) {
                    dataArray.swap(k, less++);
                }
            }
        }

        // swaps
        int dist = great - less;
        if (dist < 13) {
            div++;
        }

        dataArray.swap(less - 1, left);
        dataArray.swap(great + 1, right);

        // subarrays
        quickSort(left, less - 2, div);
        quickSort(great + 2, right, div);

        // equal elements
        if (dist > length - 13 && pivot1 != pivot2) {
            for (int k = less; k <= great; k++) {
                if (dataArray.getKey(k) == pivot1) {
                    dataArray.swap(k, less++);
                }
                else if (dataArray.getKey(k) == pivot2) {
                    dataArray.swap(k, great--);

                    if (dataArray.getKey(k) == pivot1) {
                        dataArray.swap(k, less++);
                    }
                }
            }
        }

        if (pivot1 < pivot2) { // subarray
            quickSort(less, great, div);
        }
    }

}
