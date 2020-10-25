package den.calc;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class calcServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //req.setAttribute("primer", "");
        //req.setAttribute("stroka", "");
        System.out.println("GETTT1");
        req.setAttribute("otvet", "0");

        req.getRequestDispatcher("calc.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("postststststst");
        String primer = req.getParameter("primer");
        String result = reshenie(primer);
        req.setAttribute("stroka", primer);
        req.setAttribute("otvet", result);

        req.getRequestDispatcher("/calc.jsp").forward(req, resp);
    }

    private String reshenie(String text){

        try {
            text = text.replaceAll(" ", "");
            Integer[] nums = Arrays.stream(text.split("[+|\\-|*|/]")).map(this::getMinutes).toArray(Integer[]::new);
            String[] znak = text.split("\\w{1,7}"); // этот символ встречается от 1 до 5 раз
            // если не делать уточнение, то на каждую цифру в начале строки (тут делитель строки)
            // будет пустой знак и собьется соответствие "знак - цифра перед ним"

            if (znak[0].isEmpty()) znak[0] = "+"; // если цифра первая (делитель), то хоть один, но будет пустой элемент.
            // а если он пустой (т.е. не стоял знак минус), то знак должен быть "+"

            Deque<Integer> resq = new ArrayDeque<>();

            for (int i = 0; i < znak.length; i++) {

                int x = 0;

                if (znak[i].equals("*")) {
                    x = resq.pollLast() * nums[i];
                }
                else if (znak[i].equals("/")) {
                    x = resq.pollLast() / nums[i];
                }
                else if (znak[i].equals("-")) x = nums[i] * -1;
                else x = nums[i];

                resq.offer(x);
            }

            int x = resq.stream().reduce(0, Integer::sum);

            return minutesToHoursMinutes(x);
        } catch (Exception e) {
            return "ОШИБКА!!!!";
        }

    }

    public int getMinutes(String s) {
        int result;

        if (s.matches("\\d+\\D\\d+")){
            String[] time = s.split("\\D");
            result = Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);
        }
        else if (s.matches("\\d+\\D")){
            result = Integer.parseInt(s.replaceAll("\\D", "")) * 60;
        }
        else result = Integer.parseInt(s);

        return result;
    }

    public String minutesToHoursMinutes(int minutes){
        int h = minutes / 60;
        int m = minutes - h*60;
        return h + "ч " + m + "м";
    }

}
