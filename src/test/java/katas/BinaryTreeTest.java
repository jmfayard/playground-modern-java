package katas;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static katas.BinaryTree.leaf;

class BinaryTreeTest {

    @Test
    void fullBinaryTree() {
        var input = List.of(leaf(10), BinaryTree.full(), BinaryTree.perfect(3), BinaryTree.onlyOneChild());
        var expected = List.of(true, true, true, false);
        var actual = input.stream().map(BinaryTree::isFull).toList();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void heights() {
        var input = List.of(leaf(10), BinaryTree.full(), BinaryTree.perfect(3), BinaryTree.onlyOneChild());
        var expected = List.of(1, 4, 4, 5);

        var actual = input.stream().map(BinaryTree::height).toList();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void perfect() {
        var input = List.of(leaf(10), BinaryTree.full(), BinaryTree.perfect(3), BinaryTree.onlyOneChild(), BinaryTree.complete());
        var expected = List.of(true, false, true, false, false);

        var actual = input.stream().map(BinaryTree::isPerfect).toList();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void complete() {
        var input = List.of(leaf(10), BinaryTree.full(), BinaryTree.perfect(3), BinaryTree.onlyOneChild(), BinaryTree.complete());
        var expected = List.of(true, false, true, false, true);

        var actual = input.stream().map(BinaryTree::isComplete).toList();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void isBinarySearchTree() {
        var input = List.of(leaf(10), BinaryTree.full(), BinaryTree.binarySearchTree());
        var expected = List.of(true, false, true);

        var actual = input.stream().map(BinaryTree::isBinarySearchTree).toList();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void isBalanced() {
        var input = List.of(BinaryTree.balancedBinaryTree(), leaf(10), BinaryTree.full(), BinaryTree.onlyOneChild(), BinaryTree.complete());
        var expected = List.of(true, true, false, false, true);

        var actual = input.stream().map(BinaryTree::isBalanced).toList();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void nodesCount() {
        var input = List.of(leaf(10), BinaryTree.full(), BinaryTree.onlyOneChild());
        var expected = List.of(1, 7, 5);

        var actual = input.stream().map(BinaryTree::nodesCount).toList();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void createTreeFromStringEncoding() {
        var actual = BinaryTree.fromString("5 4 3 x x 8 x x 6 x x");
        var expected = new BinaryTree(5, new BinaryTree(4, leaf(3), leaf(8)), leaf(6));
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void encodeTreeToString() {
        var input = new BinaryTree(5, new BinaryTree(4, leaf(3), leaf(8)), leaf(6));
        var expected = "5 4 3 x x 8 x x 6 x x";
        assertThat(input.encodeToString()).isEqualTo(expected);
    }

    @Test
    void inOrderTraversal() {
        var expected = "34856";
        AtomicReference<String> actual = new AtomicReference<>("");
        var tree = BinaryTree.fromString("5 4 3 x x 8 x x 6 x x");
        tree.inOrderTraversal(integer -> {
            if (integer != null) actual.set(actual.get()+integer);
        });
        assertThat(actual.get()).isEqualTo(expected);

    }

    @Test
    void postOrderTraversal() {
        var expected = "38465";
        var tree = BinaryTree.fromString("5 4 3 x x 8 x x 6 x x");

        AtomicReference<String> actual = new AtomicReference<>("");
        tree.postOrderTraversal(integer -> {
            if (integer != null) actual.set(actual.get()+integer);
        });
        assertThat(actual.get()).isEqualTo(expected);

    }

    @Test
    void breadthFirstSearch() {
        var expected = "54638";
        var tree = BinaryTree.fromString("5 4 3 x x 8 x x 6 x x");
        AtomicReference<String> actual = new AtomicReference<>("");
        tree.breadthFirstSearch(integer -> {
            actual.set(actual.get()+integer);
        });
        assertThat(actual.get()).isEqualTo(expected);
    }
}