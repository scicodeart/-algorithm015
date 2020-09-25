学习笔记

### 8 深度优先搜索/广度优先搜索
#### DFS模版
![502bb002e684d87841737e92b9fa675c.png](evernotecid://0000E11B-C14D-42DF-A687-4CFAE05027ED/appyinxiangcom/25828983/ENResource/p104)
![8a38522d25b1cedcfeceb2917f69f6d9.png](evernotecid://0000E11B-C14D-42DF-A687-4CFAE05027ED/appyinxiangcom/25828983/ENResource/p106)

* 递归
```java
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> allResults = new ArrayList<>();
        if(root==null){
            return allResults;
        }
        travel(root,0,allResults);
        return allResults;
    }


    private void travel(TreeNode root,int level,List<List<Integer>> results){
        if(results.size()==level){
            results.add(new ArrayList<>());
        }
        results.get(level).add(root.val);
        if(root.left!=null){
            travel(root.left,level+1,results);
        }
        if(root.right!=null){
           travel(root.right,level+1,results);
        }
    }
```
* 非递归
```c++
//C/C++
//非递归写法：
void dfs(Node* root) {
  map<int, int> visited;
  if(!root) return ;

  stack<Node*> stackNode;
  stackNode.push(root);

  while (!stackNode.empty()) {
    Node* node = stackNode.top();
    stackNode.pop();
    if (visited.count(node->val)) continue;
    visited[node->val] = 1;

    for (int i = node->children.size() - 1; i >= 0; --i) {
        stackNode.push(node->children[i]);
    }
  }
  return ;
}
```
#### BFS模版
![1c97ea8ca40a5dec0c8ef0d9dbdee655.png](evernotecid://0000E11B-C14D-42DF-A687-4CFAE05027ED/appyinxiangcom/25828983/ENResource/p108)
![9c768219e15a992a69e3af6297ccb316.png](evernotecid://0000E11B-C14D-42DF-A687-4CFAE05027ED/appyinxiangcom/25828983/ENResource/p109)

```java
//Java
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> allResults = new ArrayList<>();
    if (root == null) {
        return allResults;
    }
    Queue<TreeNode> nodes = new LinkedList<>();
    nodes.add(root);
    while (!nodes.isEmpty()) {
        int size = nodes.size();
        List<Integer> results = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            TreeNode node = nodes.poll();
            results.add(node.val);
            if (node.left != null) {
                nodes.add(node.left);
            }
            if (node.right != null) {
                nodes.add(node.right);
            }
        }
        allResults.add(results);
    }
    return allResults;
}
```
### 9 贪心算法 Greedy
#### 概念
* 贪心算法是一种在每一步选择中都采取在当前状态下最好或最优（即最有利）的选择，从而希望导致结果是全局最好或最优的算法。 
* 贪心算法与动态规划的不同在于它对每个子问题的解决方案都做出选择，不能回退。动态规划则会保存以前的运算结果，并根据以前的结果对当前进行选择，有回退功能。
#### 解决问题
* 最优化问题，如：求图中的最小生成树、求哈夫曼编码等。然而对于工程和生活中的问题，贪心法一般不能得到我们所要求的答案。 
#### 对比
* 贪心：当下做局部最优判定
* 回溯：能够回退
* 动态规划：最优判断+回退

### 10 二分查找
#### 二分查找的前提
1. 目标函数单调性（单调递增或者递减）
2. 存在上下界（bounded）
3. 能够通过索引访问（index accessible)
#### 代码模版
```java
// 代码模版
// Java
public int binarySearch(int[] array, int target) {
    int left = 0, right = array.length - 1, mid;
    while (left <= right) {
        mid = (right - left) / 2 + left;

        if (array[mid] == target) {
            return mid;
        } else if (array[mid] > target) {
            right = mid - 1;
        } else {
            left = mid + 1;
        }
    }

    return -1;
}
```