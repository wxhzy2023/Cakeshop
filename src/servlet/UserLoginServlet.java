package servlet;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "user_login",urlPatterns = "/user_login")
public class UserLoginServlet extends HttpServlet {
    private UserService uService = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ue = req.getParameter("ue");
        String password = req.getParameter("password");
        User user = uService.login(ue, password);
        if (user == null) {
            req.setAttribute("failMsg", "用户名、邮箱或者密码错误，请重新登录！");
            req.getRequestDispatcher("/user_login.jsp").forward(req, resp);
        } else {
            req.getSession().setAttribute("user", user);
            req.getRequestDispatcher("/user_center.jsp").forward(req, resp);
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
