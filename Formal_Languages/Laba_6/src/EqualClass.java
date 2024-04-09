import java.util.ArrayList;
import java.util.Objects;

public class EqualClass {
    private ArrayList<String> states;
    private String name;
    public EqualClass(ArrayList<String> states, String name){
        this.states = states;
        this.name = name;
    }

    public ArrayList<String> getStates() {
        return states;
    }

    public String getName() {
        return name;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        EqualClass that = (EqualClass) obj;
        return Objects.equals(states, that.states) && Objects.equals(name, that.name);
    }
    @Override
    public int hashCode() {
        return Objects.hash(states, name);
    }


}
