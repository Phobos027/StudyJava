class A {
    int ivar = 7;
    void m1(){
        System.out.println("A m1, ");
    }
    void m2(){
        System.out.println("A m2, ");
    }
    void m3(){
        System.out.println("A m3, ");
    }
}
class B extends A{
    void m1(){
        System.out.println("B m1, ");
    }
}
class C extends B{
    void m3(){
        System.out.println("C mhjj3, " + (ivar + 6));
    }
}

public class Mixed2 {
    public static void main(String[] args) {
        A a = new A();
        B b  = new B();
        C c = new C();
        A a2 = new C();

        a2.m1();
        a2.m2();
        a2.m3();
    }
}
