package this_is_cote;

import java.util.*;

public interface DpDpProblems {
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

    // 33. 퇴사

    // 34. 병사 배치하기
    class DpProblems16_34 {
        public static void main(String[] args) {
            int[] power = {15, 11, 4, 8, 5, 2, 4};
            System.out.println("result: " + solution(power)); // 결과: 2
        }

        private static int solution(int[] power) {
            int n = power.length;

            // 병사의 전투력 배열을 뒤집음
            int[] reversedPower = new int[n];
            for (int i = 0; i < n; i++) {
                reversedPower[i] = power[n - i - 1];
            }

            // dp[i]: i번째 병사를 포함했을 때 최장 증가 부분 수열의 길이
            int[] dp = new int[n];
            for (int i = 0; i < n; i++) {
                dp[i] = 1; // 모든 병사에 대해 최소 LIS는 1
                for (int j = 0; j < i; j++) {
                    if (reversedPower[j] < reversedPower[i]) {
                        dp[i] = Math.max(dp[i], dp[j] + 1); // 이전 LIS 길이를 활용
                    }
                }
            }

            // LIS의 최대 길이 계산
            int lisLength = 0;
            for (int i = 0; i < n; i++) {
                lisLength = Math.max(lisLength, dp[i]);
            }

            // 최소 제외 병사 수 = 전체 병사 수 - LIS 길이
            return n - lisLength;
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

