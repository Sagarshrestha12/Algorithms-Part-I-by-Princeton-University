// Boitin array is such type of the array that consists of both the ascending and descending order elements

public class BioticClaas {
    public int findIndex(int[] a) {
        int l = 0;
        int h = a.length - 1;
        int mid;
        while (h != l) {
            mid = l + (h - l) / 2;
            if (a[mid] >= a[mid - 1] && a[mid] >= a[mid + 1]) {
                return mid;
            }
            if (a[mid] >= a[mid - 1] && a[mid] <= a[mid + 1])// i.e if mid element is at the ascending order
            {
                l = mid + 1;
            }
            if (a[mid] <= a[mid - 1] && a[mid] >= a[mid + 1]) {// i.e if mid element is at descending order
                h = mid - 1;
            }
        }
        return (-l);
    }


    public int findAtleft(int[] a, int index, int key)// key is element we are searching in left part of the array
    {
        int mid;
        int l = 0;
        int h = index - 1;// index
        while (l != h) {
            mid = l + (h - l) / 2;
            if (a[mid] == key) {
                return mid;
            }
            if (a[mid] > key) {
                l = mid + 1;
            }
            if (a[mid] < key) {
                h = mid - 1;
            }

        }
        if (a[l] == key) {
            return l;
        } else {
            return -1;
        }
    }

    public int rightAt(int[] a, int index, int key)// key is element we are searching in left part of the array
    {
        int mid;
        int l = index + 1;
        int h = a.length - 1;// index
        while (l != h) {
            mid = l + (h - l) / 2;
            if (a[mid] == key) {
                return mid;
            }
            if (a[mid] < key) {
                h = mid - 1;
            }
            if (a[mid] > key) {
                l = mid + 1;
            }

        }
        if (a[l] == key) {
            return l;
        } else {
            return -1;
        }


    }


    public int bitonicArray(int[] a, int key) {// finding the index of the elements(key) in an bitonic array
        int index = findIndex(a);
        if (a[index] == key) {
            return index;
        }
        if (key > a[index]) {
            return -1;
        }
        int x = findAtleft(a, index, key);
        if (x != -1) {
            return rightAt(a, index, key);
        }
        return x;


    }


    public static void main(String[] args) {
        int a[] = new int[]{
                7, 8, 9, 13, 17, 20, 4, 3, 2, 1
        };
        BioticClaas x = new BioticClaas();
        System.out.println(x.bitonicArray(a, 17));


    }
}
