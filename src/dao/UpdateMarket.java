package dao;

import entiry.Market;
import service.UpdatePassword;

/**
 * Created by EVA-08 on 2017/7/13.
 */
public class UpdateMarket {
    private Market market;

    public UpdateMarket(Market market) {
        this.market = market;
    }
    public boolean update() {
        String sql = "UPDATE market SET name = ?, address = ?, tel = ? WHERE id = ?";
        return new DBUtil(DBConnection.getConnection()).execute(sql, market.getName(),
                market.getAddress(), market.getTel(), market.getMarketID());
    }
}
