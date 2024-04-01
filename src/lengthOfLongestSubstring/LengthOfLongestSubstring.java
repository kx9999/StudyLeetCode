package lengthOfLongestSubstring;

import java.util.HashSet;
import java.util.Set;

import java.util.Random;

class SolutionTest {
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ 1234567890!@#$%^&*()-=";
    private static final int STRING_LENGTH = 10;

    public static void main(String[] args) {
        Solution solution = new Solution();

        // 生成1000个随机字符串并记录耗时
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 1; i++) {
            String randomString = generateRandomString(STRING_LENGTH);
            int result = solution.lengthOfLongestSubstring_1(randomString);
            System.out.println("Test Case " + (i + 1) + ", " + randomString + ": "+ result);
        }

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time: " + totalTime + " milliseconds");
    }

    public static String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            sb.append(randomChar);
        }

        return sb.toString();
    }
}



class Solution {
    public int lengthOfLongestSubstring_1(String s) {
        int n = s.length();
        int maxLength = 0;
        int start = 0;
        int end = 0;
        int[] index = new int[128];  // 哈希表，用于存储字符和其索引

        for (end = 0; end < n; end++) {
            char c = s.charAt(end);
            start = Math.max(start, index[c]);  // 更新起始位置为重复字符的下一个位置
            maxLength = Math.max(maxLength, end - start + 1);
            index[c] = end + 1;  // 存储字符的索引
        }

        return maxLength;
    }

        public int lengthOfLongestSubstring_2(String s) {
            int n = s.length();
            int maxLength = 0;
            int start = 0;
            int end = 0;
            Set<Character> set = new HashSet<>();

            while (end < n) {
                if (!set.contains(s.charAt(end))) {
                    set.add(s.charAt(end));
                    maxLength = Math.max(maxLength, end - start + 1);
                    end++;
                } else {
                    set.remove(s.charAt(start));
                    start++;
                }
            }

            return maxLength;
        }

}
