    import java.util.*;

public class FourthRatings {
    
    public double getAverageByID (String id, int minimalRaters){
        HashMap<String,ArrayList<Double>> movieMap = new HashMap<String,ArrayList<Double>>();
        RaterDatabase RaterDB = new RaterDatabase();
        ArrayList<Rater> myRaters = RaterDB.getRaters();
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
    
    public ArrayList<Rating> getSimilarities (String id){//falta quitar los negativos ya que no tienen ninguna similitud
        ArrayList<Rating> list = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        Rater me = RaterDatabase.getRater(id);
        ArrayList<Double> ratMovMe = new ArrayList<Double>();
         

        for (String s : movies){
            if (!me.hasRating(s)){    //POSIBLE ERROR =0, CUANDO PUEDE SER NULLPOINTER

            ratMovMe.add(0.0);
          }
            else {
            ratMovMe.add(me.getRating(s));
          }
        }
        
        for (Rater r : RaterDatabase.getRaters()){
            double suma = 0;
            ArrayList<Double> ratMovRaters = new ArrayList<Double>();
            if (!r.equals(me)){
             for (String s : movies){
               if (!r.hasRating(s)){
               ratMovRaters.add(0.0);
               }
               else {
               ratMovRaters.add(r.getRating(s));
              } 
             }
             
              for (int i=0;i<ratMovMe.size();i++){  
                if(ratMovMe.get(i) != 0.0 && ratMovRaters.get(i)!= 0.0){
                 Double product = (ratMovMe.get(i)-5)*(ratMovRaters.get(i)-5);
                 suma+=product;
                }
                }
              if(suma>=0){
              Rating rat = new Rating(r.getID(),suma); 
              list.add(rat);
            }
            }
        }
        
        Collections.sort(list,Collections.reverseOrder());
        return list;
    }
    
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters){
        ArrayList<Rating> simi= getSimilarities (id);
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());        
        ArrayList<Rating> MovieRating = new ArrayList<Rating>();
        for (String s : movies){
            double sum = 0.0;
            int count = 0;
             for (int i = 0; i < numSimilarRaters; i++) {
                 Rating raterRating = simi.get(i);
                 String raterID = raterRating.getItem();
                 Rater er = RaterDatabase.getRater(raterID);
                 if(er.hasRating(s)) {
                     count++;
                     double score = er.getRating(s);
                     double res = raterRating.getValue() * score;
                     sum += res;
                    }
                }
             if (count >= minimalRaters) {
                     double weightedAverage = sum / count;
                     Rating rating = new Rating(s, weightedAverage);
                     MovieRating.add(rating);
             }
         }
         Collections.sort(MovieRating);
         Collections.reverse(MovieRating);
         return MovieRating;
    }
    
    public ArrayList<Rating> getSimilarRatingsFilters(String id, int numSimilarRaters, int minimalRaters, Filter filterCriteria){
        ArrayList<Rating> simi= getSimilarities (id);
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());        
        ArrayList<Rating> MovieRating = new ArrayList<Rating>();
        for (String s : movies){
             if (filterCriteria.satisfies(s)){
            double sum = 0.0;
            int count = 0;
             for (int i = 0; i < numSimilarRaters; i++) {
                 Rating raterRating = simi.get(i);
                 String raterID = raterRating.getItem();
                 Rater er = RaterDatabase.getRater(raterID);
                 if(er.hasRating(s)) {
                     count++;
                     double score = er.getRating(s);
                     double res = raterRating.getValue() * score;
                     sum += res;
                    }
                }
             if (count >= minimalRaters) {
                     double weightedAverage = sum / count;
                     Rating rating = new Rating(s, weightedAverage);
                     MovieRating.add(rating);
             }
         }
        }
         Collections.sort(MovieRating);
         Collections.reverse(MovieRating);
         return MovieRating;        
    }
    
    
}

