package cote_succ;

import java.util.*;
import java.util.stream.Collectors;

public interface ArrayListQuestions {

    class ArrayListQuestionMain_05_5_03 {
        public static void main(String[] args) {
            int[] result1 = solution(new int[]{2, 1, 3, 4, 1});
            System.out.println(Arrays.toString(result1));
            int[] result2 = solution(new int[]{5, 0, 2, 7});
            System.out.println(Arrays.toString(result2));
        }

        public static int[] solution(int[] members) {
            HashSet<Integer> set = new HashSet<>();
            for (int i = 0; i < members.length; i++) {
                for (int j = i + 1; j < members.length; j++) {
                    set.add(members[i] + members[j]);
                }
            }
            return set.stream().sorted().mapToInt(Integer::intValue).toArray();
        }
    }

    class ArrayListQuestionMain_05_5_04 {
        public static void main(String[] args) {
            int[] result1 = solution(new int[]{1, 2, 3, 4, 5});
            System.out.println("result1 = " + Arrays.toString(result1));
            int[] result2 = solution(new int[]{1, 3, 2, 4, 2});
            System.out.println("result2 = " + Arrays.toString(result2));

            /*
            int temp0 = 0 % 5;
            System.out.println("temp0 = " + temp0);
            int temp1 = 1 % 5;
            System.out.println("temp1 = " + temp1);
            int temp2 = 2 % 5;
            System.out.println("temp2 = " + temp2);
            int temp3 = 3 % 5;
            System.out.println("temp3 = " + temp3);
            int temp4 = 4 % 5;
            System.out.println("temp4 = " + temp4);
            int temp5 = 5 % 5;
            System.out.println("temp5 = " + temp5);
            int temp6 = 6 % 5;
            System.out.println("temp6 = " + temp6);
            int temp7 = 7 % 5;
            System.out.println("temp7 = " + temp7);
            int temp8 = 8 % 5;
            System.out.println("temp8 = " + temp8);
            int temp9 = 9 % 5;
            System.out.println("temp9 = " + temp9);
            int temp10 = 10 % 5;
            System.out.println("temp10 = " + temp10);
            int temp11 = 11 % 5;
            System.out.println("temp11 = " + temp11);
            */
        }

        public static int[] mySolution(int[] answers) {
            int[] num1 = {1, 2, 3, 4, 5};
            int[] num2 = {2, 1, 2, 3, 2, 4, 2, 5};
            int[] num3 = {3, 3, 1, 1, 2, 2, 4, 5, 5, 5};
            int index1 = 0, index2 = 0, index3 = 0;
            int answer1 = 0, answer2 = 0, answer3 = 0;
            for (int i = 0; i < answers.length; i++) {
                if (answers[i] == num1[index1]) {
                    answer1++;
                }
                if (answers[i] == num2[index2]) {
                    answer2++;
                }
                if (answers[i] == num3[index3]) {
                    answer3++;
                }
                index1++;
                if (index1 == num1.length) {
                    index1 = 0;
                }
                index2++;
                if (index2 == num2.length) {
                    index2 = 0;
                }
                index3++;
                if (index3 == num3.length) {
                    index3 = 0;
                }
            }
            int[] arrAnswer = {answer1, answer2, answer3};
            int max = Arrays.stream(arrAnswer).max().getAsInt();
            List<Integer> result = new ArrayList<>();
            for (int i = 0; i < arrAnswer.length; i++) {
                if (arrAnswer[i] == max) {
                    result.add(i + 1);
                }
            }
            return result.stream().mapToInt(Integer::intValue).toArray();
        }

        public static int[] solution(int[] answers) {
            // 1. 수포자들의 패턴
            int[][] pattern = {
                    {1, 2, 3, 4, 5},
                    {2, 1, 2, 3, 2, 4, 2, 5},
                    {3, 3, 1, 1, 2, 2, 4, 5, 5, 5}
            };
            // 2. 수포자들의 점수를 저장할 배열
            int[] scores = new int[3];

            // 3. 각 수포자의 패턴과 정답이 얼마나 일치하는지 확인
            for (int i = 0; i < answers.length; i++) {
                for (int j = 0; j < pattern.length; j++) {
                    if (answers[i] == pattern[j][i % pattern[j].length]) {
                        scores[j]++;
                    }
                }
            }

            // 4. 가장 높은 점수 저장
            int maxScore = Arrays.stream(scores).max().getAsInt();
            // 5. 가장 높은 점수를 가진 수포자들의 번호를 찾아서 리스트에 담음
            ArrayList<Integer> answer = new ArrayList<>();
            for (int i = 0; i < scores.length; i++) {
                if (scores[i] == maxScore) {
                    answer.add(i + 1);
                }
            }
            return answer.stream().mapToInt(Integer::intValue).toArray();
        }

    }

    class ArrayListQuestionMain_05_5_05 {
        public static void main(String[] args) {
            int[][] arr1 = {
                    {1, 4},
                    {3, 2},
                    {4, 1}
            };
            int[][] arr2 = {
                    {3, 3},
                    {3, 3},
            };
            int[][] result = solution(arr1, arr2);
            for (int[] r : result) {
                System.out.println(Arrays.toString(r));
            }
            int[][] arr3 = {
                    {2, 3, 2},
                    {4, 2, 4},
                    {3, 1, 4}
            };
            int[][] arr4 = {
                    {5, 4, 3},
                    {2, 4, 1},
                    {3, 1, 1}
            };
            int[][] result2 = solution(arr3, arr4);
            for (int[] r : result2) {
                System.out.println(Arrays.toString(r));
            }
        }

        private static int[][] mySolution(int[][] arr1, int[][] arr2) {
            int[][] result = new int[arr1.length][arr1[0].length];
            for (int i = 0; i < arr1.length; i++) {
                for (int j = 0; j < arr1[i].length; j++) {
                    for (int k = 0; k < arr2.length; k++) {
                        result[i][j] += arr1[i][k] * arr2[k][j];
                    }
//                    System.out.println("arr1[" + i + "][" + j + "] = " + result[i][j]);
                }
            }
            return result;
        }

        private static int[][] solution(int[][] arr1, int[][] arr2) {
            // 1. 행렬 arr1과 arr2의 행과 열의 수
            int r1 = arr1.length;
            int c1 = arr1[0].length;
            int r2 = arr2.length;
            int c2 = arr2[0].length;

            // 2. 결과를 저장할 2차원 배열 초기화
            int[][] answer = new int[r1][c1];

            // 3. 첫 번째 행렬 arr1의 각 행과 두 번째 행렬 arr2의 각 열에 대해
            for (int i = 0; i < r1; i++) {
                for (int j = 0; j < c2; j++) {
                    // 4. 두 행렬의 데이터를 곱해 결과 리스트에 더함
                    for (int k = 0; k < c1; k++) {
                        answer[i][j] += arr1[i][k] * arr2[k][j];
                    }
                }
            }
            return answer;
        }
    }

    class ArrayListQuestionMain_05_5_06 {
        public static void main(String[] args) {
            int[] n1 = {2, 1, 2, 6, 2, 4, 3, 3};
            int N1 = 5;
            int[] n2 = {4, 4, 4, 4, 4};
            int N2 = 4;
            int[] result1 = solution(N1, n1);
            int[] result11 = mySolution(N1, n1);
            System.out.println("result1 = " + Arrays.toString(result1));
            System.out.println("result11 = " + Arrays.toString(result11));
            int[] result2 = solution(N2, n2);
            int[] result22 = mySolution(N2, n2);
            System.out.println("result2 = " + Arrays.toString(result2));
            System.out.println("result22 = " + Arrays.toString(result22));
        }

        private static int[] mySolution(int N, int[] stages) {
            // map 실패율:스테이지번호
            // 2중 for문
            double stageSize = stages.length;
            HashMap<Double, List<Integer>> map = new HashMap<>();
            for (int i = 1; i <= N; i++) {
                double failCount = 0;
                for (int j = 0; j < stages.length; j++) {
                    if (i == stages[j]) {
                        failCount += 1;
                    }
                }
//                System.out.println("failCount = " + failCount);
//                System.out.println("stageSize = " + stageSize);
                double failRate = failCount / stageSize;
                if (map.containsKey(failRate)) {
                    map.get(failRate).add(i);
                } else {
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(i);
                    map.put(failRate, list);
                }
                stageSize -= failCount;
            }
//            System.out.println("map = " + map);
            List<Integer> result = new ArrayList<>();
            List<Double> failOrder = map.keySet().stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
            for (Double f : failOrder) {
                result.addAll(map.get(f));
            }
            return result.stream().mapToInt(Integer::intValue).toArray();
        }

        private static int[] solution(int N, int[] stages) {
            // 1. 스테이지별 도전자 수를 구함
            int[] challenger = new int[N + 2];  // 0 인덱스를 안쓰는 전략, 1부터 시작하기 위해 -> 값 자체를 인덱스로 활용
            for (int i = 0; i < stages.length; i++) {
                challenger[stages[i]] += 1;
            }
//            System.out.println("challenger : " + Arrays.toString(challenger));

            // 2. 스테이지별 실패한 사용자 수 계산
            HashMap<Integer, Double> fails = new HashMap<>();
            double total = stages.length;

            // 3. 각 스테이지를 순회하며, 실패율 계산
            for (int i = 1; i <= N; i++) {
                if (challenger[i] == 0) {   // 4. 도전할 사람이 없는 경우, 실패율은 0
                    fails.put(i, 0.);
                } else {
                    fails.put(i, challenger[i] / total);    // 5. 실패율 구함
                    total -= challenger[i];     // 6. 다음 스테이지 실패율을 구하기 위해 현재 스테이지의 인원을 뺌
                }
            }

            // 7. 실패율이 높은 스테이지부터 내림차순 정렬
            return fails.entrySet()
                    .stream()
                    .sorted(((o1, o2) -> Double.compare(o2.getValue(), o1.getValue())))
                    .mapToInt(Map.Entry::getKey).toArray();
        }

    }

    class ArrayListQuestionMain_05_5_07 {
        public static void main(String[] args) {
            // 문제 07 방문 길이
            String dirs1 = "ULURRDLLU";
            int result1 = solution(dirs1);
            System.out.println("result1 = " + result1);
            String dirs2 = "LULLLLLLU";
            int result2 = solution(dirs2);
            System.out.println("result2 = " + result2);
            String dirs3 = "L";
            int result3 = solution(dirs3);
            System.out.println("result3 = " + result3);
        }

        private static int mySolutionFail(String dirs) {
            // 2차원 배열로 판을 구성
            // 이동할 때마다 현재 위치를 기록
            // 이동할 때마다 경계를 넘어가는지 체크
            // 이동할 때마다 이동한 길 기록
            // true인 개수 출력
            // -5_-5_-5_-4
            // -5_-5_-4_-5
            HashMap<String, Boolean> map = new HashMap<>();
            for (int x = -5; x <= 5; x++) {
                for (int y = -5; y <= 5; y++) {
                    if (y + 1 < 6) {
                        if (!map.containsKey(x + "_" + (y + 1) + "_" + x + "_" + y)) {
                            map.put(x + "_" + y + "_" + x + "_" + (y + 1), false);
                        }
                    }
                    if (x + 1 < 6) {
                        if (!map.containsKey((x + 1) + "_" + y + "_" + x + "_" + y)) {
                            map.put(x + "_" + y + "_" + (x + 1) + "_" + y, false);
                        }
                    }
                    if (y - 1 > -6) {
                        if (!map.containsKey(x + "_" + (y - 1) + "_" + x + "_" + y)) {
                            map.put(x + "_" + y + "_" + x + "_" + (y - 1), false);
                        }
                    }
                    if (x - 1 > -6) {
                        if (!map.containsKey((x - 1) + "_" + y + "_" + x + "_" + y)) {
                            map.put(x + "_" + y + "_" + (x - 1) + "_" + y, false);
                        }
                    }
                }
            }
            System.out.println("map = " + map);
            int startX = 0, startY = 0;
            String key1 = "", key2 = "";
            char[] chars = dirs.toCharArray();
            for (char cha : chars) {
                switch (cha) {
                    case 'L':
                        key1 = (startX - 1) + "_" + startY + "_" + startX + "_" + startY;
                        key2 = startX + "_" + startY + "_" + (startX - 1) + "_" + startY;
                        startX -= 1;
                        break;
                    case 'R':
                        key1 = (startX + 1) + "_" + startY + "_" + startX + "_" + startY;
                        key2 = startX + "_" + startY + "_" + (startX + 1) + "_" + startY;
                        startX += 1;
                        break;
                    case 'U':
                        key1 = startX + "_" + startY + "_" + startX + "_" + (startY + 1);
                        key2 = startX + "_" + (startY + 1) + "_" + startX + "_" + startY;
                        startY += 1;
                        break;
                    case 'D':
                        key1 = startX + "_" + startY + "_" + startX + "_" + (startY - 1);
                        key2 = startX + "_" + (startY - 1) + "_" + startX + "_" + startY;
                        startY -= 1;
                        break;
                }
                if (map.containsKey(key1)) {
                    map.put(key1, true);
                }
                if (map.containsKey(key2)) {
                    map.put(key2, true);
                }
            }
            return (int) map.entrySet().stream().filter(Map.Entry::getValue).count();
        }

        private static int mySolution(String dirs) {
            LinkedHashSet<String> set = new LinkedHashSet<>();
            char[] chars = dirs.toCharArray();
            int x = 0, y = 0;
            String key1 = "", key2 = "";
            for (char cha : chars) {
                switch (cha) {
                    case 'L':
                        if (!(x - 1 < -5)) {
                            key1 = "" + (x - 1) + y + x + y;
                            key2 = "" + x + y + (x - 1) + y;
                            x -= 1;
                        }
                        break;
                    case 'R':
                        if (!(x + 1 > 5)) {
                            key1 = "" + (x + 1) + y + x + y;
                            key2 = "" + x + y + (x + 1) + y;
                            x += 1;
                        }
                        break;
                    case 'U':
                        if (!(y + 1 > 5)) {
                            key1 = "" + x + (y + 1) + x + y;
                            key2 = "" + x + y + x + (y + 1);
                            y += 1;
                        }
                        break;
                    case 'D':
                        if (!(y - 1 < -5)) {
                            key1 = "" + x + (y - 1) + x + y;
                            key2 = "" + x + y + x + (y - 1);
                            y -= 1;
                        }
                        break;
                }
                if (!set.contains(key2))
                    set.add(key1);
            }
            System.out.println("set = " + set);
            return set.size();
        }

        private static int solution(String dirs) {
            initLocation();
            int x = 5, y = 5;
            HashSet<String> answer = new HashSet<>();   // 3. 겹치는 좌표는 1개로 처리하기 위함
            // 4. 주어진 명령어로 움직이면서 좌표 저장
            for (int i = 0; i < dirs.length(); i++) {
                int[] offset = location.get(dirs.charAt(i));
                int nx = x + offset[0];
                int ny = y + offset[1];
                if (!isValidMove(nx, ny))   // 5. 벗어난 좌표는 인정하지 않음
                    continue;
                // 6. A에서 B로 간 경우 B에서 A도 추가해야 함(총 경로의 개수는 방향성이 없음)
                answer.add(x + "" + y + "" + nx + "" + ny);
                answer.add(nx + "" + ny + "" + x + "" + y);
                // 7. 좌표를 이동했으므로 업데이트
                x = nx;
                y = ny;
            }
            return answer.size() / 2;
        }

        // 2. 다음 좌표 결정을 위한 해시맵 생성
        private static final HashMap<Character, int[]> location = new HashMap<>();

        private static void initLocation() {
            location.put('U', new int[]{0, 1});
            location.put('D', new int[]{0, -1});
            location.put('L', new int[]{-1, 0});
            location.put('R', new int[]{1, 0});
        }

        // 1. 좌표평면을 벗어나는지 체크하는 메서드 (해당 메서드는 좌표 문제에 단골로 등장)
        private static boolean isValidMove(int nx, int ny) {
            return 0 <= nx && nx < 11 && 0 <= ny && ny < 11;
        }


    }


}
