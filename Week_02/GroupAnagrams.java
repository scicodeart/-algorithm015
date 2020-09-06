import java.util.*;

/**
 * @author Lai
 * @version 1.0
 * @date 2020-09-06 23:16
 */
public class GroupAnagrams {

    //Given an array of strings strs, group the anagrams together. You can return th
//e answer in any order.
//
// An Anagram is a word or phrase formed by rearranging the letters of a differe
//nt word or phrase, typically using all the original letters exactly once.
//
//
// Example 1:
// Input: strs = ["eat","tea","tan","ate","nat","bat"]
//Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
// Example 2:
// Input: strs = [""]
//Output: [[""]]
// Example 3:
// Input: strs = ["a"]
//Output: [["a"]]
//
//
// Constraints:
//
//
// 1 <= strs.length <= 104
// 0 <= strs[i].length <= 100
// strs[i] consists of lower-case English letters.
//
// Related Topics Hash Table String


    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<String>> groupAnagrams(String[] strs) {
            if (strs.length == 0) return new ArrayList();
            //分类
            Map<String, List> ans = new HashMap<String, List>();

            //根据排序后字符串相同，添加同类list
            for (String s : strs) {
                char[] ca = s.toCharArray();
                Arrays.sort(ca);
                //key作为分类
                String key = String.valueOf(ca);
                //添加key
                if (!ans.containsKey(key)) {
                    ans.put(key, new ArrayList());
                }
                ans.get(key).add(s);
            }
            return new ArrayList(ans.values());
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
