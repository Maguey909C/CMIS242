package genericnongeneric;
/*
* File Name: Week7Generic.java
* Author: Mia Bae
* Date: July 3, 2019
* Purpose:  Week 7 discussion - a program using nongeneric class of a generic class. 
*/
public class GenericNonGeneric {
//NonGeneric
public static class NonGeneric {
private int NG; 
 
        public NonGeneric(int NG) {
            this.NG = NG;
        }// end public NonGeneric
        public int get(){
            return NG;
        }//end public int get()
}//end public static class NonGeneric
//Generic
public static class Generic<G> {
private G g;

        public Generic(G g) {
            this.g = g;
        }//end public Generic
        public G get(){
            return g;
        }//end public G get()
}//end public static class Generic<G>

public static void main(String args[]){
//nongeneric
NonGeneric ng = new NonGeneric(12);
System.out.println("Non-Generic Integer: " + (ng.NG));
//generic
Generic<Integer> integerGeneric = new Generic<Integer>(12);   
System.out.println("Generic Integer: " + integerGeneric.get());
        }//end public static void main(String args[])
}//end public class genericnongeneric
