package servlet;

import model.Goods;
import model.Page;
import model.Type;
import service.GoodsService;
import service.TypeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "goods_List",urlPatterns = "/goods_list")
public class GoodsListServlet extends HttpServlet {
    private GoodsService gService = new GoodsService();
    private TypeService tService = new TypeService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id=0;
        if(req.getParameter("typeid")!=null)
        {
            id=Integer.parseInt(req.getParameter("typeid"));
        }
        int pageNumber=1;
        if(req.getParameter("pageNumber")!=null) {
            try {
                pageNumber=Integer.parseInt(req.getParameter("pageNumber"));
            }
            catch (Exception e)
            {

            }

        }
        Type t=null;
        if(id!=0)
        {
            t=tService.selectTypeNameByID(id);
        }
        req.setAttribute("t",t);
        //List<Goods> list=gService.selectGoodsByTypeID(id,1,8);
        //request.setAttribute("goodsList",list);
        if(pageNumber<=0)
            pageNumber=1;
        Page p=gService.selectPageByTypeID(id,pageNumber);

        if(p.getTotalPage()==0)
        {
            p.setTotalPage(1);
            p.setPageNumber(1);
        }
        else {
            if(pageNumber>=p.getTotalPage()+1)
            {
                p=gService.selectPageByTypeID(id,p.getTotalPage());
            }
        }

        req.setAttribute("p",p);
        req.setAttribute("id",String.valueOf(id));
        req.getRequestDispatcher("/goods_list.jsp").forward(req,resp);
    }
}
