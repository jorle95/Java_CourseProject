import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
    
public class FirstRatings {
    public ArrayList<Movie> loadMovies (String filename){
        ArrayList<Movie> mov = new ArrayList<Movie>();
        FileResource fr = new FileResource("data/"+filename);
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord record : parser) {
            String id = record.get("id");
            String title = record.get("title");
            String year = record.get("year");
            String genres = record.get("genre");
            String director = record.get("director");
            String country = record.get("country");
            String poster = record.get("poster");
            String minutes = record.get("minutes");
            Movie movie = new Movie(id, title, year, genres, director, country, poster, minutes);
            mov.add(movie);

        }
        return mov;
    }
    
       
    public void testLoadMovies (){
       int comedyCount = 0;
       int lengthCount = 0;
       int maxMovDir=  0;
       ArrayList<String> direct = new ArrayList<String>() ;
       HashMap<String,Integer> directors = new HashMap<String,Integer>();
       ArrayList<Movie> mov = loadMovies("ratedmoviesfull.csv");
       System.out.println("Number of movies: "+mov.size());
       for(Movie m : mov){
        System.out.println(m.getTitle());
        String genres = m.getGenres();
        int comedy = genres.indexOf("Comedy");
        int minutes= m.getMinutes();
        String director = m.getDirector();
        if(director.indexOf(",")!=-1){
            int index = 0;
            while (true){
             index =director.indexOf(",",index);
             if (index==-1){
                 break;
             } 
             director=director.substring(0,index);
             if (!directors.containsKey(director)){
                directors.put(director,1);
             }
             else{
                directors.put(director,directors.get(director)+1);
             }             
            }
            
        }
        else{
            if (!directors.containsKey(director)){
                directors.put(director,1);
            }
            else{
                directors.put(director,directors.get(director)+1);
            }
        }
        
          if(comedy!=-1){
                comedyCount+=1;
          }
          if(minutes >150){
              lengthCount+=1;
            }
          
       }
       System.out.println("Number of comedy movies: "+comedyCount);
       System.out.println("Number of movies longer than 150 minutes: "+lengthCount);
       for (String s: directors.keySet()){
           if(maxMovDir<directors.get(s)){
               maxMovDir=directors.get(s);
           }
        }
        for (String s: directors.keySet()){
            if(directors.get(s)==maxMovDir){
                direct.add(s);
            }
        }
       System.out.println("maximum number of movies by any director: "+maxMovDir+" and they are: "+direct);
    }
    
    public ArrayList<Rater> loadRaters (String filename){
     ArrayList<Rater> rate = new ArrayList<Rater>();
     FileResource fr = new FileResource("data/"+filename);
     CSVParser parser = fr.getCSVParser();
     for (CSVRecord record : parser) { 
         String raterID = record.get("rater_id");
         String IMDB = record.get("movie_id");
         Double rating = Double.parseDouble(record.get("rating"));
         Rater rater = new EfficientRater(raterID);
         rater.addRating(IMDB,rating);
         rate.add(rater);
     }
     return rate;
    }
    
    public void testLoadRaters (){
        ArrayList<Rater> raters = loadRaters("ratings.csv");//
        ArrayList<Rater> Uniqueraters= new ArrayList<Rater>();
        ArrayList<String> maxRaters= new ArrayList<String>();
        ArrayList<String> maxMovs= new ArrayList<String>();
        HashMap <String,Integer> maxRater = new HashMap <String,Integer>();
        HashMap <String,Integer> maxMov = new HashMap <String,Integer>();
        String IDtofind = "193";
        String movieRated ="1798709";
        int counterMaxRater = 0;
        int countermaxMov = 0;
        //Uniqueraters.add(raters.get(0));
        for(int k=0;k<raters.size()-1;k++){
            if(!raters.get(k).getID().equals(raters.get(k+1).getID())){
                Uniqueraters.add(raters.get(k));
            }
            if(k==raters.size()-2){
                Uniqueraters.add(raters.get(k));
            }
        }
        
        System.out.println("Number of raters: "+Uniqueraters.size());
        for(Rater r : raters){
          ArrayList<String> items = r.getItemsRated();
          String ID = r.getID();
          if(!maxRater.containsKey(ID)){
              maxRater.put(ID,1);
          }
          else {
            maxRater.put(ID,maxRater.get(ID)+1);  
            }

          System.out.println("Rater ID: "+ID+"\t"+"Number of ratings: "+r.numRatings());
          for(int i=0;i<items.size();i++){
          String IDM =items.get(i);
          if(!maxMov.containsKey(IDM)){
              maxMov.put(IDM,1);
          }
          else {
            maxMov.put(IDM,maxMov.get(IDM)+1);  
            }
              System.out.println("Items rated: "+items.get(i)+"\t"+"Rating: "+r.getRating(items.get(i)));
         }
        }
        for (String s : maxRater.keySet()){//(int i=0; i<max.size();i++){
              
            if(counterMaxRater<maxRater.get(s)){
              counterMaxRater=maxRater.get(s);
            }
        }
        for (String s : maxRater.keySet()){
           if(maxRater.get(s)==counterMaxRater){
               maxRaters.add(s);
             }
        }
        for (String s : maxMov.keySet()){
              
            if(countermaxMov<maxMov.get(s)){
              countermaxMov=maxMov.get(s);
            }
        }
        for (String s : maxMov.keySet()){
           if(maxMov.get(s)==countermaxMov){
               maxMovs.add(s);
             }
        }
        System.out.println("El numero "+IDtofind+" se repite: "+maxRater.get(IDtofind)+" veces");
        System.out.println(maxRaters+" Number of ratings  "+counterMaxRater);
        System.out.println(maxMovs+" repeticiones de calificaciones a peliculas "+countermaxMov);
        System.out.println("La pelicula "+movieRated+" se evaluo: "+maxMov.get(movieRated)+" veces");
        System.out.println("Se evaluaron en total  "+maxMov.size()+" peliculas");
    }   
}
