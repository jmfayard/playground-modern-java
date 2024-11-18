package tree;

import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Consumer;

public record BinaryTree(int val, @Nullable BinaryTree left, @Nullable BinaryTree right) {

    /**
     * Trees are represented as string of ints separated with spaces in preOrderTraversal, null nodes are represented as x
     * BinaryTree.fromString("5 4 3 x x 8 x x 6 x x")
     * is equivalent to
     * new BinaryTree(5, new BinaryTree(4, leaf(3), leaf(8), leaf(6))
     */
    public static BinaryTree fromString(String input) {
        var ints = input.split("\s+");
        return fromPrefixEncoding(ints, 0, ints.length);
    }

    private static @Nullable BinaryTree fromPrefixEncoding(String[] ints, int start, int end) {
        var val = ints[start];
        if (val.equals("x")) return null;
        var left = fromPrefixEncoding(ints, start + 1, end);
        var rightStart = start + (((left == null) ? 2 : (int) Math.pow(2, left.height() + 1)));
        var rightNode = ints[rightStart];
        var right = fromPrefixEncoding(ints, rightStart, end);
        return new BinaryTree(Integer.parseInt(val), left, right);
    }

    /*
    Pre-order visit. Consume the value of the node, or null if we are on a null tree.
     */
    public void preOrderTraversal(Consumer<Integer> consumer) {
        consumer.accept(val);
        if (left == null) {
            consumer.accept(null);
        } else {
            left.preOrderTraversal(consumer);
        }
        if (right == null) {
            consumer.accept(null);
        } else {
            right.preOrderTraversal(consumer);
        }
    }

    /*
Inn-order visit. Consume the value of the node, or null if we are on a null tree.
 */
    public void inOrderTraversal(Consumer<Integer> consumer) {
        if (left == null) {
            consumer.accept(null);
        } else {
            left.inOrderTraversal(consumer);
        }
        consumer.accept(val);
        if (right == null) {
            consumer.accept(null);
        } else {
            right.inOrderTraversal(consumer);
        }
    }

    /*
Post-order visit. Consume the value of the node, or null if we are on a null tree.
 */
    public void postOrderTraversal(Consumer<Integer> consumer) {
        if (left == null) {
            consumer.accept(null);
        } else {
            left.postOrderTraversal(consumer);
        }
        if (right == null) {
            consumer.accept(null);
        } else {
            right.postOrderTraversal(consumer);
        }
        consumer.accept(val);
    }

    /** Consume the nodes level by level **/
    public void breadthFirstSearch(Consumer<Integer> consumer) {
        Queue<BinaryTree> queue = new LinkedList<>();
        queue.add(this);
        while(!queue.isEmpty()) {
            var node = queue.poll();
            consumer.accept(node.val);
            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }
    }


    public String encodeToString() {
        var b = new StringBuilder();
        preOrderTraversal((@Nullable Integer nb) -> {
            if (nb == null) {
                b.append("x ");
            } else {
                b.append((int) nb);
                b.append(" ");
            }
        });
        return b.toString().trim();
    }

    @Override
    public String toString() {
        return encodeToString();
    }

    public boolean isFull() {
        if (isLeaf()) {
            return true;
        } else {
            return left != null && right != null;
        }
    }

    public int nodesCount() {
        var rCount = (right == null) ? 0 : right.nodesCount();
        var lCount = (left == null) ? 0 : left.nodesCount();
        return 1 + rCount + lCount;
    }

    public int height() {
        var rHeight = (right == null) ? 0 : right.height();
        var rLeft = (left == null) ? 0 : left.height();
        return 1 + Math.max(rHeight, rLeft);
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }

    public boolean isPerfect() {
        return isPerfect(height());
    }

    private boolean isPerfect(int level) {
        if (level == 1) return isLeaf();
        else if (right == null || left == null) return false;
        else return (right.isPerfect(level - 1) && left.isPerfect(level - 1));
    }

    public boolean isBinarySearchTree() {
        return isBinarySearchTreeRecursive(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isBinarySearchTreeRecursive(int min, int max) {
        boolean valueInRange = val >= min && val <= max;
        boolean leftIsBST = left == null || left.isBinarySearchTreeRecursive(min, val);
        boolean rightIsBST = right == null || right.isBinarySearchTreeRecursive(val, max);
        return leftIsBST && rightIsBST && valueInRange;
    }

    public boolean isBalanced() {
        int leftHeight = (left == null) ? 0 : left.height();
        int rightHeight = (right == null) ? 0 : right.height();
        return Math.abs(leftHeight - rightHeight) <= 1;
    }

    public boolean isComplete() {
        return isCompleteRecursive(height());
    }

    private boolean isCompleteRecursive(int level) {
        if (level == 1) return isLeaf();
        else if (level == 2) {
            return !(left == null && right != null);
        } else if (right == null || left == null) return false;
        else return (right.isCompleteRecursive(level - 1) && left.isCompleteRecursive(level - 1));
    }

    static BinaryTree leaf(int val) {
        return new BinaryTree(val, null, null);
    }


    static BinaryTree full() {
        return new BinaryTree(10, new BinaryTree(20, full30(), leaf(31)), leaf(21));
    }

    static BinaryTree full30() {
        return new BinaryTree(30, leaf(40), leaf(41));
    }

    static BinaryTree perfect(int level) {
        if (level == 0) return leaf(random.nextInt());
        else return new BinaryTree(random.nextInt(), perfect(level - 1), perfect(level - 1));
    }

    static BinaryTree complete() {
        return new BinaryTree(
                10,
                new BinaryTree(20, leaf(30), leaf(31)),
                leaf(21));
    }

    static BinaryTree onlyOneChild() {
        return new BinaryTree(10, null,
                new BinaryTree(20, null, new BinaryTree(
                        30, null, new BinaryTree(
                        40, null, leaf(50)
                )
                )));
    }

    static BinaryTree binarySearchTree() {
        return new BinaryTree(10,
                new BinaryTree(5, leaf(1), leaf(7)),
                new BinaryTree(15, leaf(12), leaf(17)));
    }

    static BinaryTree balancedBinaryTree() {
        return new BinaryTree(
                1,
                new BinaryTree(2, leaf(4), null),
                new BinaryTree(3,
                        new BinaryTree(5, null, leaf(7)),
                        leaf(6)
                )
        );
    }

    static Random random = new Random();

}
