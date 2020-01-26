/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.threads;

/**
 *
 * @author hcadavid
 */
public class CountThreadsMain {
    
	public static void main(String a[]){
        CountThread num1 = new CountThread(0,99);
        CountThread num2 = new CountThread(99,199);
        CountThread num3 = new CountThread(200,299);
        //num1.start();
        //num2.start();
        //num3.start();
        num1.run();
        num2.run();
        num3.run();
        
    }
    
}
