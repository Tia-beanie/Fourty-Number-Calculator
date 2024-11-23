/**
 * Program Name: Fourty Digits Calculator
 * Author:       Grace Dong
 * Date:         Nov 22, 2024
 * Course:       CPSC1150
 * Compiler:     JDK 22.0.2
 */

import java.util.Scanner;

public class FourtyNumCalculator{

    /**
     * The main method prompt user to enter two positive number up to 40 digits, verify the input and call the calculator then display the result.
     * @param args
     */
    public static void main(String args[]){

        Scanner scan = new Scanner(System.in);
        String num1, num2, result;

        //keep prompting user for number 1, while verify() method returns true
        do{      
            System.out.print("Enter Number 1 (positive integer number up to 40 digits): ");
            num1 = scan.nextLine();

        }while(verify(num1));

        //keep propmting user for number 2, while verify() method returns true
        do{
            System.out.print("Enter Number 2(positive integer number up to 40 digits): ");
            num2 = scan.nextLine();

        }while(verify(num2));
        
        //Invoke calculator() method, pass in the inputs num1 and num2
        result = calculator(num1, num2);
        
        //Invoke display() method, display output
        display(num1, num2, result);

    }

    //verify() method receives a str, check 3 conditions and return a boolean
    public static boolean verify(String str){

        //check if the input is empty
        if(str.length() == 0){
            System.out.println("Error: the input can't be empty. ");
            return true;
        }
        //check if the input is longer than 40
        if(str.length() > 40){
            System.out.println("Error: the input can't be longer than 40 digits.");
            return true;
        }
        //check if the input is digit
        for(int i = 0; i < str.length(); i++){
            char ch = str.charAt(i);
            if(ch < '0' || ch > '9'){
                System.out.println("Error: the input must be digit from 0 - 9.");
                return true;
            }
        }

        return false;
    }

    //calculator() method receives num1 and num2, conduct calculation and return result
    public static String calculator(String str1, String str2){

        int s1 = str1.length();
        int s2 = str2.length();

        //decide on the shorter str, and pad '0's in front of it
        if(s1 > s2){
            for(int i = 0; i < (s1 - s2) ; i++){
                str2 = '0' + str2;
            }
        }          
        else if(s2 > s1){
            for(int i = 0; i < (s2 - s1); i++){
                str1 = '0' + str1;
            }
        }

        String result = "";
        int nextDigit;
        int carry = 0;  //initiate the carry as 0

        //for loop iterates from right to left, add 2 digits, append it to result, and decide the carry to be 0 or 1
        for(int i = str1.length() - 1; i >= 0; i--){

            //when index is not 0, each cycle the result is single digit
            if(i > 0){
               
                nextDigit = (str1.charAt(i) - '0') + (str2.charAt(i) - '0') + carry;
                           
                if(nextDigit >= 10){
                    result = nextDigit % 10 + result; //use remainder to get the single digit
                    carry = 1; //if the sum is bigger than 9, set carry to 1
                }
                else{
                    result = nextDigit + result;
                    carry = 0; //if the sum is less than 9, set carry to 0
                }  
            }

            //when it comes to the last digit, the result is not necessarily single digit
            else if(i == 0){

                nextDigit = (str1.charAt(i) - '0') + (str2.charAt(i) - '0') + carry;
                result = nextDigit + result;
            }
        }   

        return result;          
    }

    //the method receives str1, str2 and result, formats them and display output
    public static void display(String str1, String str2, String result){

        //invoke format() method to formats strings before display
        str1 = format(str1);
        str2 = format(str2);
        result = format(result);

        int l1 = str1.length();
        int l2 = str2.length();
        int l3 = result.length();

        //add padding spaces in front of str1 and str2
        System.out.println(" ".repeat(l3-l1) + str1 + " +");
        System.out.println(" ".repeat(l3-l2) + str2);
        System.out.println("-".repeat(l3));
        System.out.println(result);
    }

    //this method seperates the string with commas and return it
    public static String format(String str){
        
        String styledStr = "";
        int count = 1;

        for(int i = str.length() - 1; i >= 0; i--){
    
            //when the count is multiple of 3, but not the first or last digit, adds ',' in front
            if(count % 3 == 0 && i != str.length()-1 && i != 0){
                
                styledStr = str.charAt(i) + styledStr;
                styledStr = ',' + styledStr;

            }else{
                styledStr = str.charAt(i) + styledStr;
            }

            count++;
        }       
        return styledStr;
    }
}