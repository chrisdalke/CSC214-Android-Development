////////////////////////////////////////
// Chris Dalke
// cdalke@u.rochester.edu
// CSC 214
// TA: Mariana Kim
////////////////////////////////////////
// Assignment 9
////////////////////////////////////////

package csc214.assignment09.Model;

////////////////////////////////////////
// Calculation Handler
////////////////////////////////////////

public class CalculationHandler {

    public static long calculateLargestPrime(long ceiling){
        long largestPrime = 0;
        for (long i = 0; i <= ceiling; i++){
            if (isPrime(i)){
                if (i > largestPrime){
                    largestPrime = i;
                }
            }
        }

        return largestPrime;
    }

    public static long calculateSquareRoot(long number){
        for (long i = 0; i <= number; i++){
            if (i*i >= number){
                return i;
            }
        }
        return 0;
    }

    public static boolean isPrime(long num){
        boolean isPrime = true;
        for (long i = 2; i < Math.ceil(Math.sqrt(num)); i++){
            if (num % i == 0){
                isPrime = false;
            }
        }
        return isPrime;
    }
}

////////////////////////////////////////
// End of File
////////////////////////////////////////
