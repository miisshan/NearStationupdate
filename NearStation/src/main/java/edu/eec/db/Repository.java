package edu.eec.db;

import java.sql.Connection;

public class Repository {

    /**
     * Static connection, for db handler.
     */
    private static final Connection dbConnection = new DbFunctions(
            Config.url,
            Config.dbName,
            Config.userName,
            Config.password
    ).getConnection();

}