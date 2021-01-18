import java.util.*;
import edu.duke.*;
public class MovieRunnerSimilarRatings {
    public void printAverageRatings (){
     FourthRatings FR = new FourthRatings();//"ratedmovies_short.csv","ratings_short.csv"
     RaterDatabase RDB= new RaterDatabase();
     RDB.initialize("ratings.csv");
     System.out.println("read data for "+RDB.size()+" raters");
     MovieDatabase DB = new MovieDatabase();
     DB.initialize("ratedmoviesfull.csv");
     System.out.println("read data for "+DB.size()+" movies");
     ArrayList<Rating> rating = FR.getAverageRatings(35);
     int contador=0;
     
     for(int k=0;k<rating.size();k++){
       if (rating.get(k).getValue()!=0){
           contador+=1;
           System.out.println(rating.get(k).getValue()+" "+DB.getTitle(rating.get(k).getItem()));
        }
      }
      System.out.println("found "+contador+" movies");
    }
    
    public void printAverageRatingsByYearAfterAndGenre (){
     FourthRatings FR = new FourthRatings();//"ratedmovies_short.csv","ratings_short.csv"
     RaterDatabase RDB= new RaterDatabase();
     RDB.initialize("ratings.csv");
     System.out.println("read data for "+RDB.size()+" raters");
     MovieDatabase DB = new MovieDatabase();
     DB.initialize("ratedmoviesfull.csv");
     System.out.println("read data for "+DB.size()+" movies");
     AllFilters AF = new AllFilters();
     AF.addFilter(new YearAfterFilter(1990));
     AF.addFilter(new GenreFilter("Drama"));
     ArrayList<Rating> rating = FR.getAverageRatingsByFilter(8,AF);
     int contador=0;
     
     for(int k=0;k<rating.size();k++){
       if (rating.get(k).getValue()!=0){
           contador+=1;
           System.out.println(rating.get(k).getValue()+" "+DB.getYear(rating.get(k).getItem())+" "+DB.getTitle(rating.get(k).getItem()));
           System.out.println(DB.getGenres(rating.get(k).getItem()));
        }
      }
      System.out.println(contador+" movies matched");
    }
    
    public void printSimilarRatings (){
     FourthRatings FR = new FourthRatings();//"ratedmovies_short.csv","ratings_short.csv"
     RaterDatabase RDB= new RaterDatabase();
     RDB.initialize("ratings.csv");
     //System.out.println("read data for "+RDB.size()+" raters");
     MovieDatabase DB = new MovieDatabase();
     DB.initialize("ratedmoviesfull.csv");
      ArrayList<Rating> ratingSim = FR.getSimilarRatings("71", 20, 5);
      //ArrayList<Rating> ratingS=FR.getSimilarities("65");
      for (Rating r : ratingSim){
      System.out.println(DB.getTitle(r.getItem())+" Rating: "+r.getValue());
     }
     //System.out.println("read data for "+DB.size()+" movies");
    }
    
    public void printSimilarRatingsByGenre (){
     FourthRatings FR = new FourthRatings();//"ratedmovies_short.csv","ratings_short.csv"
     RaterDatabase RDB= new RaterDatabase();
     RDB.initialize("ratings.csv");
     //System.out.println("read data for "+RDB.size()+" raters");
     MovieDatabase DB = new MovieDatabase();
     DB.initialize("ratedmoviesfull.csv");
     Filter filterCriteria = new GenreFilter("Mystery");
     ArrayList<Rating> ratingSim = FR.getSimilarRatingsFilters("964", 20, 5,filterCriteria);
      //ArrayList<Rating> ratingS=FR.getSimilarities("65");
      for (Rating r : ratingSim){
      System.out.println(DB.getTitle(r.getItem())+" Rating: "+r.getValue());
      System.out.println(DB.getGenres(r.getItem()));
     }        
    }
    
     public void printSimilarRatingsByDirector (){
     FourthRatings FR = new FourthRatings();//"ratedmovies_short.csv","ratings_short.csv"
     RaterDatabase RDB= new RaterDatabase();
     RDB.initialize("ratings.csv");
     //System.out.println("read data for "+RDB.size()+" raters");
     MovieDatabase DB = new MovieDatabase();
     DB.initialize("ratedmoviesfull.csv");
     String [] direc= {"Clint Eastwood","J.J. Abrams","Alfred Hitchcock","Sydney Pollack","David Cronenberg","Oliver Stone","Mike Leigh"};
     Filter filterCriteria = new DirectorsFilter(direc);
     ArrayList<Rating> ratingSim = FR.getSimilarRatingsFilters("120", 10, 2,filterCriteria);
      //ArrayList<Rating> ratingS=FR.getSimilarities("65");
      for (Rating r : ratingSim){
      System.out.println(DB.getTitle(r.getItem())+" Rating: "+r.getValue());
      System.out.println("Director(s): "+DB.getDirector(r.getItem()));
     }        
    }
    
         public void printSimilarRatingsByGenreAndMinutes (){
     FourthRatings FR = new FourthRatings();//"ratedmovies_short.csv","ratings_short.csv"
     RaterDatabase RDB= new RaterDatabase();
     RDB.initialize("ratings.csv");
     //System.out.println("read data for "+RDB.size()+" raters");
     MovieDatabase DB = new MovieDatabase();
     DB.initialize("ratedmoviesfull.csv");
     AllFilters AF = new AllFilters();
     AF.addFilter(new minutesFilter(80,160));
     AF.addFilter(new GenreFilter("Drama"));
     ArrayList<Rating> ratingSim = FR.getSimilarRatingsFilters("168", 10, 3,AF);
      //ArrayList<Rating> ratingS=FR.getSimilarities("65");
      for (Rating r : ratingSim){
      System.out.println(DB.getTitle(r.getItem())+" Rating: "+r.getValue());
      System.out.println("Minutes: "+DB.getMinutes(r.getItem()));
      System.out.println("Genre(s): "+DB.getGenres(r.getItem()));
     }        
    }
    
         public void printSimilarRatingsByYearAfterAndMinutes  (){
     FourthRatings FR = new FourthRatings();//"ratedmovies_short.csv","ratings_short.csv"
     RaterDatabase RDB= new RaterDatabase();
     RDB.initialize("ratings.csv");
     //System.out.println("read data for "+RDB.size()+" raters");
     MovieDatabase DB = new MovieDatabase();
     DB.initialize("ratedmoviesfull.csv");
     AllFilters AF = new AllFilters();
     AF.addFilter(new minutesFilter(70,200));
     AF.addFilter(new YearAfterFilter(1975));
     ArrayList<Rating> ratingSim = FR.getSimilarRatingsFilters("314", 10, 5,AF);
      //ArrayList<Rating> ratingS=FR.getSimilarities("65");
      for (Rating r : ratingSim){
      System.out.println(DB.getTitle(r.getItem())+" Rating: "+r.getValue());
      System.out.println("Minutes: "+DB.getMinutes(r.getItem()));
      System.out.println("Year: "+DB.getYear(r.getItem()));
     }        
    }    
}
