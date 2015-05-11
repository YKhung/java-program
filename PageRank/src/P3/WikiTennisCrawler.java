package P3;

public class WikiTennisCrawler {
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
	
	public WikiTennisCrawler() 
	{
		Time time = new Time();
		String[] topics = { "tennis", "grand slam" };
		WikiCrawler wikiCrawler = new WikiCrawler("/wiki/tennis", topics, 1000,
				"WikiTennisGraph.txt");
		wikiCrawler.crawl();
		System.out.println("# of Requst: " + wikiCrawler.getRequest());
		System.out.println("Edge:" + wikiCrawler.getEdge());
		System.out.println("Node:" + wikiCrawler.getNode());
		System.out.println("Time:" + time.elapsedTime());
		

	}
	
	
}