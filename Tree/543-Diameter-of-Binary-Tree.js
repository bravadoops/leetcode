// Return value and max
// No need global variable
var diameterOfBinaryTree = function(root) {
    if (root === null) return 0;
    return helper(root).max;
};
const helper = root => {
    if (root === null) return {val: 0, max: 0};
    let left = helper(root.left);
    let right = helper(root.right);
    let m = Math.max(left.val + right.val, Math.max(left.max, right.max));
    return {val: Math.max(left.val, right.val) + 1, max: m};
}