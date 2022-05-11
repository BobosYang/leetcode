package com.leetcode.y2022.m05;

import java.util.Arrays;

public class May09 {

   /**
         942. 增减字符串匹配
         由范围 [0,n] 内所有整数组成的 n + 1 个整数的排列序列可以表示为长度为 n 的字符串 s ，其中:
         
         如果 perm[i] < perm[i + 1] ，那么 s[i] == 'I' 
         如果 perm[i] > perm[i + 1] ，那么 s[i] == 'D' 
         给定一个字符串 s ，重构排列 perm 并返回它。如果有多个有效排列perm，则返回其中 任何一个 。
         
         示例 1：
         输入：s = "IDID"
         输出：[0,4,1,3,2]
         
         示例 2：
         输入：s = "III"
         输出：[0,1,2,3]
         
         示例 3：
         输入：s = "DDI"
         输出：[3,2,0,1]
         
         提示：
         1 <= s.length <= 10^5
         s 只包含字符 "I" 或 "D"
    */
   
   public int[] diStringMatch(String s) {
      int min = 0;
      int max = s.length();
      int[] result = new int[max + 1];
      int lessThanTimes = 0;
      int moreThanTimes = 0;
      for (int idx = 0; idx < s.length(); idx++) {
         if ('I' == s.charAt(idx)) {
            lessThanTimes++;
            System.out.printf("less ");
         } else if ('D' == s.charAt(idx)) {
            moreThanTimes++;
            System.out.printf("more ");
         }
      }
      System.out.printf("\n");
      System.out.printf("moreThanTimes=%d, lessThanTimes=%d\n", moreThanTimes, lessThanTimes);

      if ('D' == s.charAt(0)) {
         if (lessThanTimes > 0) {
            result[0] = max - lessThanTimes;
         } else {
            result[0] = max;
         }

         moreThanTimes--;
         if (moreThanTimes > 0) {
            result[1] = min + moreThanTimes;
         } else {
            result[1] = min;
         }
      } else if ('I' == s.charAt(0)) {
         if (moreThanTimes > 0) {
            result[0] = min + moreThanTimes;
         } else {
            result[0] = min;
         }

         lessThanTimes--;
         if (lessThanTimes > 0) {
            result[1] = max - lessThanTimes;
         } else {
            result[1] = max;
         }
      }
      System.out.printf("result[0]=%d, result[1]=%d\n", result[0], result[1]);
      for (int idx = 1; idx < s.length(); idx++) {
         if ('D' == s.charAt(idx)) {
            moreThanTimes--;
            if (moreThanTimes > 0) {
               result[idx + 1] = min + moreThanTimes;
            } else {
               result[idx + 1] = min;
            }
         } else if ('I' == s.charAt(idx)) {
            lessThanTimes--;
            if (lessThanTimes > 0) {
               result[idx + 1] = max - lessThanTimes;
            } else {
               result[idx + 1] = max;
            }
         }
      }
      return result;
   }

    /**
     * 双循环
     * @param s
     * @return
     */
   public int[] diStringMatch1(String s) {
      int len = s.length();
      int countleft = 0, countright = len;
      int[] res = new int[len + 1];
      for (int i = 0; i < len; i++) {
         if (s.charAt(i) == 'I') {
            res[i] = countleft++;
         }
      }
      for (int i = 0; i < len; i++) {
         if (s.charAt(i) == 'D') {
            res[i] = countright--;
         }
      }
      res[len] = countright;
      return res;
   }
    
    /**
     * 单循环
     * @param s
     * @return
     */
   public int[] diStringMatch2(String s) {
      int len = s.length();
      int countleft = 0, countright = len;
      int[] res = new int[len + 1];
      for (int i = 0; i < len; i++) {
         if (s.charAt(i) == 'I') {
            res[i] = countleft;
            countleft++;
         } else if (s.charAt(i) == 'D') {
            res[i] = countright;
            countright--;
         }
      }
      res[len] = countleft;
      return res;
   }
   
   public static void main(String[] args) {
      May09 may10 = new May09();

      String s = "IDID";
      System.out.printf("diStringMatch(\"%s\")=%s\n-------------------------------------------------\n", s, Arrays.toString(may10.diStringMatch(s)));

      s = "III";
      System.out.printf("diStringMatch(\"%s\")=%s\n-------------------------------------------------\n", s, Arrays.toString(may10.diStringMatch(s)));

      s = "DDI";
      System.out.printf("diStringMatch(\"%s\")=%s\n-------------------------------------------------\n", s, Arrays.toString(may10.diStringMatch(s)));
   }

}
