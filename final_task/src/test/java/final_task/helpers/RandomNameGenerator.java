package final_task.helpers;

public class RandomNameGenerator {
    public String generateRandomName() {

        String randomLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        int n = 5;

        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {
            int index = (int) (randomLetters.length() * Math.random());

            sb.append(randomLetters.charAt(index));
        }

        return sb.toString();
    }
}
