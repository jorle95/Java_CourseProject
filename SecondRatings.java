    import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    public SecondRatings(String moviefile , String ratingsfile) {
        FirstRatings fRat = new FirstRatings();
        myMovies= fRat.loadMovies(moviefile);
        myRaters= fRat.loadRaters(ratingsfile);
    }
    
    public int getMovieSize (){
        return myMovies.size();
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
        HashMap<String,ArrayList<Double>> movieMap = MoviesMap ();
        double average = 0.0;
        double sum = 0.0;
        //for(String s: movieMap.keySet()){//(int i=0;i<movieMap.size();i++
            
        if (movieMap.get(id).size()<minimalRaters){
           return 0.0;
        }
        else {
            for(int i=0;i<movieMap.get(id).size();i++){
                sum=sum+movieMap.get(id).get(i);
            }
            average = sum/movieMap.get(id).size();
        }
      //}
      return average;
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        HashMap<String,ArrayList<Double>> movieMap = MoviesMap ();
        ArrayList<Rating> rating = new ArrayList<Rating>();
        for(String s: movieMap.keySet()){
            Double average =getAverageByID(s,minimalRaters);
            Rating rat = new Rating (s,average);
            rating.add(rat);
        }
        return rating;
    }
    
    public String getTitle (String id){
        HashMap<String,String> titleID = new HashMap<String,String> ();
        String tit = "";
         for(Movie m: myMovies){
        String title = m.getTitle();
        String ID = m.getID();
        titleID.put(ID,title);
       }
       if (!titleID.containsKey(id)){
         return "ID not found";  
        }
       for(String s: titleID.keySet()){ 
           if(s.equals(id)){
               tit = titleID.get(s);
            }
        }
       return tit; 
    }
    
    public String getID (String title){
        HashMap<String,String> titleID = new HashMap<String,String> ();
        String id = "";
         for(Movie m: myMovies){
        String titles = m.getTitle();
        String ID = m.getID();
        titleID.put(titles,ID);
       }
       if (!titleID.containsKey(title)){
         return "Title not found";  
        }
       for(String s: titleID.keySet()){ 
           if(s.equals(title)){
               id = titleID.get(s);
            }
        }
       return id;
    }
}
