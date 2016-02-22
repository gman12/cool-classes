import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gorav_000
 */
public class Life {
    
    private boolean[][] newMatrix;
    private int bLow;
    private int bHigh;
    private int lLow;
    private int lHigh;
    
    public Life(long seed, int rows, int columns, int birthLow, int birthHigh, int liveLow, int liveHigh) {
        bLow = birthLow; 
        bHigh = birthHigh;
        lLow = liveLow;
        lHigh = liveHigh;
        newMatrix = new boolean[rows][columns];
        Random rand = new Random(seed);
        fill(rand, newMatrix);
    }
    
    public void update() {
        updateMatrix(newMatrix, bLow, bHigh, lLow, lHigh);        
    }
    
    public boolean[][] world() {
        return newMatrix;
    }
    
     public static void fill(Random rand, boolean[][] m) {
        for (int i = 1; i < m.length-1; i++) {
            for (int j = 1; j < m[0].length-1; j++) { 
                m[i][j] = rand.nextBoolean();
            }
        }
    }
    
    public static boolean[][] clone(boolean[][] m) {
        boolean[][] matrix = m.clone();
        for (int row = 0; row < m.length; ++row) {
                matrix[row] = matrix[row].clone();
        }
        return matrix;
    }
    
    public static void updateMatrix(boolean[][] m, int birthLow, int birthHigh, int liveLow, int liveHigh) {
        boolean[][] secondMatrix = clone(m);
        
        for (int i = 1; i <m.length-1; i++) {
            for (int j = 1; j < m[0].length-1; j++) {
                int count = getNeighbors(secondMatrix, i, j);
                if (secondMatrix[i][j] && liveLow > count || count > liveHigh) {
                    m[i][j] = false;
                } 
                else if (!m[i][j] && birthLow <= count && count <= birthHigh) {
                    m[i][j] = true; 
                }
            }
        }
    }
    
    public static int getNeighbors(boolean[][] m, int rows, int columns) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (m[rows + i][columns + i]) {
                    count++;
                }
            }
        }
        return count;
    }
}
