package projectCode20280;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * An implementation of a sorted map using a binary search tree.
 */

public class TreeMap<K, V> extends AbstractSortedMap<K, V> {

    // We reuse the LinkedBinaryTree class. A limitation here is that we only use the key.
    protected LinkedBinaryTree<Entry<K, V>> tree = new LinkedBinaryTree<Entry<K, V>>();

    /**
     * Constructs an empty map using the natural ordering of keys.
     */
    public TreeMap() {
        super(); // the AbstractSortedMap constructor
        tree.addRoot(null); // create a sentinel leaf as root
    }

    public TreeMap(Comparator<K> comp) {
        super(comp);              // the AbstractSortedMap constructor
        tree.addRoot(null);       // create a sentinel leaf as root
    }

    /**
     * Returns the number of entries in the map.
     *
     * @return number of entries in the map
     */
    @Override
    public int size() {
        return (tree.size() - 1) / 2; // only internal nodes have entries
    }

    /**
     * Utility used when inserting a new entry at a leaf of the tree
     */
    private void expandExternal(Position<Entry<K, V>> p, Entry<K, V> entry) {
        // set new position, children nodes are null
        tree.set(p, entry);
        tree.addLeft(p, null);
        tree.addRight(p, null);
    }

    // Some notational shorthands for brevity (yet not efficiency)
    protected Position<Entry<K, V>> root() {
        return tree.root();
    }

    protected Position<Entry<K, V>> parent(Position<Entry<K, V>> p) {
        return tree.parent(p);
    }

    protected Position<Entry<K, V>> left(Position<Entry<K, V>> p) {
        return tree.left(p);
    }

    protected Position<Entry<K, V>> right(Position<Entry<K, V>> p) {
        return tree.right(p);
    }

    protected Position<Entry<K, V>> sibling(Position<Entry<K, V>> p) {
        return tree.sibling(p);
    }

    protected boolean isRoot(Position<Entry<K, V>> p) {
        return tree.isRoot(p);
    }

    protected boolean isExternal(Position<Entry<K, V>> p) {
        return tree.isExternal(p);
    }

    protected boolean isInternal(Position<Entry<K, V>> p) {
        return tree.isInternal(p);
    }

    protected void set(Position<Entry<K, V>> p, Entry<K, V> e) {
        tree.set(p, e);
    }

    protected Entry<K, V> remove(Position<Entry<K, V>> p) {
        return tree.remove(p);
    }

    /**
     * Returns the position in p's subtree having the given key (or else the
     * terminal leaf).
     *
     * @param key a target key
     * @param p   a position of the tree serving as root of a subtree
     * @return Position holding key, or last node reached during search
     */
    private Position<Entry<K, V>> treeSearch(Position<Entry<K, V>> p, K key) {
        // if external, return p
        if (isExternal(p)) {
            return p;
        }

        // compare
        int c = compare(key, p.getElement());

        // cases for the compare
        if (c == 0) {
            return p;
        } else if (c < 0) {
            return treeSearch(left(p), key);
        } else {
            return treeSearch(right(p), key);
        }
    }

    /**
     * Returns position with the minimal key in the subtree rooted at Position p.
     *
     * @param p a Position of the tree serving as root of a subtree
     * @return Position with minimal key in subtree
     */
    protected Position<Entry<K, V>> treeMin(Position<Entry<K, V>> p) {
        Position<Entry<K, V>> step = p;

        while (isInternal(step)) {
            step = left(step);
        }

        return parent(step);
    }

    /**
     * Returns the position with the maximum key in the subtree rooted at p.
     *
     * @param p a Position of the tree serving as root of a subtree
     * @return Position with maximum key in subtree
     */
    protected Position<Entry<K, V>> treeMax(Position<Entry<K, V>> p) {
        Position<Entry<K, V>> step = p;

        while (isInternal(step)) {
            step = right(step);
        }

        return parent(step);
    }

    /**
     * Returns the value associated with the specified key, or null if no such entry
     * exists.
     *
     * @param key the key whose associated value is to be returned
     * @return the associated value, or null if no such entry exists
     */
    @Override
    public V get(K key) throws IllegalArgumentException {
        // check key
        checkKey(key);

        // make new p
        Position<Entry<K, V>> p = treeSearch(root(), key);

        // check for external
        if (isExternal(p)) {
            return null;
        }

        // return
        return p.getElement().getValue();
    }

    /**
     * Associates the given value with the given key. If an entry with the key was
     * already in the map, this replaced the previous value with the new one and
     * returns the old value. Otherwise, a new entry is added and null is returned.
     *
     * @param key   key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with the key (or null, if no such
     * entry)
     */
    @Override
    public V put(K key, V value) throws IllegalArgumentException {
        // check key
        checkKey(key);

        // make new entry
        Entry<K, V> x = new MapEntry<>(key, value);

        // new position p
        Position<Entry<K, V>> p = treeSearch(root(), key);

        // if new key
        if (isExternal(p)) {
            expandExternal(p, x);
            return null;
        } else {
            // if exisiting key
            V old = p.getElement().getValue();
            set(p, x);
            return old;
        }
    }

    /**
     * Removes the entry with the specified key, if present, and returns its
     * associated value. Otherwise does nothing and returns null.
     *
     * @param key the key whose entry is to be removed from the map
     * @return the previous value associated with the removed key, or null if no
     * such entry exists
     */
    @Override
    public V remove(K key) throws IllegalArgumentException {
        // check key
        checkKey(key);

        //make new position
        Position<Entry<K, V>> p = treeSearch(root(), key);

        // if external, ret null
        if (isExternal(p)) {
            return null;
        } else {
            // else
            V prev = p.getElement().getValue();
            // if both children are internal nodes
            if (isInternal(left(p)) && isInternal(right(p))) {
                Position<Entry<K, V>> repl = treeMax(left(p));
                set(p, repl.getElement());
                p = repl;
            }

            // p has max 1 internal node now
            Position<Entry<K, V>> leaf;

            if (isExternal(left(p))) {
                leaf = left(p);
            } else {
                leaf = right(p);
            }

            remove(leaf);
            remove(p);

            return prev;
        }
    }

    // additional behaviors of the SortedMap interface

    /**
     * Returns the entry having the least key (or null if map is empty).
     *
     * @return entry with least key (or null if map is empty)
     */
    @Override
    public Entry<K, V> firstEntry() {
        if (isEmpty()) {
            return null;
        }
        return treeMin(root()).getElement();
    }

    /**
     * Returns the entry having the greatest key (or null if map is empty).
     *
     * @return entry with greatest key (or null if map is empty)
     */
    @Override
    public Entry<K, V> lastEntry() {
        // if tree is empty, return null
        if (isEmpty()) {
            return null;
        }
        return treeMax(root()).getElement();
    }

    /**
     * Returns the entry with least key greater than or equal to given key (or null
     * if no such key exists).
     *
     * @return entry with least key greater than or equal to given (or null if no
     * such entry)
     * @throws IllegalArgumentException if the key is not compatible with the map
     */
    @Override
    public Entry<K, V> ceilingEntry(K key) throws IllegalArgumentException {
        // check key
        checkKey(key);

        // make new position
        Position<Entry<K, V>> p = treeSearch(root(), key);

        // if internal, return element of p
        if (isInternal(p)) {
            return p.getElement();
        }

        // else
        while (!isRoot(p)) {
            // while p is not root
            if (p == left(parent(p))) {
                // if p is left of parent p
                return parent(p).getElement();
            } else {
                // else reset p to parent
                p = parent(p);
            }
        }

        // null if none found
        return null;
    }

    /**
     * Returns the entry with greatest key less than or equal to given key (or null
     * if no such key exists).
     *
     * @return entry with greatest key less than or equal to given (or null if no
     * such entry)
     * @throws IllegalArgumentException if the key is not compatible with the map
     */
    @Override
    public Entry<K, V> floorEntry(K key) throws IllegalArgumentException {
        // check key
        checkKey(key);

        // make new position
        Position<Entry<K, V>> p = treeSearch(root(), key);

        // if internal return element of p
        if (isInternal(p)) {
            return p.getElement();
        }

        // while not root
        while (!isRoot(p)) {
            if (p == right(parent(p))) {
                return parent(p).getElement();
            } else {
                p = parent(p);
            }
        }

        // null if none found
        return null;
    }

    /**
     * Returns the entry with greatest key strictly less than given key (or null if
     * no such key exists).
     *
     * @return entry with greatest key strictly less than given (or null if no such
     * entry)
     * @throws IllegalArgumentException if the key is not compatible with the map
     */
    @Override
    public Entry<K, V> lowerEntry(K key) throws IllegalArgumentException {
        // check key
        checkKey(key);

        // make new position
        Position<Entry<K, V>> p = treeSearch(root(), key);

        // if internal p and left p
        if (isInternal(p) && isInternal(left(p))) {
            // return
            return treeMax(left(p)).getElement();
        }

        // while p is not root
        while (!isRoot(p)) {
            if (p == right(parent(p))) {
                return parent(p).getElement();
            } else {
                // reset p
                p = parent(p);
            }
        }

        // return null if none found
        return null;
    }

    /**
     * Returns the entry with least key strictly greater than given key (or null if
     * no such key exists).
     *
     * @return entry with least key strictly greater than given (or null if no such
     * entry)
     * @throws IllegalArgumentException if the key is not compatible with the map
     */
    @Override
    public Entry<K, V> higherEntry(K key) throws IllegalArgumentException {
        // check key
        checkKey(key);

        // make new position p
        Position<Entry<K, V>> p = treeSearch(root(), key);

        // if internal p and right p
        if (isInternal(p) && isInternal(right(p))) {
            // return
            return treeMin(right(p)).getElement();
        }

        // while p is not root
        while (!isRoot(p)) {
            if (p == left(parent(p))) {
                return parent(p).getElement();
            } else {
                // reset p
                p = parent(p);
            }
        }

        // null if none found
        return null;
    }

    // Support for iteration

    /**
     * Returns an iterable collection of all key-value entries of the map.
     *
     * @return iterable collection of the map's entries
     */
    @Override
    public Iterable<Entry<K, V>> entrySet() {
        // new arraylist of size size()
        ArrayList<Entry<K, V>> ret = new ArrayList<>(size());

        // foreach in tree
        for (Position<Entry<K, V>> p : tree.inorder()) {
            // if internal node, add element to list
            if (isInternal(p)) {
                ret.add(p.getElement());
            }
        }

        // return
        return ret;
    }

    public String toString() {
        // new arraylist of size size()
        String ret = "[";

        // foreach in tree
        for (Position<Entry<K, V>> p : tree.inorder()) {
            // if internal node, add element to list
            if (isInternal(p)) {
                ret += (p.getElement().getValue()) + ", ";
            }
        }

        ret = ret.substring(0, ret.length() - 2);
        ret += "]";
        // return
        return ret;
    }

    /**
     * Returns an iterable containing all entries with keys in the range from
     * <code>fromKey</code> inclusive to <code>toKey</code> exclusive.
     *
     * @return iterable with keys in desired range
     * @throws IllegalArgumentException if <code>fromKey</code> or
     *                                  <code>toKey</code> is not compatible with
     *                                  the map
     */
    @Override
    public Iterable<Entry<K, V>> subMap(K fromKey, K toKey) throws IllegalArgumentException {
        // check both keys
        checkKey(fromKey);
        checkKey(toKey);

        // arraylist to return
        ArrayList<Entry<K, V>> ret = new ArrayList<>(size());

        // if key comparison < 0, enter recursive routine
        if (compare(fromKey, toKey) < 0)
        {
            // enter recursive
            subMapHelper(fromKey, toKey, root(), ret);
        }

        // return
        return ret;
    }

    // helper to fill submap recursively
    private void subMapHelper(K fromKey, K toKey, Position<Entry<K, V>> p, ArrayList<Entry<K, V>> toRet) {
        // only continue if internal node
        if (isInternal(p)) {
            // compare to fromKey (left)
            if (compare(p.getElement(), fromKey) < 0) {
                // recurse
                subMapHelper(fromKey, toKey, right(p), toRet);
            } else {
                // else do left subtree first
                subMapHelper(fromKey, toKey, left(p), toRet);

                // if p is in the selection
                if (compare(p.getElement(), toKey) < 0) {
                    // add it to arraylist
                    toRet.add(p.getElement());

                    // enter right subtree next recursively
                    subMapHelper(fromKey, toKey, right(p), toRet); // right subtree as well
                }
            }
        }
    }

    // remainder of class is for debug purposes only

    /**
     * Prints textual representation of tree structure (for debug purpose only).
     */
    protected void dump() {
        dumpRecurse(root(), 0);
    }

    /**
     * This exists for debugging only
     */
    private void dumpRecurse(Position<Entry<K, V>> p, int depth) {
        String indent = (depth == 0 ? "" : String.format("%" + (2 * depth) + "s", ""));
        if (isExternal(p))
            System.out.println(indent + "leaf");
        else {
            System.out.println(indent + p.getElement());
            dumpRecurse(left(p), depth + 1);
            dumpRecurse(right(p), depth + 1);
        }
    }

    public static void main(String[] args) {
        TreeMap<Integer, Integer> treeMap = new TreeMap<Integer, Integer>();

        Random rnd = new Random();
        int n = 16;
        java.util.List<Integer> rands = rnd.ints(1, 1000).limit(n).distinct().boxed().collect(Collectors.toList());

        for (Integer i : rands) {
            treeMap.put(i, i);
        }

        System.out.println("tree entries: " + treeMap.entrySet());

        treeMap.remove(rands.get(1));

        System.out.println("tree entries after removal: " + treeMap.entrySet());
    }

}