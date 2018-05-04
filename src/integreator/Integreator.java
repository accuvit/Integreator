package integreator;

//import java.util.Random;
import java.awt.BasicStroke;
import java.awt.Color;
import java.util.Scanner;


import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;

/**
 *
 * @author laccu
 */
public class Integreator{
    
    
        
        
            
	
	
	public static double monteCarlo(int choice, int shots,double xmin,double xmax,XYSeriesCollection dataSet,double[] customFunction){
		//arguments:
                //[0][1]: xmin xmax
                //[2][3]: ymin ymax
                int sum=0;
		double arguments[] = new double[4];
                arguments[0] = xmin;
                arguments[1] = xmax;
                XYSeries dentro = new XYSeries("dentro");
                XYSeries fora = new XYSeries("fora");
                
                Function functionObj = new Function(choice,arguments,customFunction);
                
                XYSeries funcao = functionObj.getSeries();
                    
		
                for(int i = shots;i!=0;i--){    
                    double randx;
                    randx = (Math.random()*(arguments[1]-arguments[0]))+arguments[0];
                    double randy;
                    randy = (Math.random()*(arguments[3]-arguments[2]))+arguments[2];
                    if(choice==1){
                        if(randy < 0 && randy>=Function.func1(randx)){
                            dentro.add(randx,randy);
                            sum--;
                        }
                        else if(randy >= 0 && randy<=Function.func1(randx)){
                            dentro.add(randx,randy);
                            sum++;
                        }
                        else fora.add(randx,randy);
                    }
                    
                    else if(choice==2){
                        if(randy < 0 && randy>=Function.func2(randx)){
                            dentro.add(randx,randy);
                            sum--;
                        }
                        else if(randy >= 0 && randy<=Function.func2(randx)){
                            dentro.add(randx,randy);
                            sum++;
                        }
                        else fora.add(randx,randy);
                    }
                    else if(choice==3){
                        if(randy<0 && randy>=Function.funcCustom(randx, customFunction)){
                            dentro.add(randx,randy);
                            sum--;
                        }
                        else if(randy>=0&&randy<=Function.funcCustom(randx,customFunction)){
                            dentro.add(randx,randy);
                            sum++;
                        }
                        else fora.add(randx,randy);
                    }
                }
                
                dataSet.addSeries(dentro);
                dataSet.addSeries(fora);
                dataSet.addSeries(funcao);                    
                
                
                double proportion = (sum*1.0)/shots;
                double area = (arguments[1]-arguments[0])*(arguments[3]-arguments[2]);
                return proportion*area;
			
	}
	
	
	
	
	public static void main(String args[]){
		Scanner scan = new Scanner(System.in);
		int choice=1;
		int shots;
		double result;
                
                double xmin;
                double xmax;
                
                
                Integreator_gui gui = new Integreator_gui();
                gui.setVisible(true);
                
		
		//Menu - feito na época em que usavamos a linha de comando como input/output (v0.01)
//		while (true){
//			System.out.printf("--MENU--\n");
//			System.out.printf("Escolha uma das opções (Todas as integrais são de 0 a 10):\n");
//			System.out.printf("1: sin(x)\n");
//			System.out.printf("2: 5 - x^2\n");
//			System.out.printf("0: Sair\n\n");
//			
//			System.out.printf("Escolha uma opção: ");
//			choice = scan.nextInt();
//                        if(choice==0) break;
//                        
//			
//			System.out.printf("Insira a quantidade de pontos aleatórios a serem gerados: ");
//			shots = scan.nextInt();
//                        
//                        System.out.printf("Insira o limite inferior da integral: ");
//                        xmin = scan.nextDouble();
//                        System.out.printf("Insira o limite superior da integral: ");
//                        xmax = scan.nextDouble();
//			
//			//result = monteCarlo(choice,shots,xmin,xmax);
//			
//			System.out.printf(result+"     \n\n\n\n");
//			
//			
//			
//		}
	}
}