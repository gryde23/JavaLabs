
import java.util.ArrayList;

public class Table {

    private ArrayList<Integer> Storages;
    private ArrayList<Integer> Consumers;
    private ArrayList<ArrayList<Integer>> Costs;
    private ArrayList<ArrayList<Object>> table = new ArrayList<>();
    private ArrayList<ArrayList<Object>> potentials = new ArrayList<>();
    public Table(ArrayList<Integer> Storages, ArrayList<Integer> Consumers, ArrayList<ArrayList<Integer>> Costs) {
        this.Consumers = Consumers;
        this.Storages = Storages;
        this.Costs = Costs;
    }

    public void printTable(){
        System.out.print(" ".repeat(5));
        for (Integer b: Consumers){
            System.out.printf("%3d  ", b);
        }
        for (int i = 0; i < Storages.size(); i++) {
            System.out.printf("\n%3d  ", Storages.get(i));
            for (int j = 0; j < Consumers.size();j++){
                System.out.printf("%3s  ", table.get(i).get(j).toString());
            }
        }
    }

    public int TransportationPrice(){
        int price = 0;
        for (int i = 0; i < table.size(); i++){
            ArrayList<Object> row = table.get(i);
            ArrayList<Integer> cost = Costs.get(i);
            for(int j = 0; j < row.size(); j++) {
                if (row.get(j).equals("-")) continue;
                price += (int) row.get(j) * cost.get(j);
            }
        }
        return price;
    }


    public ArrayList<ArrayList<Object>> NorthWest(){
        ArrayList<Integer> Storages = new ArrayList<>(this.Storages);
        ArrayList<Integer> Consumers = new ArrayList<>(this.Consumers);
        for(int i = 0; i < Storages.size(); i++) {
            int a = Storages.get(i);
            ArrayList<Object> row = new ArrayList<>();
            for (int j = 0; j < Consumers.size(); j++) {
                int b = Consumers.get(j);
                if (a > b && b > 0) {
                    row.add(b);
                    Consumers.set(j, 0);
                    Storages.set(i, a - b);
                    a -= b;
                } else if (a > 0 && a <= b) {
                    row.add(a);
                    Consumers.set(j, b - a);
                    Storages.set(i, 0);
                    a -= a;
                } else row.add("-");
            }
            table.add(row);
        }
        return table;
    }
    public ArrayList<ArrayList<Object>> Potentials(){
        ArrayList<Integer> u = new ArrayList<>(Storages.size());
        ArrayList<Integer> v = new ArrayList<>(Consumers.size());
        u.add(0);
        for (int i = 0; i < Consumers.size(); i++) {
            v.add(null); // Добавляем null для каждого элемента, чтобы увеличить размер списка
        }
        for (int i = 1; i < Storages.size(); i++) {
            u.add(null); // Добавляем null для каждого элемента, чтобы увеличить размер списка
        }
        for (int i = 0; i < table.size(); i++){
            ArrayList<Object> row = table.get(i);
            ArrayList<Integer> cost_row = this.Costs.get(i);
            for (int j = 0; j < row.size(); j++) {
                if (row.get(j) == "-") continue;
                if (u.get(i) != null && v.get(j) == null) {
                    int c = cost_row.get(j) - u.get(i);
                    v.set(j, c);
                }
                else{
                    int c = cost_row.get(j) - v.get(j);
                    u.set(i, c);
                }
            }
        }
        for (int i = 0; i < Storages.size(); i++) {
            ArrayList<Object> row = new ArrayList<>(Consumers.size());
            for (int j = 0; j < Consumers.size(); j++) {
                row.add("-");
            }
            potentials.add(row);
        }
        for(int i = 0; i < Storages.size(); i++){
            ArrayList<Object> row = table.get(i);
            ArrayList<Integer> cost_row = this.Costs.get(i);
            ArrayList<Object> potential_row = potentials.get(i);
            for(int j = 0; j < Consumers.size(); j++){
                if(row.get(j) != "-") continue;
                int p = cost_row.get(j) - (u.get(i) + v.get(j));
                potential_row.set(j, p);
            }
        }
        return potentials;
    }
}
