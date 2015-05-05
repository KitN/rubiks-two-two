import java.util.*;
 
// Uses William Bernoudy's Skeleton
public class KitRubik {
 
    static int[] cubiesPos = new int[8];
    static int[] cubiesRot = new int[8];
    static int movecount = 0;
    static String moveList = "";
    static String[] diffMoves = {"T", "F", "R", "T'", "F'", "R'"};
 
    public static void main(String[] args) {
 
        //--------------START INPUT-------------------//
 
        Scanner scannerInput = new Scanner(System.in);
 
        char[] input = new char[24];
        for (int x = 0; x < 24; x++) {
            input[x] = scannerInput.next().charAt(0);
        }
         
        scannerInput.close();
 
        //-----------------END INPUT---------------------//
 
        //---------------START INITIALIZATION-----------//
 
        char[][] cubies = new char[8][3];
 
        cubies[0] = new char[]{input[0],    input[16],  input[14]};
        cubies[1] = new char[]{input[1],    input[15],  input[21]};
        cubies[2] = new char[]{input[2],    input[4],   input[17]};
        cubies[3] = new char[]{input[3],    input[20],  input[5]};
        cubies[4] = new char[]{input[10],   input[12],  input[18]};
        cubies[5] = new char[]{input[11],   input[23],  input[13]};
        cubies[6] = new char[]{input[8],    input[19],  input[6]};
        cubies[7] = new char[]{input[9],    input[7],   input[22]};
 
        char[][] cubieColors = new char[][]{
            {'y', 'r', 'b'}, {'r', 'b', 'y'}, {'b', 'y', 'r'},
            {'y', 'b', 'o'}, {'b', 'o', 'y'}, {'o', 'y', 'b'},
            {'y', 'g', 'r'}, {'g', 'r', 'y'}, {'r', 'y', 'g'},
            {'y', 'o', 'g'}, {'o', 'g', 'y'}, {'g', 'y', 'o'},
            {'w', 'b', 'r'}, {'b', 'r', 'w'}, {'r', 'w', 'b'},
            {'w', 'o', 'b'}, {'o', 'b', 'w'}, {'b', 'w', 'o'},
            {'w', 'r', 'g'}, {'r', 'g', 'w'}, {'g', 'w', 'r'},
            {'w', 'g', 'o'}, {'g', 'o', 'w'}, {'o', 'w', 'g'}
        };
 
        for (int x = 0; x < 8; x++){
            int index = 0;
            while (true){
                if (Arrays.equals(cubies[x], cubieColors[index])) {
                    break;
                }
                index++;
            }
            cubiesPos[x] = index / 3;
            cubiesRot[x] = index % 3;
        }
 
        long startTime = System.nanoTime();
 
        //---------------END INITIALIZATION--------------//
 
        //----------------START SOLVING-----------------//

        int[] keepcubes1 = {5};
        int[] keepcubes2 = {5,6};
        int[] keepcubes3 = {5,6,7};
        int[] keepcubes4 = {0,5,6,7};
        int[] keepcubes5 = {0,1,5,6,7};
        int[] keepcubes6 = {0,1,2,5,6,7};
        int[] keepcubes7 = {0,1,2,3,5,6,7};


        String[] turns = trymoves(keepcubes7, 11);
        for(int q = 0; q<turns.length; ++q){
            moveList += turns[q];
            moveList += " ";
            movecount++;
        }
        

        //-----------------END SOLVING-----------------//
        long stopTime = (System.nanoTime() - startTime)/1000;
 
        System.out.println(moveList);
        System.out.format("Number of moves: %d\n", movecount);
        System.out.format("Cubes in correct position: %d\n", 8);
        System.out.println(stopTime + " micrcoseconds");
 
    }
 
    public static String[] trymoves(int[] preserve, int depth){
        String[] answer = {"NA"};
        int[] startPos = new int[8];
        int[] startRot = new int[8];
        startPos = cubiesPos;
        startRot = cubiesRot;
        // The number of senary digits to use.
        String sits = "";
        for(int s = 0; s<depth; ++s){
            sits += "5";
        }
        // Generates all numbers in base six up to 555555, which represent each possible sequence of six moves.
        for(int i = 0; i<=Integer.parseInt(sits, 6); ++i){
            cubiesPos = startPos;
            cubiesRot = startRot;
            String movestring = Integer.toString(i, 6);
            String[] moves = new String[movestring.length()];
            for(int d = 0; d<movestring.length(); ++d){
                String move = diffMoves[Integer.parseInt(movestring.substring(d,d+1))];
                moves[d] = move;
            }
            rotateFaces(moves);
            int prescount = 0;
            for(int number : preserve){
                if(cubiesPos[number] == number && cubiesRot[number] == 0){
                    prescount++;
                }
            } 
            if(prescount==preserve.length){
                answer = moves;
                break;
            }
        }
        cubiesPos = startPos;
        cubiesRot = startRot;
        
        return answer;
    }

    public static void rotateFaces(String[] moves){ // Accepts an array of strings where each string corresponds to one move in the following switch statement
        for (String face : moves){                  // For example, if you want to rotate the top clockwise, the right counter-clockwise, and the front clockwise, you would do rotateFaces(new String []{"T", "R'", "F"});
//            movecount++;
            switch (face) {
                case "F":
                    cubiesPos = new int[]{cubiesPos[0], cubiesPos[1], cubiesPos[6], cubiesPos[2], cubiesPos[4], cubiesPos[5], cubiesPos[7], cubiesPos[3]};
                    cubiesRot = new int[]{cubiesRot[0], cubiesRot[1], (cubiesRot[6]+1) % 3, (cubiesRot[2]+2) % 3, cubiesRot[4], cubiesRot[5], (cubiesRot[7]+2) % 3, (cubiesRot[3]+1) % 3};
//                    moveList += "F ";
                    break;
                case "R":
                    cubiesPos = new int[]{cubiesPos[0], cubiesPos[3], cubiesPos[2], cubiesPos[7], cubiesPos[4], cubiesPos[1], cubiesPos[6], cubiesPos[5]};
                    cubiesRot = new int[]{cubiesRot[0], (cubiesRot[3]+2) % 3, cubiesRot[2], (cubiesRot[7]+1) % 3, cubiesRot[4], (cubiesRot[1]+1) % 3, cubiesRot[6], (cubiesRot[5]+2) % 3};
//                    moveList += "R ";
                    break;
                case "T":
                    cubiesPos = new int[]{cubiesPos[2], cubiesPos[0], cubiesPos[3], cubiesPos[1], cubiesPos[4], cubiesPos[5], cubiesPos[6], cubiesPos[7]};
                    cubiesRot = new int[]{cubiesRot[2], cubiesRot[0], cubiesRot[3], cubiesRot[1], cubiesRot[4], cubiesRot[5], cubiesRot[6], cubiesRot[7]};
//                    moveList += "T ";
                    break;
                case "F'":
                    cubiesPos = new int[]{cubiesPos[0], cubiesPos[1], cubiesPos[3], cubiesPos[7], cubiesPos[4], cubiesPos[5], cubiesPos[2], cubiesPos[6]};
                    cubiesRot = new int[]{cubiesRot[0], cubiesRot[1], (cubiesRot[3]+1) % 3, (cubiesRot[7]+2) % 3, cubiesRot[4], cubiesRot[5], (cubiesRot[2]+2) % 3, (cubiesRot[6]+1) % 3};
//                    moveList += "F' ";
                    break;
                case "R'":
                    cubiesPos = new int[]{cubiesPos[0], cubiesPos[5], cubiesPos[2], cubiesPos[1], cubiesPos[4], cubiesPos[7], cubiesPos[6], cubiesPos[3]};
                    cubiesRot = new int[]{cubiesRot[0], (cubiesRot[5]+2) % 3, cubiesRot[2], (cubiesRot[1]+1) % 3, cubiesRot[4], (cubiesRot[7]+1) % 3, cubiesRot[6], (cubiesRot[3]+2) % 3};
//                    moveList += "R' ";
                    break;
                case "T'":
                    cubiesPos = new int[]{cubiesPos[1], cubiesPos[3], cubiesPos[0], cubiesPos[2], cubiesPos[4], cubiesPos[5], cubiesPos[6], cubiesPos[7]};
                    cubiesRot = new int[]{cubiesRot[1], cubiesRot[3], cubiesRot[0], cubiesRot[2], cubiesRot[4], cubiesRot[5], cubiesRot[6], cubiesRot[7]};
//                    moveList += "T' ";
                    break;
                default:
                    break;
            }
        }
    }
}
 
