package projectCode20280;

import java.util.Comparator;

public class SplayTreeMap<K, V> extends TreeMap<K, V> {

    protected BalanceableBinaryTree<K, V> tree = new BalanceableBinaryTree<>();


    /**
     * Constructs an empty map using the natural ordering of keys.
     */
    public SplayTreeMap() {
        super();
    }

    /**
     * Constructs an empty map using the given comparator to order keys.
     *
     * @param comp comparator defining the order of keys in the map
     */
    public SplayTreeMap(Comparator<K> comp) {
        super(comp);
    }

    /**
     * Utility used to rebalance after a map operation.
     */
    private void splay(Position<Entry<K, V>> p) {
        while (!isRoot(p)) {
            // parent node
            Position<Entry<K, V>> par = parent(p);
            // parent of parent node
            Position<Entry<K, V>> grandPar = parent(par);

            // rotate if grandparent node doesnt exist
            if (grandPar == null) {
                tree.rotate(p);
            } else if ((par == left(grandPar)) == (p == left(par))) {
                // else if zig zig
                tree.rotate(par);
                tree.rotate(p);
            } else {
                // else
                tree.rotate(p);
                tree.rotate(p);
            }
        }
    }

    /**
     * Overrides the TreeMap rebalancing hook that is called after a node access.
     */
    protected void rebalanceAccess(Position<Entry<K, V>> p) {
        if (isExternal(p)) {
            p = parent(p);
        }

        if (p != null) {
            splay(p);
        }
    }

    /**
     * Overrides the TreeMap rebalancing hook that is called after an insertion.
     */
    protected void rebalanceInsert(Position<Entry<K, V>> p) {
        splay(p);
    }

    /**
     * Overrides the TreeMap rebalancing hook that is called after a deletion.
     */
    protected void rebalanceDelete(Position<Entry<K, V>> p) {
        if (!isRoot(p)) {
            splay(parent(p));
        }
    }
}
