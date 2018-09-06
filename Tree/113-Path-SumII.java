// 返回值是List,不想用全局变量 => 借助辅助函数传入List<List<Integer>>
// root == null && sum == 0 找到一个答案 => 应该如何加入或者返回这个值
// 作为临时容器的temp 何时应该新建 何时应该用原有的
class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        helper(res, new ArrayList<Integer>(), root, sum);
        return res;
    }
    private void helper(List<List<Integer>> res, List<Integer> temp, TreeNode root, int sum) {
        if (root == null) return;
        if (root.left == null && root.right == null && root.val == sum) {
            temp.add(root.val);
            res.add(new ArrayList<Integer>(temp));
            temp.remove(temp.size() - 1);
            return;
        }
        // ? no need return helper
        temp.add(root.val);
        helper(res, temp, root.left, sum - root.val);
        temp.remove(temp.size() - 1);


        temp.add(root.val);
        helper(res, temp, root.right, sum - root.val);
        temp.remove(temp.size() - 1);
            

    }
}