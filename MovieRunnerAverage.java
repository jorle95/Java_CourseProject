    import java.util.*;
public class MovieRunnerAverage {
 public void printAverageRatings (){
     SecondRatings SR = new SecondRatings("ratedmovies_short.csv","ratings_short.csv");//"ratedmovies_short.csv","ratings_short.csv"
     System.out.println(SR.getMovieSize ()+"\t"+SR.getRaterSize());
     ArrayList<Rating> rating = SR.getAverageRatings(3);
    
     for(int k=0;k<rating.size();k++){
       if (rating.get(k).getValue()!=0){
         System.out.println(rating.get(k).getValue()+" "+SR.getTitle(rating.get(k).getItem()));
        }
      }
    }
    
 public void getAverageRatingOneMovie (){
     SecondRatings SR = new SecondRatings("ratedmovies_short.csv","ratings_short.csv");
     String movie = "The Godfather";
     String id= SR.getID(movie);
     System.out.println(movie+" "+SR.getAverageByID(id,3));
    }
}
