package com.kaisn.flyway;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;

public class MigrationSqlite {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void migrate(){
		Flyway flyway=new Flyway();
		flyway.setDataSource(dataSource);
		flyway.setTable("schema_version");
		flyway.setLocations("db/migration");
		flyway.setEncoding("UTF-8");
		flyway.migrate();
	}
}
