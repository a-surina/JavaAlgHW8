public class HashTable {
    private Flat[] hashArr;
    private int arrSize;
    private final Flat nonFlat;

    public HashTable(int size) {
        this.arrSize = size;
        this.hashArr = new Flat[arrSize];
        this.nonFlat = new Flat(-1, "");
    }

    public void onlyFlatsPrinter() {
        for (int i = 0; i < this.arrSize; i++) {
            if (this.hashArr[i] != null) {
                System.out.println(i + ". " + this.hashArr[i].getKey());
            } else {
                System.out.println(i + ". ***");
            }
        }
    }

    public void fullPrinter() {
        for (int i = 0; i < this.arrSize; i++) {
            if (this.hashArr[i] != null) {
                System.out.println(i + ". " + this.hashArr[i].printer());
            } else {
                System.out.println(i + ". ***");
            }
        }
    }

    private int hashFunc(int key) {
        return key % this.arrSize;
    }

    public boolean insert(Flat flat) {
        int key = flat.getKey();
        int hashVal = hashFunc(key);
        int j = 0;
        while (j < this.arrSize) {
            if (this.hashArr[hashVal] == null) {
                this.hashArr[hashVal] = flat;
                return true;
            } else {
                ++hashVal;
                hashVal %= this.arrSize;
                j++;
            }
        }
        reHash();
        return insert(flat);
    }

    public boolean delete(int key) {
        int hashVal = hashFunc(key);
        int j = 0;
        while (j < this.arrSize) {
            if (this.hashArr[hashVal] != null &&
                    this.hashArr[hashVal].getKey() != -1 &&
                    this.hashArr[hashVal].getKey() == key) {
                this.hashArr[hashVal] = nonFlat;
                return true;
            } else {
                ++hashVal;
                hashVal %= this.arrSize;
                j++;
            }
        }
        return false;
    }

    public Flat find(int key) {
        int hashVal = hashFunc(key);
        int j = 0;
        while (j < this.arrSize) {
            if (this.hashArr[hashVal] != null &&
                    this.hashArr[hashVal].getKey() != -1 &&
                    this.hashArr[hashVal].getKey() == key) {
                return this.hashArr[hashVal];
            } else {
                ++hashVal;
                hashVal %= this.arrSize;
                j++;
            }
        }
        return nonFlat;
    }

    private int getNextSize(int currentSize) {
        for (int i = currentSize + 1; true; i++) {
            if (isPrime(i)) {
                return i;
            }
        }
    }

    private boolean isPrime(int number) {
        for (int j = 2; (j * j <= number); j++) {
            if (number % j == 0) {
                return false;
            }
        }
        return true;
    }

    private void reHash() {
        Flat[] oldArr = this.hashArr.clone();
        int oldSize = this.arrSize;
        int newSize = getNextSize(oldSize);
        this.hashArr = new Flat[newSize];
        this.arrSize = newSize;
        for (Flat flat : oldArr) {
            if (flat != null && flat.getKey() != -1) {
                this.insert(flat);
            }
        }
    }
}
