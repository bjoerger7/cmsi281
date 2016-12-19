public LinkedListSet {
    Node first;
    int n;

    public LinkedListSet() {
        first = null;
        n = 0;
    }

    // this is O(n)
    public void add(String s) {
        if (!this.isDupe(s)) {
            first = new Node(first, s);
            n++;
        }
    }

    // this is O(n)
    public boolean isDupe(String s) {
        for (Node n = first; n != null; n = n.next) {
            if (n.value.equals(s)) {
                return true;
            }
        }
        return false;
    }

    // this is O(n)
    public void remove(String s) {
        for (Node n = new Node(first, null); n.next != null; n = n.next) {
            if (n.next.value.equals(s)) {
                n.next = n.next.next;
            }
        }
    }

    public Node {
        Node next;
        String value;

        public Node(Node next, String value) {
            this.next = next;
            this.value = value;
        }
    }
}
