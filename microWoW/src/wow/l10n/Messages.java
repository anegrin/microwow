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
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreNotOpenException;

/**
 * this class handles bundles and get messages from them
 *
 * @author alessandro
 * @version 0.1
 */
public class Messages {

    //record store name
    private static final String STORE_NAME="l10n";
    
    //available langs
    private static final String IT="it";
    private static final String EN="en";
    private static final String[] AVAILABLE_LANGUAGES={IT, EN};
    private static final String DEFAULT_LANG=EN;
    
    //singleton instance
    private static Messages instance;
    
    //current lang and its index
    private String language;
    private int languageIndex;

    //bundles
    private Bundle bundleIT;
    private Bundle bundleEN;
    
    /** Creates a new instance of Strings */
    private Messages() {
    }
    
    /**
     * get the singleton instance
     *
     */
    public static synchronized Messages getInstance(){
        if (instance==null){
            instance=new Messages();
            instance.initLanguage();
            instance.calculateLanguageIndex();
        }
        
        return instance;
    }

    /**
     * get current language
     *
     */
    public String getLanguage() {
        return language;
    }

    /**
     * set current language by string
     *
     */
    public synchronized void setLanguage(String language) {
        
        //only if lang changes...
        if (!this.language.equals(language)){
            changeLanguage(language);
            this.language = language;
            calculateLanguageIndex();
        }
    }

    /**
     * init the language from record store or, if not present
     * from the system
     *
     */
    private void initLanguage() {
        String language=null;
        
        //TODO: activate language storage when MIDlet allows it
        language=getLanguageFromSystem();
        
        /*RecordStore store=null;
        try {
            store=RecordStore.openRecordStore(STORE_NAME, true);
            if (store.getNumRecords()==0){
                language=getLanguageFromSystem();
                
                byte[] record=language.getBytes();
                store.addRecord(record, 0, record.length);
            } else {
                language=new String(store.getRecord(1));
            }
        } catch (RecordStoreException rse) {
            //rse.printStackTrace();
            language=getLanguageFromSystem();
        } finally {
            if (store!=null){
                try {
                    store.closeRecordStore();
                } catch (RecordStoreNotOpenException rsnoe) {
                    //ignore
                    //rsnoe.printStackTrace();
                } catch (RecordStoreException rse) {
                    //ignore
                    //rse.printStackTrace();
                }
            }
        }*/
        
        this.language=language;
        
    }

    /**
     * change the language in  the store
     *
     */
    private void changeLanguage(String language) {
        RecordStore store=null;
        try {
            store=RecordStore.openRecordStore(STORE_NAME, true);
            byte[] record=language.getBytes();
            if (store.getNumRecords()==0){
                store.addRecord(record, 0, record.length);
            } else {
                store.setRecord(1, record, 0, record.length);
            }
        } catch (RecordStoreException rse) {
            //rse.printStackTrace();
        } finally {
            if (store!=null){
                try {
                    store.closeRecordStore();
                } catch (RecordStoreNotOpenException rsnoe) {
                    //ignore
                    //rsnoe.printStackTrace();
                } catch (RecordStoreException rse) {
                    //ignore
                    //rse.printStackTrace();
                }
            }
        }
    }

    /**
     * get language from the system
     *
     */
    private String getLanguageFromSystem() {
        String systemLang=System.getProperty("microedition.locale");
        if (systemLang==null){
            return DEFAULT_LANG;
        } else if (systemLang.length()>2) {
            return systemLang.substring(0, 2);
        } else {
            return systemLang;
        }
    }

    /**
     * get a localized message
     *
     */
    public String getMessage(String key) {
        String value=getCurrentBundle().getMessage(key);
        
        if (value==null) {
            return "("+getLanguage()+") "+key;
        } else {
            return value;
        }
    }

    public String[] getAvailableLanguages() {
        return AVAILABLE_LANGUAGES;
    }
    
    public int getLanguageIndex(){
        return languageIndex;
    }
    
    /**
     * set language by index
     *
     */
    public void setLanguageIndex(int index){
        setLanguage(getAvailableLanguages()[index]);
    }

    /**
     * get the current language position
     *
     */
    private void calculateLanguageIndex() {
        boolean found=false;
        int i=0;
        while(!found && i<getAvailableLanguages().length){
            found=getAvailableLanguages()[i].equals(getLanguage());
            i++;
        }
        if (found) languageIndex=i-1;
    }
    
    /**
     * get the current bundle
     *
     */
    private Bundle getCurrentBundle(){
        if (IT.equals(getLanguage())){
            if (bundleIT==null) bundleIT=new BundleIT();
            return bundleIT;
        } else {//else is en
            if (bundleEN==null) bundleEN=new BundleEN();
            return bundleEN;
        }
    }

    
    /**
     * get language alias by string
     *
     */
    public String getLanguageAlias(String language) {
        if (IT.equals(language)){
            if (bundleIT==null) bundleIT=new BundleIT();
            return bundleIT.getAlias();
        } else if (EN.equals(language)) {//else is en
            if (bundleEN==null) bundleEN=new BundleEN();
            return bundleEN.getAlias();
        } else {
            return language;
        }
    }
    
    /**
     * get language alias by position
     *
     */
    public String getLanguageAlias(int index) {
        return getLanguageAlias(getAvailableLanguages()[index]);
    }
    
    
    
}
