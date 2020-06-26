package Root.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Author: PXY
 * Email: 1817865166@qq.com
 * Date: 2020/6/25
 */
@WebFilter(filterName = "judge", urlPatterns = "/Root/control/*")//拦截所有管理员请求
public class judge implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        //若未登陆,跳转到登陆页面
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        request.setCharacterEncoding("UTF-8");
        HttpSession session = httpRequest.getSession();
        String isLogin = (String) session.getAttribute("judge");//是否登陆
        System.out.println(isLogin);
        isLogin = isLogin == null ? "" : isLogin;
        if (isLogin == "true") {
            chain.doFilter(request, response);
        } else {
            httpResponse.sendRedirect("../login.html");
        }
    }

    public void init(FilterConfig config) {

    }

}
