
import java.util.HashMap;
import java.util.HashSet;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class crawler2 {

    private static final int maxLinks = 1000;
    private HashSet<String> titles = new HashSet<String>();
    private HashSet<String> linksVisited = new HashSet<String>();
    private HashMap<String, Integer> map = new HashMap<String, Integer>();



          public void getLinks(String firstLink) {

        if ((titles.size() < maxLinks) && !linksVisited.contains(firstLink)) {

            linksVisited.add(firstLink);



                Document doc = Jsoup.connect(firstLink).get();
                Elements linksFromPage = doc.select("a[href]");


                String title = doc.select("title").first().text();

                titles.add(title);
                String text = doc.body().text();

                CountWords(text);

                for (Element link : linksFromPage) {

                    if(titles.size() <= maxLinks) {

                        getLinks(link.attr("abs:href"));
                    }
                    else {
                        System.out.println("Site did not work.");
                        System.out.println(firstLink + ", " + linksVisited.size());
                    }

                }


        }

    }


    public void PrintAllTitles() {

        for (String t : titles) {
            System.out.println(t);
        }
    }


    public void PrintAllWordsAndCount() {

        for (String key : map.keySet()) {

            System.out.println(key + " : " + map.get(key));
        }
    }

    private void CountWords(String text) {

        String[] lines = text.split(" ");

        for (String word : lines) {

            if (map.containsKey(word)) {
                int val = map.get(word);
                val += 1;
                map.remove(word);
                map.put(word, val);
            } else {
                map.put(word, 1);
            }

        }
    }
}
