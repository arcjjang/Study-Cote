package cote_succ;

import java.util.*;

public interface QueueQuestions {

    // 큐의 개념
    class QueueQuestionMain07_1 {
        public static void main(String[] args) {
            // 큐를 구현한 ArrayDeque 객체 생성
            Queue<Integer> queue = new ArrayDeque<>();

            // 큐에 데이터 추가
            queue.add(1);
            queue.add(2);
            queue.add(3);

            // 큐의 맨 앞 데이터를 제거하면서 반환
            Integer first = queue.poll();
            System.out.println("first = " + first);

            // 큐에 데이터 추가
            queue.add(4);
            queue.add(5);

            // 큐의 맨 앞 데이터를 제거하면서 반환
            first = queue.poll();
            System.out.println("first = " + first);

            ArrayDeque<Integer> queue2 = new ArrayDeque<>();

            // 큐에 데이터 추가
            queue2.add(1);
            queue2.add(2);
            queue2.add(3);

            // 큐의 맨 앞 데이터를 제거하면서 반환
            first = queue2.pollFirst();
            System.out.println("first = " + first);

            // 큐에 데이터 추가
            queue2.addLast(4);
            queue2.addLast(5);

            // 큐의 맨 앞 데이터를 제거하면서 반환
            first = queue2.pollFirst();
            System.out.println("first = " + first);
        }
    }

    // 요세푸스 문제
    class QueueQuestionMain07_2 {
        public static void main(String[] args) {
            int N = 5;
            int K = 2;
            int result = solution(N, K);
            System.out.println("result = " + result);
        }

        private static int mySolution(int N, int K) {
            ArrayDeque<Integer> queue = new ArrayDeque<>();
            for (int i = 1; i < N + 1; i++) {
                queue.add(i);
            }
            int index = 0;
            while (queue.size() > 1) {
                index++;
                if (index % K == 0) {
                    queue.poll();
                } else {
                    queue.add(queue.poll());
                }
            }
            return queue.peek();
        }

        private static int solution(int N, int K) {
            // 1. 1부터 N까지의 번호를 deque에 추가
            ArrayDeque<Integer> deque = new ArrayDeque<>();
            for (int i = 1; i <= N; i++) {
                deque.addLast(i);
            }

            // 2. deque에 하나의 요소가 남을 때까지 반복
            while (deque.size() > 1) {
                // 3. K번째 요소를 찾기 위해 앞에서부터 제거하고 뒤에 추가
                for (int i = 0; i < K - 1; i++) {
                    deque.addLast(deque.pollFirst());
                }
                deque.pollFirst();  // 4. K번째 요소 제거
            }

            return deque.pollFirst();   // 5. 마지막으로 남은 요소 반환
        }
    }

    // 기능 개발(**)
    class QueueQuestionMain07_3 {
        public static void main(String[] args) {
            int[] progresses = {93, 30, 55};
            int[] speeds = {1, 30, 5};
            int[] result = solution(progresses, speeds); // [2, 1]
            for (int i : result) {
                System.out.print(" " + i);
            }
            System.out.println();
            int[] progresses2 = {95, 90, 99, 99, 80, 99};
            int[] speeds2 = {1, 1, 1, 1, 1, 1};
            int[] result2 = solution(progresses2, speeds2); // [1, 3, 2]
            for (int i : result2) {
                System.out.print(" " + i);
            }
        }

        private static int[] mySolution(int[] progresses, int[] speeds) {
            // deque add last 93 30 55
            ArrayDeque<Integer> deque1 = new ArrayDeque<>();
            ArrayDeque<Integer> deque2 = new ArrayDeque<>();
            for (int i = 0; i < progresses.length; i++) {
                deque1.addLast(progresses[i]);
                deque2.addLast(speeds[i]);
            }
            // while 작업이 완료될 때까지
            // 하루씩
            int count = 0;
            List<Integer> result = new ArrayList<>();
            while (!deque1.isEmpty()) {
                if (deque1.peek() == 100) {
                    while (!deque1.isEmpty()) {
                        if (deque1.peek() >= 100) {
                            deque1.pollFirst();
                            deque2.pollFirst();
                            count++;
                        } else {
                            break;
                        }
                    }
                    result.add(count);
                    count = 0;
                } else {
                    for (int i = 0; i < deque1.size(); i++) {
                        int curProgress = deque1.pollFirst();
                        int curSpeed = deque2.pollFirst();
                        deque1.addLast(curProgress + curSpeed);
                        deque2.addLast(curSpeed);
                    }
                }
            }

            System.out.println("result = " + result);
            return result.stream().mapToInt(Integer::intValue).toArray();
        }

        private static int[] solution(int[] progresses, int[] speeds) {
            Queue<Integer> answer = new ArrayDeque<>();

            int n = progresses.length;
            // 1. 각 작업의 배포 가능일 계산
            int[] dayLeft = new int[n];
            for (int i = 0; i < n; i++) {
                dayLeft[i] = (int) Math.ceil((100.0 - progresses[i]) / speeds[i]);
            }
            int count = 0;  // 2. 배포될 작업의 수 카운트
            int maxDay = dayLeft[0];    // 3. 현재 배포될 작업 중 가장 늦게 배포될 작업의 가능일

            for (int i = 0; i < n; i++) {
                if (dayLeft[i] <= maxDay) { // 4. 배포 가능일이 가장 늦은 배포일보다 빠르면
                    count++;
                } else {    // 5. 배포 예정일이 기준 배포일보다 느리면
                    answer.add(count);
                    count = 1;
                    maxDay = dayLeft[i];
                }
            }

            answer.add(count);  // 6. 마지막으로 카운팅된 작업들을 함께 배포
            return answer.stream().mapToInt(Integer::intValue).toArray();
        }
    }

    // 카드 뭉치(**)
    class QueueQuestionMain07_4 {
        public static void main(String[] args) {
            String[] cards1 = {"i", "drink", "water"};
            String[] cards2 = {"want", "to"};
            String[] goal = {"i", "want", "to", "drink", "water"};
            String result = solution(cards1, cards2, goal);
            System.out.println("result = " + result);   // Yes

            String[] cards11 = {"i", "water", "drink"};
            String[] cards22 = {"want", "to"};
            String[] goal2 = {"i", "want", "to", "drink", "water"};
            String result2 = solution(cards11, cards22, goal2);
            System.out.println("result2 = " + result2);   // No
        }

        private static String mySolution(String[] cards1, String[] cards2, String[] goal) {
            String result = "No";

            // 모두 dequeue에 넣자
            // goal 에서 하나씩 빼서 cards1과 cards2에 처음 뺄 수 있는 녀석과 비교하자.
            // 있으면 반복
            // 없으면 No 리턴
            ArrayDeque<String> goalDeque = new ArrayDeque<>();
            for (String s : goal) {
                goalDeque.addLast(s);
            }
            ArrayDeque<String> cards1Deque = new ArrayDeque<>();
            for (String s : cards1) {
                cards1Deque.addLast(s);
            }
            ArrayDeque<String> cards2Deque = new ArrayDeque<>();
            for (String s : cards2) {
                cards2Deque.addLast(s);
            }
            System.out.println(goalDeque);
            System.out.println(cards1Deque);
            System.out.println(cards2Deque);
            int size = goalDeque.size();
            while (!goalDeque.isEmpty()) {
                String firstGoal = goalDeque.peek();
                if (!cards1Deque.isEmpty() && firstGoal.equals(cards1Deque.peek())) {
                    goalDeque.pollFirst();
                    cards1Deque.pollFirst();
                } else if (!cards2Deque.isEmpty() && firstGoal.equals(cards2Deque.peek())) {
                    goalDeque.pollFirst();
                    cards2Deque.pollFirst();
                } else {
                    break;
                }
            }
            result = goalDeque.isEmpty() ? "Yes" : "No";
            return result;
        }

        private static String solution(String[] cards1, String[] cards2, String[] goal) {
            // cards와 goal을 deque로 전환
            ArrayDeque<String> cardsDeque1 = new ArrayDeque<>(Arrays.asList(cards1));
            ArrayDeque<String> cardsDeque2 = new ArrayDeque<>(Arrays.asList(cards2));
            ArrayDeque<String> goalDeque = new ArrayDeque<>(Arrays.asList(goal));

            // 1. goalDeque에 문자열이 남아 있으면 계속 반복
            while (!goalDeque.isEmpty()) {
                // 2. cardsDeque의 front와 일치하는 경우
                if (!cardsDeque1.isEmpty() && cardsDeque1.peekFirst().equals(goalDeque.peekFirst())) {
                    cardsDeque1.pollFirst();
                    goalDeque.pollFirst();
                } else if (!cardsDeque2.isEmpty() && cardsDeque2.peekFirst().equals(goalDeque.peekFirst())) {   // 3. cardDeque2의 front와 일치하는 경우
                    cardsDeque2.pollFirst();
                    goalDeque.pollFirst();
                } else {
                    break;  // 일치하는 원소를 찾지 못했으므로 종료
                }
            }

            // 4. goal이 비었으면 "Yes" 아니면 "No"를 반환
            return goalDeque.isEmpty() ? "Yes" : "No";
        }
    }

}
