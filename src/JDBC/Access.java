package JDBC;/*
  Created by IntelliJ IDEA.
  User: 1817865166@qq.com
*/

public class Access {

    /*
    新闻列表
     */
    private String title;//文章标题
    private String imgList;//配图链接
    private String source;//文章来源
    private String newsId;//文章ID
    private String postTime;//推送时间

    /*
    新闻类型
     */
    private String TypeId;//类型代码
    private String TypeName;//类型名称

    /*
    IP获取，IP详细信息
     */
    private String IP;//IP地址
    private String province;//所在省份
    private String provinceID;//省份代码
    private String city;//所在城市
    private String cityId;//所在城市代码
    private String ISP;//网络供应商
    private String desc1;//服务商地址,因为desc是mysql关键字，所以用desc代替

    /*
    天气详情(当天)
     */
    private String address;//查询的城市
    private String cityCode;//等同于cityId
    private String temp;//温度
    private String weather;//天气状况
    private String windDirection;//风向
    private String windPower;//风力
    private String humidity;//湿度
    private String reportTime;//预报时间

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgList() {
        return imgList;
    }

    public void setImgList(String imgList) {
        this.imgList = imgList;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public String getPostTime() {
        return postTime;
    }

    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }

    public String getTypeId() {
        return TypeId;
    }

    public void setTypeId(String typeId) {
        TypeId = typeId;
    }

    public String getTypeName() {
        return TypeName;
    }

    public void setTypeName(String typeName) {
        TypeName = typeName;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getProvinceID() {
        return provinceID;
    }

    public void setProvinceID(String provinceID) {
        this.provinceID = provinceID;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getISP() {
        return ISP;
    }

    public void setISP(String ISP) {
        this.ISP = ISP;
    }

    public String getDesc1() {
        return desc1;
    }

    public void setDesc1(String desc) {
        this.desc1 = desc;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public String getWindPower() {
        return windPower;
    }

    public void setWindPower(String windPower) {
        this.windPower = windPower;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getReportTime() {
        return reportTime;
    }

    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
    }
}
