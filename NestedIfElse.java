import java.util.Scanner;

public class NestedIfElse {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Marks :");
        int marks = sc.nextInt();

        if(marks >= 95) {
            System.out.println("Grade O");
        }
        else{
            if(marks>=80){
                System.out.println("Grade A+");
            }
            else{
                if(marks>=65){
                    System.out.println("Grade A");
                }
                else{
                    if(marks>=50){
                        System.out.println("Grade B");
                    }
                    else{
                        if(marks>=35){
                            System.out.println("Grade C");
                        }
                        else{
                            System.out.println("Fail");
                        }
                    }
                }
            }
        }
        
        sc.close();
    }
    
}
