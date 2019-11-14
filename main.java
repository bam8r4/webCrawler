public class main {

    public static void main(String[] args) {
        crawler2 bot = new crawler2();
        bot.getLinks("https://en.wikipedia.org");
        bot.PrintAllTitles();
        System.out.println("Now outputting words from links");
        bot.PrintAllWordsAndCount();
        }

}
