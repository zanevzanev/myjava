public class Bonfire {

    public static boolean containsValueAfterIndex(int needle, int [] haystack, int index) {

        boolean checkflag = false;
        //check for zero number of values in array. If zero return false
        if (haystack.length>0) {

            for (int i = 0; i < haystack.length; i++) {

                if (haystack[i] == needle) {
                    checkflag = i > index; //Return True or False
                }
            }
        }
        return checkflag;
    }

    public static int getThirdLargest(int  [] array){
    //This function assumes a min of 3 values and the 3rd value is the magic number

        int checkInt = 0;

        if (array.length<3){
            checkInt = Integer.MIN_VALUE;
        }
        else
        {
            int largestvalue = array[0]; //set first value as largest

            for (int i = 1; i < array.length ; i++)
                if (array[i] > largestvalue) {
                    largestvalue = array[i];  //check against all other values and update if greater
                }

            //check if there are more than 3 of the same largest values in the array
            int uniquecounter=0;
            for (int i = 0; i < array.length ; i++){
                if (array[i] == largestvalue) {
                    uniquecounter = uniquecounter + 1;
                }
            }
            // if more than 3 values are the largest then exit since this only looking for 3rd largest
            if (uniquecounter>=3){
                checkInt=largestvalue;
                return checkInt;
            }
            int secondlargest = Integer.MIN_VALUE; //set to lowest possible value just in case
            for (int i = 0; i < array.length; i++)
                if (array[i] > secondlargest && array[i] < largestvalue) {
                    secondlargest = array[i];
                }

            if (uniquecounter==2) {
                checkInt=secondlargest; // This is actually the 3rd largest if there are two duplicate largest values
                return checkInt;
            }
            //Otherwise all elements do not repeat
            int thirdlargest = Integer.MIN_VALUE;
            for (int i = 0; i < array.length; i++)
                if (array[i] > thirdlargest && array[i] < secondlargest) {
                    thirdlargest = array[i]; // same logic as per secondlargest
                }
            checkInt = thirdlargest;

        }

        return checkInt;

    }


    public static void printThirdLargest(int [] array){

        //This function assumes a min of 3 values and the 3rd value is the magic number

        int checkInt = 0;

        if (array.length<3){
            checkInt = Integer.MIN_VALUE;
            System.out.println(checkInt);
            return;
        }
        else
        {
            int largestvalue = array[0]; //set first value as largest

            for (int i = 1; i < array.length ; i++)
                if (array[i] > largestvalue) {
                    largestvalue = array[i];  //check against all other values and update if greater
                }

            //check if there are more than 3 of the same largest values in the array
            int uniquecounter=0;
            for (int i = 0; i < array.length ; i++){
                if (array[i] == largestvalue) {
                    uniquecounter = uniquecounter + 1;
                }
            }
            // if more than 3 values are the largest then exit since this only looking for 3rd largest
            if (uniquecounter>=3){
                checkInt=largestvalue;
                System.out.println(checkInt);
                return;
            }
            int secondlargest = Integer.MIN_VALUE; //set to lowest possible value just in case
            for (int i = 0; i < array.length; i++)
                if (array[i] > secondlargest && array[i] < largestvalue) {
                    secondlargest = array[i];
                }

            if (uniquecounter==2) {
                checkInt=secondlargest; // This is actually the 3rd largest if there are two duplicate largest values
                System.out.println(checkInt);
                return;
            }
            //Otherwise all elements do not repeat
            int thirdlargest = Integer.MIN_VALUE;
            for (int i = 0; i < array.length; i++)
                if (array[i] > thirdlargest && array[i] < secondlargest) {
                    thirdlargest = array[i]; // same logic as per secondlargest
                }
            checkInt=thirdlargest;
            System.out.println(checkInt);

        }



    }

    public static boolean isRotation(int [] array1, int [] array2){
    // Pattern matching in this function is based on idea that the first element in array1 is the basis of the pattern
    // If the first element is not repeated then only one pattern can exist starting with that element and only one pass needed
    // If the first element is repeated then array2 needs to be checked for rotation starting from each occurence of the first element

        boolean checkflag = false;
        // if arrays are not same size they cannot be a rotation
        if (array1.length != array2.length){
            return checkflag;
        }

        //if arrays are empty then they are identical
        if (array1.length == 0){
            checkflag = true;
            return checkflag;
        }

        checkflag = false;
        for (int i = 0; i < array1.length; i++) {
            //check if all elements in array1 match array2 in same position, if so array is a full rotation
            if (array1[i]==array2[i]) {
                checkflag = true;
            }else{
                i = array1.length;
                checkflag = false;
            }
        }
        if (checkflag == true){
            return checkflag;
        }

        //Loop through array and compare entire sequence with other array.
        int startpos = 0;  //starting position in array2
        int diffstartpos = 0;   //difference between starting position and array length in array2
        boolean passedcheck = false;   // to pass to return value

        //Count how many times first element occurs. Perform that number of loops to check rotations
        int startvalue=-999;
        int startvaluecounter = 1; //If the code has made it here then there will be a starting value
        for (int i = 1; i < array1.length; i++) {
            startvalue = array1[0];
            if (startvalue==array1[i]){
                startvaluecounter=startvaluecounter + 1;
            }
        }


        //If the first element occurs more than once, we need to loop thru all iterations else do it once
        if  (startvaluecounter>1){
            for (int k = 0; k < array1.length; k++) {
                //but only do it if the startvalue occurs and check sequences from there
                if(array2[k] == startvalue){
                    for (int i = 0; i < array1.length; i++) {
                        startpos = k;
                        diffstartpos = array2.length - startpos;
                    }
                    //start looking in sequence from second element until end of array.
                    for (int i = 1; i < (array1.length - startpos); i++) {
                        if (array1[i] == array2[startpos + i]) {
                            passedcheck = true;
                        } else {
                            checkflag = false;
                        }
                    }
                    //Finish check by checking from start of Array2 up to initial starting position
                    for (int i = 0; i < startpos; i++) {
                        if (array1[diffstartpos + i] == array2[i]) {
                            passedcheck = true;
                            checkflag = true;
                        } else {
                            checkflag = false;
                        }

                    }
                }
            }
        }else {
            for (int i = 0; i < array1.length; i++) {
                for (int j = 0; j < array2.length; j++) {
                    if (array1[0] == array2[j]) {
                        //Found the first element of array1 in array 2
                        startpos = j;
                        diffstartpos = array2.length - startpos;
                        j = array2.length;
                        i = array2.length;
                        break;
                    }
                }
            }
            //start looking in sequence from second element until end of array.
            for (int i = 1; i < (array1.length - startpos); i++) {
                if (array1[i] == array2[startpos + i]) {
                    passedcheck = true;
                    checkflag = true;
                } else {
                    checkflag = false;
                }
            }
            //Finish check by checking from start of Array2 up to initial starting position
            for (int i = 0; i < startpos; i++) {
                if (array1[diffstartpos + i] == array2[i]) {
                    passedcheck = true;
                    checkflag = true;
                } else {
                    checkflag = false;
                }

            }
        }
        return checkflag;
    }


    public static int [] generateNthRotation(int [] array, int n){

        n = n * -1;
        int len = array.length; //dimension of existing array
        int[] result = new int[len]; //create a temp new array
        int k = n % len;  //derive the mod
        if (k < 0) {
            k = k + len;
        }
        //Loop to populate the array
        for (int i = 0; i < len; i++) {
            result[(i + len - k) % len] = array[i];
        }
        return result;
    }




    public static void printRibbon(int n, int width){


    }

    public static double difficultyRating(){

        return 3;
    }

    public static double hoursSpent(){

        return 5;
    }

}
