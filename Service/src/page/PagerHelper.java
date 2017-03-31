package page;

import javax.servlet.http.HttpServletRequest;
public class PagerHelper
{

    public PagerHelper()
    {
    }

    public static Pager getPager(HttpServletRequest httpServletRequest, int totalRows, int pageSize)
    {
        Pager pager = new Pager(totalRows, pageSize);
        String currentPage = httpServletRequest.getParameter("cpage");
        if(currentPage != null)
            pager.setStart(Integer.parseInt(currentPage));
        else
        if(currentPage == null)
            pager.setStart(1);
        return pager;
    }
}