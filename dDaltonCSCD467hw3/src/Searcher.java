import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Searcher extends Thread {
    // TODO figure out what the valid maxSize is
    SharedQueue queue;
    int currentRow = 0;
    int totalFound = 0;
    String pattern;

    public Searcher(String inputPattern, SharedQueue inputQueue) {
        this.pattern = inputPattern;
        this.queue = inputQueue;
    }

    public void run() {
        try {
            while (this.queue.isEmpty() == false && this.queue.getDoneReading() == false) {
                String line = this.queue.dequeue();

                Pattern pattern = Pattern.compile(this.pattern);
                Matcher matcher = pattern.matcher(line);

                int count = 0;
                while (matcher.find()) {
                    count++;
                    System.out.println("found: " + count + " : " + matcher.start() + " - " + matcher.end());
                }

                this.totalFound += count;
                this.currentRow++;
            }

            System.out.println("Total number of lines searched is " + this.currentRow);
            System.out.println("Total occurance of pattern " + this.pattern + "is " + this.totalFound);
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

}
