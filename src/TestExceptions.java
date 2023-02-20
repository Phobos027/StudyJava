public class TestExceptions {
    public static void main(String[] args) {
        String test = "yes";
        try {
            System.out.println("Start Try");
            doRisky(test);
            System.out.println("Fin try");
        } catch (ScaryException scaryException){
            System.out.println("Scary exception");
        } finally {
            System.out.println("finally");
        }
        System.out.println("Fin main");
    }

    static void doRisky(String test) throws ScaryException {
        System.out.println("Start risk method");
        if("yes".equals(test)) {
            throw new ScaryException();
        }
        System.out.println("Finally scary method");
        return;
    }
}