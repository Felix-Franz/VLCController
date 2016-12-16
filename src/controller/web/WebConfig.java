package controller.web;

public class WebConfig {
	private int port = 0;
	private int maxConnections = 0;
	private boolean autoStart = false;
	
	public void setPort(int port) {
		this.port = port;
	}

	public void setMaxConnections(int maxConnections) {
		this.maxConnections = maxConnections;
	}

	public void setAutoStart(boolean autoStart) {
		this.autoStart = autoStart;
	}

	public int getPort() {
		return port;
	}

	public int getMaxConnections() {
		return maxConnections;
	}

	public boolean isAutoStart() {
		return autoStart;
	}
}
