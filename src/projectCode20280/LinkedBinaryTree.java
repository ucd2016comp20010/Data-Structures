package projectCode20280;

/**
 * Concrete implementation of a binary tree using a node-based, linked structure.
 */
public class LinkedBinaryTree<E extends Comparable<E>> extends AbstractBinaryTree<E> {


    /**
     * Nested static class for a binary tree node.
     */
    protected static class Node<E> implements Position<E> {
        private E element;
        private Node<E> parent;
        private Node<E> left;
        private Node<E> right;

        public Node(E e, Node<E> parent, Node<E> leftChild, Node<E> rightChild) {
            element = e;
            parent = parent;
            left = leftChild;
            right = rightChild;
        }

        // accessors
        public E getElement() {
            return element;
        }

        public Node<E> getParent() {
            return parent;
        }

        public Node<E> getLeft() {
            return left;
        }

        public Node<E> getRight() {
            return right;
        }

        // mutators
        public void setElement(E e) {
            element = e;
        }

        public void setParent(Node<E> parentNode) {
            parent = parentNode;
        }

        public void setLeft(Node<E> leftChild) {
            left = leftChild;
        }

        public void setRight(Node<E> rightChild) {
            right = rightChild;
        }
    }

    /**
     * Factory function to create a new node storing element e.
     */
    protected Node<E> createNode(E e, Node<E> parent,
                                 Node<E> left, Node<E> right) {
        return new Node<E>(e, parent, left, right);
    }

    // LinkedBinaryTree instance variables
    /**
     * The root of the binary tree
     */
    protected Node<E> root = null;

    /**
     * The number of nodes in the binary tree
     */
    private int size = 0;

    // constructor

    /**
     * Construts an empty binary tree.
     */
    public LinkedBinaryTree() {
    }

    /**
     * Verifies that a Position belongs to the appropriate class, and is
     * not one that has been previously removed. Note that our current
     * implementation does not actually verify that the position belongs
     * to this particular list instance.
     *
     * @param p a Position (that should belong to this tree)
     * @return the underlying Node instance for the position
     * @throws IllegalArgumentException if an invalid position is detected
     */
    protected Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof Node))
            throw new IllegalArgumentException("Not valid position type");
        Node<E> node = (Node<E>) p;// safe cast
        if (node.getParent() == node)     // our convention for defunct node
            throw new IllegalArgumentException("p is no longer in the tree");
        return node;
    }

    // accessor methods (not already implemented in AbstractBinaryTree)

    /**
     * Returns the number of nodes in the tree.
     *
     * @return number of nodes in the tree
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns the root Position of the tree (or null if tree is empty).
     *
     * @return root Position of the tree (or null if tree is empty)
     */
    @Override
    public Position<E> root() {
        return root;
    }

    /**
     * Returns the Position of p's parent (or null if p is root).
     *
     * @param p A valid Position within the tree
     * @return Position of p's parent (or null if p is root)
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     */
    @Override
    public Position<E> parent(Position<E> p) throws IllegalArgumentException {
        // validate position
        Node<E> temp = validate(p);

        // return its parent
        return temp.getParent();
    }

    /**
     * Returns the Position of p's left child (or null if no child exists).
     *
     * @param p A valid Position within the tree
     * @return the Position of the left child (or null if no child exists)
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     */
    @Override
    public Position<E> left(Position<E> p) throws IllegalArgumentException {
        // validate position
        Node<E> temp = validate(p);

        // return its left Node
        return temp.getLeft();
    }

    /**
     * Returns the Position of p's right child (or null if no child exists).
     *
     * @param p A valid Position within the tree
     * @return the Position of the right child (or null if no child exists)
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     */
    @Override
    public Position<E> right(Position<E> p) throws IllegalArgumentException {
        // validate position
        Node<E> temp = validate(p);

        // return its right Node
        return temp.getRight();
    }

    // update methods supported by this class

    /**
     * Places element e at the root of an empty tree and returns its new Position.
     *
     * @param e the new element
     * @return the Position of the new element
     * @throws IllegalStateException if the tree is not empty
     */
    public Position<E> addRoot(E e) throws IllegalStateException {
        // can only add root if tree is empty
        if (!isEmpty()) {
            throw new IllegalStateException("Tree is not empty");
        }

        // create node, set root to new node, set size 1, return value
        root = createNode(e, null, null, null);
        size = 1;
        return root;
    }

    public void insert(E e) {
        //recursively add from root
        root = addRecursive(root, e);
        ++size;
    }

    //recursively add Nodes to binary tree in proper position
    private Node<E> addRecursive(Node<E> p, E e) {
        //TODO
        return null;
    }


    /**
     * Creates a new left child of Position p storing element e and returns its Position.
     *
     * @param p the Position to the left of which the new element is inserted
     * @param e the new element
     * @return the Position of the new element
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     * @throws IllegalArgumentException if p already has a left child
     */
    public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException {
        // make new valid node
        Node<E> par = validate(p);

        // if has left child, throw exception
        if (par.getLeft() != null) {
            throw new IllegalArgumentException("p has left child, can't add");
        }

        // make child node
        Node<E> child = createNode(e, par, null, null);

        // set left child
        par.setLeft(child);

        // increase size
        size = size + 1;

        // return child
        return child;
    }

    /**
     * Creates a new right child of Position p storing element e and returns its Position.
     *
     * @param p the Position to the right of which the new element is inserted
     * @param e the new element
     * @return the Position of the new element
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     * @throws IllegalArgumentException if p already has a right child
     */
    public Position<E> addRight(Position<E> p, E e) throws IllegalArgumentException {
        // make new valid node
        Node<E> par = validate(p);

        // if has right child, throw exception
        if (par.getRight() != null) {
            throw new IllegalArgumentException("par has right child, can't add");
        }

        // make child node
        Node<E> child = createNode(e, par, null, null);

        // set right child
        par.setRight(child);

        // increase size
        size = size + 1;

        // return child
        return child;
    }

    /**
     * Replaces the element at Position p with element e and returns the replaced element.
     *
     * @param p the relevant Position
     * @param e the new element
     * @return the replaced element
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     */
    public E set(Position<E> p, E e) throws IllegalArgumentException {
        // make new valid node
        Node<E> x = validate(p);

        // get element of node
        E temp = x.getElement();

        // set element to new element
        x.setElement(e);

        // return replaced data
        return temp;
    }

    /**
     * Attaches trees t1 and t2, respectively, as the left and right subtree of the
     * leaf Position p. As a side effect, t1 and t2 are set to empty trees.
     *
     * @param p  a leaf of the tree
     * @param t1 an independent tree whose structure becomes the left child of p
     * @param t2 an independent tree whose structure becomes the right child of p
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     * @throws IllegalArgumentException if p is not a leaf
     */
    public void attach(Position<E> p, LinkedBinaryTree<E> t1, LinkedBinaryTree<E> t2) throws IllegalArgumentException {
        // make new valid node
        Node<E> x = validate(p);

        // if p isn't a leaf, throw exception
        if (isInternal(p)) {
            throw new IllegalArgumentException("p isn't a leaf");
        }

        // size incremenets by the size of the new trees
        size += t1.size() + t2.size();

        // if tree 1 isn't empty
        if (!t1.isEmpty()) {
            // set t1's root's parent to new node
            t1.root.setParent(x);

            // set x root left to t1
            x.setLeft(t1.root);

            // t1.root is null
            t1.root = null;

            // size of t1 is 0
            t1.size = 0;
        }
        if (!t2.isEmpty()) {
            // set t2 new parent
            t2.root.setParent(x);

            // set x root right to t2 root
            x.setRight(t2.root);

            // t2 root is null
            t2.root = null;

            // t2 size is 0
            t2.size = 0;
        }
    }

    /**
     * Removes the node at Position p and replaces it with its child, if any.
     *
     * @param p the relevant Position
     * @return element that was removed
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     * @throws IllegalArgumentException if p has two children.
     */
    public E remove(Position<E> p) throws IllegalArgumentException {
        // make new node
        Node<E> node = validate(p);

        // if p has 2 children, can't remove
        if (numChildren(p) == 2) {
            throw new IllegalArgumentException("p has 2 children");
        }

        // make temp child node
        Node child = null;

        // assign node child
        if (node.getLeft() == null) {
            node.getRight();
        } else {
            node.getLeft();
        }

        if (child != null) {
            // if not null, set parent
            child.setParent(node.getParent());
        }

        if (node == root) {
            // if node is root, new root is child
            root = child;
        } else {
            // else make parent node
            Node<E> parent = node.getParent();

            // set parent left and right
            if (node == parent.getLeft()) {
                parent.setLeft(child);
            } else {
                parent.setRight(child);
            }
        }

        // decrement size
        size--;

        // save data
        E temp = node.getElement();

        // set all to null
        node.setLeft(null);
        node.setRight(null);
        node.setParent(node);

        // return deleted data
        return temp;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Position<E> p : positions()) {
            sb.append(p.getElement());
            sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<Integer>();

        int[] arr = {12, 25, 31, 58, 36, 42, 90, 62, 75};
        for (int i : arr) {
            bt.insert(i);
        }
        System.out.println("bt: " + bt.size() + " " + bt);

    }
}

