package dao;

/**
 * Created by EVA-08 on 2017/7/10.
 */
class DAOException extends Exception {
    DAOException(Exception e) {
        super(e);
    }
}
class DBConnectionException extends DAOException {
    DBConnectionException(Exception e) {
        super(e);
    }
}
class DBUtilException extends DAOException {
    DBUtilException(Exception e) {
        super(e);
    }
}
class QueryCommoditiesException extends DAOException {
    QueryCommoditiesException(Exception e) {
        super(e);
    }
}
class UpdateCommodityException extends DAOException {
    UpdateCommodityException(Exception e) {
        super(e);
    }
}