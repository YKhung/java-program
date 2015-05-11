package P3;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WikiCrawler {
	private int Request = 0;
	private int edge = 0;
	private String seedUrl;
	private String[] keyWords;
	private int max;
	private String fileName;
	private final String header = "http://en.wikipedia.org";
	private ArrayList<String> maxPages = new ArrayList<String>();
	private HashMap<String, String> savedPageToContent = new HashMap<String, String>();
	private Queue<String> q = new LinkedList<String>();

	public WikiCrawler(String seedUrl, String[] keyWords, int max,
			String fileName) {
		this.seedUrl = seedUrl;
		this.keyWords = keyWords;
		this.max = max;
		this.fileName = fileName;
	}

	public void crawl() {
		FileWriter fw;

		try {
			maxPages.add(seedUrl);
			q.add(seedUrl);
			fw = new FileWriter(new File(fileName));
			fw.write(String.valueOf(max) + "\r\n");
			while (!q.isEmpty()) {
				String url = q.poll();
				String page;
				if (savedPageToContent.containsKey(url)) {
					page = savedPageToContent.get(url);
				} else {
					page = downloadPage(url, true);

				}

				ArrayList<String> Strings = retriveLinks(page, url);
				for (String s : Strings) {
					edge++;
					fw.write(url + " " + s + "\r\n");

				}
			}
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private ArrayList<String> retriveLinks(String page, String url) {
		Pattern p = Pattern.compile("href=\"(.*?)\"");
		Matcher matcher = p.matcher(page);
		ArrayList<String> cur = new ArrayList<String>();
		while (matcher.find()) {
			String s1 = matcher.group(1);
			if (s1.startsWith("/wiki") && !s1.contains(":")
					&& !s1.contains("#") && !cur.contains(s1)
					&& !s1.equals(url)) {
				if (maxPages.contains(s1)) {
					cur.add(s1);
					continue;
				}
				if (maxPages.size() <= max && pageHasKeyWords(s1)) {
					cur.add(s1);
					maxPages.add(s1);
					q.add(s1);
				}
			}

			if (cur.size() >= max) {
				break;
			}

		}

		return cur;
	}

	private boolean pageHasKeyWords(String url) {

		String[] strings = url.split("/");
		String page = downloadPage(
				"/w/index.php?title=" + strings[2] + "&action=raw", false)
				.toLowerCase();
		boolean test = true;
		for (String s : keyWords) {
			if (!page.contains(s.toLowerCase())) {
				test = false;
				break;
			}
		}
		if (test && !savedPageToContent.containsKey(url))
			savedPageToContent.put(url, downloadPage(url, true));
		return test;
	}



	private String downloadPage(String s, boolean filter) {
		Request++;
		s = header + s;
		try {
			URL url = new URL(s);

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					url.openStream()));
			String line;
			StringBuffer pageBuffer = new StringBuffer();
			boolean start = false;
			if (filter)
				while ((line = reader.readLine()) != null) {
					if (!start && line.contains("<p>"))
						start = true;
					if (start)
						pageBuffer.append(line);
				}
			else {
				while ((line = reader.readLine()) != null)
					pageBuffer.append(line);
			}
			return pageBuffer.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public int getRequest() {
		return Request;
	}

	public int getNode() {
		return max;
	}

	public int getEdge() {
		return edge;
	}

}
