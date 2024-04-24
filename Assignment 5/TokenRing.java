package Assign5;

import java.util.Scanner;

public class TokenRing {

	
	public static void main(String[] args) {
		System.out.println("Welcome to Token Ring Algorithm");
		
		System.out.println("Enter How Many Nodes You Want");
		Scanner sc=new Scanner(System.in);
		
		int n=sc.nextInt();
		
		
		
		//show the nodes to client
		
		for(int i=0;i<n;i++) {
			System.out.print(i+"->");
		}
		
		System.out.print(0);
		
		//
		System.out.println();
		int choice=0;
		
		do {
			int token=0;
			//enter the sender
			System.out.println("enter the value of Sender");
			int sender=sc.nextInt();
			
		//enter the value of reciever..
			System.out.println("enter the value of reciever");
			int reciever=sc.nextInt();
			
			//Enter data
			
			System.out.println("enter the data ");
			int data=sc.nextInt();
			
			//token passing 
			System.out.println("token passing");
			
			for(int i=token;i<sender;i++) {
				System.out.print(" "+i+"->");
			}
			System.out.print(" "+sender);
			
			System.out.println("Sender sending data");
			
			//send data
			
			for(int i=sender;i<reciever;i=(i+1)%n) {
				System.out.println("data: "+data+"Forwarding...."+i);
			}
			
			
			System.out.println("reciever" + reciever+ " recieve the "+data+" data from  "+ sender);
			token=sender;
			
			System.out.println("Do you still want to continue,press 1 if not enter 0");
			choice=sc.nextInt();
			
			
			
		}while(choice==1);
		
		
	
	
		
	}
	
	
	
	
}
