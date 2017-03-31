package page;


public class Pager
{

    public Pager()
    {
    }

    public int getTotalRows()
    {
        return totalRows;
    }

    public void setTotalRows(int totalRows)
    {
        this.totalRows = totalRows;
    }

    public int getPageSize()
    {
        return pageSize;
    }

    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }

    public int getCurrentPage()
    {
        return currentPage;
    }

    public void setCurrentPage(int currentPage)
    {
        this.currentPage = currentPage;
    }

    public int getTotalPages()
    {
        return totalPages;
    }

    public void setTotalPages(int totalPages)
    {
        this.totalPages = totalPages;
    }

	public int getStartRow()
    {
        return startRow;
    }

    public void setStartRow(int startRow)
    {
        this.startRow = startRow;
    }

    public String getLinkUrl()
    {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl)
    {
        this.linkUrl = linkUrl;
    }

    public Pager(int totalRows, int pageSize)
    {
        this.totalRows = totalRows;
        this.pageSize = pageSize;
        totalPages = totalRows / pageSize;
        int mod = totalRows % pageSize;
        if(mod > 0)
            totalPages++;
        currentPage = 1;
        startRow = 0;
    }

    public void setStart(int currentPage)
    {
        this.currentPage = currentPage;
        startRow = (currentPage - 1) * pageSize;
    }

    private int totalRows;
    private int pageSize;
    private int currentPage;
    private int totalPages;
    private int startRow;
    private String linkUrl;
}