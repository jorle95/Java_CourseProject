    import java.util.*;
    import edu.duke.*;
public class MovieRunnerWithFilters  {
    
 public void printAverageRatings (){
     ThirdRatings TR = new ThirdRatings("ratings.csv");//"ratedmovies_short.csv","ratings_short.csv"
     System.out.println("read data for "+TR.getRaterSize()+" raters");
     MovieDatabase DB = new MovieDatabase();
     DB.initialize("ratedmoviesfull.csv");
     System.out.println("read data for "+DB.size()+" movies");
     ArrayList<Rating> rating = TR.getAverageRatings(35);
     int contador=0;
     
     for(int k=0;k<rating.size();k++){
       if (rating.get(k).getValue()!=0){
           contador+=1;
           System.out.println(rating.get(k).getValue()+" "+DB.getTitle(rating.get(k).getItem()));
        }
      }
      System.out.println("found "+contador+" movies");
    }
    
 public void printAverageRatingsByYear (){
     ThirdRatings TR = new ThirdRatings("ratings.csv");//"ratedmovies_short.csv","ratings_short.csv"
     System.out.println("read data for "+TR.getRaterSize()+" raters");
     MovieDatabase DB = new MovieDatabase();
     DB.initialize("ratedmoviesfull.csv");
     System.out.println("read data for "+DB.size()+" movies");
     Filter filterCriteria = new YearAfterFilter(2000);
     ArrayList<Rating> rating = TR.getAverageRatingsByFilter(20,filterCriteria);
     int contador=0;
     
     for(int k=0;k<rating.size();k++){
       if (rating.get(k).getValue()!=0){
           contador+=1;
           System.out.println(rating.get(k).getValue()+" "+DB.getTitle(rating.get(k).getItem()));
        }
      }
      System.out.println("found "+contador+" movies");
    }
    
    public void printAverageRatingsByGenre (){
     ThirdRatings TR = new ThirdRatings("ratings.csv");//"ratedmovies_short.csv","ratings_short.csv"
     System.out.println("read data for "+TR.getRaterSize()+" raters");
     MovieDatabase DB = new MovieDatabase();
     DB.initialize("ratedmoviesfull.csv");
     System.out.println("read data for "+DB.size()+" movies");
     Filter filterCriteria = new GenreFilter("Comedy");
     ArrayList<Rating> rating = TR.getAverageRatingsByFilter(20,filterCriteria);
     int contador=0;
     
     for(int k=0;k<rating.size();k++){
       if (rating.get(k).getValue()!=0){
           contador+=1;
           System.out.println(rating.get(k).getValue()+" "+DB.getTitle(rating.get(k).getItem()));
           System.out.println(DB.getGenres(rating.get(k).getItem()));
        }
      }
      System.out.println("found "+contador+" movies");
    }
    
    public void printAverageRatingsByMinutes (){
     ThirdRatings TR = new ThirdRatings("ratings.csv");//"ratedmovies_short.csv","ratings_short.csv"
     System.out.println("read data for "+TR.getRaterSize()+" raters");
     MovieDatabase DB = new MovieDatabase();
     DB.initialize("ratedmoviesfull.csv");
     System.out.println("read data for "+DB.size()+" movies");
     Filter filterCriteria = new minutesFilter(105,135);
     ArrayList<Rating> rating = TR.getAverageRatingsByFilter(5,filterCriteria);
     int contador=0;
     
     for(int k=0;k<rating.size();k++){
       if (rating.get(k).getValue()!=0){
           contador+=1;
           System.out.println(rating.get(k).getValue()+" "+DB.getTitle(rating.get(k).getItem()));
           System.out.println("Time: "+DB.getMinutes(rating.get(k).getItem()));
        }
      }
      System.out.println("found "+contador+" movies");
 }
 
     public void printAverageRatingsByDirectors (){
     ThirdRatings TR = new ThirdRatings("ratings.csv");//"ratedmovies_short.csv","ratings_short.csv"
     System.out.println("read data for "+TR.getRaterSize()+" raters");
     MovieDatabase DB = new MovieDatabase();
     DB.initialize("ratedmoviesfull.csv");
     System.out.println("read data for "+DB.size()+" movies");
     String [] direc= {"Clint Eastwood","Joel Coen","Martin Scorsese","Roman Polanski","Nora Ephron","Ridley Scott","Sydney Pollack"};
     Filter filterCriteria = new DirectorsFilter(direc);
     ArrayList<Rating> rating = TR.getAverageRatingsByFilter(4,filterCriteria);
     int contador=0;
     
     for(int k=0;k<rating.size();k++){
       if (rating.get(k).getValue()!=0){
           contador+=1;
           System.out.println(rating.get(k).getValue()+" "+DB.getTitle(rating.get(k).getItem()));
           System.out.println("Director(s): "+DB.getDirector(rating.get(k).getItem()));
        }
      }
      System.out.println("found "+contador+" movies");
 }
 
 public void printAverageRatingsByYearAfterAndGenre (){
     ThirdRatings TR = new ThirdRatings("ratings.csv");//"ratedmovies_short.csv","ratings_short.csv"
     System.out.println("read data for "+TR.getRaterSize()+" raters");
     MovieDatabase DB = new MovieDatabase();
     DB.initialize("ratedmoviesfull.csv");
     System.out.println("read data for "+DB.size()+" movies");
     AllFilters AF = new AllFilters();
     AF.addFilter(new YearAfterFilter(1990));
     AF.addFilter(new GenreFilter("Drama"));
     ArrayList<Rating> rating = TR.getAverageRatingsByFilter(8,AF);
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
    
  public void printAverageRatingsByDirectorsAndMinutes  (){
     ThirdRatings TR = new ThirdRatings("ratings.csv");//"ratedmovies_short.csv","ratings_short.csv"
     System.out.println("read data for "+TR.getRaterSize()+" raters");
     MovieDatabase DB = new MovieDatabase();
     DB.initialize("ratedmoviesfull.csv");
     System.out.println("read data for "+DB.size()+" movies");
     String [] direc= {"Clint Eastwood","Joel Coen","Tim Burton","Ron Howard","Nora Ephron","Sydney Pollack"};
     AllFilters AF = new AllFilters();
     AF.addFilter(new DirectorsFilter(direc));
     AF.addFilter(new minutesFilter(90,180));
     ArrayList<Rating> rating = TR.getAverageRatingsByFilter(3,AF);
     int contador=0;
     
     for(int k=0;k<rating.size();k++){
       if (rating.get(k).getValue()!=0){
           contador+=1;
           System.out.println(rating.get(k).getValue()+" "+DB.getMinutes(rating.get(k).getItem())+" "+DB.getTitle(rating.get(k).getItem()));
           System.out.println(DB.getDirector(rating.get(k).getItem()));
        }
      }
      System.out.println(contador+" movies matched");
    }   
}