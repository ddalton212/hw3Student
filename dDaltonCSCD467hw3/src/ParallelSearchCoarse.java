
public class ParallelSearchCoarse {

	public static void main(String args[]) throws InterruptedException {
		if( args.length < 2) {
			System.out.println("Usage: Java ParallelSearchCoarse FileName Pattern. Please add arguments for FILENAME and PATTERN");
			System.exit(0);
		}

		// TODO REMOVE DEBUG FILES
		String fname = args[0];         // fileName = files/wikipedia2text-extracted.txt
		String pattern = args[1];      // pattern = "(John) (.+?) ";
		long start = System.currentTimeMillis();
		
		// Create your thread reader and searcher here
		SharedQueue queue = new SharedQueue(100);
		Reader reader = new Reader(fname, queue);
		Searcher searcher = new Searcher(pattern, queue);

		reader.start();
		searcher.start();

		reader.join();
		searcher.join();
		long end = System.currentTimeMillis();
		System.out.println("Time cost for concurrent solution is " + (end - start));
		
	}

}
