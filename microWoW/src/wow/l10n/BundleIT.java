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
 * italian bundle
 *
 * @author alessandro negrin
 * @version 0.1
 */
public class BundleIT extends Bundle {
    
    private static final String ALIAS="Italiano";
    
    private static final String[][] MESSAGES={
        //commands
        {"exitCommand.label", "Esci"},
        {"newCommand.label", "Nuovo"},
        {"wakeUpCommand.label", "Sveglia"},
        {"deleteCommand.label", "Cancella"},
        {"editCommand.label", "Modifica"},
        {"okCommand.label", "OK"},
        {"cancelCommand.label", "Annulla"},
        //screens
        {"profilesList.title", "Profili"},
        {"confirmDeletionAlert.title", "Cancellazione profilo"},
        {"confirmDeletionAlert.text", "Per favore conferma la cancellazione del profilo selezionato"},
        {"confirmWakeUpAlert.title", "Risveglio sistema"},
        {"confirmWakeUpAlert.text", "Procedere con il risveglio del sistema remoto?"},
        {"profileForm.title", "Profilo"},
        {"wakedUpAlert.title", "Successo"},
        {"wakedUpAlert.text", "Magic Packet inviato con successo"},
        {"notWakedUpAlert.title", "Fallimento"},
        {"notWakedUpAlert.text", "Impossibile inviare il Magic Packet"},
        {"genericErrorAlert.title", "Errore"},
        {"wakeUpScreen.title", "Invio in corso..."},
        {"wakeUpScreen.text", "Invio Magic Packet"},
        //items
        {"title1.text", "Informazioni"},
        {"title2.text", "Rete"},
        {"title3.text", "Sicurezza"},
        {"profileNameField.title","Nome"},
        {"profileHostField.title","Host"},
        {"profilePortField.title","Porta"},
        {"profileMACField.title","MAC"},
        {"profileRepeatGauge.title","Pacchetti da inviare"},
        {"profileUsePasswordGroup.element.0","Invia password"},
        {"profilePasswordField.title","Password"},
        //messages
        {"message.noProfileToEdit", "Non ci sono profili da modificare"},
        {"message.cantDeleteProfile", "Impossibile cancellare il profilo "},
        {"message.cantSaveProfile", "Impossibile salvare il profilo "},
        {"message.cantLoadProfile", "Impossibile caricare il profilo "},
        {"message.nameMandatory", "Il nome del profilo è obbligatorio"},
        {"message.hostMandatory", "Host è obbligatorio"},
        {"message.portMandatory", "La porta è obbligatoria"},
        {"message.passwordLength", "La password deve avere 8 o 12 caratteri"},
        {"message.cantParsePassword", "Impossibile decodificare la password; deve essere nella forma 1A2B3C4D o 1A2B3C4D5E6F"},
        {"message.MACRequired", "MAC è obbligtorio"},
        {"message.MACLength", "MAC deve avere 17 caratteri"},
        {"message.cantParseMAC", "Impossibile decodificare MAC; deve essere nella forma 1A:2B:3C:4D:5E:6F"},
        {"message.passwordFragmentIs", "Il frammento di password mancante: "}
    };
    
    private Hashtable messages;
    
    /** Creates a new instance of BundleIT */
    BundleIT() {
        super(MESSAGES);
    }
    
    public String getAlias() {
        return ALIAS;
    }
    
    
}
