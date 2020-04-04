import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        // 2倍チェック
        // twice();

        // 増減管理
        // increaseAndDecrease();

        // 3番目
        // third();

        // 重複検査
        duplicate();
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

        private ScannerUtil() {
        }
    }
}
