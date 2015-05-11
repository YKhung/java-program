package P3;

public class MyWikiCrawler {
	public class Time {
	    private final long start;
	    public Time() {
	        start = System.currentTimeMillis();
	    }
	    public double elapsedTime() {
	        long now = System.currentTimeMillis();
	        return (now - start) / 1000.0;
	    }

	}
	public MyWikiCrawler() {
		Time time = new Time();
		String[] topics = { "basketball", "national basketball association" };
		WikiCrawler wikiCrawler = new WikiCrawler("/wiki/basketball", topics, 1000,
				"WikiBasketballGraph.txt");
		wikiCrawler.crawl();
		System.out.println("# of Requst: " + wikiCrawler.getRequest());
		System.out.println("Edge:" + wikiCrawler.getEdge());
		System.out.println("Node:" + wikiCrawler.getNode());
		System.out.println("Time:" + time.elapsedTime());
	}
}
