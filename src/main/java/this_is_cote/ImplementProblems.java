package this_is_cote;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

// CHAPTER 04/12 구현
public interface ImplementProblems {

    class ImplementProblemMain04_01 {

    }

    // 삼성전자 SW 역량테스트 뱀
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
