package prim.resources;

public class Database {
	private String postgresUser;
	private String postgresPass;
	private String dbIp;
	private String dbPort;
	private String dbName;
	
	
	
	
	public String getPostgresUser() {
		return postgresUser;
	}
	public void setPostgresUser(String postgresUser) {
		this.postgresUser = postgresUser;
	}


	public String getPostgresPass() {
		return postgresPass;
	}
	public void setPostgresPass(String postgresPass) {
		this.postgresPass = postgresPass;
	}


	public String getDbIp() {
		return dbIp;
	}
	public void setDbIp(String dbIp) {
		this.dbIp = dbIp;
	}


	public String getDbPort() {
		return dbPort;
	}

	public void setDbPort(String dbPort) {
		this.dbPort = dbPort;
	}


	public String getDbName() {
		return dbName;
	}
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}


	public String getConectionUrl() {
		return "jdbc:postgresql://"+ dbIp +":"+ dbPort +"/"+ dbName;
	}
	
}
