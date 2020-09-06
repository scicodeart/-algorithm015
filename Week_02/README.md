学习笔记

### 3.哈希表、映射、集合

#### 3.1 Hash Table
##### 概念
哈希表（Hash table），也叫散列表，是根据关键码值（Key value）而直接进行访问的数据结构。
##### 实现
它通过把关键码值映射到表中一个位置来访问记录，以加快查找的速度。这个映射函数叫作散列函数（Hash Function），存放记录的数组叫作哈希表（或散列表）。
##### 应用
• 电话号码簿
• 用户信息表
• 缓存（LRU Cache） • 键值对存储（Redis）
##### Hash函数
每个字母ascii码值相加，然后取模运算：
![1a338272b4da5f052b69613b06609baa.png](evernotecid://0000E11B-C14D-42DF-A687-4CFAE05027ED/appyinxiangcom/25828983/ENResource/p81)
##### Hash碰撞
![f4f364e423df4a55962cf9c45a9724ee.png](evernotecid://0000E11B-C14D-42DF-A687-4CFAE05027ED/appyinxiangcom/25828983/ENResource/p82)

* 解决办法：1.链地址法(hashmap)、2.开放地址法：再推空间(线性探测、再平方探测、随机探测)
#### 3.2 hashMap & hashSet 源码总结
##### HashMap总结
在 JDK 1.7 中 HashMap 是以数组加链表的形式组成的，JDK 1.8 之后新增了红黑树的组成结构，当链表大于 8 并且容量大于 64 时，链表结构会转换成红黑树结构，它的组成结构如下图所示：
![8bfb0a4f7c034623c1112f01b7076285.png](evernotecid://0000E11B-C14D-42DF-A687-4CFAE05027ED/appyinxiangcom/25828983/ENNote/p124?hash=8bfb0a4f7c034623c1112f01b7076285)
JDK 1.8 之所以添加红黑树是因为一旦链表过长，会严重影响 HashMap 的性能，而红黑树具有快速增删改查的特点，这样就可以有效的解决链表过长时操作比较慢的问题。
* put源码分析
![63cf97b493d37084414c5959e7b13f9c.png](evernotecid://0000E11B-C14D-42DF-A687-4CFAE05027ED/appyinxiangcom/25828983/ENNote/p124?hash=63cf97b493d37084414c5959e7b13f9c)

```java
public V put(K key, V value) {
    // 对 key 进行哈希操作
    return putVal(hash(key), key, value, false, true);
}
final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
               boolean evict) {
    Node<K,V>[] tab; Node<K,V> p; int n, i;
    // 哈希表为空则创建表
    if ((tab = table) == null || (n = tab.length) == 0)
        n = (tab = resize()).length;
    // 根据 key 的哈希值计算出要插入的数组索引 i
    if ((p = tab[i = (n - 1) & hash]) == null)
        // 如果 table[i] 等于 null，则直接插入
        tab[i] = newNode(hash, key, value, null);
    else {
        Node<K,V> e; K k;
        // 如果 key 已经存在了，直接覆盖 value
        if (p.hash == hash &&
            ((k = p.key) == key || (key != null && key.equals(k))))
            e = p;
        // 如果 key 不存在，判断是否为红黑树
        else if (p instanceof TreeNode)
            // 红黑树直接插入键值对
            e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
        else {
            // 为链表结构，循环准备插入
            for (int binCount = 0; ; ++binCount) {
                // 下一个元素为空时
                if ((e = p.next) == null) {
                    p.next = newNode(hash, key, value, null);
                    // 转换为红黑树进行处理
                    if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                        treeifyBin(tab, hash);
                    break;
                }
                //  key 已经存在直接覆盖 value
                if (e.hash == hash &&
                    ((k = e.key) == key || (key != null && key.equals(k))))
                    break;
                p = e;
            }
        }
        if (e != null) { // existing mapping for key
            V oldValue = e.value;
            if (!onlyIfAbsent || oldValue == null)
                e.value = value;
            afterNodeAccess(e);
            return oldValue;
        }
    }
    ++modCount;
    // 超过最大容量，扩容
    if (++size > threshold)
        resize();
    afterNodeInsertion(evict);
    return null;
}

```
* get源码分析
```java
public V get(Object key) {
    Node<K,V> e;
    // 对 key 进行哈希操作
    return (e = getNode(hash(key), key)) == null ? null : e.value;
}
final Node<K,V> getNode(int hash, Object key) {
    Node<K,V>[] tab; Node<K,V> first, e; int n; K k;
    // 非空判断
    if ((tab = table) != null && (n = tab.length) > 0 &&
        (first = tab[(n - 1) & hash]) != null) {
        // 判断第一个元素是否是要查询的元素
        if (first.hash == hash && // always check first node
            ((k = first.key) == key || (key != null && key.equals(k))))
            return first;
        // 下一个节点非空判断
        if ((e = first.next) != null) {
            // 如果第一节点是树结构，则使用 getTreeNode 直接获取相应的数据
            if (first instanceof TreeNode)
                return ((TreeNode<K,V>)first).getTreeNode(hash, key);
            do { // 非树结构，循环节点判断
                // hash 相等并且 key 相同，则返回此节点
                if (e.hash == hash &&
                    ((k = e.key) == key || (key != null && key.equals(k))))
                    return e;
            } while ((e = e.next) != null);
        }
    }
    return null;
}

```
从以上源码可以看出，当哈希冲突时我们需要通过判断 key 值是否相等，才能确认此元素是不是我们想要的元素。

##### HashSet源码
```java
class HashSet<E>
    extends AbstractSet<E> //继承的是Set
    implements Set<E>, Cloneable, java.io.Serializable{
    
    /** 
     * Constructs a new, empty set; the backing <tt>HashMap</tt> instance has
     * default initial capacity (16) and load factor (0.75).
     * 底层还是hashMap
     */
    public HashSet() {
        map = new HashMap<>();
    } 
    
    
    /**
     * 元素放在hashmap的key中，value为present（存的是dummy value）
     */
    public boolean add(E e) {
        return map.put(e, PRESENT)==null;
        }
    }
```