public class Array2D {
    public static void main(String[] args) {
        int[][] mat = {
            {1, 2, 3},
            {4, 5, 6}
        };
        for (int r = 0; r < mat.length; r++) {
            for (int c = 0; c < mat[r].length; c++) {
                System.out.print(mat[r][c] + " ");
            }
            System.out.println();
        }
    }
}
