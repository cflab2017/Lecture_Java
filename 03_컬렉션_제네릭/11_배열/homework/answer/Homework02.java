/** 행렬 전치. */
public class Homework02 {
    public static void main(String[] args) {
        int[][] mat = { {1, 2, 3}, {4, 5, 6} };

        System.out.println("원본");
        print(mat);

        int[][] t = new int[mat[0].length][mat.length];
        for (int r = 0; r < mat.length; r++) {
            for (int c = 0; c < mat[r].length; c++) {
                t[c][r] = mat[r][c];
            }
        }
        System.out.println("전치");
        print(t);
    }

    static void print(int[][] m) {
        for (int[] row : m) {
            for (int v : row) System.out.print(v + " ");
            System.out.println();
        }
    }
}
