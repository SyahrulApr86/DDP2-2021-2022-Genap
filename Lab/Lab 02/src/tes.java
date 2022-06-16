//public class tes {
////    create a recursive method that multiplies a number with an odd index by 2
//    public static int oddIndex(int[] arr, int index) {
//        if (index == arr.length - 1) {
//            return arr[index];
//        } else {
//            return arr[index] * 2 + oddIndex(arr, index + 1);
//        }
//    }
//
////    public static int recSumDigitOdd(String str) {
////        if (str.length() == 1) {
////            return Integer.parseInt(str)*2;
////        } else {
////            return Integer.parseInt(str.substring(0, 1))*2 + recSumDigitOdd(str.substring(2));
////        }
////    }
//
//    public static int recSumDigitOdd(long kode) {
//        String str = String.valueOf(kode);
//        if (str.length() == 1) {
//            return Integer.parseInt(str)*2;
//        } else {
//            return Integer.parseInt(str.substring(0, 1))*2 + recSumDigitOdd(Long.parseLong(str.substring(2)));
//        }
//    }
//
//    public static int recSumDigitEven(String str) {
//        if (str.length() == 1) {
//            return 0;
//        } else {
//            return Integer.parseInt(str.substring(1, 2)) + recSumDigitEven(str.substring(3));
//        }
//    }
//
//    public static int recSumDigitEven(long kode) {
//        String str = String.valueOf(kode);
//        if (str.length() == 1) {
//            return 0;
//        } else {
//            return Integer.parseInt(str.substring(1, 2)) + recSumDigitEven(Long.parseLong(str.substring(3)));
//        }
//    }
//
////    recreate method recSumDigitOdd but with int instead of String
//
//
//    public static int sumNumber(int num) {
//        if (num>9) {
//            return Integer.parseInt(String.valueOf(num).substring(0, 1)) + Integer.parseInt(String.valueOf(num).substring(1));
//        }
//        return num;
//    }
//
//
//    public static void main(String[] args) {
//        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
//        System.out.println(oddIndex(arr, 0));
//        System.out.println(recSumDigitOdd(11111111111L));
////        int a= 12345;
////        Integer b = a;
////        System.out.println(b.toString());
// 10101010101
//
//   1010101
//
//     101
//      0
//    }
//}
