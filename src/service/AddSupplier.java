package service;

import entiry.Supplier;

import javax.servlet.http.HttpSession;

/**
 * Created by EVA-08 on 2017/7/11.
 */

//增加供应商
public class AddSupplier {
    private HttpSession session;
    private Supplier supplier;
    private Integer marketID;
    public AddSupplier(String supplier, String address, String tel, Integer marketID, HttpSession session) {
        this.supplier = new Supplier();
        this.supplier.setSupplier(supplier);
        this.supplier.setAddress(address);
        this.supplier.setTel(tel);
        this.session = session;
        this.marketID = marketID;
    }

    public void add() {
        boolean success = new dao.AddSupplier(supplier, marketID).add();
        if (success) {
            session.setAttribute("addSucceeded", true);
        } else {
            session.setAttribute("addSucceeded", false);
        }
    }
}
