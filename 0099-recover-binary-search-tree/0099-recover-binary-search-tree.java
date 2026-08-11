class Solution {
    public void recoverTree(TreeNode root) {

        TreeNode curr = root;
        TreeNode prev = null;

        TreeNode first = null;
        TreeNode second = null;

        while (curr != null) {

            if (curr.left == null) {

                // Visit curr
                if (prev != null && prev.val > curr.val) {
                    if (first == null) {
                        first = prev;
                    }
                    second = curr;
                }

                prev = curr;
                curr = curr.right;

            } else {

                TreeNode pred = curr.left;

                // Find inorder predecessor
                while (pred.right != null && pred.right != curr) {
                    pred = pred.right;
                }

                if (pred.right == null) {

                    // Create thread
                    pred.right = curr;
                    curr = curr.left;

                } else {

                    // Remove thread
                    pred.right = null;

                    // Visit curr
                    if (prev != null && prev.val > curr.val) {
                        if (first == null) {
                            first = prev;
                        }
                        second = curr;
                    }

                    prev = curr;
                    curr = curr.right;
                }
            }
        }

        // Swap the two incorrect values
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }
}