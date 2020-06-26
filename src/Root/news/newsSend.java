package Root.news;

import JDBC.Bean.resultJson;
import JDBC.Dao;
import JDBC.DaoImpl;
import com.alibaba.fastjson.JSON;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: PXY
 * Email: 1817865166@qq.com
 * Date: 2020/6/21
 */
@WebServlet(name = "newsSend", urlPatterns = "/newsSend")
public class newsSend extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // 上传文件存储目录
    private static final String UPLOAD_DIRECTORY = "upload";

    // 上传配置
    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

    /**
     * 上传数据及保存文件
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (!ServletFileUpload.isMultipartContent(request)) {
            PrintWriter writer = response.getWriter();
            writer.println("Error: 表单必须包含 enctype=multipart/form-data");
            writer.flush();
            return;
        }

        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setFileSizeMax(MAX_FILE_SIZE);
        upload.setSizeMax(MAX_REQUEST_SIZE);
        upload.setHeaderEncoding("UTF-8");
        String uploadPath = request.getServletContext().getRealPath("/").split("out")[0] + "web" + File.separator + UPLOAD_DIRECTORY;

        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        Map<String, Object> map = new HashMap<>();//存放表中其他字段
        String filepathReal = "";//图片路径，存放于数据库

        try {
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(request);

            if (formItems != null && formItems.size() > 0) {
                for (FileItem item : formItems) {
                    if (item.isFormField()) {
                        String filedName = item.getFieldName();
                        String value = item.getString("utf-8");
                        map.put(filedName, value);
                    } else {
                        String fileName = new File(item.getName()).getName();
                        uploadPath = uploadPath + File.separator + map.get("date");
                        File uploadDirReal = new File(uploadPath);
                        if (!uploadDirReal.exists()) {
                            uploadDirReal.mkdir();
                        }

                        String filePath = uploadPath + "\\" + fileName;
                        File storeFile = new File(filePath);
                        filepathReal = filePath.split("web")[1];
                        item.write(storeFile);
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        //向数据库存放数据
        Dao dao = new DaoImpl();
        String sql = "insert into swpnews(title,author,type,date,content,imgUrl) values(?,?,?,?,?,?)";
        List<Object> params = new ArrayList<>();
        params.add(map.get("title"));
        params.add(map.get("author"));
        params.add(map.get("type"));
        params.add(map.get("date"));
        params.add(map.get("content"));
        params.add(filepathReal);
        String information = dao.update(sql, params);
        resultJson resultJson = new resultJson();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().append(resultJson.operator(information));

    }
}
