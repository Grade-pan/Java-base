package JDBC_DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PetDaoImpl extends BaseDao implements PetDao {
    @Override
    public List<Pet> findAllPts() throws Exception {
        Connection conn = this.getConnection();
        String sql = "select * from pet";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<Pet> petList = new ArrayList<>();
        while (rs.next()) {
            Pet pet = new Pet();
            pet.setID(rs.getInt("id"));
            //System.out.println(rs.getInt("id"));
            pet.setOwnerID(rs.getInt("ownerID"));
            pet.setStoreID(rs.getInt("storeID"));
            pet.setName(rs.getString("name"));
            pet.setTypename(rs.getString("typename"));
            pet.setHealth(rs.getInt("health"));
            pet.setLove(rs.getInt("love"));
            pet.setBirthday(rs.getString("birthday"));
            petList.add(pet);
        }
        //关闭连接
        this.closeALL(conn, pstmt, rs);
        return petList;
    }

    @Override
    public Integer singleFind(String user, String password) throws Exception {
        String sql = "select id from pet where name=? and typename=?";
        return this.executeSQL(sql, user, password);
    }
}
