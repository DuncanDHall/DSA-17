public class AVLTree<T extends Comparable<T>> extends BinarySearchTree<T> {

    /**
     * Delete a key from the tree rooted at the given node.
     */
    @Override
    TreeNode<T> delete(TreeNode<T> n, T key) {
        n = super.delete(n,key);
        if(n != null) {
            n.height = height(n);
            n = balance(n);
            // TODO: Update height and balance tree
        }
        return n;
    }
    /**
     * Insert a key into the tree rooted at the given node.
     */
    @Override
    TreeNode<T> insert(TreeNode<T>  n, T key) {
        n = super.insert(n,key);

        n.height = height(n);
        n = balance(n);
        // TODO: update height and balance tree

        return n;
    }

    /**
     * Delete the minimum descendant of the given node.
     */
    @Override
    TreeNode<T> deleteMin(TreeNode<T> n){
        n = super.deleteMin(n);
        if(n != null) {
            n.height = 1 + Math.max(height(n.leftChild), height(n.rightChild));
            return balance(n);
        }
        return null;
    }

    // Return the height of the given node. Return -1 if null.
    private int height(TreeNode<T> n) {
        if (n == null) return -1;
        return Math.max(height(n.leftChild), height(n.rightChild)) + 1;
    }

    public int height() {
        return Math.max(height(root),0);
    }

    // Restores the AVL tree property of the subtree.
    TreeNode<T> balance(TreeNode<T> n) {
        if (balanceFactor(n) < -1) {
            if (balanceFactor(n.leftChild) > 0) n.leftChild = rotateLeft(n.leftChild);
            n = rotateRight(n);
        }
        else if (balanceFactor(n) > 1) {
            if (balanceFactor(n.rightChild) < 0) n.rightChild = rotateRight(n.rightChild);
            n = rotateLeft(n);
        }
        // TODO
        return n;
    }

    /**
     * Returns the balance factor of the subtree. The balance factor is defined
     * as the difference in height of the left subtree and right subtree, in
     * this order. Therefore, a subtree with a balance factor of -1, 0 or 1 has
     * the AVL property since the heights of the two child subtrees differ by at
     * most one.
     */
    private int balanceFactor(TreeNode<T> n) {
        int a = n.leftChild == null? -1 : n.leftChild.height;
        int b = n.rightChild == null? -1 : n.rightChild.height;
        return b - a;
    }

    /**
     * Perform a right rotation on node `n`. Return the head of the rotated tree.
     */
    private TreeNode<T> rotateRight(TreeNode<T> n) {
        TreeNode<T> newN = n.leftChild;
        n.leftChild = newN.rightChild;
        newN.rightChild = n;
        n.height = height(n);
        newN.height = height(newN);
        return newN;
    }

    /**
     * Perform a left rotation on node `n`. Return the head of the rotated tree.
     */
    private TreeNode<T> rotateLeft(TreeNode<T> n) {
        TreeNode<T> newN = n.rightChild;
        n.rightChild = newN.leftChild;
        newN.leftChild = n;
        n.height = height(n);
        newN.height = height(newN);
        return newN;
    }

    public static void main(String[] args) {
        AVLTree<Integer> tree = new AVLTree<>();

        tree.add(1);
        tree.add(2);
        tree.add(3);
        tree.add(4);
        tree.add(5);
        tree.add(6);

        System.out.println(tree);

    }
}
