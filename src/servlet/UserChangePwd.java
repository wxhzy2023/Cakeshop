package servlet;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "user_changepwd",urlPatterns = "/user_changepwd")
public class UserChangePwd extends HttpServlet {
    private UserService uService = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String password = req.getParameter("password");
        String newPwd = req.getParameter("newPassword");

        User u = (User) req.getSession().getAttribute("user");
        if (password.equals(u.getPassword())) {
            u.setPassword(newPwd);
            uService.updatePwd(u);
            req.setAttribute("msg", "修改成功！");
            req.getRequestDispatcher("/user_center.jsp").forward(req, resp);
        } else {
            req.setAttribute("failMsg", "修改失败，原密码不正确，你再想想！");
            req.getRequestDispatcher("/user_center.jsp").forward(req, resp);
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
