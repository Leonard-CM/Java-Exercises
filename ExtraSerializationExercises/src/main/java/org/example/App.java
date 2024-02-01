package org.example;
public class App 
{
    public static void main( String[] args )
    {
        //array of integers
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        // sumEvenNumber method call to store results
        int result = sumEvenNumbers(numbers);
        //prints sums of results
        System.out.println("Total Sum of even numbers: " + result);
    }
    //calculates the sum of even numbers in an array
    public static int sumEvenNumbers(int[] arr) {
        //Variable to store the sum of even numbers
        int sum = 0;
        // Iterate through each element in the array
        for (int num : arr) {
            // Check if the current number is even
            if (num % 2 == 0) {
                //add it to the sum,if even
                sum += num;
            }
        }
        // Return the final sum of even numbers
        return sum;
    }
}
