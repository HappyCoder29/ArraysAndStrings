package edu.northeastern.ashish;

import java.lang.reflect.Array;
import java.sql.PreparedStatement;
import java.util.*;

public class Main {

    public static void main(String[] args) {

//        int[] arr = {-1,1,5,-3, 4, 7, 3, 12, 15};
//        System.out.println(twoValueSum(arr, 29));

//        String str = "ZXABCCBAY";
//
//        System.out.println(getLongestPalindromeSubString(str));

//        ArrayList<Interval> arrayList = new ArrayList<>();
//        arrayList.add(new Interval(1,5));
//        arrayList.add(new Interval(3,7));
//        arrayList.add(new Interval(2,4));
//        arrayList.add(new Interval(8,15));
//
//        mergeIntervals(arrayList);

//        int[] arr = {-4,-2,0, 1,3,5};
//        int[] squares = squareOfSortedArray(arr);

//        int[] arr = {5,6,1,2,3,4};
//        findMinInSortedRotated(arr);
//        System.out.println(findMinInSortedRotated(arr));
//        int[] arr1 = {1,3,5,7};
//
//        int[] arr2 = {2,4,6,8,9,10};
//
//        int[] result = mergeSortedArrays(arr1, arr2);
//        System.out.println("");

        System.out.println(isInterleaved("ACEF", "BD", "ABEDCF"));


    }

    private static  boolean threeValueSum(int[] arr, int target){
        Arrays.sort(arr);

        for(int i = 0 ; i < arr.length -1; i ++){
            if(twoValueSum(arr, i+1,target ) == true)
                return  true;
        }
        return  false;

    }

    private static  boolean twoValueSum(int[] arr, int start, int target){
        int end = arr.length -1;

        while(start < end){
            int sum = arr[start] + arr[end];
            if(sum == target){
                return  true;
            }
            else if(sum < target){
                start++;
            }
            else{
                end--;
            }
        }
        return false;
    }

    private static  boolean twoValueSum(int[] arr, int target){
        Arrays.sort(arr);

        int start = 0;
        int end = arr.length -1;

        while(start < end){
            int sum = arr[start] + arr[end];
            if(sum == target){
                return  true;
            }
            else if(sum < target){
                start++;
            }
            else{
                end--;
            }
        }
        return false;
    }

    private static boolean  isPalindrome(String str){
        int start = 0;
        int end = str.length() -1;

        while(start < end){
            if(str.charAt(start) != str.charAt(end))
                return false;
            start ++;
            end --;
        }
        return  true;
    }

    private  static Interval getBiggerInterval(Interval int1, Interval int2){
        return  int1.diff - int2.diff > 0 ? int1: int2;
    }

    private static  boolean canMakePalindrome(String str){
        Set<Character> set = new HashSet<>();
        for(int i = 0 ; i < str.length(); i ++){
            if(set.contains(str.charAt(i)))
                set.remove(str.charAt(i));
            else
                set.add(str.charAt(i));
        }

        return (set.size() <= 1 );
    }


    private static String getLongestPalindromeSubString(String str){
        int maxLength = 1;
        int start = 0;
        int low, high;

        Interval maxInterval = new Interval(0,0);

        for(int i = 1; i < str.length(); i ++){

            // even size palindrome
            low = i-1;
            high = i;
            // find max palindrome starting with center = i for even
            Interval intervalEven = getMaxPalindrome(str, low, high);

            // find max palindrome starting with center = i for odd
            low = i-1;
            high = i +1;
            Interval intervalOdd = getMaxPalindrome(str, low, high );

            // get the max of even or odd
            Interval bigger = getBiggerInterval(intervalEven, intervalOdd);

            // compare with the max Length
            maxInterval = getBiggerInterval(bigger, maxInterval);
        }

        return str.substring(maxInterval.start, maxInterval.end +1);

    }

    private static  Interval getMaxPalindrome(String str, int low, int high){
        int maxLength = 1;
        int start = low;

        while(  low>=0 && high < str.length() &&
                str.charAt(low) == str.charAt(high)
                )
        {
            if(high -low +1 > maxLength){
                start = low;
                maxLength = high -low +1;
            }

            low --;
            high ++;

        }

        Interval interval = new Interval(start, start + maxLength -1);
        return  interval;
    }


    private static void mergeIntervals(ArrayList<Interval> intervals){

        intervals.sort(new Comparator<Interval>() {
            @Override
            public int compare(Interval x, Interval y) {
                if(x.start > y.start)
                    return 1;
                else if (x.start < y.start)
                    return  -1;
                return 0;
            }


        });

        Stack<Interval> stack = new Stack<Interval>();
        stack.push(intervals.get(0));

        for(int i = 0 ; i < intervals.size(); i ++){
            Interval top = stack.peek();
            if(top.end < intervals.get(i).start){
                stack.push(intervals.get(i));
            }
            else if( top.end < intervals.get(i).end){
                top.end = intervals.get(i).end;
                stack.pop();
                stack.push(top);
            }
        }

        while(stack.size() > 0){
            Interval val = stack.pop();
            System.out.println("[" + val.start + "," + val.end + "]");

        }
    }

    private static int[] squareOfSortedArray(int[] arr){
        int[] squares = new int[arr.length];

        int start = 0;
        int end = arr.length -1;
        int index = arr.length -1;

        while(start <= end){
            int sq1 = arr[start] * arr[start];
            int sq2 = arr[end] * arr[end];

            if( sq1 > sq2){
                squares[index --] = sq1;
                start++;
            }else{
                squares[index --] = sq2;
                end--;
            }
        }

        return  squares;
    }


    private static int bittonicPeak(int[] arr){
        int start = 0;
        int end = arr.length -1;
        while(start < end){

            int mid = (start + end)/2;

            if(arr[mid] < arr[mid+1]){
                start = mid +1;
            }else{
                end = mid;
            }
        }
        return  arr[start];
    }

    private static void swap(int[] arr, int start, int end){

        int temp = arr[start];
        arr[start] = arr[end];
        arr[end] = temp;
    }

    private static void reverse(int[] arr, int start, int end){
        while(start < end){
            swap(arr, start,end);
            start++;
            end--;
        }
    }

    private static void rotateArray(int[] arr, int k){
        k = k % arr.length;

        reverse(arr, 0, arr.length-k -1);
        reverse(arr, arr.length-k, arr.length-1);

        reverse(arr, 0, arr.length -1);

    }


    private static int findMinInSortedRotated(int[] arr){
        int start = 0;
        int end = arr.length -1;

        if(arr[start] < arr[end])
            return arr[start];
        while(start < end -1){

            int mid = (start + end)/2;
            if(arr[start] > arr[mid]){
                end = mid;
            }else{
                start = mid;
            }
        }

        return  arr[end];
    }


    private static int[] mergeSortedArrays(int[] arr1, int[] arr2){
        int[] result = new int[arr1.length  + arr2.length];
        int ptr1 = 0;
        int ptr2 = 0;
        int index = 0;
        while(ptr1 < arr1.length && ptr2 < arr2.length){
            if(arr1[ptr1] < arr2[ptr2]){
                result[index++] = arr1[ptr1++];
            }else{
                result[index++] = arr2[ptr2++];
            }
        }

        while(ptr1 < arr1.length){
            result[index++] = arr1[ptr1++];
        }

        while(ptr2 < arr2.length){
            result[index++] = arr2[ptr2++];
        }

        return  result;
    }


    private static  boolean isInterleaved(String str1, String str2, String str3){

        return  isInterleaved(str1, str2, str3, "", 0, 0);
    }

    private static  boolean isInterleaved(String str1,
                                          String str2,
                                          String str3,
                                          String result,
                                          int ptr1,
                                          int ptr2){

        if( result.equals(str3) && ptr1 == str1.length() && ptr2 == str2.length())
            return  true;

        boolean answer = false;
        if( ptr1 < str1.length()){
            answer = answer || isInterleaved(str1, str2, str3, result + str1.charAt(ptr1),ptr1 +1, ptr2);
        }
        if( ptr2 < str2.length()){
            answer = answer || isInterleaved(str1, str2, str3, result + str2.charAt(ptr2),ptr1 , ptr2 +1);
        }

        return  answer;
    }





    }
