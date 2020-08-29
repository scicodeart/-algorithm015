学习笔记
### 0.数据结构概览

![0df4a919f1c35dccf762cf7f69112590.png](evernotecid://0000E11B-C14D-42DF-A687-4CFAE05027ED/appyinxiangcom/25828983/ENResource/p71)

### 1.数组、链表、跳表
#### 1.1 Array
##### 底层原理：
内存管理器：每当申请数组时，计算机开辟了一段连续的地址。
![97e58caeb2e6619168611df87724ee67.png](evernotecid://0000E11B-C14D-42DF-A687-4CFAE05027ED/appyinxiangcom/25828983/ENResource/p72)
##### 时间复杂度：
* 访问：O(1);
* 插入删除：O(n);
    * O(n-i+1)
    * 最差：O(n);
    * 最好：O(1);
    
```java
    class ArrayList{
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        modCount++;
        final int s;
        Object[] elementData;
        if ((s = size) == (elementData = this.elementData).length)
            elementData = grow();
        System.arraycopy(elementData, index,
                         elementData, index + 1,
                         s - index);
        elementData[index] = element;
        size = s + 1;
    }
    
    // 扩容
    private Object[] grow(int minCapacity) {
        int oldCapacity = elementData.length;
        if (oldCapacity > 0 || elementData != DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            int newCapacity = ArraysSupport.newLength(oldCapacity,
            minCapacity - oldCapacity, /* minimum growth */
            oldCapacity >> 1 /* preferred growth (1.5倍) */);
            return elementData = Arrays.copyOf(elementData, newCapacity);
        } else {
            return elementData = new Object[Math.max(DEFAULT_CAPACITY, minCapacity)];
        }
    } }
```
> <<: 左移运算符，num << 1，相当于num乘以2; >>:右移运算符，num >> 1，相当于num除以2
#### 1.2 Linked List
##### 定义
```java
 class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
    }
}
```
* Java：双向链表
```java
    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
```
##### Linked List 增加结点
![841352adbaf0eee558a42188facc8503.png](evernotecid://0000E11B-C14D-42DF-A687-4CFAE05027ED/appyinxiangcom/25828983/ENResource/p74)
##### Linked List 删除结点
![6f6b2fe0736d3d2ff6fa9aee8e737d50.png](evernotecid://0000E11B-C14D-42DF-A687-4CFAE05027ED/appyinxiangcom/25828983/ENResource/p76)
##### 时间复杂度
* prepend： O(1)
* append： O(1)
* lookup： O(n)
* insert： O(1)
* delete： O(1)
> 增删快，查询耗时

#### 1.3 Skip List
##### 背景：
1989年出现：科学家对于链表的优化操作
##### 特点：
* 注意：只能用于元素有序的情况。
* 跳表（skip list）对标的是平衡树（AVL Tree）和二分查找
* 是一种 插入/删除/搜索 都是 O(log n) 的数据结构。
* 它最大的优势是原理简单、容易实现、方便扩展、效率更高。
* 应用：替代平衡树，如 Redis、LevelDB等。

##### 结构：
![71df04e26ce971b49bb92e46c095630a.png](evernotecid://0000E11B-C14D-42DF-A687-4CFAE05027ED/appyinxiangcom/25828983/ENResource/p77)
##### 过程：
![dfd6753e600c5cd0075c46947a4f0a95.png](evernotecid://0000E11B-C14D-42DF-A687-4CFAE05027ED/appyinxiangcom/25828983/ENResource/p78)
##### 复杂度分析：
$n/2$、$n/4$、$n/8$、第 k 级索引结点的个数就是 $n/(2^k)$
假设索引有 h 级，最高级的索引有 2 个结点。$n/(2^h)=2$，从而求得$h = log_2(n)-1$
> 链表查询优化，但是维护成本较大：增加或删除元素，需要重新规划索引

#### 1.4 总结
* 数组-> 在添加，删除操作频繁的情况下：数组并不不好用
* 链表-> 增删快，查询耗时
* 跳表-> 查询链表优化，但是维护成本较大：增加或删除元素，需要重新规划索引

#### 1.5 应用
* 链表：LRU缓存机制
  > [LRU缓存](https://www.jianshu.com/p/b1ab4a170c3c)
  >  [LRU-leetcode](https://leetcode-cn.com/problems/lru-cache)
* 跳表：Redis
  > [Redis跳表](https://redisbook.readthedocs.io/en/latest/internal-datastruct/skiplist.html)
  > [Redis为什么使用跳表而不用红黑树](https://www.zhihu.com/question/20202931)