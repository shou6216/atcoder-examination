import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // 2倍チェック
        // twice();

        // 増減管理
        increaseAndDecrease();
    }

    /**
     * 2倍
     */
    private static void twice() {
        String s = ScannerUtil.getString();

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
        List<Integer> list = ScannerUtil.getIntegerList();

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

    public static class ScannerUtil {

        public static String getString() {
            Scanner scanner = new Scanner(System.in);
            return scanner.nextLine();
        }

        public static int getInteger() {
            return Integer.parseInt(getString());
        }

        public static List<Integer> getIntegerList() {
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

        private ScannerUtil() {
        }
    }
}
