    import java.util.*;

public class ThirdRatings {
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }
    
    public ThirdRatings(String ratingsfile) {
        FirstRatings fRat = new FirstRatings();
        myRaters= fRat.loadRaters(ratingsfile);
    }
    
    
    public int getRaterSize(){
        ArrayList<Rater> Uniqueraters= new ArrayList<Rater>();
        for(int k=0;k<myRaters.size()-1;k++){
            if(!myRaters.get(k).getID().equals(myRaters.get(k+1).getID())){
                Uniqueraters.add(myRaters.get(k));
            }
            if(k==myRaters.size()-2){
                Uniqueraters.add(myRaters.get(k));
            }
        }
        return Uniqueraters.size();
    }
    
    private HashMap<String,ArrayList<Double>> MoviesMap (){
        HashMap<String,ArrayList<Double>> movieMap = new HashMap<String,ArrayList<Double>>(); 
        for(Rater r : myRaters){
             ArrayList<String> movIDs = r.getItemsRated();                        
             for(int i=0;i<movIDs.size();i++){
                 String movID =movIDs.get(i);
                 Double rating = r.getRating(movID);
                if(!movieMap.containsKey(movID)){
                 ArrayList<Double> ratings = new ArrayList<Double>();
                 ratings.add(rating);
                 movieMap.put(movID,ratings);
                  }
                 else {
                     ArrayList<Double> ratings = movieMap.get(movID);
                     ratings.add(rating);
                     movieMap.put(movID,ratings);
                }
             }
        }
        return movieMap;
    }
    
    public double getAverageByID (String id, int minimalRaters){
        HashMap<String,ArrayList<Double>> movieMap = new HashMap<String,ArrayList<Double>>(); 
        for(Rater r : myRaters){
             ArrayList<String> movIDs = r.getItemsRated();                        
             for(int i=0;i<movIDs.size();i++){
                 String movID =movIDs.get(i);
                 Double rating = r.getRating(movID);
                if(!movieMap.containsKey(movID)){
                 ArrayList<Double> ratings = new ArrayList<Double>();
                 ratings.add(rating);
                 movieMap.put(movID,ratings);
                  }
                 else {
                     ArrayList<Double> ratings = movieMap.get(movID);
                     ratings.add(rating);
                     movieMap.put(movID,ratings);
                }
             }
        }
        double average = 0.0;
        double sum = 0.0;
        //for(String s: movieMap.keySet()){//(int i=0;i<movieMap.size();i++
       if(movieMap.containsKey(id)){     
        if (movieMap.get(id).size()<minimalRaters){
           return 0.0;
        }
        else {
            for(int i=0;i<movieMap.get(id).size();i++){
                sum=sum+movieMap.get(id).get(i);
            }
            average = sum/movieMap.get(id).size();
        }
      }
      return average;
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        ArrayList<Rating> rating = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for (String s : movies){
            Double average =getAverageByID(s,minimalRaters);
            Rating rat = new Rating (s,average);
            rating.add(rat);            
        }
        return rating;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter (int minimalRaters, Filter filterCriteria){
        ArrayList<Rating> rating = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for (String s : movies){
            if (filterCriteria.satisfies(s)){
            Double average =getAverageByID(s,minimalRaters);
            Rating rat = new Rating (s,average);
            rating.add(rat);
           }
        }
        return rating;
    }
    
}
