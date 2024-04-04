import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Integer[] storages_f = {300,250,200};
        Integer[] consumers_f = {160, 120, 140, 200, 170};
        Integer[][] costs_f = {
                {1,4,2,1,3},
                {6,2,3,5,1},
                {2,3,4,1,4}
        };
        ArrayList<Integer> Storages = new ArrayList<>(List.of(storages_f));
        ArrayList<Integer> Consumers = new ArrayList<>(List.of(consumers_f));
        ArrayList<ArrayList<Integer>> Costs = new ArrayList<>();
        for (Integer[] c: costs_f){
            ArrayList<Integer> cs = new ArrayList<>(List.of(c));
            Costs.add(cs);
        }
        int sum_a = 0, sum_b = 0;
        for (Integer a: storages_f){
            sum_a += a;
        }
        for (Integer b: consumers_f){
            sum_b += b;
        }
        if (sum_a < sum_b) {
            int a = sum_b - sum_a;
            Storages.add(a);
            ArrayList<Integer> c = new ArrayList<>(Consumers.size());
            for (int i = 0; i < Consumers.size(); i++) {
                c.add(0);
            }
            Costs.add(c);
        } else if (sum_a > sum_b) {
            int b = sum_a - sum_b;
            Consumers.add(b);
            for(int i = 0; i < Storages.size(); i++){
                Costs.get(i).add(0);
            }
        }
        Table table = new Table(Storages, Consumers, Costs);
        ArrayList<ArrayList<Object>> tab = table.NorthWest();
        table.printTable();
        System.out.println("\nFullPrice = " + table.TransportationPrice());
        ArrayList<ArrayList<Object>> potentials = table.Potentials();
        for (ArrayList<Object> row: potentials){
            for (Object elem: row) {
                System.out.printf("%3s  ", elem.toString());
            }
            System.out.println();
        }
    }
}