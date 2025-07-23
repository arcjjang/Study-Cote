package this_is_cote;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public interface DfsBfsProblems {
    class IDfsBfsProblems00_15 {
        public static void main(String[] args) {
//            int[][] graph = {
//                    {1, 2},
//                    {1, 3},
//                    {2, 3},
//                    {2, 4}
//            };
//            int N = 4; // 도시 개수
//            int K = 2; // 최단거리
//            int X = 1; // 시작도시
            int[][] graph = {
                    {1, 2},
                    {1, 3}
            };
            int N = 3;  // 도시 개수
            int K = 1;  // 최단 거리
            int X = 1;  // 시작 도시
            List<Integer> result = findCitiesAtDistanceK(N, graph, K, X);
            if (result.isEmpty()) {
                System.out.println(-1);
            } else {
                result.stream().sorted().forEach(System.out::println);
            }
        }

        private static List<Integer> findCitiesAtDistanceK(int n, int[][] roads, int k, int start) {
            // 1. 그래프 생성 (인접 리스트)
            List<List<Integer>> graph = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                graph.add(new ArrayList<>());
            }
            for (int[] road : roads) {
                graph.get(road[0]).add(road[1]);
            }

            // 2. 최단거리 배열 초기화
            int[] dist = new int[n + 1];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[start] = 0;

            // 3. BFS 초기화
            Queue<Integer> queue = new LinkedList<>();
            queue.add(start);

            // 4. BFS 실행
            while (!queue.isEmpty()) {
                int current = queue.poll();

                // 현재 노드와 연결된 모든 인접 노드를 탐색
                for (int next : graph.get(current)) {
                    // 아직 방문하지 않은 도시에 대해 거리 갱신
                    if (dist[next] == Integer.MAX_VALUE) {
                        dist[next] = dist[current] + 1;
                        queue.add(next);
                    }
                }
            }

            // 5. 결과 생성 (거리가 K인 도시 찾기)
            List<Integer> result = new ArrayList<>();
            for (int i = 1; i <= n; i++) {
                if (dist[i] == k) {
                    result.add(i);
                }
            }
            return result;
        }

    }
}

