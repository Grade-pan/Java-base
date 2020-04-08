package JDBC_DAO;

import java.util.List;

public class test {
    public static void main(String[] args) throws Exception {
        PetDao petDao = new PetDaoImpl();
        //按条件查询
        System.out.println("查询结果是： " + petDao.singleFind("王浩哲", "cat"));
        //查询全部
        List<Pet> petList = petDao.findAllPts();
        for (Pet pet : petList) {
            System.out.println("ID  " + pet.getID());
            System.out.println("ownerID  " + pet.getOwnerID());
            System.out.println("StoreID  " + pet.getStoreID());
            System.out.println("Name  " + pet.getName());
            System.out.println("Typename  " + pet.getTypename());
            System.out.println("Health  " + pet.getHealth());
            System.out.println("Love  " + pet.getLove());
        }
    }
}
