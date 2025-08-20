package this_is_cote;

import java.util.Arrays;

public interface DpProblems {
    // 31. 금광
    class DpProblems16_31 {
        public static void main(String[] args) {
            int n = 3, m = 4;
            int[][] gold = {
                    {1, 3, 3, 2},
                    {2, 1, 4, 1},
                    {0, 6, 4, 7}
            };
            System.out.println("result : " + solution(n, m, gold)); // 결과: 19
        }

        private static int solution(int n, int m, int[][] gold) {
            int[][] dp = new int[n][m];

            // 마지막 열에서 초기값 설정
            for (int i = 0; i < n; i++) {
                dp[i][m - 1] = gold[i][m - 1];
            }

            // 금광의 오른쪽부터 왼쪽으로 계산 -> 미래의 정보를 기반으로 현재 계산하기 위해서 -> 오른쪽 열(더 미래의 값)에는 정보가 없으니 최적 경로를 결정할 수 없습니다.
            for (int j = m - 2; j >= 0; j--) {
                for (int i = 0; i < n; i++) {
                    // 오른쪽 위, 오른쪽, 오른쪽 아래 값 계산
                    int rightUp = (i == 0) ? 0 : dp[i - 1][j + 1];
                    int right = dp[i][j + 1];
                    int rightDown = (i == n - 1) ? 0 : dp[i + 1][j + 1];

                    // 최대 금 계산
                    dp[i][j] = gold[i][j] + Math.max(rightUp, Math.max(right, rightDown));
                }
            }

            // 첫 번째 열에서 최대값 찾기
            int maxGold = 0;
            for (int i = 0; i < n; i++) {
                maxGold = Math.max(maxGold, dp[i][0]);
            }

            return maxGold;
        }
    }

    // 32. 정수 삼각형
    class DpProblems16_32 {
        public static void main(String[] args) {
            int[][] triangle = {
                    {7},
                    {3, 8},
                    {8, 1, 0},
                    {2, 7, 4, 4},
                    {4, 5, 2, 6, 5}
            };
            System.out.println("최댓값 합: " + solution(triangle)); // 결과: 30
        }

        private static int solution(int[][] triangle) {
            int n = triangle.length;

            // dp 배열 초기화 (마지막 줄 복사)
            int[][] dp = new int[n][n];
            for (int i = 0; i < n; i++) {
                dp[n - 1][i] = triangle[n - 1][i];
            }

            // Bottom-up 방식으로 계산
            for (int i = n - 2; i >= 0; i--) {
                for (int j = 0; j <= i; j++) {
                    dp[i][j] = triangle[i][j] + Math.max(dp[i + 1][j], dp[i + 1][j + 1]);
                }
            }

            // 꼭대기의 최대값 반환
            return dp[0][0];
        }
    }

    // 33.퇴사
    class DpProblemsMain16_33 {
        public static void main(String[] args) {
            int N = 7;
            int[] T = {0, 3, 5, 1, 1, 2, 4, 2};
            int[] P = {0, 10, 20, 10, 20, 15, 40, 200};
            int result = solution2(N, T, P);
            System.out.println("result = " + result);   // 45
        }

        // 앞에서 풀기
        public static int solution2(int N, int[] T, int[] P) {
            int[] dp = new int[N + 2]; // N+1일까지 필요

            for (int i = 1; i <= N; i++) {
                // 오늘 일을 안 하고 넘어가는 경우
                dp[i + 1] = Math.max(dp[i + 1], dp[i]);

                // 오늘 일을 하는 경우 (끝나는 날 수익 반영)
                int endDay = i + T[i];
                if (endDay <= N + 1) {
                    dp[endDay] = Math.max(dp[endDay], dp[i] + P[i]);
                }
                System.out.println("i = " + i + ", endDay = " + endDay);
                System.out.println("dp : " + Arrays.toString(dp));
            }

            return dp[N + 1]; // 퇴사일(N+1일)에 얻을 수 있는 최대 수익
        }

        // 뒤에서 풀기
        public static int solution(int N, int[] T, int[] P) {
            int[] dp = new int[N + 2];
            for (int i = N; i >= 1; i--) {
                int nextDay = i + T[i];
                // 상담 가능한 경우
                if (nextDay <= N + 1) {
                    dp[i] = Math.max(P[i] + dp[nextDay], dp[i + 1]);    // max((지금 상담하고 보상 + 이후 최댓값), (지금 스킵하고 다음날 최댓값))
                } else {
                    // 상담 불가능한 경우
                    dp[i] = dp[i + 1];  // 다음 날부터 최대로 벌 수 있는 금액
                }
                System.out.println("i = " + i + ", nextDay = " + nextDay);
                System.out.println("dp : " + Arrays.toString(dp));
            }
            return dp[1];
        }
    }

    // 35. 못생긴 수
    class DpProblems16_35 {
        public static void main(String[] args) {
            int n = 10;
            System.out.println(n + "번째 못생긴 수: " + solution(n)); // 결과: 12
        }

        public static int solution(int n) {
            // 못생긴 수를 저장할 배열
            int[] ugly = new int[n];
            ugly[0] = 1; // 첫 번째 못생긴 수는 1

            // 포인터 초기화
            int p2 = 0, p3 = 0, p5 = 0;

            // 못생긴 수를 계산하고 저장
            for (int i = 1; i < n; i++) {
                // 다음 후보 못생긴 수 계산
                int next2 = ugly[p2] * 2;
                int next3 = ugly[p3] * 3;
                int next5 = ugly[p5] * 5;

                // 최솟값을 선택하여 못생긴 수 배열에 추가
                int nextUgly = Math.min(next2, Math.min(next3, next5));
                ugly[i] = nextUgly;

                // 최솟값이 계산된 요소에 따라 포인터 증가
                if (nextUgly == next2) p2++;
                if (nextUgly == next3) p3++;
                if (nextUgly == next5) p5++;
            }

            // n번째 못생긴 수 반환
            return ugly[n-1];
        }
    }

    // 36. 편집 거리
    class DpProblems16_36 {
        public static void main(String[] args) {
            String A = "sunday";
            String B = "saturday";
            System.out.println("최소 편집 거리: " + solution(A, B)); // 결과: 3
        }

        public static int solution(String A, String B) {
            int m = A.length();
            int n = B.length();

            // DP 테이블 초기화
            int[][] dp = new int[m + 1][n + 1];

            // Base Case (첫 번째 행과 첫 번째 열 초기화)
            for (int i = 0; i <= m; i++) {
                dp[i][0] = i; // A를 비우려면 문자 삭제 연산 필요
            }
            for (int j = 0; j <= n; j++) {
                dp[0][j] = j; // B를 만들려면 문자 삽입 연산 필요
            }

            // DP 테이블 채우기
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    if (A.charAt(i - 1) == B.charAt(j - 1)) {
                        // 문자가 같으면 교체 불필요
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {
                        // 삽입, 삭제, 교체 중 최소 연산 선택
                        dp[i][j] = Math.min(
                                dp[i - 1][j] + 1,    // 삭제
                                Math.min(
                                        dp[i][j - 1] + 1,    // 삽입
                                        dp[i - 1][j - 1] + 1 // 교체
                                )
                        );
                    }
                }
            }

            // 최종 편집 거리 반환
            return dp[m][n];
        }
    }
}
