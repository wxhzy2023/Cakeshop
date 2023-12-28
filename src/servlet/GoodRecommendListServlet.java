package servlet;

import model.Page;
import service.GoodsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "goodsrecommend_list",urlPatterns = "/goodsrecommend_list")
public class GoodRecommendListServlet extends HttpServlet {
    private GoodsService gService = new GoodsService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int type = Integer.parseInt(req.getParameter("type") ) ;
        int pageNumber = 1;
        if(req.getParameter("pageNumber") != null) {
            try {
                pageNumber=Integer.parseInt(req.getParameter("pageNumber") ) ;
            }
            catch (Exception e)
            {

            }
        }
        if(pageNumber<=0)
            pageNumber=1;
        Page p = gService.getGoodsRecommendPage(type, pageNumber);

        if(p.getTotalPage()==0)
        {
            p.setTotalPage(1);
            p.setPageNumber(1);
        }
        else {
            if(pageNumber>=p.getTotalPage()+1)
            {
                p = gService.getGoodsRecommendPage(type, p.getTotalPage());
            }
        }
        req.setAttribute("p", p);
        req.setAttribute("t", type);
        req.getRequestDispatcher("goodsrecommend_list.jsp").forward(req, resp);
    }
}