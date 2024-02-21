public class task4 {
    private int x1, y1, x2, y2, r1, r2;

    public void setX1(int x1) {
        this.x1 = x1;
    }
    public void setX2(int x2) {
        this.x2 = x2;
    }
    public void setR1(int r1) {
        this.r1 = r1;
    }
    public void setY1(int y1) {
        this.y1 = y1;
    }
    public void setR2(int r2) {
        this.r2 = r2;
    }
    public void setY2(int y2) {
        this.y2 = y2;
    }

    public int task() {
        int x1 = this.x1, x2 = this.x2, y1 = this.y1, y2 = this.y2, r1 = this.r1, r2 = this.r2;
        double D = Math.pow((Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2)), 0.5);
        if (r1 + r2 == D) {
            System.out.print("Окружности пересекаются в 1 точке. Возвращенное значение - ");
            return 1;
        } else if (r1 + r2 > D && D > 0) {
            System.out.print("Окружности пересекаются в 2 точках. Возвращенное значение - ");
            return 2;
        } else if (r1 == r2 && D == 0) {
            System.out.print("Окружности совпадают. Возвращенное значение - ");
            return 3;
        } else if (r1 + r2 < D && D > 0) {
            System.out.print("Окружности не пересекаются и не вложены друг в друга. Возвращенное значение - ");
            return 4;
        } else if (D < r1 && r2 < r1) {
            System.out.print("Вторая окружность вложена в первую. Возвращенное значение - ");
            return 5;
        } else if (D < r2 && r1 < r2) {
            System.out.print("Первая окружность вложена во вторую. Возвращенное значение - ");
            return 6;
        } else {
            System.out.print("Ситуация не подходит ни под один из случаев. Возвращенное значение - ");
            return 0;
        }
    }
}
