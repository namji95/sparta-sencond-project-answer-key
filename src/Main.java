import domain.*;
import java.util.*;

import service.Avg_calculation;

import static domain.CourseType.MANDATORY;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws Exception {
            Management management = new Management();
            management.run();
            management.end();
    }
}