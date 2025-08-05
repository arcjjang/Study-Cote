package this_is_cote;

import java.util.*;
import java.util.stream.Collectors;

public interface SortProblems {

    // CHAPTER 14 : 정렬 문제
    // 23 국영수
    // https://www.acmicpc.net/problem/10825
    class SortProblemsMain14_23 {
        public static void main(String[] args) {
            List<Student> students = Arrays.asList(
                    new Student("Junkyu", 50, 60, 100),
                    new Student("Sangkeun", 80, 60, 50),
                    new Student("Sunyoung", 80, 70, 100),
                    new Student("Soong", 50, 60, 90),
                    new Student("Haebin", 50, 60, 100),
                    new Student("Kangsoo", 60, 80, 100),
                    new Student("Donghyuk", 80, 60, 100),
                    new Student("Sei", 70, 70, 70),
                    new Student("Wonseob", 70, 70, 90),
                    new Student("Sanghyun", 70, 70, 80),
                    new Student("nsj", 80, 80, 80),
                    new Student("Taewhan", 50, 60, 90)
            );
//            students.sort((s1, s2) -> {
//                if (s1.korean != s2.korean) {
//                    return s2.korean - s1.korean;
//                } else if (s1.english != s2.english) {
//                    return s1.english - s2.english;
//                } else if (s1.math != s2.math) {
//                    return s2.math - s1.math;
//                } else {
//                    return s1.name.compareTo(s2.name);
//                }
//            });
//            for (Student student : students) {
//                System.out.println(student.name);
//            }

            List<String> result = solution(students);
            result.forEach(System.out::println);
        }

        static public class Student {
            String name;
            int korean;
            int english;
            int math;

            public Student(String name, int korean, int english, int math) {
                this.name = name;
                this.korean = korean;
                this.english = english;
                this.math = math;
            }

            public String getName() {
                return name;
            }

            public int getKorean() {
                return korean;
            }

            public int getEnglish() {
                return english;
            }

            public int getMath() {
                return math;
            }
        }

        public static List<String> solution(List<Student> students) {
            return students.stream()
                    .sorted(Comparator.comparing(Student::getKorean).reversed() // 국어 점수 내림차순
                            .thenComparing(Student::getEnglish) // 영어 점수 오름차순
                            .thenComparing(Comparator.comparing(Student::getMath).reversed()) // 수학 점수 내림차순
                            .thenComparing(Student::getName)) // 이름 오름차순
                    .map(Student::getName)
                    .toList();
        }
    }

    // 24 안테나
    class SortProblemsMain14_24 {
        public static void main(String[] args) {
//            int[] houses = {5, 1, 7, 9};
            int[] houses = {1, 2, 5, 9};
            int result = solution(houses);
            System.out.println(result);
        }

        public static int solution(int[] houses) {
            Arrays.sort(houses);
            return houses[(houses.length - 1) / 2];     // 중앙값
        }
    }

    // 25 실패율
    // https://school.programmers.co.kr/learn/courses/30/lessons/42889#
    class SortProblemsMain14_25 {
        public static void main(String[] args) {
            int N1 = 5;
            int[] n1 = {2, 1, 2, 6, 2, 4, 3, 3};
            int N2 = 4;
            int[] n2 = {4, 4, 4, 4, 4};
            int[] result1 = solution(N1, n1);
            int[] result2 = solution(N2, n2);
            System.out.println("result1 = " + Arrays.toString(result1));
            System.out.println("result2 = " + Arrays.toString(result2));
        }

        // 실패율 = (해당 스테이지에 도달했지만 실패한 수) / (해당 스테이지에 도달한 수)
        private static int[] solution(int N, int[] stages) {
            // 1. 스테이지별 도전자 수를 구함
            int[] challenger = new int[N + 2];  // 0 인덱스를 안쓰는 전략, 1부터 시작하기 위해 -> 값 자체를 인덱스로 활용
            for (int i = 0; i < stages.length; i++) {
                challenger[stages[i]] += 1;
            }

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

    // 26 카드 정렬하기
    class SortProblemsMain14_26 {
        public static void main(String[] args) {
            int[] n = {10, 20, 40};
            int result = solution(n);
            System.out.println(result);
        }

        public static int solution(int[] n) {
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            for (int i = 0; i < n.length; i++) {
                pq.add(n[i]);
            }

            int total = 0;

            while (pq.size() > 1) {
                int first = pq.poll();
                int second = pq.poll();

                int sum = first + second;
                total += sum;

                pq.add(sum);
            }

            return total;
        }

    }

    // CHAPTER 15 : 이진 탐색 문제
    // 27 정렬된 배열에서 특정 수의 개수 구하기
    class SortProblemsMain15_27 {
        public static void main(String[] args) {
            int x1 = 2;
            int[] arr1 = {1, 1, 2, 2, 2, 2, 3};
            int result1 = solution(arr1, x1);
            System.out.println("result1 = " + result1); // 4
            int x2 = 4;
            int[] arr2 = {1, 1, 2, 2, 2, 2, 3};
            int result2 = solution(arr2, x2);
            System.out.println("result2 = " + result2); // -1
        }

        public static int solution(int[] arr, int x) {
            int lower = lowerBound(arr, x); // x 이상의 첫 번째 위치
            int upper = upperBound(arr, x); // x 초과의 첫 번째 위치
            int count = upper - lower;   // 개수 = upper - lower
            return count > 0 ? count : -1;
        }

        // lowerBound: x 이상의 값이 처음으로 등장하는 위치를 찾음
        public static int lowerBound(int[] arr, int x) {
            int low = 0;
            int high = arr.length;

            while (low < high) {
                int mid = (low + high) / 2;
                if (arr[mid] >= x) {
                    high = mid; // 범위를 왼쪽으로 축소
                } else {
                    low = mid + 1; // 범위를 오른쪽으로 이동
                }
            }

            return low;
        }

        // upperBound: x 초과의 값이 처음으로 등장하는 위치를 찾음
        public static int upperBound(int[] arr, int x) {
            int low = 0;
            int high = arr.length;

            while (low < high) {
                int mid = (low + high) / 2;
                if (arr[mid] > x) {
                    high = mid; // 범위를 왼쪽으로 축소
                } else {
                    low = mid + 1; // 범위를 오른쪽으로 이동
                }
            }

            return low;
        }
    }

    // 28 고정점 찾기
    class SortProblemsMain15_28 {
        public static void main(String[] args) {
            int[] arr1 = {-15, -6, 1, 3, 7};
            int result1 = solution(arr1);
            System.out.println("result1 = " + result1);
            int[] arr2 = {-15, -4, 2, 8, 9, 13, 15};
            int result2 = solution(arr2);
            System.out.println("result2 = " + result2);
            int[] arr3 = {-15, -4, 3, 8, 9, 13, 15};
            int result3 = solution(arr3);
            System.out.println("result3 = " + result3);
        }

        // 고정점을 찾는 이진 탐색 함수
        public static int solution(int[] arr) {
            int low = 0;
            int high = arr.length - 1;

            while (low <= high) {
                int mid = (low + high) / 2;

                // 고정점 발견
                if (arr[mid] == mid) {
                    return mid;
                }

                // 배열 값이 현재 인덱스보다 작으면 오른쪽으로 탐색
                if (arr[mid] < mid) {
                    low = mid + 1;
                }
                // 배열 값이 현재 인덱스보다 크면 왼쪽으로 탐색
                else {
                    high = mid - 1;
                }
            }

            // 고정점이 없는 경우
            return -1;
        }
    }

    // 29 공유기 설치
    class SortProblemsMain15_29 {
        public static void main(String[] args) {
            int[] houses1 = {1, 2, 8, 4, 9};
            int c = 3;
            int result1 = solution(houses1, c);
            System.out.println("result1 = " + result1);
        }

        public static int solution(int[] houses, int c) {
            Arrays.sort(houses); // 집을 좌표 기준으로 정렬

            // 거리 범위 설정
            int low = 1; // 최소 거리
            int high = houses[houses.length - 1] - houses[0]; // 최대 거리
            int result = 0; // 최대 거리 결과 저장

            // 이진 탐색 수행
            while (low <= high) {
                int mid = (low + high) / 2;

                if (canInstall(houses, mid, c)) {
                    result = mid; // 설치 가능 -> 결과 저장
                    low = mid + 1; // 더 큰 거리 탐색
                } else {
                    high = mid - 1; // 더 작은 거리 탐색
                }
            }

            return result;
        }

        // 특정 거리 d로 공유기가 C개 설치 가능한지 확인
        public static boolean canInstall(int[] houses, int d, int C) {
            int count = 1; // 첫 번째 공유기를 설치
            int prevHouse = houses[0];

            for (int i = 1; i < houses.length; i++) {
                if (houses[i] - prevHouse >= d) {
                    count++; // 공유기 설치
                    prevHouse = houses[i]; // 현재 집에 공유기 설치
                }

                if (count >= C) {
                    return true; // 공유기 C개 설치 완료
                }
            }

            return false;
        }
    }

    // 30 가사 검색
    //https://school.programmers.co.kr/learn/courses/30/lessons/60060
}
