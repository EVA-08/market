package service;

import entiry.Supplier;

import javax.servlet.http.HttpSession;

/**
 * Created by EVA-08 on 2017/7/11.
 */
public class UpdateSupplier {
    private HttpSession session;
    private Supplier supplier;
    private Integer marketID;
    private String oldSupplier;
    public UpdateSupplier(String supplier, String address, String tel, String oldSupplier, Integer marketID, HttpSession session) {
        this.supplier = new Supplier();
        this.supplier.setSupplier(supplier);
        this.supplier.setAddress(address);
        this.supplier.setTel(tel);
        this.session = session;
        this.marketID = marketID;
        this.oldSupplier = oldSupplier;
    }

    public void update() {
        boolean success = new dao.UpdateSupplier(supplier, oldSupplier, marketID).update();
        if (success) {
            session.setAttribute("updateSucceeded", true);
        } else {
            session.setAttribute("updateSucceeded", false);
        }
    }
}
