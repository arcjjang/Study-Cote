package cote_succ;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;

public interface UnionFindQuestions {
    // 10-1 집합과 상호배타적 집합의 개념
    // 집합의 개념 : 집합은 순서와 중복이 없는 원소들을 갖는 자료구조
    // 상호배타적 집합 : 교집합이 없는 집합 관계
    // 사용처 : 그래프 알고리즘에서 사이클 확인용

    // 10-2 집합의 연산
    // 집합은 보통 트리로 표현
    // 대표 연산 : 합치기와 탑색
    // 집합은 배열을 활용한 트리로 구현
    // 대표 원소 = 루트 노드
    // 배열로 상호배타적 관계를 가지는 집합을 모두 표현
    // 트리 형태 : 배열의 인덱스는 자신을, 배열값은 부모 노드를 의미한다.

    // 유니온-파인드 알고리즘
    // 파인드 연산 : 특정 노드의 루트 노드가 무엇인지 탐색하는 방법
    // 경로 압축은 집합을 구성하는 트리를 평평하게(flatten) 만들어서 찾기 연산을 효율적으로 할 수 있게 함
    // 합치기 연산 : 두 집합을 루트 노드 기준으로 하나로 합치는 연산
    // 1. 주어진 두 노드의 루트 노드를 찾기 연산을 통해 찾음
    // 2. 각 노드가 속한 집합을 합침. 두 집합의 루트 노드를 같게 하는 것, 두 집합 중 어떤 루트 노드로 해도 상관 없음
    // 유니온-파인트 알고리즘을 활용해야 하는 시나리오
    // - 조건 : 여러 개의 그룹이 형성되어 있음
    // - 문제 : 특정 원소가 주어졌을 때, 이 원소들이 같은 그룹인지 판단해야 할 경우

    // 몸풀기 문제
    // 간단한 유니온-파인드 알고리즘 구현하기(**)
    class TreeQuestionMain_10_03 {
        public static void main(String[] args) {
            int k = 3;
            int[][] operations = {{0, 0, 1}, {0, 1, 2}, {1, 1, 2}};
            Boolean[] result = solution(k, operations);
            for (Boolean b : result) {
                System.out.println("b = " + b);
            }
        }

        // 부모 저장을 위한 배열
        private static int[] parent;

        // 루트 노드를 찾는 메서드
        private static int find(int x) {
            // 만약 x의 부모가 자기 자신이면, 즉 x가 루트 노드라면 x를 반환
            if (parent[x] == x) {
                return x;
            }
            // 그렇지 않다면 x의 부모를 찾아서 parent[x]에 저장합니다.
            parent[x] = find(parent[x]);
            return parent[x];   // 찾은 루트 노드를 반환
        }

        private static void union(int x, int y) {
            int root1 = find(x);    // x가 속한 집합의 루트 노드 찾기
            int root2 = find(y);    // y가 속한 집합의 루트 노드 찾기
            parent[root2] = root1;  // y가 속한 집합을 x가 속한 집합에 합침
        }

        private static Boolean[] solution(int k, int[][] operation) {
            // 노드의 수 만큼 배열 생성
            parent = new int[k];
            // 처음에는 각 노드가 자기 자신을 부모로 가지도록 초기화
            for (int i = 0; i < k; i++) {
                parent[i] = i;
            }

            ArrayList<Boolean> answer = new ArrayList<>();

            for (int[] op : operation) {
                if (op[0] == 0) {   // 0 연산이면
                    union(op[1], op[2]);
                } else {    // 1 연산이면
                    answer.add(find(op[1]) == find(op[2]));
                }
            }

            return answer.toArray(new Boolean[0]);
        }


    }

    // 합격자가 되는 모의 테스트
    // 폰켓몬(*)
    class TreeQuestionMain_10_04 {
        public static void main(String[] args) {
            int[] nums = {3, 1, 2, 3};
            int result = mySolution(nums);
            System.out.println("result = " + result);
            int[] nums2 = {3, 3, 3, 2, 2, 4};
            int result2 = mySolution(nums2);
            System.out.println("result2 = " + result2);
            int[] nums3 = {3, 3, 3, 2, 2, 2};
            int result3 = mySolution(nums3);
            System.out.println("result3 = " + result3);
        }

        private static int mySolution(int[] nums) {
            // pickup = nums.length / 2
            // hashset 으로 중복을 제거
            // hashset의 size와 pickup 비교(min)
            int pickup = nums.length / 2;
            HashSet<Integer> phoneSet = new HashSet<Integer>();
            for (int i = 0; i < nums.length; i++) {
                phoneSet.add(nums[i]);
            }
            return Math.min(pickup, phoneSet.size());
        }

        private static int solution(int[] nums) {
            // 1. nums 리스트에서 중복을 제거한 집합(set)을 구함
            HashSet<Integer> set = Arrays.stream(nums).boxed().collect(Collectors.toCollection(HashSet::new));
            // 2. 폰켓몬의 총 수
            int n = nums.length;
            // 3. 선택할 폰켓몬의 수
            int k = n / 2;
            // 4. 중복을 제거한 폰켓몬의 종류 수와 선택할 폰켓몬의 수 중 작은 값 반환
            return Math.min(k, set.size());
        }

        private static int solution2(int[] nums) {
            return Math.min((int) Arrays.stream(nums).distinct().count(), nums.length / 2);
        }

    }

    // 영어 끝말잇기(*)
    class TreeQuestionMain_10_05 {
        public static void main(String[] args) {
            int n = 3;
            String[] words = {"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"};
            int[] result = solution(n, words);
            for (int r : result) {
                System.out.println("r = " + r);
            }
            int n2 = 5;
            String[] words2 = {"hello", "observe", "effect", "take", "either", "recognize", "encourage", "ensure", "establish", "hang", "gather", "refer", "reference", "estimate", "executive"};
            int[] result2 = mySolution(n2, words2);
            for (int r : result2) {
                System.out.println("r = " + r);
            }
            int n3 = 2;
            String[] words3 = {"hello", "one", "even", "never", "now", "world", "draw"};
            int[] result3 = mySolution(n3, words3);
            for (int r : result3) {
                System.out.println("r = " + r);
            }
        }

        private static int[] mySolution(int n, String[] words) {
            // 정답 : [번호, 차례]
            // 단어 중복 체크는 set으로 확인
            int[] result = new int[2];

            // n개씩 loop
            // 이전갑 저장
            // 차례를 위한 count
            HashSet<String> set = new HashSet<>();
            set.add(words[0]);
            String preWord = words[0];
            int order = 1;
            int count = 1;
            for (int i = 1; i < words.length; i++) {
                if (count == n) {
                    order++;
                    count = 0;
                }
//                System.out.println("i % n = " + i % n);
//                System.out.println("words[i] = " + words[i]);
//                System.out.println("order = " + order);
                if (set.contains(words[i]) || preWord.toCharArray()[preWord.length() - 1] != words[i].toCharArray()[0]) {
                    result[0] = i % n + 1;
                    result[1] = order;
                }
                set.add(words[i]);
                preWord = words[i];
                count++;
            }
            return result;
        }

        private static int[] solution(int n, String[] words) {
            // 1. 이미 사용한 단어를 저장하는 Set
            HashSet<String> usedWords = new HashSet<>();
            // 2. 이전 단어의 마지막 글자, 최초 상태는 첫 번째 사람이 말하는 첫 번째 글자로 초기화
            char prevWord = words[0].charAt(0);

            for (int i = 0; i < words.length; i++) {
                // 3. 이미 사용한 단어이거나 첫 글자가 이전 단어와 일치하지 않으면
                if (usedWords.contains(words[i]) || words[i].charAt(0) != prevWord) {
                    // 4. 탈락하는 사람의 번호와 차례를 반환
                    return new int[]{(i % n) + 1, (i / n) + 1};
                }
                // 5. 사용한 단어로 추가
                usedWords.add(words[i]);
                // 6. 이전 단어의 마지막 글자 업데이트
                prevWord = words[i].charAt(words[i].length() - 1);
            }

            // 7. 모두 통과했을 경우 반환값
            return new int[]{0, 0};
        }
    }

    // 섬 연결하기(***)
    // n개의 섬 사이에 다리를 건설하는 비용 costs가 주어질 때 최소 비용으로 모든 섬이 서로 통해하는 solution() 함수를 완성하세요.
    class TreeQuestionMain_10_06 {
        public static void main(String[] args) {
            int[][] costs = {{0, 1, 1}, {0, 2, 2}, {1, 2, 5}, {1, 3, 1}, {2, 3, 8}};
        }

        // 각 섬의 다리를 건설하는 비용을 오름차순으로 정렬
        // 비용이 작은 다리부터 선택해 섬을 연결(Greedy)
        // N개의 섬을 연결하려면 N-1의 다리가 필요하므로 N-1개의 다리가 선택될 때까지 위 두 과정을 반복
        // 비용을 최소화하기 위해 다리를 추가할 때 사이클을 형성하지 않도록 함

        private static int[] parent;

        private static int find(int x) {
            // 1. x가 속한 집합의 루트 노드 찾기
            if (parent[x] == x) {
                return x;
            }
            // 2. 경로 압축 : x의 부모 루트로 설정
            return parent[x] = find(parent[x]);
        }

        private static void union(int x, int y) {
            // 3. 두 집합을 하나의 집합으로 합치기
            int root1 = find(x);
            int root2 = find(y);
            parent[root2] = root1;
        }

        public int solution(int n, int[][] costs) {
            // 4. 비용을 기준으로 다리를 오름차순 정렬
            Arrays.sort(costs, (o1, o2) -> Integer.compare(o1[2], o2[2]));

            // 5. parent 배열 초기화
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }

            int answer = 0; // 최소 신장 트리의 총비용
            int edges = 0;  // 연결된 다리의 수

            for (int[] edge : costs) {
                // 6. n - 1개의 다리가 연결된 경우 모든 섬이 연결됨
                if (edges == n - 1)
                    break;

                // 7. 현재 다리가 연결하는 두 섬이 이미 연결되어 있는지 확인
                if (find(edge[0]) != find(edge[1])) {
                    // 8. 두 섬을 하나의 집합으로 연결함
                    union(edge[0], edge[1]);
                    // 9. 현재 다리의 건설 비용을 총비용에 추가
                    answer += edge[2];
                    // 10. 사용된 다리의 수 1 증가
                    edges++;
                }
            }
            return answer;
        }
    }
}
