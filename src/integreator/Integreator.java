/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integreator;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author laccu
 */
public class Integreator{
	
	
	public static double monteCarlo(int choice, int shots,double xmin,double xmax){
		//arguments:
                //[0][1]: xmin xmax
                //[2][3]: ymin ymax
                int sum=0;
		double arguments[] = new double[4];
                arguments[0] = xmin;
                arguments[1] = xmax;
                if(choice==1) function1MinMax(arguments);
                if(choice==2) function2MinMax(arguments);
		
                for(int i = shots;i!=0;i--){
                    double randx = (Math.random()*(arguments[1]-arguments[0]))+arguments[0];
                    double randy = (Math.random()*(arguments[3]-arguments[2]))+arguments[2];
                    if(choice==1){
                        if(randy < 0 && randy>=function1(randx))sum--;
                        if(randy >= 0 && randy<=function1(randx))sum++;
                    }
                    
                    if(choice==2){
                        if(randy < 0 && randy>=function2(randx))sum--;
                        if(randy >= 0 && randy<=function2(randx))sum++;
                    }
                    
                }
                
                double proportion = (sum*1.0)/shots;
                double area = (arguments[1]-arguments[0])*(arguments[3]-arguments[2]);
                return proportion*area;
			
	}
	
	public static void function1MinMax(double args[]){
		double ymax=Double.MIN_VALUE,ymin=Double.MAX_VALUE;
                
                for(double i=args[0];i<=args[1];i+=0.01){
                    if(function1(i)>ymax) ymax=function1(i);
                    if(function1(i)<ymin) ymin=function1(i);
                }
                
                args[2]=ymin;
                args[3]=ymax;
	}
        
        public static double function1(double x){
            return Math.sin(x);
        }
        
        public static void function2MinMax(double args[]){
            double ymax=Double.MIN_VALUE,ymin=Double.MAX_VALUE;
                
                for(double i=args[0];i<=args[1];i+=0.01){
                    if(function2(i)>ymax) ymax=function2(i);
                    if(function2(i)<ymin) ymin=function2(i);
                }
                
                args[2]=ymin;
                args[3]=ymax;
        }
        
        public static double function2(double x){
            return 5-(x*x);
        }
	
	
	
	
	public static void main(String args[]){
		Scanner scan = new Scanner(System.in);
		int choice=1;
		int shots;
		double result;
                
                double xmin;
                double xmax;
		
		//Menu
		while (true){
			System.out.printf("--MENU--\n");
			System.out.printf("Escolha uma das opções (Todas as integrais são de 0 a 10):\n");
			System.out.printf("1: sin(x)\n");
			System.out.printf("2: 5 - x^2\n");
			System.out.printf("0: Sair\n\n");
			
			System.out.printf("Escolha uma opção: ");
			choice = scan.nextInt();
                        if(choice==0) break;
                        
			
			System.out.printf("Insira a quantidade de pontos aleatórios a serem gerados: ");
			shots = scan.nextInt();
                        
                        System.out.printf("Insira o limite inferior da integral: ");
                        xmin = scan.nextDouble();
                        System.out.printf("Insira o limite superior da integral: ");
                        xmax = scan.nextDouble();
			
			result = monteCarlo(choice,shots,xmin,xmax);
			
			System.out.printf(result+"     \n\n\n\n");
			
			
			
		}
	}
}