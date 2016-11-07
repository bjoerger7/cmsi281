public class CircularArrayList extends AbstractArrayList implements CircularCollectible {

    public CircularArrayList() {
        super();
    }

    public CircularArrayList(String[] elements) {
        super(elements);
    }


    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void add(String s) {
        // if you still have room in your static array
        if (size < arraySize) {
            // you can just add it like normal
            elements[size] = s;
            size++;
        } else {
            // otherwise...
            // you have to double the array to make more space
            arraySize = arraySize * 2;
            String[] doubleElements = new String[arraySize];

            // and then copy the elements over one by one...
            for (int i = 0; i < size; i++) {
                doubleElements[i] = elements[i];
            }

            elements = doubleElements;

            // ...and then you can add the new element like normal
            elements[size] = s;
            size++;
        }
    }

    public String first() {
        return elements[0];
    }

    /** remove(String s):
    *  removes the first element in the list for which
    *      element.equals(s)
    *  is true.
    */
    public void remove(String s) {
        boolean removed = false;
        for (int i = 0; i < size && !removed; i++) {
            if (elements[i] == s) {
                elements[i] = null;
                removed = true;
            }
        }
    }

    /** removeAll(String s):
    *  removes all elements in the list for which
    *      element.equals(s)
    *  is true.
    */
    public void removeAll(String s) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == s) {
                elements[i] = null;
            }
        }
    }

    public CircularIterator iterator() {
        return new CircularArrayListIterator();
    }

    class CircularArrayListIterator implements CircularIterator {
        int currentIndex;

        public CircularArrayListIterator() {
            currentIndex = 0;
        }

        public boolean hasNext() {
            return size == 0;
        }

        public String next() {
            do {
                currentIndex++;
                if (currentIndex >= arraySize) {
                    currentIndex -= arraySize;
                }
            } while (elements[currentIndex] == null);
            return elements[currentIndex];
        }

        public void remove() {
            elements[currentIndex] = null;
            size--;
        }

        public String removeKthElement(int k) {
            for (int i = 0; i < k; i++) {
                next();
            }
            String kthElement = elements[currentIndex];
            remove();
            return kthElement;
        }

        public boolean oneElementLeft() {
            return size == 1;
        }
    }

}
