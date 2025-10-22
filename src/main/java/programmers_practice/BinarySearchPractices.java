package programmers_practice;

import java.util.*;

public interface BinarySearchPractices {

    // 입국심사
    class BinarySearch01 {
        public static void main(String[] args) {
            BinarySearch01 main = new BinarySearch01();
            int[] times = {7, 10}; // 심사관 시간
            int n = 6;             // 인원 수
            System.out.println(main.solution(n, times)); // 28
        }

        // 풀이 아이디어
        // 1. 시간의 범위를 설정:
        // - 최솟값 = 1분
        // - 최댓값 = 가장 느린 심사관 시간 × M명 (예: 최악의 경우 한 명이 전부 심사)
        // 2. mid 시간 동안 몇 명을 처리할 수 있는지 계산:
        // - sum(mid / 심사시간[i]) (각 심사관이 mid 분 동안 처리 가능한 사람 수)
        // 3. 처리 가능한 인원이 M명 이상이면 → 시간이 충분하다 → 더 줄여본다. (hi = mid)
        //    처리 가능한 인원이 M명 미만이면 → 시간이 부족하다 → 시간을 늘린다. (lo = mid + 1)
        public long solution(int n, int[] times) {
            long answer = 0;

            long lo = 1;
            long hi = (long) Arrays.stream(times).max().getAsInt() * n;    // 최악의 시간
            answer = hi;

            while (lo <= hi) {
                long mid = (lo + hi) / 2;
                long processed = 0;
                for (int t : times) {
                    processed += mid / t;
                    if (processed >= n) break;  // 이미 충분하면 스킵
                }
                if (processed >= n) {
                    answer = mid;   // 가능한 시간
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            }

            return answer;
        }
    }

    // 징검다리
    class BinarySearch02 {
        public static void main(String[] args) {
            BinarySearch02 main = new BinarySearch02();
            int distance = 25;
            int[] rocks = {2, 14, 11, 21, 17};
            int n = 2;
            System.out.println(main.solution(distance, rocks, n)); // 4
        }

        public int solution(int distance, int[] rocks, int n) {
            int answer = 0;

            Arrays.sort(rocks);

            int lo = 1, hi = distance;

            while (lo <= hi) {
//                int mid = lo + ((hi - lo) >>> 1); // 후보 '최소거리' <-- 덧셈 오버플로우 예방
                int mid = (lo + hi) / 2;
                if (ok(mid, distance, rocks, n)) {
                    // mid 거리 확보 가능 → 더 크게 갈 수 있는지 시도
                    answer = mid;
                    lo = mid + 1;
                } else {
                    // mid 불가능 → 줄여서 탐색
                    hi = mid - 1;
                }
            }
            return answer;
        }

        // 인접 최소거리 >= x 를 만들기 위해 제거해야 하는 돌 수가 n 이하인가?
        private boolean ok(int x, int distance, int[] rocks, int n) {
            int removed = 0;
            int prev = 0; // 시작점(0)

            for (int r : rocks) {
                if (r - prev < x) {
                    removed++;
                    if (removed > n) return false; // 조기 종료
                } else {
                    prev = r; // 돌을 남기고 기준점 갱신
                }
            }
            // 종점까지 마지막 간격 확인
            if (distance - prev < x) removed++;

            return removed <= n;
        }
    }
}
