/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publisher;
/**
 *
 * @author Felipe
 */
public class Sleeper {
    
    public static void sleep_thread() {
        int num = random_int(2,3);
        try {
            Thread.sleep(num * 1000);
        }catch(Exception e){
            
        }
    }
    public static int random_int(int Min, int Max) {
        return (int) (Math.random() * (Max - Min)) + Min;
    }
}
