/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integreator;

import org.jfree.data.xy.XYSeries;

/**
 *
 * @author Jubs
 */
public class Function {
    public int function;
    public double xmin;
    public double xmax;
    public double ymin;
    public double ymax;
    public XYSeries series;
    public double[] custom;
    public double[] args;

    
    public Function(int function,double[] args,double[] custom){
        this.function = function;
        this.series = new XYSeries("Funcao");
        this.custom = custom;
        this.args = args;
        
        functionMinMax(this.function,this.args,this.series,this.custom); //atualiza args[] com ymin e ymax, também aproveita o ciclo e popula o XYSeries
        
        
        
        
    }
    
    //Alguns getters/setters
    public double[] getArgs(){
        return this.args;
    }
    
    public void setArgs(double[] in){
        this.args = in;
    }
    
    public XYSeries getSeries(){
        return this.series;
    }
    
    
    
    
	
    private static void functionMinMax(int choice, double args[],XYSeries funcao,double[] custom){
            double ymax=Double.MIN_VALUE,ymin=Double.MAX_VALUE;
            
            if(choice==1){

                for(double i=args[0];i<=args[1];i+=0.01){
                    if(func1(i)>ymax) ymax=func1(i);
                    if(func1(i)<ymin) ymin=func1(i);
                    funcao.add(i,func1(i));
                }
            }
            
            else if(choice==2){
                for(double i=args[0];i<=args[1];i+=0.01){
                    if(func2(i)>ymax) ymax=func2(i);
                    if(func2(i)<ymin) ymin=func2(i);
                    funcao.add(i,func2(i));
                }
            }
            
            else{ //if choice==3, não tem outras opções
                for(double i=args[0];i<=args[1];i+=0.01){
                    if(funcCustom(i,custom)>ymax) ymax=funcCustom(i,custom);
                    if(funcCustom(i,custom)<ymin) ymin=funcCustom(i,custom);
                    funcao.add(i,funcCustom(i,custom));
                }
            }

            args[2]=ymin;
            args[3]=ymax;
    }
    
    
    
    public static double func1(double x){
        return Math.sin(x);
    }
    
    public static double func2(double x){
        return 5-x*x;
    }
    
    public static double funcCustom(double x,double[] args){
        return ( args[0]
                +args[1]*   x        //
                +args[2]*  x*x      //Ficou bonitinha essa parte do codigo ♥
                +args[3]* x*x*x    // 
                +args[4]*x*x*x*x);//
    }
            
}
