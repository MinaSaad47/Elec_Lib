
package out_err;

import java.io.IOException;

public class IO {
    
    
    public static void list(String... list) {
        for (int i = 0; i < list.length ; i++) {
            System.out.printf(" [%d] %s ", i + 1, list[i]);
            sleep(50);
        }
        System.out.printf("\n\n");
    }
    
    public static void error(String str) {
        str = "!! Error: " + str + " !!";
        block(str, 5);  
    }
    
    public static void prompt(String str) {
        System.out.printf("\tEnter %s: ", str);
    }

    public static void menu(String str) {
        block(str, 5);
    }
    
    public static void loading(String str) {
        IO.clear();
        block(str + " .", 0);
        IO.sleep(500);
        IO.clear();
        block(str + " ..", 0);
        IO.sleep(500);
        IO.clear();
        block(str + " ...", 0);
        IO.sleep(500);
        IO.clear();
        block(str + " ....", 0);
        IO.sleep(500);
        IO.clear();
        block(str + " .....", 0);
        IO.sleep(500);
        IO.clear();
    }
    
    private static void block(String str, int milSec) {
        int len = 80;
        int strLen = str.length();
        System.out.printf(" ");
        for (int i = 0; i < len; i++) {
            System.out.printf("-");
            System.out.flush();
            sleep(milSec);
        }
        System.out.printf("\n|");
        for (int i = 0; i < (len - strLen) / 2; i++) {
            System.out.print(" ");
        }
        System.out.printf("%s",  str);
        for (int i = 0; i < (len - strLen) / 2; i++) {
            System.out.print(" ");
        }
        System.out.printf("|\n ");
        for (int i = 0; i < len; i++) {
            System.out.printf("-");
            System.out.flush();
            sleep(milSec);
        }
        System.out.printf("\n\n");
    }
    
    public static void sleep(int milSec) {
        try {
            Thread.sleep(milSec);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }
    
    public static void clear() {
        try {

        if (System.getProperty("os.name").contains("Windows"))
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else
            Runtime.getRuntime().exec("clear");
        } catch (IOException ex) {} catch (InterruptedException ex) {
            
        }
    }
    public static void rowContent(String sizes, String... cols) {
        int len =row(sizes, cols);
        System.out.printf(" ");
        for (int i = 0; i < len ; i++) {
            System.out.printf("-");
        }
        System.out.printf("\n");
    }
    
    
    public static int row(String sizes,String... cols) {
        int col = cols.length;
        int len = col;
        int size[] = new int[col];
        for (int i = 0, j = 0; i < col; i++, j += 2) {
            size[i] = Integer.parseInt(sizes.substring(j, j + 2));
            len += size[i];
        }
        
        for (int i = 0; i < col; i++) {
            System.out.printf("|");
            System.out.printf("%s", cols[i]);
            for (int j = 0; j < size[i] - cols[i].length(); j++) {
                System.out.printf(" ");
            }
        }
        System.out.printf(" |\n");
        
        System.out.printf(" ");
        for (int i = 0; i < len; i++) {
            System.out.printf("-");
        }
        System.out.printf("\n");   
        return len;
    }
    
    
    
    
    
    
}