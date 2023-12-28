package servlet;

import model.Goods;
import model.Order;
import service.GoodsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "goods_buy",urlPatterns = "/goods_buy")
public class GoodsBuyServlet extends HttpServlet {
    private GoodsService gService = new GoodsService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Order o = null;
        if(req.getSession().getAttribute("order") != null) {
            o = (Order) req.getSession().getAttribute("order");
        }else {
            o = new Order();
            req.getSession().setAttribute("order", o);
        }
        int goodsid = Integer.parseInt(req.getParameter("goodsid"));
        Goods goods = gService.getGoodsById(goodsid);
        if(goods.getStock()>0) {
            o.addGoods(goods);
            resp.getWriter().print("ok");
        }else {
            resp.getWriter().print("fail");
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}

