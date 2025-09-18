public class ThreeDArray {
    public static void main(String[] args) {
        int[][][] cube = {
            {
                {1, 2},
                {3, 4}
            },
            {
                {5, 6},
                {7, 8}
            }
        };

        System.out.println("3D Array Elements:");
        for (int i = 0; i < cube.length; i++) {           // layer
            for (int j = 0; j < cube[i].length; j++) {     // row
                for (int k = 0; k < cube[i][j].length; k++) { // column
                    System.out.print(cube[i][j][k] + " ");
                }
                System.out.println();
            }
            System.out.println("---"); // separate layers
        }
    }
}
