package backend.general.connector.specialConnectors;

import backend.general.Factory;
import backend.general.connector.AbstractConnector;
import backend.general.connector.enums.Command;
import backend.general.connector.enums.PlayerState;
import backend.utils.StringCutter;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.logging.Level;

/**
 * Created by Felix on 21.10.2017.
 */

/*

+----[ VLM commands ]
|help
|    Commands Syntax:
|        new (name) vod|broadcast|schedule [properties]
|        setup (name) (properties)
|        show [(name)|media|schedule]
|        del (name)|all|media|schedule
|        control (name) [instance_name] (command)
|        save (config_file)
|        export
|        load (config_file)
|    Media Proprieties Syntax:
|        input (input_name)
|        inputdel (input_name)|all
|        inputdeln input_number
|        output (output_name)
|        option (option_name)[=value]
|        enabled|disabled
|        loop|unloop (broadcast only)
|        mux (mux_name)
|    Schedule Proprieties Syntax:
|        enabled|disabled
|        append (command_until_rest_of_the_line)
|        date (year)/(month)/(day)-(hour):(minutes):(seconds)|now
|        period (years_aka_12_months)/(months_aka_30_days)/(days)-(hours):(minut                                                                                                             es):(seconds)
|        repeat (number_of_repetitions)
|    Control Commands Syntax:
|        play [input_number]
|        pause
|        stop
|        seek [+-](percentage) | [+-](seconds)s | [+-](milliseconds)ms
+----[ CLI commands ]
| add XYZ  . . . . . . . . . . . . . . . . . . . . add XYZ to playlist
| enqueue XYZ  . . . . . . . . . . . . . . . . . queue XYZ to playlist
| playlist . . . . . . . . . . . . .  show items currently in playlist
| search [string]  . .  search for items in playlist (or reset search)
| delete [X] . . . . . . . . . . . . . . . . delete item X in playlist
| move [X][Y]  . . . . . . . . . . . . move item X in playlist after Y
| sort key . . . . . . . . . . . . . . . . . . . . . sort the playlist
| sd [sd]  . . . . . . . . . . . . . show services discovery or toggle
| play . . . . . . . . . . . . . . . . . . . . . . . . . . play stream
| stop . . . . . . . . . . . . . . . . . . . . . . . . . . stop stream
| next . . . . . . . . . . . . . . . . . . . . . .  next playlist item
| prev . . . . . . . . . . . . . . . . . . . .  previous playlist item
| goto, gotoitem . . . . . . . . . . . . . . . . .  goto item at index
| repeat [on|off]  . . . . . . . . . . . . . .  toggle playlist repeat
| loop [on|off]  . . . . . . . . . . . . . . . .  toggle playlist loop
| random [on|off]  . . . . . . . . . . . . . .  toggle playlist random
| clear  . . . . . . . . . . . . . . . . . . . . .  clear the playlist
| status . . . . . . . . . . . . . . . . . . . current playlist status
| title [X]  . . . . . . . . . . . . . . set/get title in current item
| title_n  . . . . . . . . . . . . . . . .  next title in current item
| title_p  . . . . . . . . . . . . . .  previous title in current item
| chapter [X]  . . . . . . . . . . . . set/get chapter in current item
| chapter_n  . . . . . . . . . . . . . .  next chapter in current item
| chapter_p  . . . . . . . . . . . .  previous chapter in current item
|
| seek X . . . . . . . . . . . seek in seconds, for instance `seek 12'
| pause  . . . . . . . . . . . . . . . . . . . . . . . .  toggle pause
| fastforward  . . . . . . . . . . . . . . . . . . set to maximum rate
| rewind . . . . . . . . . . . . . . . . . . . . . set to minimum rate
| faster . . . . . . . . . . . . . . . . . .  faster playing of stream
| slower . . . . . . . . . . . . . . . . . .  slower playing of stream
| normal . . . . . . . . . . . . . . . . . .  normal playing of stream
| rate [playback rate] . . . . . . . . . .  set playback rate to value
| frame  . . . . . . . . . . . . . . . . . . . . . play frame by frame
| fullscreen, f, F [on|off]  . . . . . . . . . . . . toggle fullscreen
| info . . . . . . . . . . . . .  information about the current stream
| stats  . . . . . . . . . . . . . . . .  show statistical information
| get_time . . . . . . . . .  seconds elapsed since stream's beginning
| is_playing . . . . . . . . . . . .  1 if a stream plays, 0 otherwise
| get_title  . . . . . . . . . . . . . the title of the current stream
| get_length . . . . . . . . . . . .  the length of the current stream
|
| volume [X] . . . . . . . . . . . . . . . . . .  set/get audio volume
| volup [X]  . . . . . . . . . . . . . . .  raise audio volume X steps
| voldown [X]  . . . . . . . . . . . . . .  lower audio volume X steps
| achan [X]  . . . . . . . . . . . .  set/get stereo audio output mode
| atrack [X] . . . . . . . . . . . . . . . . . . . set/get audio track
| vtrack [X] . . . . . . . . . . . . . . . . . . . set/get video track
| vratio [X] . . . . . . . . . . . . . . .  set/get video aspect ratio
| vcrop, crop [X]  . . . . . . . . . . . . . . . .  set/get video crop
| vzoom, zoom [X]  . . . . . . . . . . . . . . . .  set/get video zoom
| vdeinterlace [X] . . . . . . . . . . . . . set/get video deinterlace
| vdeinterlace_mode [X]  . . . . . . .  set/get video deinterlace mode
| snapshot . . . . . . . . . . . . . . . . . . . . take video snapshot
| strack [X] . . . . . . . . . . . . . . . . .  set/get subtitle track
|
| vlm  . . . . . . . . . . . . . . . . . . . . . . . . .  load the VLM
| description  . . . . . . . . . . . . . . . . .  describe this module
| help, ? [pattern]  . . . . . . . . . . . . . . . . .  a help message
| longhelp [pattern] . . . . . . . . . . . . . . a longer help message
| lock . . . . . . . . . . . . . . . . . . . .  lock the telnet prompt
| logout . . . . . . . . . . . . . .  exit (if in a socket connection)
| quit . . . . . . . .  quit VLC (or logout if in a socket connection)
| shutdown . . . . . . . . . . . . . . . . . . . . . . .  shutdown VLC
+----[ end of help ]

 */
public class VLCConnector implements AbstractConnector {

    private String name;
    private String host;
    private int port;
    private String password;

    private Socket connection = null;
    private BufferedReader in = null;
    private PrintWriter out = null;

    public VLCConnector(String name, String host, int port, String password){
        this.name = name;
        this.host = host;
        this.port = port;
        this.password = password;
    }

    @Override
    public boolean connect(){
        try {
            disconnect();
            connection = new Socket();
            connection.setSoTimeout(Factory.getSettings().getConnectorTimeout());
            connection.connect(new InetSocketAddress(host, port), Factory.getSettings().getConnectorTimeout());
            connection = new Socket(host, port);
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            out = new PrintWriter(new OutputStreamWriter(connection.getOutputStream()));
            in.readLine();
            out.println(password);
            out.flush();
            in.readLine();
            if (in.readLine().equals("Welcome, Master")) return true;
        } catch (IOException e) {
            Factory.getLogger().log(Level.WARNING, "Could not connect to VLC Media Player " + name + ". Please check your configuration!");
            return false;
        }
        Factory.getLogger().log(Level.WARNING, "Failed to log in to VLC Media Player " + name + ". Wrong password!");
        return false;
    }

    @Override
    public void disconnect() {
        if (connection!=null)
            try {
                connection.close();
            } catch (IOException e) {
                Factory.getLogger().log(Level.WARNING, "Could not disconnect from " + name + ".");
            }
    }

    @Override
    public boolean runCommand(Command command){
        if (command == Command.PAUSE && getState() == PlayerState.PAUSED) return true;
        try {
            out.println(command.getCommand());
            out.flush();
            return true;
        } catch (Exception e) {
            Factory.getLogger().log(Level.WARNING, "Logger " + name + " could not run " + command + " command!");
        }
        return false;
    }

    @Override
    public String getTitle() {
        try {
            out.println("get_title");
            out.flush();
            String title = in.readLine().replace("> ", "");
            if (!title.equals("")) return title;
        } catch (Exception e) {
            Factory.getLogger().log(Level.WARNING, "Logger " + name + " could not read the actual title!");
        }
        return "no title";
    }

    @Override
    public int getVolume() {
        try {
            out.println("volume");
            out.flush();
            String rawVolume = in.readLine().replace("> ", "");
            int volume = Integer.valueOf(rawVolume);
            return volume*100/256;
        } catch (Exception e) {
            Factory.getLogger().log(Level.WARNING, "Logger " + name + " could not read the actual title!");
        }
        return -1;
    }

    @Override
    public PlayerState getState() {
        String output = "";
        try {
            out.println("status");
            out.flush();
            output+=in.readLine();
            output+=in.readLine();
            if (output.contains("new input"))       //if nothing is in playlist this line is missing
                output+=in.readLine();
        } catch (Exception e) {
            // empty output
        }

        String rawState = new StringCutter(output).cut("( state ", " )");
        if (rawState == null) return PlayerState.UNDEFINED;
        switch (rawState){
            case "playing":
                return PlayerState.PLAYING;
            case "paused":
                return PlayerState.PAUSED;
            case "stopped":
                return PlayerState.STOPPED;
            default:
                return PlayerState.UNDEFINED;
        }
    }
}
