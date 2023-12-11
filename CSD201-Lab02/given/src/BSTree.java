
import java.io.*;
import java.util.*;

public class BSTree {

    Node root;

    // Default constructor
    BSTree() {
        this.root = null;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public void clear() {
        this.root = null;
    }

    public void visit(Node p) {
        System.out.print("p.info: ");
        if (p != null) {
            System.out.println(p.getInfo() + " ");
        }
    }

    public void fvisit(Node p, RandomAccessFile f) throws Exception {
        if (p != null) {
            f.writeBytes(p.getInfo() + " ");
        }
    }

    public void breadth(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        Queue q = new Queue();
        q.enqueue(p);
        Node r;
        while (!q.isEmpty()) {
            r = q.dequeue();
            fvisit(r, f);

            if (r.left != null) {
                q.enqueue(r.left);
            }

            if (r.right != null) {
                q.enqueue(r.right);
            }
        }
    }

    public void preOrder(Node p, RandomAccessFile f) throws Exception {

        if (p == null) {
            return;
        }

        fvisit(p, f);
        preOrder(p.left, f);
        preOrder(p.right, f);
    }

    void inOrder(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }

        inOrder(p.left, f);
        fvisit(p, f);
        inOrder(p.right, f);
    }

    void postOrder(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }

        postOrder(p.left, f);
        postOrder(p.right, f);
        fvisit(p, f);
    }

    /**
     * Do NOT modify this method Load 3 lines of data from file: line k (for
     * owner), and line k+1 (for price), and line k+2 (for color)
     *
     * @param k the k-th line where data is started to be loaded
     */
    void loadData(int k) {
        String[] a = Lib.readLineToStrArray("data.txt", k);
        int[] b = Lib.readLineToIntArray("data.txt", k + 1);
        int[] c = Lib.readLineToIntArray("data.txt", k + 2);

        int n = a.length;
        for (int i = 0; i < n; i++) {
            insert(a[i], b[i], c[i]); // insert the new Node(a[i], b[i], c[i]) into the BST
        }
    }

    /**
     * Luy y: 1. SV KHONG su dung tieng Viet co dau trong bai lam de tranh Error
     * khi run chuong trinh. 2. Neu khong tuan thu se nhan diem 0 (khong).
     *
     * Question 1: use Bird’s price as the key attribute when building a BST.
     * implement the 'insert' method that inserts a new Node into the BST if the
     * attribute 'Price' of Bird is higher than zero (>0). The output of this
     * method will be written into the file 'f1.txt'. Therefore you should open
     * this file to see/test your code output. Example: with the content given
     * in the file 'data.txt', the content of 'f1.txt' after insertion should be
     * (A,7,9) (C,4,3) (B,9,4) (E,2,5) (Y,6,-7) (D,8,6) (E,2,5) (C,4,3) (Y,6,-7)
     * (A,7,9) (D,8,6) (B,9,4)
     *
     *
     * @param xOwner the owner of the input Bird
     * @param xPrice the price of the input Bird
     * @param xColor the color of the input Bird
     */
    Node insert(String xOwner, int xPrice, int xColor) {
        //---------------------------------------------------------------------------------------
        //------ Start your code here------------------------------------------------------------
        // Check if xPrice is greater than zero
        if (xPrice <= 0) {
            return null;
        }

        // Create a new Bird with the given attributes
        Bird newBird = new Bird(xOwner, xPrice, xColor);

        // If the tree is empty, make the new Bird the root
        if (root == null) {
            root = new Node(newBird);
            return root;
        }

        // Use a recursive helper function to insert the new Bird
        insertRec(root, newBird);

        return root;
        //------ End your code here--------------------------------------------------------------
        //---------------------------------------------------------------------------------------
    }

    private void insertRec(Node currentNode, Bird newBird) {
        // Compare the new Bird's price with the current node's Bird price
        if (newBird.getPrice() < currentNode.getInfo().getPrice()) {
            // If the new Bird's price is less, go left
            if (currentNode.left == null) {
                // If there's no left child, insert the new Bird as the left child
                currentNode.left = new Node(newBird);
            } else {
                // Otherwise, recursively insert in the left subtree
                insertRec(currentNode.left, newBird);
            }
        } else if (newBird.getPrice() > currentNode.getInfo().getPrice()) {
            // If the new Bird's price is greater, go right
            if (currentNode.right == null) {
                // If there's no right child, insert the new Bird as the right child
                currentNode.right = new Node(newBird);
            } else {
                // Otherwise, recursively insert in the right subtree
                insertRec(currentNode.right, newBird);
            }
        }
    }

    /**
     * Do NOT modify this method This is a helper method for writing data
     * (node's info) stored in the BST to file
     *
     * @throws Exception
     */
    void f1() throws Exception {
        clear();
        loadData(1);
        String fname = "f1.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        breadth(root, f);
        f.writeBytes("\r\n");
        inOrder(root, f);
        f.writeBytes("\r\n");
        f.close();
    }

    // This method is used for Question 2
    void f2() throws Exception {
        clear();
        loadData(5);
        String fname = "f2.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        breadth(root, f);
        f.writeBytes("\r\n");

        /**
         * Question 2: Perform breadth-first-search on the BST, but ONLY visit
         * nodes that has Bird's color higher than 5. Hint: This method is
         * similar to the method 'breadth' (provided in this class already). You
         * should create a new method which body is similar to 'breadth' for
         * doing BFS but considering only color higher than 5. The output f2()
         * will be written into the file 'f2.txt'. Therefore you should open
         * this file to see/test your code output. Example: With the data
         * provided in 'data.txt', the content of 'f2.txt' after running this
         * method is (C,8,2) (D,6,1) (E,9,4) (F,2,3) (G,7,8) (H,1,7) (I,3,9)
         * (J,5,5) (K,4,6) (G,7,8) (H,1,7) (I,3,9) (K,4,6)
         */
        //---------------------------------------------------------------------------------------
        //------ Start your code here------------------------------------------------------------
        if (root == null) {
            return;
        }

        java.util.Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            Bird currentBird = current.getInfo();

            // Check if the current Bird's color is greater than 5
            if (currentBird.getColor() <= 6) {
                System.out.print(currentBird);
            }

            if (current.left != null) {
                queue.add(current.left);
            }

            if (current.right != null) {
                queue.add(current.right);
            }
        }

        System.out.println();
        //------ End your code here--------------------------------------------------------------
        //---------------------------------------------------------------------------------------
        f.writeBytes("\r\n");
        f.close();
    }

    // This method is used for Question 3
    void f3() throws Exception {
        clear();
        loadData(9);
        String fname = "f3.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        postOrder(root, f);
        f.writeBytes("\r\n");
        /**
         * Question 3: Insert into the current tree a new Bird which Onwer =
         * 'XYZ', price = 100k, color = 200k, where k is height of the current
         * tree before insertion Hint: (1) Implement a method to calculate the
         * tree's height (2) Insert the new Bird('XYZ', 100*Tree Height,
         * 200*Tree Height) into the current tree The output f3() will be
         * written into the file 'f3.txt'. Therefore you should open this file
         * to see/test your code output. Example: With the data provided in
         * 'data.txt', the content of 'f3.txt' after running this method is
         * (H,1,7) (K,4,6) (J,5,5) (I,3,9) (F,2,3) (G,7,8) (D,6,1) (E,9,4)
         * (C,8,2) (H,1,7) (K,4,6) (J,5,5) (I,3,9) (F,2,3) (G,7,8) (D,6,1)
         * (XYZ,600,1200) (E,9,4) (C,8,2)
         */
        //---------------------------------------------------------------------------------------
        //------ Start your code here------------------------------------------------------------
        // Calculate the height of the tree
        int treeHeight = getHeight(root);

        // Insert the new Bird with calculated values
        Bird newBird = new Bird("XYZ", 100 * treeHeight, 200 * treeHeight);
        insertRec(root, newBird);
        //------ End your code here--------------------------------------------------------------
        //---------------------------------------------------------------------------------------
        postOrder(root, f);
        f.writeBytes("\r\n");
        f.close();
    }

    int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = getHeight(node.left);
        int rightHeight = getHeight(node.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }
    
    int maxHeight(Node node){
        if (node == null) {
            return 0;
        } else {
            int lDepth = maxHeight(node.left);
            int rDepth = maxHeight(node.right);
            if (lDepth>rDepth){
                return lDepth + 1;
            } else {
                return rDepth + 1;
            }
        }    
    }

    // This method is used for Question 4
    void f4() throws Exception {
        clear();
        loadData(13);;
        String fname = "f4.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        postOrder(root, f);
        f.writeBytes("\r\n");
        /**
         * Question 4: Reset the Bird's color of all leaf Nodes to 100 Hint:
         * Leaf nodes have neither left child nor right child The output f4()
         * will be written into the file 'f4.txt'. Therefore you should open
         * this file to see/test your code output. Example: With the data
         * provided in 'data.txt', the content of 'f4.txt' after running this
         * method is (H,1,7) (K,4,6) (J,5,5) (I,3,9) (F,2,3) (G,7,8) (D,6,1)
         * (E,9,4) (C,8,2) (H,1,100) (K,4,100) (J,5,5) (I,3,9) (F,2,3) (G,7,100)
         * (D,6,1) (E,9,100) (C,8,2)
         */
        //---------------------------------------------------------------------------------------
        //------ Start your code here------------------------------------------------------------
        resetBird(root);

        //------ End your code here--------------------------------------------------------------
        //---------------------------------------------------------------------------------------
        postOrder(root, f);
        f.writeBytes("\r\n");
        f.close();
    }
    
    void resetBird(Node node){
        if (node == null){
            return;
        }
        if (node.left==null && node.right==null){
            node.getInfo().setColor(100);
        }
        
        resetBird(node.left);
        resetBird(node.right);
    }
    
    int countLeaf(Node node){
        if (node == null){
            return 0;
        }
        
        if (node.left==null && node.right==null){
            return 1;
        }
        
        return countLeaf(node.left) + countLeaf(node.right);
    }
    
    int countNode(Node node){
        if (node == null){
            return 0;
        }
        
        return countNode(node.left) + countNode(node.right) + 1;
    }
    
    // This method is used for Question 5
    void f5() throws Exception {
        clear();
        loadData(13);;
        String fname = "f5.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        postOrder(root, f);
        f.writeBytes("\r\n");

        //Question 5: perform the left single rotation from the root node.
        //---------------------------------------------------------------------------------------
        //------ Start your code here------------------------------------------------------------
        root = rotateWithLeftChild(root);

        //------ End your code here--------------------------------------------------------------
        //---------------------------------------------------------------------------------------
        postOrder(root, f);
        f.writeBytes("\r\n");
        f.close();
    }

    Node rotateWithLeftChild(Node k2) {
        Node k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        return k1;
    }

    Node rotateWithRightChild(Node k1) {
        Node k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        return k2;
    }

    // This method is used for Question 6
    void f6() throws Exception {
        clear();
        loadData(13);;
        String fname = "f6.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        postOrder(root, f);
        f.writeBytes("\r\n");

        //Question 6: perform the double rotation for the left-right case (from the root node).
        //---------------------------------------------------------------------------------------
        //------ Start your code here------------------------------------------------------------
        root = doubleRotateWithLeftChild(root);
        //------ End your code here--------------------------------------------------------------
        //---------------------------------------------------------------------------------------
        postOrder(root, f);
        f.writeBytes("\r\n");
        f.close();
    }

    Node doubleRotateWithLeftChild(Node k3) {
        k3.left = rotateWithRightChild(k3.left);
        return rotateWithLeftChild(k3);
    }

    Node doubleRotateWithRightChild(Node k1) {
        k1.right = rotateWithLeftChild(k1.right);
        return rotateWithRightChild(k1);
    }

    // This method is used for Question 7
    void f7() throws Exception {
        clear();
        loadData(13);;
        String fname = "f7.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        postOrder(root, f);
        f.writeBytes("\r\n");

        //Question 7: delete all nodes with the price = 8.
        //---------------------------------------------------------------------------------------
        //------ Start your code here------------------------------------------------------------
        deleteNodesWithPrice(root, 8);
        //------ End your code here--------------------------------------------------------------
        //---------------------------------------------------------------------------------------
        postOrder(root, f);
        f.writeBytes("\r\n");
        f.close();
    }

    Node deleteNodesWithPrice(Node root, int priceToDelete) {
        if (root == null) {
            return null;
        }

        // Recursively delete nodes with the specified price in both subtrees
        root.left = deleteNodesWithPrice(root.left, priceToDelete);
        root.right = deleteNodesWithPrice(root.right, priceToDelete);

        // If the current node has the specified price, delete it
        if (root.getInfo().getPrice() == priceToDelete) {
            // Cases:
            // 1. Node with only one child or no child
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            // 2. Node with two children: get the inorder successor (smallest in the right subtree)
            root.getInfo().setPrice(minValue(root.right));

            // Delete the inorder successor
            root.right = deleteNodesWithPrice(root.right, root.getInfo().getPrice());
        }

        return root;
    }

// Function to find the minimum value node in a BST
    int minValue(Node node) {
        int minValue = node.getInfo().getPrice();
        while (node.left != null) {
            minValue = node.left.getInfo().getPrice();
            node = node.left;
        }
        return minValue;
    }

}
