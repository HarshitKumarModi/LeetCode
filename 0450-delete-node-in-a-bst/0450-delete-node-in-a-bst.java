/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public void delete(TreeNode root, int key){
        if(root == null) return;
        if(root.val>key){
            if(root.left == null) return;
            if(root.left.val == key){
                if(root.left.left == null && root.left.right == null){ // 0 child
                    root.left =null;
                }
                else if(root.left.left == null || root.left.right == null){ // 1 child
                    if(root.left.left!=null){
                        root.left = root.left.left;
                    }
                    else{
                        root.left = root.left.right;
                    }
                }
                else {
                    TreeNode curr = root.left;
                    if(curr.left != null){
                        TreeNode pred = curr.left;
                        while(pred.right != null){
                            pred = pred.right;
                        }
                        delete(root,pred.val);
                        pred.left = curr.left;
                        pred.right = curr.right;
                        root.left = pred;
                    }
                }
            }
            else delete(root.left,key);
            
        }
        else {
            if(root.right == null) return;
            if(root.right.val == key){
                if(root.right.left == null && root.right.right == null){ // 0 child
                    root.right =null;
                }
                else if(root.right.left == null || root.right.right == null){ // 1 child
                    if(root.right.left!=null){
                        root.right = root.right.left;
                    }
                    else{
                        root.right = root.right.right;
                    }
                }
                else {
                    TreeNode curr = root.right;
                    if(curr.left != null){
                        TreeNode pred = curr.left;
                        while(pred.right != null){
                            pred = pred.right;
                        }
                        delete(root,pred.val);
                        pred.left = curr.left;
                        pred.right = curr.right;
                        root.right = pred;
                    }
                }
            }
            else delete(root.right,key);
        }
        return;
    }
    public TreeNode deleteNode(TreeNode root, int key) {
        TreeNode temp = new TreeNode(Integer.MAX_VALUE);
        temp.left = root;
        delete(temp,key);
        root = temp.left;
        return root;
    }
}