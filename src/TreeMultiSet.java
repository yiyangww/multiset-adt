class TreeMultiSet<T> implements MultiSet<T> {
    private Tree<T> tree;

    // Constructor
    public TreeMultiSet() {
        this.tree = new Tree<>(null);  // Create an empty tree
    }

    // Add an item to the multiset
    @Override
    public boolean add(T item) {
        tree.insert(item);  // Delegate to the Tree class
        return true;
    }

    // Remove one occurrence of the item from the multiset
    @Override
    public void remove(T item) {
        tree.deleteItem(item);  // Delegate to the Tree class
    }

    // Check if the item exists in the multiset
    @Override
    public boolean contains(T item) {
        return tree.contains(item);  // Delegate to the Tree class
    }

    // Check if the multiset is empty
    @Override
    public boolean isEmpty() {
        return tree.isEmpty();  // Delegate to the Tree class
    }

    // Count how many times the item appears in the multiset
    @Override
    public int count(T item) {
        return tree.count(item);  // Delegate to the Tree class
    }

    // Get the number of items in the multiset
    @Override
    public int size() {
        return tree.size();  // Delegate to the Tree class
    }
}

