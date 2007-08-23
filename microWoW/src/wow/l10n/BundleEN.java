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
package wow.l10n;

import java.util.Hashtable;

/**
 * english bundle
 *
 * @author alessandro negrin
 * @version 0.1
 */
public class BundleEN extends Bundle {
    
    private static final String ALIAS="English";
    
    private static final String[][] MESSAGES={
        //commands
        {"exitCommand.label", "Exit"},
        {"newCommand.label", "New"},
        {"wakeUpCommand.label", "Wake up"},
        {"deleteCommand.label", "Delete"},
        {"editCommand.label", "Edit"},
        {"okCommand.label", "OK"},
        {"cancelCommand.label", "Cancel"},
        //screens
        {"profilesList.title", "Profiles"},
        {"confirmDeletionAlert.title", "Profile deletion"},
        {"confirmDeletionAlert.text", "Please confirm current profile deletion"},
        {"confirmWakeUpAlert.title", "System wake up"},
        {"confirmWakeUpAlert.text", "Proceed to remote system wake up"},
        {"profileForm.title", "Profile"},
        {"wakedUpAlert.title", "Success"},
        {"wakedUpAlert.text", "Magic Packet successfully sent"},
        {"notWakedUpAlert.title", "Failure"},
        {"notWakedUpAlert.text", "Can't send Magic Packet"},
        {"genericErrorAlert.title", "Error"},
        {"wakeUpScreen.title", "Sending..."},
        {"wakeUpScreen.text", "Sending Magic Packet"},
        //items
        {"title1.text", "Informations"},
        {"title2.text", "Network"},
        {"title3.text", "Security"},
        {"profileNameField.title","Name"},
        {"profileHostField.title","Host"},
        {"profilePortField.title","Port"},
        {"profileMACField.title","MAC"},
        {"profileRepeatGauge.title","Packets to send"},
        {"profileUsePasswordGroup.element.0","Send password"},
        {"profilePasswordField.title","Password"},
        //messages
        {"message.noProfileToEdit", "There's no profile to edit"},
        {"message.cantDeleteProfile", "Can't delete profile "},
        {"message.cantSaveProfile", "Can't save profile "},
        {"message.cantLoadProfile", "Cant' load profile "},
        {"message.nameMandatory", "Profile name is mandatory"},
        {"message.hostMandatory", "Host is mandatory"},
        {"message.portMandatory", "Port is mandatory"},
        {"message.passwordLength", "Password must have 8 or 12 characters"},
        {"message.cantParsePassword", "Can't decode password; it has to be in the form 1A2B3C4D or 1A2B3C4D5E6F"},
        {"message.MACRequired", "MAC is mandatory"},
        {"message.MACLength", "MAC must have 17 characters"},
        {"message.cantParseMAC", "Can't decode MAC; it has to be in the form 1A:2B:3C:4D:5E:6F"},
        {"message.passwordFragmentIs", "Il frammento di password mancante: "}
    };

    private Hashtable messages;
    
    /** Creates a new instance of BundleIT */
    BundleEN() {
        super(MESSAGES);
    }

    public String getAlias() {
        return ALIAS;
    }
    
    
}
