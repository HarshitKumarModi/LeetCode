/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {

        int minDistance = Integer.MAX_VALUE;
        int maxDistance = -1;

        ListNode prev = head;
        ListNode curr = head.next;

        int index = 1;
        int firstCritical = -1;
        int prevCritical = -1;

        while (curr != null && curr.next != null) {

            // Check if current node is a critical point
            if ((curr.val > prev.val && curr.val > curr.next.val) ||
                (curr.val < prev.val && curr.val < curr.next.val)) {

                // First critical point
                if (firstCritical == -1) {
                    firstCritical = index;
                }

                // Calculate distance from previous critical point
                if (prevCritical != -1) {
                    minDistance = Math.min(
                        minDistance,
                        index - prevCritical
                    );

                    maxDistance = index - firstCritical;
                }

                prevCritical = index;
            }

            prev = curr;
            curr = curr.next;
            index++;
        }

        // Fewer than two critical points
        if (firstCritical == -1 || prevCritical == firstCritical) {
            return new int[]{-1, -1};
        }

        return new int[]{minDistance, maxDistance};
    }
}