package JDBC_DAO;

import java.util.List;

public interface PetDao {
    /*
    查询所有宠物
     */
    List<Pet> findAllPts() throws Exception;

    //单个查询
    Integer singleFind(String user,String password) throws Exception;
}
