package cote_succ;

import java.util.*;

public interface TreeQuestions {

    // 이진 트리 표현하기
    class TreeQuestionMain_09_02 {
        public static void main(String[] args) {
            // 배열로 표현하기
            // - 루트 노드는 배열 인덱스 1번으로 저장
            // - 왼쪽 자식 노드의 배열 인덱스는 부모 노드의 배열 인덱스 x 2
            // - 오른쪽 자식 노드의 배열 인덱스는 부모 노드의 배열 인덱스 x 2 + 1

            // 전위 순회 : 부모 -> 왼쪽 -> 오른쪽 <-- 트리를 복사할 때
            // 중위 순회 : 왼쪽 -> 부모 -> 오른쪽 <-- 트리를 정렬된 순서로 값을 가져올 때
            // 후위 순회 : 왼쪽 -> 오른쪽 -> 부모 <-- 트리를 삭제할 때

            // 포인터로 표현하기
            // 인접 리스트로 표현하기
        }
    }

    // 이진 트리 탐색하기
    class TreeQuestionMain_09_03 {
        public static void main(String[] args) {
            // 이진 트리에서 가장 중요한 것은 바로 탐색을 효율적으로 할 수 있도록 트리를 구축하는 것
            // 이진 탐색 트리는 데이터 크기를 따져 크기가 작으면 왼쪽 자식 위치에, 크리가 같으면 오른쪽 자식 위치에 배치하는 독특한 정렬 방식을 갖음
            // 이진 탐색 트리는 크면 오른쪽, 작으면 왼쪽
        }
    }

    // 트리 순회
    class TreeQuestionMain_09_04 {
        public static void main(String[] args) {
            int[] nodes = {1, 2, 3, 4, 5, 6, 7};
            String[] result = solution(nodes);
            for (String s : result) {
                System.out.println("s = " + s);
            }
        }

        private static String[] solution(int[] nodes) {
            String[] result = new String[3];
            result[0] = preorder(nodes, 0).trim();  // 마지막 공백 제거
            result[1] = inorder(nodes, 0).trim();  // 마지막 공백 제거
            result[2] = postorder(nodes, 0).trim();  // 마지막 공백 제거
            return result;
        }

        private static String preorder(int[] nodes, int idx) {
            if (idx >= nodes.length) {  // idx가 범위를 벗어나면 빈 문자열 변환
                return "";
            }

            // 루트 노드 -> 왼쪽 서브 트리 -> 오른쪽 서브 트리 순으로 재귀 호출하여 결과를 이어 붙임
            return nodes[idx] + " " + preorder(nodes, 2 * idx + 1) + preorder(nodes, 2 * idx + 2);
        }

        private static String inorder(int[] nodes, int idx) {
            if (idx >= nodes.length) {  // idx가 범위를 벗어나면 빈 문자열 반환
                return "";
            }

            // 왼쪽 서브 트리 -> 루트 노드 -> 오른쪽 서브 트리 순으로 재귀 호출하여 결과를 이어 붙임
            return inorder(nodes, 2 * idx + 1) + nodes[idx] + " " + inorder(nodes, 2 * idx + 2);
        }

        private static String postorder(int[] nodes, int idx) {
            if (idx >= nodes.length) {  // idx가 범위를 벗어나면 빈 문자열 반환
                return "";
            }

            // 왼쪽 서브 트리 -> 오른쪽 서브 트리 -> 루트 노드 순으로 재귀 호출하여 결과를 이어 붙임
            return postorder(nodes, 2 * idx + 1) + postorder(nodes, 2 * idx + 2) + nodes[idx] + " ";
        }
    }

    // 예상 대진표(*)
    class TreeQuestionMain_09_05_01 {
        public static void main(String[] args) {
            int N = 8, A = 4, B = 7;
            int result = solution(N, A, B);
            System.out.println("result = " + result);
        }

        private static int solution(int n, int a, int b) {
            int answer = 0;

            for (answer = 0; a != b; answer++) {
                a = (a + 1) / 2;
                b = (b + 1) / 2;
            }
            return answer;
        }
    }

    // 다단계 칫솔 판매(**)
    class TreeQuestionMain_09_05_02 {
        public static void main(String[] args) {
            String[] enroll = {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"}; // 판매원의 이름 배열
            String[] referral = {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"}; // 추천인의 이름 배열
            String[] seller = {"young", "john", "tod", "emily", "mary"}; // 판매량 집계 데이터의 판매원 이름 배열
            int[] amount = {12, 4, 2, 5, 10}; // 판매량 집계 데이터의 판매 수량 배열
            int[] result = solution(enroll, referral, seller, amount);
            for (int i : result) {
                System.out.println("i = " + i);
            }
        }

        private static int[] mySolution(String[] enroll, String[] referral, String[] seller, int[] amount) {
            // enroll 과 referral로 트리를 만듬
            // 10%씩 떼어주고 1원 미만이면 안줌

            // john
            // mary : 900
            // edward - mary
            // sam - edward - mary
            // emily - mary (*) 45
            // jaimie - mary
            // tod - jaimie - mary (*) 2
            // young - edward - mary (*) 12


            // 다단계 구조는 May<String, LinkedList<String>>
            // 결과갑은 May<String, Integer>
            // 결과는 int[]
            // seller 와 amount 를 돌면서 계산
            LinkedHashMap<String, Integer> moneyMap = new LinkedHashMap<>();
            HashMap<String, LinkedList<String>> cascadedMap = new HashMap<>();
            for (int i = 0; i < enroll.length; i++) {
                moneyMap.put(enroll[i], 0);
                if ("-".equals(referral[i])) {
                    cascadedMap.put(enroll[i], new LinkedList<>());
                } else {
                    LinkedList<String> list;
                    if (cascadedMap.containsKey(referral[i])) {
                        list = new LinkedList<>(cascadedMap.get(referral[i]));
                        list.addFirst(referral[i]);
                    } else {
                        list = new LinkedList<>();
                        list.add(referral[i]);
                    }
                    cascadedMap.put(enroll[i], list);
                }
            }
            System.out.println("cascadedMap = " + cascadedMap);
            for (int i = 0; i < seller.length; i++) {
                int sumMoney = amount[i] * 100;
                int distMoney = sumMoney / 10;
                int myMoney = sumMoney - distMoney;
                LinkedList<String> upList = cascadedMap.get(seller[i]);
                System.out.println("i = " + i);
                for (int j = 0; j < upList.size(); j++) {
                    int temp = distMoney / 10;
                    int temp2 = distMoney - temp;
                    System.out.println("j = " + j);
                    moneyMap.put(upList.get(j), moneyMap.get(upList.get(j)) + temp2);
                    distMoney = temp;
                }
                moneyMap.put(seller[i], moneyMap.get(seller[i]) + myMoney);
            }

            System.out.println("moneyMap = " + moneyMap);
            return moneyMap.values().stream().mapToInt(Integer::intValue).toArray();
        }

        private static int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
            // 1. parent 해시맵. key는 enroll의 노드, value는 referral의 노드로 구성됨
            HashMap<String, String> parent = new HashMap<>();
            for (int i = 0; i < enroll.length; i++) {
                parent.put(enroll[i], referral[i]);
            }

            // 2. total 해시맵 생성
            HashMap<String, Integer> total = new HashMap<>();

            // 3. seller 배열과 amount 배열을 이용하여 이익 분배
            for (int i = 0; i < seller.length; i++) {
                String curName = seller[i];
                // 4. 판매자가 판매한 총금액 계산
                int money = amount[i] * 100;
                // 5. 판매자부터 차례대로 상위 노드로 이동하며 이익 분배
                while (money > 0 && !curName.equals("-")) {
                    // 6. 현재 판매자가 받을 금액 계산(10%를 제외한 금액)
                    total.put(curName, total.getOrDefault(curName, 0) + money - (money / 10));
                    curName = parent.get(curName);
                    // 7. 10% 를 제외한 금액 계산
                    money /= 10;
                }
            }

            // 8. enroll 배열의 모든 노드에 대한 해당하는 이익을 배열로 반환
            int[] answer = new int[enroll.length];
            for (int i = 0; i < enroll.length; i++) {
                answer[i] = total.getOrDefault(enroll[i], 0);
            }
            return answer;
        }
    }

    // 양과 늑대(*****)
    class TreeQuestionMain_09_05_03 {
        public static void main(String[] args) {
            int[] info = {0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1};
            int[][] edges = {{0, 1}, {1, 2}, {1, 4}, {0, 8}, {8, 7}, {9, 10}, {9, 11}, {4, 3}, {6, 5}, {4, 6}, {8, 9}};
            int result = solution(info, edges);
            System.out.println("result = " + result);
        }

        // 현재 위치, 양의 수, 늑대의 수, 방문한 노드 저장을 위한 클래스
        private static class Info {
            int node, sheep, wolf;
            HashSet<Integer> visited;

            public Info(int node, int sheep, int wolf, HashSet<Integer> visited) {
                this.node = node;
                this.sheep = sheep;
                this.wolf = wolf;
                this.visited = visited;
            }
        }

        private static ArrayList<Integer>[] tree;   // 트리 정보를 저장할 인접 리스트

        // 1. 트리 구축 메서드
        private static void buildTree(int[] info, int[][] edges) {
            tree = new ArrayList[info.length];  // 2. 트리 생성
            for (int i = 0; i < tree.length; i++) {
                tree[i] = new ArrayList<>();
            }

            for (int[] edge : edges) {
                tree[edge[0]].add(edge[1]);
            }
        }

        private static int solution(int[] info, int[][] edges) {
            buildTree(info, edges); // 2. 트리 생성
            int answer = 0; // 3. 최대 양의 수를 저장할 변수

            // 4. BFS를 위한 큐 생성 및 초기 상태 설정
            ArrayDeque<Info> queue = new ArrayDeque<>();
            queue.add(new Info(0, 1, 0, new HashSet<>()));

            // BFS(너비 우선 탐색) 시작
            while (!queue.isEmpty()) {
                // 5. 큐에서 현재 상태를 꺼냄
                Info now = queue.poll();
                // 6. 최대 양의 수 업데이트
                answer = Math.max(answer, now.sheep);
                // 7. 방문한 노드 집합에 현재 노드의 이웃 노드 추가
                now.visited.addAll(tree[now.node]);

                // 8. 인접한 노드들에 대해 탐색
                for (int next : now.visited) {
                    // 9. 기존 해시셋의 데이터를 복사하고 현재 방문한 정점을 해시셋에서 제거
                    HashSet<Integer> set = new HashSet<>(now.visited);
                    set.remove(next);

                    if (info[next] == 1) {  // 10. 늑대일 경우
                        if (now.sheep != now.wolf + 1) {
                            queue.add(new Info(next, now.sheep, now.wolf + 1, set));
                        }
                    } else {    // 11. 양일 경우
                        queue.add(new Info(next, now.sheep + 1, now.wolf, set));
                    }
                }
            }

            return answer;
        }

    }


}
