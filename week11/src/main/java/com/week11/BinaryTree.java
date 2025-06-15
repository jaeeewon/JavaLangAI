package com.week11;

public class BinaryTree {
    class TreeNode {
        int data;
        TreeNode left, right;

        TreeNode(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    TreeNode root;

    BinaryTree() {
        // 1 -> 2, 3
        // 3 -> 4, 5
        // 하드코딩
        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
    }

    int sumTree(TreeNode currNode, int sum) {
        // 재귀적 함수 설계
        // (1) 기본 상태에서의 동작
        // (2) 재귀적 호출 수행
        // (3) 수행 결과 합산

        sum += currNode.data;
        if (currNode.left != null) {
            sum = sumTree(currNode.left, sum);
        }
        if (currNode.right != null) {
            sum = sumTree(currNode.right, sum);
        }

        return sum;
    }

    // contains 동작을 수행을 pre, in, post-order
    void preorder(TreeNode root, int key) {
        // root left right
        if (root.data == key) {
            System.out.println("The key is found!");
        }
        if (root.left != null) {
            preorder(root.left, key);
        }
        if (root.right != null) {
            preorder(root.right, key);
        }
    }

    void inorder(TreeNode root, int key) {
        // left, root, right
        if (root.left != null) {
            inorder(root.left, key);
        }
        if (root.data == key) {
            System.out.println("The key is found!");
        }
        if (root.right != null) {
            inorder(root.right, key);
        }
    }

    void postorder(TreeNode root, int key) {
        // left, right, root
        if (root.left != null) {
            postorder(root.left, key);
        }
        if (root.right != null) {
            postorder(root.right, key);
        }
        if (root.data == key) {
            System.out.println("The key is found!");
        }

    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree(); // 하드코딩했던 트리가 만들어지는
        tree.preorder(tree.root, 1);
    }
}
