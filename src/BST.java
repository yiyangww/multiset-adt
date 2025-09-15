/**
 * A minimal implementation of a binary search tree. See the python version for
 * additional documentation.
 * You can also see <a href=
 * "https://www.teach.cs.toronto.edu/~csc148h/notes/binary-search-trees/bst_implementation.html">
 * CSC148 Course Notes Section 8.5 BST Implementation and Search</a>
 * if you want a refresher on BSTs, but it is not required to complete this
 * assignment.
 */
public class BST {
    // we use Integer here so that we can set the root to null. This is the same
    // idea as
    // how the Python code uses None in the BST implementation.
    private Integer root;

    private BST left;
    private BST right;

    public BST(int root) {
        this.root = root;
        this.left = new BST();
        this.right = new BST();
    }

    /**
     * Alternate constructor, so we don't have to explicitly pass in null.
     */
    public BST() {
        root = null;
        // left and right default to being null
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean contains(int item) {
        // provided as an example
        if (this.isEmpty()) {
            return false;
        } else if (item == this.root) {
            return true;
        } else if (item < this.root) {
            return (this.left != null) ? this.left.contains(item) : false;
        }
        return (this.right != null) ? this.right.contains(item) : false;

    }

    public void insert(int item) {
        if (this.isEmpty()) {
            this.root = item;
            this.left = new BST();
            this.right = new BST();
        } else if (item <= this.root) {
            if (this.left != null) {
                this.left.insert(item);
            }
        } else {
            if (this.right != null) {
                this.right.insert(item);
            }
        }
    }

    public void delete(int item) {
        if (this.isEmpty()) {
            return;
        } else if (this.root == item) {
            this.deleteRoot();
        } else if (item < this.root) {
            if (this.left != null) {
                this.left.delete(item);
            }
        } else {
            if (this.right != null) {
                this.right.delete(item);
            }
        }
    }

    private void deleteRoot() {
        if ((this.left == null || this.left.isEmpty()) && (this.right == null || this.right.isEmpty())) {
            this.root = null;
            this.left = null;
            this.right = null;
        } else if (this.left == null || this.left.isEmpty()) {
            BST oldRight = this.right;
            this.root = oldRight.root;
            this.left = oldRight.left;
            this.right = oldRight.right;
        } else if (this.right == null || this.right.isEmpty()) {
            BST oldLeft = this.left;
            this.root = oldLeft.root;
            this.left = oldLeft.left;
            this.right = oldLeft.right;
        } else {
            this.root = this.left.extractMax();
        }
    }

    private int extractMax() {
        if (this.right == null || this.right.isEmpty()) {
            int maxItem = this.root;
            if (this.left == null || this.left.isEmpty()) {
                this.root = null;
                this.left = null;
                this.right = null;
            } else {
                BST oldLeft = this.left;
                this.root = oldLeft.root;
                this.left = oldLeft.left;
                this.right = oldLeft.right;
            }
            return maxItem;
        } else {
            return this.right.extractMax();
        }
    }

    public int height() {
        if (this.isEmpty()) {
            return 0;
        } else {
            int leftHeight = (this.left == null) ? 0 : this.left.height();
            int rightHeight = (this.right == null) ? 0 : this.right.height();
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    public int count(int item) {
        if (this.isEmpty()) {
            return 0;
        } else if (this.root > item) {
            return (this.left == null) ? 0 : this.left.count(item);
        } else if (this.root == item) {
            int leftCount = (this.left == null) ? 0 : this.left.count(item);
            int rightCount = (this.right == null) ? 0 : this.right.count(item);
            return 1 + leftCount + rightCount;
        } else {
            return (this.right == null) ? 0 : this.right.count(item);
        }
    }

    public int getSize() {
        if (this.isEmpty()) {
            return 0;
        }
        int leftSize = (this.left == null) ? 0 : this.left.getSize();
        int rightSize = (this.right == null) ? 0 : this.right.getSize();
        return 1 + leftSize + rightSize;
    }

    public static void main(String[] args) {
        // You can also add code here to do some basic testing if you want,
        // but make sure it doesn't contain syntax errors
        // or else we won't be able to run your code on MarkUs since the file won't
        // compile. Always make sure to run the self tests on MarkUs after you update
        // your code.
        BST bst = new BST();
        int a = 1;
        bst.insert(a);
        System.out.println(bst.contains(a));
    }

}
