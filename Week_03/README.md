学习笔记
### 6.泛型递归、树的递归
#### 6.1 Example:树的递归
* 树的面试题解法一般都是递归
``` go
def preorder(self, root): 
if root: 
 self.traverse_path.append(root.val) 
 self.preorder(root.left) 
 self.preorder(root.right) 
def inorder(self, root): 
if root: 
 self.inorder(root.left) 
 self.traverse_path.append(root.val) 
 self.inorder(root.right) 
def postorder(self, root): 
if root: 
 self.postorder(root.left) 
 self.postorder(root.right) 
 self.traverse_path.append(root.val)
```
#### 6.2 递归模板
```java
//1.终止条件 2.业务逻辑 3.level
class test{
public void recur(int level, int param) {
// terminator 
if (level > MAX_LEVEL) {
// process result 
 return; }
 
// process current logic 
 process(level, param);
 
// drill down 
 recur( level: level + 1, newParam);
// restore current status 
}}
```

### 7 分治与回溯 Divide & Conquer
（特殊的递归）重复性
![530adab542e80b5b01288aa52cad494c.png](evernotecid://0000E11B-C14D-42DF-A687-4CFAE05027ED/appyinxiangcom/25828983/ENResource/p100)

```java
// 递归模版
/*1.recursion terminator 终止条件
2.process 当前层的逻辑处理
3.drill down 寻找下层
4.revert */
```

```python
分治法模版：
1.recusion terminator
2.prepare data 处理当前逻辑
3.conquer subproblems 调用函数下探到下一层 分解子问题
4.process and generate the final result(组装结果集返回)
5.revert the current level states

------------------------------------------
def divide_conquer(problem, param1, param2, ...): 
# recursion terminator 
if problem is None: 
 print_result 
return
# prepare data 处理当前逻辑
 data = prepare_data(problem) 
 subproblems = split_problem(problem, data) 
# conquer subproblems 调用函数下探到下一层 分解子问题
 subresult1 = self.divide_conquer(subproblems[0], p1, ...) 
 subresult2 = self.divide_conquer(subproblems[1], p1, ...) 
 subresult3 = self.divide_conquer(subproblems[2], p1, ...) 
...
# process and generate the final result 
 result = process_result(subresult1, subresult2, subresult3, …) 
# revert the current level states

```
#### 7.2 回溯 Backtracking
排列后限制条件