
package CalculatingSales;

/**
 *
 * @author John Kirch, CSC 240, Bruce Haddon, 2/5/17
 */
import java.util.Scanner;

public class CalculatingSales {
    
    int quantity; // quantity input from user
    int prodNum; // product input from user
    int count1; // count of product 1
    int count2; // count of product 2
    int count3; // count of product 3
    int count4; // count of product 4
    int count5; // count of product 5
    private double totalProd; // value of all products purchased
    static double product1 = 2.98;
    static double product2 = 4.50;
    static double product3 = 9.98;
    static double product4 = 4.49;
    static double product5 = 6.87;
    
    Scanner input = new Scanner(System.in);
    
    public void calculatingSales () {
        
        do {
            
            System.out.println("Enter a product number from 1 to 5(or 0 to stop): ");
            prodNum = input.nextInt();
            if (prodNum == 0) // end do...while loop and return product values
                break;
            
            System.out.println("Enter quantity ordered: ");
            quantity = input.nextInt();
            // switch statement for counting quantity and total of each product
            switch (prodNum) {
                    
                case 1:
                    count1 = count1 + quantity;
                    totalProd = totalProd + product1 * quantity;
                    break;
                    
                case 2:
                    count2 = count2 + quantity;
                    totalProd = totalProd + product2 * quantity;
                    break;
                    
                case 3:
                    count3 = count3 + quantity;
                    totalProd = totalProd + product3 * quantity;
                    break;
                    
                case 4:
                    count4 = count4 + quantity;
                    totalProd = totalProd + product4 * quantity;
                    break;
                    
                case 5:
                    count5 = count5 + quantity;
                    totalProd = totalProd + product5 * quantity;
                    break;
                    
                default:
                    System.out.println("No product availble with that ID. Please continue.");
            }
            
        } while (quantity != 0);
        
        // print out of all items ordered, quantity, price, and extension; along with final total
        System.out.println("     Item     | Quantity |  Price  | Extenstion |");
        System.out.println("------------------------------------------------|");
       
            if (count1 > 0)
                System.out.printf("%14s|%10d|$%8.02f|$%11.02f|%n","SOAP", count1, product1, (double) count1 * product1);
            if (count2 > 0)
                System.out.printf("%14s|%10d|$%8.02f|$%11.02f|%n","SHAMPOO", count2, product2, (double) count2 * product2);
            if (count3 > 0)
                System.out.printf("%14s|%10d|$%8.02f|$%11.02f|%n","STEAK", count3, product3, (double) count3 * product3);
            if (count4 > 0)
                System.out.printf("%14s|%10d|$%8.02f|$%11.02f|%n","POTATOES", count4, product4, (double) count4 * product4);
            if (count5 > 0)
                System.out.printf("%14s|%10d|$%8.02f|$%11.02f|%n","CANDY", count5, product5, (double) count5 * product5);
       
       System.out.println("------------------------------------------------|");
       System.out.printf("%35s|%12.02f|%n%n", "Total for Order ", totalProd);
    }
}
