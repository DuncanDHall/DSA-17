import java.util.Collections;
import java.util.List;

public class Problems {

    public static BinarySearchTree<Integer> minimalHeight(List<Integer> values) {
        List<Integer> sorted_values = values;
        Collections.sort(sorted_values);

        BinarySearchTree<Integer> res = new BinarySearchTree<>();
        addMedianRecurse(sorted_values, 0, sorted_values.size()-1, res);

        return res;
    }

    private static void addMedianRecurse(List<Integer> sorted_values, int first, int last, BinarySearchTree<Integer> tree) {
        if (first >= last) {
            tree.add(sorted_values.get(first));
        }
        else {
            int mid = (first + last) / 2;
            tree.add(sorted_values.get(mid));
            addMedianRecurse(sorted_values, first, mid-1, tree);
            addMedianRecurse(sorted_values, mid+1, last, tree);
        }
    }

    public static boolean isIsomorphic(TreeNode n1, TreeNode n2) {
        if (n1 == null && n2 == null) return true;
        if (n1.key !=  n2.key) return false;
        return (
                isIsomorphic(n1.leftChild, n2.leftChild) && isIsomorphic(n1.rightChild, n2.rightChild)
                || isIsomorphic(n1.leftChild, n2.rightChild) && isIsomorphic(n1.rightChild, n2.leftChild)
        );
    }
}
