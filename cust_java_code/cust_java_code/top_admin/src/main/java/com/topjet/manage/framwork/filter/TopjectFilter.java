package com.topjet.manage.framwork.filter;

import com.topjet.manage.constants.WebConstants;
import org.apache.commons.lang.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 登录拦截
 *
 * @author Administrator
 */
@WebFilter
public class TopjectFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("初始化过滤器...");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String uri = httpServletRequest.getRequestURI();
        if (StringUtils.isEmpty(uri) || uri.endsWith("/admin/") || uri.endsWith("/adminv2/") || uri.endsWith("/top_admin/") ||
                uri.endsWith("/view/") || uri.endsWith("/main/")) {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/view/main/login.html");
            return;
        }

        //地推系统接口
        String apiToken = httpServletRequest.getParameter("api");
        if("dtsys".equals(apiToken)) {
            ((HttpServletResponse) response).setHeader("Access-Control-Allow-Credentials", "false");
            chain.doFilter(request, response);
            return;
        }

        Object userSession = httpServletRequest.getSession().getAttribute(WebConstants.SESSION_USER);
        if ((uri.endsWith(".jsp") || uri.endsWith(".html") || uri.endsWith(".do"))
                && userSession == null && !uri.endsWith("/login.html")
                && !uri.endsWith("/login.do") && !uri.endsWith("/logout.do") && !uri.endsWith("/verfiedPassword.do") && !uri
                .endsWith("/callCenterLog.do")) {
            if (httpServletRequest.getHeader("x-requested-with") != null
                    && httpServletRequest.getHeader("x-requested-with").equals("XMLHttpRequest")) { // ajax请求
                httpServletResponse.setHeader("sessionstatus", "timeout");
//                httpServletResponse.sendError(org.apache.http.HttpStatus.SC_UNAUTHORIZED, "登录信息过期");
                return;
            } else {
//                httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/view/main/login.html");
                httpServletResponse.setContentType("text/html;charset=UTF-8");
                PrintWriter out = httpServletResponse.getWriter();
                out.println("<html>");
                out.println("<script>");
                if (!uri.equals("/view/main/login.html") && !uri.equals("/view/main/login.html")) {
                    out.println("alert('登录超时,请重新登录!');");
                }
                out.println("window.top.location.href='" + httpServletRequest.getContextPath() + "/view/main/login.html" + "'");
                out.println("</script>");
                out.println("</html>");
                out.flush();
                out.close();
                return;
            }
        } else if ((uri.endsWith(".do") || uri.endsWith(".html"))
                && !uri.endsWith("/main.do")
                && !uri.endsWith("/login.do")
                && !uri.endsWith("/logout.do")
                && !uri.endsWith("/login.html")
                && !uri.endsWith("/callCenterLog.do")
                && httpServletRequest.getHeader("origin") == null
                && httpServletRequest.getHeader("referer") == null) {

            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/view/main/login.html");
            return;
        } else {
            //这里表示正确，会去寻找下一个链，如果不存在，则进行正常的页面跳转
            ((HttpServletResponse) response).setHeader("Access-Control-Allow-Credentials", "false");
            chain.doFilter(request, response);
            return;
        }


    }

    @Override
    public void destroy() {
    }

}
