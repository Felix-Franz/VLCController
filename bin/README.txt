This sofware can remote controle different instances of vlc on different maschines

Configuration
1. Edit conf/server.conf in the config folder (add the pc's running vlc, be sure to use the network ip)
	Only the server part of the config should be edited, the other part only if you know what you are doing!
2. go to the vlc pcs and change some settings (screenshots for this point in the docs folder):
	Tools > Preferences > Advanced View (edge: bottom left) > Interface > Main Interface
		- Tick Telnet
		- Goto Submenu Lua an set a password & port for this pc (same as in server.conf)
		- Set network ip of this pc (even the same as in server.conf)
4. run Start.bat

Using:
- You can use the Command line to controle vlc
- You also can use the webpage to controle vlc
	Just open (default) 127.0.0.1:8080 (or <NetworkIP>:8080)


Change webpage
Just look at webpage-simple.html to see how to controle vlc