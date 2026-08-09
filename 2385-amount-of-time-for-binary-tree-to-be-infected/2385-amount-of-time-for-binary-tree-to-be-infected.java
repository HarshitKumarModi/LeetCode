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

    HashMap<TreeNode, TreeNode> parent = new HashMap<>();

    public int amountOfTime(TreeNode root, int start) {

        // Step 1: Store parent of every node
        TreeNode startNode = findParent(root, null, start);

        // Step 2: BFS
        Queue<TreeNode> q = new LinkedList<>();
        HashSet<TreeNode> visited = new HashSet<>();

        q.add(startNode);
        visited.add(startNode);

        int time = -1;

        while (!q.isEmpty()) {

            int size = q.size();
            time++;

            for (int i = 0; i < size; i++) {

                TreeNode curr = q.poll();

                // Left child
                if (curr.left != null && !visited.contains(curr.left)) {
                    visited.add(curr.left);
                    q.add(curr.left);
                }

                // Right child
                if (curr.right != null && !visited.contains(curr.right)) {
                    visited.add(curr.right);
                    q.add(curr.right);
                }

                // Parent
                TreeNode p = parent.get(curr);

                if (p != null && !visited.contains(p)) {
                    visited.add(p);
                    q.add(p);
                }
            }
        }

        return time;
    }

    // Find start node + create parent map
    TreeNode findParent(TreeNode root, TreeNode par, int start) {

        if (root == null)
            return null;

        parent.put(root, par);

        if (root.val == start)
            return root;

        TreeNode left = findParent(root.left, root, start);

        if (left != null)
            return left;

        return findParent(root.right, root, start);
    }
}