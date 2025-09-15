import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tree {
    private Integer root;
    private List<Tree> subtrees;
    private static Random random = new Random();

    public Tree() {
        this.root = null;
        this.subtrees = new ArrayList<>();
    }

    public Tree(int root) {
        this.root = root;
        this.subtrees = new ArrayList<>();
    }

    public Tree(int root, List<Tree> subtrees) {
        this.root = root;
        this.subtrees = new ArrayList<>(subtrees);
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int getSize() {
        if (isEmpty()) {
            return 0;
        } else {
            int size = 1;
            for (Tree subtree : subtrees) {
                size += subtree.getSize();
            }
            return size;
        }
    }

    public int count(int item) {
        if (isEmpty()) {
            return 0;
        } else {
            int num = 0;
            if (root == item) {
                num += 1;
            }
            for (Tree subtree : subtrees) {
                num += subtree.count(item);
            }
            return num;
        }
    }

    public boolean contains(int item) {
        if (isEmpty()) {
            return false;
        }
        if (root == item) {
            return true;
        } else {
            for (Tree subtree : subtrees) {
                if (subtree.contains(item)) {
                    return true;
                }
            }
            return false;
        }
    }

    public void insert(int item) {
        if (isEmpty()) {
            this.root = item;
        } else if (subtrees.isEmpty()) {
            subtrees.add(new Tree(item));
        } else {
            if (random.nextInt(3) + 1 == 3) {
                subtrees.add(new Tree(item));
            } else {
                int subtreeIndex = random.nextInt(subtrees.size());
                subtrees.get(subtreeIndex).insert(item);
            }
        }
    }

    public boolean deleteItem(int item) {
        if (isEmpty()) {
            return false;
        } else if (root == item) {
            deleteRoot();
            return true;
        } else {
            for (int i = 0; i < subtrees.size(); i++) {
                Tree subtree = subtrees.get(i);
                boolean deleted = subtree.deleteItem(item);
                if (deleted && subtree.isEmpty()) {
                    subtrees.remove(i);
                    return true;
                } else if (deleted) {
                    return true;
                }
            }
            return false;
        }
    }

    private void deleteRoot() {
        if (subtrees.isEmpty()) {
            root = null;
        } else {
            Tree chosenSubtree = subtrees.remove(subtrees.size() - 1);
            root = chosenSubtree.root;
            subtrees.addAll(chosenSubtree.subtrees);
        }
    }
}
