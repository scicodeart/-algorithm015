import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Lai
 * @version 1.0
 * @date 2020-10-11 23:39
 */
public class WordSearchII {

    //给定一个二维网格 board 和一个字典中的单词列表 words，找出所有同时在二维网格和字典中出现的单词。
//
// 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。
//
//
// 示例:
//
// 输入:
//words = ["oath","pea","eat","rain"] and board =
//[
//  ['o','a','a','n'],
//  ['e','t','a','e'],
//  ['i','h','k','r'],
//  ['i','f','l','v']
//]
//
//输出: ["eat","oath"]
//
// 说明:
//你可以假设所有输入都由小写字母 a-z 组成。
//
// 提示:
//
//
// 你需要优化回溯算法以通过更大数据量的测试。你能否早点停止回溯？
// 如果当前单词不存在于所有单词的前缀中，则可以立即停止回溯。什么样的数据结构可以有效地执行这样的操作？散列表是否可行？为什么？ 前缀树如何？如果你想学习如何
//实现一个基本的前缀树，请先查看这个问题： 实现Trie（前缀树）。
//
// Related Topics 字典树 回溯算法


    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 把所有的words放入trie里面---- 构建prefix
         * @param board
         * @param words
         * @return
         */
        private Set<String> result = new HashSet<>();
        public List<String> findWords(char[][] board, String[] words) {


                Trie trie = new Trie();
                for (String word : words) {
                    trie.insert(word);
                }
                for (int i = 0; i < board.length; i++) {
                    for (int j = 0; j < board[0].length; j++) {
                        dfs(trie, board, new StringBuilder(), i, j, new int[board.length][board[0].length]);
                    }
                }
                return new ArrayList<>(result);
         }

            private void dfs(Trie parent, char[][] board, StringBuilder prefix, int i, int j, int[][] used) {
                if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || used[i][j] == 1) {
                    return;
                }
                Trie trie = parent.indexOf(board[i][j]);
                if (trie == null) {
                    return;
                }
                used[i][j] = 1;
                prefix.append(board[i][j]);
                if (trie.isEnd()) {
                    result.add(prefix.toString());
                }
                dfs(trie, board, prefix, i - 1, j, used);
                dfs(trie, board, prefix, i + 1, j, used);
                dfs(trie, board, prefix, i, j - 1, used);
                dfs(trie, board, prefix, i, j + 1, used);
                used[i][j] = 0;
                prefix.deleteCharAt(prefix.length() - 1);
            }


        }
    }

    class Trie {
        private Trie[] nexts;

        private boolean isEnd;

        public Trie() {
            nexts = new Trie[26];
            isEnd = false;
        }

        public void insert(String word) {
            Trie temp = this;
            for (int i = 0; i < word.length(); i++) {
                temp = temp.put(word.charAt(i));
            }
            temp.isEnd(true);
        }

        public Trie put(Character c) {
            if (nexts[c - 'a'] == null) {
                nexts[c - 'a'] = new Trie();
            }
            return nexts[c - 'a'];
        }

        public Trie indexOf(char c) {
            return nexts[c - 'a'];
        }

        public Trie indexOf(String prefix) {
            Trie temp = this;
            for (int i = 0; i < prefix.length(); i++) {
                if (temp == null) {
                    return null;
                }
                temp = temp.indexOf(prefix.charAt(i));
            }
            return temp;
        }

        public void isEnd(boolean isEnd) {
            this.isEnd = isEnd;
        }

        public boolean isEnd() {
            return this.isEnd;
        }
//leetcode submit region end(Prohibit modification and deletion)

}
