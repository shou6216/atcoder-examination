import java.util.List;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.stream.Collectors;

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
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();

        try {
            int result = Integer.parseInt(s) * 2;
            System.out.println(String.valueOf(result));

        } catch (NumberFormatException e) {
            System.out.println("error");
        }
    }

    /**
     * 増減管理
     */
    private static void increaseAndDecrease() {
        Scanner scanner = new Scanner(System.in);

        List<Integer> sales = scanner.findAll("\"\\d*\"")
                .map(MatchResult::group)
                .map(Integer::valueOf)
                .collect(Collectors.toList());

        int size = sales.size();
        for (int i = 0; i < size - 1; i++) {
            int ai = sales.get(i);
            int ai1 = sales.get(i + 1);

            if (ai1 == ai) {
                System.out.println("stay");

            } else if (ai1 < ai) {
                System.out.println("down " + (ai - ai1));

            } else {
                System.out.println("up " + (ai1 - ai));
            }
        }
    }
}
