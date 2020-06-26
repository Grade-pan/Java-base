package JDBC.Bean;

/**
 * Author: PXY
 * Email: 1817865166@qq.com
 * Date: 2020/6/12
 *
 * @desc 实现分页
 */

public class DivPage {
    private String urlParam;//url参数
    private int count;//总条数

    public DivPage(String urlParam, int count) {
        this.urlParam = urlParam;
        this.count = count;
    }

    public int[] PageLimit() {
        int[] pageLimit = new int[2];//返回分页起始位置的数
        int page = Integer.parseInt(urlParam.split("&")[0].split("=")[1]);
        int limit = Integer.parseInt(urlParam.split("&")[1].split("=")[1]);
        //如果请求页数大于count(总页数),直接返回（0，limit).
        if (limit >= count) {
            pageLimit[1] = count;
        }
        if (limit < count) {
            if (page == 1) {
                pageLimit[1] = limit;
            } else {
                if ((page * limit - count) >= 0) {
                    pageLimit[0] = (page - 1) * limit;
                    pageLimit[1] = count-(limit*(page-1));
                } else {
                    pageLimit[0] = (page - 1) * limit;
                    pageLimit[1] = limit;
                }
            }
        }
        return pageLimit;
    }
}
