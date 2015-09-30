import controller.SystemUser;
import view.View;import java.lang.String;

public class Program {

    public static void main(String[] args){

        View v = new View();
        SystemUser s = new SystemUser();
        s.startProgram(v);

    }
}
