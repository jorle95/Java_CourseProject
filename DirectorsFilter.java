
public class DirectorsFilter implements Filter {
    private String[] Directors;

    
    public DirectorsFilter(String [] directors) {
        Directors=directors;

    }
    
    @Override
    public boolean satisfies(String id) {
     for (int k=0; k<Directors.length; k++){
        if (MovieDatabase.getDirector(id).indexOf(Directors[k])!=-1){
        return true;
     }
    }
     return false;
    }
}
