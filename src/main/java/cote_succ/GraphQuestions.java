package cote_succ;

import java.util.*;

public interface GraphQuestions {

    // 그래프의 구현 방식에는 인접 행렬, 인접 리스트가 있음
    // 인접 핼렬을 활용한 그래프 구현
    // 인접 리스트를 활용한 그래프 구현
    // 코딩 테스트에서는 인접 행렬보다는 인접 리스트를 더 자주 사용

    // 몸풀기 문제
    // 깊이 우선 탐색 순회
    class GraphQuestions_11_04_01 {
        public static void main(String[] args) {
            int[][] graph = {{1, 2}, {2, 3}, {3, 4}, {4, 5}};
            int start = 1;
            int n = 5;
//            int[] result = solution(graph, start, n);
            int[] result = mySolution(graph, start, n);
            for (int i : result) {  //
                System.out.print(i + " ");  // 1 2 3 4 5
            }
        }

        // 인접 리스트 저장할 ArrayList 배열
        private static ArrayList<Integer>[] adjList;

        // 방문 여부를 저장할 boolean 배열
        private static boolean[] visited;
        private static ArrayList<Integer> answer;

        // DFS를 스택으로 구현해보기
        private static int[] mySolution(int[][] graph, int start, int n) {
            adjList = new ArrayList[n + 1];
            for (int i = 0; i < adjList.length; i++) {
                adjList[i] = new ArrayList<>();
            }

            for (int[] edge : graph) {
                adjList[edge[0]].add(edge[1]);
            }

            visited = new boolean[n + 1];
            answer = new ArrayList<>();

            myDfs(start);

            return answer.stream().mapToInt(Integer::intValue).toArray();
        }

        private static void myDfs(int start) {
            Stack<Integer> stack = new Stack<>();
            stack.push(start);

            while (!stack.isEmpty()) {
                Integer now = stack.pop();
                visited[now] = true;
                answer.add(now);
                for (int next : adjList[now]) {
                    stack.push(next);
                }
            }
        }

        private static int[] solution(int[][] graph, int start, int n) {
            // 1. 인접 리스트 초기화
            adjList = new ArrayList[n + 1];
            for (int i = 0; i < adjList.length; i++) {
                adjList[i] = new ArrayList<>();
            }

            // 2. 그래프를 인접 리스트로 변환
            for (int[] edge : graph) {
                adjList[edge[0]].add(edge[1]);
            }

            // DFS를 순회한 결과를 반환
            visited = new boolean[n + 1];
            answer = new ArrayList<>();
            dfs(start); // 7. 시작 노드에서 깊이 우선 탐색 시작

            // 8. DFS 탐색 결과 반환
            return answer.stream().mapToInt(Integer::intValue).toArray();
        }

        // 3. DFS 탐색 메서드
        private static void dfs(int now) {
            visited[now] = true;    // 4. 현재 노드를 방문했음을 저장
            answer.add(now);        // 5. 현재 노드를 결과 리스트에 추가
            // 6. 현재 노드와 인접한 노드 순회
            for (int next : adjList[now]) {
                if (!visited[next]) {
                    dfs(next);
                }
            }
        }

    }

    // 너비 우선 탐색 순회
    class GraphQuestions_11_04_02 {
        public static void main(String[] args) {
            int[][] graph = {{1, 3}, {3, 4}, {3, 5}, {5, 2}};
            int start = 1;
            int n = 5;
            int[] result = solution(graph, start, n);
            for (int i : result) {
                System.out.print(i + " ");
            }

        }

        // 인접 리스트 저장할 ArrayList 배열
        private static ArrayList<Integer>[] adjList;

        // 방문 여부를 저장할 boolean 배열
        private static boolean[] visited;
        private static ArrayList<Integer> answer;

        private static int[] solution(int[][] graph, int start, int n) {
            adjList = new ArrayList[n + 1];
            for (int i = 0; i < adjList.length; i++) {
                adjList[i] = new ArrayList<>();
            }

            for (int[] edge : graph) {
                adjList[edge[0]].add(edge[1]);
            }

            // 1. 방문 여부를 저장할 배열
            visited = new boolean[n + 1];
            answer = new ArrayList<>();
            bfs(start); // 8. 시작 노드에서 너비 우선 탐색 시작

            return answer.stream().mapToInt(Integer::intValue).toArray();
        }

        // BFS 탐색 메서드
        private static void bfs(int start) {
            // 2. 탐색시 맨 처음 방문한 노드를 add 하고 방문 처리
            ArrayDeque<Integer> queue = new ArrayDeque<>();
            queue.add(start);
            visited[start] = true;

            // 3. 큐가 비어 있지 않은 동안 반복
            while (!queue.isEmpty()) {
                // 4. 큐에 있는 원소 중 가장 먼저 추가된 원소를 poll하고 정답 리스트에 추가
                int now = queue.poll();
                answer.add(now);
                // 5. 인접한 이웃 노드들에 대해서
                for (int next : adjList[now]) {
                    if (!visited[next]) {   // 6. 방문하지 않은 인접한 노드인 경우
                        // 7. 인접한 노드를 방문 처리함
                        queue.add(next);
                        visited[next] = true;
                    }
                }
            }
        }

    }

    // 다익스트라 알고리즘
    class GraphQuestions_11_04_03 {
        public static void main(String[] args) {
            int[][] graph = {{0, 1, 9}, {0, 2, 3}, {1, 0, 5}, {2, 1, 1}};
            int start = 0;
            int n = 3;
            int[] result = solution2(graph, start, n);
            for (int i : result) {
                System.out.print(i + " ");
            }
        }

        // 노드 정보(노드 번호와 거리)를 쌍으로 저장할 클래스 생성
        private static class Node {
            int dest, cost;

            public Node(int dest, int cost) {
                this.dest = dest;
                this.cost = cost;
            }
        }

        private static int[] solution(int[][] graph, int start, int n) {
            // 1. 인접 리스트를 저장할 ArrayList 배열 초기화
            ArrayList<Node>[] adjList = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                adjList[i] = new ArrayList<>();
            }

            // 2. graph 정보를 인접 리스트로 저장
            for (int[] edge : graph) {
                adjList[edge[0]].add(new Node(edge[1], edge[2]));
            }

            int[] dist = new int[n];
            // 3. 모든 노드의 거리 값을 무한대로 초기화
            Arrays.fill(dist, Integer.MAX_VALUE);

            // 4. 시작 노드의 거리 값은 0으로 초기화
            dist[start] = 0;

            // 5. 우선순위 큐를 생성하고 시작 노드를 삽입
            PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
            pq.add(new Node(start, 0));

            while (!pq.isEmpty()) {
                // 6. 현재 가장 거리가 짧은 노드를 가져옴
                Node now = pq.poll();

                // 7. 만약 현재 노드의 거리 값이 큐에서 가져온 거리 값보다 크면, 해당 노드는 이미 방문한 것으로 무시
                if (dist[now.dest] < now.cost) {
                    continue;
                }

                // 8. 현재 노드와 인접한 노드들의 거리 값을 계산하여 업데이트
                for (Node next : adjList[now.dest]) {
                    // 9. 기존에 발견했던 거리보다 더 짧은 거리를 발견하면 거리 값을 갱신하고 큐에 넣음
                    if (dist[next.dest] > now.cost + next.cost) {
                        dist[next.dest] = now.cost + next.cost;
                        pq.add(new Node(next.dest, dist[next.dest]));
                    }
                }
            }

            // 10. 최단 거리를 담고 있는 배열을 반환
            return dist;
        }

        private static int[] solution2(int[][] graph, int start, int n) {
            ArrayList<Node>[] adjList = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                adjList[i] = new ArrayList<>();
            }

            for (int[] edge : graph) {
                adjList[edge[0]].add(new Node(edge[1], edge[2]));
            }

            int[] dist = new int[n];
            Arrays.fill(dist, Integer.MAX_VALUE);
            boolean[] visited = new boolean[n];     // 방문 여부를 저장할 배열

            dist[start] = 0;

            PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
            pq.add(new Node(start, 0));

            while (!pq.isEmpty()) {
                Node now = pq.poll();

                if (visited[now.dest]) {    // 이미 방문한 노드면 건너 뜀
                    continue;
                }

                for (Node next : adjList[now.dest]) {
                    if (dist[next.dest] > now.cost + next.cost) {
                        dist[next.dest] = now.cost + next.cost;
                        pq.add(new Node(next.dest, dist[next.dest]));
                    }
                }
            }

            return dist;
        }

    }

    // 합격자가 되는 모의 테스트
    // 게임 맵 최단 거리
    class GraphQuestions_11_05_01 {
        public static void main(String[] args) {
            int[][] maps = {
                    {1, 0, 1, 1, 1},
                    {1, 0, 1, 0, 1},
                    {1, 0, 1, 1, 1},
                    {1, 1, 1, 0, 1},
                    {0, 0, 0, 0, 1},
            };
            int result = solution(maps);
            System.out.println("result = " + result);
        }

        // 1. 이동할 수 있는 방향을 나타내는 배열 rx, ry 선언
        private static final int[] rx = {0, 0, 1, -1};
        private static final int[] ry = {1, -1, 0, 0};

        private static class Node {
            int r, c;

            public Node(int r, int c) {
                this.r = r;
                this.c = c;
            }
        }

        private static int solution(int[][] maps) {
            // 2. 맵의 크기를 저장하는 변수 선언
            int N = maps.length;
            int M = maps[0].length;

            // 3. 최단 거리를 저장할 배열 생성
            int[][] dist = new int[N][M];

            // 4. bfs 탐색을 위한 큐 생성
            ArrayDeque<Node> queue = new ArrayDeque<>();

            // 5. 시작 정점에 대해서 큐에 추가, 최단 거리 저장
            queue.addLast(new Node(0, 0));
            dist[0][0] = 1;

            // 6. queue가 빌 때까지 반복
            while (!queue.isEmpty()) {
                Node now = queue.pollFirst();

                // 7. 현재 위치에서 이동할 수 있는 모든 방향
                for (int i = 0; i < 4; i++) {
                    int nr = now.r + rx[i];
                    int nc = now.c + ry[i];

                    // 8. 맵 밖으로 나가는 경우 예외 처리
                    if (nr < 0 || nc < 0 || nr >= N || nc >= M) {
                        continue;
                    }

                    // 9. 벽으로 가는 경우 예외 처리
                    if (maps[nr][nc] == 0) {
                        continue;
                    }

                    // 10. 이동할 위치가 처음 방문하는 경우, queue에 추가하고 거리 갱신
                    if (dist[nr][nc] == 0) {
                        queue.addLast(new Node(nr, nc));
                        dist[nr][nc] = dist[now.r][now.c] + 1;
                    }
                }
            }

            // 목적지까지 최단 거리 반환, 목적이에 도달하지 못한 경우에는 -1 반환
            return dist[N - 1][M - 1] == 0 ? -1 : dist[N - 1][M - 1];
        }

    }

    // 네트워크
    class GraphQuestions_11_05_02 {
        public static void main(String[] args) {
            int n = 3;
            int[][] computers = {
                    {1, 1, 0},
                    {1, 1, 0},
                    {0, 0, 1}
            };
            int result = solution(n, computers);
            System.out.println("result = " + result);
            int n2 = 3;
            int[][] computers2 = {
                    {1, 1, 0},
                    {1, 1, 1},
                    {0, 1, 1}
            };
            int result2 = solution(n2, computers2);
            System.out.println("result = " + result2);
        }

        private static int mySolution(int n, int[][] computers) {
            // 인접 리스트 초기화
            ArrayList<Integer>[] adjList = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                adjList[i] = new ArrayList<>();
            }

            // 인접 리스트 만들기
            for (int i = 0; i < computers.length; i++) {
                for (int j = 0; j < computers[i].length; j++) {
                    if (i != j && computers[i][j] != 0) {
                        adjList[i].add(j);
                    }
                }
            }

            // 방문 배열을 만든다.
            boolean[] visited = new boolean[n];

            // dfs를 위한 큐를 만든다.
            int result = 0;
            for (int i = 0; i < n; i++) {
                if (visited[i])
                    continue;
                visited[i] = true;
                result++;
                ArrayDeque<Integer> queue = new ArrayDeque<>();
                queue.add(i);
                while (!queue.isEmpty()) {
                    int now = queue.poll();
                    for (int next : adjList[now]) {
                        if (!visited[next]) {
                            queue.add(next);
                            visited[next] = true;
                        }
                    }
                }
            }

            return result;
        }

        // 이런 문제는 최적의 해를 구하는 것이 아니라 모든 요소를 탐색하는 것이 목적
        // 모든 요소를 탐색하는 문제는 깊이 우선 탐색이 좋다.
        private static boolean[] visit;
        private static int[][] computer;

        private static void dfs(int now) {
            visit[now] = true;  // 1. 현재 노드 방문 처리
            for (int i = 0; i < computer[now].length; i++) {
                // 2. 연결되어 있으며 방문하지 않은 노드라면
                if (computer[now][i] == 1 && !visit[i]) {
                    dfs(i); // 3. 해당 노드를 방문하러 이동
                }
            }
        }

        private static int solution(int n, int[][] computers) {
            int answer = 0;
            computer = computers;
            visit = new boolean[n]; // 4. 방문 여부를 저장할 배열

            for (int i = 0; i < n; i++) {
                if (!visit[i]) { // 5. 아직 방문하지 않은 노드라면 해당 노드를 시작으로 깊이 우선 탐색 진행
                    dfs(i);
                    answer++;   // 6. DFS로 연결된 노드들을 모두 방문하면서 네트워크 개수 증가
                }
            }
            return answer;
        }


    }

    // 미로 탈출
    class GraphQuestions_11_05_03 {
        public static void main(String[] args) {
            String[] maps = {
                    "SOOOL",
                    "XXXXO",
                    "OOOOO",
                    "OXXXX",
                    "OOOOE"
            };
            int result = solution(maps);
            System.out.println("result = " + result);
        }

        // 1. 위, 아래, 왼쪽, 오른쪽 이동 방향
        private static int[] dx = {0, 0, 1, -1};
        private static int[] dy = {1, -1, 0, 0};

        // 2. 위치 정보(x, y)를 저장할 클래스 생성
        private static class Point {
            int nx, ny;
            public Point(int nx, int ny) {
                this.nx = nx;
                this.ny = ny;
            }
        }

        private static char[][] map;
        private static int N, M;

        private static int solution(String[] maps) {
            int N = maps.length;
            int M = maps[0].length();

            // 3. 미로에 대한 정보를 배열로 저장
            map = new char[N][M];
            for (int i = 0; i < N; i++) {
                map[i] = maps[i].toCharArray();
            }

            Point start = null, end = null, lever = null;

            // 4. 시작 지점, 출구 그리고 레버의 위치를 찾음
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 'S')
                        start = new Point(i, j);
                    else if (map[i][j] == 'E')
                        end = new Point(i, j);
                    else if (map[i][j] == 'L')
                        lever = new Point(i, j);
                }
            }

            // 5. 시작 지점 -> 레버, 레버 -> 출구까지의 최단 거리를 각각 구함
            int startLever = bfs(start, lever);
            int leverEnd = bfs(lever, end);

            // 6. 도착 불가능한 경우는 -1, 도착 가능한 경우는 최단 거리를 반환
            if (startLever == -1 || leverEnd == -1)
                return -1;
            else
                return startLever + leverEnd;
        }

        // start -> end로 너비 우선 탐색하여 최단거리 반환
        private static int bfs(Point start, Point end) {
            // 7. 너비 우선 탐색 초기 과정
            int[][] dist = new int[N][M];
            ArrayDeque<Point> queue = new ArrayDeque<>();
            dist[start.ny][start.nx] = 1;
            queue.add(start);

            while (!queue.isEmpty()) {
                Point now = queue.poll();
                // 8. 네 방향으로 이동
                for (int i = 0; i < 4; i++) {
                    int nextX = now.nx + dx[i];
                    int nextY = now.ny + dy[i];

                    // 9. 범위를 벗어나는 경우 예외 처리
                    if (nextX < 0 || nextX >= M || nextY < 0 || nextY >= N)
                        continue;

                    // 10. 이미 방문한 지점인 경우 탐색하지 않음
                    if (dist[nextY][nextX] > 0)
                        continue;

                    // 11. X가 아닌 지점만 이동 가능
                    if (map[nextY][nextX] == 'X')
                        continue;

                    // 12. 거리 1 증가
                    dist[nextY][nextX] = dist[now.ny][now.nx] + 1;

                    // 13. 다음 정점을 큐에 넣음
                    queue.add(new Point(nextX, nextY));

                    // 14. 도착점에 도달하면 최단 거리를 반환
                    if (nextX == end.nx && nextY == end.ny) {
                        return dist[end.ny][end.nx] - 1;
                    }
                }
            }
            // 15. 탐색을 종료할 때까지 도착 지점에 도달하지 못했다면 -1 반환
            return -1;
        }


    }
}
