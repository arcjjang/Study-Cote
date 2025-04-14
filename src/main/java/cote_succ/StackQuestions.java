package cote_succ;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

public interface StackQuestions {

    class StackQuestionMain06_2 {
        public static void main(String[] args) {
            Stack<Integer> stack = new Stack<>();   // 스택 객체 생성
            // 스택에 데이터 푸시
            stack.push(1);
            stack.push(3);
            // 스택이 비어 있는지 확인
            System.out.println(stack.isEmpty());    // false
            // 스택에서 팝
            System.out.println(stack.pop());    // 3
            System.out.println(stack.pop());    // 1
            System.out.println(stack.isEmpty());    // true

            Stack<Integer> stack2 = new Stack<>();
            stack2.push(6);
            stack2.push(5);
            // 스택에 가장 최근에 푸시한 값(peek)
            System.out.println(stack2.peek());  // 5
            System.out.println(stack2.pop());   // 5
            // 스택에 들어 있는 데이터의 개수(size)
            System.out.println(stack2.size());  // 1
            stack2.push(7);
            stack2.push(4);
            System.out.println(stack2.pop());   // 4
            System.out.println(stack2.pop());   // 7
            System.out.println(stack2.pop());   // 6
            System.out.println(stack2.size());  // 0
        }
    }

    // 몸풀기 문제
    // 올바른 괄호(**)
    class StackQuestionMain06_3_8 {
        public static void main(String[] args) {
            String s1 = "()()";
            boolean result1 = solution(s1);
            System.out.println("result1 = " + result1);
            String s2 = "(())()";
            boolean result2 = solution(s2);
            System.out.println("result2 = " + result2);
            String s3 = ")()(";
            boolean result3 = solution(s3);
            System.out.println("result3 = " + result3);
            String s4 = "(()(";
            boolean result4 = solution(s4);
            System.out.println("result4 = " + result4);
        }

        private static boolean mySolution(String s) {
            Stack<Character> checkStack = new Stack<>();
            for (Character c : s.toCharArray()) {
                if (checkStack.isEmpty()) {
                    checkStack.push(c);
                    continue;
                }
                if (')' == c && checkStack.peek() == '(') {
                    checkStack.pop();
                } else {
                    checkStack.push(c);
                }
            }
            return checkStack.isEmpty();
        }

        private static boolean solution(String s) {
            ArrayDeque<Character> stack = new ArrayDeque<>();

            char[] a = s.toCharArray();
            for (char c : a) {
                if (c == '(') {
                    stack.push(c);
                } else {
                    if (stack.isEmpty() || stack.pop() == c) {  // 1. 스택이 비어 있는지 먼저 검사
                        return false;
                    }
                }
            }

            return stack.isEmpty();
        }


    }

    // 10진수를 2진수로 변환하기(*)
    class StackQuestionMain06_3_9 {
        public static void main(String[] args) {
            // 10 -> 1010
            // 27 -> 11011
            // 12345 -> 10011100000011
            int n1 = 10;
            String result1 = mySolution(n1);
            System.out.println("result1 = " + result1);
            int n2 = 27;
            String result2 = mySolution(n2);
            System.out.println("result2 = " + result2);
            int n3 = 12345;
            String result3 = mySolution(n3);
            System.out.println("result3 = " + result3);
        }

        private static String mySolution(int n) {
            ArrayDeque<Integer> stack = new ArrayDeque<>();
            int num = n;
            while (num > 0) {
                stack.push(num % 2);
                num = num / 2;
            }
            StringBuilder sb = new StringBuilder();
            while (!stack.isEmpty()) {
                sb.append(stack.pop());
            }
            return sb.toString();
        }

        private static String solution(int decimal) {
            Stack<Integer> stack = new Stack<>();
            while (decimal > 0) {
                int remainder = decimal % 2;
                stack.push(remainder);
                decimal /= 2;
            }

            // String의 + 연산은 시간 복잡도 측면에서 성능이 좋지 않음
            // 따라서 StringBuilder를 사용했음
            StringBuilder sb = new StringBuilder();
            while (!stack.isEmpty()) {
                sb.append(stack.pop());
            }

            return sb.toString();
        }

    }

    // 괄호 회전하기(*)
    class StackQuestionMain06_3_10 {
        public static void main(String[] args) {
            String s1 = "[](){}";
            int result1 = solution(s1);
            System.out.println("result1 = " + result1);

            String s2 = "}]()[{";
            int result2 = solution(s2);
            System.out.println("result2 = " + result2);

            String s3 = "[)(]";
            int result3 = solution(s3);
            System.out.println("result3 = " + result3);

            String s4 = "}}}";
            int result4 = solution(s4);
            System.out.println("result4 = " + result4);
        }

        private static int mySolution(String s) {
            char[] chars = s.toCharArray();
            int size = chars.length;
            int count = 0;
            for (int x = 0; x < size; x++) {
                count = checkValidStack(makeNewString(x, chars)) ? count + 1 : count;
            }
            return count;
        }

        private static String makeNewString(int x, char[] chars) {
            StringBuilder sb = new StringBuilder();
            if (x == 0) {
                for (int i = 0; i < chars.length; i++) {
                    sb.append(chars[i]);
                }
            } else {
                for (int i = x; i < chars.length; i++) {
                    sb.append(chars[i]);
                }
                for (int i = 0; i < x; i++) {
                    sb.append(chars[i]);
                }
            }
            return sb.toString();
        }

        private static boolean checkValidStack(String s) {
            System.out.println("s = " + s);
            Stack<Character> stack = new Stack<>();
            char[] a = s.toCharArray();
            for (char c : a) {
                switch (c) {
                    case '(':
                    case '{':
                    case '[':
                        stack.push(c);
                        break;
                    case ')':
                    case '}':
                    case ']':
                        if (stack.isEmpty()) {
                            return false;
                        }
                        char curr = stack.peek();
                        if (curr == ')' || curr == '}' || curr == ']') {
                            return false;
                        }
                        if (curr == '(' && c == ')') {
                            stack.pop();
                        } else if (curr == '[' && c == ']') {
                            stack.pop();
                        } else if (curr == '{' && c == '}') {
                            stack.pop();
                        }
                        break;
                }
            }
            return stack.isEmpty();
        }

        private static int solution(String s) {
            // 1. 괄호 정보를 저장함. 코드를 간결하게 할 수 있음
            HashMap<Character, Character> map = new HashMap<>();
            map.put(')', '(');
            map.put('}', '{');
            map.put(']', '[');

            int n = s.length(); // 원본 문자열의 길이 저장
            s += s; // 원본 문자열 뒤에 원본 문자열을 이어 붙여서 2번 나오도록 만들어줌
            int answer = 0;

            // 2. 확인할 문자열의 시작 인덱스를 0부터 n까지 이동
            A:
            for (int i = 0; i < n; i++) {
                ArrayDeque<Character> stack = new ArrayDeque<>();
                // 3. i(시작 위치)부터 원본 문자열의 길이인 n개까지 올바른 괄호 문자열인지 확인
                for (int j = i; j < i + n; j++) {
                    char c = s.charAt(j);
                    // 해시맵 안에 해당 키가 없다면 열리는 괄호임
                    if (!map.containsKey(c)) {
                        stack.push(c);
                    } else {
                        // 4. 짝이 맞지 않으면 내부 for문을 종료하고 for문 A로 이동
                        if (stack.isEmpty() || !stack.pop().equals(map.get(c))) {
                            continue A;
                        }
                    }
                }
                // 5. 3에서 continue되지 않았고, 스택이 비어있으면 올바른 괄호 문자열임
                if (stack.isEmpty())
                    answer++;
            }
            return answer;
        }
    }

    // 짝지어 제거하기(*)
    class StackQuestionMain06_3_11 {
        public static void main(String[] args) {
            String s = "baabaa";
            int result1 = mySolution(s);
            System.out.println("result1 = " + result1);
            String s2 = "cdcd";
            int result2 = mySolution(s2);
            System.out.println("result2 = " + result2);
        }

        private static int mySolution(String s) {
            char[] chars = s.toCharArray();
            Stack<Character> stack = new Stack<>();
            for (char c : chars) {
                if (stack.isEmpty()) {
                    stack.push(c);
                } else {
                    if (stack.peek() == c) {
                        stack.pop();
                    } else {
                        stack.push(c);
                    }
                }
            }
            return stack.isEmpty() ? 1 : 0;
        }

        private static int solution(String s) {
            Stack<Character> stack = new Stack<>();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                // 1. 스택이 비어 있지 않고, 현재 문자와 스택의 맨 위 문자가 같으면
                if (!stack.isEmpty() && stack.peek() == c) {
                    stack.pop();  // 2. 스택의 맨 위 문자 제거
                } else {
                    stack.push(c);  // 3. 스택에 현재 문자 추가
                }
            }

            return stack.isEmpty() ? 1 : 0;   // 4. 스택이 비어 있으면 1, 그렇지 않으면 0 반환
        }

    }

    // 주식 가격(**)
    class StackQuestionMain06_3_12 {
        public static void main(String[] args) {
            int[] prices = {1, 2, 3, 2, 3};
            int[] result = solution(prices);
            System.out.println(Arrays.toString(result));
        }

        private static int[] solution(int[] prices) {
            int n = prices.length;
            int[] answer = new int[n];  // 1. 가격이 떨어지지 않은 기간을 저장할 배열

            // 스택을 사용해 이전 가격과 현재 가격 비교
            Stack<Integer> stack = new Stack<>();   // 2. 스택 생성
            stack.push(0);

            for (int i = 1; i < n; i++) {
                while (!stack.isEmpty() && prices[i] < prices[stack.peek()]) {
                    // 3. 가격이 떨어졌으므로 이전 가격의 기간 계산
                    int j = stack.pop();
                    answer[j] = i - j;
                }
                stack.push(i);
            }

            // 4. 스택에 남아 있는 가격들은 가격이 떨어지지 않은 경우
            while (!stack.isEmpty()) {
                int j = stack.pop();
                answer[j] = n - 1 - j;
            }
            return answer;
        }
    }

    // 크레인 인형 뽑기 게임(**)
    class StackQuestionMain06_3_13 {
        public static void main(String[] args) {
            int[][] board = {
                    {0, 0, 0, 0, 0},
                    {0, 0, 1, 0, 3},
                    {0, 2, 5, 0, 1},
                    {4, 2, 4, 4, 2},
                    {3, 5, 1, 3, 1},
            };
            int[] moves = {1, 5, 3, 5, 1, 2, 1, 4};
            int result1 = mySolution(board, moves);
            System.out.println("result1 = " + result1);
        }

        private static int mySolution(int[][] board, int[] moves) {
            int pickup, position;
            int result = 0;
            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i < moves.length; i++) {
                for (int j = 0; j < board.length; j++) {
                    position = moves[i];
                    pickup = board[j][position - 1];
                    if (pickup != 0) {
                        if (!stack.isEmpty() && stack.peek() == pickup) {
                            stack.pop();
                            result += 2;
                        } else {
                            stack.push(pickup);
                        }
                        board[position - 1][j] = 0;
                    }
                }
            }
            return result;
        }

        private static int solution(int[][] board, int[] moves) {
            // 1. 각 열에 대한 스택을 생성
            Stack<Integer>[] lanes = new Stack[board.length];
            for (int i = 0; i < lanes.length; i++) {
                lanes[i] = new Stack<>();
            }

            // 2. board를 역순으로 탐색하며, 각 열의 인형을 lanes에 추가
            for (int i = board.length - 1; i >= 0; i--) {
                for (int j = 0; j < board[i].length; j++) {
                    if (board[i][j] > 0) {
                        lanes[i].push(board[i][j]);
                    }
                }
            }

            // 3. 인형을 담을 bucket을 생성
            Stack<Integer> bucket = new Stack<>();
            // 4. 사라진 인형의 총 개수를 저장할 변수를 초기화
            int answer = 0;

            // 5. moves를 순회하며, 각 열에서 인형을 뽑아 bucket에 추가
            for (int move : moves) {
                if (!lanes[move -1].isEmpty()) {    // 해당 열에 인형이 있는 경우
                    int doll = lanes[move - 1].pop();
                    // 6. 바구니에 인형이 있고, 가장 위에 있는 인형과 같은 경우
                    if (!bucket.isEmpty() && bucket.peek() == doll) {
                        bucket.pop();
                        answer += 2;
                    } else {    // 7. 바구니에 인형이 없거나, 가장 위에 있는 인형과 다른 경우
                        bucket.push(doll);
                    }
                }
            }
            return answer;
        }
    }

    // 표 편집(*****)
    class StackQuestionMain06_3_14 {
        public static void main(String[] args) {
            int n = 8;
            int k = 2;
            String[] cmd = {"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z"};
            String result = solution(n, k, cmd);
            System.out.println("result = " + result);
        }

        private static String solution(int n, int k, String[] cmd) {
            // 1. 삭제된 행의 인덱스를 저장하는 스택
            Stack<Integer> deleted = new Stack<>();
            // 2. 각 행을 기준으로 연산에 따른 위치를 표시하기 위한 배열
            int[] up = new int[n + 2];
            int[] down = new int[n + 2];

            for (int i = 0; i < n + 2; i++) {
                up[i] = i - 1;
                down[i] = i + 1;
            }

            // 3. 현재 위치를 나타내는 인덱스
            k++;

            // 4. 주어진 명령어(cmd) 배열을 하나씩 처리
            for (String c : cmd) {
                // 5. 현재 위치를 삭제하고 그 다음 위치로 이동
                if (c.startsWith("C")) {
                    deleted.push(k);
                    up[down[k]] = up[k];
                    down[up[k]] = down[k];
                    k = n < down[k] ? up[k] : down[k];
                } else if (c.startsWith("Z")) { // 6. 가장 최근에 삭제된 행을 복원
                    int restore = deleted.pop();
                    down[up[restore]] = restore;
                    up[down[restore]] = restore;
                } else {    // 7. U 또는 D를 사용해 현재 위치를 위, 아래로 이동
                    String[] s = c.split(" ");
                    int x = Integer.parseInt(s[1]);
                    for (int i = 0; i < x; i++) {
                        k = s[0].equals("U") ? up[k] : down[k];
                    }
                }
            }

            // 8. 삭제된 행의 위치에 'X'를, 그렇지 않은 행 위치에는 'O'를 저장한 문자열 반환
            char[] answer = new char[n];
            Arrays.fill(answer, '0');

            for (int i : deleted) {
                answer[i - 1] = 'X';
            }

            return new String(answer);
        }
    }
}
