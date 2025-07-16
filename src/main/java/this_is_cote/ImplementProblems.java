package this_is_cote;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

// CHAPTER 04/12 구현
public interface ImplementProblems {

    class ImplementProblemMain04_01 {

    }

    // 럭키 스트레이드
    class ImplementProblemMain012_07 {
        public static void main(String[] args) {
            int N = 123402;
            String result = solution(N);
            System.out.println("result = " + result);
            int N2 = 7755;
            String result2 = solution(N2);
            System.out.println("result2 = " + result2);
        }

        public static String solution(int N) {
            String str = String.valueOf(N);
            int len = str.length();
            int leftSum = 0;
            int rightSum = 0;

            for (int i = 0; i < len / 2; i++) {
                leftSum += str.charAt(i) - '0';
                rightSum += str.charAt(len - 1 - i) - '0';
            }

            return leftSum == rightSum ? "LUCKY" : "READY";
        }
    }

    // 문자열 재정렬
    class ImplementProblemMain012_08 {
        public static void main(String[] args) {
            String S1 = "K1KA5CB7";
            String result1 = solution(S1);
            System.out.println("result1 = " + result1);
            String S2 = "AJKDLSI412K4JSJ9D";
            String result2 = solution(S2);
            System.out.println("result2 = " + result2);
        }

        public static String solution(String S) {
            StringBuilder letters = new StringBuilder();
            int sum = 0;

            for (char c : S.toCharArray()) {
                if (Character.isLetter(c)) {
                    letters.append(c);
                } else if (Character.isDigit(c)) {
                    sum += c - '0';
                }
            }

            char[] sortedLetters = letters.toString().toCharArray();
            Arrays.sort(sortedLetters);

            return new String(sortedLetters) + (sum > 0 ? sum : "");
        }
    }

    // 2020 카카오 신입 공채 - 문자열 압축
    class ImplementProblemMain012_09 {
        public static void main(String[] args) {
            String s1 = "aabbaccc";
            int result1 = solution(s1);
            System.out.println("result1 = " + result1); // 7
        }

        public static int solution(String S) {
            int minLength = S.length();

            for (int i = 1; i <= S.length() / 2; i++) {
                StringBuilder compressed = new StringBuilder();
                String prev = S.substring(0, i);
                int count = 1;

                for (int j = i; j < S.length(); j += i) {
                    String current;
                    if (j + i > S.length()) {
                        current = S.substring(j);
                    } else {
                        current = S.substring(j, j + i);
                    }

                    if (current.equals(prev)) {
                        count++;
                    } else {
                        compressed.append(count > 1 ? count : "").append(prev);
                        prev = current;
                        count = 1;
                    }
                }

                compressed.append(count > 1 ? count : "").append(prev);
                minLength = Math.min(minLength, compressed.length());
            }

            return minLength;
        }
    }

    // 2020 카카오 신입 공채 - 자물쇠와 열쇠
    class ImplementProblemMain012_10 {
        public static void main(String[] args) {
            int[][] kye = {
                    {0, 0, 0},
                    {1, 0, 0},
                    {0, 1, 1}
            };
            int[][] lock = {
                    {1, 1, 1},
                    {1, 1, 0},
                    {1, 0, 1}
            };
            boolean result = solution(kye, lock);
            System.out.println("result = " + result); // true
        }

        public static boolean solution(int[][] key, int[][] lock) {
            int N = lock.length;
            int M = key.length;

            // 자물쇠의 크기를 3배로 확장
            int[][] extendedLock = new int[N * 3][N * 3];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    extendedLock[i + N][j + N] = lock[i][j];
                }
            }

            // 키를 회전시키면서 자물쇠에 맞춰보기
            for (int rotation = 0; rotation < 4; rotation++) {
                key = rotateKey(key);
                if (canUnlock(extendedLock, key, N, M)) {
                    return true;
                }
            }

            return false;
        }

        private static int[][] rotateKey(int[][] key) {
            int N = key.length;
            int[][] rotatedKey = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    rotatedKey[j][N - 1 - i] = key[i][j];
                }
            }
            return rotatedKey;
        }

        private static boolean canUnlock(int[][] lock, int[][] key, int N, int M) {
            for (int i = 0; i <= lock.length - M; i++) {
                for (int j = 0; j <= lock.length - M; j++) {
                    if (tryToUnlock(lock, key, i, j, N, M)) {
                        return true;
                    }
                }
            }
            return false;
        }

        private static boolean tryToUnlock(int[][] lock, int[][] key, int startX, int startY, int N, int M) {
            // 자물쇠의 상태를 복사
            int[][] tempLock = new int[lock.length][lock.length];
            for (int i = 0; i < lock.length; i++) {
                System.arraycopy(lock[i], 0, tempLock[i], 0, lock.length);
            }

            // 키를 자물쇠에 맞춰보기
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < M; j++) {
                    tempLock[startX + i][startY + j] += key[i][j];
                }
            }

            // 자물쇠가 모두 1인지 확인
            for (int i = N; i < N * 2; i++) {
                for (int j = N; j < N * 2; j++) {
                    if (tempLock[i][j] != 1) {
                        return false;
                    }
                }
            }

            return true;
        }
    }

    // 삼성전자 SW 역량테스트 - 뱀
    class ImplementProblemMain012_11 {
        public static void main(String[] args) {
            int N = 6; // 보드 크기
            int[][] apples = {
                    {3, 4}, {2, 5}, {5, 3}
            }; // 사과 위치
            String[] directions = {
                    "3,D", "15,L", "17,D"
            }; // 방향 전환 정보
            int result = solution(N, apples, directions);
            System.out.println("result = " + result); // 9

            int N2 = 10;
            int[][] apples2 = {
                    {1, 2}, {1, 3}, {1, 4}, {1, 5}
            };
            String[] directions2 = {
                    "8,D", "10,D", "11,D", "13,L"
            };
            int result2 = solution(N2, apples2, directions2);
            System.out.println("result2 = " + result2); // 21

            int N3 = 10;
            int[][] apples3 = {
                    {1, 5}, {1, 3}, {1, 2}, {1, 6}, {1, 7}
            };
            String[] directions3 = {
                    "8,D", "10,D", "11,D", "13,L"
            }; // 방향 전환 정보
            int result3 = solution(N3, apples3, directions3);
            System.out.println("result3 = " + result3); // 13
        }

        // 방향 : 상 -> 우 -> 하 -> 좌 (시계 방향)
        private static int[] dx = {-1, 0, 1, 0};
        private static int[] dy = {0, 1, 0, -1};

        public static int solution(int N, int[][] apples, String[] directions) {
            // 보드 만들기
            int[][] board = new int[N][N];
            // 사과 표
            for (int[] apple : apples) {
                board[apple[0] - 1][apple[1] - 1] = 1;  // 배열 인덱스 조절을 위해 -1
            }
            // 방향 정보
            Map<Integer, String> directionChangeMap = new HashMap<>();
            for (String direction : directions) {
                int time = Integer.parseInt(direction.split(",")[0]);
                String dir = direction.split(",")[1];
                directionChangeMap.put(time, dir);
            }

            // 뱀 초기화
            Deque<int[]> snake = new ArrayDeque<>();
            snake.offer(new int[]{0, 0});   // 뱀 초기 위치는 (0,0)
            board[0][0] = -1;   // 뱀이 있는 곳은 -1로 표시

            int time = 0;   // 게임 시간
            int direction = 1;  // 초기 방향은 오른쪽

            while (true) {
                time++;

                // 뱀의 다음 머리 위치 계산
                int[] head = snake.peekFirst();
                int nx = head[0] + dx[direction];
                int ny = head[1] + dy[direction];

                // 벽이나 자기 몸에 부딪히면 게임오버
                if (nx < 0 || ny < 0 || nx >= N || ny >= N || board[nx][ny] == -1) {
                    break;
                }

                // 이동이 가능한 경우
                if (board[nx][ny] == 1) {   // 사과가 있다면 머리만 추가
                    board[nx][ny] = -1;
                    snake.addFirst(new int[]{nx, ny});
                } else {    // 사과가 없다면 머리 추가 & 꼬리 제거
                    board[nx][ny] = -1;
                    snake.addFirst(new int[]{nx, ny});
                    int[] tail = snake.pollLast();
                    board[tail[0]][tail[1]] = 0;
                }

                // 시간에 따른 방향 전환 처리
                if (directionChangeMap.containsKey(time)) {
                    String dir = directionChangeMap.get(time);
                    if ("L".equals(dir)) {
                        direction = (direction + 3) % 4;    // 왼쪽으로 회전
                    } else if ("D".equals(dir)) {
                        direction = (direction + 1) % 4;    // 오른쪽으로 회전
                    }
                }
            }

            return time;
        }
    }



}
