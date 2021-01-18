
public class minutesFilter implements Filter {
    private int min;
    private int max;
    
    public minutesFilter(int minMin, int maxMin) {
        min=minMin;
        max=maxMin;
    }
    
    @Override
    public boolean satisfies(String id) {
        if (MovieDatabase.getMinutes(id)<=max && MovieDatabase.getMinutes(id)>= min){
        return true;
     }
     return false;
    }
}
