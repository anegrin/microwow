/**
 * This file is part of microWow.
 *
 * microWow is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation.
 *
 * microWow is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package wow;

import javax.microedition.io.Connector;
import javax.microedition.io.Datagram;
import javax.microedition.io.UDPDatagramConnection;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import wow.ProfileManager.Profile;

/**
 * this is the main class: the MIDlet
 *
 * @author anegrin
 * @version 0.2
 */
public class WowMidlet extends MIDlet implements CommandListener, ItemStateListener {
    
    private static long DELAY=250;
    
    private boolean editingProfile=false;
    
    /** Creates a new instance of HelloMidlet */
    public WowMidlet() {
    }
    
    private Command exitCommand;//GEN-BEGIN:MVDFields
    private Command newCommand;
    private List profilesList;
    private Command wakeUpCommand;
    private Command deleteCommand;
    private Alert confirmDeletionAlert;
    private Alert confirmWakeUpAlert;
    private Command editCommand;
    private Command okCommand;
    private Command cancelCommand;
    private Form profileForm;
    private TextField profileNameField;
    private TextField profileHostField;
    private TextField profilePortField;
    private TextField profileMACField;
    private org.netbeans.microedition.lcdui.WaitScreen wakeUpScreen;
    private org.netbeans.microedition.util.SimpleCancellableTask sendMagicPacketCancellableTask;
    private Alert wakedUpAlert;
    private Alert notWakedUpAlert;
    private Image itemImage;
    private Image sendErrorImage;
    private Image sendImage;
    private Image deleteImage;
    private Image errorImage;
    private TextField profilePasswordField;
    private ChoiceGroup profileUsePasswordGroup;
    private Gauge profileRepeatGauge;
    private Image keyImage;
    private StringItem title1;
    private Font boldFont;
    private StringItem title2;
    private StringItem title3;//GEN-END:MVDFields
    
//GEN-LINE:MVDMethods
    
    /** This method initializes UI of the application.//GEN-BEGIN:MVDInitBegin
     */
    private void initialize() {//GEN-END:MVDInitBegin
        // Insert pre-init code here
        getDisplay().setCurrent(get_profilesList());//GEN-LINE:MVDInitInit
        // Insert post-init code here
    }//GEN-LINE:MVDInitEnd
    
    /** Called by the system to indicate that a command has been invoked on a particular displayable.//GEN-BEGIN:MVDCABegin
     * @param command the Command that ws invoked
     * @param displayable the Displayable on which the command was invoked
     */
    public void commandAction(Command command, Displayable displayable) {//GEN-END:MVDCABegin
        // Insert global pre-action code here
        if (displayable == profilesList) {//GEN-BEGIN:MVDCABody
            if (command == deleteCommand) {//GEN-END:MVDCABody
                // Insert pre-action code here
                getDisplay().setCurrent(get_confirmDeletionAlert(), get_profilesList());//GEN-LINE:MVDCAAction15
                // Insert post-action code here
            } else if (command == wakeUpCommand) {//GEN-LINE:MVDCACase15
                // Insert pre-action code here
                getDisplay().setCurrent(get_confirmWakeUpAlert(), get_profilesList());//GEN-LINE:MVDCAAction13
                // Insert post-action code here
            } else if (command == newCommand) {//GEN-LINE:MVDCACase13
                // Insert pre-action code here
                
                editingProfile=false;
                resetProfileForm();
                
                getDisplay().setCurrent(get_profileForm());//GEN-LINE:MVDCAAction12
                // Insert post-action code here
            } else if (command == exitCommand) {//GEN-LINE:MVDCACase12
                // Insert pre-action code here
                exitMIDlet();//GEN-LINE:MVDCAAction11
                // Insert post-action code here
            } else if (command == editCommand) {//GEN-LINE:MVDCACase11
                // Insert pre-action code here
                
                editingProfile=true;
                resetProfileForm();
                loadCurrentProfile();
                
                getDisplay().setCurrent(get_profileForm());//GEN-LINE:MVDCAAction39
                // Insert post-action code here
            }//GEN-BEGIN:MVDCACase39
        } else if (displayable == confirmWakeUpAlert) {
            if (command == cancelCommand) {//GEN-END:MVDCACase39
                // Insert pre-action code here
                getDisplay().setCurrent(get_profilesList());//GEN-LINE:MVDCAAction30
                // Insert post-action code here
            } else if (command == okCommand) {//GEN-LINE:MVDCACase30
                // Insert pre-action code here
                getDisplay().setCurrent(get_wakeUpScreen());//GEN-LINE:MVDCAAction29
                // Insert post-action code here
            }//GEN-BEGIN:MVDCACase29
        } else if (displayable == confirmDeletionAlert) {
            if (command == cancelCommand) {//GEN-END:MVDCACase29
                // Insert pre-action code here
                getDisplay().setCurrent(get_profilesList());//GEN-LINE:MVDCAAction28
                // Insert post-action code here
            } else if (command == okCommand) {//GEN-LINE:MVDCACase28
                // Insert pre-action code here
                getDisplay().setCurrent(get_profilesList());//GEN-LINE:MVDCAAction27
                // Insert post-action code here
                
                deleteCurrentProfile();
                
            }//GEN-BEGIN:MVDCACase27
        } else if (displayable == profileForm) {
            if (command == cancelCommand) {//GEN-END:MVDCACase27
                // Insert pre-action code here
                getDisplay().setCurrent(get_profilesList());//GEN-LINE:MVDCAAction38
                // Insert post-action code here
            } else if (command == okCommand) {//GEN-LINE:MVDCACase38
                // Insert pre-action code here
                
                if (checkCurrentProfile()){
                    saveCurrentProfile();
                } else {
                    return;
                }
                
                getDisplay().setCurrent(get_profilesList());//GEN-LINE:MVDCAAction37
                // Insert post-action code here
            }//GEN-BEGIN:MVDCACase37
        } else if (displayable == wakeUpScreen) {
            if (command == wakeUpScreen.FAILURE_COMMAND) {//GEN-END:MVDCACase37
                // Insert pre-action code here
                getDisplay().setCurrent(get_notWakedUpAlert(), get_profilesList());//GEN-LINE:MVDCAAction42
                // Insert post-action code here
            } else if (command == wakeUpScreen.SUCCESS_COMMAND) {//GEN-LINE:MVDCACase42
                // Insert pre-action code here
                getDisplay().setCurrent(get_wakedUpAlert(), get_profilesList());//GEN-LINE:MVDCAAction41
                // Insert post-action code here
            } else if (command == cancelCommand) {//GEN-LINE:MVDCACase41
                // Insert pre-action code here
                
                wakeUpScreen.getTask().cancel();
                
                getDisplay().setCurrent(get_profilesList());//GEN-LINE:MVDCAAction67
                // Insert post-action code here
            }//GEN-BEGIN:MVDCACase67
        }//GEN-END:MVDCACase67
        // Insert global post-action code here
}//GEN-LINE:MVDCAEnd
    
    /**
     * This method should return an instance of the display.
     */
    public Display getDisplay() {//GEN-FIRST:MVDGetDisplay
        return Display.getDisplay(this);
    }//GEN-LAST:MVDGetDisplay
    
    /**
     * This method should exit the midlet.
     */
    public void exitMIDlet() {//GEN-FIRST:MVDExitMidlet
        getDisplay().setCurrent(null);
        destroyApp(true);
        notifyDestroyed();
    }//GEN-LAST:MVDExitMidlet
    
    
    
    /** This method returns instance for exitCommand component and should be called instead of accessing exitCommand field directly.//GEN-BEGIN:MVDGetBegin5
     * @return Instance for exitCommand component
     */
    public Command get_exitCommand() {
        if (exitCommand == null) {//GEN-END:MVDGetBegin5
            // Insert pre-init code here
            exitCommand = new Command("Exit", Command.EXIT, 1);//GEN-LINE:MVDGetInit5
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd5
        return exitCommand;
    }//GEN-END:MVDGetEnd5
    
    /** This method returns instance for newCommand component and should be called instead of accessing newCommand field directly.//GEN-BEGIN:MVDGetBegin6
     * @return Instance for newCommand component
     */
    public Command get_newCommand() {
        if (newCommand == null) {//GEN-END:MVDGetBegin6
            // Insert pre-init code here
            newCommand = new Command("New", Command.SCREEN, 4);//GEN-LINE:MVDGetInit6
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd6
        return newCommand;
    }//GEN-END:MVDGetEnd6
    
    /** This method returns instance for profilesList component and should be called instead of accessing profilesList field directly.//GEN-BEGIN:MVDGetBegin8
     * @return Instance for profilesList component
     */
    public List get_profilesList() {
        if (profilesList == null) {//GEN-END:MVDGetBegin8
            // Insert pre-init code here
            profilesList = new List("Profiles", Choice.IMPLICIT, new String[0], new Image[0]);//GEN-BEGIN:MVDGetInit8
            profilesList.addCommand(get_exitCommand());
            profilesList.addCommand(get_newCommand());
            profilesList.addCommand(get_wakeUpCommand());
            profilesList.addCommand(get_deleteCommand());
            profilesList.addCommand(get_editCommand());
            profilesList.setCommandListener(this);
            profilesList.setSelectedFlags(new boolean[0]);
            profilesList.setSelectCommand(get_wakeUpCommand());//GEN-END:MVDGetInit8
            // Insert post-init code here
            
            initProfiles();
            
        }//GEN-BEGIN:MVDGetEnd8
        return profilesList;
    }//GEN-END:MVDGetEnd8
    
    /** This method returns instance for wakeUpCommand component and should be called instead of accessing wakeUpCommand field directly.//GEN-BEGIN:MVDGetBegin10
     * @return Instance for wakeUpCommand component
     */
    public Command get_wakeUpCommand() {
        if (wakeUpCommand == null) {//GEN-END:MVDGetBegin10
            // Insert pre-init code here
            wakeUpCommand = new Command("Wake up", Command.OK, 2);//GEN-LINE:MVDGetInit10
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd10
        return wakeUpCommand;
    }//GEN-END:MVDGetEnd10
    
    /** This method returns instance for deleteCommand component and should be called instead of accessing deleteCommand field directly.//GEN-BEGIN:MVDGetBegin14
     * @return Instance for deleteCommand component
     */
    public Command get_deleteCommand() {
        if (deleteCommand == null) {//GEN-END:MVDGetBegin14
            // Insert pre-init code here
            deleteCommand = new Command("Delete", Command.ITEM, 5);//GEN-LINE:MVDGetInit14
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd14
        return deleteCommand;
    }//GEN-END:MVDGetEnd14
    
    /** This method returns instance for confirmDeletionAlert component and should be called instead of accessing confirmDeletionAlert field directly.//GEN-BEGIN:MVDGetBegin21
     * @return Instance for confirmDeletionAlert component
     */
    public Alert get_confirmDeletionAlert() {
        if (confirmDeletionAlert == null) {//GEN-END:MVDGetBegin21
            // Insert pre-init code here
            confirmDeletionAlert = new Alert("Delete", "Please confirm profile deletion", get_deleteImage(), AlertType.CONFIRMATION);//GEN-BEGIN:MVDGetInit21
            confirmDeletionAlert.addCommand(get_okCommand());
            confirmDeletionAlert.addCommand(get_cancelCommand());
            confirmDeletionAlert.setCommandListener(this);
            confirmDeletionAlert.setTimeout(-2);//GEN-END:MVDGetInit21
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd21
        return confirmDeletionAlert;
    }//GEN-END:MVDGetEnd21
    
    /** This method returns instance for confirmWakeUpAlert component and should be called instead of accessing confirmWakeUpAlert field directly.//GEN-BEGIN:MVDGetBegin23
     * @return Instance for confirmWakeUpAlert component
     */
    public Alert get_confirmWakeUpAlert() {
        if (confirmWakeUpAlert == null) {//GEN-END:MVDGetBegin23
            // Insert pre-init code here
            confirmWakeUpAlert = new Alert("Wake up", "Please confirm system wake up", get_sendImage(), AlertType.CONFIRMATION);//GEN-BEGIN:MVDGetInit23
            confirmWakeUpAlert.addCommand(get_okCommand());
            confirmWakeUpAlert.addCommand(get_cancelCommand());
            confirmWakeUpAlert.setCommandListener(this);
            confirmWakeUpAlert.setTimeout(-2);//GEN-END:MVDGetInit23
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd23
        return confirmWakeUpAlert;
    }//GEN-END:MVDGetEnd23
    
    /** This method returns instance for editCommand component and should be called instead of accessing editCommand field directly.//GEN-BEGIN:MVDGetBegin24
     * @return Instance for editCommand component
     */
    public Command get_editCommand() {
        if (editCommand == null) {//GEN-END:MVDGetBegin24
            // Insert pre-init code here
            editCommand = new Command("Edit", Command.ITEM, 3);//GEN-LINE:MVDGetInit24
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd24
        return editCommand;
    }//GEN-END:MVDGetEnd24
    
    /** This method returns instance for okCommand component and should be called instead of accessing okCommand field directly.//GEN-BEGIN:MVDGetBegin25
     * @return Instance for okCommand component
     */
    public Command get_okCommand() {
        if (okCommand == null) {//GEN-END:MVDGetBegin25
            // Insert pre-init code here
            okCommand = new Command("Ok", Command.OK, 1);//GEN-LINE:MVDGetInit25
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd25
        return okCommand;
    }//GEN-END:MVDGetEnd25
    
    /** This method returns instance for cancelCommand component and should be called instead of accessing cancelCommand field directly.//GEN-BEGIN:MVDGetBegin26
     * @return Instance for cancelCommand component
     */
    public Command get_cancelCommand() {
        if (cancelCommand == null) {//GEN-END:MVDGetBegin26
            // Insert pre-init code here
            cancelCommand = new Command("Cancel", Command.CANCEL, 2);//GEN-LINE:MVDGetInit26
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd26
        return cancelCommand;
    }//GEN-END:MVDGetEnd26
    
    /** This method returns instance for profileForm component and should be called instead of accessing profileForm field directly.//GEN-BEGIN:MVDGetBegin31
     * @return Instance for profileForm component
     */
    public Form get_profileForm() {
        if (profileForm == null) {//GEN-END:MVDGetBegin31
            // Insert pre-init code here
            profileForm = new Form("Profile", new Item[] {//GEN-BEGIN:MVDGetInit31
                get_title1(),
                get_profileNameField(),
                get_title2(),
                get_profileHostField(),
                get_profilePortField(),
                get_profileMACField(),
                get_profileRepeatGauge(),
                get_title3(),
                get_profileUsePasswordGroup(),
                get_profilePasswordField()
            });
            profileForm.addCommand(get_okCommand());
            profileForm.addCommand(get_cancelCommand());
            profileForm.setCommandListener(this);//GEN-END:MVDGetInit31
            // Insert post-init code here
            
            profileForm.setItemStateListener(this);
            
        }//GEN-BEGIN:MVDGetEnd31
        return profileForm;
    }//GEN-END:MVDGetEnd31
    
    /** This method returns instance for profileNameField component and should be called instead of accessing profileNameField field directly.//GEN-BEGIN:MVDGetBegin32
     * @return Instance for profileNameField component
     */
    public TextField get_profileNameField() {
        if (profileNameField == null) {//GEN-END:MVDGetBegin32
            // Insert pre-init code here
            profileNameField = new TextField("Name", null, 16, TextField.ANY);//GEN-LINE:MVDGetInit32
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd32
        return profileNameField;
    }//GEN-END:MVDGetEnd32
    
    /** This method returns instance for profileHostField component and should be called instead of accessing profileHostField field directly.//GEN-BEGIN:MVDGetBegin34
     * @return Instance for profileHostField component
     */
    public TextField get_profileHostField() {
        if (profileHostField == null) {//GEN-END:MVDGetBegin34
            // Insert pre-init code here
            profileHostField = new TextField("Host", null, 256, TextField.URL);//GEN-LINE:MVDGetInit34
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd34
        return profileHostField;
    }//GEN-END:MVDGetEnd34
    
    /** This method returns instance for profilePortField component and should be called instead of accessing profilePortField field directly.//GEN-BEGIN:MVDGetBegin35
     * @return Instance for profilePortField component
     */
    public TextField get_profilePortField() {
        if (profilePortField == null) {//GEN-END:MVDGetBegin35
            // Insert pre-init code here
            profilePortField = new TextField("Port", "9", 5, TextField.DECIMAL);//GEN-LINE:MVDGetInit35
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd35
        return profilePortField;
    }//GEN-END:MVDGetEnd35
    
    /** This method returns instance for profileMACField component and should be called instead of accessing profileMACField field directly.//GEN-BEGIN:MVDGetBegin36
     * @return Instance for profileMACField component
     */
    public TextField get_profileMACField() {
        if (profileMACField == null) {//GEN-END:MVDGetBegin36
            // Insert pre-init code here
            profileMACField = new TextField("MAC", "1A:2B:3C:4D:5E:6F", 17, TextField.ANY | TextField.NON_PREDICTIVE);//GEN-LINE:MVDGetInit36
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd36
        return profileMACField;
    }//GEN-END:MVDGetEnd36
    
    /** This method returns instance for wakeUpScreen component and should be called instead of accessing wakeUpScreen field directly.//GEN-BEGIN:MVDGetBegin40
     * @return Instance for wakeUpScreen component
     */
    public org.netbeans.microedition.lcdui.WaitScreen get_wakeUpScreen() {
        if (wakeUpScreen == null) {//GEN-END:MVDGetBegin40
            // Insert pre-init code here
            wakeUpScreen = new org.netbeans.microedition.lcdui.WaitScreen(getDisplay());//GEN-BEGIN:MVDGetInit40
            wakeUpScreen.addCommand(get_cancelCommand());
            wakeUpScreen.setCommandListener(this);
            wakeUpScreen.setTitle("Sending...");
            wakeUpScreen.setFullScreenMode(true);
            wakeUpScreen.setText("Sending magic packet");
            wakeUpScreen.setImage(get_sendImage());
            wakeUpScreen.setTask(get_sendMagicPacketCancellableTask());//GEN-END:MVDGetInit40
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd40
        return wakeUpScreen;
    }//GEN-END:MVDGetEnd40
    
    /** This method returns instance for sendMagicPacketCancellableTask component and should be called instead of accessing sendMagicPacketCancellableTask field directly.//GEN-BEGIN:MVDGetBegin43
     * @return Instance for sendMagicPacketCancellableTask component
     */
    public org.netbeans.microedition.util.SimpleCancellableTask get_sendMagicPacketCancellableTask() {
        if (sendMagicPacketCancellableTask == null) {//GEN-END:MVDGetBegin43
            // Insert pre-init code here
            sendMagicPacketCancellableTask = new org.netbeans.microedition.util.SimpleCancellableTask();//GEN-BEGIN:MVDGetInit43
            sendMagicPacketCancellableTask.setExecutable(new org.netbeans.microedition.util.Executable() {
                public void execute() throws Exception {
                    sendMagicPacket();
                }
            });//GEN-END:MVDGetInit43
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd43
        return sendMagicPacketCancellableTask;
    }//GEN-END:MVDGetEnd43
    
    /** This method returns instance for wakedUpAlert component and should be called instead of accessing wakedUpAlert field directly.//GEN-BEGIN:MVDGetBegin46
     * @return Instance for wakedUpAlert component
     */
    public Alert get_wakedUpAlert() {
        if (wakedUpAlert == null) {//GEN-END:MVDGetBegin46
            // Insert pre-init code here
            wakedUpAlert = new Alert("Success", "Magic packet has been sent", get_sendImage(), AlertType.INFO);//GEN-BEGIN:MVDGetInit46
            wakedUpAlert.setTimeout(3000);//GEN-END:MVDGetInit46
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd46
        return wakedUpAlert;
    }//GEN-END:MVDGetEnd46
    
    /** This method returns instance for notWakedUpAlert component and should be called instead of accessing notWakedUpAlert field directly.//GEN-BEGIN:MVDGetBegin47
     * @return Instance for notWakedUpAlert component
     */
    public Alert get_notWakedUpAlert() {
        if (notWakedUpAlert == null) {//GEN-END:MVDGetBegin47
            // Insert pre-init code here
            notWakedUpAlert = new Alert("Failure", "Can\'t send magic packet", get_sendErrorImage(), AlertType.ERROR);//GEN-BEGIN:MVDGetInit47
            notWakedUpAlert.setTimeout(3000);//GEN-END:MVDGetInit47
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd47
        return notWakedUpAlert;
    }//GEN-END:MVDGetEnd47
    
    /** This method returns instance for itemImage component and should be called instead of accessing itemImage field directly.//GEN-BEGIN:MVDGetBegin48
     * @return Instance for itemImage component
     */
    public Image get_itemImage() {
        if (itemImage == null) {//GEN-END:MVDGetBegin48
            // Insert pre-init code here
            try {//GEN-BEGIN:MVDGetInit48
                itemImage = Image.createImage("/res/item.png");
            } catch (java.io.IOException exception) {
                exception.printStackTrace();
            }//GEN-END:MVDGetInit48
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd48
        return itemImage;
    }//GEN-END:MVDGetEnd48
    
    /** This method returns instance for sendErrorImage component and should be called instead of accessing sendErrorImage field directly.//GEN-BEGIN:MVDGetBegin49
     * @return Instance for sendErrorImage component
     */
    public Image get_sendErrorImage() {
        if (sendErrorImage == null) {//GEN-END:MVDGetBegin49
            // Insert pre-init code here
            try {//GEN-BEGIN:MVDGetInit49
                sendErrorImage = Image.createImage("/res/send_error.png");
            } catch (java.io.IOException exception) {
                exception.printStackTrace();
            }//GEN-END:MVDGetInit49
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd49
        return sendErrorImage;
    }//GEN-END:MVDGetEnd49
    
    /** This method returns instance for sendImage component and should be called instead of accessing sendImage field directly.//GEN-BEGIN:MVDGetBegin50
     * @return Instance for sendImage component
     */
    public Image get_sendImage() {
        if (sendImage == null) {//GEN-END:MVDGetBegin50
            // Insert pre-init code here
            try {//GEN-BEGIN:MVDGetInit50
                sendImage = Image.createImage("/res/send.png");
            } catch (java.io.IOException exception) {
                exception.printStackTrace();
            }//GEN-END:MVDGetInit50
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd50
        return sendImage;
    }//GEN-END:MVDGetEnd50
    
    /** This method returns instance for deleteImage component and should be called instead of accessing deleteImage field directly.//GEN-BEGIN:MVDGetBegin51
     * @return Instance for deleteImage component
     */
    public Image get_deleteImage() {
        if (deleteImage == null) {//GEN-END:MVDGetBegin51
            // Insert pre-init code here
            try {//GEN-BEGIN:MVDGetInit51
                deleteImage = Image.createImage("/res/delete.png");
            } catch (java.io.IOException exception) {
                exception.printStackTrace();
            }//GEN-END:MVDGetInit51
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd51
        return deleteImage;
    }//GEN-END:MVDGetEnd51
    
    /** This method returns instance for errorImage component and should be called instead of accessing errorImage field directly.//GEN-BEGIN:MVDGetBegin52
     * @return Instance for errorImage component
     */
    public Image get_errorImage() {
        if (errorImage == null) {//GEN-END:MVDGetBegin52
            // Insert pre-init code here
            try {//GEN-BEGIN:MVDGetInit52
                errorImage = Image.createImage("/res/error.png");
            } catch (java.io.IOException exception) {
                exception.printStackTrace();
            }//GEN-END:MVDGetInit52
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd52
        return errorImage;
    }//GEN-END:MVDGetEnd52
    
    /** This method returns instance for profilePasswordField component and should be called instead of accessing profilePasswordField field directly.//GEN-BEGIN:MVDGetBegin55
     * @return Instance for profilePasswordField component
     */
    public TextField get_profilePasswordField() {
        if (profilePasswordField == null) {//GEN-END:MVDGetBegin55
            // Insert pre-init code here
            profilePasswordField = new TextField("Password", null, 12, TextField.ANY | TextField.NON_PREDICTIVE);//GEN-LINE:MVDGetInit55
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd55
        return profilePasswordField;
    }//GEN-END:MVDGetEnd55
    
    /** This method returns instance for profileUsePasswordGroup component and should be called instead of accessing profileUsePasswordGroup field directly.//GEN-BEGIN:MVDGetBegin57
     * @return Instance for profileUsePasswordGroup component
     */
    public ChoiceGroup get_profileUsePasswordGroup() {
        if (profileUsePasswordGroup == null) {//GEN-END:MVDGetBegin57
            // Insert pre-init code here
            profileUsePasswordGroup = new ChoiceGroup(null, Choice.MULTIPLE, new String[] {"Send password"}, new Image[] {get_keyImage()});//GEN-BEGIN:MVDGetInit57
            profileUsePasswordGroup.setSelectedFlags(new boolean[] {false});//GEN-END:MVDGetInit57
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd57
        return profileUsePasswordGroup;
    }//GEN-END:MVDGetEnd57
    
    /** This method returns instance for profileRepeatGauge component and should be called instead of accessing profileRepeatGauge field directly.//GEN-BEGIN:MVDGetBegin63
     * @return Instance for profileRepeatGauge component
     */
    public Gauge get_profileRepeatGauge() {
        if (profileRepeatGauge == null) {//GEN-END:MVDGetBegin63
            // Insert pre-init code here
            profileRepeatGauge = new Gauge("Packets to send", true, 10, 1);//GEN-LINE:MVDGetInit63
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd63
        return profileRepeatGauge;
    }//GEN-END:MVDGetEnd63
    /** This method returns instance for keyImage component and should be called instead of accessing keyImage field directly.//GEN-BEGIN:MVDGetBegin66
     * @return Instance for keyImage component
     */
    public Image get_keyImage() {
        if (keyImage == null) {//GEN-END:MVDGetBegin66
            // Insert pre-init code here
            try {//GEN-BEGIN:MVDGetInit66
                keyImage = Image.createImage("/res/key.png");
            } catch (java.io.IOException exception) {
                exception.printStackTrace();
            }//GEN-END:MVDGetInit66
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd66
        return keyImage;
    }//GEN-END:MVDGetEnd66

    /** This method returns instance for title1 component and should be called instead of accessing title1 field directly.//GEN-BEGIN:MVDGetBegin68
     * @return Instance for title1 component
     */
    public StringItem get_title1() {
        if (title1 == null) {//GEN-END:MVDGetBegin68
            // Insert pre-init code here
            title1 = new StringItem("Infos", null);//GEN-BEGIN:MVDGetInit68
            title1.setFont(get_boldFont());//GEN-END:MVDGetInit68
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd68
        return title1;
    }//GEN-END:MVDGetEnd68

    /** This method returns instance for boldFont component and should be called instead of accessing boldFont field directly.//GEN-BEGIN:MVDGetBegin69
     * @return Instance for boldFont component
     */
    public Font get_boldFont() {
        if (boldFont == null) {//GEN-END:MVDGetBegin69
            // Insert pre-init code here
            boldFont = Font.getFont(Font.FACE_SYSTEM, 0x1, Font.SIZE_SMALL);//GEN-LINE:MVDGetInit69
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd69
        return boldFont;
    }//GEN-END:MVDGetEnd69

    /** This method returns instance for title2 component and should be called instead of accessing title2 field directly.//GEN-BEGIN:MVDGetBegin70
     * @return Instance for title2 component
     */
    public StringItem get_title2() {
        if (title2 == null) {//GEN-END:MVDGetBegin70
            // Insert pre-init code here
            title2 = new StringItem("Network", null);//GEN-BEGIN:MVDGetInit70
            title2.setFont(get_boldFont());//GEN-END:MVDGetInit70
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd70
        return title2;
    }//GEN-END:MVDGetEnd70

    /** This method returns instance for title3 component and should be called instead of accessing title3 field directly.//GEN-BEGIN:MVDGetBegin71
     * @return Instance for title3 component
     */
    public StringItem get_title3() {
        if (title3 == null) {//GEN-END:MVDGetBegin71
            // Insert pre-init code here
            title3 = new StringItem("Security", null);//GEN-BEGIN:MVDGetInit71
            title3.setFont(get_boldFont());//GEN-END:MVDGetInit71
            // Insert post-init code here
        }//GEN-BEGIN:MVDGetEnd71
        return title3;
    }//GEN-END:MVDGetEnd71
    
    public void startApp() {
        initialize();
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }
    
    /**
     * delete the selected profile
     *
     */
    private void deleteCurrentProfile() {
        int index=profilesList.getSelectedIndex();
        String currentProfileName=profilesList.getString(index);
        try {
            ProfileManager.getInstance().deleteProfile(currentProfileName);
            profilesList.delete(index);
        } catch (Exception e) {
            displayError("Can't delete profile "+currentProfileName);
        }
    }
    
    /**
     * init profiles list
     *
     */
    private void initProfiles() {
        String[] names=ProfileManager.getInstance().getProfileNames();
        if (names!=null){
            for (int i=0; i < names.length; i++) {
                profilesList.append(names[i], get_itemImage());
            }
        }
    }
    
    /**
     * save the profile (existing or new one)
     *
     */
    private void saveCurrentProfile() {
        
        try {
            
            String name=profileNameField.getString();
            String host=profileHostField.getString();
            int port=Integer.parseInt(profilePortField.getString());
            byte[] MAC = parseMAC(profileMACField.getString());
            boolean usePassword=profileUsePasswordGroup.isSelected(0);
            byte[] password=parsePassword(profilePasswordField.getString());
            int repeat=profileRepeatGauge.getValue();
            
            Profile profile=new Profile(name, host, port, MAC, usePassword, password, repeat);
            
            ProfileManager.getInstance().saveProfile(profile);
            
            //if it's a new one let's add it to the list
            if (!editingProfile) profilesList.append(name, get_itemImage());
            
        } catch (Exception e) {
            displayError("Can't save profile "+profileNameField.getString());
        }
        
        
    }
    
    /**
     * load the selected profile
     *
     */    
    private void loadCurrentProfile() {
        int index=profilesList.getSelectedIndex();
        String currentProfileName=profilesList.getString(index);
        try {
            Profile currentProfile=ProfileManager.getInstance().loadProfile(currentProfileName);
            
            //it's not a new profile; setting name filed to read only
            get_profileNameField().setConstraints(TextField.UNEDITABLE);
            get_profileNameField().setString(currentProfile.getName());
            get_profileHostField().setString(currentProfile.getHost());
            get_profilePortField().setString(String.valueOf(currentProfile.getPort()));
            get_profileMACField().setString(formatMAC(currentProfile.getMAC()));
            
            if (currentProfile.isUsePassword()) get_profileUsePasswordGroup().setSelectedIndex(0, true);
            get_profilePasswordField().setString(formatPassword(currentProfile.getPassword()));
            
            get_profileRepeatGauge().setValue(currentProfile.getRepeat());
            
        } catch (Exception e) {
            displayError("Can't delete profile "+currentProfileName);
        }
    }
    
    /**
     * check profile form fields
     *
     */
    private boolean checkCurrentProfile() {
        
        String name=profileNameField.getString();
        String host=profileHostField.getString();
        String port=profilePortField.getString();
        String MAC = profileMACField.getString();
        String password = profilePasswordField.getString();
        
        if (name==null || name.length()==0){
            displayError("Profile name is required");
            return false;
        }
        
        if (host==null || host.length()==0){
            displayError("Profile host is required");
            return false;
        }
        
        if (port==null || port.length()==0){
            displayError("Profile port is required");
            return false;
        }
        
        //password is not mandatory but if it's set it has to be of 12 or 8 chars
        //and must contain valid hex codes
        if (password!=null && password.length()>0){
            if (password.length()!=12 && password.length()!=8) {
                displayError("Password address must have 12 or 8 characters");
                return false;
            } else {
                try{
                    parsePassword(password);
                } catch (Exception e){
                    e.printStackTrace();
                    displayError("Can't parse password; it has to be in the form 1A2B3C4D or 1A2B3C4D5E6F");
                    return false;
                }
            }
        }
        
        //MAC has to be of 17 chars and must contain valid hex codes separated by :
        if (MAC==null || MAC.length()==0){
            displayError("Profile MAC is required");
            return false;
        } else if (MAC.length()!=17) {
            displayError("MAC address must have 17 characters");
            return false;
        } else {
            try{
                parseMAC(MAC);
            } catch (Exception e){
                e.printStackTrace();
                displayError("Can't parse MAC address; it has to be in the form 1A:2B:3C:4D:5E:6F");
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * display an alert with the given error message
     *
     */
    private void displayError(String message) {
        Alert error=new Alert("Error", message, get_errorImage(), AlertType.ERROR);
        error.setTimeout(Alert.FOREVER);
        getDisplay().setCurrent(error);
    }
    
    /** 
     * parse MAC string to bytes
     *
     */
    private byte[] parseMAC(String MAC) throws Exception {
        
        StringBuffer buffer=new StringBuffer(MAC);
        buffer.deleteCharAt(2).deleteCharAt(4).deleteCharAt(6).deleteCharAt(8).deleteCharAt(10);//remove :
        byte[] parsedMAC=toBytes(buffer.toString());
        
        return parsedMAC;
    }
    
    /** 
     * parse password string to bytes
     *
     */
    private byte[] parsePassword(String password) throws Exception {
        
        if (password==null || password.length()==0){
            return new byte[0];
        }
        
        StringBuffer buffer=new StringBuffer(password);
        byte[] parsedPassword=toBytes(buffer.toString());
        
        return parsedPassword;
    }
    
    /**
     * fomrmat MAC's bytes to hex string with : as separator
     *
     */
    private String formatMAC(byte[] MAC) {
        
        StringBuffer buffer=new StringBuffer(17);
        
        for (int i=0; i < MAC.length; i++) {
            if (i>0) buffer.append(':');
            String hex=toHexString(MAC[i]);
            if (hex.length()==1) buffer.append('0');
            buffer.append(hex);
        }
        
        return buffer.toString();
        
    }
    
    /**
     * fomrmat password's bytes to hex string
     *
     */
    private String formatPassword(byte[] password) {
        
        if (password==null || password.length==0){
            return "";
        }
        
        StringBuffer buffer=new StringBuffer(12);
        
        for (int i=0; i < password.length; i++) {
            String hex=toHexString(password[i]);
            if (hex.length()==1) buffer.append('0');
            buffer.append(hex);
        }
        
        return buffer.toString();
        
    }

    /**
     * parse hex string to bytes
     *
     */
    private byte[] toBytes(String hexString) {
        byte[] bytes = new byte[hexString.length()/2];
        for(int i=0, h=0; i<bytes.length; i++, h+=2) {
            bytes[i] = (byte)Integer.parseInt(hexString.substring(h, h+2), 16);
        }
        return bytes;
    }
    
    /**
     * get hex string for the passed byte
     *
     */
    private String toHexString(byte in) {
        
        byte ch = 0x00;
        int i = 0;
        String pseudo[] = {
            "0", "1", "2",
            "3", "4", "5", "6", "7", "8",
            "9", "A", "B", "C", "D", "E",
            "F"
        };
        
        StringBuffer out = new StringBuffer(2);
        
        ch = (byte) (in & 0xF0);
        ch = (byte) (ch >>> 4);
        ch = (byte) (ch & 0x0F);
        out.append(pseudo[ (int) ch]);
        ch = (byte) (in & 0x0F);
        out.append(pseudo[ (int) ch]);
        
        return out.toString();
        
    }
    
    /**
     * reset the profile form and all it's elements
     *
     */
    private void resetProfileForm() {
        profileForm=null;
        profileNameField=null;
        profileHostField=null;
        profilePortField=null;
        profileMACField=null;
        profileUsePasswordGroup=null;
        profilePasswordField=null;
        profileRepeatGauge=null;
        title1=null;
        title2=null;
        title3=null;
    }
    
    /**
     * create and send the magick packet
     *
     */
    private void sendMagicPacket() throws Exception {
        try {
            
            int index=profilesList.getSelectedIndex();
            String currentProfileName=profilesList.getString(index);
            Profile currentProfile=ProfileManager.getInstance().loadProfile(currentProfileName);
            
            
            byte packet[] = null;
            
            if (!currentProfile.isUsePassword()){
                packet=new byte[ 6 + 16*6 ];//FF FF FF FF FF FF + 16 MAC
            } else {
                packet=new byte[ 6 + 16*6 + currentProfile.getPassword().length ];//FF FF FF FF FF FF + 16 MAC + pwd
            
            }
            
            for (int i=0; i<6; i++) {//header
                packet[i]=(byte)0xFF;
            }
            
            byte[] MAC=currentProfile.getMAC();
            for (int i=1; i<=16; i++){//16 MAC
                System.arraycopy(MAC, 0, packet, 6*i, MAC.length);
            }
            
            byte[] password=currentProfile.getPassword();
            if (currentProfile.isUsePassword()){//password
                System.arraycopy(password, 0, packet, 6*17, password.length);
            }
            
            String url = "datagram://" + currentProfile.getHost().trim() + ":" + currentProfile.getPort();
            
            for (int i=0; i < currentProfile.getRepeat(); i++) {
                UDPDatagramConnection connection = (UDPDatagramConnection)Connector.open(url, Connector.WRITE, true);
                Datagram datagram = connection.newDatagram(packet, packet.length);
                connection.send(datagram);
                
                try {//sleep a little before closing
                    Thread.sleep(DELAY);
                } catch (Exception e) {
                    //don't care
                }
                
                connection.close();
                
                try {//sleep a little before next packet
                    Thread.sleep(DELAY);
                } catch (Exception e) {
                    //don't care
                }
            }
            //System.out.println("sent "+packet.length+" bytes - "+formatMAC(packet));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        
    }

    /**
     * used to control repeat gauge: if set to 0 reset to minimum (1)
     *
     */
    public void itemStateChanged(Item item) {
        if (item==profileRepeatGauge){
            if (profileRepeatGauge.getValue()==0){
                profileRepeatGauge.setValue(1);//minimum
            }
        }
    }
    
}
