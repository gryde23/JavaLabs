
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;

public class Table {

    private ArrayList<Integer> Storages;
    private ArrayList<Integer> Consumers;
    private ArrayList<ArrayList<Integer>> Costs;
    private ArrayList<ArrayList<Object>> table = new ArrayList<>();
    private ArrayList<ArrayList<Object>> potentials = new ArrayList<>();
    private ArrayList<Pair> Cycle_begin = new ArrayList<>();
    private ArrayList<Integer> Cycle = new ArrayList<>();
    private ArrayList<Pair> Indexies = new ArrayList<>();
    enum Direction{
        up, down, right, left
    }
    public Table(ArrayList<Integer> Storages, ArrayList<Integer> Consumers, ArrayList<ArrayList<Integer>> Costs) {
        this.Consumers = Consumers;
        this.Storages = Storages;
        this.Costs = Costs;
    }

    public void printTable() {
        System.out.print(" ".repeat(5));
        for (Integer b : Consumers) {
            System.out.printf("%3d  ", b);
        }
        for (int i = 0; i < Storages.size(); i++) {
            System.out.printf("\n%3d  ", Storages.get(i));
            for (int j = 0; j < Consumers.size(); j++) {
                System.out.printf("%3s  ", table.get(i).get(j).toString());
            }
        }
    }

    public ArrayList<ArrayList<Object>> getPotentials() {
        return potentials;
    }

    public void checkTable(){
        int count = 0;
        for (ArrayList<Object> row: table){
            for (Object elem: row){
                if (elem == "-") continue;
                ArrayList<Pair> neighbours = neighbours(table.indexOf(row), row.indexOf(elem));
                count++;
                if (neighbours.isEmpty()) {
                    if (table.indexOf(row) != 0) {
                        int i = table.indexOf(row) - 1;
                        table.get(i).set(row.indexOf(elem), 0);
                        count++;
                    }
                    else if (table.indexOf(row) != Storages.size() - 1){
                        int i = table.indexOf(row) + 1;
                        table.get(i).set(row.indexOf(elem), 0);
                        count++;
                    }
                }
            }
        }
        if (count > Storages.size() + Consumers.size() - 1) {
            for (ArrayList<Object> row: table){
                for (Object elem: row){
                    if (elem == "-") continue;
                    if ((Integer) elem == 0) row.set(row.indexOf(elem), "-");
                }
            }
        }
    }
    public ArrayList<Integer> getCycle() {
        return Cycle;
    }

    public ArrayList<Pair> getIndexies() {
        return Indexies;
    }

    public int TransportationPrice() {
        int price = 0;
        for (int i = 0; i < table.size(); i++) {
            ArrayList<Object> row = table.get(i);
            ArrayList<Integer> cost = Costs.get(i);
            for (int j = 0; j < row.size(); j++) {
                if (row.get(j).equals("-")) continue;
                price += (int) row.get(j) * cost.get(j);
            }
        }
        return price;
    }

    public ArrayList<ArrayList<Object>> NorthWest() {
        ArrayList<Integer> Storages = new ArrayList<>(this.Storages);
        ArrayList<Integer> Consumers = new ArrayList<>(this.Consumers);
        for (int i = 0; i < Storages.size(); i++) {
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
        for (int i = 0; i < Storages.size(); i++) {
            for (int j = 0; j < Consumers.size(); j++){
                if (table.get(i).get(j) == "-") continue;
                if (i > 0 && j > 0) {
                    if (table.get(i-1).get(j) == "-" && table.get(i).get(j - 1) == "-") {
                        table.get(i-1).set(j, 0);
                    }
                }
            }
        }
        return table;
    }

    public ArrayList<ArrayList<Object>> Potentials() {
        checkTable();
        ArrayList<Integer> u = new ArrayList<>(Storages.size());
        ArrayList<Integer> v = new ArrayList<>(Consumers.size());
        u.add(0);
        for (int i = 0; i < Consumers.size(); i++) {
            v.add(null); // Добавляем null для каждого элемента, чтобы увеличить размер списка
        }
        for (int i = 1; i < Storages.size(); i++) {
            u.add(null); // Добавляем null для каждого элемента, чтобы увеличить размер списка
        }
        boolean flag = false;
        p:while (true) {
            p1:
            for (int i = 0; i < table.size(); i++) {
                ArrayList<Object> row = table.get(i);
                ArrayList<Integer> cost_row = this.Costs.get(i);
                for (int j = 0; j < row.size(); j++) {
                    if (row.get(j) == "-") continue;
                    if (u.get(i) == null && v.get(j) == null && flag) {
                        table.get(i - 1).set(j, 0);
                        flag = false;
                        continue p;
                    }
                    if (u.get(i) == null && v.get(j) == null) continue;
                    if (u.get(i) != null && v.get(j) == null) {
                        int c = cost_row.get(j) - u.get(i);
                        v.set(j, c);
                    } else {
                        int c = cost_row.get(j) - v.get(j);
                        u.set(i, c);
                    }
                }
            }
            p2:
            for (int i = Storages.size() - 1; i >= 0; i--) {
                ArrayList<Object> row = table.get(i);
                ArrayList<Integer> cost_row = this.Costs.get(i);
                for (int j = Consumers.size() - 1; j >= 0; j--) {
                    if (row.get(j) == "-") continue;
                    if (u.get(i) == null && v.get(j) == null) {
                        flag = true;
                        continue p;
                    }
                    if (u.get(i) != null && v.get(j) == null) {
                        int c = cost_row.get(j) - u.get(i);
                        v.set(j, c);
                    } else {
                        int c = cost_row.get(j) - v.get(j);
                        u.set(i, c);
                    }
                }
            }
            break ;
        }
        potentials = new ArrayList<>();
        Cycle_begin = new ArrayList<>();
        for (int i = 0; i < Storages.size(); i++) {
            ArrayList<Object> row = new ArrayList<>(Consumers.size());
            for (int j = 0; j < Consumers.size(); j++) {
                row.add("-");
            }
            potentials.add(row);
        }
        for (int i = 0; i < Storages.size(); i++) {
            ArrayList<Object> row = table.get(i);
            ArrayList<Integer> cost_row = this.Costs.get(i);
            ArrayList<Object> potential_row = potentials.get(i);
            for (int j = 0; j < Consumers.size(); j++) {
                if (row.get(j) != "-") continue;
                int p = cost_row.get(j) - (u.get(i) + v.get(j));
                potential_row.set(j, p);
            }
        }
        int min = 0;
        for (ArrayList<Object> row : potentials) {
            for (Object elem : row) {
                if (elem == "-") continue;
                Integer e = (Integer) elem;
                if (e < min) {
                    min = e;
                }
            }
        }
        for (ArrayList<Object> row: potentials){
            for (Object elem: row){
                if (elem == "-" || (Integer) elem != min) continue;
                Cycle_begin.add(new Pair(potentials.indexOf(row), row.indexOf(elem)));
            }
        }
        return potentials;
    }

    public ArrayList<ArrayList<Object>> tableCut() {
        ArrayList<ArrayList<Object>> Cut_table = new ArrayList<>(table);
        for (ArrayList<Object> row : table) {
            ArrayList<Object> copy = new ArrayList<>(row);
            Cut_table.set(table.indexOf(row), copy);
        }
        ArrayList<Integer> Cut_rows = new ArrayList<>();
        ArrayList<Integer> Cut_columns = new ArrayList<>();
        cut:
        while (true) {
            for (ArrayList<Object> row : Cut_table) {
                int count = 0;
                for (Object elem : row) {
                    if (elem == "-") continue;
                    count++;
                }
                if (count < 2 && !Cut_rows.contains(Cut_table.indexOf(row))) {
                    for (Object elem : row) {
                        row.set(row.indexOf(elem), "-");
                    }
                    Cut_rows.add(Cut_table.indexOf(row));
                    continue cut;
                }
            }
            for (int j = 0; j < Consumers.size(); j++) {
                int count = 0;
                for (int i = 0; i < Storages.size(); i++) {
                    if (Cut_table.get(i).get(j) == "-") continue;
                    count++;
                }
                if (count < 2 && !Cut_columns.contains(j)) {
                    for (int i = 0; i < Storages.size(); i++) {
                        Cut_table.get(i).set(j, "-");
                    }
                    Cut_columns.add(j);
                    continue cut;
                }
            }
            break;
        }
        return Cut_table;
    }

    public boolean searching(){
        for (Pair index: Cycle_begin){
            if (searchCycle(index.getFirst(), index.getSecond())) return true;
        }
        return false;
    }
    public boolean searchCycle(Integer cycle_begin_i, Integer cycle_begin_j) {
        table.get(cycle_begin_i).set(cycle_begin_j, 0);
        ArrayList<ArrayList<Object>> Cut_table = tableCut();
        Cycle = new ArrayList<>();
        Indexies = new ArrayList<>();
        Cycle.add((Integer) table.get(cycle_begin_i).get(cycle_begin_j));
        Indexies.add(new Pair(cycle_begin_i, cycle_begin_j));
        boolean up = false, down = false, right = false, left = false;
        int row = cycle_begin_i, column = cycle_begin_j;
        search:
        while (true) {
            if (row == cycle_begin_i && column == cycle_begin_j && Cycle.size() >= 4) {
                for (int i = 1; i < Cycle.size(); i += 2){
                    if (Cycle.get(i) == 0) {
                        table.get(cycle_begin_i).set(cycle_begin_j, "-");
                        return false;
                    }
                }
                Cycle.removeLast();
                Indexies.removeLast();
                return true;
            }
            if (!up) {
                for (int i = row - 1; i >= 0; i--) { // go up
                    if (Cut_table.get(i).get(column) == "-") continue;
                    down = true;
                    left = false;
                    right = false;
                    Cycle.add((Integer) Cut_table.get(i).get(column));
                    Indexies.add(new Pair(i, column));
                    row = i;
                    continue search;
                }
            }
            if (!down) {
                for (int i = row + 1; i < Storages.size(); i++) { // go down
                    if (Cut_table.get(i).get(column) == "-") continue;
                    up = true;
                    left = false;
                    right = false;
                    Cycle.add((Integer) Cut_table.get(i).get(column));
                    Indexies.add(new Pair(i, column));
                    row = i;
                    continue search;
                }
            }
            if (!right) {
                for (int j = column + 1; j < Consumers.size(); j++) { // go right
                    if (Cut_table.get(row).get(j) == "-") continue;
                    left = true;
                    up = false;
                    down = false;
                    Cycle.add((Integer) Cut_table.get(row).get(j));
                    Indexies.add(new Pair(row, j));
                    column = j;
                    continue search;
                }
            }
            if (!left) {
                for (int j = column - 1; j >= 0; j--) { // go left
                    if (Cut_table.get(row).get(j) == "-") continue;
                    right = true;
                    up = false;
                    down = false;
                    Cycle.add((Integer) Cut_table.get(row).get(j));
                    Indexies.add(new Pair(row, j));
                    column = j;
                    continue search;
                }
            }
        }
    }
    public void rebaseTable(){
        int min = Cycle.get(1);
        for (int i = 1; i < Cycle.size(); i += 2){
            if (Cycle.get(i) < min) min = Cycle.get(i);
        }
        for (int i = 0; i < Cycle.size(); i++){
            int elem = Cycle.get(i);
            if (i % 2 == 0) Cycle.set(i, elem + min);
            else {
                Cycle.set(i, elem - min);
                if (elem - min == 0) {
                    Cycle.set(i, null);
                }
            }
        }
        for (int i = 0; i < Cycle.size(); i++){
            int row = Indexies.get(i).getFirst();
            int column = Indexies.get(i).getSecond();
            if (Cycle.get(i) == null) table.get(row).set(column, "-");
            else table.get(row).set(column, Cycle.get(i));
        }
        checkTable();
    }
    private ArrayList<Pair> neighbours(Integer row, Integer column){
        ArrayList<Pair> neighbours = new ArrayList<>();
        for(Direction dir: Direction.values()){
            switch(dir){
                case up -> {
                    for(int i = row - 1; i >= 0; i--){
                        if (table.get(i).get(column) == "-") continue;
                        neighbours.add(new Pair(i, column));
                        break;
                    }
                }
                case down -> {
                    for (int i = row + 1; i < Storages.size(); i++){
                        if (table.get(i).get(column) == "-") continue;
                        neighbours.add(new Pair(i, column));
                        break;
                    }
                }
                case right -> {
                    for (int j = column + 1; j < Consumers.size(); j++) {
                        if (table.get(row).get(j) == "-") continue;
                        neighbours.add(new Pair(row, j));
                        break;
                    }
                }
                case left -> {
                    for (int j = column - 1; j >= 0; j--) {
                        if (table.get(row).get(j) == "-") continue;
                        neighbours.add(new Pair(row, j));
                        break;
                    }
                }
            }
        }
        return neighbours;
    }
}
