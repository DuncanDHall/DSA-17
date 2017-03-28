import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrderTraversal {

    public static List<Integer> levelOrderTraversal(TreeNode<Integer> n) {
        // TODO: Your code here

        Queue<TreeNode> bfsQueue = new LinkedList<>();
        bfsQueue.offer(n);

        List<Integer> res = new LinkedList<>();

        while (!bfsQueue.isEmpty()) {
            n = bfsQueue.poll();
            res.add(n.key);
            if (n.hasLeftChild()) bfsQueue.offer(n.leftChild);
            if (n.hasRightChild()) bfsQueue.offer(n.rightChild);
        }

        return res;
    }
}
