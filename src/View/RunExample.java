package View;

import Controller.Controller;

import java.io.IOException;
import Exception.MyException;

public class RunExample extends Command {
    private Controller ctr;
    public RunExample(String key, String desc,Controller ctr){
        super(key, desc);
        this.ctr=ctr;
    }
    @Override
    public void execute() throws IOException, MyException, InterruptedException {
        try{
            ctr.allStep();
        }catch(Exception e) {
            System.out.println(e);
        }
    }
}
