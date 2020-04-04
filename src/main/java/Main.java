import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        // 2倍チェック
        // twice();

        // 増減管理
        // increaseAndDecrease();

        // 3番目
        // third();

        // 重複検査
        //duplicate();

        // SNS のログ
        snsLog();
    }

    /**
     * 2倍
     */
    private static void twice() {
        String s = ScannerUtil.getStringLine();

        try {
            System.out.println(Integer.parseInt(s) * 2);

        } catch (NumberFormatException e) {
            System.out.println("error");
        }
    }

    /**
     * 増減管理
     */
    private static void increaseAndDecrease() {
        List<Integer> list = ScannerUtil.getIntegerLines();

        int n = list.get(0);
        for (int i = 1; i < n; i++) {
            int ai = list.get(i);
            int ai1 = list.get(i + 1);

            if (ai1 == ai) {
                System.out.println("stay");

            } else if (ai1 < ai) {
                System.out.println("down " + (ai - ai1));

            } else {
                System.out.println("up " + (ai1 - ai));
            }
        }
    }

    /**
     * 3倍
     */
    private static void third() {
        List<Integer> list = ScannerUtil.getIntegers();

        list.sort(Comparator.reverseOrder());
        System.out.println(list.get(2));
    }

    /**
     * 重複検査
     */
    private static void duplicate() {
        List<Integer> list = ScannerUtil.getIntegerLines();

        int n = list.get(0);
        List<Integer> a = list.subList(1, list.size());

        if (a.stream().distinct().count() == n) {
            System.out.println("Correct");

        } else {
            Map<Integer, List<Integer>> map = a.stream()
                    .collect(Collectors.groupingBy(_a -> _a));

            int lostValue = 0;
            int duplicateValue = 0;
            for (int i = 1; i <= n; i++) {
                if (map.containsKey(i)) {
                    if (map.get(i).size() > 1) {
                        duplicateValue = i;
                    }

                } else {
                    lostValue = i;
                }

                if (lostValue > 0 && duplicateValue > 0) {
                    break;
                }
            }
            System.out.println(String.format("%d %d", duplicateValue, lostValue));
        }
    }

    /**
     * SNSのログ
     */
    private static void snsLog() {
        List<List<Integer>> lists = ScannerUtil.getIntegersLines();

        List<Integer> firstLine = lists.get(0);
        int n = firstLine.get(0);
        int q = firstLine.get(1);
        List<List<Integer>> s = lists.subList(1, lists.size());

        Map<Integer, Set<Integer>> follow = new HashMap<>();

        for (int i = 0; i < q; i++) {
            List<Integer> si = s.get(i);
            int operation = si.get(0);
            int fromUser = si.get(1);

            switch (operation) {
                case 1: // フォロー
                    follow(fromUser, si.get(2), follow);
                    break;

                case 2: // フォロー全返し
                    Set<Integer> toUsers = follow.entrySet()
                            .stream()
                            .filter(entry -> entry.getValue().contains(fromUser))
                            .map(Map.Entry::getKey)
                            .collect(Collectors.toSet());

                    toUsers.forEach(toUser -> follow(fromUser, toUser, follow));

                case 3: // フォローフォロー
                default:
                    toUsers = follow.getOrDefault(fromUser, Collections.emptySet())
                            .stream()
                            .flatMap(toUser -> follow.getOrDefault(
                                    toUser, Collections.emptySet())
                                    .stream())
                            .filter(xToUser -> xToUser != fromUser)
                            .collect(Collectors.toSet());

                    toUsers.forEach(_toUser -> follow(fromUser, _toUser, follow));
            }
        }

        for (int i = 1; i <= n; i++) {
            Set<Integer> toUsers = follow.get(i);
            String result = IntStream.rangeClosed(1, n)
                    .mapToObj(j -> Optional.ofNullable(toUsers)
                            .map(_toUsers -> _toUsers.contains(j) ? "Y" : "N")
                            .orElse("N"))
                    .collect(Collectors.joining());
            System.out.println(result);
        }
    }

    private static void follow(int fromUser, int toUser, Map<Integer, Set<Integer>> map) {
        map.compute(fromUser, (_fromUser, _toUsers) -> {
            return Optional.ofNullable(_toUsers)
                    .map(t -> {
                        t.add(toUser);
                        return t;
                    })
                    .orElse(Stream.of(toUser).collect(Collectors.toSet()));
        });
    }

    public static class ScannerUtil {

        public static String getStringLine() {
            Scanner scanner = new Scanner(System.in);
            return scanner.nextLine();
        }

        public static List<Integer> getIntegerLines() {
            List<Integer> list = new ArrayList<Integer>();

            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.length() == 0) {
                    break;
                }
                list.add(Integer.valueOf(line));
            }

            return list;
        }

        public static List<Integer> getIntegers() {
            List<Integer> list = new ArrayList<Integer>();

            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.length() == 0) {
                    break;
                }

                Arrays.stream(line.split(" "))
                        .map(Integer::parseInt)
                        .forEach(list::add);
            }

            return list;
        }

        public static List<List<Integer>> getIntegersLines() {
            List<List<Integer>> list = new ArrayList<List<Integer>>();

            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.length() == 0) {
                    break;
                }

                list.add(Arrays.stream(line.split(" "))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList()));
            }

            return list;
        }

        private ScannerUtil() {
        }
    }
}
