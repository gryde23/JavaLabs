import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Integer[] storages_f = {200,150,350};
        Integer[] consumers_f = {120, 120, 200, 180, 110};
        Integer[][] costs_f = {
                {1,2,3,5,2},
                {4,6,7,3,1},
                {2,2,3,4,5}
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

//        for(ArrayList<Object> row: Cut_table){
//            for (Object elem: row) {
//                System.out.printf("%3s  ", elem.toString());
//            }
//            System.out.println();
//        }
        while (table.searching()){
            table.rebaseTable();
            table.Potentials();
            table.printTable();
            System.out.println("\nPotentials");
            potentials = table.Potentials();
            for (ArrayList<Object> row: potentials){
                for (Object elem: row) {
                    System.out.printf("%3s  ", elem.toString());
                }
                System.out.println();
            }
            System.out.println("\nprice = " + table.TransportationPrice());
        }
//        table.searchCycle(Cut_table);
//        table.rebaseTable();
//        table.printTable();
//        System.out.println("\nprice = " + table.TransportationPrice());
//        Cut_table = table.tableCut();
//        table.searchCycle(Cut_table);
//        table.rebaseTable();
//        table.printTable();
    }
}