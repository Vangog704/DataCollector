package Filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.io.IOException;

@WebFilter(filterName = "AuthFilter", urlPatterns = { "*.xhtml" })
public class AuthorizationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        try {
            HttpServletRequest reqt = (HttpServletRequest) request;
            HttpServletResponse resp = (HttpServletResponse) response;
            HttpSession ses = reqt.getSession(false);

            String reqURI = reqt.getRequestURI();
            if(reqURI.indexOf("/admin/") >= 0) {
                if (ses != null && ses.getAttribute("username") != null)
                    chain.doFilter(request, response);
                else
                    resp.sendRedirect(reqt.getContextPath() + "/login.xhtml");
            }
            else {
                chain.doFilter(request, response);
            }

        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "doFilter is failed", JOptionPane.OK_OPTION);
        }
    }

    @Override
    public void destroy() {
    }
}
