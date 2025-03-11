package cote_succ;

import java.util.*;
import java.util.stream.Stream;

public interface HashQuestions {
    class HashQuestionMain_08_02 {
        public static void main(String[] args) {
            // HashMap<KeyType, ValueType>입니다.
            HashMap<String, Integer> hashMap = new HashMap<>();

            // hashMap에 데이터 추가
            hashMap.put("ABC", 10);
            hashMap.put("BBB", 20);
            hashMap.put("AAA", 30);
            hashMap.put("ABC", 15);

            System.out.println(hashMap.isEmpty());  // false
            System.out.println(hashMap.get("ABC")); // 15
            System.out.println(hashMap.containsKey("ABC")); // true

            hashMap.remove("ABC"); // hashMap에서 키가 "ABC"인 데이터 제거
            System.out.println(hashMap.size()); // 2

            hashMap.clear();    // 해시맵의 모든 데이터 삭제
            System.out.println(hashMap.isEmpty());  // true
        }
    }

    class HashQuestionMain_08_04_01 {
        public static void main(String[] args) {
            int[] arr = {1, 2, 3, 4, 8};
            int target = 6;
            boolean result = solution(arr, target);
            System.out.println("result = " + result);
            int[] arr2 = {2, 3, 5, 9};
            int target2 = 10;
            boolean result2 = solution(arr2, target2);
            System.out.println("result2 = " + result2);
        }

        private static boolean mySolution(int[] arr, int target) {
            // arr size만큼 for문
            // target - arr[i] 의 값을 set에서 찾음
            // 없으면 set에 add
            // 있으면 return true
            HashSet<Integer> set = new HashSet<>();
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] >= target) {
                    continue;
                }
                int reqNum = target - arr[i];
                if (set.contains(reqNum)) {
                    return true;
                }
                {
                    set.add(arr[i]);
                }
            }
            return false;
        }

        private static boolean solution(int[] arr, int target) {
            HashSet<Integer> hashSet = new HashSet<>();

            for (int i : arr) {
                // 1. target에서 현재 원소를 뺀 값이 해시셋 있는지 확인
                if (hashSet.contains(target - i)) {
                    return true;
                }
                // 2. 해시셋 현재 값 저장
                hashSet.add(i);
            }

            return false;
        }
    }

    class HashQuestionMain_08_05_01 {
        public static void main(String[] args) {
            String[] participant = {"leo", "kiki", "eden"};
            String[] completion = {"kiki", "eden"};
            String result = solution(participant, completion);
            System.out.println("result = " + result);
            String[] participant2 = {"mislav", "stanko", "mislav", "ana"};
            String[] completion2 = {"stanko", "ana", "mislav"};
            String result2 = solution(participant2, completion2);
            System.out.println("result2 = " + result2);
        }

        private static String mySolution(String[] participant, String[] completion) {
            HashSet<String> hashSet = new HashSet<>();
            for (int i = 0; i < participant.length; i++) {
                hashSet.add(participant[i]);
            }
            for (int i = 0; i < completion.length; i++) {
                hashSet.remove(completion[i]);
            }
            return hashSet.iterator().next();
        }

        private static String solution(String[] participant, String[] completion) {
            // 1. 해시맵 생성
            HashMap<String, Integer> hashMap = new HashMap<>();
            // 2. 완주한 선수들의 이름을 해시맵에 저장
            for (String s : completion) {
                hashMap.put(s, hashMap.getOrDefault(s, 0) + 1);
            }
            // 3. 참가한 선수들의 이름을 키로 하는 값을 1씩 감소
            for (String s : participant) {
                // 4. 완주하지 못한 선수를 찾으면 반환
                if (hashMap.getOrDefault(s, 0) == 0) {
                    return s;
                }
                hashMap.put(s, hashMap.get(s) - 1);
            }
            return null;
        }

    }

    class HashQuestionMain_08_05_02 {
        public static void main(String[] args) {
            String[] want = {"banana", "apple", "rice", "pork", "pot"};
            int[] number = {3, 2, 2, 2, 1};
            String[] discount = {"chicken", "apple", "apple", "banana", "rice", "apple", "pork", "banana", "pork", "rice", "pot", "banana", "apple", "banana"};
            int result = solution(want, number, discount);
            System.out.println("result = " + result);   // 3
            String[] want2 = {"apple"};
            int[] number2 = {10};
            String[] discount2 = {"banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana"};
            int result2 = solution(want2, number2, discount2);
            System.out.println("result2 = " + result2);   // 0
        }

        private static int mySolution(String[] want, int[] number, String[] discount) {
            HashMap<String, Integer> wantMap = new HashMap<>();
            for (int i = 0; i < want.length; i++) {
                wantMap.put(want[i], number[i]);
            }

            HashMap<String, Integer> discountMap = new HashMap<>();
            for (int i = 0; i < 10; i++) {
                discountMap.put(discount[i], discountMap.getOrDefault(discount[i], 0) + 1);
            }
            int count = 0;
            for (int i = 10; i < discount.length; i++) {
                System.out.println("wantMap = " + wantMap);
                System.out.println("discountMap = " + discountMap);
                count = isSameMap(wantMap, discountMap) ? count + 1 : count;
                discountMap.put(discount[i], discountMap.getOrDefault(discount[i], 0) + 1);
                discountMap.put(discount[i - 10], discountMap.getOrDefault(discount[i - 10], 0) - 1);
            }
            System.out.println("wantMap = " + wantMap);
            System.out.println("discountMap = " + discountMap);
            count = isSameMap(wantMap, discountMap) ? count + 1 : count;    // 마지막에 한 번 더 체크
            return count;
        }

        private static boolean isSameMap(HashMap<String, Integer> wantMap, HashMap<String, Integer> discountMap) {
            for (String key : wantMap.keySet()) {
                if (!discountMap.containsKey(key) || discountMap.get(key) < wantMap.get(key)) {
                    return false;
                }
            }
            return true;
        }

        private static int solution(String[] want, int[] number, String[] discount) {
            // 1. want, number배열의 값을 해시맵에 저장
            HashMap<String, Integer> wantMap = new HashMap<>();
            for (int i = 0; i < want.length; i++) {
                wantMap.put(want[i], number[i]);
            }
            int answer = 0; // 2. 총 일수를 계산할 변수 초기화

            // 3. 특정일 i에 회원가입 시 할인받을 수 있는 품목 체크
            for (int i = 0; i < discount.length - 9; i++) {
                // 4. i일 회원가입 시 할인받는 제품 및 개수를 담을 해시맵
                HashMap<String, Integer> discount10d = new HashMap<>();

                // 5. i일 회원가입 시 할인받는 제품 및 개수로 해시맵 구성
                for (int j = i; j < i + 10; j++) {
                    if (wantMap.containsKey(discount[j])) {
                        discount10d.put(discount[j], discount10d.getOrDefault(discount[j], 0) + 1);
                    }
                }
                // 6. 할인하는 상품의 개수가 원하는 수량과 일치하면 정답 변수에 1 추가
                if (discount10d.equals(wantMap)) {
                    answer++;
                }
            }

            return answer;
        }

    }

    class HashQuestionMain_08_05_03 {
        public static void main(String[] args) {
            String[] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234", "Enter uid1234 Prodo", "change uid4567 Ryan"};
            String[] result = solution(record);
            for (String s : result) {
                System.out.println("s = " + s); // Prodo님이 들어왔습니다., Ryan님이 들어왔습니다., Prodo님이 나갔습니다., Prodo님이 들어왔습니다.
            }
        }

        private static String[] mySolution(String[] record) {
            // 1. map (uid, nick)
            // 2. for
            HashMap<String, String> nickMap = new HashMap<>();
            ArrayList<String> newRecords = new ArrayList<>();
            for (String r : record) {
                String[] subRecord = r.split(" ");
                switch (subRecord[0]) {
                    case "Enter":
                        nickMap.put(subRecord[1], subRecord[2]);
                        newRecords.add(subRecord[1] + "님이 들어왔습니다.");
                        break;
                    case "change":
                        nickMap.put(subRecord[1], subRecord[2]);
                        break;
                    case "Leave":
                        newRecords.add(subRecord[1] + "님이 나갔습니다.");
                        break;
                }
            }

            // newRecords.get(0).substring(0, newRecords.get(0).indexOf("님"))
            return newRecords.stream().map(s -> nickMap.get(s.substring(0, s.indexOf("님"))) + s.substring(s.indexOf("님"))).toArray(String[]::new);
        }

        private static String[] solution(String[] record) {
            // Enter/Leave 메시지를 저장할 해시맵 생성
            HashMap<String, String> msg = new HashMap<>();
            msg.put("Enter", "님이 들어왔습니다.");
            msg.put("Leave", "님이 나갔습니다.");

            HashMap<String, String> uid = new HashMap<>();

            // 1. record의 각 줄을 하나씩 처리
            for (String s : record) {
                String[] cmd = s.split(" ");
                if (cmd.length == 3) {  // 2. Enter 또는 Change인 경우
                    uid.put(cmd[1], cmd[2]);
                }
            }

            // 답을 저장할 answer List 생성
            ArrayList<String> answer = new ArrayList<>();
            // 3. record의 각 줄을 하나씩 처리
            for (String s : record) {
                String[] cmd = s.split(" ");
                // 4. 각 상태에 맞는 메시지를 answer에 저장
                if (msg.containsKey(cmd[0])) {
                    answer.add(uid.get(cmd[1]) + msg.get(cmd[0]));
                }
            }

            return answer.toArray(new String[0]);
        }

    }

    class HashQuestionMain_08_05_04 {
        public static void main(String[] args) {
            String[] genres = {"classic", "pop", "classic", "classic", "pop"};
            int[] plays = {500, 600, 150, 800, 2500};
            int[] result = solution(genres, plays);   // 4, 1, 3, 0
            for (int i : result) {
                System.out.println("i = " + i);
            }
        }

        private static int[] mySolution(String[] genres, int[] plays) {
            // map 0(key:장르, value:재생횟수) -->
            // 모든 장르는 재생된 횟수가 다르다면 정렬을 위해 map1(key:재생횟수, value:장르)
            // 같은 장르에서도 곡 정렬을 위해 map2(key:장르, value:곡 재생횟수)
            // 곡 정렬 후 곡 순서를 알기 위해 map3(key:곡 재생횟수, value:곡 번호)
            HashMap<String, Integer> map0 = new HashMap<>();
            HashMap<Integer, String> map1 = new HashMap<>();
            HashMap<String, ArrayList<Integer>> map2 = new HashMap<>();
            HashMap<Integer, Integer> map3 = new HashMap<>();   // 이 부분이 잘못됨...
            for (int i = 0; i < genres.length; i++) {
                map0.put(genres[i], map0.getOrDefault(genres[i], 0) + plays[i]);
                ArrayList<Integer> playsList = map2.getOrDefault(genres[i], new ArrayList<>());
                playsList.add(plays[i]);
                map2.put(genres[i], playsList);
                map3.put(plays[i], i);
            }
            for (String key : map0.keySet()) {
                map1.put(map0.get(key), key);
            }
            System.out.println("map0 = " + map0);
            System.out.println("map1 = " + map1);
            System.out.println("map2 = " + map2);
            System.out.println("map3 = " + map3);
            int[] sortedKey1 = map1.keySet().stream().sorted(Comparator.reverseOrder()).mapToInt(Integer::intValue).toArray();
            ArrayList<Integer> result = new ArrayList<>();
            for (int i = 0; i < sortedKey1.length; i++) {
                map2.get(map1.get(sortedKey1[i])).stream().sorted(Comparator.reverseOrder()).mapToInt(Integer::intValue).limit(2).map(map3::get).forEach(result::add);
            }
            return result.stream().mapToInt(Integer::intValue).toArray();
        }

        private static int[] solution(String[] genres, int[] plays) {
            // 총 재생 횟수를 기준으로 장르를 내림차순으로 정렬
            // 각 장르별로 2곡씩 선정해서 플레이리스트 만들기
            // 재생 횟수가 같은 경우 고유 번호순으로 정렬(놓친 부분)
            HashMap<String, ArrayList<int[]>> genreMap = new HashMap<>();   // 팁 : 곡의 재생 횟수와 곡의 인덱스를 int[]로 묶어서 저장
            HashMap<String, Integer> playMap = new HashMap<>();

            // 1. 장르별 총 재생 횟수와 각 곡의 재생 횟수 저장
            for (int i = 0; i < genres.length; i++) {
                String genre = genres[i];
                int play = plays[i];
                if (!genreMap.containsKey(genre)) {
                    genreMap.put(genre, new ArrayList<>());
                    playMap.put(genre, play);
                }
                genreMap.get(genre).add(new int[]{i, play});    // 정리하자면 고유 번호가 낮은 순서대로 ArrayList에 데이터를 삽입했으므로
                playMap.put(genre, playMap.get(genre) + play);
            }

            ArrayList<Integer> answer = new ArrayList<>();

            // 2. 총 재생 횟수가 많은 장르순으로 내림차순 정렬
            Stream<Map.Entry<String, Integer>> sortedGenre = playMap.entrySet() // 팁 : entrySet 으로 정렬하면 매핑을 유지할 수 있다.
                    .stream()
                    .sorted((o1, o2) -> Integer.compare(o2.getValue(), o1.getValue()));

            // 3. 각 장르 내에서 노래를 재생 횟수 순으로 정렬해 최대 2곡까지 선택
            sortedGenre.forEach(entry -> {
                Stream<int[]> sortedSongs = genreMap.get(entry.getKey()).stream()
                        .sorted((o1, o2) -> Integer.compare(o2[1], o1[1]))
                        .limit(2);
                sortedSongs.forEach(song -> answer.add(song[0]));
            }); // 코드에서는 고유 번호가 낮은 순으로 정렬하는 것이 없는 것처럼 보이지만 sorted는 순서가 있는 스트림을 정렬할 때는 기존 순서가 유지되는 stable sort로 정렬

            return answer.stream().mapToInt(Integer::intValue).toArray();
        }
    }

    class HashQuestionMain_08_05_05 {
        public static void main(String[] args) {
            String[] id_list = {"muzi", "frodo", "apeach", "neo"};
            String[] report = {"muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"};
            int k = 2;
            // 각 유저별로 처리 결과 메일을 받은 횟수
            int[] result = solution(id_list, report, k); // [2, 1, 1, 0]
            for (int i : result) {
                System.out.println("i = " + i);
            }
            String[] id_list2 = {"con", "ryan"};
            String[] report2 = {"ryan con", "ryan con", "ryan con", "ryan con"};
            int k2 = 3;
            int[] result2 = solution(id_list2, report2, k2); // [0, 0]
            for (int j : result2) {
                System.out.println("j = " + j);
            }
        }

        private static int[] mySolution(String[] id_list, String[] report, int k) {
            // id별로 신고횟수 저장하는 map1(id:count)
            // muzi : 0
            // frodo : 0
            // apeach : 0
            // neo : 0
            // id별로 신고한 사용자를 저장하는 map2(id:list)
            // report 돌면서 map1, map2 데이터 업데이트
            // id_list별로 map2를 조회해서 list를 가져오고 거기서 map1을 조회해서 k번 이상인 것 카운트해서 결과에 반영
            HashMap<String, Integer> reportCountMap = new HashMap<>();
            for (String id : id_list) {
                reportCountMap.put(id, 0);
            }
            // 동일한 유저에 대한 신고 횟수는 1회로 처리
            // 이걸 처리하기 위해서는
            HashSet<String> reportSet = new HashSet<>();    // 중복 체크용 <-- 굳이 필요하지 않음
            HashMap<String, HashSet<String>> reportListMap = new HashMap<>();
            for (String r : report) {
                System.out.println("r = " + r);
                if (!reportSet.contains(r)) {
                    reportSet.add(r);
                    String[] s = r.split(" ");
                    reportCountMap.put(s[1], reportCountMap.getOrDefault(s[1], 0) + 1);
                    HashSet<String> reportList = reportListMap.getOrDefault(s[0], new HashSet<>());
                    reportList.add(s[1]);
                    reportListMap.put(s[0], reportList);
                }
            }
            ArrayList<Integer> result = new ArrayList<>();
            for (String id : id_list) {
                int count = 0;
                if (reportListMap.containsKey(id)) {
                    count = (int) reportListMap.get(id)
                            .stream()
                            .filter(s -> reportCountMap.getOrDefault(s, 0) >= k)
                            .count();
                }
                result.add(count);
            }
            System.out.println("reportCountMap = " + reportCountMap);
            System.out.println("reportListMap = " + reportListMap);
            System.out.println("result = " + result);
            return result.stream().mapToInt(Integer::intValue).toArray();
        }

        private static int[] solution(String[] id_list, String[] report, int k) {
            // 신고당한 유저 - 신고 유저 집합을 저장할 해시맵
            HashMap<String, HashSet<String>> reportedUser = new HashMap<>();
            // 처리 결과 메일을 받은 유저 - 받은 횟수를 저장할 해시맵
            HashMap<String, Integer> count = new HashMap<>();

            // 1. 신고 기록 순회
            for (String r : report) {
                String[] s = r.split(" ");
                String userId = s[0];
                String reportedId = s[1];

                if (!reportedUser.containsKey(reportedId)) {    // 2. 신고당한 기록이 없다면
                    reportedUser.put(reportedId, new HashSet<>());
                }
                // 3. 신고한 사람의 아이디를 해시맵의 value인 해시셋에 추가
                reportedUser.get(reportedId).add(userId);
            }

            for (Map.Entry<String, HashSet<String>> entry : reportedUser.entrySet()) {
                if (entry.getValue().size() >= k) { // 4. 정지 기준에 만족하는지 확인
                    for (String uid : entry.getValue()) {   // 5. 해시셋을 순회하며 count 계산
                        count.put(uid, count.getOrDefault(uid, 0) + 1);
                    }
                }
            }

            int[] answer = new int[id_list.length];


            // 6. 각 아이디별 메일을 받은 횟수를 순서대로 정리
            for (int i = 0; i < id_list.length; i++) {
                answer[i] = count.getOrDefault(id_list[i], 0);
            }

            return answer;
        }

    }


}
