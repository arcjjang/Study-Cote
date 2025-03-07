package cote_succ;

import java.util.HashMap;
import java.util.HashSet;

public interface HashQuestions {
    class HashQuestionMain_08_02 {
        public static void main(String[] args) {
            // HashMap<KeyType, ValueType>입니다.
            HashMap<String, Integer> hashMap = new HashMap<>();

            // hashMap에 데이터 추가
            hashMap.put("ABC", 10);
            hashMap.put("BBB", 20);
            hashMap.put("AAA", 30);
            hashMap.put("ABC", 15);

            System.out.println(hashMap.isEmpty());  // false
            System.out.println(hashMap.get("ABC")); // 15
            System.out.println(hashMap.containsKey("ABC")); // true

            hashMap.remove("ABC"); // hashMap에서 키가 "ABC"인 데이터 제거
            System.out.println(hashMap.size()); // 2

            hashMap.clear();    // 해시맵의 모든 데이터 삭제
            System.out.println(hashMap.isEmpty());  // true
        }
    }

    class HashQuestionMain_08_04_01 {
        public static void main(String[] args) {
            int[] arr = {1, 2, 3, 4, 8};
            int target = 6;
            boolean result = solution(arr, target);
            System.out.println("result = " + result);
            int[] arr2 = {2, 3, 5, 9};
            int target2 = 10;
            boolean result2 = solution(arr2, target2);
            System.out.println("result2 = " + result2);
        }

        private static boolean mySolution(int[] arr, int target) {
            // arr size만큼 for문
            // target - arr[i] 의 값을 set에서 찾음
            // 없으면 set에 add
            // 있으면 return true
            HashSet<Integer> set = new HashSet<>();
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] >= target) {
                    continue;
                }
                int reqNum = target - arr[i];
                if (set.contains(reqNum)) {
                    return true;
                }
                {
                    set.add(arr[i]);
                }
            }
            return false;
        }

        private static boolean solution(int[] arr, int target) {
            HashSet<Integer> hashSet = new HashSet<>();

            for (int i : arr) {
                // 1. target에서 현재 원소를 뺀 값이 해시셋 있는지 확인
                if (hashSet.contains(target - i)) {
                    return true;
                }
                // 2. 해시셋 현재 값 저장
                hashSet.add(i);
            }

            return false;
        }
    }

    class HashQuestionMain_08_04_02 {
        public static void main(String[] args) {
            String[] participant = {"leo", "kiki", "eden"};
            String[] completion = {"kiki", "eden"};
            String result = solution(participant, completion);
            System.out.println("result = " + result);
            String[] participant2 = {"mislav", "stanko", "mislav", "ana"};
            String[] completion2 = {"stanko", "ana", "mislav"};
            String result2 = solution(participant2, completion2);
            System.out.println("result2 = " + result2);
        }

        private static String mySolution(String[] participant, String[] completion) {
            HashSet<String> hashSet = new HashSet<>();
            for (int i = 0; i < participant.length; i++) {
                hashSet.add(participant[i]);
            }
            for (int i = 0; i < completion.length; i++) {
                hashSet.remove(completion[i]);
            }
            return hashSet.iterator().next();
        }

        private static String solution(String[] participant, String[] completion) {
            // 1. 해시맵 생성
            HashMap<String, Integer> hashMap = new HashMap<>();
            // 2. 완주한 선수들의 이름을 해시맵에 저장
            for (String s : completion) {
                hashMap.put(s, hashMap.getOrDefault(s, 0) + 1);
            }
            // 3. 참가한 선수들의 이름을 키로 하는 값을 1씩 감소
            for (String s : participant) {
                // 4. 완주하지 못한 선수를 찾으면 반환
                if (hashMap.getOrDefault(s, 0) == 0) {
                    return s;
                }
                hashMap.put(s, hashMap.get(s) - 1);
            }
            return null;
        }

    }

    class HashQuestionMain_08_04_03 {
        public static void main(String[] args) {
            String[] want = {"banana", "apple", "rice", "pork", "pot"};
            int[] number = {3, 2, 2, 2, 1};
            String[] discount = {"chicken", "apple", "apple", "banana", "rice", "apple", "pork", "banana", "pork", "rice", "pot", "banana", "apple", "banana"};
            int result = mySolution(want, number, discount);
            System.out.println("result = " + result);   // 3
            String[] want2 = {"apple"};
            int[] number2 = {10};
            String[] discount2 = {"banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana"};
            int result2 = mySolution(want2, number2, discount2);
            System.out.println("result2 = " + result2);   // 0
        }

        private static int mySolution(String[] want, int[] number, String[] discount) {
            HashMap<String, Integer> wantMap = new HashMap<>();
            for (int i = 0; i < want.length; i++) {
                wantMap.put(want[i], number[i]);
            }

            HashMap<String, Integer> discountMap = new HashMap<>();
            for (int i = 0; i < 10; i++) {
                discountMap.put(discount[i], discountMap.getOrDefault(discount[i], 0) + 1);
            }
            int count = 0;
            for (int i = 10; i < discount.length; i++) {
                System.out.println("wantMap = " + wantMap);
                System.out.println("discountMap = " + discountMap);
                count = isSameMap(wantMap, discountMap) ? count + 1 : count;
                discountMap.put(discount[i], discountMap.getOrDefault(discount[i], 0) + 1);
                discountMap.put(discount[i - 10], discountMap.getOrDefault(discount[i - 10], 0) - 1);
            }
            System.out.println("wantMap = " + wantMap);
            System.out.println("discountMap = " + discountMap);
            count = isSameMap(wantMap, discountMap) ? count + 1 : count;    // 마지막에 한 번 더 체크
            return count;
        }

        private static boolean isSameMap(HashMap<String, Integer> wantMap, HashMap<String, Integer> discountMap) {
            for (String key : wantMap.keySet()) {
                if (!discountMap.containsKey(key) || discountMap.get(key) < wantMap.get(key)) {
                    return false;
                }
            }
            return true;
        }
    }


}
