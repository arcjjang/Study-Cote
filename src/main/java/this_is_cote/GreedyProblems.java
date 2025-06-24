package this_is_cote;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.ConcurrentNavigableMap;

public interface GreedyProblems {
    // 큰 수의 법칙
    class GreedyProblemMain02_02 {
        public static void main(String[] args) {
            int m = 8, k = 3;
            int[] nums = new int[]{2, 4, 5, 4, 6};
            int result = solution3(m, k, nums);
            System.out.println("result = " + result);   // 46
        }

        public static int solution(int m, int k, int[] nums) {
            int[] sortedNums = Arrays.stream(nums).boxed().sorted((a, b) -> b - a).mapToInt(Integer::intValue).toArray();
//            Integer[] boxedNums = Arrays.stream(nums).boxed().toArray(Integer[]::new);
//            Arrays.sort(boxedNums, Collections.reverseOrder());
//            System.out.println(Arrays.toString(boxedNums));
            int count = 0, result = 0;
            for (int i = 0; i < m; i++) {
                if (count == k) {
                    count = 0;
                    result += sortedNums[1];
                } else {
                    result += sortedNums[0];
                    count++;
                }
            }
            return result;
        }

        public static int solution2(int m, int k, int[] nums) {
            Arrays.sort(nums);
            int count = 0, result = 0;
            for (int i = 0; i < m; i++) {
                if (count == k) {
                    count = 0;
                    result += nums[nums.length - 2];
                } else {
                    result += nums[nums.length - 1];
                    count++;
                }
            }
            return result;
        }

        public static int solution3(int m, int k, int[] nums) {
            Arrays.sort(nums);
            int first = nums[nums.length - 1];
            int second = nums[nums.length - 2];
            int count = m / (k + 1);
            count *= k;
            count += m % (k + 1);
            System.out.println("count = " + count);
            return count * first + (m - count) * second;
        }
    }

    // 숫자 카드 게임
    class GreedyProblemMain02_03 {
        public static void main(String[] args) {
            int[][] nums = new int[][]{
                    {3, 1, 2},
                    {4, 1, 4},
                    {2, 2, 2}
            };
            int result = solution(nums);
            System.out.println("result = " + result);   // 2

            int[][] nums2 = new int[][]{
                    {7, 3, 1, 8},
                    {3, 3, 3, 4}
            };
            int result2 = solution(nums2);
            System.out.println("result2 = " + result2);  // 3
        }

        public static int solution(int[][] nums) {
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < nums.length; i++) {
                Arrays.sort(nums[i]);
                max = Math.max(max, nums[i][0]);
            }
            return max;
        }
    }

    // 1이 될 때까지
    class GreedyProblemMain02_04 {
        public static void main(String[] args) {
            int N = 17;
            int K = 4;
            int result = solution(N, K);
            System.out.println("result = " + result);   // 2

            K = 3;
            result = solution2(N, K);
            System.out.println("result = " + result);   // 6
        }

        public static int solution(int N, int K) {
            int result = 0;
            while (N > 1) {
                if (N % K == 0) {
                    N /= K;
                } else {
                    N--;
                }
                result++;
            }
            return result;
        }

        public static int solution2(int N, int K) {
            int result = 0;


            while (true) {
                int target = (N / K) * K;
                System.out.println("target = " + target);
                result += (N - target);
                N = target;
                System.out.println("N = " + N);
                if (N < K) {
                    break;
                }
                result += 1;
                N /= K;
            }

            // 마지막으로 남은 수에 대하여 1씩 빼기
            result += (N - 1);
            return result;
        }

    }

    class GreedyProblemMain11_05 {
        public static void main(String[] args) {
            int[] foodTimes = {3, 1, 2};
            int k = 5;
            int result = solution(foodTimes, k);
            System.out.println("result = " + result);   // 1
        }

        public static int solution(int[] food_times, long k) {
            int index = 0, curr = 0;
            int count = 0;
            while (true) {
                if (count == k + 1) {
                    System.out.println("curr = " + curr);
                    break;
                }

                System.out.println("index = " + index);
                if (food_times[index] > 0) {
                    food_times[index]--;
                    count++;
                    curr = index;
                    System.out.println(Arrays.toString(food_times));
                }
                if (index == food_times.length - 1) {
                    index = 0;
                } else {
                    index++;
                }
            }

            return curr + 1;
        }
    }

}
