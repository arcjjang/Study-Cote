package cote_succ;

import java.util.*;

public interface GraphQuestions {

    // 그래프의 구현 방식에는 인접 행렬, 인접 리스트가 있음
    // 인접 핼렬을 활용한 그래프 구현
    // 인접 리스트를 활용한 그래프 구현
    // 코딩 테스트에서는 인접 행렬보다는 인접 리스트를 더 자주 사용

    // 몸풀기 문제
    // 깊이 우선 탐색 순회
    class GraphQuestions_11_04 {
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
    class GraphQuestions_11_05 {
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
    class GraphQuestions_11_06 {
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
}
