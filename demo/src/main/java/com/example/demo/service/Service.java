package com.example.demo.service;
public class Service {
    public static String bankingmenu(){
        StringBuilder getMenu=new StringBuilder();
        // Scanner scanner=new Scanner(System.in);
        getMenu.append("*************************\n        K Banking       \n*************************\nWelcome to K Banking System\n0. Register\n1. Deposit\n2. Withdraw\n3. Check Balance\n4. Reset Pin\n5. Mini Statement");
        return getMenu.toString();
        // int choices = scanner.nextInt();
        // switch (choices) {
        //     case 0:
        //         System.out.println("You selected: Register");
        //         break;
        //     case 1:
        //         System.out.println("You selected: Deposit");
        //         break;
        //     case 2:
        //         System.out.println("You selected: Withdraw");
        //         break;
        //     case 3:
        //         System.out.println("You selected: Check balance");
        //         break;
        //     case 4:
        //         System.out.println("You selected: Reset Pin");
        //         break;
        //     case 5:
        //         System.out.println("You selected: Mini Statement");
        //         break;
        //     default:
        //         System.out.println("Imput a valid option");
        // }
        // System.out.println(getMenu);
    }
    
}
