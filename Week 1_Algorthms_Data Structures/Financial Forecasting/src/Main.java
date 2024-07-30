public class Main {

    public static double predictFutureValue(double presentValue, double annualGrowthRate, int years) {
        if (years == 0) {
            return presentValue;
        }
        return predictFutureValue(presentValue * (1 + annualGrowthRate), annualGrowthRate, years - 1);
    }

    public static void main(String[] args) {
        double presentValue = 1000.0;
        double annualGrowthRate = 0.05;
        int years = 10;
        double futureValue = predictFutureValue(presentValue, annualGrowthRate, years);
        System.out.println("Predicted future value after " + years + " years: " + futureValue);
    }
}
