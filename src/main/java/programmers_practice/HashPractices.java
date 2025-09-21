package programmers_practice;

import java.util.*;

public interface HashPractices {

    // 전화번호 목록
    class HashPractices01 {
        public static void main(String[] args) {
            HashPractices01 main = new HashPractices01();
            String[] phone_book1 = {"119", "97674223", "1195524421"};
            boolean result1 = main.solutionHash(phone_book1);
            System.out.println("result1 = " + result1); // false
            String[] phone_book2 = {"123","456","789"};
            boolean result2 = main.solution(phone_book2);
            System.out.println("result2 = " + result2); // true
            String[] phone_book3 = {"12","123","1235","567","88"};
            boolean result3 = main.solution(phone_book3);
            System.out.println("result3 = " + result3); // false
        }

        // 문제 핵심
        // 1. 무식하게 전체를 비교하면 효율성 테스트를 실패하게 된다. (ex 2중 for문)
        // 2. phone_book 을 정렬한다.
        // 3. 정렬후 인접한 것끼리 비교하면 된다.
        public boolean solution(String[] phone_book) {
            boolean answer = true;

            Arrays.sort(phone_book);
            for (int i = 0; i < phone_book.length - 1; i++) {
                if (phone_book[i + 1].startsWith(phone_book[i])) {
                    answer = false;
                    break;
                }
            }

            return answer;
        }

        public boolean solutionHash(String[] phone_book) {
            Set<String> set = new HashSet<>(Arrays.asList(phone_book));
            for (String num : phone_book) {
                for (int cut = 1; cut < num.length(); cut++) {
                    // System.out.println(num.substring(0, cut));
                    if (set.contains(num.substring(0, cut))) {
                        return false;
                    }
                }
            }
            return true;
        }
    }

    class HashPractices02 {
        public static void main(String[] args) {
            HashPractices02 main = new HashPractices02();
            String[][] clothes1 = {{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}};
            int result1 = main.solution(clothes1);
            System.out.println("result1 = " + result1);   // 5
            String[][] clothes2 = {{"crow_mask", "face"}, {"blue_sunglasses", "face"}, {"smoky_makeup", "face"}};
            int result2 = main.solution(clothes2);
            System.out.println("result2 = " + result2); // 3
        }

        // 1. 종류를 기준으로 HashMap<String, Integer>
        // 2. 종류별로 개수를 구할 때 안입는 경우도 있으므로 + 1 (주의1)
        // 3. 전체 경우의 수를 구하기
        // 4. 모두 안 입는 경우가 있으므로 전체 수에서 -1 (주의2)
        public int solution(String[][] clothes) {
            int answer = 0;

            Map<String, Integer> map = new HashMap<>();
            for (String[] clothe : clothes) {
                map.put(clothe[1], map.getOrDefault(clothe[1], 0) + 1);
            }
            answer = 1;
            // System.out.println("map = " + map);
            for (Integer value : map.values()) {
                answer *= value + 1;    // 종류별로 안입는 경우도 있으므로 + 1
            }
            answer -= 1;    // 모두 안 입는 경우 제외

            return answer;
        }
    }
}
