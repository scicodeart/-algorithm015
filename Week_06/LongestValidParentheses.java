import java.util.Deque;
import java.util.LinkedList;

/**
 * @author Lai
 * @version 1.0
 * @date 2020-10-11 23:54
 */
public class LongestValidParentheses {

    class Solution {
        public int countSubstrings(String s) {
            // 用栈解决括号的合法性问题，向栈中存入下标
            Deque<Integer> stack = new LinkedList<Integer>();
            // 向栈中预置一个-1，将计算长度的方式转化成“）”的下标减去出栈后栈顶元素的下标
            stack.push(-1);
            int len = 0;
            for (int i = 0; i < s.length(); i++) {
                if ('('==s.charAt(i)) {
                    stack.push(i);
                }
                if (')'==s.charAt(i)) {
                    stack.pop();
                    // 如栈空，则注入新的i作为预置下标
                    if (stack.isEmpty()) {
                        stack.push(i);
                    }
                    len = Math.max(len, i-stack.peek());

                }
            }
            return len;
        }
    }
}
