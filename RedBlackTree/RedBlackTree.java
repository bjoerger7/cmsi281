public class RedBlackTree {
    Node root;
    int size;

    public RedBlackTree() {
        root = null;
    }

    // inserts a node with value x as if the tree were a binary search tree, then climbs up the tree making sure it still
    // has the red black properties.
    public void insert(int x) {
        Node n = binaryInsert(x);
        Node p, g, y;
        while (n != null && n.parent != null && n.parent.parent != null && n.color == Color.RED) {
            p = n.parent;
            g = p.parent;
            if (g.left == p) {
                y = g.right;
                if (y != null && y.color == Color.RED) {
                    p.color = Color.BLACK;
                    y.color = Color.BLACK;
                    g.color = Color.RED;
                } else {
                    if (n == p.right) {
                        rotateLeft(n, p);
                        Node temp = n;
                        n = p;
                        p = temp;
                    }
                    rotateRight(p, g);
                    n = n.parent.parent;
                }
            } else {
                y = g.left;
                if (y != null && y.color == Color.RED) {
                    p.color = Color.BLACK;
                    y.color = Color.BLACK;
                    g.color = Color.RED;
                } else {
                    if (n == p.left) {
                        rotateRight(n, p);
                        Node temp = n;
                        n = p;
                        p = temp;
                    }
                    rotateLeft(p, g);
                }
            }
            n = n.parent.parent;
        }
        root.color = Color.BLACK;
    }

    // reconstructs part of the tree so that x's parent becomes its left child, x's left child becomes it's parent's
    // right child, and x's grandparent becomes it's parent.
    public void rotateLeft(Node x, Node p) {
        Color temp = x.color;
        x.color = p.color;
        p.color = temp;

        x.parent = p.parent;
        if (x.parent != null) {
            p.parent.right = x;
        } else {
            root = x;
        }
        p.parent = x;
        p.right = x.left;
        if (p.right != null) p.right.parent = p;
        x.left = p;
    }

    // same as rotateLeft change (left <-> right).
    public void rotateRight(Node x, Node p) {
        Color temp = x.color;
        x.color = p.color;
        p.color = temp;

        if (p.parent != null) {
            p.parent.left = x;
        } else {
            root = x;
        }
        p.parent = x;
        p.left = x.right;
        if (p.left != null) p.left.parent = p;
        x.right = p;
    }

    // Inserts node with value x into the tree as if it was a BinarySearchTree. If the node already exists,
    // it will simply return the existing node.
    public Node binaryInsert(int x) {
        if (root == null) {
            root = new Node(x);
            return root;
        }
        Node current = root;
        while (true) {
            if (x < current.val) {
                if (current.left != null) {
                    current = current.left;
                } else {
                    current.left = new Node(x);
                    current.left.parent = current;
                    size++;
                    return current.left;
                }
            } else if (x > current.val) {
                if (current.right != null) {
                    current = current.right;
                } else {
                    current.right = new Node(x);
                    current.right.parent = current;
                    size++;
                    return current.right;
                }
            } else {
                return current;
            }
        }
    }

    // searches for a node with the given value in the tree and returns either the node or null if it is not in the tree.
    public Node search(int x) {
        Node current = root;
        while (current != null) {
            if (x < current.val) {
                current = current.left;
            } else if (x > current.val) {
                current = current.right;
            } else {
                return current;
            }
        }
        return current;
    }

    // traverses through the red black tree, outputting the value of each node with the pre-order method.
    public void preOrderTraversal(Node n) {
        if (n != null) {
            preOrderTraversal(n.left);
            System.out.println(n.val);
            preOrderTraversal(n.right);
        }
    }

    public class Node {
        int val;
        Color color;
        Node left;
        Node right;
        Node parent;

        public Node(int val) {
            this.val = val;
            color = Color.RED;
            left = null;
            right = null;
            parent = null;
        }
    }

    public enum Color {
        RED, BLACK
    }

    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();

        for (int i = 0; i < args.length; i++) {
            tree.insert(Integer.parseInt(args[i]));
        }

        tree.preOrderTraversal(tree.root);
    }
}
