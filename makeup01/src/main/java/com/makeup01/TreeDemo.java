package com.makeup01;

class Node<E> {
    E key;
    Node<E> left, right;
    int height = 1;

    Node(E key) {
        this.key = key;
    }
}

class BinaryTree<E> {
    public Node<E> root;

    // BinaryTree() {
    // this.root = new Node(1);
    // this.root.left = new Node(2);
    // this.root.left.left = new Node(4);
    // this.root.left.right = new Node(5);
    // this.root.right = new Node(3);
    // this.root.right.right = new Node(6);
    // }

    public void preorder(Node<E> root) {
        if (root == null)
            return;

        System.out.print(root.key);
        System.out.print(" ");
        preorder(root.left);
        preorder(root.right);
    }

    public void inorder(Node<E> root) {
        if (root == null)
            return;

        inorder(root.left);
        System.out.print(root.key);
        System.out.print(" ");
        inorder(root.right);
    }

    public void postorder(Node<E> root) {
        if (root == null)
            return;

        postorder(root.left);
        postorder(root.right);
        System.out.print(root.key);
        System.out.print(" ");
    }
}

class BinarySearchTree<E extends Comparable<E>> extends BinaryTree<E> {
    // 대소비교 가능한 제너릭 타입

    // 기본 상태: empty tree -> Binary Search Tree? 맞음 (BST가 요구하는 조건을 충족함)
    // Binary Search Tree가 주어졌을 때 contains(변화 x -> BST 망가지지 않음)
    // insert(구조 변화), remove(구조 변화) -> invariant 위배 가능성

    public Node<E> insert(Node<E> root, E key) {
        // 어디 넣어야 invariant 안 꺠질까

        // root가 null이면 아무렇게나
        if (root == null) {
            root = new Node<>(key);
            return root;
        }

        // root가 null아 아니면? left / right??
        // 제네릭 타입이라 compareTo 메소드를 사용해야

        // a.compareTo(b)
        // a == b: 0
        // a < b: -
        // a > b: +
        if (key.compareTo(root.key) < 0) {
            root.left = insert(root.left, key);
            return root;
        } else if (key.compareTo(root.key) > 0) {
            root.right = insert(root.right, key);
            return root;
        }

        return root;
    }

    public boolean contains(Node<E> root, E k) {
        if (root == null)
            return false;

        if (k.compareTo(root.key) == 0)
            return true;
        else if (k.compareTo(root.key) < 0)
            return contains(root.left, k);
        else
            return contains(root.right, k);
    }
}

class AVLTree<E extends Comparable<E>> extends BinarySearchTree<E> {
    @Override
    public Node<E> insert(Node<E> root, E key) {
        // 집어 넣는 과정은 BST와 동일하게
        // 대신 balance 검사해줘야

        if (root == null) {
            root = new Node<>(key);
        }

        if (key.compareTo(root.key) < 0) {
            root.left = insert(root.left, key);
        } else if (key.compareTo(root.key) > 0) {
            root.right = insert(root.right, key);
        } else {
            return root;
        }

        // height 계속 추적해 imbalance 감지
        updateHeight(root);

        int bf = balance(root);

        // rotation
        if (bf > 1 && balance(root.left) >= 0) {
            // balance 깨져있고, 왼쪽에 한 줄로 정렬된 경우
            // LL
            root = rotateRight(root);
        } else if (bf > 1) {
            // balance 깨져있고, 꼬여있는 케이스, LR
            root.left = rotateLeft(root.left);
            root = rotateRight(root);
        } else if (bf < -1 && balance(root.right) <= 0) {
            // balance 깨져있고 오른쪽에 한 줄로 정렬된 경우
            // RR
            root = rotateLeft(root);
        } else if (bf < -1) {
            // balance 깨져있고 꼬여있는 케이스, RL
            root.right = rotateRight(root.right); // 한 줄로 펴고
            root = rotateLeft(root); // root에 대해 rotate
        }

        return root;
    }

    private int height(Node<E> root) {
        // empty tree면 0
        if (root == null)
            return 0;
        else
            return root.height;
    }

    private void updateHeight(Node<E> root) {
        // 새로운 노드는 어딘가의 리프 노드에 달렸을 거임

        root.height = 1 + Math.max(height(root.left), height(root.right));
        // max(left subtree's height, right subtree's height)
    }

    // 1 or 0 or -1만 balanced!
    private int balance(Node<E> root) {
        if (root == null)
            return 0;
        else
            return height(root.left) - height(root.right);
    }

    private Node<E> rotateRight(Node<E> root) {
        /*
         * root(x)
         * root.left(y) root.right(x_r)
         * root.left.left(z) root.left.right(y_r)
         * 
         * 
         * to
         * 
         * 
         * y
         * z x
         * yr xr
         * 
         * 으 열심히 썼는데;;;;;;;;
         */

        Node<E> x = root, y = root.left, z = root.left.left, xr = root.right, yr = root.left.right;

        y.left = z;
        y.right = x;
        x.left = yr;
        // x.right = xr; // 원래 x 오른쪽에 달려 있으니

        // x와 y의 구조가 변경되었으므로
        updateHeight(y);
        updateHeight(x);

        return y;
    }

    private Node<E> rotateLeft(Node<E> root) {
        Node<E> x = root, y = root.right, z = root.right.right, xl = root.left, yl = root.right.left;

        y.left = x;
        y.right = z;
        // x.left = xl; // 원래 x 왼쪽에 달려 있으니
        x.right = yl;

        // x와 y의 구조가 변경되었으므로
        updateHeight(x);
        updateHeight(y);

        return y;
    }
}

public class TreeDemo {
    public static void main(String[] args) {
        // BinaryTree<Integer> bt = new BinaryTree<>();

        // System.out.println("Preorder traversal: ");
        // bt.preorder(bt.root);

        // System.out.println("\nInorder traversal: ");
        // bt.inorder(bt.root);

        // System.out.println("\nPostorder traversal: ");
        // bt.postorder(bt.root);

        // ========================================================= //

        // BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        // // 넣는 순서에 따라 다른 트리 가질 수도,,

        // bst.root = bst.insert(bst.root, 4);
        // bst.root = bst.insert(bst.root, 2);
        // bst.root = bst.insert(bst.root, 5);
        // bst.root = bst.insert(bst.root, 1);
        // bst.root = bst.insert(bst.root, 3);
        // bst.root = bst.insert(bst.root, 6);

        // System.out.println("Preorder traversal: ");
        // bst.preorder(bst.root);

        // System.out.println("\nInorder traversal: ");
        // bst.inorder(bst.root);

        // System.out.println("\nPostorder traversal: ");
        // bst.postorder(bst.root);

        // // ** REMOVE는 구현하지 않음 **

        // System.out.println();
        // System.out.println(bst.contains(bst.root, 5)); // true
        // System.out.println(bst.contains(bst.root, 7)); // false

        AVLTree<Integer> avl = new AVLTree<>();
        // 정렬된 순서로 넣는 게 최악

        avl.root = avl.insert(avl.root, 8);
        avl.root = avl.insert(avl.root, 9);
        avl.root = avl.insert(avl.root, 10);
        avl.root = avl.insert(avl.root, 12);
        avl.root = avl.insert(avl.root, 11);

        System.out.println("Preorder traversal: ");
        avl.preorder(avl.root);

        System.out.println("\nInorder traversal: ");
        avl.inorder(avl.root);

        System.out.println("\nPostorder traversal: ");
        avl.postorder(avl.root);
    }
}
