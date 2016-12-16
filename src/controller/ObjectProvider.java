package controller;

public class ObjectProvider {
	
	private String confPath;
	private ConfigReader confReader;
	private CommandSplitter splitter;
	private CommandHandler handler;
	private CommandlineReader commandlineReader;
	private controller.web.WebServer web;
	private controller.web.WebConfig webConf;
	
	public ObjectProvider(String confPath){
		this.confPath = confPath;
		init();
	}
	
	private void init(){
		splitter = new CommandSplitter();
		web = new controller.web.WebServer(this);
		handler = new CommandHandler(this);
		commandlineReader = new CommandlineReader();
		confReader = new ConfigReader(this);
		webConf = new controller.web.WebConfig();
		
		confReader.readConfig();
		web.autostart();
	}

	public String getConfPath() {
		return confPath;
	}

	public CommandSplitter getSplitter() {
		return splitter;
	}

	public CommandHandler getHandler() {
		return handler;
	}

	public CommandlineReader getCommandlineReader() {
		return commandlineReader;
	}

	public controller.web.WebServer getWeb() {
		return web;
	}
	
	public controller.web.WebConfig getWebConf() {
		return webConf;
	}
}
