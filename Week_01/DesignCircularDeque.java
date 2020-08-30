public class DesignCircularDeque {
    //Design your implementation of the circular double-ended queue (deque).
//
// Your implementation should support following operations:
//
//
// MyCircularDeque(k): Constructor, set the size of the deque to be k.
// insertFront(): Adds an item at the front of Deque. Return true if the operati
//on is successful.
// insertLast(): Adds an item at the rear of Deque. Return true if the operation
// is successful.
// deleteFront(): Deletes an item from the front of Deque. Return true if the op
//eration is successful.
// deleteLast(): Deletes an item from the rear of Deque. Return true if the oper
//ation is successful.
// getFront(): Gets the front item from the Deque. If the deque is empty, return
// -1.
// getRear(): Gets the last item from Deque. If the deque is empty, return -1.
// isEmpty(): Checks whether Deque is empty or not.
// isFull(): Checks whether Deque is full or not.
//
//
//
//
// Example:
//
//
//MyCircularDeque circularDeque = new MycircularDeque(3); // set the size to be
//3
//circularDeque.insertLast(1);			// return true
//circularDeque.insertLast(2);			// return true
//circularDeque.insertFront(3);			// return true
//circularDeque.insertFront(4);			// return false, the queue is full
//circularDeque.getRear();  			// return 2
//circularDeque.isFull();				// return true
//circularDeque.deleteLast();			// return true
//circularDeque.insertFront(4);			// return true
//circularDeque.getFront();			// return 4
//
//
//
//
// Note:
//
//
// All values will be in the range of [0, 1000].
// The number of operations will be in the range of [1, 1000].
// Please do not use the built-in Deque library.
//
// Related Topics Design Queue


        //leetcode submit region begin(Prohibit modification and deletion)
        class MyCircularDeque {

            class Node {
                int val;
                Node prev, next;

                Node(int val) {
                    this.val = val;
                    this.prev = null;
                    this.next = null;
             }}

            int count;
            int capacity;
            Node head, tail;


            /**
             * Initialize your data structure here. Set the size of the deque to be k.
             */
            public MyCircularDeque(int k) {
                this.count = 0;
                this.capacity = k;
            }

            /**
             * Adds an item at the front of Deque. Return true if the operation is successful.
             */
            public boolean insertFront(int value) {
                if (this.count == this.capacity) {
                    return false;
                }
                Node newNode = new Node(value);
                if (this.count == 0) {
                    this.head = this.tail = newNode;
                } else {
                    this.head.prev = newNode;
                    newNode.next = this.head;
                    this.head = this.head.prev;
                }
                this.count++;
                return true;
            }

            /**
             * Adds an item at the rear of Deque. Return true if the operation is successful.
             */
            public boolean insertLast(int value) {
                if (this.count == this.capacity) {
                    return false;
                }
                Node newNode = new Node(value);
                if (this.count == 0) {
                    this.head = this.tail = newNode;
                } else {
                    this.tail.next = newNode;
                    newNode.prev = this.tail;
                    this.tail = this.tail.next;
                }
                this.count++;
                return true;
            }

            /**
             * Deletes an item from the front of Deque. Return true if the operation is successful.
             */
            public boolean deleteFront() {
                if (this.count == 0) {
                    return false;
                }
                if (this.count == 1) {
                    this.head = this.tail = null;
                } else {
                    Node newHead = this.head.next;
                    this.head.next = null;
                    this.head = newHead;
                    this.head.prev = null;
                }
                this.count--;
                return true;
            }

            /**
             * Deletes an item from the rear of Deque. Return true if the operation is successful.
             */
            public boolean deleteLast() {
                if (this.count == 0) {
                    return false;
                }
                if (this.count == 1) {
                    this.head = this.tail = null;
                } else {
                    Node newTail = this.tail.prev;
                    this.tail.prev = null;
                    this.tail = newTail;
                    this.tail.next = null;
                }
                this.count--;
                return true;
            }

            /**
             * Get the front item from the deque.
             */
            public int getFront() {
                return this.count > 0 ? this.head.val : -1;
            }

            /**
             * Get the last item from the deque.
             */
            public int getRear() {
                return this.count > 0 ? this.tail.val : -1;
            }

            /**
             * Checks whether the circular deque is empty or not.
             */
            public boolean isEmpty() {
                return this.count == 0;
            }

            /**
             * Checks whether the circular deque is full or not.
             */
            public boolean isFull() {
                return this.count == 0;
            }
        }

/**
 * Your MyCircularDeque object will be instantiated and called as such:
 * MyCircularDeque obj = new MyCircularDeque(k);
 * boolean param_1 = obj.insertFront(value);
 * boolean param_2 = obj.insertLast(value);
 * boolean param_3 = obj.deleteFront();
 * boolean param_4 = obj.deleteLast();
 * int param_5 = obj.getFront();
 * int param_6 = obj.getRear();
 * boolean param_7 = obj.isEmpty();
 * boolean param_8 = obj.isFull();
 */
//leetcode submit region end(Prohibit modification and deletion)

}
