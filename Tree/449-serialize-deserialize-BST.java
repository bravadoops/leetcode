// 简单遍历方法
// Worst time complexity O(N^2) Time complexity O(N^lgN)
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        // right method I(recursively)
        if (root == null) return "*";
        StringBuilder sb = new StringBuilder();
        sb.append(root.val).append(",");
        if (root.left != null) sb.append(serialize(root.left));
        if (root.right != null) sb.append(serialize(root.right));
        return sb.toString();
        // right method II
        // return iterate(root);
        
        // Wrong method
        // return root.val + "," 
        //     + serialize(root.left) == "*" ? "" : (serialize(root.left) + ",")
        //     + serialize(root.right) == "*" ? "" : (serialize(root.right) + ",");
    }

    private String iterate(TreeNode root) {
        // right method II(iteratively)
        StringBuilder sb = new StringBuilder();
        if (root == null) return "*";
        Stack<TreeNode> st = new Stack<>();
        st.push(root);
        while (!st.isEmpty()) {
            root = st.pop();
            sb.append(root.val).append(",");
            if (root.right != null) st.push(root.right);
            if (root.left != null) st.push(root.left);
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        // right method I
        if (data == "*") return null;
        
        String[] str = data.split(",");
        int[] nums = new int[str.length];
        for (int i = 0; i < str.length; i++) {
            nums[i] = Integer.parseInt(str[i]);
        }
        TreeNode res = helper(nums, 0, nums.length);
        return res;
        // right method II
        // return queueDeserialize(data);
    }
    private TreeNode queueDeserialize(String data) {
        if (data == "*") return null;
        String[] str = data.split(",");
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < str.length; i++) {
            q.offer(Integer.parseInt(str[i]));
        }
        return getNode(q);
    }
    private TreeNode helper(int[] nums, int start, int end) {
        if (start >= end) return null;
        if (start + 1 == end) return new TreeNode(nums[start]);
        
        TreeNode root = new TreeNode(nums[start]);
        int sp = start;
        while (sp < nums.length && nums[sp] <= nums[start]) {
            sp++;
        }
        
        root.left = helper(nums, start+1, sp);
        root.right = helper(nums, sp, end);
        return root;
    }
    private TreeNode getNode(Queue<Integer> q) {
        if (q.isEmpty()) return null;
        TreeNode root = new TreeNode(q.poll());
        
        Queue<Integer> left = new LinkedList<>();
        while (!q.isEmpty() && q.peek() < root.val) left.offer(q.poll());
        root.left = getNode(left);
        root.right = getNode(q);
        return root;
    }
}
