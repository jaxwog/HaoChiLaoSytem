package page;

import java.io.IOException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

public class PageTag extends TagSupport {

	public PageTag() {
	}

	public int doStartTag() {
		try {
			JspWriter out = pageContext.getOut();
			out.print((new StringBuilder("\u7B2C "))
					.append(pager.getCurrentPage()).append(" \u9875/\u5171 ")
					.append(pager.getTotalPages()).append(" \u9875").toString());
			if (pager.getCurrentPage() == 1) {
				out.print("[\u6700\u9996\u9875]");
				out.print("[\u4E0A\u4E00\u9875]");
			}
			if (pager.getCurrentPage() != 1) {
				out.print((new StringBuilder("[<a href='"))
						.append(pager.getLinkUrl())
						.append("cpage=1'>\u6700\u9996\u9875</a>]").toString());
				out.print((new StringBuilder("[<a href='"))
						.append(pager.getLinkUrl()).append("cpage=")
						.append(pager.getCurrentPage() - 1)
						.append("'>\u4E0A\u4E00\u9875</a>]").toString());
			}
			for (int i = pager.getCurrentPage() - 3; i <= pager
					.getCurrentPage() + 3; i++)
				if (i > 0 && i <= pager.getTotalPages())
					if (i == pager.getCurrentPage())
						out.print((new StringBuilder(
								"[<span style='color:#FF0000; border: 1px solid #cccccc; font-weight:bold; width:15px;text-align: center;'> "))
								.append(i).append(" </span>]").toString());
					else
						out.print((new StringBuilder("[<a href='"))
								.append(pager.getLinkUrl()).append("cpage=")
								.append(i).append("'> ").append(i)
								.append(" </a>]").toString());

			if (pager.getCurrentPage() == pager.getTotalPages()
					|| pager.getTotalPages() == 0) {
				out.print("[\u4E0B\u4E00\u9875]");
				out.print("[\u6700\u672B\u9875]");
			}
			if (pager.getCurrentPage() != pager.getTotalPages()
					&& pager.getTotalPages() != 0) {
				out.print((new StringBuilder("[<a href='"))
						.append(pager.getLinkUrl()).append("cpage=")
						.append(pager.getCurrentPage() + 1)
						.append("'>\u4E0B\u4E00\u9875</a>]").toString());
				out.print((new StringBuilder("[<a href='"))
						.append(pager.getLinkUrl()).append("cpage=")
						.append(pager.getTotalPages())
						.append("'>\u6700\u672B\u9875</a>]").toString());
			}
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int doEndTag() {
		return 6;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public Pager getPager() {
		return pager;
	}

	private static final long serialVersionUID = 1L;
	private Pager pager;
}