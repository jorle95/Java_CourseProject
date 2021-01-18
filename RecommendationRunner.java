import java.util.*;
import edu.duke.*;
public class RecommendationRunner implements Recommender {
    public ArrayList<String> getItemsToRate (){
        ArrayList<String> movies = new ArrayList<String>();
        ArrayList<String> tmovies = new ArrayList<String>();
        MovieDatabase DB = new MovieDatabase();
        DB.initialize("ratedmoviesfull.csv");        
        AllFilters list = new AllFilters();
        Filter filter1 = new YearAfterFilter(2000);        
        Filter filter2 = new GenreFilter("Comedy");

        list.addFilter(filter1);
        list.addFilter(filter2);
        movies = MovieDatabase.filterBy(list);
        for (int k=0;k<20;k++) {
            tmovies.add(movies.get(k));
        }
        return tmovies;
    }
    
        public void test(){
        ArrayList<String> testlist = getItemsToRate();
        for(String s: testlist){
            System.out.println(s);
        }
    }
    
    public void printRecommendationsFor(String webRaterID){
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        FourthRatings fR = new FourthRatings();
        int numSimilarRaters = 10;
        int minimalRaters = 3;
        
        ArrayList<Rating> movs = fR.getSimilarRatings(webRaterID, numSimilarRaters, minimalRaters);                  
        if(movs.size() == 0){
            System.out.println("There are no movie recommendations for your choice, please try again");
        }
        else{
            
            // Create a table to list all the rated movies
            System.out.println("<table><tr><th class='column name'>POSTER</th><th class='column name'>TITLE</th><th class='column name'>GENRE</th><th class='column name'>DIRECTORS</th><th class='column name'>MINUTES</th><th class='column name'>YEAR</th><th class='column name'>RATING</th>");
            for(int i = 0; i < movs.size(); i++){
                Rating currentRating = movs.get(i);
                String currentMovieID = currentRating.getItem();
                System.out.println("<tr><td>" + "<img src= \"" + MovieDatabase.getPoster(currentMovieID) + "\"width=\"60%\"/> </td>");
                System.out.println("<td>" + MovieDatabase.getTitle(currentMovieID) + "</td>");
                System.out.println("<td>" + MovieDatabase.getCountry(currentMovieID)+ "</td>");
                System.out.println("<td>" + MovieDatabase.getGenres(currentMovieID)+ "</td>");
                System.out.println("<td>" + MovieDatabase.getMinutes(currentMovieID)+ "</td>");
                System.out.println("<td>" + MovieDatabase.getYear(currentMovieID)+ "</td>");
                System.out.println("<td>" + currentRating.getValue() + "</td></tr>");
            }
            
            System.out.println("</table>");
            System.out.println("<style>table, tr, td, th {border: 1px solid black; border-width:1px; border-color: solid black;}");
            System.out.println("table{width: 100%;text-align: left;background-color: #f5f5f5;}");
            System.out.println("th{background-color: #D2D2F2;}");
        }
    }
}
