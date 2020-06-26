package dowlandFile;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

/*
  Created by IntelliJ IDEA.
  User: 1817865166@qq.com
*/
@WebServlet(name = "DownlandServlet", urlPatterns = "/downlandFile")
public class DownlandServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取文件名
        String fileName = request.getParameter("filename");

        //加载文件，找到文件路径
        ServletContext servletContext = this.getServletContext();
        String realPath = servletContext.getRealPath("/upload/" + fileName);

        //获取文件流
        FileInputStream fis = new FileInputStream(realPath);

        //设置响应头，不直接读取文件
        //MIME类型就是设定某种扩展名的文件用一种应用程序来打开的方式类型，当该扩展名文件被访问的时候，浏览器会自动使用指定应用程序来打开。
        String miniType = servletContext.getMimeType(fileName);
        response.setHeader("content-type", miniType);

        //中文乱码
        String user_agent = request.getHeader("user-agent");

        //使用工具类编码文件
        fileName = DownlandUtils.getFileName(user_agent, fileName);

        //设置响应头打开方式
        response.setHeader("content-disposition", "attachment;filename=" + fileName);

        //保存文件
        ServletOutputStream os = response.getOutputStream();
        byte[] bytes = new byte[1024 * 1024 * 1024];
        int len;
        while ((len = fis.read(bytes)) != -1) {
            os.write(bytes, 0, len);
        }
        fis.close();
    }
}
