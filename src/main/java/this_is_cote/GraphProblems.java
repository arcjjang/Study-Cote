package this_is_cote;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public interface GraphProblems {
    // 37. 플로이드
    class GraphProblems17_37 {
        public static void main(String[] args) {
            int n = 5;
            int m = 14;
            int[][] edges = {
                    {1, 2, 2},
                    {1, 3, 3},
                    {1, 4, 1},
                    {1, 5, 10},
                    {2, 4, 2},
                    {3, 4, 1},
                    {3, 5, 1},
                    {4, 5, 3},
                    {3, 5, 10},
                    {3, 1, 8},
                    {1, 4, 2},
                    {5, 1, 7},
                    {3, 4, 2},
                    {5, 2, 4}
            };
            int[][] result = solution(n, m, edges);
            // 결과 출력 (필요시)
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (result[i][j] == (int) 1e9) {
                        System.out.print("INF ");
                    } else {
                        System.out.print(result[i][j] + " ");
                    }
                }
                System.out.println();
            }
        }

        public static int[][] solution(int n, int m, int[][] edges) {
            int INF = (int) 1e9;
            int[][] graph = new int[n + 1][n + 1];

            // 그래프 초기화
            for (int i = 1; i <= n; i++) {
                Arrays.fill(graph[i], INF);
                graph[i][i] = 0; // 자기 자신으로 가는 비용은 0
            }

            // 간선 정보 입력
            for (int[] edge : edges) {
                int a = edge[0];
                int b = edge[1];
                int cost = edge[2];
                graph[a][b] = Math.min(graph[a][b], cost); // 여러 간선 중 최소 비용 저장
            }

            // 플로이드 워셜 알고리즘
            for (int k = 1; k <= n; k++) {
                for (int i = 1; i <= n; i++) {
                    for (int j = 1; j <= n; j++) {
                        if (graph[i][j] > graph[i][k] + graph[k][j]) {
                            graph[i][j] = graph[i][k] + graph[k][j];
                        }
                    }
                }
            }

            return graph;
        }
    }

    // 38. 정확한 순위
    class GraphProblems17_38 {
        public static void main(String[] args) {

        }
    }

    // 39. 화성 탐사
    class GraphProblems17_39 {
        public static void main(String[] args) {
            int[][] spaces = {
                    {5, 5, 4},
                    {3, 9, 1},
                    {3, 2, 7}
            };
            loggingDist(spaces);
            System.out.println("result : " + solution(spaces)); // result : 20
        }

        public static class Node {
            int x, y, cost;

            public Node(int x, int y, int cost) {
                this.x = x;
                this.y = y;
                this.cost = cost;
            }
        }

        // 우선순위 큐를 사용한 다익스트라 알고리즘
        public static int solution(int[][] spaces) {
            int n = spaces.length;
            int m = spaces[0].length;

            // 4가지 방향 벡터 (상, 하, 좌, 우)
            int[] dx = {-1, 1, 0, 0};
            int[] dy = {0, 0, -1, 1};

            // 최단 거리 배열 초기화
            int[][] dist = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    dist[i][j] = Integer.MAX_VALUE;
                }
            }
            dist[0][0] = spaces[0][0];
            // loggingDist(dist);  // TODO: 디버깅용

            // 우선순위 큐 초기화 (거리, x, y)
            PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.cost));
            pq.offer(new Node(0, 0, spaces[0][0]));

            while (!pq.isEmpty()) {
                Node current = pq.poll();
                int currentDist = current.cost;
                int x = current.x;
                int y = current.y;

                // 현재 노드가 이미 최단 거리가 아닌 경우 무시
                if (currentDist > dist[x][y]) {
                    continue;
                }

                // 인접 노드 탐색
                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    // 범위 체크
                    if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                        int newDist = currentDist + spaces[nx][ny];
                        // 최단 거리 갱신
                        if (newDist < dist[nx][ny]) {
                            dist[nx][ny] = newDist;
                            // loggingDist(dist);  // TODO: 디버깅용
                            pq.offer(new Node(nx, ny, newDist));
                        }
                    }
                }
            }

            return dist[n - 1][m - 1];
        }

        private static void loggingDist(int[][] dist) {
            for (int[] row : dist) {
                System.out.println(Arrays.toString(row));
            }
            System.out.println("--------------------------------------");
        }
    }

}
