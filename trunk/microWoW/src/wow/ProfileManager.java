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

import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreNotFoundException;

/**
 * this class manage profiles storage
 *
 * @author anegrin
 * @version 0.2
 */
public class ProfileManager {
    
    private static ProfileManager instance=new ProfileManager();
    
    private byte BYTE_TRUE=1;
    private byte BYTE_FALSE=0;
    
    /** Creates a new instance of ProfileManager */
    private ProfileManager() {
    }
    
    /**
     * get the singleton instance
     *
     */
    static ProfileManager getInstance() {
        return instance;
    }
    
    /**
     * get all profiles names
     *
     */
    public String[] getProfileNames(){
        return RecordStore.listRecordStores();
    }
    
    /**
     * save the passed profile (existing or new)
     *
     */
    public void saveProfile(Profile profile) throws RecordStoreException {
        RecordStore rs=null;
        try {
            try {
                deleteProfile(profile.getName());
            } catch (RecordStoreNotFoundException rsnfe) {
                //don't care; it's a new one
            }
            
            rs=RecordStore.openRecordStore(profile.getName(), true, RecordStore.AUTHMODE_PRIVATE, true);
            
            byte[] host=profile.getHost().getBytes();
            rs.addRecord(host, 0, host.length);
            
            byte[] port=intToByteArray(profile.getPort());
            rs.addRecord(port, 0, port.length);
            
            byte[] MAC=profile.getMAC();
            rs.addRecord(MAC, 0, MAC.length);
            
            byte[] usePasswordAsBoolean= profile.isUsePassword() ? new byte[]{BYTE_TRUE} : new byte[]{BYTE_FALSE};
            rs.addRecord(usePasswordAsBoolean, 0, usePasswordAsBoolean.length);
            
            byte[] password=profile.getPassword();
            rs.addRecord(password, 0, password.length);
            
            byte[] repeat=intToByteArray(profile.getRepeat());
            rs.addRecord(repeat, 0, repeat.length);
            
        } finally {
            rs.closeRecordStore();
        }
    }
    
    /**
     * load an existing profile
     *
     */
    public Profile loadProfile(String name) throws RecordStoreException {
        RecordStore rs=null;
        try {
            
            rs=RecordStore.openRecordStore(name, false);
            
            byte[] host=rs.getRecord(1);
            byte[] port=rs.getRecord(2);
            byte[] MAC=rs.getRecord(3);
            byte[] usePassword=rs.getRecord(4);
            byte[] password=rs.getRecord(5);
            byte[] repeat=rs.getRecord(6);
            
            boolean usePasswordAsBoolean=(usePassword[0]==BYTE_TRUE);
            
            Profile profile=new Profile(name, new String(host), byteArrayToInt(port), MAC, usePasswordAsBoolean, password, byteArrayToInt(repeat));
            
            return profile;
        } finally {
            rs.closeRecordStore();
        }
    }
    
    /**
     * delete an existing profile
     *
     */
    public void deleteProfile(String name) throws RecordStoreException{
        RecordStore.deleteRecordStore(name);
    }
    
    
    /**
     * get bytes representation of an int
     *
     */
    private byte[] intToByteArray(int value) {
        
        return new byte[]{
            (byte)(value >>> 24),
            (byte)(value >> 16 & 0xff),
            (byte)(value >> 8 & 0xff),
            (byte)(value & 0xff)
        };
    }
    
    /**
     * get int representation of some bytes
     *
     */
    private int byteArrayToInt(byte[] b) {
        int value = 0;
        for (int i = 0; i < 4; i++) {
            int shift = (4 - 1 - i) * 8;
            value += (b[i] & 0xff) << shift;
        }
        return value;
    }
    
    
    /**
     * this class encapsulates profile infos
     *
     * @author anegrin
     * @version 0.2
     */
    static class Profile{
        
        private String host;
        private int port;
        private byte[] MAC;
        private String name;
        private boolean usePassword;
        private byte[] password;
        private int repeat;
        
        Profile(String name, String host, int port, byte[] MAC, boolean usePassword, byte[] password, int repeat){
            this.name=name;
            this.host=host;
            this.port=port;
            this.MAC=MAC;
            this.usePassword=usePassword;
            this.password=password;
            this.repeat=repeat;
        }
        
        public String getHost() {
            return host;
        }
        
        public void setHost(String host) {
            this.host = host;
        }
        
        public int getPort() {
            return port;
        }
        
        public void setPort(int port) {
            this.port = port;
        }
        
        public byte[] getMAC() {
            return MAC;
        }
        
        public void setMAC(byte[] MAC) {
            this.MAC = MAC;
        }
        
        public String getName() {
            return name;
        }
        
        public String toString(){
            return getName();
        }
        
        public boolean isUsePassword() {
            return usePassword;
        }
        
        public void setUsePassword(boolean usePassword) {
            this.usePassword = usePassword;
        }
        
        public byte[] getPassword() {
            return password;
        }
        
        public void setPassword(byte[] password) {
            this.password = password;
        }
        
        public int getRepeat() {
            return repeat;
        }
        
        public void setRepeat(int repeat) {
            this.repeat = repeat;
        }
        
    }
    
}
