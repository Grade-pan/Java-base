package UploadFile;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/*
  Created by IntelliJ IDEA.
  User: 1817865166@qq.com
*/
@WebServlet(name = "UploadServlet", urlPatterns = "/uploadFile")
public class UploadServlet extends HttpServlet {
    //上传文件存储目录
    private static final String UPLOAD_DIRECTORY = "upload";
    // 上传配置
    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //检测是否为多媒体上传
        if (!ServletFileUpload.isMultipartContent(request)) {
            //如果不是则停止
            PrintWriter out = response.getWriter();
            out.println("\"Error: 表单必须包含 enctype=multipart/form-data\"");//未设置上传参数
            out.flush();
            return;
        }
        // 配置上传参数
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        // 设置临时存储目录
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        ServletFileUpload upload = new ServletFileUpload(factory);

        // 设置最大文件上传值
        upload.setFileSizeMax(MAX_FILE_SIZE);

        // 设置最大请求值 (包含文件和表单数据)
        upload.setSizeMax(MAX_REQUEST_SIZE);

        // 中文处理
        upload.setHeaderEncoding("UTF-8");

        //构造临时路径来存储上传的文件，这个路径相对当前应用的目录，此路径在out目录下，不作为文件真实存放路径
        String uploadPath = request.getServletContext().getRealPath("./") + File.separator + UPLOAD_DIRECTORY;

        //如果目录不存在则创建
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        try {
            //解析请求内容，提取文件数据
            List<FileItem> formItems = upload.parseRequest(request);
            if (formItems != null && formItems.size() > 0) {
                //迭代表单数据
                for (FileItem item : formItems) {
                    //处理不在表单中的字段
                    if (!item.isFormField()) {
                        String fileName = new File(item.getName()).getName();
                        String filePath = "I:\\JSPTEST2\\web\\upload\\" + File.separator + fileName;//文件存放路径
                        File storeFile = new File(filePath);
                        //输出文件路径
                        System.out.println(storeFile);
                        //写入磁盘
                        item.write(storeFile);
                        request.setAttribute("message", "文件上传成功！");
                    }
                }
            }
        } catch (Exception e) {
            request.setAttribute("message", "错误信息:" + e.getMessage());
        }
        // 跳转到 message.jsp
        request.getServletContext().getRequestDispatcher("/JSP/message.jsp").forward(
                request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
