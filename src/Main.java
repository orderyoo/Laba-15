import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("x: ");
        double x = scanner.nextDouble();
        ExpressionCalculator calculator = new ExpressionCalculator(x);
        System.out.println(calculator.getY());
        System.out.print("action: ");
        String action = scanner.next();
        if(action.equals("save")){
            calculator.saveState();
        }
        else if(action.equals("load")){
            ExpressionCalculator calculator1 = ExpressionCalculator.loadState();
            System.out.println(calculator1.getY());
        }
        else {
            System.out.println("Invalid command");
        }
    }
}
class ExpressionCalculator implements Serializable {
    private double x;
    private double y;
    public ExpressionCalculator(double x) {
        this.x = x;
        this.y = calculateExpression();
    }
    public double calculateExpression() {
        return x - Math.sin(x);
    }
    public void saveState() {
        try {
            FileOutputStream fileOut = new FileOutputStream("state.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
            System.out.println("Object state saved in state.ser");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static ExpressionCalculator loadState() {
        ExpressionCalculator obj = null;
        try {
            FileInputStream fileIn = new FileInputStream("state.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            obj = (ExpressionCalculator) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("Object state loaded from state.ser");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return obj;
    }
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
}