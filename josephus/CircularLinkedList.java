public class CircularLinkedList extends AbstractLinkedList implements CircularCollectible {
    Node last;

    public CircularLinkedList() {
        super();
        last = null;
    }

    public CircularLinkedList(String[] elements) {
        super(elements);
    }


    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void add(String s) {
        first = new Node(first, s);
        if (size() == 0) {
            last = first;
        }
        last.next = first;
        n++;
    }

    public String first() {
        return first.value;
    }

    /** remove(String s):
    *  removes the first element in the list for which
    *      element.equals(s)
    *  is true.
    */
    public void remove(String s) {
        boolean removed = false;
        for (int i = 0; i < size() && !removed; i++) {
            if (current.next.value == s) {
                current.next = current.next.next;
                n--;
                removed = true;
            }
            next();
        }
    }

    /** removeAll(String s):
    *  removes all elements in the list for which
    *      element.equals(s)
    *  is true.
    */
    public void removeAll(String s) {
       throw new UnsupportedOperationException();
    }

    public CircularIterator iterator() {
        return new CircularLinkedListIterator();
    }

    class CircularLinkedListIterator implements CircularIterator {
        Node current;

        public CircularLinkedListIterator() {
            current = first;
        }

        public boolean hasNext() {
            return size() == 0;
        }

        public String next() {
            current = current.next;
            return current.value;
        }

        public void remove() {

        }

        public String removeKthElement(int k) {
            for (int i = 1; i < k; i++) {
                next();
            }
            current.next = current.next.next;
        }

        public boolean oneElementLeft() {
            return n == 1;
        }
    }
}
